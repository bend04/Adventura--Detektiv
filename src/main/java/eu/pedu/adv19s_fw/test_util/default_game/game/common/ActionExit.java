/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.common;

import eu.pedu.adv19s_fw.test_util.default_game.I18n;



/*******************************************************************************
 * {@code ActionExit} instances process the commands
 * which require the prematured game termination.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class ActionExit extends AAction
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
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates the action instance for
     * prematured termination of the game.
     */
    ActionExit()
    {
        super (I18n.L("aEXIT"),
               "It says thanks, good-bye and ends the game.");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * The method ends the game and says thanks to the player.
     *
     * @param arguments Parameters of the command - not used here
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        AAction.stopGame();
        return I18n.L("mGOOD_BYE");
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
