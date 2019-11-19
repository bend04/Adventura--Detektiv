/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;



////////////////////////////////////////////////////////////////////////////////
//%P-  +++++ End of ignored starting text - place for imports ++++++++++++++++++

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.nio.charset.Charset;

import java.util.Locale;



/*******************************************************************************
 * Instance třídy {@code IndentingPrintStream} představují dekorátory,
 * které "ozdobí" dekorované výstupní proudy schopností
 * zapisovat vystupující informace tak, aby vynikla požadovaná hierarchie.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class IndentingPrintStream extends PrintStream
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Implicitní nastavované kódování. */
    protected static final String DEFAULT_ENCODING =
              //null;
              "windows-1250";
              //"UTF-8";



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Zda neovlivnitelné nastavení původního výstupního proudu vyžaduje
     *  převod vystupujícího textu do čistého ASCII. */
    private static boolean toASCII = false;

    /** Implicitní odsazení pokračovacích řádků. */
    private static String defaultIndentIncrement = "|  ";

    /** Původní standardní systémový výstup. */
    private static volatile PrintStream originalSystemOut;

    /** Odsazující standardní systémový výstup. */
    private static volatile IndentingPrintStream mySystemOut;

    /** Příznak toho, budou-li vytvářené datové proudy vláknově citlivé. */
    private static boolean defaultThreadSensitivity;



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Vrátí implicitní řetězec vkládaný několikrát na počátek každého
     * tištěného řádku, přičemž počet vložení je shodný s úrovní vnoření.
     *
     * @return Implicitní odsazovací řetězec
     */
    public static String getDefaultIndentIncrement()
    {
        return defaultIndentIncrement;
    }


    /***************************************************************************
     * Nastaví implicitní řetězec vkládaný několikrát na počátek každého
     * tištěného řádku, přičemž počet vložení je shodný s úrovní vnoření.
     *
     * @param defaultIncrement Implicitní odsazovací řetězec
     */
    public static void setDefaultIndentIncrement(String defaultIncrement)
    {
        defaultIndentIncrement = defaultIncrement;
    }


    /***************************************************************************
     * Zadá, zda mají odsazované tisky respektovat vlákna.
     * Pokus ano, vytiskne se na počátku každého řádku název daného vlákna.
     *
     * @param sensitive Příznak toho, mají-li tisky respektovat vlákna
     */
    public static void setThreadSensitive(boolean sensitive)
    {
        defaultThreadSensitivity = sensitive;
    }


    /***************************************************************************
     * Není-li ještě standardní výstup dekorován jako odsazovací, učiní tak.
     */
    public static void setSystemOut()
    {
        setSystemOut(DEFAULT_ENCODING);
    }


    /***************************************************************************
     * Není-li ještě standardní výstup dekorován jako odsazovací, učiní tak
     * a přitom mu nastaví zadané kódování.
     * <p>
     * Potřebujeme-li změnit kódování aktuálního výstupu,
     * je třeba nejprve nastavit původní stav (zavolat {@link #returnSystemOut})
     * a pak požádat o nastavení standardního výstupu se zadaným kódováním.
     *
     * @param  encoding  Použité kódování výstupu
     */
    public static synchronized void setSystemOut(String encoding)
    {
        setSystemOut(System.out, encoding);
    }


    /***************************************************************************
     * Není-li ještě standardní výstup dekorován jako odsazovací, učiní tak
     * a přitom mu nastaví zadané kódování.
     * <p>
     * Potřebujeme-li změnit kódování aktuálního výstupu,
     * je třeba nejprve nastavit původní stav (zavolat {@link #returnSystemOut})
     * a pak požádat o nastavení standardního výstupu se zadaným kódováním.
     *
     * @param  outputStream Výstupní proud nastavovaný jako systémový
     */
    public static synchronized void setSystemOut(OutputStream outputStream)
    {
        setSystemOut(outputStream, DEFAULT_ENCODING);
    }


    /***************************************************************************
     * Není-li ještě standardní výstup dekorován jako odsazovací, učiní tak
     * a přitom mu nastaví zadané kódování.
     * <p>
     * Potřebujeme-li změnit kódování systémového výstupu,
     * je třeba nejprve nastavit původní stav (zavolat {@link #returnSystemOut})
     * a pak požádat o nastavení standardního výstupu se zadaným kódováním.
     *
     * @param  outputStream Výstupní proud nastavovaný jako systémový
     * @param  encoding     Použité kódování výstupu
     */
    public static synchronized void setSystemOut(OutputStream outputStream,
                                                 String encoding)
    {
        if (originalSystemOut == null) {
            originalSystemOut = System.out;
            if (outputStream instanceof IndentingPrintStream) {
                IndentingPrintStream ips  = (IndentingPrintStream)outputStream;
                Charset cSet;
                if (encoding == null) {
                    cSet = Charset.defaultCharset();
                }
                else {
                    cSet = Charset.forName(encoding);
                }
                if (! cSet.equals(ips.charset)) {
                    throw new IllegalArgumentException(
                            "\nZnaková sada nastavovaného proudu neodpovídá " +
                            "požadované: " + encoding + " # " + cSet);
                }
                mySystemOut = ips;
            }
            else {
                mySystemOut = indentifyPrintStream(outputStream, encoding);
            }
            System.setOut(mySystemOut);
        }
        else if (mySystemOut != outputStream) {
            throw new IllegalArgumentException(
                    "\nStandardní systémový výstup je již nastaven." +
                    "\nPokud jej chete změnit, musíte nejprve původní " +
                      "nastavení zrušit");
        }
    }


    /***************************************************************************
     * Nastaví, zda neovlivnitelné nastavení původního výstupního proudu
     * vyžaduje převod vystupujícího textu do čistého ASCII.
     *
     * @param toASCII Nastavovaná hodnota
     */
    public static void setToASCII(boolean toASCII)
    {
        IndentingPrintStream.toASCII = toASCII;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Vrátí standardní výstup do původního nastavení.
     *
     * @return Pokud se něco změnilo, vrátí {@code true},
     *         jinak vrátí {@code false}
     */
    public static synchronized boolean returnSystemOut()
    {
        if (originalSystemOut == null) {
            return false;
        }
        if (System.out != mySystemOut) {
            throw new IllegalStateException(
                    "\nNelze vrátit původní nastvení objektu System.out, " +
                      "protože je někdo mezitím změnil");
        }
        System.setOut(originalSystemOut);
        originalSystemOut = null;
        mySystemOut       = null;
        return true;
    }




//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Kódování použité v daném proudu. */
    private final Charset charset;

    /** Příznak citlivosti na vlákna. */
    private final boolean threadSensitive;

    /** Řetězec vkládaný na počátek každého tištěného řádku. */
    private final ThreadLocalIndent indent;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Řetězec vkládaný při další úrovni zanoření na konec řetězce
     *  {@link #indent} a po vynoření opět odebíraný. */
    private String indentIncrement = defaultIndentIncrement;

    /** Naposledy používané vlákno. */
    private Thread lastThread;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vrátí zadaný výstupní proud dekorovaný o schopnost řízeně odsazovat
     * vystupující text a vytvořený dekorovaný proud vrátí.
     * Pro vytvořený proud bude použito kódování UTF-8.
     *
     * @param stream  Dekorovaný proud
     * @return Standardní výstupní proud dekorovaný o schopnost odsazování
     */
    public static IndentingPrintStream indentifyPrintStream(
                                                OutputStream stream)
    {
        return indentifyPrintStream(System.out, "UTF-8");
    }


    /***************************************************************************
     * Vrátí zadaný výstupní proud dekorovaný o schopnost řízeně odsazovat
     * vystupující text a vytvořený dekorovaný proud vrátí.
     *
     * @param stream   Dekorovaný proud
     * @param codepage Kódová stránka vytvořeného proudu
     * @return Standardní výstupní proud dekorovaný o schopnost odsazování
     */
    public static IndentingPrintStream indentifyPrintStream(
                                       OutputStream stream, String codepage)
    {
        IndentingPrintStream ips;
        try {
            ips = new IndentingPrintStream(stream, codepage);
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(
                "\nZadaná kódová stránka není podporována: " + codepage, ex );
        }
        return ips;
    }


    /***************************************************************************
     * Vytvoří instanci dekorující zadaný výstupní proud
     * schopností odsazování.
     *
     * @param encoding Kódová stránka vytvořeného proudu
     * @param stream   Dekorovaný výstupní proud
     * @throws UnsupportedEncodingException Při zadání nepodporovaného kódování
     */
    protected IndentingPrintStream(OutputStream stream, String encoding)
              throws UnsupportedEncodingException
    {
        //Pro začátek nastavuji za obalovaný proud standardní výstup,
        //protože něco se tam dát musí a nemohu odkazovat na svoji instanci,
        //(vnitřní třída potřebuje odkaz na sdruženou instanci)
        //dokud není vytvořen rodičovský podobjekt předka
        super(System.out, true, encoding);

        charset = Charset.forName(encoding);

        threadSensitive = defaultThreadSensitivity;

        indent = new ThreadLocalIndent();

        //Nastavím obalovaný proud na vytvořenou instanci své vnitřní třídy
        //(je vnitřní, protože se mnou sdílí odsazovací řetězec),
        //protože má rodičovská třída PrintStream řeší některé reakce
        //na odřídkování sama, a já na ně přitom potřebuji reagovat po svém -
        //tak nastavím reakci do své vnitřní třídy, kterou jí předám k dekoraci.
        //Posloupnost volání je nyní:
        // Já -> PrintStream -> FilterOutputStream -> IndentingOutputStream
        //    (to je má vnitřní třída) -> FilterOutputStream -> stream
        out = new IndentingOutputStream(stream);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí kódování nastavené pro daný odsazovací tiskový proud.
     *
     * @return Nastavené kódování
     */
    public Charset getCharset()
    {
        return charset;
    }


    /***************************************************************************
     * Nastaví  řetězec vkládaný několikrát na počátek každého tištěného řádku,
     * přičemž počet vložení je shodný s úrovní vnoření.
     *
     * @param indentIncrement Zadávaný odsazovací řetězec
     */
    public void setIndentIncrement(String indentIncrement)
    {
        this.indentIncrement = indentIncrement;
    }


    /***************************************************************************
     * Vrátí informaci o tom, zda je standardní výstup přesměrován
     * na odsazující proud dotazované instance.
     *
     * @return Informace o přesměrování standardního výstupu
     */
    public boolean isSystemOut()
    {
        return (this == mySystemOut);
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Přidá jednu úroveň odsazení a odřádkuje.
     * Další řádek tak bude o jedno odsazení bohatší.
     *
     * @return Vrátí odkaz na sebe, aby bylo možno příkazy řetězit
     */
    public IndentingPrintStream indent()
    {
        indent.set(indent.get() + indentIncrement);
        println();
        return this;
    }


    /***************************************************************************
     * Odebere jednu úroveň odsazení a odřádkuje.
     * Další řádek tak bude o jedno odsazení chudší.
     *
     * @return Vrátí odkaz na sebe, aby bylo možno příkazy řetězit
     */
    public IndentingPrintStream outdent()
    {
        if (indent.get().length() < indentIncrement.length()) {
            throw new RuntimeException(
                "\nByla přijata přespočetná žádost o přisazení");
        }
        indent.set(indent.get()
                  .substring(0, indent.get().length()
                              - indentIncrement.length()));
        println();
        return this;
    }



//== Inherited methods with casted return values ===============================

    /**********************************************************************
     * {@inheritDoc}
     * Přebíjím jenom proto, abych dostal požadovaný typ návratové hodnoty.
     *
     * @param csq   Připojovaná posloupnost znaků
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    @Override
    public IndentingPrintStream append(CharSequence csq)
    {
        //Netřeba konvertovat, rodič to převádí na print, který přebíjím
        super.append(csq);
        return this;    //Efektivnější než přetypování vrácené hodnoty
    }


    /**********************************************************************
     * {@inheritDoc}
     *
     * @param csq   Připojovaná posloupnost znaků
     * @param start Index prvního přidávaného znaku
     * @param end   Index znaku za posledním přidávaným znakem
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    @Override
    public IndentingPrintStream append(CharSequence csq, int start, int end)
    {
        if (toASCII) {
            CharSequence cs = (csq == null ? "null" : csq);
            String s = cs.subSequence(start, end).toString();
            s = IO.removeAccents(s);
            super.print(s);
        }
        else {
            super.append(csq, start, end);
        }
        return this;    //Efektivnější než přetypování vrácené hodnoty
    }


    /**********************************************************************
     * {@inheritDoc}
     *
     * @param c Přidávaný znak
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    @Override
    public IndentingPrintStream append(char c)
    {
        if (toASCII) {
            String s = IO.removeAccent(c);
            super.print(s); //Rodičovský append se převádí na print
        }
        else {
            super.append(c);
        }
        return this;    //Efektivnější než přetypování vrácené hodnoty
    }


    /**********************************************************************
     * {@inheritDoc}
     *
     * @param format Formát vraceného textu
     * @param args   Parametry, jejichž hodnoty se budou tisknout
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    @Override
    public IndentingPrintStream format(String format, Object... args)
    {
        if (toASCII) {
            super.format(format, (Object)removeAccents(args));
        }
        else {
            super.format(format, args);
        }
        return this;    //Efektivnější než přetypování vrácené hodnoty
    }


    /**********************************************************************
     * {@inheritDoc}
     *
     * @param l      Použité {@link Locale}
     * @param format Formát vraceného textu
     * @param args   Parametry, jejichž hodnoty se budou tisknout
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    @Override
    public IndentingPrintStream format(Locale l, String format, Object... args)
    {
        if (toASCII) {
            super.format(l, format, (Object)removeAccents(args));
        }
        else {
            super.format(l, format, args);
        }
        return this;    //Efektivnější než přetypování vrácené hodnoty
    }


    /**********************************************************************
     * {@inheritDoc}
     *
     * @param format Formát vraceného textu
     * @param args   Parametry, jejichž hodnoty se budou tisknout
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    @Override
    public IndentingPrintStream printf(String format, Object... args)
    {
        //Rodič to převádí na format, který přebíjím =>
        //stačí upravit typ návratové hodnoty
        super.printf(format, args);
        return this;    //Efektivnější než přetypování vrácené hodnoty
    }


    /**********************************************************************
     * {@inheritDoc}
     * Rodič to převádí na format, který přebíjím
     *
     * @param l      Použité {@link Locale}
     * @param format Formát vraceného textu
     * @param args   Parametry, jejichž hodnoty se budou tisknout
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
    */
    @Override
    public IndentingPrintStream printf(Locale l, String format, Object... args)
    {
        //Rodič to převádí na format, který přebíjím =>
        //stačí upravit typ návratové hodnoty
        super.format(l, format, args);
        return this;    //Efektivnější než přetypování vrácené hodnoty
    }


    /**********************************************************************
     * {@inheritDoc}
     *
     * @param s Zapisovaný řetězec
     */
    @Override
    public void print(String s)
    {
        if (toASCII) {
            s = IO.removeAccents(s);
        }
        super.print(s);
    }


    /**********************************************************************
     * {@inheritDoc}
     *
     * @param b Zapisovaný znak
     */
    @Override
    public void write(int b)
    {
        if (toASCII) {
            String s = IO.removeAccent((char)b);
            super.print(s);
        }
        super.write(b);
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Převede pole objektů na pole stringů,
     * přičemž cestou provede i.převod jednotlivých znaků na ASCII.
     *
     * @param objects Převáděné pole
     * @return Převedené pole
     */
    private static String[] removeAccents(Object[] objects)
    {
        final int L = objects.length;
        String[] strings = new String[L];
        for (int i=0;   i < L;   i++) {
            String s = objects[i].toString();
            s = IO.removeAccents(s);
            strings[i] = s;
        }
        return strings;
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

    /**************************************************************************
     * Instance třídy {@code IndentingOutputStream} představují dekorátory
     * doplňující u dekorovaného proudu operaci odřádkování
     * o vypsání odsazovacího řetězce
     * zpřehledňujícího hierarchickou strukturu vypisovaného textu.
     */
    private class IndentingOutputStream extends FilterOutputStream
    {
        /***********************************************************************
         * Vytvoří objekt dekorující zadaný výstupní proud.
         *
         * @param stream Dekorovaný výstupní proud
         */
        IndentingOutputStream(OutputStream stream)
        {
            super(stream);
        }


        /***********************************************************************
         * Vypíše zadaný znak, přičemž za odřádkování automaticky doplní
         * počáteční odsazovací řetězec.
         *
         * @param b Vypisovaný znak
         * @throws IOException Vypsání se nezdařilo
         */
        @Override
        public synchronized void write(int b) throws IOException
        {
            Thread currentThread = Thread.currentThread();
            if (! currentThread.equals(lastThread)) {
                lastThread = currentThread;
                write('\n');
            }
            super.write(b);
            if (b == '\n') {
                super.write(indent.get().getBytes());
            }
        }


        @Override
        public synchronized void write(byte[] b, int off, int len)
               throws IOException
        {
            super.write(b, off, len);
        }

    }



    /**************************************************************************
     * Instance třídy {@code IndentingOutputStream} představují dekorátory
     * doplňující u dekorovaného proudu operaci odřádkování
     * o vypsání odsazovacího řetězce
     * zpřehledňujícího hierarchickou strukturu vypisovaného textu.
     */
    private class ThreadLocalIndent extends ThreadLocal<String>
    {
        /***********************************************************************
         * Definuje počáteční hodnotu vláknové proměnné
         * s předsazením pro dané vlákno.
         *
         * @return Počáteční hodnota odsazení pro aktuální vlákno
         */
        @Override
        public String initialValue()
        {
            if (threadSensitive) {
                return Thread.currentThread().toString() + ": ";
            }
            else{
                return "";
            }
        }
    }



}
