/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_tx;

/**=============================================================================
Connection to framework:  Adv_19s_FW
Pseudo-root package: eu.pedu.adv19s_fw

Project B52e17z_EnhancedUI
  * Class created
==============================================================================*/



////////////////////////////////////////////////////////////////////////////////
//%P-  +++++ End of ignored starting text - place for imports +++++

import eu.pedu.adv19s_fw.game_txt.IBag;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IItem;
import eu.pedu.adv19s_fw.game_txt.IWorld;

//import eu.pedu.adv19s_fw.game_txt.IBag;
//import eu.pedu.adv19s_fw.game_txt.IGame;
//import eu.pedu.adv19s_fw.game_txt.IItem;
//import eu.pedu.adv19s_fw.game_txt.IPlace;
//import eu.pedu.adv19s_fw.game_txt.IWorld;

import java.util.Collection;
import java.util.Scanner;
import javax.swing.JOptionPane;
import eu.pedu.adv19s_fw.game_txt.IPlace;



/*******************************************************************************
 * The {@code UIE_Informing} instances realize the user's interface,
 * that enhances the abilities of instances of {@link UID_Multiplayer}
 * by informing the user about the current game state.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class UIE_Informing extends UID_Multiplayer
{
//== CONSTANT CLASS FIELDS =====================================================
//== VARIABLE CLASS FIELDS =====================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE FIELDS ==================================================
//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Creates object realizing the user's interface
     * that utilizes the given object for solving certain details.
     *
     * @param multiplayer Object defining solution of certain details
     */
    public UIE_Informing(IGameMultiplayer multiplayer)
    {
        super(multiplayer);
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Return the answer decorated with supplement information
     * describing the current game state.
     *
     * @param answer Game answer to the last command
     * @param game   The played game
     * @return The decorated answer
     */
    @Override
    public String decorate(String answer, IGame game)
    {
        IBag bag = game.getBag();
        Collection<? extends IItem> bagItems = bag.getItems();

        IWorld world = game.getWorld();
        IPlace place = world.getCurrentPlace();
        Collection<? extends IPlace> neighbors  = place.getNeighbors();
        Collection<? extends IItem>  placeItems = place.getItems();

        String result = answer
                      + "\nCurrent place: " + place
                      + "\nIts neighbors: " + neighbors
                      + "\nIts items: "     + placeItems
                      + "\nBag content: "   + bagItems
                      + "\n";
        return result;
    }




//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================


//##############################################################################
//== NESTED DATA TYPES =========================================================



//##############################################################################
//== MAIN METHOD ===============================================================

    /***************************************************************************
     * Method starting the default game and enabling to enter
     * if it  will be used
     * the {@link JOptionPane} utilizing communication
     * or the {@link Scanner} class and standard input and output.
     *
     * @param args Parameters of the command line
     */
    public static void main(String[] args)
    {
        IGameMultiplayer gameMultiplayer;
        gameMultiplayer = ((args.length < 1)  ||  (! args[0].equals("-con")))
                        ? new ByJOptionPane()
                        : new ByScanner();
        new UIE_Informing(gameMultiplayer).multistartGame();
        System.exit(0);
    }

}
/**=============================================================================
Connection to framework:  Adv_19s_FW
Pseudo-root package: eu.pedu.adv19s_fw
Project B52e17z_EnhancedUI
 * Class created
==============================================================================*/
