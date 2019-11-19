/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.common;

import eu.pedu.adv19s_fw.test_util.default_game.I18n;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyItem;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyPlace;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyWorld;
import java.util.Optional;



@SuppressWarnings({"rawtypes", "unchecked"})
/*******************************************************************************
 * {@code ActionOpen} instances process the commands
 * which have to close certain item.
 * In this game the item is always the ice-box in kitchen,
 * but generally the game can be extended to opening other items
 * in other places.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class ActionClose extends AAction
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
     * Creates an action instance for
     * closing the item that is the current place.
     * After this closing, the place in which the closed item is placed
     * becomes the current place.
     */
    ActionClose()
    {
        super (I18n.L("aCLOSE"),
        "It verifies if the player really wants to close ice-box,\n"
      + "given as the command argument.\n"
      + "If it is so, it is closed and the player moves to kitchen.\n"
      + "Otherwise nothing will be done "
      + "and the error message will be reported.");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Closes the given item and moves the player to the place,
     * in which the closed item is located.
     * But before that it checks,
     * <ul>
     * <li>if the item that should be opened is given,</li>
     * <li>if this item is the ice-box,</li>
     * <li>if the ice-box is a current place.</li>
     * </ul>
     *
     * @param arguments The only allowed argument is up-to-now the ice-box
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return I18n.L("mNO_CLOSE");
        }
        String       roomName = arguments[1];
        IMyWorld        world = AAction.getGame().getWorld();
        IMyPlace currentPlace = world.getCurrentPlace();
        if (! currentPlace.getName().equalsIgnoreCase(roomName)) {
            return I18n.L("mNOT_CURRENT_PLACE");
        }
        if (! roomName.equalsIgnoreCase(I18n.L("ICE_BOX"))) {
            return I18n.L("mONLY_ICEBOX");
        }
        Optional<IMyPlace> oRoom = world.getORoom(I18n.L("KITCHEN"));
        IMyPlace      newCurrent = oRoom.get();
        world.setCurrentPlace(newCurrent);
        return I18n.L("mICEBOX_CLOSED");
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
