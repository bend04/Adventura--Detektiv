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

import eu.pedu.adv19s_fw.game_txt.IGame;

import java.util.Scanner;
import javax.swing.JOptionPane;



/*******************************************************************************
 * The {@code UID_Multiplayer} instances realize the user's interface,
 * that extends the possibilities of the {@link IGamePlayer} interface
 * by asking the user after the game end
 * if he/she wants to play the game once again, and if yes,
 * they allow him/her to choose the preferred interface type.
 * .
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class UID_Multiplayer extends UIC_GamePlayer
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

    /** Object specifying certain details of the conversation. */
    protected final IGameMultiplayer multiplayer;



//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Creates object realizing the user's interface
     * that utilizes the given object for solving certain details.
     *
     * @param multiplayer Object defining solution of certain details
     */
    public UID_Multiplayer(IGameMultiplayer multiplayer)
    {
        super(multiplayer);
        this.multiplayer = multiplayer;
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Starts the default game and after its ending asks the user,
     * if he/she wants to play once again, and if yes, starts again the game.
     * Communicates with the user through the mean set in constructor.
     */
    public void multistartGame()
    {
        startGame(getFactory().getGame());
    }


    /***************************************************************************
     * Starts the given game and after its ending asks the user,
     * if he/she wants to play once again, and if yes, starts again the game.
     * Communicates with the user through the mean set in constructor.
     *
     * @param game The game, which should be run
     */
    public void multistartGame(IGame game)
    {
        do {
            startGame(game);
        } while(multiplayer.wantContinue());
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================

    /***************************************************************************
     * The {@code IGamePlayer} instances define variant parts
     * of the universal text user's interface
     * for playing text conversation games.
     */
    public interface IGameMultiplayer extends IGamePlayer
    {
        /***********************************************************************
         * Asks if the user wants to play once again
         * and returns his/her answer.
         *
         * @return If the user wants to play again, it returns {@code true},
         *         otherwise it returns {@code false}
         */
        public boolean wantContinue();
    }



////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
     * The {@code ByJOptionPane} instances mediate communication with the user
     * through static methods of the {@link JOptionPane} class.
     */
    public static class ByJOptionPane extends UIC_GamePlayer.ByJOptionPane
                                      implements IGameMultiplayer
    {
        /** {@inheritDoc} */
        @Override public boolean wantContinue()
        {
            int answer = JOptionPane.showConfirmDialog(PARENT,
                                     "Chcete hrát znovu?");
            return (answer == 0);
        }
    }



////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
     * The {@code ByScanner} instances mediate communication with the user
     * through standard input and output,
     * the input is wrapped into the instance of the {@link Scanner} class.
     */
    public static class ByScanner extends UIC_GamePlayer.ByScanner
                                  implements IGameMultiplayer
    {
        /** {@inheritDoc} */
        @Override public boolean wantContinue() {
            String answer = askCommand("Chcete hrát znovu? (Y/N)?");
            answer = answer.trim().toUpperCase();
            return (answer.charAt(0) == 'A');
        }
    }



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
        new UID_Multiplayer(gameMultiplayer).multistartGame();
        System.exit(0);
    }
}
