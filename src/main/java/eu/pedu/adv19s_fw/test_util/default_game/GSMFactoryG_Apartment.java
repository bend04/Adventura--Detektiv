/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game;

import eu.pedu.adv19s_fw.game_gui.IGSMFactoryG;
import eu.pedu.adv19s_fw.game_gui.IGSMFactoryProductG;
import eu.pedu.adv19s_fw.game_gui.IUIG;
import eu.pedu.adv19s_fw.scenario.AScenarioManagerG;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyGSMFactory;
import eu.pedu.adv19s_fw.test_util.default_game.game.version_g.GameG_Icebox;
import eu.pedu.adv19s_fw.test_util.default_game.smanagers
                                               .ScenMan_GLit_Icebox;



@SuppressWarnings("rawtypes")
/*******************************************************************************
 * {@code GSMFactoryG_Apartment } instances represent the factory objects
 * which are able to deliver the game instance,
 * an instance of scenario manager of this game
 * and an instance of the text user interface.
 * As long as some of these objects are not yet fully defined,
 * the methods throw the
 * {@link eu.pedu.adv19s_fw.utilities.UncompletedMethodException}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public   class GSMFactoryG_Apartment
    implements IGSMFactoryG, IAuthorDemo, IGSMFactoryProductG, IMyGSMFactory
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================

    static {
        new InitProperties("Implicitní textová hra - Služební byt s ledničkou",
                           GSMFactoryG_Apartment.class);
    }



//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates the factory object providing the key application objects.
     */
    public GSMFactoryG_Apartment()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which also the mother class of this instance is.
     *
     * @return The class-object of the factory class
     */
    @Override
    public Class<GSMFactoryG_Apartment> getFactoryClass()
    {
        return GSMFactoryG_Apartment.class;
    }

//
//    /***************************************************************************
//     * Returns the instance of the scenario manager.
//     *
//     * @return Required scenario manager
//     */
//    @Override
//    public ScenMan_TCon_Icebox getScenarioManager()
//    {
//        return ScenMan_GCon_Icebox.getInstance();
//    }
//

    /***************************************************************************
     * Returns the instance of the scenario manager.
     *
     * @return Required scenario manager
     */
    @Override
    public AScenarioManagerG getScenarioManager()
    {
        return ScenMan_GLit_Icebox.getInstance();
    }


    /***************************************************************************
     * Returns the instance of text version of the game.
     *
     * @return Required game
     */
    @Override
    public GameG_Icebox getGame()
    {
        return GameG_Icebox.getInstance();
    }


    /***************************************************************************
     * Returns the object executing the user interface.
     *
     * @return Required user interface
     */
    @Override
    public IUIG getUI()
    {
        IUIG ui = null;
//                 new UIA_JOptionPane();
//                 new UIB_Console();
//                 new UIC_GamePlayer(new UIC_GamePlayer.ByJOptionPane());
//                 new UIC_GamePlayer(new UIC_GamePlayer.ByScanner());
//                 new UID_Multiplayer(new UID_Multiplayer.ByJOptionPane());
//                 new UID_Multiplayer(new UID_Multiplayer.ByScanner());
        return ui;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
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
        GSMFactoryG_Apartment factory = new GSMFactoryG_Apartment();
        AScenarioManagerG scenarioManager = factory.getScenarioManager();
//        scenarioManager.autoTest();
        scenarioManager.testGame();
        System.exit(0);
    }
}
