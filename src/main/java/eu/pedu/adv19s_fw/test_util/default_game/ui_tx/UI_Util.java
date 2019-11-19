/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_tx;

import eu.pedu.adv19s_fw.game_txt.IBag;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IItem;
import eu.pedu.adv19s_fw.game_txt.IPlace;
import eu.pedu.adv19s_fw.game_txt.IWorld;

import java.util.Collection;



/*******************************************************************************
 * Instance třídy {@code UI_Util} představují ...
 * The {@code UI_Util} class instances represent ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class UI_Util
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Return the answer decorated with supplement information
     * describing the current game state.
     *
     * @param game   The played game
     * @return The decorated answer
     */
    public static String gameState(IGame game)
    {
        IBag bag = game.getBag();
        Collection<? extends IItem> bagItems = bag.getItems();

        IWorld world = game.getWorld();
        IPlace place = world.getCurrentPlace();
        Collection<? extends IPlace> neighbors  = place.getNeighbors();
        Collection<? extends IItem>  placeItems = place.getItems();

        String result = "\n--------------------------------------------------"
                      + "\nCurrent place: " + place
                      + "\nIts neighbors: " + neighbors
                      + "\nIts items: "     + placeItems
                      + "\nBag content: "   + bagItems
                      + "\n";
        return result;
    }



//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     */
    public UI_Util()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
