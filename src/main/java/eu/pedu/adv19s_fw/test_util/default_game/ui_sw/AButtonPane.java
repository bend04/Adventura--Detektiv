/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_txt.INamed;

import java.util.Collection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;



/*******************************************************************************
 * Instance třídy {@code AButtonPane} představují panely,
 * v nichž jsou umístěny komponenty zobrazující jednotlivé vložené prvky.
 *
 * @param <Named> Typ prvků představovaných jednotlivými tlačítky
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
@SuppressWarnings("serial")
public abstract class AButtonPane<Named extends INamed> extends Box
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Velikost mezery vkládané mezi jednotlivé prvky. */
    private static final int PLACE = 10;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Titulek oznamující obsah okna. */
    private final JLabel titleLabel;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří základ panelu prvků nadepsaného zadaným titulkem.
     *
     * @param title Označení vytvářeného panelu prvků
     */
    public AButtonPane(String title)
    {
        super(BoxLayout.Y_AXIS);
        titleLabel = new JLabel(
            "<html><font color=\"red\">" + title +
            "</font><hr></html>");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================

    /***************************************************************************
     * Aktualizuje obsah panelu tak,
     * aby tlačítka reprezentovala objekty ze zadané kolekce.
     *
     * @param source Kolekce objektů, jež mají být reprezentovány tlačítky
     */
    public abstract void update(Collection<Named> source);


    /***************************************************************************
     * Inicializuje panel pro práci s novou hrou.
     * Při ní se panel musí přihlásit u hry jako posluchač jistého druhu objektů
     * a zobrazit příslušné objekty na počátku hry.
     *
     * @param hra Hra, s níž bude panel od této chvíle komunikovat
     */
    abstract public void inicialize(IGameG hra);



//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Závěrečná fáze zpracování obdrženého hlášení poté,
     * co oslovený objekt připravil kolekci zobrazovaných objektů.
     * Metoda vyčistí panel a naplní jej objekty obdrženými v parametru.
     *
     * Kolekce předávaná jako parametr má definované prvky typu
     * {@code <? extends JComponent>} proto, aby bylo možno
     * jednoduše přejít od návěští, tj. od prvků typu {@link PaneItem},
     * které jsou potomky typu {@link JLabel},
     * k tlačítkům, tj. k prvkům typu {@code ButtonPaneItem},
     * které jsou potomky typu {@link javax.swing.JButton}.
     * Typ {@link JComponent} je totiž jejich nejbližším společným předkem.
     *
     * @param items Prvky, které je v panelu třeba zobrazit.
     */
    public void processNotice(Collection<? extends JComponent> items)
    {
        this.removeAll();
        this.add(titleLabel);
        for (JComponent item : items) {
            this.add(item);
            this.add(Box.createVerticalStrut(PLACE)); //Oddělovací mezera
        }
        this.add(Box.createVerticalStrut(PLACE));
        this.repaint();
        this.revalidate();
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
