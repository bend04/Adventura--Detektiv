/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_gui;

import eu.pedu.adv19s_fw.game_txt.IWorld;

import java.util.Collection;



/*******************************************************************************
 * An instance of the {@code IWorld} interface represents the game world.
 * It should be defined as a singleton.
 * It is responsible for arrangement of individual places and keeps information,
 * in which place the player is just situated.
 * The mutual arrangement may change during the game,
 * the places can gain and/or lose their neighbors.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IWorldG
         extends IWorld
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the collection of all places of the game.
     *
     * @return Collection of all places performing in the game
     */
    @Override
    public Collection<? extends IPlaceG> getAllPlaces()
    ;


    /***************************************************************************
     * Returns the current place,
     * i.e. to the place in which the player is just situated.
     *
     * @return The place in which the player is just situated
     */
    @Override
    public IPlaceG getCurrentPlace();



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
