/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IGameG;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;



/*******************************************************************************
 * Instance třídy {@code MenuGame} představují nabídku umožňující nastavovat
 * různé možnosti hry, mezi nimi:
 * <ul>
 *   <li>Zobrazení plánku hry</li>
 *   <li>Spuštění zadané hry</li>
 *   <li>Předčasné ukončení hry</li>
 *   <li>Ukončení celé aplikace</li>
 * </ul>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
@SuppressWarnings("serial")
class MenuGame extends JMenu
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** GUI, pro které nabídka pracuje. */
    private final IMyGUI gui;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří novou hlavní nabídku pro zadané GUI.
     *
     * @param gui GUI, pro něž se nabídka vytváří
     */
    MenuGame(IMyGUI gui)
    {
        super("Hra");
        setMnemonic('H');
        this.gui = gui;

        showMapCommand();
        playAgainCommand();
        choseNewGameCommand();
        endoOfGameCommand();
        endOfApplicationCommand();
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Nastaví, zda se bude či nebude zobrazovat plánek hry
     * s pohybujícím se kurzorem představujícím hráče, který zobrazí místnost,
     * ve které místnosti se hráč právě nachází.
     */
    private void showMapCommand()
    {
        final JCheckBoxMenuItem showMapItem=  new JCheckBoxMenuItem(
                                               "Zobrazovat plánek hry", true);
        showMapItem.setMnemonic('P');
//        gui.setGameMapVisible(gui.isGameMapVisible());
        this.add(showMapItem);

        showMapItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                gui.setGameMapVisible(showMapItem.getState());
            }
        });
    }


    /***************************************************************************
     * Spustí aktuální hru znovu od začátku.
     */
    private void playAgainCommand()
    {
        JMenuItem playAgainItem = new JMenuItem("Hrát znovu stejnou hru");
        playAgainItem.setMnemonic('Z');
        this.add(playAgainItem);

        playAgainItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                IGameG game = stopGame();
                gui.startGame(game);
            }
        });
    }


    /***************************************************************************
     * Nabídne seznam her, z nichž si může hráč vybrat, která se spustí.
     */
    private void choseNewGameCommand()
    {
        JMenuItem enterNewGameItem = new JMenuItem("Zadat další hru");
        enterNewGameItem.setMnemonic('d');
        this.add(enterNewGameItem);
        enterNewGameItem.setEnabled(false);
    }


    /***************************************************************************
     * Ukončí právě hranou hru.
     */
    private void endoOfGameCommand()
    {
        JMenuItem stopGameItem = new JMenuItem("Ukončit hru");
        stopGameItem.setMnemonic('U');
        this.add(stopGameItem);

        stopGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                IGameG hra = stopGame();
            }
        });
    }


    /***************************************************************************
     * Ukončí celou aplikaci.
     */
    private void endOfApplicationCommand()
    {
        JMenuItem endOfApplicationItem = new JMenuItem("Konec aplikace");
        endOfApplicationItem.setMnemonic('K');
        this.add(endOfApplicationItem);

        endOfApplicationItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                stopGame();
                System.exit(0);
            }
        });
    }


    /***************************************************************************
     * Zjistí, která hra je právě ovládána, a pokud ještě není ukončena,
     * tak ji ukončí.
     *
     * @return Odkaz na právě ukončenou hru
     */
    private IGameG stopGame()
    {
        IGameG game = gui.getGame();
        if ((game != null) && (game.isAlive())) {
            game.stop();
        }
        return game;
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
