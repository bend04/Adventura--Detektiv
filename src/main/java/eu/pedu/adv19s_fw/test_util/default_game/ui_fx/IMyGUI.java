/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_fx;

import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_gui.IUIG;
import eu.pedu.adv19s_fw.test_util.default_game.GSMFactoryG_Apartment;
import eu.pedu.adv19s_fw.test_util.default_game.IAuthorDemo;
import javafx.stage.Stage;



/*******************************************************************************
//%L+ CZ
 * Instance interfejsu {@code IMyGUI} představují ...
//%Lx EN
 * The {@code IMyGUI} interface instances represent ...
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
interface   IMyGUI
    extends IUIG, IAuthorDemo
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
    public IGameG getGame()
    ;


    /***************************************************************************
     * Vrátí okno zobrazující plánek hry a aktuální pozici hráče.
     *
     * @return Okno zobrazující plánek hry a aktuální pozici hráče
     */
    public MapWindow getMapWindow()
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
//\DG== DEFAULT GETTERS AND SETTERS ============================================

    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which also the mother class of this instance is.
     *
     * @return The class-object of the factory class
     */
    @Override
    default public Class<GSMFactoryG_Apartment> getFactoryClass()
    {
        return GSMFactoryG_Apartment.class;
    }



//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
