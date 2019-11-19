/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.scenario_test;

import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.scenario.AScenarioManager;
import eu.pedu.adv19s_fw.scenario.Scenario;
import eu.pedu.adv19s_fw.scenario.ScenarioStep;
import eu.pedu.adv19s_fw.test_util.ATester;
import eu.pedu.adv19s_fw.test_util.common.TestException;
import eu.pedu.adv19s_fw.test_util.common.TestUtilitiy;

import java.util.Date;

import static eu.pedu.adv19s_fw.utilities.ConditionalPrinter.*;
import static eu.pedu.adv19s_fw.utilities.FormatStrings.*;



/*******************************************************************************
 * Instance třídy {@code ScenarioManagerTester} umějí otestovat
 * splnění základních povinných vlastností správce scénářů.
 * Testují však pouze korektnost vlastních správců scénářů
 * a korektnost jimi spravovaných scénářů.
 * Nepokouší se pomocí scénářů zadaného správce příslušnou hru testovat.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class ScenarioManagerTester
     extends ATester
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================




//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Testovaný správce scénářů. */
    private final AScenarioManager manager;

    /** Kompletní zpráva o průběhu testu. */
    private final StringBuilder verboseMessageBuilder;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Přepravka se souhrnem informací zjištěných ze scénářů spravovaných
     *  testovaným správcem scénářů. */
    protected SMSummary smSummary;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří objekt schopný testovat správce scénářů.
     *
     * @param manager Testovaný správce scénářů
     */
    public ScenarioManagerTester(AScenarioManager manager)
    {
//        super(IGSMFactory.getInstanceOfFactory(manager.getFactoryClass()),
//              ATester.SM_LEVEL);
        super(manager.getFactory(), ATester.SM_LEVEL);
        this.manager               = manager;
        this.verboseMessageBuilder = new StringBuilder();
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Základní test ověřující,
     * jestli správce scénářů vyhovuje zadaným okrajovým podmínkám, tj. jestli:
     * <ul>
     *   <li>Umí vrátit správně naformátované jméno autora/autorky hry
     *       a jeho/její xname.</li>
     *   <li>Definuje základní úspěšný a základní chybový scénář.</li>
     *   <li>Základní chybový scénář má následující vlastnosti:
     *     <ul>
     *       <li>Startovní příkaz, jehož název je prázdný řetězec</li>
     *       <li>Minimální požadovaný počet kroků</li>
     *       <li>Minimální počet navštívených místností</li>
     *       <li>Minimální počet "zahlédnutých" místností</li>
     *       <li>Použití příkazů pro přechod z prostoru do prostoru
     *         zvednutí nějakého objektu a položení nějakého objektu</li>
     *     </ul>
     *   </li>
     *   <li>Základní chybový scénář má následující vlastnosti:
     *     <ul>
     *       <li>Startovní příkaz, jehož název je prázdný řetězec</li>
     *       <li>Použití všech povinných chybových typů kroku
     *         definovaných ve třídě
     *         {@link eu.pedu.adv19s_fw.scenario.TypeOfStep}</li>
     *       <li>Vyvolání nápovědy</li>
     *       <li>Ukončení příkazem pro nestandardní ukončení hry</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return Vyhovuje-li, vrátí {@code true}, jinak vrátí {@code false}
     */
    public SMSummary verify()
    {
        initResultInformation();
        Date startTime =  new Date();
        String message = String.format(
                         "Author of the tested scenario manager: %s – %s\n" +
                         "Instace of the class: %s%n" +
                         "########## START: %ta %<TF — %<tT\n",
                         manager.getAuthorID(), manager.getAuthorName(),
                         manager.getClass().getName(), startTime);
        verboseMessageBuilder.append(message);
        prln(message);

        Class<?> cls = manager.getClass();
        TestUtilitiy.verifySingleAndPrivateConstructor(cls);
        TestUtilitiy.verifyGetInstanceMethod(cls);

        writeInvitation();

        verifyHappyScenario  (manager);    //Ověří, že nultým scénářem je ZÚspě
        verifyMistakeScenario(manager);    //Ověří, že prvním scénářem je ZChyb

        smSummary = ScenarioTester.testAllScenarios(manager);
        boolean ok = smSummary.ok;

        Date stopTime =  new Date();
        long duration = (stopTime.getTime() - startTime.getTime());
        message = String.format("The scenario test was %sSUCCESSFUL",
                                (ok ? "" : "UN"));
        message = String.format(
            "########## STOP: %ta %<TF — %<tT\n  —  " +
            "Test duration: %d ms – %s\n" +
            "End of scenario tests from: %s – %s" + N_HASHES_N,
            stopTime, duration, message,
            manager.getAuthorID(), manager.getAuthorName());
        verboseMessageBuilder.append(message);
        prln(message);
        return smSummary;
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Inicializuje {@link StringBuilder}, do nějž se budou zapisovat výsledky.
     */
    private void initResultInformation()
    {
        verboseMessageBuilder.delete(0, verboseMessageBuilder.length());
    }


    /***************************************************************************
     * Prověří, že základní úspěšný scénář dodaný testovaným správcem
     * je prvním scénářem nabízeným jeho iterátorem.
     *
     * @param manager Testovaný správce scénářů
     * @throws TestException Chyba v testované aplikaci
     */
    private void verifyHappyScenario(AScenarioManager manager)
    {
        Scenario happyScenario  = manager.getHappyScenario();
        if (happyScenario == null)  {
            throw new TestException("\nIterátor správce scénářů musí vždy "  +
                             "nabídnout jako první základní úspěšný scénář," +
              "\nkterý musí být současně získatelný jako scénář s indexem 0");
        }
    }


    /***************************************************************************
     * Prověří, že základní chybový scénář dodaný testovaným správcem
     * je druhým scénářem nabízeným jeho iterátorem.
     *
     * @param manager Testovaný správce scénářů
     * @throws TestException Chyba v testované aplikaci
     */
    private void verifyMistakeScenario(AScenarioManager manager)
    {
        Scenario mistakeScenario = manager.getMistakeScenario();
        if (mistakeScenario == null) {
            throw new TestException("\nIterátor správce scénářů musí vždy " +
                             "nabídnout jako druhý základní chybový scénář," +
              "\nkterý musí být současně získatelný jako scénář s indexem 1");
        }
    }


    /***************************************************************************
     * Vypíše úvodní zprávu hry, ze které by se mělo dát odhadnout,
     * o čem hra bude.
     *
     * @param manager Správce scénářů hry, jejíž uvítání se vypisuje
     */
    private void writeInvitation()
    {
        ScenarioStep initialStep = manager.getStartStep();
        prf("Welcome message:\n" +
            "================\n%s\n", initialStep.message);
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
