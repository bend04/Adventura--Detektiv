/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game;

import eu.pedu.adv19s_fw.game_txt.IItemContainer;
import eu.pedu.adv19s_fw.test_util.default_game.game.version_t.ItemT;

import java.util.Collection;
import java.util.Optional;



/*******************************************************************************
 * Instances of the {@code IItemContainer} interface represent containers
 * containing items occuring in the game.
 * The special cases of these containers are places and bag.
 *
 * @param <TI> Type of items
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IMyItemContainer<TI extends IMyItem>
         extends IItemContainer
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns a collection of items located in the given container.
     *
     * @return Collection of items located in the given container
     */
    @Override
    public Collection<TI> getItems()
    ;


    /***************************************************************************
     * Returns the item with the given name wrapped into
     * an {@link Optional}{@code <}{@link ItemT}{@code >}.
     *
     * @param  name Name of the asked item
     * @return The item with the given name wrapped into
     *         an {@link Optional}{@code <}{@link ItemT}{@code >}
     */
//    @Override
    public Optional<TI> getOItem(String name)
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================

    /***************************************************************************
     * Adds the given item into the container.
     *
     * @param item The item that has to be added into the container
     */
//    @Override
    public void addItem(TI item)
    ;


    /***************************************************************************
     * Cleans the container and saves into it the items
     * located in the given container at the game beginning.
     */
//    @Override
    public void initializeItems()
    ;


    /***************************************************************************
     * Removes the given item from this container.
     *
     * @param item Item to be removed
     */
//    @Override
    public void removeItem(TI item)
    ;



//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
