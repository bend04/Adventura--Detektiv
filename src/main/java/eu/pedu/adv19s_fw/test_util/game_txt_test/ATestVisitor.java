/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.game_txt_test;

import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.scenario.Scenario;
import eu.pedu.adv19s_fw.scenario.ScenarioStep;



/*******************************************************************************
 * Instance třídy {@code ATestVisitor} představují rodičovské podobjekty
 * návštěvníků schopných ovlivnit způsob testování hry
 * podle jim známých dispozic.
 * <p>
 * Slouží především k tomu, aby bylo možno doplnit základní testy
 * o doplňkové kontroly prověřující, zda byly správně zapracovány
 * všechny úpravy vyžadované při obhajobách aplikace.
 * <p>
 * Tato třída definuje všechny virtuální metody návštěvníka jako prázdné s tím,
 * že pro každé zadání požadované modifikace pro obhajobu
 * bude definován odpovídající potomek kontrolující správnost řešení.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public abstract class ATestVisitor
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

    /** Test konkrétního řešení, pro které daný návštěvník pracuje. */
    protected final ASolutionTester myTest;

    /** Tovární objekt schopný dodat hlavní objekty aplikace. */
    protected final IGSMFactory gsmFactory;

    /** Testovaná hra. */
    protected final IGame game;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Testovací scénář. */
    protected Scenario scenario;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří návštěvníka, který bude připraven prověřit, nakolik testované
     * řešení splňuje modifikované zadání prověřované zadaným testerem.
     *
     * @param myTest     Tester zadání, jehož správnou realizaci
     *                   návštěvník kontroluje
     * @param gsmFactory Tovární objekt schopný dodat klíčové objekty
     *                   testované aplikace
     */
    protected ATestVisitor(ASolutionTester myTest, IGSMFactory gsmFactory)
    {
        this.myTest     = myTest;
        this.gsmFactory = gsmFactory;
        
        IGame iGame;
        try                 { iGame = gsmFactory.getGame(); }
        catch(Exception ex) { iGame = null; }
        this.game = iGame;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================

    /***************************************************************************
     * Vrátí sdružený tester, jehož zadání prověřuje.
     * Protože návštěvník bývá definován prostřednictvím vnitřní třídy
     * příslušného testeru, vrací většinou instanci své vnější třídy.
     *
     * @return Sdružený tester
     */
    abstract public ASolutionTester getTester();



//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***********************************************************************
     * Vrátí testovanou hru. Nutnost použít zjišťovací metody zabezpečí,
     * aby potomci nemohli omylem tento odkaz změnit
     *
     * @return Testovaná hra
     */
    public final IGame getGame()
    {
        return game;
    }


    /***********************************************************************
     * Vrátí scénář, podle nějž se hra testuje.
     *
     * @return Scénář, podle nějž se hra testuje
     */
    public final Scenario getScenario()
    {
        return scenario;
    }


    /***********************************************************************
     * Nastaví scénář, podle nějž se hra bude testovat.
     *
     * @param scenario Scénář, podle nějž se hra bude testovat
     */
    public final void setScenario(Scenario scenario)
    {
        this.scenario = scenario;
    }


    /***************************************************************************
     * Vrátí informaci o tom, je-li v prověřovaném zadání povoleno testování hry
     * i v případě, kdy správce scénářů neprojde verifikací.
     *
     * @return Je-li v prověřovaném zadání povoleno testování hry
     *         i v případě, kdy správce scénářů neprojde verifikací,
     *         vrátí {@code true}, jinak vrátí {@code false}
     */
    public boolean isAllowedImperfectSM()
    {
        return false;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Akce, která se má provést po provedení startovacího kroku hry
     * (tj. ve chvíli, kde je hra již inicializována),
     * ale před jeho testem, tj. před ověření, že stav hry odpovídá scénáři.
     * Cílem metody je připravit potřebné informace o testované hře.
     *
     * @param scenario Scénář, podle nějž se bude hra provádět
     */
    public void afterGameStart(Scenario scenario)
    {
    }


    /***************************************************************************
     * Akce, která se má provést po provedení kroku hry,
     * ale před jeho testem, tj. před ověření, že stav hry odpovídá scénáři.
     *
     * @param step      Aktuálně testovaný krok scénáře
     * @param message   Zpráva vrácená hrou v daném kroku
     */
    public void beforeStepTest(ScenarioStep step, String message)
    {
    }


    /***************************************************************************
     * Akce, která se má provést po testu aktuálního kroku.
     *
     * @param step      Aktuálně testovaný krok scénáře
     * @param message   Zpráva vrácená hrou v daném kroku
     */
    public void afterStepTest(ScenarioStep step, String message)
    {
    }


    /***************************************************************************
     * Akce, která se má provést po testu posledního kroku.
     *
     * @param throwable      Objekt případně vyhozené chyby či výjimky
     * @param verboseMessage Kompletní zpráva o průběhu testu
     */
    public void afterGameEnd(Throwable throwable, String verboseMessage)
    {
    }


    /***************************************************************************
     * Akce, která se má provést po testu posledního kroku.
     *
     * @param summary Přepravka s kompletními informacemi o průběhu hry
     */
    public void afterGameEnd(GameSummary summary)
    {
        afterGameEnd(summary.getThrowable(), summary.getVerboseMessage());
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
