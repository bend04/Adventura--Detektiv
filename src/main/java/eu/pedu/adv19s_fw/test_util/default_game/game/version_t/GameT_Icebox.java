/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.version_t;

import eu.pedu.adv19s_fw.game_txt.BasicActions;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.test_util.default_game.GSMFactoryTLit_Apartment;
import eu.pedu.adv19s_fw.test_util.default_game.IAuthorDemo;
import eu.pedu.adv19s_fw.test_util.default_game.I18n;
import eu.pedu.adv19s_fw.test_util.default_game.InitProperties;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyGame;
import eu.pedu.adv19s_fw.test_util.default_game.game.common.AAction;
import eu.pedu.adv19s_fw.test_util.default_game.game.common.ANamed;

import java.util.Collection;



/*******************************************************************************
 * The {@code GameT_Icebox} instance (singleton) is a game manager.
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
public class GameT_Icebox
     extends ANamed
    implements IMyGame<ItemT, PlaceT_Room>, IAuthorDemo
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** The crate keeping the mandatory action names. */
    private static final BasicActions BASIC_ACTIONS;

    /** The crate keeping the mandatory action names. */
    private static final String GAME_NAME;

    /** The only instance (singleton) of this game. */
    private static final GameT_Icebox SINGLETON;



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
        SINGLETON = new GameT_Icebox();
    }



//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * The factory method returning the only instance of the given game.
     *
     * @return The instance of the given game
     */
    public static GameT_Icebox getInstance()
    {
        return SINGLETON;
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * The private constructor defining the only instance of the game class.
     */
    private GameT_Icebox()
    {
        super(GAME_NAME);
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


    /***********************************************************************
     * Returns the bag to which the player will save the taken items.
     *
     * @return The bag to which the player saves the taken items
     */
    @Override
    public BagT_Hands getBag()
    {
        return BagT_Hands.getInstance();
    }


    /***********************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which also the mother class of this instance is.
     *
     * @return The class-object of the factory class
     */
    @Override
    public Class<GSMFactoryTLit_Apartment> getFactoryClass()
    {
        return GSMFactoryTLit_Apartment.class;
    }


    /***********************************************************************
     * Returns the world in which the game takes place.
     *
     * @return The world in which the game takes place
     */
    @Override
    public WorldT_Apartment getWorld()
    {
        return WorldT_Apartment.getInstance();
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
