/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;



/*******************************************************************************
 * Knihovní třída {@code DBG} poskytuje metody pro podmíněný výstup
 * ladicích tisků do předem zadaného proudu, kterým muže byt standardní výstup,
 * standardní chybový výstup, libovolný proud typu {@link PrintStream}
 * nebo soubor, který se v případě potřeby nejprve vytvoří.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public final class DBG
{
//    static { Systém.out.println("CLASS - DBG - START"); }
//    { Systém.out.println("INSTANCE - DBG - START"); }
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Mapa přiřazující zadané výstupní proudy odpovídajícím
     *  odsazujícím proudům. */
    private static final Map<OutputStream, IndentingPrintStream>
                                  OUTPUT_2_INDENTING_STREAM = new HashMap<>();
//
//    /** Seznam výstupních proudů, do nichž se bude zapisovat. */
//    private final static List<PrintStream> PRINT_STREAMS = new ArrayList<>();
//
//    /** Seznam odsazujících výstupních proudů, vytvořených vhodnou dekorací
//     *  zadaných výstupních proudů. */
//    private final static List<IndentingPrintStream> INDENTING_PRINT_STREAMS =
//                                                    new ArrayList<>();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Proměnná, jejíž hodnota ovlivňuje,
     *  zda se budou požadované tisky provádět nebo ne.
     *  Čím vyšší hodnota, tím víc bude realizovaných kontrolních tisků. */
    public static int DEBUG_LEVEL = 1;

