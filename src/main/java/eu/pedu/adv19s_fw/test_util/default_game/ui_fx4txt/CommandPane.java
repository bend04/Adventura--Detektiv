/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_fx4txt;

import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.test_util.default_game.ui_tx.UI_Util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;



/*******************************************************************************
 * Instance třídy {@code CommandPane} představují panely pro zadávání příkazů
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class CommandPane
    extends HBox
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

    /** GUI, jehož je tento panel součástí. */
    private final IMyGUI4txt gui;

    /** Návěští s výzvou k zadání příkazu. */
    private final Label prompt;

    /** Vstupní textové pole, kam uživatel zadává příkazy hře. */
    private final TextField  inputField;

    /** Ovladač reagující na zadání příkazu ve vstupním textovém poli. */
    private final EventHandler<ActionEvent> inputHandler;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Příznak příkazu startujícího hru, při němž ještě není celé GUI
     *  inicializováno a není proto možné ostatní součásti úkolovat. */
    private boolean startingCommand;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Pro zadané GUI vytvoří vytvoří panel pro zadávání příkazů.
     *
     * @param gui GUI, pro něž je panel vytvářen
     */
    CommandPane(IMyGUI4txt gui)
    {
        this.gui     = gui;
        prompt       = new Label("Zadávaný příkaz: ");
        inputField   = new TextField();
        inputHandler = this::onAction;

        inputField.prefWidthProperty().set(300);
        getChildren().addAll(prompt, inputField);

        inputField.setOnAction(inputHandler);
        this.alignmentProperty().set(Pos.CENTER);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Inicializuje se pro zadanou (nově spuštěnou) hru —
     * odstartuje zadanou hru.
     *
     * @param game Nově spouštěná hra
     */
//    @Override
    public void initializeForGame(IGame game)
    {
        this.startingCommand = true;
        executeCommand("");
    }


    /***************************************************************************
     * Zpracuje příkaz předaný v parametru jako by byl zadán standardním
     * postupem z klávesnice. Metoda umožňuje testovat reakce daného
     * uživatelského rozhraní na příkazy zadávané z klávesnice.
     *
     * @param command Zadávaný příkaz
     */
    void executeCommand(String command)
    {
        inputField  .setText(command);
        inputHandler.handle(new ActionEvent());
    }

//
//    /***************************************************************************
//     * Requests that this {@code Node} get the input focus, and that this
//     * {@code Node}'s top-level ancestor become the focused window. To be
//     * eligible to receive the focus, the node must be part of a scene, it and
//     * all of its ancestors must be visible, and it must not be disabled.
//     * If this node is eligible, this function will cause it to become this
//     * {@code Scene}'s "focus owner". Each scene has at most one focus owner
//     * node. The focus owner will not actually have the input focus, however,
//     * unless the scene belongs to a {@code Stage} that is both visible
//     * and active.
//     */
//    @Override
//    public void requestFocus()
//    {
//        inputField.requestFocus();
//    }
//


//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Ovladač události vyvolané po zadání příkazu, přesněji
     * vyvolané stiskem klávesy Enter v době, když má vstupní pole fokus.
     * Předá zadaný příkaz hře a vytiskne ve výstupní oblasti její odpověď.
     *
     * @param event Událost, na níž reagujeme
     */
    private void onAction(ActionEvent event)
    {
        String command, gameAnswer, resultAnswer;
        IGame  game  = gui.getGame();
        command      = inputField.getText();    //Převezme zadaný příkaz
        gameAnswer   = game.executeCommand(command);
        resultAnswer = command
                     + "\n-------------------------------------------------\n"
                     + gameAnswer
                     + UI_Util.gameState(game)
                     + "=================================================\n\n";
        gui.getOutputPane().appendText(resultAnswer);
        AButtonPane.actualizeButtons();

        inputField.setText("");
        inputField.requestFocus();

        if (startingCommand) {
            startingCommand = false;
        }
        if (! gui.getGame().isAlive()) {
            inputField.setOnAction(null);
        }
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
