/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.textui;

import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IUI;

import cz.vse.java.adventura.IAuthorDB;
import cz.vse.java.adventura.DetGSMFactory;
import cz.vse.java.adventura.game.BenDetektivGame;

import java.awt.Component;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



/*******************************************************************************
 * Instance třídy {@code UIC_GamePlayer} realizují uživatelské rozhraní,
 * kterému lze zadat objekt typu {@link IGamePlayer}, jehož prostřednictvím
 * bude program komunikovat s uživatelem.
 *
 * @author  Dominik Beneš
 * @version 2019 léto
 */
public class UIC_GamePlayer implements IUI, IAuthorDB
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

    /** Objekt specifikující některé detaily konverzace. */
    private final IGamePlayer player;



//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    /** Aktuálně hraná hra. */
    private IGame game;



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří instanci využívající pro řešení některých detailů zadaný objekt.
     *
     * @param player Objekt definující řešení některých detailů
     */
    public UIC_GamePlayer(IGamePlayer player)
    {
        this.player = player;
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
        String  command = "";
        String  answer;
        for(;;) {
            answer  = game.executeCommand(command);
            if (! game.isAlive()) { break; }    //---------->
            command =  player.askCommand(answer);
        }
        player.sendMessage(answer);
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================

    /***************************************************************************
     * Instance interfejsu {@code IGamePlayer} definují variantní části
     * univerzálního textového uživatelského rozhraní
     * pro hraní textových konverzačních her.
     */
    public interface IGamePlayer
    {
        /***********************************************************************
         * Pošle uživateli zadanou zprávu a převezme od něj další příkaz.
         *
         * @param message Posílaná zpráva
         * @return Uživatelem zadaný příkaz
         */
        public String askCommand (String message);


        /***********************************************************************
         * Pošle uživateli zadanou zprávu.
         *
         * @param message Posílaná zpráva
         */
        public void sendMessage(String message);
    }



////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
     * Instance třídy {@code ByJOptionPane} zprostředkovávají komunikaci
     * s uživatelem prostřednictvím statických metod třídy {@link JOptionPane}.
     */
    public static class ByJOptionPane implements IGamePlayer
    {
        final Component PARENT;

        /** Vytvoří novou instanci. Při té příležitosti vytvoří rodičovskou
         *  komponentu definující umístění dialogových oken. */
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
     * Instance třídy {@code ByScanner} zprostředkovávají komunikaci
     * s uživatelem prostřednictvím standardního (konzolového) vstupu a výstupu,
     * přičemž vstup je zabalen do instance třídy {@link Scanner}.
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
     * Metoda spouštějící hru {@link RUPApartmentGame} umožňující zadat
     * prostřednictvím parametrů příkazového řádku,
     * zda bude použito uživatelském rozhraním využívající služeb
     * třídy {@link JOptionPane} nebo standardního výstupu a
     * standardního vstupu zabaleného do instance třídy {@link Scanner}.
     *
     * @param args Parametry příkazového řádku
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
