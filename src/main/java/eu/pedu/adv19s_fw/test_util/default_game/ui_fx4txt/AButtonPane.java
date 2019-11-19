/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_fx4txt;

import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.INamed;

import java.util.ArrayList;
import java.util.Collection;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



/*******************************************************************************
 * Instance třídy {@code AButtonPane} představují panely,
 * v nichž jsou umístěny komponenty zobrazující jednotlivé vložené prvky.
 * Třída současně slouží jako správce používaných panelů tlačítek.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
abstract class  AButtonPane
    extends     VBox
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//
//    //Původně bylo GUI volána přímo, ale v zájmu sjednocení s demo příklady,
//    //v nichž stejné panely mohou vystupovat v několika GUI
//    //bylo zvoleno zadávat GUI prostřednictvím parametru konstruktoru
//    //a uchovávat je proto jako konstantu instance.
//    /** Instance hlavní třídy GUI, která slouží i jako zprostředkovatel. */
//    static volatile DefaultGUIFX GUI = DefaultGUIFX.getInstance();

    /** Kolekce panelů tlačítek, jež je třeba po každém příkazu aktualizovat. */
    private static final Collection<AButtonPane> PANES = new ArrayList<>();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Aktualizuje všechny panely tlačítek.
     */
    public static void actualizeButtons()
    {
        PANES.forEach(p -> p.actualize());
    }



//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** GUI, jehož je tento panel součástí. */
    final IMyGUI4txt gui;

    /** Vnitřní panel, v němž budeme zobrazovat tlačítka představující
     *  příslušné entity – objekty nebo sousedy.
     *  Tento panel je umístěn pod návěštím s názvem dané sady tlačítek. */
    private final VBox buttons = new VBox(5);



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Aktuálně hraná hra. */
    private IGame game;

    /** Název příkazu zadávaného stiskem tlačítka v daném panelu. */
    private String commandName;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Pro zadané GUI vytvoří vytvoří panel tlačítek.
     *
     * @param gui   GUI, pro něž je panel vytvářen
     * @param title Titulek oznamující obsah daného panelu
     */
    AButtonPane(IMyGUI4txt gui, String title)
    {
        this.gui = gui;
        Label label = new Label(title);
        label.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
        getChildren().addAll(label, buttons);

        PANES.add(this);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================

    /***************************************************************************
     * Vrátí název akce realizující požadovaný příkaz.
     *
     * @return Název akce realizující požadovaný příkazů
     */
//    @Override
    abstract String getCommandName();

    /***************************************************************************
     * Inicializuje se pro zadanou (nově spuštěnou) hru.
     *
     * @param game Nově spouštěná hra
     */
//    @Override
    abstract public void initializeForGame(IGame game);


    /***************************************************************************
     * Zjistí nový stav zobrazovaného kontejneru a vrátí kolekci jeho prvků.
     * Prvky mohou být buď h-objekty nebo prostory (sousedé).
     *
     * @return Aktuální kolekce prvků zobrazovaného kontejneru
     */
//    @Override
    abstract Collection<? extends INamed> getObjects();



//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Zareaguje na spuštění nové hry – přihlásí se u ní jako posluchač
     * objektů v batohu.
     *
     * @param game Nově spouštěná hra
     * @param commandName Název příkazu zadávaného
     *                    stiskem tlačítka v daném panelu
     */
    void initializationForGame(IGame game, String commandName)
    {
        this.game        = game;
        this.commandName = commandName;
    }


    /***************************************************************************
     * Hlášeni o výskytu očekávané události.
     *
     * @param informant Objekt, který je schopen poskytnout informace
     *                  o události, kterou zavolání dané metody ohlašuje
     */
    public void actualize()
    {
        ObservableList<Node> buttonList = buttons.getChildren();
        buttonList.clear();
        Collection<? extends INamed> objects = getObjects();
        for (INamed iNamed : objects) {
            String name = iNamed.getName();
            Button button = new Button(name);
            button.idProperty().set(name);
            button.setOnAction(this::onButtonAction);
            buttonList.add(button);
        }
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Definuje reakci na stisk tlačítka.
     *
     * @param event Událost, na kterou se reaguje
     */
    private void onButtonAction(ActionEvent event)
    {
        String commandName = getCommandName();
        Button button      = (Button)(event.getSource());
        String name        = button.idProperty().get();
        String command     = commandName + " " + name;
        gui.getCommandPane().executeCommand(command);
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
