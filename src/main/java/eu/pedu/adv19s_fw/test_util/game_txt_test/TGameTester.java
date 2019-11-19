/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.game_txt_test;

import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.scenario.Scenario;
import eu.pedu.adv19s_fw.test_util.ATester;

import java.util.List;

import static eu.pedu.adv19s_fw.utilities.FormatStrings.*;
import static eu.pedu.adv19s_fw.utilities.ConditionalPrinter.*;



/*******************************************************************************
 * Instance třídy {@code TGameTester} testují aplikaci
 * zadanou továrním objektem podle požadavků zadaného
 * zadavatele – instance třídy {@link ASolutionTester}.
 * Tento zadavatel je poskytuje sadu scénářů, podle nichž se testuje, spolu
 * s návštěvníkem, který ve vhodnou chvíli ověřuje požadované skutečnosti.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class TGameTester extends ATester
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

    /** Seznam scénářů, podle nichž se řešení testuje. */
    protected final List<Scenario> testingScenarios;

    /** Objekt vystupující jako zadavatel poskytující pomocné objekty –
     *  testovací scénáře a návštěvníka. */
    protected final ASolutionTester solutionTester;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří novou instanci testovací třídy testující objekty poskytnuté
     * instancí zadané tovární třídy a modifikované návštěvníky
     * vytvářenými zadaným továrním objektem.
     *
     * @param solutionTester  Tester zadání, jehož realizace se prověřuje
     * @param gsmFactory      Tovární objekt zprostředkovávající přístup
     *                        ke klíčovým objektům aplikace.
     */
    public TGameTester(ASolutionTester solutionTester, IGSMFactory gsmFactory)
    {
        super(gsmFactory);
        this.solutionTester   = solutionTester;
        this.testingScenarios = solutionTester.getScenarios(gsmFactory);
        if (testingScenarios==null  ||  testingScenarios.isEmpty()) {
            throw new RuntimeException(
                "\nPro tester " + solutionTester
              + "\nNebyly zadány scénáře, podle nichž se má testovat hra "
              + gsmFactory.getGame());
        }
    }


//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//
//    /***************************************************************************
//     * Zadá seznam scénářů, podle nichž se bude testovat.
//     *
//     * @param scenarioNames  Názvy scénářů, podle nichž bude testovat
//     */
//    public void setScenarios(String... scenarioNames)
//    {
//        AScenarioManager manager = gsmFactory.getScenarioManager();
//        testingScenarios = Arrays.stream(scenarioNames)
//                                 .map(name -> manager.getScenario(name))
//                                 .collect(Collectors.toList());
//    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Prověří hru tak, že ji postupně "zahraje" podle scénářů
     * se zadanými názvy. Zadané scénáře musí být testovací.
     *
     */
    public void testGame()
    {
        GameSummary gameSummary = new GameSummary(solutionTester, gsmFactory);

        //Prověř dodržení základních konstraktů
        new TGameInitTester(gameSummary).verify();

        TGameRunTester tester = new TGameRunTester(gameSummary);
        for (Scenario scenario : testingScenarios) {
            tester.executeScenario(scenario);
        }
        StringBuilder msgBuilder = new StringBuilder(
                                       "\nAccording the test results ");
        if (gameSummary.getThrowable() != null) {
            msgBuilder.append("PROGRAM CONTAIN CERTAIN ERRORS");
        }
        else {
            msgBuilder.append(
                    "program probably does not contain any serious error.");
            gameSummary.addToScore(1);
        }
        msgBuilder.append(N_HASHES_N).append(HASHES_N);
        String txt = msgBuilder.toString();
        gameSummary.verboseMessageBuilder.append(txt);
        prln(txt);
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
