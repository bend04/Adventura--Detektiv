/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game;

import eu.pedu.adv19s_fw.game_txt.IWorld;

import java.util.Collection;
import java.util.Optional;



/*******************************************************************************
 * An instance of the {@code IWorld} interface represents the game world.
 * It should be defined as a singleton.
 * It is responsible for arrangement of individual places and keeps information,
 * in which place the player is just situated.
 * The mutual arrangement may change during the game,
 * the places can gain and/or lose their neighbors.
 *
 * @param <TI> Type of items
 * @param <TP> Type of places
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IMyWorld<TI extends IMyItem, TP extends IMyPlace<TI, TP>>
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
    public Collection<TP> getAllPlaces()
    ;


    /***************************************************************************
     * Returns the current place,
     * i.e. to the place in which the player is just situated.
     *
     * @return The place in which the player is just situated
     */
    @Override
    public TP getCurrentPlace();


    /***************************************************************************
     * Sets the given place as the current one,
     * i.e. the place, in which the player is just situated.
     *
     * @param destinationRoom The set place
     */
//    @Override
    public void setCurrentPlace(TP destinationRoom)
    ;


    /***************************************************************************
     * Returns the place (room) with the given name
     * wrapped in the {@link Optional}.
     *
     * @param name Name of the required place
     * @return The wrapped place with the given name
     */
//    @Override
    public Optional<TP> getORoom(String name)
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================

    /***************************************************************************
     * The method initializing the game world.
     * Firstly it initializes all places
     * and then it sets the initial current place.
     */
    public void initialize()
    ;



//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
