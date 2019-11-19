/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;



////////////////////////////////////////////////////////////////////////////////
//%P-  +++++ End of ignored starting text - place for imports +++++

import eu.pedu.adv19s_fw.utilities.CallerReporter.Level;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.util.Locale;

import java.util.function.Consumer;

//%X+ xxxxx Start of the overjumped text with declaration of the envelope xxxxx
/*******************************************************************************
 *<pre>
 * Previous:  No - this is a newly defined class
 * Following: No
 *
 * Project  Ppp
 *   + Aded
 *   - Removed
 *   ~ Changed
 *
 *</pre>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
//public class Reporter_000_
//{    private Reporter_000_() {}   static
////////////////////////////////////////////////////////////////////////////////
//%X- ----- End of the overjumped text with declaration of the envelope -----


/*******************************************************************************
 * Instance třídy {@code IndentingReporter} představují zpravodaje,
 * kteří jsou schopni získávat informace o volajících metodách
 * a zapisovat požadované informace do zadaného výstupního proudu tak,
 * aby vynikla hierarchie volání, případně jiná požadovaná hierarchie.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class IndentingReporter extends IndentingPrintStream
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================

    /** Původní standardní systémový výstup. */
    private static volatile PrintStream originalSystemOut;

    /** Odsazující standardní systémový výstup. */
    private static volatile IndentingReporter mySystemOut;



//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS AND SETTERS =================================================

    /***************************************************************************
     * Vrátí standardní výstup do původního nastavení
     * a uvolní aktuální verzi zpravodaje zapisujícího na standardní výstup.
     *
     * @return Byl-li nastaven zpravodaj zapisující na standardní výstup,
     *         který byl vypnut, vrátí {@code true}, jinak vrátí {@code false}
     */
    public static synchronized boolean returnSystemOut()
    {
        if (originalSystemOut == null) {
            return false;
        }
        System.setOut(originalSystemOut);
        originalSystemOut = null;
        mySystemOut       = null;
        return true;
    }



