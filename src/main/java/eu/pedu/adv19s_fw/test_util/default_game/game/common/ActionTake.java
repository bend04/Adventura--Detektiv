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
 * {@code ActionTake} instances process the commands
 * that take away items from the current place and put them to the bag.
 * </p>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class ActionTake extends AAction
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
     * taking an item away from the current place and putting it into the bag.
     */
    ActionTake()
    {
        super (I18n.L("aTAKE"),
        "It moves the given item from the current place to the bag.\n"
      + "It moves only items that can be picked up.\n"
      + "Otherwise nothing will be done "
      + "and the error message will be reported.");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Removes the item given in an argumenant from the current place (room)
     * and puts it into the bag.
     * But it requires so that the item with the given name<br>
     * a) would be located in the current place,<br>
     * b) could be picked up and<br>
     * c) would fit into the bag.<br>
     * Otherwise nothing will be done and the command is reported as wrong.
     *
     * @param arguments Parameters of the command
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return I18n.L("mNO_TAKE_ITEM");
        }
        String         itemName = arguments[1];
        IMyGame            game = AAction.getGame();
        IMyWorld          world = game.getWorld();
        IMyPlace    currentRoom = world.getCurrentPlace();
        IMyBag              bag = game.getBag();
        Optional<IMyItem> oItem = currentRoom.getOItem(itemName);
        if (! oItem.isPresent()) {
            return I18n.L("mNOT_HERE") + itemName;
        }
        IMyItem item = oItem.get();
        if (item.getWeight() > bag.getCapacity()) {
            return I18n.L("mHEAVY_ITEM") + itemName;
        }
        boolean added = bag.tryAddItem(item);
        if (added) {
            if (currentRoom.equals(world.getORoom(I18n.L("ICE_BOX")).get()) &&
                                                  item.isAlcoholic()        &&
                                                  ! State.isMajor())
            {
                bag.removeItem(item);
                return Conversation.start(item);
            }
            currentRoom.removeItem(item);
            return I18n.L("mTAKEN") + itemName;
        }
        else {
            return I18n.L("mBAG_FULL");
        }
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
