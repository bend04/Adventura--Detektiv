/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.textui;

import cz.vse.java.adventura.DetGSMFactory;
import cz.vse.java.adventura.IAuthorDB;
import cz.vse.java.adventura.game.BenDetektivGame;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IUI;

import javax.swing.*;
import java.awt.*;



/*******************************************************************************
 * Instance třídy {@code UIA_JOptionPane} realizují uživatelské rozhraní,
 * které využívá služeb třídy {@link JOptionPane}.
 *
 * @author  Dominik BENEŠ
 * @version 2019 léto
 */
public class UIA_JOptionPane implements IUI, IAuthorDB
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    /** Tovární třída, jejíž instancemi jsou tovární objekty poskytující
     *  instanci správce scénářů i hry, jejíž scénáře daný správce spravuje. */
    private final static Class<DetGSMFactory> FACTORY_CLASS =
                                                      DetGSMFactory.class;



//== VARIABLE CLASS ATTRIBUTES =================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    /** Aktuálně hraná hra. */
    private IGame game;



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public UIA_JOptionPane()
    {
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí class-objekt tovární třídy, jejíž instance umějí zprostředkovat
     * získání všech klíčových objektů aplikace.
     *
     * @return Class-objekt tovární třídy
     */
    @Override
    public Class<DetGSMFactory> getFactoryClass()
    {
        return FACTORY_CLASS;
    }


    /***************************************************************************
     * Vrátí aktuálně hranou hru.
     *
     * @return Aktuálně hraná hra
     */
    @Override
    public IGame getGame()
    {
        return game;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Spustí komunikaci mezi implicitní hrou
     * a danou instancí uživatelského rozhraní.
     */
    @Override
    public void startGame()
    {
        startGame(BenDetektivGame.getInstance());
    }


    /***************************************************************************
     * Spustí komunikaci mezi zadanou hrou a danou instancí
     * mající na starosti komunikaci s uživatelem.
     *
     * @param game Hra, kterou ma dané UI spustit
     */
    @Override
    public void startGame(IGame game)
    {
        this.game = game;
        Component parent = new JFrame();
        parent.setLocation(100, 100);
        parent.setVisible(true);

        String command;
        String answer  = game.executeCommand("");
        do {
            command = JOptionPane.showInputDialog(parent, answer);
            answer  = game.executeCommand(command);
        } while (game.isAlive());
        JOptionPane.showMessageDialog(parent, answer);
        System.exit(0);
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================



//##############################################################################
//== MAIN METHOD ===============================================================

    /***************************************************************************
     * Metoda spouštějící hru {@link RUPApartmentGame}
     * s uživatelským rozhraním využívajícím služeb
     * třídy {@link JOptionPane}.
     *
     * @param args Parametry příkazového řádku - prozatím nepoužívané
     */
    public static void main(String[] args)
    {
        new UIA_JOptionPane().startGame();
    }
}
