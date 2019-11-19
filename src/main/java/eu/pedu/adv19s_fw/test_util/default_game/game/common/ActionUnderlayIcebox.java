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
 * {@code ActionUnderlayIcebox} instances process the commands
 * that have to underlay the given item with the given item.
 * In this game the supported item is always the ice-box in the kitchen,
 * but generally the game can be extended by supporting other items
 * in other places.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class ActionUnderlayIcebox extends AAction
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
     * moving of opening the item that can be opened
     * (after checking the defined conditions)
     * and after opening it becomes a current place.
     */
    ActionUnderlayIcebox()
    {
        super (I18n.L("aUNDERLAY"),
        "It underlaid the given item with the given item.\n"
      + "So that the command would work, "
      + "the first argument has to be an ice-box,\n"
      + "that has to be located in the current room,\n"
      + "that the second argument has to be a magazine,\n"
      + "which has to be located in the bag (the player has to hold it)\n"
      + "and that the player has one hand free "
      + "to be able to raise the ice-box.\n"
      + "Otherwise nothing will be done "
      + "and the error message will be reported.");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Underlays the item given in the first argument
     * with the item given in the second argument.
     * Before that it checks,
     * <ul>
     * <li>if the item that should be underlaid is given,</li>
     * <li>if the item serving as a underlayment is given,</li>
     * <li>if the item being underlaid is in the current place,</li>
     * <li>if the underlying item (the underlayment) is in the bag
     *     (=in the player's hand),</li>
     * <li>if the item being underlaid is the ice-box,</li>
     * <li>if the underlying item (the underlayment) is the magazine.</li>
     * </ul>
     *
     * @param parameters The only allowed arguments are up-to-now
     *                   the "ice-box" as the first one
     *                   and the "magazine" as the second one
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return I18n.L("mWHAT_UNDERLAY");
        }
        if (arguments.length < 3) {
            return I18n.L("mUNDERLAY_WITH");
        }
        String nameUnderlaid  = arguments[1];
        String nameUnderlying = arguments[2];
        String message1       = I18n.L("mWANT_UNDERLAY") + nameUnderlaid
                              + I18n.L("mWITH_ITEM")     + nameUnderlying
                              + '.';
        String messageErr     = message1 + '\n';
        IMyGame            game = AAction.getGame();
        IMyWorld          world = game.getWorld();
        IMyPlace    currentRoom = world.getCurrentPlace();
        IMyBag              bag = game.getBag();
        Optional<IMyItem> oUnderlaidd = currentRoom.getOItem(nameUnderlaid);
        Optional<IMyItem> oUnderlying = bag        .getOItem(nameUnderlying);

        if (! oUnderlaidd.isPresent()) {
            return messageErr + I18n.L("mNO_UNDERLAID") + nameUnderlaid;
        }
        if (! oUnderlying.isPresent()) {
            return messageErr + I18n.L("mNO_UNDERLAYMENT") + nameUnderlaid;
        }
        if (! I18n.L("ICE_BOX").equalsIgnoreCase(nameUnderlaid)) {
            return messageErr + I18n.L("mNOT_UNDERLAYABLE") + nameUnderlaid;
        }
        else if (I18n.L("MAGAZINE").equalsIgnoreCase(nameUnderlaid)) {
            return messageErr + I18n.L("mNOT_USABLE") + nameUnderlying;
        }
        else if (bag.isFull()) {
            return messageErr + I18n.L("mNOT_CAPABLE");
        }
        else {
            bag.removeItem(oUnderlying.get());
            State.setIceboxUnderlaid(true);
            return message1 + I18n.L("mICEBOX_UNDERLAID");
        }
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
