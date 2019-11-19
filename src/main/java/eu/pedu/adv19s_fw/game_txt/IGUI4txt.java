/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_txt;

import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IUI;
import eu.pedu.adv19s_fw.test_util.default_game.GSMFactoryG_Apartment;
import eu.pedu.adv19s_fw.test_util.default_game.IAuthorDemo;

import javafx.stage.Stage;



/*******************************************************************************
 * Instance interfejsu {@code IMyGUI4TXT} představují grafická uživatelská
 * rozhraní schopná spuštění textové verze hry (tj. nepodporující obrázky).
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IGUI4txt
         extends IUI
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================
//\AM== REMAINING ABSTRACT METHODS =============================================

    /***************************************************************************
     * Zpracuje příkaz předaný v parametru jako by byl zadán standardním
     * postupem z klávesnice.
     * Metodu přebijte v případě, kdy vybavíte textovou hru grafickým rozhraním
     * a budete potřebovat otestovat reakce tohoto GUI
     * na příkazy zadávané z klávesnice.
     *
     * @param command Zadávaný příkaz
     */
//    default
    public void executeCommand(String command)
;



//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
