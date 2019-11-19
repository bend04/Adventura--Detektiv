/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.common;

import eu.pedu.adv19s_fw.test_util.default_game.I18n;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyBag;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyGame;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyItem;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyPlace;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyWorld;

import java.time.LocalDate;
import java.util.function.Function;



@SuppressWarnings({"rawtypes", "unchecked"})
/*******************************************************************************
 * The {@code Conversation} class defines the code holding a conversation with
 * the user. When holding it the whole system is in a special state,
 * in which processing of texts given by the user is modified.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class Conversation
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** The lowest acceptable age. */
    private static final int LOW_AGE = 5;

    /** The highest acceptable age. */
    private static final int HIGH_AGE = 105;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** The alcoholic drink that the player wants to take from the ice-box
     *  and for which the whole conversation is held. */
    private static IMyItem drink;

    /** The variable referring to the function responsible for the reaction
     *  on the player answer at the given conversation state. */
    private static Function<String, String> stateDependentAnswer;

    /** The player age given at the first conversation phase.
     *  In the second phase it will be compared with the given birth year. */
    private static int age;



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Method solving the reaction to player answers within the conversation.
     *
     * @param command The player answer to the previous question
     * @return The game answer to the player
     */
    static String answer(String command)
    {
        return stateDependentAnswer.apply(command);
    }


    /***************************************************************************
     * Starts the conversation on impossibility to take the object,
     * that is an alcoholic drink.
     *
     * @param drink The object that the player wants to take from the ice-box
     * @return The first part of the conversation said by the ice-box
     */
    static String start(IMyItem drink)
    {
        State.setConversation(true);
        Conversation.  drink = drink;
        String     drinkName = drink.getName();
        stateDependentAnswer = Conversation::waitingForTheAge;
        return I18n.L("mTAKES_ALCOHOL") + drinkName + "."
             + I18n.L("mHOW_OLD");
//             "You try to take from an intelligent ice-box " + drinkName +
//             "This ice-box does not enable serving of alcoholic drinks " +
//             "to under-agers.\nWhat is your age?");
    }




//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================


    /***************************************************************************
     * Method solving the game reaction to user answers in the conversation
     * first stage, when the user has to enter his age.
     *
     * @param command The user answer
     * @return The game answer to the player
     */
    private static String waitingForTheAge(String userAnswer)
    {
        try {
            age = Integer.parseInt(userAnswer);
            if ((age < LOW_AGE)  ||  (HIGH_AGE < age)) {
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe) {
            return String.format(I18n.L("fWRONG_INTEGER")
                               + I18n.L("fNOT_ALLOWED")
                               + I18n.L("fONCE_MORE"),
                                 I18n.L("fAGE"),
                                 I18n.L("LOW_AGE"),
                                 I18n.L("HIGH_AGE"));
        }
        if (age < 18) {
            State.setConversation(false);
            return String.format(I18n.L("fTOO_LOW")
                               + I18n.L("fNOT_ALLOWED"), drink.getName());
        }
        stateDependentAnswer = Conversation::waitingForTheYear;
        return I18n.L("mBIRTH");
            // "In which year you were born?";
    }


    /***************************************************************************
     * Method solving the game reaction to user answers in the conversation
     * second stage, when the user has to enter his birth year.
     *
     * @param command The user answer
     * @return The game answer to the player
     */
    private static String waitingForTheYear(String userAnswer)
    {
        int year;
        try {
            year = Integer.parseInt(userAnswer);
        }catch(NumberFormatException nfe) {
            return String.format(I18n.L("fWRONG_INTEGER")
                               + I18n.L("fONCE_MORE"),
                                 I18n.L("fYEAR"));
        }
        State.setConversation(false);

        int thisYear   = LocalDate.now().getYear();
        int countedAge = thisYear - year;
        if (Math.abs(age - countedAge) > 1) {
            return String.format(I18n.L("fDOES_NOT_MATCH")
                               + I18n.L("fNOT_ALLOWED"),
                                 age, year, drink.getName());
        }
        State.setMajor(true);
        IMyGame            game = AAction.getGame();
        IMyWorld          world = game.getWorld();
        IMyPlace    currentRoom = world.getCurrentPlace();
        IMyBag              bag = game.getBag();
        currentRoom.removeItem(drink);
        bag        .tryAddItem(drink);
        return I18n.L("mBELIEVE") + I18n.L("mTAKEN_FROM_IB")
             + drink.getName()    + I18n.L("mDO_NOT_FORGET");
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     *
     */
    private Conversation()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
