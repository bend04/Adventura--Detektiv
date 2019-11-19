/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_gui.IUIG;
import eu.pedu.adv19s_fw.test_util.default_game.GSMFactoryG_Apartment;
import eu.pedu.adv19s_fw.test_util.default_game.IAuthorDemo;

import java.awt.Component;

import java.awt.Rectangle;



/*******************************************************************************
 * Instance rozhraní {@code IMyGUI} představují ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IMyGUI
         extends IUIG, IAuthorDemo
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================
//
//    /***************************************************************************
//     * Vrátí odkaz na aktuálně ovládanou hru.
//     *
//     * @return Odkaz na aktuálně ovládanou hru
//     */
////    @Override
//    public IGameG getGame();
//

    /***************************************************************************
     * Vrátí aktuální stav zobrazování plánku hry.
     *
     * @return Aktuální stav zobrazování plánku hry
     */
//    @Override
    public boolean isGameMapVisible();


    /***************************************************************************
     * Nastaví, zda se bude během hry zobrazovat plánek hry.
     *
     * @param zobrazovat {@code true} = bude se zobrazovat plánek hry
     */
//    @Override
    public void setGameMapVisible(boolean zobrazovat);


    /***************************************************************************
     * Vrátí aktuální rozměr okna hry.
     *
     * @return Aktuální rozměr okna hry
     */
//    @Override
    public Rectangle getArea();


    /***************************************************************************
     * Vrátí komponentu, která může sloužit jako rodič
     * otevíraných dialogových oken.
     *
     * @return Rodičovská komponenta
     */
//    @Override
    public Component getParents();



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
