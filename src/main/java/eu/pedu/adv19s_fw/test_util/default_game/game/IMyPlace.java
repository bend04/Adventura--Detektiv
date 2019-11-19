/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game;

import eu.pedu.adv19s_fw.game_txt.INamed;
import eu.pedu.adv19s_fw.game_txt.IPlace;

import java.util.Collection;



/*******************************************************************************
 * Instances of the {@code IPlace} interface represent the places in the game.
 *
 * We can take the place visiting as a partial goal,
 * which the player tries to reach.
 * The places can be rooms, planets, live stages etc.
 * The places can contain various items.that may help user to reach the goal.
 * Each place knows its current neighboring places and it knows
 * which items it currently contains.
 * The neighbors as well as the contained items can change during the game.
 *
 * @param <TI> Type of items
 * @param <TP> Type of places
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IMyPlace<TI extends IMyItem, TP extends IMyPlace<TI, TP>>
         extends INamed, IPlace, IMyItemContainer<TI>
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the collection of current neighbors of this place, i.e. the
     * collection of places, to which we can move from this place with the
     * command of the {@link eu.pedu.adv19s_fw.scenario.TypeOfStep#tsMOVE
     * TypeOfStep.tsMOVE} type.
     *
     * @return Collection of neighbors
     */
    @Override
    public Collection<TP> getNeighbors()
    ;


    /***************************************************************************
     * Returns a collection of items located in the given place.
     *
     * @return Collection of items located in the given place
     */
    @Override
    public Collection<TI> getItems()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================

    /***************************************************************************
     * Method initializing the given place.
     * Based on the names remembered by the constructor
     * it initializes the neighbors of the given place and its items.
     */
//    @Override
    public void initialize()
    ;



//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
