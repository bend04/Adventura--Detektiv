/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.scenario;

import eu.pedu.adv19s_fw.game_gui.IGSMFactoryG;
import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_gui.IScenarioManagerG;
import eu.pedu.adv19s_fw.game_gui.IUIG;
import eu.pedu.adv19s_fw.test_util.default_game.GSMFactoryG_Apartment;

//import eu.pedu.adv19s_fw.test_util._Test_115;


/*******************************************************************************
 * Instance třídy {@code AScenarioManagerG} spravují scénáře své hry
 * předpokládající grafické uživatelské rozhraní.
 * Každá hra musí mít k dispozici minimálně
 * <b>základní úspěšný scénář</b>,
 * který demonstruje některou z možných cest úspěšného dokončení hry,
 * a <b>chybový scénář</b>,
 * který demonstruje reakci hry na chybně zadané příkazy.
 * <p>
 * Jednotlivé scénáře se musí lišit svým názvem,
 * přičemž názvy základního úspěšného a základního chybového scénáře
 * jsou předem pevně dány a není je možno změnit.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public abstract class AScenarioManagerG
              extends AScenarioManager
           implements IScenarioManagerG
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Vrátí tovární objekt poskytující klíčové objekty ukázkové hry.
     *
     * @return Požadovaný tovární objekt
     */
    public static IGSMFactoryG getDefaultFactory()
    {
        IGSMFactoryG factory = new GSMFactoryG_Apartment();
        return factory;
    }


    /***************************************************************************
     * Vrátí instanci hry, kterou můžeme hrát podle spravovaných scénářů.
     * Metoda však nezaručuje stav hry, tj. jestli je např. právě rozehraná
     * a nebude ji proto třeba ji před dalším spuštěním nejprve ukončit.
     *
     * @return Instance hry hratelné podle spravovaných scénářů
     */
    public static final IGameG getDemoGameG()
    {
        Class<? extends IGSMFactoryG> factoryClass =GSMFactoryG_Apartment.class;
        IGSMFactoryG factory = IGSMFactoryG.getInstanceOfFactory(factoryClass);
        IGameG game = factory.getGame();
        return game;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Factory class, the instances of which are factory objects providing
     *  the instance of scenario manager and the game,
     *  the scenario of which the manager manages. */
    private final Class<? extends IGSMFactoryG> factoryClassG;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří nového správce scénářů spravujícího všechny scénáře hry,
     * která je instancí třídy zadané svým class-objektem.
     * <p>
     * Správce, jehož je daná instance rodičovským podobjektem,
     * není po vytvoření tohoto rodiče ještě plně funkční - takovým se stane
     * až poté, co budou do jeho správy předány všechny požadované scénáře
     * a seznam spravovaných scénářů se zalepí.
     *
     * @param factoryClass  Class-objekt třídy, která je schopna zprostředkovat
     *                      instanci vytvářeného správce scénářů.
     */
    protected AScenarioManagerG(Class<? extends IGSMFactoryG> factoryClass)
    {
        super(factoryClass);
        this.factoryClassG = factoryClass;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which is also the mother class of this instance.
     *
     * @return The class-object of the factory class
     */
    @Override
    public Class<? extends IGSMFactoryG> getFactoryClass()
    {
        return factoryClassG;
    }

//
//    /***************************************************************************
//     * Vrátí scénář určený pro test GUI.
//     *
//     * @return Scénář určený pro test GUI
//     */
//    public final Scenario getGuiTestScenario()
//    {
//        verifySealed();
//        return getScenario(GUI_TEST_SCENARIO_NAME);
//    }
//


//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Test demonstrující hraní hry na implicitním GUI.
     */
    public void testGameG()
    {
//        _Test_115 test = _Test_115.getInstance(getDemoGameG());
//        test.playGameByScenario(HAPPY_SCENARIO_NAME);
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================



//##############################################################################
//\MM== MAIN METHOD ============================================================

    /***************************************************************************
     * Metoda spouštějící celou aplikaci.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        IGSMFactoryG factory = AScenarioManagerG.getDefaultFactory();
        IGameG       game    = factory.getGame();
        IUIG         gui     = factory.getUI();
        gui.startGame(game);
    }
}
