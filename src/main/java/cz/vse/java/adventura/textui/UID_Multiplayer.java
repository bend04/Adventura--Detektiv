 /* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.textui;

import java.util.Scanner;
import javax.swing.JOptionPane;



/*******************************************************************************
 * Instance třídy {@code UID_Multiplayer} realizují uživatelské rozhraní,
 * které rozšiřuje možnosti rozhraní typu {@link IGamePlayer} o možnost
 * zadat po skončení hry, zda si hráč chce zahrát ještě jednou,
 * a pokud ano, tak si také vybrat typ rozhraní, kterému bude dávat přednost.
 * .
 *
 * @author  Dominik Beneš
 * @version 2019 léto
 */
public class UID_Multiplayer extends UIC_GamePlayer
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    /** Objekt specifikující některé detaily konverzace. */
    private final IGameMultiplayer multiplayer;



//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří instanci využívající pro řešení některých detailů zadaný objekt.
     *
     * @param multiplayer Objekt definující řešení některých detailů
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
     * Komunikuje s uživatelem prostřednictvím zadaného prostředku.
     * Vždy spustí hru a po jejím ukončení se uživatele zeptá,
     * chce-li si zahrát ještě jednou, a proku ano, znovu spustí hru.
     */
    public void multistartGame()
    {
        do {
            startGame();
        } while(multiplayer.wantContinue());
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================

    /***************************************************************************
     * Instance interfejsu {@code IGameMultiplayer} definují variantní části
     * univerzálního textového uživatelského rozhraní
     * pro hraní textových konverzačních her.
     */
    public interface IGameMultiplayer extends IGamePlayer
    {
        /***********************************************************************
         * Zjistí, chce-li si uživatel zahrát ještě jednou.
         *
         * @return Chce-li si uživatel znovu zahrát, vrátí {@code true},
         *         jinak vrátí {@code false}
         */
        public boolean wantContinue();
    }



////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
     * Instance třídy {@code ByJOptionPane} zprostředkovávají komunikaci
     * s uživatelem prostřednictvím statických metod třídy {@link JOptionPane}.
     */
    public static class ByJOptionPane extends UIC_GamePlayer.ByJOptionPane
                                      implements IGameMultiplayer
    {
        /** {@inheritDoc} */
        @Override public boolean wantContinue()
        {
            int answer = JOptionPane.showConfirmDialog(PARENT,
                                     "Chcete si zahrát ještě jednou?");
            return (answer == 0);
        }
    }



////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /***************************************************************************
     * Instance třídy {@code ByJOptionPane} zprostředkovávají komunikaci
     * s uživatelem prostřednictvím statických metod třídy {@link JOptionPane}.
     */
    public static class ByScanner extends UIC_GamePlayer.ByScanner
                                  implements IGameMultiplayer
    {
        /** {@inheritDoc} */
        @Override public boolean wantContinue() {
            String answer = askCommand("Chcete si zahrát ještě jednou (A/N)?");
            answer = answer.trim().toUpperCase();
            return (answer.charAt(0) == 'A');
        }
    }



//##############################################################################
//== MAIN METHOD ===============================================================

    /***************************************************************************
     * //TODO přeměnit na příštím řádku @code na @link
     * Metoda spouštějící hru {@code OfficialApartmentGame} umožňující zadat
     * prostřednictvím parametrů příkazového řádku,
     * zda bude použito uživatelském rozhraním využívající služeb
     * třídy {@link JOptionPane} nebo standardního výstupu a
     * standardního vstupu zabaleného do instance třídy {@link Scanner}.
     *
     * @param args Parametry příkazového řádku
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
