/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game;

import eu.pedu.adv19s_fw.game_txt.IItem;



/*******************************************************************************
 * Instances of the {@code IItem} interface represent the items in places.
 * These items can be things as well as persons or other entities
 * (flowers, light, charm etc.)
 * <p>
 * Some of these items can qualify certain game or place properties
 * (the light is on), others may be determined for taken and so gain a property
 * (e.g. ability to go through a strange place), or capability
 * (e.g. key for unlocking the door, sward for killing the monster,
 * money for bribing the guard etc.),
 * <p>
 * You can define items which serve simultaneously as places and can
 * therefore contain other items (e.g. safe, window etc.).
 * You have to enter in these items/places with a special command
 * (e.g. <i>open safe</i>, <i>look_from window</i>, etc.) and leave them with
 * another special command (e.g. <i>close safe</i>, <i>shut window</i> etc.).
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IMyItem
         extends IItem
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the item weight, and/or the corresponding characteristics.
     * The items that cannot be raised
     * have higher weight than the bag capacity is.
     *
     * @return Weight of the item
     */
    @Override
    public int getWeight()
    ;


    /***************************************************************************
     * Returns information if it is the alcoholic drink.
     *
     * @return If it is the alcoholic drink, it returns {@code true},
     *         otherwise it returns {@code false}
     */
//    @Override
    public boolean isAlcoholic()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
