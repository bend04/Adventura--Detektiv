/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_fx4txt;

import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IUI;
import eu.pedu.adv19s_fw.test_util.default_game.GSMFactoryG_Apartment;
import eu.pedu.adv19s_fw.test_util.default_game.IAuthorDemo;

import javafx.stage.Stage;
import eu.pedu.adv19s_fw.game_txt.IGUI4txt;



/*******************************************************************************
//%L+ CZ
 * Instance interfejsu {@code IMyGUI4txt} představují ...
//%Lx EN
 * The {@code IMyGUI4txt} interface instances represent ...
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
interface   IMyGUI4txt
    extends IGUI4txt, IAuthorDemo
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí panel s polem pro zadávání textových příkazů.
     *
     * @return Panel s polem pro zadávání textových příkazů
     */
    public CommandPane getCommandPane()
    ;


    /***************************************************************************
     * Vrátí aktuálně hranou hru.
     *
     * @return Aktuálně hraná hra
     */
    @Override
    public IGame getGame()
    ;


    /***************************************************************************
     * Vrátí panel s výstupní textovou oblastí, do níž se zapisují
     * zadávané příkazy spolu s na ně reagujícími odpověďmi hry.
     *
     * @return Panel s výstupní textovou oblastí
     */
    public OutputPane getOutputPane()
    ;


    /***************************************************************************
     * Vrátí hlavní aplikační okno daného GUI.
     *
     * @return Hlavní aplikační okno daného GUI
     */
    public Stage getPrimaryStage()
    ;



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
//    {
//        System.out.println(command + "\n-----------------------------------\n"
//                         + getGame().executeCommand(command)
//                         + "\n===========================================\n\n");
//    }



//\DG== DEFAULT GETTERS AND SETTERS ============================================

    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which also the mother class of this instance is.
     *
     * @return The class-object of the factory class
     */
    @Override
    default
    public Class<GSMFactoryG_Apartment> getFactoryClass()
    {
        return GSMFactoryG_Apartment.class;
    }



//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
