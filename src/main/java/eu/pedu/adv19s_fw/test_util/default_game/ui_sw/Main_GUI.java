/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_gui.IUIG;
import eu.pedu.adv19s_fw.test_util.default_game.game.version_g.GameG_Icebox;



/*******************************************************************************
 * The class {@code Main_GUI} is the main class of the project
 * that ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class Main_GUI
{
    /***************************************************************************
     * Method starting the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args)
    {
        IUIG   gui  = DefaultGUI.getInstance();
        IGameG game = GameG_Icebox.getInstance();
        gui.startGame(game);
    }


    /** Private constructor preventing instance creation.*/
    private Main_GUI() {}
}
