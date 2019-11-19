/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.DATA;

import java.util.Arrays;
import java.util.stream.Collectors;



/*******************************************************************************
 * The {@code Texts} library class serves as a box for text constants,
 * that are used at various places of the program.
 * By centralizing the definitions of these text strings we can easily reach,
 * that texts, that should be identical at various program places,
 * really will be identical.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class Texts
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Names of used places (rooms in this game) - without prefix. */
    public static final String
           HALL         = "Hall",
           BEDROOM      = "Bedroom",
           LIVING_ROOM  = "Living-room",
           BATHROOM     = "Bathroom",
           KITCHEN      = "Kitchen";


    /** Names of used items - without prefix. */
    public static final String
           SHOE_RACK    = "Shoe-rack",
           UMBRELLA     = "Umbrella",
           GLASSES      = "Glasses",
           SINK         = "Sink",
           TV           = "TV",
           MAGAZINE     = "Magazine",
           ICE_BOX      = "Ice-box",
           PAPER        = "Paper",
           BEER         = "Beer",
           RUM          = "Rum",
           SALAMI       = "Salami",
           BUN          = "Bun",
           WINE         = "Wine",
           BED          = "Bed",
           MIRROR       = "Mirror",
           BATHROBE     = "Bathrobe";


    /** Names of used actions - prefix {@code 'a'}. */
    public static final String
           aHELP        = "?",
           aGOTO        = "GoTo",
           aPUT_ON      = "Put_on",
           aOPEN        = "Open",
           aUNDERLAY    = "Underlay",
           aPUT_DOWN    = "Put_down",
           aREAD        = "Read",
           aTAKE        = "Take",
           aCLOSE       = "Close",
           aEXIT        = "End";


    /** Part of the message supplement informing about the current state. */
    public static final String
           sNEIGHBORS   = "Neighbors: ",
           sITEMS       = "Items:     ",
           sBAG         = "Bag:       ";


    /** Messages written as reaction to the command
     * running the mandatory actions - prefix {@code 'm'}. */
    public static final String
    mNOT_START = "The first command is not the starting one."
        + "\nNot running game can be started only by the starting command.",

    mWELCOME_MSG =
          "Welcome in the official apartment. You are surely hungry."
        + "\nFind the ice-box - refreshment is waiting you there.",

    mANCO           = "The entered action has not been carried out.\n",
    mASK_HELP       = "\nIf you need a help, enter a command ?",

    mEMPTY_CMD      = "You have entered an empty command.\n"
                    + "It can be used only for starting the game." + mASK_HELP,
    mNO_CMD         = mANCO + "I do not know this command." + mASK_HELP,

    mMOVED          = "You have moved to a room: ",
    mNO_TARGET      = mANCO + "You did not enter where to go",
    mNOT_NEIGHBOR   = mANCO + "The destination room is not a neighbor "
                            + "of the current room: ",

    mTAKEN          = "You have taken an item: ",
    mNO_TAKE_ITEM   = mANCO + "You did not enter what should be taken",
    mHEAVY_ITEM     = mANCO + "The entered item cannot be taken: ",
    mNOT_HERE       = mANCO + "The entered item is not in the room: ",
    mBAG_FULL       = mANCO + "You cannot take the entered item, "
                            + "both your hands are already full.",

    mPUT_DOWN       = "You have put down an item: ",
    mNO_PUT_ITEM    = mANCO + "You did not enter what should be put down",
    mNOT_IN_BAG     = mANCO + "The item is not in the bag: ",

    mHELP           = "Commands that can be entered during the game course:"
                  + "\n====================================================",

    mGOOD_BYE       = "Game end.\nThank you that you have tried our game.";


    /** Texts written in reaction to commands recalling the optional actions. */
    public static final String
    mNO_OPENING     = mANCO + "You did not enter what should be open",
    mNOT_OPENABLE   = mANCO + "The entered item cannot be open: ",
    mICEBOX_CANNOT_BE_OPENED = mANCO + "Ice-box cannot be open. "
                                     + "Paper with writing lies on it.",
    mNO_READING     = mANCO + "You did not enter what should be read",
    mNOT_READABLE   = mANCO + "At the entered item there is nothing to read: ",
    mWANT_READ      = "You have decided to read the ",
    mNO_GLASS = "."
        + "\nHowever, text is written with too small letters that are blurry."
        + "\nYou have to take on glasses.",
    mNO_PUT_ON    = mANCO + "You did not enter what the player should put on.",
    mNOT_PUTABLE  = mANCO + "The entered item cannot be put on: ",
    mGLASS_PUT_ON = "You have put glasses on.",
    mWRITTEN_ON_PAPER = "."
                    + "\nIt is written at the paper:"
                    + "\nIce-box stands askew and therefore "
                    + "it opens with difficulty."
                    + "\nIn case of problems, underlay it with something.",
    mWRITTEN_IN_MAGAZINE = "."
                    + "There is nothing interesting in the magazine",
    mWHAT_UNDERLAY  = mANCO + "You did not enter what you want to underlay.",
    mUNDERLAY_WITH  = mANCO + "You did not enter "
                    + "with what you want to underlay the entered item.",
    mNO_UNDERLAID   = mANCO
                    + "The item that you want to underlay is not in this room.",
    mNO_UNDERLAYMENT= mANCO + "The item that you want to use "
                    + "as an underlayment is not in your hands.",
    mNOT_UNDERLAYABLE = mANCO + "The entered item cannot be underlaid: ",
    mNOT_USABLE     = mANCO + "You cannot use the entered item "
                    + "as an underlayment for ice-box:",
    mWANT_UNDERLAY  = "You have decided to underlay ",
    mWITH_ITEM      = " with ",
    mNOT_CAPABLE    = mANCO + "Unfortunately your both hands are full\n"
                    + "and thus you have no empty hand for execution.",
    mICEBOX_UNDERLAID = "\nIce-box is successfully underlaid - "
                    + "now it could be open.",
    mICEBOX_OPENED  = "You have successfully opened the ice-box.",
    mTAKES_ALCOHOL  = "You try to take ",
    mHOW_OLD        = "\nThis is an intelligent ice-box which does not enable"
                    + "\nserving of alcoholic drinks to under-aged."
                    + "\nHow old are you?",
    mBIRTH          = "What is your year of birth?",
    mBELIEVE        = "I believe you and pass you the required drink.",
    mTAKEN_FROM_IB  = "\nYou have taken from ice-box: ",
    mDO_NOT_FORGET  = "\nBon appetit. Do not forget to close the ice-box.",
    mNO_CLOSE       = mANCO + "It was not entered what should be closed",
    mNOT_CURRENT_PLACE = mANCO + "Only the current place could be closed",
    mONLY_ICEBOX    = mANCO + "The only closable item is the ice-box",
    mICEBOX_CLOSED  = "You have successfully closed the ice-box.";


    /** Formats of messages written in reaction to certain commands. */
    public static final String
    fWRONG_INTEGER  = "You have to enter your %s as the whole number",
    fAGE            = "age",
    fYEAR           = "year of birth",
    fWRONG_RANGE    = " in the range of %d to %d",
    fONCE_MORE      = ".\nTry it once more.",
    fTOO_LOW        = "You cannot consume alcoholic drinks in your age.",
    fNOT_ALLOWED    = "\nWith regards to it I cannot distribute %s."
                    + "\nTake anything else or close the ice-box.",
    fDOES_NOT_MATCH = "Your quoted age does not correspond "
                    + "with the year of your birth %s";


    /** Parts of internal errors (exceptions) messages. */
    public static final String
    eAACTION_1   = "\nAction «",
    eAACTION_2   = "» has been already created",
    eITEM_PREFIX = "\nUnknown prefix value: «",
    eITEM_SUFFIX = "»",
    eLOCALE_SET  = "Locale was yet set.",
    eEND = null;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Returns a string containing the entered names separated with commas.
     *
     * @param názvy Names that are necessary to be unified
     * @return The resulting string from unified entered names
     */
    static String cm(String... názvy)
    {
        String result = Arrays.stream(názvy)
                              .collect(Collectors.joining(", "));
        return result;
    }




//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /** Private constructor preventing the instance creating.*/
    private Texts() {}



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
