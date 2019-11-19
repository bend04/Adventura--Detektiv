/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.common;

import eu.pedu.adv19s_fw.test_util.default_game.I18n;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyBag;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyGame;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyItem;

import java.util.Optional;



@SuppressWarnings({"rawtypes", "unchecked"})
/*******************************************************************************
 * {@code ActionRead} instances process the commands
 * that are responsible for reading certain item.
 * In this game the paper or the magazine can be read.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class ActionRead extends AAction
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
     * reading the paper or magazine (after checking the defined conditions).
     */
    ActionRead()
    {
        super (I18n.L("aREAD"),
        "It verifies that the player holds the item he wants to read,\n"
      + "that this item is readable and the player has glasses on.\n"
      + "If these conditions are fulfilled, it reveals to the player \n"
      + "the text written at the given item.\n"
      + "Otherwise nothing will be done "
      + "and the error message will be reported.");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Reveals the text written at the given item to the player.
     * But before that it checks,
     * <ul>
     * <li>if the item that has to be read is given,</li>
     * <li>if this item is in the bag (i.e. if the player holds it),</li>
     * <li>if this item is readable (it has to be a paper or a magazine,</li>
     * <li>if the player has put the glasses on.</li>
     * </ul>
     *
     * @param parameters Item the player wants to read
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return I18n.L("mNO_READING");                        //==========>
        }
        String      itemName = arguments[1];
        IMyGame               game = AAction.getGame();
        IMyBag                 bag = game.getBag();
        Optional<IMyItem>    oItem = bag.getOItem(itemName);
        if (! oItem.isPresent()) {
            return I18n.L("mNOT_IN_BAG");                        //==========>
        }
        String message;
        if (I18n.L("PAPER").equalsIgnoreCase(itemName)) {
            message = I18n.L("mWRITTEN_ON_PAPER");
        }
        else if (I18n.L("MAGAZINE").equalsIgnoreCase(itemName)) {
            message = I18n.L("mWRITTEN_IN_MAGAZINE");
        }
        else {
            return I18n.L("mNOT_READABLE") + itemName;         //==========>
        }
        return I18n.L("mWANT_READ") + oItem.get().getName() +
               (State.isGlassesPutOn()  ?  message  :  I18n.L("mNO_GLASS"));
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
