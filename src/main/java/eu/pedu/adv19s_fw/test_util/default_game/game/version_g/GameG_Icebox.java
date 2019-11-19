/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.version_g;

import eu.pedu.adv19s_fw.game_gui.IGSMFactoryG;
import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_gui.Icon;
import eu.pedu.adv19s_fw.game_txt.BasicActions;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.test_util.default_game.DATA.DataPkg;
import eu.pedu.adv19s_fw.test_util.default_game.GSMFactoryG_Apartment;
import eu.pedu.adv19s_fw.test_util.default_game.IAuthorDemo;
import eu.pedu.adv19s_fw.test_util.default_game.I18n;
import eu.pedu.adv19s_fw.test_util.default_game.InitProperties;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyGame;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyPlace;
import eu.pedu.adv19s_fw.test_util.default_game.game.common.AAction;
import eu.pedu.adv19s_fw.test_util.default_game.game.common.ANamed;
import eu.pedu.adv19s_fw.test_util.default_game.game.common.Util;
import eu.pedu.adv19s_fw.test_util.default_game.game.version_t.GameT_Icebox;
import java.net.URL;

import java.util.Collection;
import java.util.function.Supplier;



/*******************************************************************************
 * The {@code GameG_Icebox} instance (singleton) is a game manager.
 * It is able to accept individual commands and provide information
 * on current state of the game and its parts.
 * <p>
 * The game class has to be defined as a singleton and,
 * besides methods declared in the {@link IGame} interface,
 * it has to define the {@code getInstance()} static factory method.
 * Fulfilling of this condition cannot be verified by the compiler, but they
 * can by verified by test utilizing the associated scenario manager.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public   class GameG_Icebox
       extends ANamed
    implements IGameG, IMyGame<ItemG, PlaceG_Room>,IAuthorDemo
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** The crate keeping the mandatory action names. */
    private static final BasicActions BASIC_ACTIONS;

    /** The crate keeping the mandatory action names. */
    private static final String GAME_NAME;

    /** The only instance (singleton) of this game. */
    private static final GameG_Icebox SINGLETON;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================

    static {
        InitProperties init = InitProperties.getInstance();
        String aGOTO = I18n.L("aGOTO");
        BASIC_ACTIONS= new BasicActions(aGOTO,
                                        I18n.L("aTAKE"), I18n.L("aPUT_DOWN"),
                                        I18n.L("aHELP"), I18n.L("aEXIT"));
        GAME_NAME = init.gameName;
        SINGLETON = new GameG_Icebox();
    }



//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * The factory method returning the only instance of the given game.
     *
     * @return The instance of the given game
     */
    public static GameG_Icebox getInstance()
    {
        return SINGLETON;
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

        /** Obrázek plánku hry. */
        private final Icon MAP_IMAGE;

        /** Obrázek hráče. */
        private final Icon PLAYER_IMAGE;

        /** URL souboru s nápovědou. */
        private final URL HELP_URL;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * The private constructor defining the only instance of the game class.
     */
    private GameG_Icebox()
    {
        super(GAME_NAME);
        MAP_IMAGE    = Util.getImage("Plánek");
        PLAYER_IMAGE = Util.getImage("Player");
        HELP_URL     = DataPkg.class.getResource("Help.html");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns information if the game is currently running.
     * The once started game cannot be started again.
     * If you want to start the game again you have to finish it firstly.
     *
     * @return If the game is running, it returns {@code true},
     *         otherwise it returns {@code false}
     */
    @Override
    public boolean isAlive()
    {
        return AAction.isAlive();
    }


    /***************************************************************************
     * Returns the collection of all actions usable in the game.
     *
     * @return The collection of all actions usable in the game
     */
    @Override
    public Collection<AAction> getAllActions()
    {
        return AAction.getAllActions();
    }


    /***************************************************************************
     * Returns the crate with names of mandatory actions, i.e. actions for
     * <ul>
     *   <li>moving into neighboring place,</li>
     *   <li>taking item from the current place and putting it into bag,</li>
     *   <li>taking item from the bag and
     *       putting it down into the current place,</li>
     *   <li>asking for help,</li>
     *   <li>immediate game termination.</li>
     * </ul>
     *
     * @return The crate with names of mandatory actions
     */
    @Override
    public BasicActions getBasicActions()
    {
        return BASIC_ACTIONS;
    }


    /***************************************************************************
     * Returns the bag to which the player will save the taken items.
     *
     * @return The bag to which the player saves the taken items
     */
    @Override
    public BagG_Hands getBag()
    {
        return BagG_Hands.getInstance();
    }


    /***********************************************************************
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


    /***************************************************************************
     * Returns the world in which the game takes place.
     *
     * @return The world in which the game takes place
     */
    @Override
    public WorldG_Apartment getWorld()
    {
        return WorldG_Apartment.getInstance();
    }


        /***********************************************************************
         * Vrátí URL adresu stránky s nápovědou.
         *
         * @return URL adresa stránky s nápovědou
         */
        @Override
        public URL getHelpURL()
        {
            return HELP_URL;
        }


        /***********************************************************************
         * Vrátí obrázek s plánkem prostoru, v němž se hraje.
         *
         * @return Obrázek s plánkem prostoru
         */
        @Override
        public Icon getMap()
        {
            return MAP_IMAGE;
        }


        /***********************************************************************
         * Vrátí obrázek hráče, který bude zobrazován na plánku hry,
         * aby uživatel věděl, ve kterém prostoru se jeho hráč aktuálně nachází.
         *
         * @return Obrázek hráče
         */
        @Override
        public Icon getPlayer()
        {
            return PLAYER_IMAGE;
        }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Processes the given command and returns the answer to the user.
     * But it delegates the command processing itself to the action manager,
     * that is the class object {@link AAction}.
     *
     * @param command The entered command
     * @return The answer of the game after processing the command
     */
    @Override
    public String executeCommand(String command)
    {
        return AAction.executeCommand(command);
    }


    /***************************************************************************
     * Ends the whole game and returns the allocated resources.
     */
    @Override
    public void stop()
    {
        AAction.stopGame();
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