//== OTHER NON-PRIVATE CLASS METHODS ===========================================

    /***************************************************************************
     * Vrátí řetězec s jednoduchým názvem třídy a její metody
     * volající tuto metodu.
     *
     * @return Jednoduchý název třídy a její metody volající tuto metodu
     */
    public static String getCallerName()
    {
        String callerName = CallerReporter.getCallerName(Level.CLASS, 1);
        return callerName;
    }



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vrátí instanci zpravodaje zapisující na standardní výstup.
     * Pokud instance zapisující na standardní výstup neexistuje,
     * vytvoří ji a nastaví jí kódování UTF-8.
     * Současně přesměruje standardní výstup na její odsazující proud.
     * To se hodí v případě, kdy bychom chtěli na standardní výstup
     * zapisovat další informace a využívat přitom odsazování.
     * <p>
     * Potřebujeme-li změnit kódování aktuálního zpravodaje,
     * je třeba nejprve odebrat původního zpravodaje
     * a pak požádat o nového zpravodaje se zadaným kódováním výstupu.
     *
     * @return Zpravodaj zapisující na standardní výstup
     */
    public static IndentingReporter getSystemOut()
    {
        return getSystemOut("UTF-8");
    }


    /***************************************************************************
     * Vrátí instanci zpravodaje zapisující na standardní výstup.
     * Pokud instance zapisující na standardní výstup neexistuje,
     * vytvoří ji a nastaví jí zadané kódování.
     * Současně přesměruje standardní výstup na její odsazující proud.
     * To se hodí v případě, kdy bychom chtěli na standardní výstup
     * zapisovat další informace a využívat přitom odsazování.
     * <p>
     * Potřebujeme-li změnit kódování aktuálního zpravodaje,
     * je třeba nejprve odebrat původního zpravodaje
     * a pak požádat o nového zpravodaje se zadaným kódováním výstupu.
     *
     * @param  codepage  Použité kódování výstupu
     * @return Zpravodaj zapisující na standardní výstup
     */
    public static synchronized IndentingReporter getSystemOut(String codepage)
    {
        if (originalSystemOut == null) {
            originalSystemOut = System.out;
            mySystemOut       = getInstance(System.out, codepage);
            System.setOut(mySystemOut);
        }
        return mySystemOut;
    }


    /***************************************************************************
     * Vytvoří zpravodaje zapisujícího do zadaného výstupního proudu
     * a používajícího kódování UTF-8.
     *
     * @param stream  Dekorovaný proud
     * @return Vytvořený zpravodaj
     */
    public static IndentingReporter getInstance(OutputStream stream)
    {
        return getInstance(stream, "UTF-8");
    }


    /***************************************************************************
     * Vytvoří zpravodaje zapisujícího do zadaného výstupního proudu
     * a používajícího zadané kódování.
     *
     * @param stream   Dekorovaný proud
     * @param codepage Kódová stránka vytvořeného proudu
     * @return Vytvořený zpravodaj
     */
    public static IndentingReporter getInstance(OutputStream stream,
                                                String codepage)
    {
        IndentingReporter reporter;
        try {
            reporter = new IndentingReporter(stream, codepage);
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(
                "\nZadaná kódová stránka není podporována: " + codepage, ex );
        }
        return reporter;
    }


    /***************************************************************************
     * Vytvoří zpravodaje zapisujícího do zadaného výstupního proudu
     * a používajícího zadané kódování.
     *
     * @param stream   Dekorovaný proud
     * @param codepage Kódová stránka vytvořeného proudu
     * @throws UnsupportedEncodingException Při zadání nepodporovaného kódování
     */
    public IndentingReporter(OutputStream stream, String codepage)
           throws UnsupportedEncodingException
    {
        super(stream, codepage);
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí informaci o tom, zda je standardní výstup přesměrován
     * na odsazující proud dotazované instance.
     *
     * @return Informace o přesměrování standardního výstupu
     */
    @Override
    public boolean isSystemOut()
    {
        return (this == mySystemOut);
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Vypíše jednoduchý název třídy a její metody volající tuto metodu,
     * zvýší pro příští řádky odsazení a odřádkuje.
     *
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    public IndentingReporter reportStart()
    {
        return reportStart(1, (String)null);
    }


    /***************************************************************************
     * Zmenší pro příští řádky počáteční odsazení, odřádkuje a na další řádek
     * vypíše jednoduchý název třídy a její metody, která tuto metodu zavolala.
     *
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    public IndentingReporter reportEnd()
    {
        return reportEnd(1, (String)null);
    }


    /***************************************************************************
     * Vypíše jednoduchý název třídy a její metody, která tuto metodu zavolala,
     * zvýší pro příští řádky počáteční odsazení,
     * odřádkuje a vypíše zadaný text.
     *
     * @param text  Text, vypsaný za řádkem s oznámením vstupu do metody
     *              Je-li zadán prázdný řetězec nebo {@code null},
     *              řádek se nevygeneruje.
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    public IndentingReporter reportStart(String text)
    {
        return reportStart(1, text);
    }


    /***************************************************************************
     * Vypíše zadaný text, zmenší pro příští řádky počáteční odsazení,
     * odřádkuje a na další řádek vypíše jednoduchý název třídy a její metody
     * volající tuto metodu.
     *
     * @param text  Text, vypsaný za řádkem s oznámením vstupu do metody
     *              Je-li zadán prázdný řetězec nebo {@code null},
     *              řádek se nevygeneruje.
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    public IndentingReporter reportEnd(String text)
    {
        return reportEnd(1, text);
    }


    /***************************************************************************
     * Vypíše jednoduchý název třídy a její metody volající tuto metodu, zvýší
     * pro příští řádky počáteční odsazení, odřádkuje a vypíše zadaný text.
     *
     * @param depth Hloubka dotazu na metodu, jejíž název se vypisuje,
     *              pro případ, že by se neměl vypsat název metody bezprostředně
     *              volající tuto metodu (pro ni je {@code depth=1}, ale název
     *              některé z metod, které ji volají až zprostředkovaně &ndash;
     *              viz {@link CallerReporter#getCallerName(int, int)}
     * @param text  Text, vypsaný za řádkem s názvem metody
     *              Je-li zadán prázdný řetězec nebo {@code null},
     *              řádek se nevygeneruje.
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    public IndentingReporter reportStart(int depth, String text)
    {
        String callerName = CallerReporter.getCallerName(Level.CLASS, depth+1);
        append(callerName).indent();
        if (text != null) {
            append(text);
            append('\n');
        }
        return this;
    }


    /***************************************************************************
     * Je-li zadán text, odřádkuje a vypíše jej;
     * pak zmenší pro příští řádky počáteční odsazení,
     * odřádkuje a na další řádek vypíše jednoduchý název třídy a její metody
     * volající tuto metodu.
     *
     * @param depth Hloubka dotazu na metodu, jejíž název se vypisuje,
     *              pro případ, že by se neměl vypsat název metody bezprostředně
     *              volající tuto metodu (pro ni je {@code depth=1}, ale název
     *              některé z metod, které ji volají až zprostředkovaně &ndash;
     *              viz {@link CallerReporter#getCallerName(int, int)}
     * @param text  Text, vypsaný před řádkem s oznámením výstupu z metody
     *              Je-li zadán prázdný řetězec nebo {@code null},
     *              řádek se nevygeneruje.
     * @return Jednoduchý název třídy a její metody volající tuto metodu
     */
    public IndentingReporter reportEnd(int depth, String text)
    {
        if (text != null) {
            append(text);
        }
        String callerName = CallerReporter.getCallerName(Level.CLASS, depth+1);
        outdent().append(callerName).append('\n');
        return this;
    }


    /***************************************************************************
     * Vypíše jednoduchý název třídy a její metody volající tuto metodu, zvýší
     * pro příští řádky počáteční odsazení, odřádkuje a provede zadanou akci.
     *
     * @param action Akce, která se má provést
     *               po vypsání řádku s názvem třídy a metody
     * @return Jednoduchý název třídy a její metody volající tuto metodu
     */
    public IndentingReporter reportStart(Consumer<IndentingReporter> action)
    {
        return reportStart(1, action);
    }


    /***************************************************************************
     * Provede zadanou akci, zmenší pro příští řádky počáteční odsazení,
     * odřádkuje a na další řádek vypíše jednoduchý název třídy a její metody
     * volající tuto metodu.
     *
     * @param action Akce, která se má provést
     *               po vypsání řádku s názvem třídy a metody
     * @return Jednoduchý název třídy a její metody volající tuto metodu
     */
    public IndentingReporter reportEnd(Consumer<IndentingReporter> action)
    {
        return reportEnd(1, action);
    }


    /***************************************************************************
     * Vypíše jednoduchý název třídy a její metody volající tuto metodu, zvýší
     * pro příští řádky počáteční odsazení, odřádkuje a provede zadanou akci.
     *
     * @param depth Hloubka dotazu na metodu, jejíž název se vypisuje,
     *              pro případ, že by se neměl vypsat název metody bezprostředně
     *              volající tuto metodu (pro ni je {@code depth=1}, ale název
     *              některé z metod, které ji volají až zprostředkovaně &ndash;
     *              viz {@link CallerReporter#getCallerName(int, int)}
     * @param action Akce, která se má provést
     *               po vypsání řádku s názvem třídy a metody
     * @return Jednoduchý název třídy a její metody volající tuto metodu
     */
    public IndentingReporter reportStart(int depth, Consumer<IndentingReporter> action)
    {
        String callerName = CallerReporter.getCallerName(Level.CLASS, depth+1);
        append(callerName).indent();
        action.accept(this);
        return this;
    }


    /***************************************************************************
     * Provede zadanou akci, zmenší pro příští řádky počáteční odsazení,
     * odřádkuje a na další řádek vypíše jednoduchý název třídy a její metody
     * volající tuto metodu.
     *
     * @param depth Hloubka dotazu na metodu, jejíž název se vypisuje,
     *              pro případ, že by se neměl vypsat název metody bezprostředně
     *              volající tuto metodu (pro ni je {@code depth=1}, ale název
     *              některé z metod, které ji volají až zprostředkovaně &ndash;
     *              viz {@link CallerReporter#getCallerName(int, int)}
     * @param action Akce, která se má provést
     *               po vypsání řádku s názvem třídy a metody
     * @return Jednoduchý název třídy a její metody volající tuto metodu
     */
    public IndentingReporter reportEnd(int depth, Consumer<IndentingReporter> action)
    {
        String callerName = CallerReporter.getCallerName(Level.CLASS, depth+1);
        action.accept(this);
        outdent().append(callerName).append('\n');
        return this;
    }


    /***************************************************************************
     * Do odsazovaného výstupu vypíše zadaný text.
     *
     * @param  text Vypisovaný text
     * @return Odkaz na instanci pro možnost zřetězení
     */
    public IndentingReporter report(String text)
    {
        append(text);
        return this;
    }


    /***************************************************************************
     * Do odsazovaného výstupu vypíše zadaný text a odřádkuje.
     *
     * @param text Vypisovaný text
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    public IndentingReporter reportln(String text)
    {
        append(text).append('\n');
        return this;
    }



//== Inherited methods with casted return values ===============================

    /**********************************************************************
     * {@inheritDoc}
     *
     * @param csq   Připojovaná posloupnost znaků
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    @Override
    public IndentingReporter append(CharSequence csq)
    {
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
    public IndentingReporter append(CharSequence csq, int start, int end)
    {
        super.append(csq, start, end);
        return this;    //Efektivnější než přetypování vrácené hodnoty
    }


    /**********************************************************************
     * {@inheritDoc}
     *
     * @param c Přidávaný znak
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    @Override
    public IndentingReporter append(char c)
    {
        super.append(c);
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
    public IndentingReporter format(String format, Object... args)
    {
        super.format(format, args);
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
    public IndentingReporter format(Locale l, String format, Object... args)
    {
        super.format(l, format, args);
        return this;    //Efektivnější než přetypování vrácené hodnoty
    }


    /***************************************************************************
     * Přidá další úroveň odsazení a odřádkuje.
     * Další řádek tak bude o jedno odsazení bohatší.
     *
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    @Override
    public IndentingReporter indent()
    {
        super.indent();
        return this;    //Efektivnější než přetypování vrácené hodnoty
    }


    /***************************************************************************
     * Odebere jednu úroveň odsazení a odřádkuje.
     * Další řádek tak bude o jedno odsazení chudší.
     *
     * @return Odkaz na svoji instanci, aby bylo možno příkazy řetězit
     */
    @Override
    public IndentingReporter outdent()
    {
        super.outdent();
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
    public IndentingReporter printf(String format, Object... args)
    {
        super.printf(format, args);
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
    public IndentingReporter printf(Locale l, String format, Object... args)
    {
        super.printf(l, format, args);
        return this;    //Efektivnější než přetypování vrácené hodnoty
    }



//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
//%U=}
//%E# ---- Start of the overjumped text with closing of the envelope ----
////////////////////////////////////////////////////////////////////////////////
//== QUCK TESTS ================================================================
}
