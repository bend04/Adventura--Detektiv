/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;



/*******************************************************************************
 * Instance třídy {@code LogWindow} představují okna,
 * do nichž se na požádání vypisují zprávy žurnálu.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class LogWindow
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

    /** Okno, v němž se bude obsah žurnálu zobraovat. */
    private final JFrame logFrame = new JFrame("Žurnál");

    /** Textové pole, , v němž se bude obsah žurnálu zobraovat. */
    private final JTextArea textArea = new JTextArea(16, 64);

    /** Dokument s obsahem zobrazovaným v textovém poli. */
    private final Document document;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     *
     */
    public LogWindow()
    {
        document = textArea.getDocument();
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);
        logFrame.add(scrollPane);
        logFrame.pack();
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí informaci o viditelnosti okna.
     *
     * @return Je-li okno viditelné, vrátí {@code true},
     *         jinak vrátí {@code false}
     */
    public boolean isVisible()
    {
        return logFrame.isVisible();
    }


    /***************************************************************************
     * Nastaví viditelnost okna.
     *
     * @param visible Nastavovaná viditelnost
     */
    public void setVisible(boolean visible)
    {
        logFrame.setVisible(visible);
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Přidá zadaný text na další řádek za konec zobrazované textu
     * a přesune za něj kurzor, aby byl text viditelný.
     *
     * @param text Přidávaný text
     */
    public void append(String text)
    {
        int behindEndIndex = document.getLength();
        try {
            document.insertString(behindEndIndex, text, null);
        } catch (BadLocationException ex) {
            throw new RuntimeException(
                    "\nŠpatně spočtená délka žurnálu", ex);
        }
        textArea.setCaretPosition(document.getLength());
    }


    /***************************************************************************
     * Smaže obsah okna.
     *
     */
    public void erase()
    {
        textArea.setText("");
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
