/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.common;

import eu.pedu.adv19s_fw.game_txt.INamed;
import eu.pedu.adv19s_fw.game_txt.IWorld;
import eu.pedu.adv19s_fw.test_util.default_game.I18n;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyPlace;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyWorld;

import java.util.Optional;



@SuppressWarnings({"rawtypes", "unchecked"})
/*******************************************************************************
 * {@code ActionGoTo} instances process the commands
 * that move the player from the current place to the given neighboring one.
 * </p>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class ActionGoTo extends AAction
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
     * moving the player from the current place to the given neighboring one.
     */
    ActionGoTo()
    {
        super (I18n.L("aGOTO"),
        "It moves the player to the neighboring room given in an argument.\n"
      + "But it requires so that this room would be a neighbor "
      + "of the current room,\n"
      + "Otherwise nothing will be done "
      + "and the error message will be reported.");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Moves the player into the place (room) given in an argument.
     * Requires that this place has to be a neighbor of the current place,
     * otherwise nothing will be done and the command is reported as wrong.
     *
     * @param arguments Parameter of the command
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return I18n.L("mNO_TARGET");
        }
        String destinationName = arguments[1];
        IMyWorld         world = AAction.getGame().getWorld();
        IMyPlace   currentRoom = world.getCurrentPlace();
        Optional<IMyPlace> oDestination = INamed.getO(destinationName,
                                                  currentRoom.getNeighbors());
        if (! oDestination.isPresent()) {
            return I18n.L("mNOT_NEIGHBOR") + destinationName;
        }
        IMyPlace destinationRoom = oDestination.get();
        world.setCurrentPlace(destinationRoom);
        return I18n.L("mMOVED") + destinationRoom.getName();
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
