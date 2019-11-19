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
 * that have to open certain item.
 * In this game the item is always the ice-box in the kitchen,
 * but generally the game can be extended by opening other items
 * in other places.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class ActionOpen extends AAction
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
     * opening the item that can be opened
     * (after checking the defined conditions)
     * and after opening it becomes a current place.
     */
    ActionOpen()
    {
        super (I18n.L("aOPEN"),
        "It verifies that the player really wants to open the open-able item,\n"
      + "given as an argument of the command.\n"
      + "If it opens the ice-box, it checks if it is supported.\n"
      + "If all conditions are fulfilled, "
      + "the player moves into the given item.\n"
      + "Otherwise nothing will be done "
      + "and the error message will be reported.");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Opens the given item and moves the player into it as into a new place.
     * But before that it checks,
     * <ul>
     * <li>if the item that has to be open is given,</li>
     * <li>if this item is in the current place,</li>
     * <li>if this item can be opened, i.e. if it is also the place,</li>
     * <li>if the ice-box has been already supported to be open.</li>
     * </ul>
     *
     * @param parameters The only allowed argument is up-to-now the ice-box
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return I18n.L("mNO_OPENING");
        }
        String         itemName = arguments[1];
        IMyWorld          world = AAction.getGame().getWorld();
        IMyPlace    currentRoom = world.getCurrentPlace();
        Optional<IMyItem> oItem = currentRoom.getOItem(itemName);
        if (! oItem.isPresent()) {
            return I18n.L("mNOT_HERE");
        }
        Optional<IMyPlace> oDestinationRoom = world.getORoom(itemName);
        if (! oDestinationRoom.isPresent()) {
            return I18n.L("mNOT_OPENABLE");
        }
        if (! State.isIceboxUnderlaid()) {
            return I18n.L("mICEBOX_CANNOT_BE_OPENED");
        }
        world.setCurrentPlace(oDestinationRoom.get());
        return I18n.L("mICEBOX_OPENED");
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
