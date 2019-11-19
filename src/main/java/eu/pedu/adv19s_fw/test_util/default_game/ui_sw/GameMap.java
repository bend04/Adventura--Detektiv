/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IGameG;

import java.awt.Point;
import java.awt.Rectangle;

//import javax.swing.Icon;
//import javax.swing.ImageIcon;
import eu.pedu.adv19s_fw.game_gui.Icon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import eu.pedu.adv19s_fw.game_gui.IPlaceG;



/*******************************************************************************
 * Instance třídy {@code GameMap} přestavují aplikační okno
 * zobrazující plánek hry a na něm kurzor představující hráče.
 * Z pozice kurzoru je možno odvodit pozici hráče.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class GameMap
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Název v titulkové liště okna plánu hry. */
    private static final String MAP_TITLE = "Plánek hry";

    /** Instance jedináčka. */
    private static final GameMap SINGLETON = new GameMap();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Okno zobrazující plánek hry. */
    private final JFrame mapFrame;

    /** Návěští s obrázkem plánku hry. */
    private final JLabel mapLabel;

    /** Návěští s obrázkem kurzoru představujícího hráče. */
    private final JLabel playerLabel;

    /** Obrázek kurzoru představujícího hráče. */
    private final Icon playerIcon;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Obrázek s plánkem hry. */
    private Icon mapIcon;

    /** Aktuálně ovládaná hra. */
    private IGameG controlledGame;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vrátí instanci jedináčka.
     *
     * @return Instance jedináčka
     */
    public static GameMap getInstance()
    {
        return SINGLETON;
    }


    /***************************************************************************
     * Vytvoří aplikační okno s plánkem provozované hry a kurzorem hráče,
     * který se po tomto plánku pohybuje.
     */
    private GameMap()
    {
        mapFrame = new JFrame(MAP_TITLE);
        mapFrame.setLayout(null);
        mapLabel = new JLabel();

        playerIcon  = DefaultGUI.getInstance().getGame().getPlayer();
        playerLabel = new JLabel(playerIcon);
        playerLabel.setBounds(0, 0,
                    playerIcon.getIconWidth(), playerIcon.getIconHeight());

        //Později přidaná návěští se umísťují pod návěští přidaná dříve
        mapFrame.add(playerLabel);
        mapFrame.add(mapLabel);
    }


//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Nastaví viditelnost okna s plánkem hry.
     *
     * @param visible Má-li být plánek viditelný ({@code true} + má být)
     */
    public void setVisible(boolean visible)
    {
        mapFrame.setVisible(visible);
    }


//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Hlášení o změně aktuální místnosti
     *
     * @param currentRoom Nová aktuální místnost
     */
    public void update(IPlaceG currentRoom)
    {
        placePlayerIcon();
    }


    /***************************************************************************
     * Inicializuje komunikaci se zadanou hrou.
     *
     * @param game  Hra, s níž bude GUI komunikovat
     */
    void initialize(IGameG game)
    {
        DefaultGUI gui = DefaultGUI.getInstance();
        this.controlledGame = game;

        //Nastaví obrázek s plánkem
        mapIcon = game.getMap();
        mapLabel.setIcon(mapIcon);

        int w = mapIcon.getIconWidth();
        int h = mapIcon.getIconHeight();
        mapLabel.setBounds(4, 4, w, h);
        mapFrame.setSize(w + 16, h + 36);

        //Nastavení pozice plánku pod aplikačním oknem
        Rectangle rctGUI = gui.getArea();
        mapFrame.setLocation(rctGUI.x,  rctGUI.y + rctGUI.height);

        placePlayerIcon();
        boolean zobrazit = gui.isGameMapVisible();
        setVisible(zobrazit);
    }


    /***************************************************************************
     *
     */
    final void placePlayerIcon()
    {
        Point playerPosition = controlledGame.getWorld()
                                             .getCurrentPlace().getPosition();
        playerLabel.setLocation(playerPosition);
        mapFrame.setVisible(true);
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
