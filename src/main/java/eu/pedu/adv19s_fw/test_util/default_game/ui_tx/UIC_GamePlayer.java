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
import eu.pedu.adv19s_fw.game_txt.IUI;
import eu.pedu.adv19s_fw.test_util.default_game.IAuthorTDemo;

import java.awt.Component;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



/*******************************************************************************
 * The {@code UIC_GamePlayer} instances realize the user's interface,
 * to which the object of {@link IGamePlayer} type can be entered,
 * through which the program will communicate with the user.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class UIC_GamePlayer
  implements IUI, IAuthorTDemo
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
    private final IGamePlayer player;



//== VARIABLE INSTANCE FIELDS ==================================================

    /** Currently played game. */
    private IGame game;



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Creates object realizing the user's interface
     * that utilizes the given object for solving certain details.
     *
     * @param player Object defining solution of certain details
     */
    public UIC_GamePlayer(IGamePlayer player)
    {
        this.player = player;
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Returns the currently played game.
     *
     * @return The currently played game
     */
    @Override
    public IGame getGame()
    {
        return game;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Starts the the default game
     * and runs the communication between this game and the user.
     */
    @Override
    public void startGame()
    {
        startGame(getFactory().getGame());
    }


    /***************************************************************************
     * Starts the the given game
     * and runs the communication between this game and the user.
     *
     * @param game The game, which should be run
     */
    @Override
    public void startGame(IGame game)
    {
        String  command = "";
        String  answer;
        for(;;) {
            answer = game.executeCommand(command);
            answer = decorate(answer, game);
            if (! game.isAlive()) { break; }    //---------->
            command = player.askCommand(answer);
        }
        player.sendMessage(answer);
    }


    /***************************************************************************
     * Return the answer decorated with supplement information
     * describing the current game state etc.
     * This version returns the obtained answer untouched.
     *
     * @param answer Game answer to the last command
     * @param game   The played game
     * @return The possible decorated answer
     */
//    @Override
    public String decorate(String answer, IGame game)
    {
        return answer;
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================

    /***************************************************************************
     * The {@code IGamePlayer} instances define variant parts
     * of the universal text user's interface
     * for playing text conversation games.
     */
    public interface IGamePlayer
    {
        /***********************************************************************
         * Sends to user the entered message
         * and takes over the next command from it.
         *
         * @param message Sent message
         * @return Command entered by the user
         */
        public String askCommand (String message);


        /***********************************************************************
         * It sends the given message to the user.
         *
         * @param message Sent message
         */
        public void sendMessage(String message);
    }



////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
     * The {@code ByJOptionPane} instances mediate communication with the user
     * through static methods of the {@link JOptionPane} class.
     */
    public static class ByJOptionPane implements IGamePlayer
    {
        final Component PARENT;

        /** Creates a new instance. At this occasion it creates the parent
         *  component defining the location of dialogues. */
        public ByJOptionPane() {
            PARENT = new JFrame();
            PARENT.setLocation(100, 100);
            PARENT.setVisible(true);
        }

        /** {@inheritDoc} */
        @Override public String askCommand(String message) {
            return JOptionPane.showInputDialog(PARENT, message);
        }

        /** {@inheritDoc} */
        @Override public void sendMessage(String message) {
            JOptionPane.showMessageDialog(PARENT, message);
        }
    }



////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
     * The {@code ByScanner} instances mediate communication with the user
     * through standard input and output,
     * the input is wrapped into the instance of the {@link Scanner} class.
     */
    public static class ByScanner implements IGamePlayer
    {
        Scanner  scanner = new Scanner(System.in);

        /** {@inheritDoc} */
        @Override public String askCommand(String message) {
            sendMessage(message);
            return scanner.nextLine();
        }

        /** {@inheritDoc} */
        @Override public void sendMessage(String message) {
            System.out.println(message);
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
        IGamePlayer gamePlayer;
        gamePlayer = ((args.length < 1)  ||  (! args[0].equals("-con")))
                   ? new ByJOptionPane()
                   : new ByScanner();
        new UIC_GamePlayer(gamePlayer).startGame();
        System.exit(0);
    }
}
