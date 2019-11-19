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

import java.util.Optional;



@SuppressWarnings({"rawtypes", "unchecked"})
/*******************************************************************************
 * {@code ActionPickUp} instances process the commands
 * that take away items from the current place and puts them into the bag.
 * </p>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class ActionPutDown extends AAction
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
     * Creates an action instance for taking
     * the item away from the bag and putting it into the current place.
     */
    ActionPutDown()
    {
        super (I18n.L("aPUT_DOWN"),
        "It moves the given item from the bag into the current place.");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Removes the item given in an argumenant from the bag
     * and puts it into the current place (room).
     * But it requires that the given named item would be located in the bag.
     * Otherwise nothing will be done and the command is reported as wrong.
     *
     * @param arguments Parameters of the command
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return I18n.L("mNO_PUT_ITEM");
        }
        String         itemName = arguments[1];
        IMyGame            game = AAction.getGame();
        IMyBag              bag = game.getBag();
        Optional<IMyItem> oItem = bag.getOItem(itemName);
        if (! oItem.isPresent()) {
            return I18n.L("mNOT_IN_BAG") + itemName;
        }
        IMyItem item = oItem.get();
        bag.removeItem(item);
        IMyWorld       world = game.getWorld();
        IMyPlace currentRoom = world.getCurrentPlace();
        currentRoom.addItem(item);
        return I18n.L("mPUT_DOWN") + item.getName();
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
