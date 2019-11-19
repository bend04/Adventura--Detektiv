/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.game_txt_test;

import eu.pedu.adv19s_fw.Framework;
import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.scenario.AScenarioManager;
import eu.pedu.adv19s_fw.test_util.ATester;
import eu.pedu.adv19s_fw.test_util.common.TestException;
import eu.pedu.adv19s_fw.test_util.scenario_test.SMSummary;
import eu.pedu.adv19s_fw.test_util.scenario_test.ScenarioManagerTester;
import java.util.ArrayList;
import java.util.List;

import java.util.function.Supplier;



/*******************************************************************************
 * Instance třídy {@code TGameInitTester} slouží k testování
 * her implementujících interfejs {@code IGame}
 * a ovladatelných podle scénářů spravovaných
 * instancí potomka třídy {@link AScenarioManager}.
 * <p>
 * Instance však prověřují pouze základní povinné vlastnosti hry, tj.
 * správnou implementaci interfejsu {@code IGame} včetně dodatečných kontraktů,
 * aniž by se snažily spustit některý z definovaných scénářů.
 * <p>
 * Pokud hra při spuštění testu běží, tak ji nejprve ukončí,
 * a pak ji znovu odstartují, aby se inicializovala.
 * Na konci testu ji opět ukončí.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class TGameInitTester extends ATester
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Seznam lokalizovaných zpráv. */
    private static final List<String> I18N;

    /** Index zprávy o tom, že se hra po zastavení hlásí jako běžící. */
    private static final int AFTER_STOP;

    /** Index zprávy o tom, že se hra po spuštění hlásí jako neběžící. */
    private static final int AFTER_START;

    /** Index zprávy o nesouhlasu názvů povinných akcí. */
    private static final int CMD_NAMES;

    /** Index zprávy o nedokonalé implementaci metod. */
    private static final int NOT_SUPPORTED;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Statický konstruktor nastavuje:<br>
     *  - jazykovou verzi textů chybových hlášení
     *  - implicitní ošetření nezachycených přerušení
     */
    static {
        List<String> CZ = new ArrayList<>();
        List<String> EN = new ArrayList<>();

        AFTER_STOP = CZ.size();
        CZ.add(": Zavolání metody stop() hru neukončilo");
        EN.add(": Calling the stop() method did not stop the game");

        AFTER_START = CZ.size();
        CZ.add(": Po odstartování se hra nehlásí jako živá");
        EN.add(": After start the game is not alive");

        CMD_NAMES = CZ.size();
        CZ.add("Názvy základních akcí si neodpovídají"
           + "\nZe scénářů vyplývá: %s"
           + "\nHra deklaruje: %s");
        EN.add("The name of mandatory actions does not correspond"
           + "\nThe scenarios announce: %s"
           + "\nThe game declares: %s");

        NOT_SUPPORTED = CZ.size();
        CZ.add("Třída hry ještě nemá plnohodnotně implementovanou "
           +   "metodu get%s()");
        EN.add("The game has not yet fully implemented the "
           +   "method: get%s()");

        switch (Framework.LANGUAGE)
        {
            case Framework.CZECH:   I18N = CZ;  break;
            case Framework.ENGLISH: I18N = EN;  break;
            default:
                throw new RuntimeException(
                    "\nThe text language is wrong.");
        }
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============


    /** Testovaná hra. */
    private final IGame game;

    /** Správce scénářů testované hry. */
    private final AScenarioManager manager;

    /** Přeprava s výsledky testů správce scénářů (SM = ScenarioManager). */
    private final SMSummary smSummary;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří tester hry na základě informací obdržených v zadaném parametru.
     *
     * @param gameSummary Přepravka se souhrnnými informacemi
     *                    o testované aplikaci a o provedených testech
     */
    TGameInitTester(GameSummary gameSummary)
    {
        super(gameSummary.factory);
        this.game      = gameSummary.game;
        this.manager   = gameSummary.manager;
        this.smSummary = gameSummary.smSummary;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Prověří, nakolik se informace obdržené přímo od spuštěné hry
     * shodují s informacemi poskytnutými správcem scénářů.
     */
    public void verify()
    {
        verifyAliveness(0);
        verifyAliveness(1);
        verifyGetters();
        game.stop();
        verifyBasicActions();
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Prověří, zda se hra po ukončení hlásí jako ukončená
     * a naopak po spuštění jako živá.
     * Po ukončení metody hra běží a lze ji dále testovat.
     */
    private void verifyAliveness(int index)
    {
        System.out.println(index + " ***** Chystám se hru zastavit *****");
        game.stop();
        System.out.println(index + " ***** Hra zastavena ***** isAlive() == "
                           + game.isAlive());
        if (game.isAlive()) {
            ERROR(index + I18N.get(AFTER_STOP));
        }
        System.out.println(index + " ***** Chystám se hru spustit *****");
        game.executeCommand("");
        System.out.println(index + " ***** Hra spuštěna ***** isAlive() == "
                           + game.isAlive());
        if (! game.isAlive()) {
            ERROR(index + I18N.get(AFTER_START));
        }
    }


    /***************************************************************************
     * Prověří, že vracené názvy povinných příkazů odpovídají názvům
     * odvozeným ze spravovaných scénářů.
     */
    private void verifyBasicActions()
    {
        if (game.getBasicActions().equals(smSummary.basicActions)) {
            return;
        }
        ERROR(I18N.get(CMD_NAMES),
              smSummary.basicActions, game.getBasicActions());
    }


    /***************************************************************************
     * Prověří, že všechny getry hry vracejí alespoň formálně platné objekty.
     */
    private void verifyGetters()
    {
        verifyGetter("AllActions",   game::getAllActions);
        verifyGetter("AuthorID",     game::getAuthorID);
        verifyGetter("AuthorName",   game::getAuthorName);
        verifyGetter("Bag",          game::getBag);
        verifyGetter("BasicActions", game::getBasicActions);
        verifyGetter("FactoryClass", game::getFactoryClass);
        verifyGetter("World",        game::getWorld);
    }


    /***************************************************************************
     * Metoda prověří, zda má třída hry plnohodnotně implementovaný
     * zadaný getter, tj. jestli příslušná metoda vrací objekt.
     *
     * @param <T>    Typ vracené hodnoty
     * @param name   Název testované metody
     * @param getter Funkční objekt obalující testovanou metodu
     */
    private <T> void verifyGetter(String name, Supplier<T> getter)
    {
        try {
            T obtained = getter.get();
            if (obtained == null) {
                throw new Exception();
            }
        }
        catch (Exception ex) {
            ERROR(I18N.get(NOT_SUPPORTED), name);
        }
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
