/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.textui;

import cz.vse.java.adventura.game.Controller;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IUI;

import cz.vse.java.adventura.IAuthorDB;
import cz.vse.java.adventura.DetGSMFactory;
import cz.vse.java.adventura.game.BenDetektivGame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Scanner;


/*******************************************************************************
 * Instance třídy {@code UIB_Console} realizují uživatelské rozhraní,
 * které využívá standardní vstup a výstup.
 *
 * @author  Dominik BENEŠ
 * @version 2019 léto
 */
public class Start extends Application implements IUI, IAuthorDB
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


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scena.fxml"));
        Parent rootComponent = loader.load();

        Scene scene = new Scene(rootComponent);
        primaryStage.setScene(scene);

        BenDetektivGame game = new BenDetektivGame();
        Controller controller = loader.getController();
        controller.setHra(game);

        primaryStage.setTitle("Detektiv");
        InputStream iconStream = getClass().getResourceAsStream("/ikona.png");
        Image icon = new Image(iconStream);
        primaryStage.getIcons().add(icon);
        primaryStage.show();
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
        Scanner scanner = new Scanner(System.in);
        String  command = "";
        String  answer;
        for(;;) {
            answer  = game.executeCommand(command);
            System.out.println(answer);
            if (! game.isAlive()) { break; }    //---------->
            command =  scanner.nextLine();
        }
    }





//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================



//##############################################################################
//== MAIN METHOD ===============================================================

    /***************************************************************************
     * Metoda spouštějící hru {@link BenDetektivGame}
     * s uživatelským rozhraním využívajícím služeb
     * standardního vstupu a výstupu a třídy {@link Scanner}.
     *
     * @param args Parametry příkazového řádku - prozatím nepoužívané
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
