/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.game_txt_test;

import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.scenario.AScenarioManager;
import eu.pedu.adv19s_fw.scenario.Scenario;
import eu.pedu.adv19s_fw.scenario.ScenarioStep;
import eu.pedu.adv19s_fw.scenario.TypeOfStep;
import eu.pedu.adv19s_fw.test_util.ATester;
import eu.pedu.adv19s_fw.test_util.common.TestException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import static eu.pedu.adv19s_fw.utilities.ConditionalPrinter.*;
import static eu.pedu.adv19s_fw.utilities.FormatStrings.*;


/*******************************************************************************
 * Instance třídy {@code TGameRunTester} slouží k otestování běhu hry
 * her implementujících interfejs {@code IGame}
 * a ovladatelných podle scénářů spravovaných
 * instancí potomka třídy {@link AScenarioManager}.
 * <p>
 * Instance jsou schopny prověřit,
 * zda při spuštění libovolného testovacího scénáře hra reaguje tak,
 * jak je ve scénáři naplánováno.
 * Za testovací scénář se přitom považuje libovolný scénář,
 * který není demonstrační.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class TGameRunTester extends ATester
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

    /** Kompletní zpráva o průběhu testu. */
    public final StringBuilder verboseMessageBuilder;

    /** Návštěvník zabezpečující v průběhu testu
     *  provádění některých doplňkových akci. */
    private final ATestVisitor testVisitor;

    /** Přeprava se souhrnnými informacemi o testované aplikaci
     *  a o provedených testech. */
    private final GameSummary gameSummary;

    /** Tester jednotlivých kroků testované hry. */
    private final TGameStepTester stepTester;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============
