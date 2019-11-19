/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game;

import eu.pedu.adv19s_fw.game_txt.IGame;





/*******************************************************************************
 * Instances of the {@code IGame} interface are responsible for the game
 * logics. They are able to accept individual commands and provide information
 * on current state of the game and its parts.
 * <p>
 * The game class has to be defined as a singleton and,
 * besides methods declared in the {@link IGame} interface,
 * it has to define the {@code getInstance()} static factory method.
 * Fulfilling of this condition cannot be verified by the compiler, but they
 * can by verified by test utilizing the associated scenario manager.
 *
 * @param <TI> Type of items
 * @param <TP> Type of places
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IMyGame<TI extends IMyItem, TP extends IMyPlace<TI, TP>>
         extends IGame
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the bag to which the player will save the taken items.
     *
     * @return The bag to which the player saves the taken items
     */
    @Override
    public IMyBag<TI> getBag()
    ;


    /***************************************************************************
     * Returns the world in which the game takes place.
     *
     * @return The world in which the game takes place
     */
    @Override
    public IMyWorld<TI, TP> getWorld()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
