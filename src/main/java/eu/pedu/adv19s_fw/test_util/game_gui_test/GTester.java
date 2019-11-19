/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.game_gui_test;

import eu.pedu.adv19s_fw.Framework;
import eu.pedu.adv19s_fw.game_txt.IAuthor;
import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.test_util.common.TestException;
import eu.pedu.adv19s_fw.test_util.scenario_test.SMSummary;
import eu.pedu.adv19s_fw.test_util.game_txt_test.GameSummary;
import eu.pedu.adv19s_fw.utilities.IO;

import javax.swing.JOptionPane;

import static eu.pedu.adv19s_fw.utilities.ConditionalPrinter.*;
import static eu.pedu.adv19s_fw.utilities.FormatStrings.*;



/*******************************************************************************
 * Potomek třídy {@code ATester},
 * který slouží jako potenciální vzor (případně rodič) tříd
 * určených k testování grafického uživatelského rozhraní.
 * Vznikl jako kopie třídy před tím, ne byly její části smazány.
 *
 * Třída {@code GTester} slouží jako rodičovská třída
 * různých testovacích tříd kterým poskytuje společné metody
 * pro zadání reakce na chybu
 * a pro nastavení příznaku práce v grafickém režimu.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
//TODO Opravid dokumentační komentář třídy
public class GTester
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Příznak provozu v grafickém režimu. */
    private static boolean GUI = false;



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Statický konstruktor nastavuje:
     *  - implicitní ošetření nezachycených přerušení
     */
    static {
        Thread.setDefaultUncaughtExceptionHandler(
            (Thread t, Throwable e) ->
            {
                prf(N_DOUBLELINE_N +
                    "An exception was thrown in the thread %s:\n%s\n",
                    t, stackTrace(e));
            });
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Nastaví příznak provozu v grafickém režimu.
     *
     * @param GUI Nastavovaná hodnota příznaku
     */
    public static void setGUI(boolean GUI)
    {
        GTester.GUI = GUI;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================



//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================


    /***************************************************************************
     * Vytiskne a vrátí chybové hlášení a v případě,
     * že je program provozován pod grafickým uživatelským rozhraním,
     * vypíše chybovou zprávu v okně.
     *
     * @param format    Formát tisku chybového hlášení. Bude ještě doplněn
     *                  společnou úvodní a závěrečnou sekvencí.
     * @param arguments Případné další parametry k tisku
     * @return Chybové hlášení
     */
    private static String error(String format, Object[] arguments)
    {
        String message = String.format('\n' + format, arguments);
        String complet = N_BERFORE + message + N_AFTER_N;
//        prln(complet);
        if (GUI) {
            JOptionPane.showMessageDialog(null, message);
        }
        return message;
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//
//    /** Kompletní zpráva o průběhu testu. */
//    protected final StringBuilder verboseMessageBuilder;
//
//
//
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============
//
//    /** Celkový bodový zisk. */
//    protected double score;
//
//    /** Přepravka se souhrnem informací zjištěných ze scénářů spravovaných
//     *  správcem scénářů. */
//    protected SMSummary smSummary;

    /** Přepravka na informace o testu a jeho výsledcích. */
    protected GameSummary gameSummary;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Rodičovský konstruktor nemusí provádět žádné speciální akce,
     * jen si zapamatuje přepravku s informacemi o testu a jeho výsledcích.
     *
     * @param gameSummary Přepravka na informace o testu a jeho výsledcích
     */
    protected GTester(GameSummary gameSummary)
    {
        this.gameSummary = gameSummary;
    }


    /***************************************************************************
     * Rodičovský konstruktor pro testery vytvářené před sestavením
     * instance {@link GameSummary}.
     */
    protected GTester()
    {
        this.gameSummary = null;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Statická verze metody, která vytiskne chybové hlášení
     * a v případě, že program používá grafickém uživatelské rozhraní,
     * vypíše chybovou zprávu v okně;
     * poté vyhodí výjimku {@code TestException}.
     *
     * @param format Formát tisku chybového hlášení ve stylu {@code printf}.
     *               Bude ještě doplněn společnou úvodní a závěrečnou sekvencí.
     * @param parameters Případné další parametry k tisku
     */
    protected void ERROR(String format, Object... parameters)
    {
        String        message   = error(format, parameters);
        TestException exception = new TestException(message);
//TODO Tady se při hlášení chyby kouše IO
//        IO.inform("Běh prgramu skončil chybou:\n\n" + message);
        if (gameSummary != null) {
            gameSummary.setThrowable(exception);
        }
        throw exception;
    }


    /***************************************************************************
     * Prověří délku řádků zadaného textu.
     *
     * @param description Popis prověřovaného textu
     * @param text        Prověřovaný text
     */
    public void verifyMessageLength(String description, String text)
    {
        String[] lines = text.split("\\n");
        for (String line : lines) {
            if (line.length() > Framework.MAX_LINE_LENGTH) {
                ERROR("Line length is %s characters, which is more than "
                    + "%d allowed characters.",
                      description, Framework.MAX_LINE_LENGTH);
                return;
            }
        }
    }


    /***************************************************************************
     * Prověří, že se zadaná třída se nachází ve správném balíčku,
     * jehož název je odvozen z ID a jména jejího autora.
     *
     * @param authored Prověřovaná  třída
     */
    public void verifyPackage(IAuthor authored)
    {
        String  ID      = authored.getAuthorID().toLowerCase();
        String  name    = authored.getAuthorName();
        String[]words   = name.split(" ");
        String  surname = words[0].toLowerCase();
        String  ascii   = IO.removeAccents(surname);
        String  reqName = '.' + ID + "_" + ascii; //Požadovaný název

        if ("pecinovsky".equals(ascii)) {
            return;                             //==========>
        }

        Class<? extends IAuthor> cls = authored.getClass();
        Package pkg         = cls.getPackage();
        String  pkgFullName = pkg.getName();
        if (! pkgFullName.contains(reqName)) {
            ERROR("The expected and obtained package names differs.\n   %s"
                + "\n   The announced author should have its classes "
                +       "in the package %s"
                + "\n   or in certain of its subpackages."
                , cls.getName(), reqName);
        }
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
