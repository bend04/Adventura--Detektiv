/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IGameG;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;




/*******************************************************************************
 * Instance třídy {@code NabidkaNapoveda} přestavují ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
@SuppressWarnings("serial")
class MenuHelp extends JMenu
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    private static final String ABOUT_STRING =
        "<html><font color=\"red\">Vzorové řešení</font><br>" +
        "grafického rozhraní adventury<br><br>" +
        "tentokrát již doplněné o nabídku s možností<br>" +
        "aktivovat nápovědu" +
        "</html>";



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
     *
     * @param gui Grafické rozhraní, pro které instance pracuje
     */
    MenuHelp(IMyGUI gui)
    {
        super("Nápověda");
        this.gui = gui;
        this.setMnemonic( 'N' );

        creatHelpItem();
        CreateAboutItem();
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Otevře aplikační okno zobrazující obsah hlavního souboru nápovědy,
     * ze kterého vedou hyperodkazy do dalších souborů.
     * Metoda umožňuje otevřít několik instancí tohoto okna
     * a v každé si zobrazit jinou část nápovědy.
     */
    private void openHelpWindow()
    {
        JFrame      helpWindow = new JFrame("Nápověda");
        JEditorPane helpPane   = new JEditorPane();

        //Připraví konfiguraci panelu s nápovědou v okně
        helpPane.setEditable(false);
        JScrollPane editorScrollPane = new JScrollPane(helpPane);
        editorScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        helpWindow.add(editorScrollPane);

        //Naplní panel obsahem
        IGameG game = gui.getGame();
        URL    url  = game.getHelpURL();
        try {
            helpPane.setPage(url);
        } catch (Exception e) {
            throw new RuntimeException(
                "\nNepodařilo se otevřít nápovědu na URL: " + url, e);
        }
        helpPane.addHyperlinkListener(new HLL(helpPane));
        helpWindow.pack();
        helpWindow.setVisible(true);
    }


    /***************************************************************************
     * V nabídce Nápověda vytvoří položku Nápověda,
     * po jejímž zadání se zobrazí okno s nápovědou.
     */
    private void creatHelpItem()
    {
        JMenuItem helpItem = new JMenuItem("Nápověda");
        this.add(helpItem);

        helpItem.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent ae ) {
                    openHelpWindow();
                }
            }
        );
    }


    /***************************************************************************
     * V nabídce Nápověda vytvoří položku O aplikaci, po jejímž zadání
     * se zobrazí okno se základními informacemi o dané aplikaci.
     */
    private void CreateAboutItem()
    {
        JMenuItem aboutItem = new JMenuItem("O aplikaci");
        this.add(aboutItem);

        aboutItem.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent ae ) {
                    JOptionPane.showMessageDialog(null,
                        ABOUT_STRING,
                        "Druhá vzorové řešení GUI adventury",
                        JOptionPane.PLAIN_MESSAGE);

                }
            }
        );
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

    /***************************************************************************
     * Instance třídy <b><code>HLL</code></b> přestavují posluchače
     * reagující na klepnutí na hypertextový odkaz.
     *
     * @author  Rudolf PECINOVSKÝ
     * @version 2018-Winter
     */
    public class HLL implements HyperlinkListener
    {
    //\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==========
    //\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==========



    //##########################################################################
    //\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =====================
    //\CF== CLASS (STATIC) FACTORY METHODS =====================================
    //\CG== CLASS (STATIC) GETTERS AND SETTERS =================================
    //\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS =======================
    //\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS =======================



    //##########################################################################
    //\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===========

        /** */
        private final JEditorPane helpPane;



    //\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===========



    //##########################################################################
    //\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===============================

        /***********************************************************************
         * Vytvoří nového posluchače ovlivňujícího obsah zadaného panelu.
         *
         * @param helpPane Panel s ovlivňovaným obsahem
         */
        public HLL(JEditorPane helpPane)
        {
            this.helpPane = helpPane;
        }


    //\IA== INSTANCE ABSTRACT METHODS ==========================================
    //\IG== INSTANCE GETTERS AND SETTERS =======================================
    //\IM== INSTANCE REMAINING NON-PRIVATE METHODS =============================

        /***********************************************************************
         *
         */
        @Override
        public void hyperlinkUpdate(final HyperlinkEvent e) {
            URL url = e.getURL();
            if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                    helpPane.setPage(url);
                } catch (IOException ioe) {
                    System.err.println("Netrefim na: " + url);
                }
            }
        }



    //\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =============================



    //##########################################################################
    //\NT== NESTED DATA TYPES ==================================================
    }

}