//    /** Nastavitelný výstupní proud.
//     *  Nyní již není potřeba, protože je nahrazen kolekcí výstupních proudů,
//     *  které umožňují tisknout do několika proudů současně. */
//    private static PrintStream out = System.out;



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    static {
        addOutputStream(System.out);
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Vrátí aktuální hladinu podrobnosti kontrolních tisků;
     * čím vyšší hodnota, tím víc bude realizovaných kontrolních tisků.
     *
     * @return Aktuální hladina podrobnosti kontrolních tisků
     */
    public static int getDEBUG_LEVEL()
    {
        return DEBUG_LEVEL;
    }


    /***************************************************************************
     * Nastaví aktuální hladinu podrobnosti kontrolních tisků;
     * čím vyšší hodnota, tím víc bude realizovaných kontrolních tisků.
     * Implicitní hodnota je rovna 1, při níž tisknou i ty metody,
     * které nemají explicitně zadán parametr s hladinou tisku.
     * Při hladině 0 již tyto metody netisknou.
     * Metody s explicitně zadaným parametrem hladiny tisků tisknou tehdy,
     * je-li hodnota tohoto parametru {@code <=} aktuální hladina.
     *
     * @param newLevel Požadovaná nová hladina;
     */
    public static void setDEBUG_LEVEL(int newLevel)
    {
        DBG.DEBUG_LEVEL = newLevel;
    }


    /***************************************************************************
     * Vrátí kolekci proudů, do nichž odcházejí tisky
     *
     * @return Kolekce proudů, do nichž odcházejí tisky
     */
    public static Collection<IndentingPrintStream> getOutputStreams()
    {
        return OUTPUT_2_INDENTING_STREAM.values();
    }


    /***************************************************************************
     * Vrátí proud typu {@link IndentingPrintStream} dekorující zadaný proud.
     *
     * @param stream Tiskový proud, zařazený mezi výstupní
     * @return Proud typu {@link IndentingPrintStream} dekorující daný proud
     */
    public static IndentingPrintStream getWrapperOf(PrintStream stream)
    {
        return OUTPUT_2_INDENTING_STREAM.get(stream);
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Přidá zadaný výstupní proud mezi proudy, do nichž se zapisuje
     * a vrátí proud typu {@link IndentingPrintStream}, dekorující zadaný proud;
     * pokud byl zadaný proud již dříve přidán a není jej proto potřeba znovu
     * přidávat, vrátí {@code null}.
     *
     * @param stream Přidávaný tiskový proud
     * @return Nebyl-li proud v seznamu a byl proto přidán, vrátí {@code true},
     *         by-li již v seznamu a nebylo jej proto třeba přidávat,
     *         vrátí {@code false}
     */
    public static IndentingPrintStream addOutputStream(OutputStream stream)
    {
        if (OUTPUT_2_INDENTING_STREAM.containsKey(stream))
        {
            return null;
        } else {
            IndentingPrintStream ips = IndentingPrintStream.
                                       indentifyPrintStream(stream);
            OUTPUT_2_INDENTING_STREAM.put(stream, ips);
            return ips;
        }
    }


    /***************************************************************************
     * Je-li jako parametr zadaná hodnota {@code "out"} nebo
     * {@code "err"}, přidá do seznamu příslušný standardní proud;
     * jiná podoba řetězce definuje cestu k souboru,
     * který má být přidán do seznamu výstupních proudů.
     * Pokud tento soubor neexistuje, metoda se jej pokusí vytvořit.
     * Proudy jsou při přidání automaticky dekorovány jako odsazovací,
     * tj. jako proudy typu {@link IndentingPrintStream}.
     *
     * @param path Název / cesta k proudu přidávanému do seznamu
     */
    public static void addOutputStream(String path)
    {
        switch (path) {
            case "out":
                addOutputStream(System.out);
                break;

            case "err":
                addOutputStream(System.err);
                break;

            default:
                File file = new File(path);
                try {
                    if (! file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream out = new FileOutputStream(file);
                    addOutputStream(out);
                } catch (IOException ex2) {
                    throw new RuntimeException("\nSoubor " + file +
                        " se nepodařilo najít ani vytvořit", ex2);
                }
                break;
        }
    }


    /***************************************************************************
     * Odebere zadaný tiskový proud ze seznamu proudů, do nichž se zapisuje
     * a vrátí informaci o tom, jestli se tím seznam změnil,
     * tj. jestli byl zadaný proud před žádostí o odebrání v seznamu.
     * Protože se zadává instance odebíraného proudu, tak tiskové proudy
     * přidávané zadáním názvu {@code "out"} nebo {@code "err"}
     * není možno obdobně odebrat.
     * Je-li proto očekávatelné odebrání některého z nich,
     * je vhodnější je vložit zadáním instance než zadáním textu.
     *
     * @param outputStream Odebíraný tiskový proud
     * @return Byl-li proud v seznamu, vrátí {@code true},
     *         jinak vrátí {@code false}
     */
    public static boolean removeOutputStream(OutputStream outputStream)
    {
        IndentingPrintStream stream =
                             OUTPUT_2_INDENTING_STREAM.remove(outputStream);
        return (stream != null);
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL > 0}, vypíše zadanou hodnotu
     * bez závěrečného odřádkování.
     *
     * @param value Vypisovaná hodnota
     */
    public static void pr(Object value)
    {
        if (DEBUG_LEVEL > 0) {
            print(value);
        }
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL >= dbgLevel}, vypíše zadanou hodnotu
     * bez závěrečného odřádkování.
     *
     * @param dbgLevel Hladina významnosti určující, zda se bude vypisovat
     * @param value    Vypisovaná hodnota
     */
    public static void pr(int dbgLevel, Object value)
    {
        if (DEBUG_LEVEL >= dbgLevel) {
            print(value);
        }
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL > 0}, vypíše zadané hodnoty
     * v zadaném formátu.
     *
     * @param format Formát specifikující způsob vypsání zadaných hodnot
     * @param values Vypisované hodnoty
     */
    public static void prf(String format, Object... values)
    {
        if (DEBUG_LEVEL > 0) {
            printf(format, values);
        }
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL >= hladina}, vypíše zadané hodnoty
     * v zadaném formátu.
     *
     * @param dbgLevel Hladina významnosti určující, zda se bude vypisovat
     * @param format   Formát specifikující způsob vypsání zadaných hodnot
     * @param values   Vypisované hodnoty
     */
    public static void prf(int dbgLevel, String format, Object... values)
    {
        if (DEBUG_LEVEL >= dbgLevel) {
            printf(format, values);
        }
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL > 0}, vypíše zadanou hodnotu
     * a odřádkuje.
     *
     * @param value Vypisovaná hodnota
     */
    public static void prln(Object value)
    {
        if (DEBUG_LEVEL > 0) {
            println(value);
        }
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL >= dbgLevel}, vypíše zadanou hodnotu
     * a odřádkuje.
     *
     * @param dbgLevel Hladina významnosti určující, zda se bude vypisovat
     * @param value    Vypisovaná hodnota
     */
    public static void prln(int dbgLevel, Object value)
    {
        if (DEBUG_LEVEL >= dbgLevel) {
            println(value);
        }
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL > 0}, vypíše zadané záhlaví
     * a pod ním podpisy jednotlivých prvků zadaného pole
     * každý na samostatný řádek odsazený proti záhlaví.
     *
     * @param header Záhlaví vytištěného pole
     * @param array  Pole objektů, jejichž podpisy se budou vypisovat
     */
    public static void prNln(String header, Object[] array)
    {
        prNln(-1, header, array);
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL > dbgLevel}, vypíše zadané záhlaví
     * a pod ním podpisy jednotlivých prvků zadaného pole
     * každý na samostatný řádek odsazený proti záhlaví.
     *
     * @param dbgLevel Hladina významnosti určující, zda se bude vypisovat
     * @param header   Záhlaví vypisovaného pole
     * @param array    Pole objektů, jejichž podpisy se budou vypisovat
     */
    public static void prNln(int dbgLevel, String header, Object[] array)
    {
        prNln(dbgLevel, header, Arrays.stream(array));
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL > 0}, vypíše zadané záhlaví
     * a pod ním podpisy jednotlivých prvků zadané kolekce
     * každý na samostatný řádek odsazený proti záhlaví.
     *
     * @param header      Záhlaví vypisované kolekce
     * @param collection  Kolekce objektů, jejichž podpisy se budou vypisovat
     */
    public static void prNln(String header, Collection<?> collection)
    {
        prNln(-1, header, collection);
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL > dbgLevel}, vypíše zadané záhlaví
     * a pod ním podpisy jednotlivých prvků zadané kolekce
     * každý na samostatný řádek odsazený proti záhlaví.
     *
     * @param dbgLevel   Hladina významnosti určující, zda se bude vypisovat
     * @param header     Záhlaví vypisované kolekce
     * @param collection Kolekce objektů, jejichž podpisy se budou vypisovat
     */
    public static void prNln(int dbgLevel, String header,
                             Collection<?> collection)
    {
        prNln(dbgLevel, header, collection.stream());
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL > 0}, vypíše zadané záhlaví
     * a pod ním podpisy jednotlivých prvků zadaného proudu
     * každý na samostatný řádek odsazený proti záhlaví.
     *
     * @param header Záhlaví vypisovaného pole
     * @param stream Pole objektů, jejichž podpisy se budou vypisovat
     */
    public static void prNln(String header, Stream<?> stream)
    {
        prNln(-1, header, stream);
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL > dbgLevel}, vypíše zadané záhlaví
     * a pod ním podpisy jednotlivých prvků zadaného pole
     * každý na samostatný řádek odsazený proti záhlaví.
     *
     * @param dbgLevel  Hladina významnosti určující, zda se bude vypisovat
     * @param header    Záhlaví vypisovaného pole
     * @param stream    Pole objektů, jejichž podpisy se budou vypisovat
     */
    @SuppressWarnings( {"rowtypes", "unchecked"})
    public static void prNln(int dbgLevel, String header, Stream<?> stream)
    {
        if (DEBUG_LEVEL >= dbgLevel) {
            StringBuilder sb = new StringBuilder(header).append(": ");
            stream.forEachOrdered((item) ->
                                  { sb.append('\n').append(item); });
            sb.append('\n');
            println(sb.toString());
        }
    }


    /***************************************************************************
     * Vrátí řetězec s výpisem zásobníku odpovídajícím zadané výjimce.
     *
     * @param e Výjimka, jejíž výpis zásobníku nás zajímá
     * @return Text výpisu zásobníku zadané výjimky
     */
    public static String stackTrace(Throwable e)
    {
        StringBuilder sb = new StringBuilder(512);
        while (e != null) {
            sb.append(e).append('\n');
            StackTraceElement[] stes = e.getStackTrace();
            for (StackTraceElement ste : stes) {
                sb.append("\tat ").append(ste).append('\n');
            }
            e = e.getCause();
            if (e != null) {
                sb.append("\nCaused by\n");
            }
        }
        return sb.toString();
    }


    /***************************************************************************
     * Počká zadaný počet milisekund.
     * Na přerusení reaguje tak, že skončí čekání a před ukončením metody
     * znovu požádá o přerušení vlastního vlákna.
     * Ošetření tohoto přerušení tak deleguje na volající metodu.
     *
     * @param milliseconds   Počet milisekund, po něž se má čekat.
     */
    public static void wait(int milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }




//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================


    /***************************************************************************
     * Vypíše zadanou hodnotu bez závěrečného odřádkování.
     *
     * @param value Vypisovaná hodnota
     */
    private static void print(Object value)
    {
        printf("%s", value);
    }


    /***************************************************************************
     * Vypíše zadané hodnoty v zadaném formátu.
     *
     * @param format Formát specifikující způsob výpisu následujících hodnot
     * @param values Vypisované hodnoty
     */
    private static void printf(String format, Object... values)
    {
        OUTPUT_2_INDENTING_STREAM.values().stream().
            forEachOrdered((IndentingPrintStream ps) -> {
                ps.printf(format, values);
                ps.flush();
            });
    }


    /***************************************************************************
     * Je-li {@code DEBUG_LEVEL > 0}, vypíše zadanou hodnotu a odřádkuje.
     *
     * @param value  Vypisovaná hodnota
     */
    private static void println(Object value)
    {
        printf("%s\n", value);
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /** Soukromý konstruktor zabraňující vytvoření instance.*/
    private DBG() {}



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
