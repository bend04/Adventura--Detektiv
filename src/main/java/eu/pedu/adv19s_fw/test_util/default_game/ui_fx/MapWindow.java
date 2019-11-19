/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_fx;

import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_gui.IPlaceG;
import eu.pedu.adv19s_fw.game_gui.IUIG;
import eu.pedu.adv19s_fw.game_gui.Icon;

import java.awt.Point;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



/*******************************************************************************
 * Instance třídy {@code MapWindow} představují ...
 * Instances of class {@code MapWindow} represent ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class    MapWindow
    extends     Stage
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

    /** Instance hlavní třídy GUI, která slouží i jako zprostředkovatel. */
    private final IMyGUI gui;

    private final Pane pane = new Pane();



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Aktuálně hraná hra. */
    private IGameG game;

    /** Obrázek plánku hry. */
    private Icon map;

    /** Obrázek hráče pohybující se po plánku. */
    private Icon player;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří prázdné okno, v němž se bude po spuštění hry zobrazovat
     * plánek dané hry spolu s hráčem umístěným v aktuálním prostoru.
     */
    MapWindow(IMyGUI gui)
    {
        super();    //StageStyle.UNDECORATED);
        this.gui = gui;
        Scene scene = new Scene(pane);
        setScene(scene);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Inicializuje se pro zadanou (nově spuštěnou) hru —
     * načte obrázky jejího plánku a hráce a vloží je do přeipraveného okna.
     *
     * @param game Nově spouštěná hra
     */
//    @Override
    public void initializeForGame(IGameG game)
    {
        this.game    = game;
        this.map     = game.getMap();
        this.player  = game.getPlayer();
        movePlayer();

        ObservableList<Node> children = pane.getChildren();
        children.clear();
        children.addAll(map, player);

        show();
    }


    /***************************************************************************
     * Zobrazí na plánku hráče na pozici odpovídající zadanému prostoru.
     */
    public void movePlayer()
    {
        IPlaceG place    = game.getWorld().getCurrentPlace();
        Point   position = place.getPosition();
        player.setX(position.x);
        player.setY(position.y);
    }


    /***************************************************************************
     * Přilepit okno s plánkem zprava k zadanému oknu.
     *
     * @param primaryStage Okno, k němuž se budeme lepit
     */
    void stayOn(Stage primaryStage)
    {
        positionListener(null, null, null);
        primaryStage.     xProperty().addListener(this::positionListener);
        primaryStage.     yProperty().addListener(this::positionListener);
        primaryStage. widthProperty().addListener(this::positionListener);
//        primaryStage.heightProperty().addListener(this::positionListener);
    }


    /***************************************************************************
     * Podle hodnoty parametru nastaví nebo potlačí zobrazování okna.
     *
     * @param show Je-li {@code true}, bude zobrazeno, jinak bude schováno
     */
    void show(boolean show)
    {
        if (show) { show(); }
             else { hide(); }
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Přizpůsobuje svoji pozici velikosti a pozici hlavního okna.
     * Parametry nejsou používané.
     */
    private void positionListener(ObservableValue<? extends Number> observable,
                                  Number oldValue, Number newValue)
    {
        Stage primaryStage = gui.getPrimaryStage();
        setX(primaryStage.getX() + primaryStage.getWidth());
        setY(primaryStage.getY());
    }




//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
