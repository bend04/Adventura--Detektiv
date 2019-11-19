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
 * {@code ActionHelp} instances process the commands
 * that induce the player puts on the glasses.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class ActionPutGlassesOn extends AAction
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
     * the player puts on the glasses.
     */
    ActionPutGlassesOn()
    {
        super (I18n.L("aPUT_ON"),
        "It puts the given item on.\n"
      + "The item has to be glasses (nothing else can be put on)\n"
      + "and the player has to hold them in his hand in the \"bag\".\n"
      + "Otherwise nothing will be done "
      + "and the error message will be reported.");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Simulates putting the given item on. This object should be
     * the glasses, which has to be in the bag (in hand).
     *
     * @param arguments Parameters of the command
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return I18n.L("mNO_PUT_ON");
        }
        String            itemName = arguments[1];
        IMyGame               game = AAction.getGame();
        IMyBag                 bag = game.getBag();
        Optional<IMyItem> oGlasses = bag.getOItem(itemName);
        if (! oGlasses.isPresent()) {
            return I18n.L("mNOT_IN_BAG") + itemName;
        }
        if (I18n.L("GLASSES").equalsIgnoreCase(itemName)) {
            State.setGlassesPutOn(true);
            bag.removeItem(oGlasses.get());
            return I18n.L("mGLASS_PUT_ON");
        }
        else {
            return I18n.L("mNOT_PUTABLE") + itemName;
        }
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