//
//    /** Proměnná udržující informaci o tom, tvrdí-li hra, že běží. */
//    private boolean gameIsAlive;
//
//    /** Scénář, který je aktuálně aplikován na testovanou hru. */
//    private Scenario scenario;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří instanci, která bude testovat zadanou hru.
     * Konstruktor předpokládá, že parametry již prošly základní prověrkou.
     *
     * @param gameSummary Přepravka s informacemi o testu a jeho výsledcích
     */
    public TGameRunTester(GameSummary gameSummary)
    {
        super(gameSummary.factory);
        this.gameSummary           = gameSummary;
        this.verboseMessageBuilder = gameSummary.verboseMessageBuilder;
//        this.factory     = factory;
//        this.game        = factory.getGame();
//        this.gamename    = game   .getClass().getName();
//        this.manager     = factory.getScenarioManager();
        this.testVisitor = gameSummary.solutionTester
                                      .getVisitor(gsmFactory);
        this.stepTester  = new TGameStepTester(gameSummary);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Spustí hru řízenou zadaným scénářem;
     * této hře bude postupně zadávat příkazy scénáře
     * a porovnávat její odpovědi s požadavky scénáře.
     *
     * @param scenario    Scénář, kterým testujeme hru
     */
    public void executeScenario(Scenario scenario)
    {
        prf("\n########## Tested scenarion: ##########\n%s\n"
            + "########################################\n",
            scenario);
        verifyScenarioManager(scenario);

        Throwable throwable = null;
//        foundErrors.clear();

        String text = header(scenario) +
                      "===== Test of the executed actions =====";
        prln(text);
        try {
            //Ověří, že před spuštěním scénáře hra neběží
            verifyIsAlive(ScenarioStep.NOT_START_STEP);
            for (ScenarioStep step : scenario) {
                //Ověří krok a vytiskne zprávu o daném kroku
                verifyScenarioStep(scenario, step);
            }
        } catch(Throwable e) {
            throwable = e;
        } finally {
            updateGameSummary(scenario, throwable);
            testVisitor.afterGameEnd(gameSummary);
        }
        if (! gameSummary.isOk()) {
//            gameSummary.game.stop();
            System.exit(1);
        }
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Vrátí hlavičku testu zadaného scénáře.
     *
     * @param scenario
     * @return String s hlavičkou testu zadaného scénáře
     */
    private String header(Scenario scenario)
    {
        IGame  game = gameSummary.game;
        String text = N_HASHES
             + "\n##### Game:       " + game.getName()
             + "\n##### Author:     " + game.getAuthorString()
             + "\n##### Scenario:   " + scenario.getName()
             + "\n##### Game class: " + game.getClass().getName()
             + "\n##### Time:       " + new Date()
             + N_HASHES_N;
        return text;
    }


    /***************************************************************************
     * Zpracuje ukončení testu, které se musí provést nezávisle na tom,
     * zda test proběhl korektně nebo byla v jeho průběhu vyhozena výjimka.
     *
     * @param scenario  Scénář, podle nějž se testovalo
     * @param throwable Případná zachycená vyhozená výjimka či {@code null}
     */
    private void updateGameSummary(Scenario scenario, Throwable throwable)
    {
        prf("\n===== End of test of the executed actions =====%s\n",
            header(scenario));
        if (throwable != null) {
            gameSummary.setThrowable(throwable);
            StringWriter sw = new StringWriter();
            try (PrintWriter pw = new PrintWriter(sw)) {
                pw.print("End was caused by throwing an exception:\n");
                throwable.printStackTrace(pw);
                String txt = sw.toString();
                verboseMessageBuilder.append(txt);
                prln(txt);
            }
        }
    }


    /***************************************************************************
     * Prověří, jestli testovaná hra správně hlásí svoji spuštěnost
     * (před startem a po skončení ne, jinak ano).
     *
     * @param shouldBeAlive Informace o tom, zda má hra běžet
     * @param step          Aktuální krok scénáře
     */
    private void verifyIsAlive(ScenarioStep step)
    {
        boolean gameIsAlive = gameSummary.game.isAlive();
        if ((step.typeOfStep == TypeOfStep.tsNOT_START)  || (step.theLast)) {
            if (gameIsAlive) {
                String prefix = step.theLast
                              ? "After the end"
                              : "Before starting ";
                ERROR(prefix + " the game poses switched on,"
                               + " despite it should be switched off");
            }
        }
        else {
            if (! gameIsAlive) {
                ERROR("The game claims that it does not run "
                    + "despite it should be just running");
            }
        }
    }


    /***************************************************************************
     * Prověří, zda je zadaný scénář řízen dříve dodaným správcem.
     *
     * @param scenario Prověřovaný scénář
     * @throws TestException Zapamatovaný a scénářem dodaný správce se liší
     */
    private void verifyScenarioManager(Scenario scenario)
        throws TestException
    {
        if (scenario.getManager() != gameSummary.manager) {
            throw new TestException(
                "\nDifferent managers announced by the scenario " + scenario
              + "\n   Scenario returns: " + scenario.getManager().getClass()
              + "\n   Factory returns:  " + gameSummary.manager.getClass()
              +"\n");
        }
    }


    /***************************************************************************
     * Prověří správnou funkci hry v zadaném kroku testu.
     *
     * @param  scenario Scénář obsahující daný krok
     * @param  step     Prováděný krok zadaného scénáře
     * @throws IllegalStateException Stav hry neodpovídá tomu,
     *         který podle hodnot dané instance kroku testu očekáváme
     */
    private void verifyScenarioStep(Scenario scenario, ScenarioStep step)
    {
        String command = step.getCommand();
        String message;
        try {
            message = gameSummary.game.executeCommand(command);
        }
        catch (Exception ex) {
            throw new TestException(
                    "\nBy executing the action: «" + command + "»"
                  + "\nthe game has thrown the " + ex.getClass().getSimpleName()
                  + "\nannouncing: " + ex.getMessage()
                  , ex);
        }
        if (step.typeOfStep == TypeOfStep.tsSTART) {
            testVisitor.afterGameStart(scenario);
        }
        testVisitor.beforeStepTest(step, message);
        String inspection = stepTester.verify(step, message);
        verboseMessageBuilder.append(inspection);
        prln(inspection);
        testVisitor.afterStepTest(step, message);
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
