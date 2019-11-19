package cz.vse.java.adventura.game;



import cz.vse.java.adventura.DetGSMFactory;
import cz.vse.java.adventura.textui.Start;
import eu.pedu.adv19s_fw.game_txt.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.event.TreeModelEvent;
import java.util.Optional;
import java.util.Scanner;

public class Controller {

    public static final int SIRKA_IKONY = 60;
    public static final int VYSKA_IKONY = 45;



    @FXML
    private VBox seznamVychodu;
    @FXML
    public VBox seznamPredmetuVMistnosti;
    @FXML
    public VBox seznamPredmetuVBatohu;
    private IGame hra;

    public ImageView obrazekLokace;

    public IBag batoh;

    @FXML
    private Label jmenoLokace;
    private Item vec;

    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;


    /*Nastaví hru.*/
    public void setHra(IGame hra) {
        this.hra = hra;
        hra.executeCommand("");
        IWorld world = hra.getWorld();
        IPlace actualRoom = world.getCurrentPlace();
        zmenRoom((Room) actualRoom);
       textArea.setText(Texts.zUVÍTÁNÍ);
    }



    /*Změna místnosti*/
    private void zmenRoom(Room prostor) {
        hra.executeCommand("jdi " + prostor.getName());
        System.out.println(hra.getWorld().getCurrentPlace().getName());

        jmenoLokace.setText(prostor.getName());


        String nazevObrazku = "/" + prostor.getName() + ".jpg";
        Image image = new Image(getClass().getResourceAsStream(nazevObrazku));
        obrazekLokace.setImage(image);


        pridejVychody(prostor);
        pridejPredmety(prostor);
        pridejPredmetBatoh(vec);
    }

    /*Přidá východy + obrázky*/
    private void pridejVychody(Room prostor) {
        seznamVychodu.getChildren().clear();
        for (Room p : prostor.getNeighbors()) {

            HBox vychod = new HBox();
            vychod.setSpacing(10);
            Label nazevProstoru = new Label(p.getName());

            ImageView vychodImageView = new ImageView();
            Image vychodImage = new Image(getClass().getClassLoader().getResourceAsStream(  p.getName() + ".jpg"));
            vychodImageView.setFitHeight(VYSKA_IKONY);
            vychodImageView.setFitWidth(SIRKA_IKONY);
            vychodImageView.setImage(vychodImage);

            vychod.getChildren().addAll(vychodImageView, nazevProstoru);

            seznamVychodu.getChildren().add(vychod);
            vychod.setOnMouseClicked(event -> {
                zmenRoom(p);
            });
        }
    }
    /*Přidá předměty do místnosti + logika sebrání věci*/
    private void pridejPredmety(Room prostor) {
        seznamPredmetuVMistnosti.getChildren().clear();

        for (Item vec : prostor.getItems()) {

            HBox predmet = new HBox();
            predmet.setSpacing(10);
            Label nazevPredmetu = new Label(vec.getName());


            ImageView predmetImageView = new ImageView();
            Image predmetImage = new Image(getClass().getClassLoader().getResourceAsStream(   vec.getName() + ".jpg"));
            predmetImageView.setFitHeight(VYSKA_IKONY);
            predmetImageView.setFitWidth(SIRKA_IKONY);
            predmetImageView.setImage(predmetImage);

            predmet.getChildren().addAll(predmetImageView, nazevPredmetu);
            seznamPredmetuVMistnosti.getChildren().add(predmet);
            Hands bag = Hands.getInstance();
            String itemName = vec.getName();


            predmet.setOnMouseClicked(event ->
            {


                if (vec.jePrenositelna() && getMisto() != 0) {


                    Room currentRoom = Dum.getInstance().getCurrentPlace();
                    currentRoom.removeItem(vec);
                    boolean added = bag.tryAddItem(vec);
                    hra.executeCommand("vezmi" + vec.getName());

                    seznamPredmetuVMistnosti.getChildren().remove(nazevPredmetu);
                    seznamPredmetuVMistnosti.getChildren().remove(predmetImageView);
                    misto -= vec.getWeight();



                    if (added) {
                        if (itemName.toLowerCase().equals(Texts.KLIC.toLowerCase())) {
                            State.setKey(true);
                        }
                        if (itemName.toLowerCase().equals(Texts.PRISTUPOVA_KARTA.toLowerCase())) {
                            State.setCard(true);
                        }
                        if (itemName.toLowerCase().equals(Texts.LAHVICKA.toLowerCase())) {
                            State.setEvidence(true);
                        }
                    }
                }
                refresh();
            });
        }


    }


