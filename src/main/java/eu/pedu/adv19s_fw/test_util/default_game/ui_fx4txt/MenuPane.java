/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_fx4txt;

import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.scenario.AScenarioManagerG;
import eu.pedu.adv19s_fw.test_util.default_game.GSMFactoryG_Apartment;


import java.util.function.Supplier;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventDispatchChain;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/*******************************************************************************
 * Instance třídy {@code MenuPane} představují panel nabídek.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class       MenuPane
    extends MenuBar
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



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Pro zadané GUI vytvoří panel nápovědy.
     *
     * @param gui GUI, pro něž je panel vytvářen
     */
    MenuPane(IMyGUI4txt gui)
    {
        this.gui = gui;
        getMenus().addAll(prepareGameMenu(),
                          prepareHelpMenu());
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Vytvoří (a vrátí) nabídku umožňující ukončit aktuální hru
     * a spustit zadanou hru.
     *
     * @return Požadovaná nabídka
     */
    private Menu prepareGameMenu()
    {
        String menuName;
        MenuItem myGame = new MenuItem(menuName = "Moje hra");
        myGame.setOnAction(changeGameFor(() ->
            IGSMFactory.getInstanceOfFactory(GSMFactoryG_Apartment.class)
                           .getGame(),
            menuName));

        MenuItem demoGame = new MenuItem(menuName = "Demonstrační hra");
        demoGame.setOnAction(
                 changeGameFor(AScenarioManagerG.getDefaultFactory()::getGame,
                               menuName));

        MenuItem restartGame = new MenuItem(menuName = "Restart aktuální hry");
        restartGame.setOnAction(changeGameFor(
                                    DefaultGUI4txt.getInstance()::getGame,
                                    menuName));

        MenuItem separator = new SeparatorMenuItem();

        MenuItem exit = new MenuItem(menuName = "Ukončení aplikace");
        exit.setOnAction(p -> Platform.exit());

        Menu gameMenu = new Menu("Hra");
        gameMenu.getItems().addAll(myGame, demoGame, restartGame, separator,
                                   exit);

        return gameMenu;
    }


    /***************************************************************************
     * Vytvoří (a vrátí) nabídku ....
     *
     * @return Požadovaná nabídka
     */
    private Menu prepareHelpMenu()
    {
        MenuItem aboutItem = new MenuItem("Informace o aplikaci");
        aboutItem.setOnAction(this::showAboutWindow);

        Menu helpMenu = new Menu("Nápověda");
        helpMenu.getItems().addAll(aboutItem);

        return helpMenu;
    }


    @Override
    public EventDispatchChain buildEventDispatchChain(EventDispatchChain tail)
    {
        //TODO MenuPane.buildEventDispatchChain - Not finished yet
        return super.buildEventDispatchChain(tail);
    }


    /***************************************************************************
     * Vrátí ovladač události {@link ActionEvent}, který
     * <ol>
     *   <li>ukončí běh aktuálně hrané hry,</li>
     *   <li>zapíše do výstupní oblasti záznam o provedené změně,</li>
     *   <li>spustí hru od poskytnutou zadaným dodavatelem.</li>
     * </ol>
     *
     * @param gameFactory Dodavatel hry, která se má spustit
     * @param menuName    Název spuštěné nabídky – pouze pro dokumentační účely
     * @return Požadovaný ovladač události
     */
    private EventHandler<ActionEvent> changeGameFor(
        Supplier<IGame> gameFactory, String menuName)
    {
        EventHandler<ActionEvent> handler =
        (e) ->
        {
            IGame nextGame = gameFactory.get();
            gui.getOutputPane().appendText(
                "\n\n======================================" +
                  "\nAkce: "        + menuName +
                  "\nPřepínám na: " + nextGame.getClass() +
                  "\n======================================" +
                "\n\n");
            nextGame.stop();
            gui.startGame(gameFactory.get());
        };
        return handler;
    }


    /***************************************************************************
     * Zobrazí okno s informacemi o aplikaci.
     *
     * @param event Událost zapříčinivší vyvolání této metody
     */
    private void showAboutWindow(ActionEvent event)
    {
        IGame  game    = gui.getGame();
        String content =
                "\nAplikace řešící první semestrální úlohu předmětu" +
                "\n4IT115 Základy softwarového inženýrství." +
                "\n" +
                "\nHra je definována ve třídě: " + game.getClass().getName() +
                "\nAutor(ka) hry: " + game.getAuthorName() +
                "\n" +
                "\nGUI je definováno ve třídě: " + gui.getClass().getName() +
                "\nAutor(ka) GUI: " + gui.getAuthorName();
        Text text = new Text(10, 10, content);
        text.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));

        Pane  textPane = new Pane(text);
        textPane.paddingProperty().set(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(textPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }



//##############################################################################
//== EMBEDDED TYPES AND INNER CLASSES ==========================================
}
