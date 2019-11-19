 /* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_fx4txt;

import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_txt.IGame;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;



/*******************************************************************************
 * Instance třídy {@code DefaultGUI4txt} představují hlavní okno
 * implicitní demonstrační aplikace;
 * tato třída představuje verzi po vyřešení domácího úkolu,
 * která již obsahuje nabídku a definuje třídu jako jedináčka.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class DefaultGUI4txt
     extends Application
  implements IMyGUI4txt
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================

    private static volatile DefaultGUI4txt GUI;



//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    //Následující konstanty nejsou inicializovány v deklaraci,
    //aby bylo zaručeno, že při zavádění jejich třídy je již inicializována
    //proměnná GUI.

    /** Hlavní nabídka. */
    private final MenuPane menuPane;

    /** Textová oblast, kam se vypisují zadané příkazy spolu s odpověďmi hry. */
    private final OutputPane outputPane;

    /** Panel se vstupní textovým polem a případnými dalšími prvky
     *  pro zadávání příkazů hře. */
    private final CommandPane commandPane;

    /** Panel s tlačítky reprezentujícími objekty v batohu. */
    private final BagPane bagPane;

    /** Panel s tlačítky reprezentujícími objekty v aktuálním prostoru. */
    private final ItemsPane itemsPane;

    /** Panel s tlačítky reprezentujícími sousedy aktuálního prostoru. */
    private final NeigborsPane neigborsPane;



//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    /** Aplikační okno. */
    private Stage primaryStage;

    /** AQktuálně provozovaná hra. */
    private IGame game;



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public DefaultGUI4txt()
    {
        assert (GUI == null)
             :  "Aplikaci je možno vytvořit pouze jedinou!";
        GUI              = this;
        //Inicializace následujících konstant musela počkat na inicializaci GUI
        //Jednotlivé následující inicializace musí být uvedeny v pořadí,
        //které respektuje vzájemné zváslosti těchto objektů (panelů)
        outputPane       = new OutputPane  (this);//      .getInstance();
        commandPane      = new CommandPane (this);
        bagPane          = new BagPane     (this);
        itemsPane        = new ItemsPane   (this);
        neigborsPane     = new NeigborsPane(this);
        menuPane         = new MenuPane    (this);
    }


    /***************************************************************************
     * Vrátí instanci (jedináčka) dané hry.
     *
     * @return Jediná existující instance dané hry
     */
    public static DefaultGUI4txt getInstance()
    {
        return GUI;
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí panel s polem pro zadávání textových příkazů.
     *
     * @return Panel s polem pro zadávání textových příkazů
     */
    @Override
    public CommandPane getCommandPane()
    {
        return commandPane;
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


    /***************************************************************************
     * Vrátí panel s výstupní textovou oblastí, do níž se zapisují
     * zadávané příkazy spolu s na ně reagujícími odpověďmi hry.
     *
     * @return Panel s výstupní textovou oblastí
     */
    @Override
    public OutputPane getOutputPane()
    {
        return outputPane;
    }


    /***************************************************************************
     * Vrátí objekt aplikačního okna.
     *
     * @return Aplikační okno
     */
    @Override
    public Stage getPrimaryStage()
    {
        return primaryStage;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Zpracuje příkaz předaný v parametru jako by byl zadán standardním
     * postupem z klávesnice. Metoda umožňuje testovat reakce daného
     * uživatelského rozhraní na příkazy zadávané z klávesnice.
     *
     * @param command Zadávaný příkaz
     */
    @Override
    public void executeCommand(String command)
    {
        commandPane.executeCommand(command);
    }


    /***************************************************************************
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;

        BorderPane borderPane = new BorderPane();

        borderPane.setTop   (menuPane   );
        borderPane.setCenter(outputPane );
        borderPane.setBottom(commandPane);

        VBox rightPane = new VBox(itemsPane, neigborsPane);
        borderPane.setRight(rightPane);
        borderPane.setLeft (bagPane  );

        Scene scene = new Scene(borderPane, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.setTitle(getAuthorID() + " - " + getAuthorName());
        primaryStage.setOnCloseRequest(p -> Platform.exit());
        primaryStage.show();

        startGame();
        commandPane.requestFocus();
    }


    /***************************************************************************
     * Spustí komunikaci mezi zadanou hrou a danou instancí GUI
     * mající na starosti komunikaci s uživatelem;
     * nejprve však zkontroluje, že zadaná hra je ve skutečnosti
     * instancí rozhraní {@link IGameG},
     * protože jiné neposkytují metody, které grafické UI očekává.
     *
     * @param game Hra, kterou ma dané UI spustit
     * @throws IllegalArgumentException Hra není instancí {@link IGameG}
     */
    @Override
    public void startGame(IGame game)
    {
        this.game = game;
        outputPane  .initializeForGame(this.game);
        commandPane .initializeForGame(this.game);
        bagPane     .initializeForGame(this.game);
        itemsPane   .initializeForGame(this.game);
        neigborsPane.initializeForGame(this.game);
    }


    /***************************************************************************
     * Spustí komunikaci mezi implicitní hrou
     * a danou instancí uživatelského rozhraní.
     */
    @Override
    public void startGame()
    {
        startGame(getFactory().getGame());
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
//##############################################################################
//== EMBEDDED TYPES AND INNER CLASSES ==========================================



//##############################################################################
//== MAIN METHOD ===============================================================

    /***************************************************************************
     * Metoda spouštějící celou aplikaci.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {

//        eu.pedu.adv19s_fw.test_util._Test_115.getDefaultGame();
        Application.launch(args);
        Platform.exit();
    }
}