        /*Správce batohu + nastavení klik batoh*/
    public void pridejPredmetBatoh(Item vec) {
        seznamPredmetuVBatohu.getChildren().clear();
        Hands bag = Hands.getInstance();
        for (Item veciBatoh : bag.getItems()
        ) {

            HBox batoh = new HBox();
            batoh.setSpacing(10);
            Label nazevPredmetu = new Label(veciBatoh.getName());

            ImageView batohImageView = new ImageView();
            Image batohImage = new Image(getClass().getClassLoader().getResourceAsStream(  veciBatoh.getName() + ".jpg"));
            batohImageView.setFitHeight(VYSKA_IKONY);
            batohImageView.setFitWidth(SIRKA_IKONY);
            batohImageView.setImage(batohImage);

            batoh.getChildren().addAll(batohImageView, nazevPredmetu);

           seznamPredmetuVBatohu.getChildren().add(batoh);
            batoh.setOnMouseClicked(event -> {

                Label vecBatoh = new Label(veciBatoh.getName());

                    hra.executeCommand("poloz" + veciBatoh.getName());

                    bag.removeItem(veciBatoh);
                    seznamPredmetuVMistnosti.getChildren().add(vecBatoh);
                    seznamPredmetuVBatohu.getChildren().remove(batohImageView);
                    seznamPredmetuVBatohu.getChildren().remove(vecBatoh);
                    Room currentRoom = Dum.getInstance().getCurrentPlace();
                    currentRoom.addItem(veciBatoh);
                    misto += veciBatoh.getWeight();
                    refresh();
                });
        }
    }


    /*Zjistí aktuální kapacitu. */
    public int getMisto() {return misto;};

    /* Kapacita batohu v FX*/

    public int misto = 2;

    /*Ukončí hru.*/
    public void konecHry(ActionEvent actionEvent) {
        Platform.exit();
    }

    /*Spustí nápovědu*/
    public void napoveda(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Scene scene = new Scene(new Label("Tvým úkolem je najít vraha pana Nováka.   \n" +
                "   K tomu, aby se ti to povedlo, musíš po domě najít různé důkazy - je třeba udělat 4 akce.    \n" +
                "   Pomocí příkazů ve hře konáš aktivity. K přesunu z místnosti do místnosti, můzeš" +
                "   použít pravý sloupec Místnosti (klikat na jednotlivé místnosti) a také u předmětů, stejným způsobem" +
                "   je zvedat či pokládat.   \n" +
                "   Pro zobrazení všech příkazů, zadej příkaz: ? (otazník)\n"));
        stage.setScene(scene);
        stage.show();
    }

    /*Spustí nápovědu jak vyhrát*/
    public void  jakVyhrat(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Scene scene = new Scene(new Label("    Tvým úkolem je najít vraha pana Nováka.\n" +
                " Startuješ v místnosti Předsíň. Přesuň se do koupelny, vezmi lahvičku - 1 důkaz.\n" +
                "Poté se přesun do obýváku, prohledej mrtvolu - 2 důkaz, a vezmi si klíč. Poté jdi přes kuchyn do pracovny.\n" +
                "Odemkni trezor, otevři trezor, vezmi přístupovou kartu a vrat se zpátky do kuchyně. Nadále pokračuj přes \n" +
                "zahradu, kulnu do bezpečnostní místnosti. Prohledej počítač - 3 důkaz. Vrat se zpět do Předsíně a poté\n" +
                "jdi do ložnice, kde se nachazí manželka. Prohledni si manželku - 4 důkaz. Obviň ji ze vraždy.  \n"));
        stage.setScene(scene);
        stage.show();
    }

    /*Zpracování příkazu - herní příkazový řádek*/
    @FXML
    public void zadaniPrikazu(ActionEvent e){

        String terminalText = textField.getText();
        System.out.println(terminalText);
        textArea.setText(hra.executeCommand(terminalText));
        refresh();
    }

    /* Stará se o aktuální "vizáž" hry.*/
    private void refresh(){
        zmenRoom((Room) hra.getWorld().getCurrentPlace());
        pridejPredmety((Room) hra.getWorld().getCurrentPlace());
        pridejVychody((Room) hra.getWorld().getCurrentPlace());
        textField.clear();
    }
    /*Spustí novou hru*/
    public void novaHra(ActionEvent actionEvent) {

        AAction.stopGame();
        String terminalText = textField.getText();
        textField.setText("a");
        textArea.setText(hra.executeCommand(terminalText));
        textArea.setText(Texts.zUVÍTÁNÍ);
        seznamPredmetuVBatohu.getChildren().clear();
        misto = 2;
        refresh();

    }
}





   


