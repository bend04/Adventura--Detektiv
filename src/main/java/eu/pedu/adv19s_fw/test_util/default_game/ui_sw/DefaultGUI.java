/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_gui.IUIG;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.test_util.default_game.IAuthorDemo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



/*******************************************************************************
 * Instance třídy {@code DefaultGUI} přestavují pro testovací účely používané
 * implicitní grafické uživatelské rozhraní
 * pro hry implementující rozhraní {@link IGameG}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public   class DefaultGUI
    implements IUIG, IMyGUI
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    private static final DefaultGUI SINGLETON = new DefaultGUI();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================

    /***************************************************************************
     * Inicializuje vstupní a výstupní panel pro práci s novou hrou.
     * Hlavní úlohou této metody je incializovat {@link #outputBuilder}.
     */
    private void initializeIOPanels()
    {
        messageOutputPane.setText("");
        outputBuilder.setLength(0);
        outputBuilder.append("<html>");
    }


    /***************************************************************************
     * Prověří, že zadaná hra implementuje rozhraní {@link IGameG}
     * a je schopna spuštění, zapamatuje si ji
     * a umístí název autora a hry do titulku aplikačního okna.
     *
     * @param game Spouštěná hra
     * @throws IllegalArgumentException Něco je špatně
     */
    private void verifyGame(final IGame game) throws IllegalArgumentException
    {
        if (! (game instanceof IGameG)) {
            throw new IllegalArgumentException( "\nSpouštěná hra" +
                "neimplementuje rozhraní adventura_115_10J.rámec.IGameG");
        }
        if (game.isAlive()) {
            throw new IllegalArgumentException(
                "\nPřed dalším spuštěním je třeba hru nejprve ukončit");
        }
        applicationWindow.setTitle(               //Podepíše aplikační okno
            "Autor GUI: "     + getAuthorName() +
            ",   Autor hry: " + game.getAuthorName());

        this.controlledGame = (IGameG)game;       //Zapamatuje si ovládanou hru
    }


    /***************************************************************************
     * Připraví systém nabídek a vrátí nabídku,
     * která umožní ovlivnit zaznamenávání zadaných příkazů.
     *
     * @return Nabídka umožňující ovlivnit logování příkazů
     */
    private MenuLogger prepareMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        applicationWindow.setJMenuBar(menuBar);

        MenuGame   menuGame = new MenuGame  (this);
        MenuLogger menuLog  = new MenuLogger(this);
        MenuHelp   menuHelp = new MenuHelp  (this);

        menuBar.add(menuGame);
        menuBar.add(menuLog);
        menuBar.add(menuHelp);

        return menuLog;
    }


    /***************************************************************************
     * Připraví v západní zóně aplikačního okna panel,
     * který bude sloužit zobrazení předmětů v batohu.
     */
    private void prepareBagButtonPane()
    {
        applicationWindow.add(bagButtonPane, BorderLayout.WEST);
    }


    /***************************************************************************
     * Připraví ve východní zóně aplikačního okna panel,
     * který bude sloužit zobrazení předmětů v aktální místnosti
     * a sousedů této místnosti, kam lze v daný okamžik přejít.
     */
    private void prepareRoomButtonPane()
    {
        applicationWindow.add(roomButtonPane, BorderLayout.EAST);
    }


    /***************************************************************************
     * Připraví v jižní zóně aplikačního okna panel,
     * který bude sloužit pro standardní zadávání příkazů.
     * Současně přihlásí u vstupního pole jeho posluchače.
     */
    private void prepareCommandInputField()
    {
        //Popisek označující význam vstupního pole
        JLabel  inputLabel = new JLabel ("Zadej prikaz:");
        JButton stopButton = new JButton("KONEC");

        //Spodní (jižní) panel pro zadávání příkazů
        JPanel bottomPanel = new JPanel();

        bottomPanel.add(inputLabel);
        bottomPanel.add(commandInputField);
        bottomPanel.add(stopButton);

        applicationWindow.add(bottomPanel, BorderLayout.SOUTH);
        commandInputField  .addActionListener(inputListener);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (controlledGame != null) {
                    String příkaz = controlledGame.getBasicActions()
                                                  .END_CMD_NAME;
                    executeCommand(příkaz);
                }
            }
        });
    }


    /***************************************************************************
     * Připraví v centrální zóně aplikačního okna panel,
     * v němž se budou zobrazovat všechny zprávy o průběhu hry.
     */
    private void prepareMessageOutputPane()
    {
        messageOutputPane.setEditable(false);
        JScrollPane posuvnik = new JScrollPane(messageOutputPane);
        posuvnik.setPreferredSize(new Dimension(400, 300));
        applicationWindow.add(posuvnik, BorderLayout.CENTER);
    }


    /***************************************************************************
     * Připraví mapu hry.
     */
    private void prepareGameMap()
    {
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Aplikační okno hry. */
    private final JFrame applicationWindow = new JFrame();

    /** Vstupní textové pole pro zadávání příkazů. */
    private final JTextField commandInputField = new JTextField(40);

    /** Čeká na zadání příkazu, který pak předá obsluhované hře a
     *  zadaný příkaz spolu s obdrženou odpovědí zapíše do výstupního pole. */
    private final InputListener inputListener = new InputListener();

    /** Textová oblast, v níž se vypisují odpovědi hry na zadané příkazy. */
    private final JEditorPane messageOutputPane =
              new JEditorPane("text/html", "");

    /** Objekt určený pro postupné skládání jednotlivých odpovědí hry
     *  do jednoho souvislého textového řetězce. */
    private final StringBuilder outputBuilder = new StringBuilder();

    /** Panel zobrazující obsah batohu (předměty, které s v něm nacházejí. */
    private final BagButtonPane bagButtonPane = new BagButtonPane(this);

    /** Panel zobrazující předměty v aktuální místnosti a její sousedy.*/
    private final RoomButtonPane roomButtonPane =new RoomButtonPane(this);

    /** Objekt řídící zaznamenávání zadaných příkazu. */
    private MenuLogger logger;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Hra, kterou dané gui právě ovládá. */
    private IGameG controlledGame;

    /** Příznak toho, zda se bude v průběhu hry zobrazovat její plánek. */
    private boolean gameMapVisible =  true;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vrátí instanci jedináčka realizujícího GUI.
     *
     * @return Instance jedináčka
     */
    public static DefaultGUI getInstance()
    {
        return SINGLETON;
    }


    /***************************************************************************
     * Vytvoří a zobrazí prázdné aplikační okno, jež čeká na zadání hry,
     * kterou bude obsluhovat.
     */
    private DefaultGUI()
    {
        logger = prepareMenuBar();
        prepareGameMap();

        prepareMessageOutputPane();
        prepareCommandInputField();
        prepareBagButtonPane();
        prepareRoomButtonPane();

        applicationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí odkaz na aktuálně ovládanou hru.
     *
     * @return Odkaz na aktuálně ovládanou hru
     */
    @Override
    public IGameG getGame()
    {
        return controlledGame;
    }


    /***************************************************************************
     * Vrátí aktuální rozměr okna hry.
     *
     * @return Aktuální rozměr okna hry
     */
    @Override
    public Rectangle getArea()
    {
        return applicationWindow.getBounds();
    }


    /***************************************************************************
     * Vrátí komponentu, která může sloužit jako rodič
     * otevíraných dialogových oken.
     *
     * @return Rodičovská komponenta
     */
    @Override
    public Component getParents()
    {
        return applicationWindow;
    }


    /***************************************************************************
     * Vrátí aktuální stav zobrazování plánku hry.
     *
     * @return Aktuální stav zobrazování plánku hry
     */
    @Override
    public boolean isGameMapVisible()
    {
        return gameMapVisible;
    }


    /***************************************************************************
     * Nastaví, zda se bude během hry zobrazovat plánek hry.
     *
     * @param zobrazovat {@code true} = bude se zobrazovat plánek hry
     */
    @Override
    public void setGameMapVisible(boolean zobrazovat)
    {
        gameMapVisible = zobrazovat;
        GameMap.getInstance().setVisible(zobrazovat);
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Spustí komunikaci touto instancí a zadanou hrou, která musí být
     * ve skutečnosti instancí rozhraní {@link IGameG}
     * a která nevíc nesmí být ještě rozběhnutá - rozbíhá ji až GUI.
     *
     * @param hra Hra, kterou ma dané GUI spustit
     * @throws IllegalArgumentException Pokud zadaná hra neimplementuje
     *         rozhraní {@link IGameG} nebo pokud je již rozběhnutá
     */
    @Override
    public void startGame(final IGame hra)
    {
        verifyGame(hra);

        //Hru odstartujeme zadáním prázdného příkazu
        logger.updateLog(null);    //Prázdný odkaz = nová hra
        initializeIOPanels();
        executeCommand("");

        //Inicializuje podřízené panely
        gameMapVisible = true;
        bagButtonPane        .inicialize(controlledGame);
        roomButtonPane       .inicialize(controlledGame);
        GameMap.getInstance().initialize(controlledGame);

        //Zviditelnění aplikačního okna a úprava jeho velikosti a umístění
        applicationWindow.pack();
        applicationWindow.setVisible(true);
        applicationWindow.setLocation(POSITION_0);

        //Od této chvíle přebírá řízení hry posluchač vstupního pole
    }


    /***************************************************************************
     * Spustí komunikaci mezi implicitní hrou (ta nesmí v danou chvíli běžet)
     * a danou instancí GUI mající na starosti komunikaci s uživatelem.
     */
    @Override
    public void startGame()
    {
        //TODO DefaultGUI.startGame - Operace ještě není implementována
        throw new UnsupportedOperationException("Prozatím neimplementováno");
//        ovládanáHra = IMPLICITNÍ_HRA;
//        startGame(ovládanáHra);
    }



//+++ Přidáno pro rozšířené zadání v předmětu 4IT115 +++++++++++++++++++++++++++

    /***************************************************************************
     * Zpracuje příkaz předaný v parametru jako by byl zadán standardním
     * postupem z klávesnice. Metoda umožňuje testovat reakce daného
     * uživatelského rozhraní na příkazy zadávané z klávesnice.
     *
     * @param příkaz Zadávaný příkaz
     */
    @Override
    public void executeCommand(String příkaz)
    {
        commandInputField.setText(příkaz);

        //Nemusím zadávat parametr volané metody,
        //protože ta si potřebné informce zjistí od pole txfVstup
        inputListener.actionPerformed(null);
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

    /***************************************************************************
     * Instance třídy "poslouchá" události ve vstupním poli,
     * do nějž se zadávají příkazy, předá zadaný příkaz obsluhované hře
     * a zadaný příkaz spolu s obdrženou odpovědí zapíše do výstupního pole.
     */
    private class InputListener implements ActionListener
    {
        /***********************************************************************
         * Požadovaným způsobem zareaguje na zadání příkazu,
         * tj. předá příkaz hře, převede její odpověď do formát HTML
         * a přidá ji spolu se zadaným příkazem na konec výpisu akcí.
         * Zabezpečí přitom, že zadaný příkaz je vysazen tučně a
         * odpověď hry netučně.
         *
         * @param e Nastalá událost
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String command = commandInputField.getText();
            logger.updateLog(command);

            outputBuilder.append("<b>").append(command).append("</b><br>");

            String answer = controlledGame.executeCommand(command);

            answer = answer.replace("\n", "<br>");
            outputBuilder.append(answer).append("<br><br>");
            messageOutputPane.setText(outputBuilder + "</html>");

            commandInputField.setText(""); //Vyprázdnění obsahu vstupního pole
            //Přesunutí kurzoru na konec série zpráv, kterým dosáhneme
            //přesunutí posuvníku a zobrazení tohoto konce
            messageOutputPane.setCaretPosition(messageOutputPane.getDocument().
                                               getLength());
            applicationWindow.pack();
            commandInputField.requestFocus();
        }
    }

}
