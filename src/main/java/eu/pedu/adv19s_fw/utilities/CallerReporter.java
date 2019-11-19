/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;



////////////////////////////////////////////////////////////////////////////////
//%P-  +++++ End of ignored starting text - place for imports ++++++++++++++++++



/*******************************************************************************
 * Tovární třída {@code CallerReporter} poskytuje sadu metod umožňujících
 * zjistit název a majitele (třídu, balíček) metody,
 * která přímo či zprostředkovaně volala aktuální metodu,
 * přesněji metodu volající některou z poskytovaných metod.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class CallerReporter
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Vrátí řetězec s názvem metody, která tuto metodu zavolala.
     *
     * @param level {@code Level.METHOD}  - vrátí pouze název metody
     *              {@code Level.CLASS}   - vrátí název metody s názvem třídy
     *              {@code Level.PACKAGE} - vrátí název metody s názvem třídy
     *                                      a balíčku
     * @param depth Ptá-li se metoda na svůj název nebo na název některé
     *                jí volajících metod.
     *                0 - vrátí název metody, která zavolala tuto metodu
     *                1 - vrátí název metody, jež zavolala metodu,
     *                    která zavolala tuto metodu
     *                atd.
     * @return Název metody, která tuto metodu zavolala
     */
    public static String getCallerName(Level level, int depth)
    {
        return getCallerName(level.ordinal(), depth+1);
    }


    /***************************************************************************
     * Vrátí řetězec s názvem metody, která tuto metodu zavolala.
     *
     * @param level 0 - vrátí pouze název metody
     *              1 - vrátí název metody s názvem třídy
     *              2 - vrátí název metody s názvem třídy a balíčku
     * @param depth Ptá-li se metoda na svůj název nebo na název některé
     *              z jí volajících metod.
     *              0 - vrátí název metody, která zavolala tuto metodu
     *              1 - vrátí název metody, jež zavolala metodu,
     *                  která zavolala tuto metodu
     *              atd.
     * @return Název metody, která tuto metodu zavolala
     */
    public static String getCallerName(int level, int depth)
    {
        Throwable t = new Throwable();
        StackTraceElement[] ste = t.getStackTrace();
        if (ste.length < (depth+2)) {
            return "== NIKDO ==";
        }
        StackTraceElement element = ste[depth+1];
        String            name    = element.getMethodName();
        if (level > Level.METHOD.ordinal()) {
            String className = element.getClassName();
            if (level < Level.PACKAGE.ordinal()) {
                className = getSimpleClassName(className);
            }
            name = className + "." + name;
        }
        return name;
    }


    /***************************************************************************
     * Vrátí řetězec s názvem metody, která tuto metodu zavolala.
     *
     * @param level  0 - vrátí pouze název metody<br>
     *               1 - vrátí název metody s názvem třídy<br>
     *               2 - vrátí název metody s názvem třídy a balíčku.
     * @return Název metody, která tuto metodu zavolala
     */
    public static String getCallerName(int level)
    {
        return getCallerName(level, 1);
    }


    /***************************************************************************
     * Vrátí řetězec s pouhým názvem metody, která tuto metodu zavolala.
     *
     * @return Název metody, která tuto metodu zavolala
     */
    public static String getCallerName()
    {
        return getCallerName(1, 0);
    }


    /***************************************************************************
     *  Převede úplný název třídy na její jednoduchý název bez balíčků.
     *
     * @param  fullName Úplný název třídy včetně názvů balíčků
     * @return          Jednoduchý název třídy
     */
    public static String getSimpleClassName(String fullName)
    {
        return fullName.substring(1+fullName.lastIndexOf('.'));
    }

//
//    /***************************************************************************
//     * Throws the {@link UncompletedMethodException} announcing,
//     * that the called method in not yet completed.
//     *
//     * @throws UncompletedMethodException
//     *         The called method in not yet completed
//     */
//    public static String notYetCompleted()
//    {
//        String message = "\nMethod is not yet completed: "
//                       + getCallerName(Level.CLASS, 1);
//        throw new UncompletedMethodException(message);
//    }
//



//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     *
     */
    public CallerReporter()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

    /***************************************************************************
     * Výčtový typ specifikující podrobnost výpisu informace o volající metodě.
     */
    public static enum Level
    {
        /** Vypisuje se pouze název volající metody.    */      METHOD,
        /** Vypisuje se název třídy a volající metody.  */      CLASS,
        /** Vypisuje se úplný název třídy (tj. včetně balíčku)
         *  následovaný názvem volající metody.         */      PACKAGE;
    }
}
