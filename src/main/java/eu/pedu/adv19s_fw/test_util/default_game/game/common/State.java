/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.common;



/*******************************************************************************
 * The library class {@code State} is a box for various flags,
 * that should be remembered in the game course.

 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class State
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Flag of glasses on. */
    private static boolean glassesPutOn;

    /** Flag indicating, if the ice-box has already been underlaid. */
    private static boolean iceboxUnderlaid;

    /** Flag of running conversation. */
    private static boolean isConversation;

    /** Flag of verified full age. */
    private static boolean isMajor;



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Returns information if the player has his glasses on.
     *
     * @return {@code true} if he has the glasses on, otherwise {@code false}
     */
    static boolean isGlassesPutOn()
    {
        return glassesPutOn;
    }


    /***************************************************************************
     * Sets the flag indicating if the player has his glasses on.
     *
     * @param glassesPutOn The set value
     */
    static void setGlassesPutOn(boolean glassesPutOn)
    {
        State.glassesPutOn = glassesPutOn;
    }


    /***************************************************************************
     * Returns information if the ice-box is underlaid.
     *
     * @return {@code true} if it is underlaid, otherwise {@code false}
     */
    static boolean isIceboxUnderlaid()
    {
        return iceboxUnderlaid;
    }


    /***************************************************************************
     * Sets the flag indicating if the ice-box is underlaid.
     *
     * @param iceboxUnderlaid The set value
     */
    static void setIceboxUnderlaid(boolean iceboxUnderlaid)
    {
        State.iceboxUnderlaid = iceboxUnderlaid;
    }


    /***************************************************************************
     * Returns information if the game is in conversation mode.
     *
     * @return {@code true} if it is in this mode, otherwise {@code false}
     */
    static boolean isConversation()
    {
        return isConversation;
    }


    /***************************************************************************
     * Sets the flag indicating if the game is in the conversation mode.
     *
     * @param isConversation The set value
     */
    static void setConversation(boolean isConversation)
    {
        State.isConversation = isConversation;
    }


    /***************************************************************************
     * Returns information if the player's full age has been verified.
     *
     * @return {@code true} if it is verified, otherwise {@code false}
     */
    static boolean isMajor()
    {
        return isMajor;
    }


    /***************************************************************************
     * Sets the flag indicating if the player's full age is verified.
     *
     * @param isMajor The set value
     */
    static void setMajor(boolean isMajor)
    {
        State.isMajor = isMajor;
    }




//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Initializes all attributes keeping information
     * concerning the current state of the game and its parts.
     */
    static void initialize()
    {
        glassesPutOn    = false;
        iceboxUnderlaid = false;
        isConversation  = false;
        isMajor         = false;
    }




//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Private constructor preventing to create an instance.
     */
    private State()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
