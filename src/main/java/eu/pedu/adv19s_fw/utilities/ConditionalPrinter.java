/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;



/*******************************************************************************
 * Knihovní třída {@code ConditionalPrinter} poskytuje metody
 * pro podmíněny výstup zadaných (většinou ladících) textů
 * do předem zadaného tiskového proudu, tj. proudu typu {@link PrintStream}.
 * Tímto proudem může být standardní výstup, standardní chybový výstup,
 * libovolný tiskový proud nebo soubor,
 * který se v případe potřeby nejprve vytvoří.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public final class ConditionalPrinter
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Konstanta vyhodnotitelná v době překladu, jejiž hodnota ovlivňuje,
     *  zda se budou požadované tisky provádět nebo ne.<br>
     *  0 a méně - Tiskne se jen na výslovnou žádost
     *  1 - Standardní hodnota, při níž tisknou i metody bez nastavené hladiny
     *  2 a více - tisknou se podrobnější výpisy
     */
    public static final int LEVEL = 1;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    private static PrintStream out = System.out;


//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Nastaví výstup do zadaného tiskového proudu.
     *
     * @param ps Cílový výstupní tiskový proud
     */
    public static void setOut(PrintStream ps) {
        out = ps;
    }


    /***************************************************************************
     * Je-li jako parametr zadaná hodnota <b>{@code out}</b> nebo
     * <b>{@code err}</b> nastaví výstup do zadaného systémového proudu;
     * Jiná podoba řetězce definuje cestu k souboru, do nějž se ma výstup
     * poslat. Pokud tento soubor neexistuje, metoda se jej pokusí vytvořit.
     *
     * @param path Cílový výstupní tiskový proud
     */
    public static void setOut(String path) {
        if (path.equals("out")) {
            out = System.out;
        }
        else if (path.equals("err")) {
            out = System.err;
        }
        else {
            File file = new File(path);
            try {
                out = new PrintStream(file);
            } catch (FileNotFoundException ex1) {
                try {
                    file.createNewFile();
                } catch (IOException ex2) {
                    throw new RuntimeException("\nSoubor " + file +
                        " se nepodařilo najít ani vytvořit", ex2);
                }
            }
        }
    }


    /***************************************************************************
     * Vrátí proud, do nějž odcházejí ladící tisky
     *
     * @return Proud, do nějž odcházejí ladící tisky
     */
    public static PrintStream getPrintStream()
    {
        return out;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Je-li <b>{@code LEVEL > 0}</b>, vytiskne zadanou hodnotu
     * bez závěrečného odřádkování.
     *
     * @param value Vypisovaná hodnota
     */
    public static void pr(Object value)
    {
        if (LEVEL > 0) {
            out.print(value);
        }
    }


    /***************************************************************************
     * Je-li <b>{@code LEVEL >= level}</b>, vytiskne zadanou hodnotu
     * bez závěrečného odřádkování.
     *
     * @param level Hladina kontrolních tisků
     * @param value Vypisovaná hodnota
     */
    public static void pr(int level, Object value)
    {
        if (LEVEL >= level) {
            out.print(value);
        }
    }


    /***************************************************************************
     * Je-li <b>{@code LEVEL > 0}</b>, vytiskne požadovaný text
     * v zadaném formátu.
     *
     * @param format Formát výstupních tisků
     * @param values Vypisované hodnoty
     */
    public static void prf(String format, Object... values)
    {
        if (LEVEL > 0) {
            out.printf(format, values);
        }
    }


    /***************************************************************************
     * Je-li <b>{@code LEVEL >= level}</b>, vytiskne zadané hodnoty
      * v zadaném formátu.
      *
     * @param level  Hladina kontrolních tisků
     * @param format Formát výstupních tisků
     * @param values Vypisované hodnoty
     */
    public static void prf(int level, String format, Object... values)
    {
        if (LEVEL >= level) {
            out.printf(format, values);
        }
    }


    /***************************************************************************
     * Je-li <b>{@code LEVEL > 0}</b>, vytiskne zadanou hodnotu
     * a odřádkuje.
     *
     * @param value Vypisovaná hodnota
     */
    public static void prln(Object value)
    {
        if (LEVEL > 0) {
            out.println(value);
        }
    }


    /***************************************************************************
     * Je-li <b>{@code LEVEL >= level}</b>, vytiskne zadanou hodnotu
     * a odřádkuje.
     *
     * @param level Hladina kontrolních tisků
     * @param value Vypisovaná hodnota
     */
    public static void prln(int level, Object value)
    {
        if (LEVEL >= level) {
            out.println(value);
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




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /** Soukromý konstruktor zabraňující vytvoření instance. */
    private ConditionalPrinter() {/**/}



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
