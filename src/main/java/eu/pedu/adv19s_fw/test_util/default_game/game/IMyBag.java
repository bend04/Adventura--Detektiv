/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game;

import eu.pedu.adv19s_fw.game_txt.IBag;





/*******************************************************************************
//%L+ CZ
 * Instance interfejsu {@code IMyBag} představují ...
 *
 * @param <TI> Typ h-objektů
//%Lx EN
 * The {@code IMyBag} interface instances represent ...
 *
 * @param <TI> Type of items
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IMyBag<TI extends IMyItem>
         extends IBag, IMyItemContainer<TI>
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns information if the bag is full,
     * or if some item can be still put in.
     *
     * @return {@code true} if it is full, {@code false} otherwise
     */
//    @Override
    boolean isFull()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================

    /***************************************************************************
     * The method initializing the bag.
     * As the player of this game has an empty bag at the game beginning,
     * this method can only clean the collection of items in the bag.
     */
//    @Override
    public void initialize()
    ;


    /***************************************************************************
     * If the given item fits to the bag, it adds it;
     * after that it returns the message on the result.
     *
     * @param item The item that has to be added into the bag
     * @return The message on the result: {@code true} = was added,
     *         {@code false} = was not added
     */
//    @Override
    public boolean tryAddItem(TI item)
    ;



//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
