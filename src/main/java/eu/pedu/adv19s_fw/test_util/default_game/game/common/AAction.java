/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.common;

import eu.pedu.adv19s_fw.game_txt.IAction;
import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.test_util.default_game.I18n;
import eu.pedu.adv19s_fw.test_util.default_game.InitProperties;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyGSMFactory;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyGame;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



@SuppressWarnings("rawtypes")
/*******************************************************************************
 * The {@code AAction} abstract class is a common superclass of all classes,
 * the instances of which are responsible for interpretation of commands
 * entered by user playing the game.
 * Name of the executed action is the first word of the entered command.
 * The further words are interpreted as arguments.
 * <p>
 * You can define also a command that opens the conversation
 * (e.g. with a person present in the room) and thus switch to the mode,
 * in which the entered texts are not interpreted as commands,
 * but are passed to the defined object up to moment,
 * when the conversation ends and the object controlling the dialogue
 * switches the game back to the basic command mode.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
abstract
public    class AAction
        extends ANamed
     implements IAction
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Map mediating the conversion of the action name to its instance. */
    private static final Map<String, AAction> NAME_2_ACTION;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

//%L+ CZ
    /** Hra, jejíž příkazy se zpracovávají. */
//%Lx EN
    /** The game, which commands are processed. */
//%L-
    private static IMyGame game;

    /** Keeps information if the game is just played
     *  or only waits for being started. */
    private static boolean isAlive;



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================

    static {
        isAlive = false;
        NAME_2_ACTION = new HashMap<>();
        new ActionHelp();
        new ActionGoTo();
        new ActionTake();
        new ActionPutDown();
        new ActionExit();
        new ActionOpen();
        new ActionRead();
        new ActionPutGlassesOn();
        new ActionUnderlayIcebox();
        new ActionClose();
    }



//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

//%L+ CZ
    /***************************************************************************
     * Vrátí hru, jejíž příkazy se zpracovávají.
     *
     * @return Hra, jejíž příkazy se zpracovávají
//%Lx EN
    /***************************************************************************
     * Returns the game, which commands are processed.
     *
     * @return The game, which commands are processed
//%L-
     */
    protected static IMyGame getGame()
    {
        return game;
    }


    /***************************************************************************
     * Returns information if the game is currently running.
     * The running game cannot be started once again.
     * If we want to start the game again, we have to finish it firstly.
     *
     * @return If the game is running, it returns {@code true},
     *         otherwise it returns {@code false}
     */
    public static boolean isAlive()
    {
        return isAlive;
    }


    /***************************************************************************
     * Returns a collection of all actions that can be used in the game.
     *
     * @return A collection of all actions usable in the game
     */
    public static Collection<AAction> getAllActions()
    {
        Collection<AAction> collection, result;
        collection = NAME_2_ACTION.values();
        result     = Collections.unmodifiableCollection(collection);
        return result;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Processes the given command and returns the answer to the user.
     *
     * @param command The entered command
     * @return The answer of the game after processing the command
     */
    public static String executeCommand(String command)
    {
        command = command.trim();
        String answer;
        if (isAlive) {
            answer = State.isConversation()  ?  Conversation.answer(command)
                                             :  executeCommonComand(command);
        }
        else {
            answer = startGame(command);
        }
//        answer += "\n(demo-version included in framework)";
        return answer;
    }


    /***************************************************************************
     * Ensures the correct ending of the game
     * and saves information that the game is finished.
     */
    public static void stopGame()
    {
        isAlive = false;
    }




//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================


    /***************************************************************************
     * The method gradually initializes all key objects of the game.
     */
    private static void initialize()
    {
        InitProperties initProperties = InitProperties.getInstance();
        IMyGSMFactory  factory        = initProperties.factory;
        game = factory.getGame();
        game.getBag()  .initialize();
        game.getWorld().initialize();
        State.initialize();
    }


    /***************************************************************************
     * Finds out which action should be activated by the entered command,
     * and if it is a known action, it accomplishes this action.
     * Otherwise it returns the error message about the wrongly entered command.
     *
     * @param command Entered command
     * @return  Game answer to the entered command
     */
    private static String executeCommonComand(String command)
    {
        if (command.isEmpty()) {
            return I18n.L("mEMPTY_CMD");
        }
        String[] words       = command.toLowerCase().split("\\s+");
        String   acttionName = words[0];
        AAction  action      = NAME_2_ACTION.get(acttionName);
        String   answer;
        if (action == null) {
            answer = I18n.L("mNO_CMD");
        }
        else {
            answer = action.execute(words);
        }
        return answer;
    }


    /***************************************************************************
     * Verifies if the game is started with the proper (= empty) command,
     * and if yes, starts the game.
     *
     * @param command Command starting the game
     * @return  Game answer to the entered command
     */
    private static String startGame(String command)
    {
        String answer;
        if (command.isEmpty()) {
            initialize();
            answer  = I18n.L("mWELCOME_MSG");
            isAlive = true;
        }
        else {
            answer = I18n.L("mNOT_START");
        }
        return answer;
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Brief description of the given action. */
    private final String description;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates the parent sub-object of the created action.
     *
     * @param name  Name of the created action = text, which the player has to
     *              enter as an initial word of the entered command
     * @param description Brief description of the created action
     */
    AAction(String name, String description)
    {
        super(name);
        this.description = description;
        AAction previous = NAME_2_ACTION.put(name.toLowerCase(), this);
        if (previous != null) {
            throw new IllegalArgumentException(I18n.L("eAACTION_1") + name
                                             + I18n.L("eAACTION_2"));
//            throw new IllegalArgumentException(eAACTION_1 + name + eAACTION_2);
//                "\nAction «" + name + "» has been already created");
        }
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================

    /***************************************************************************
     * Processes the command composed from the given words
     * and returns the game answer to the user.
     * Number of arguments depend on particular action, however it must be
     * at least one, because the zeroth element contains the action name.
     * The number of remaining arguments depend on the action:
     * the actions of <i>end</i> and <i>help</i> type do not have any,
     * the actions of <i>go</i> and <i>take</i> type have one,
     * the actions of <i>apply</i> type ) can have two (e.g. apply key lock)
     * or three (e.g. apply key to lock) etc.
     *
     * @param arguments Action arguments –
     *                  their number can be different for each action,
     *                  but for all executions of the same action is the same
     * @return The answer of the game after processing the command
     */
    @Override
    abstract
    public String execute(String... arguments)
    ;



//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the action description with explanation of its function
     * and the meaning of individual parameters.
     * This description can be used as a help for usage of this action.
     *
     * @return Action description
     */
    @Override
    public String getDescription()
    {
        return description;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
