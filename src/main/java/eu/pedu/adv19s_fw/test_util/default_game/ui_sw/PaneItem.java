/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IItemG;
import eu.pedu.adv19s_fw.game_txt.INamed;
import javax.swing.JLabel;
import javax.swing.SwingConstants;



/*******************************************************************************
 * Instance třídy {@code PaneItem} představují komponenty
 * sloužící k zobrazení jim zadaného objektu.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
@SuppressWarnings("serial")
public class PaneItem extends JLabel
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

    /** Název zobrazovaného prvku. */
    private final String caption;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří komponentu zobrazující název zadaného předmětu
     * spolu s jeho obrázkem.
     *
     * @param object Zobrazovaný předmět
     */
    public PaneItem(IItemG object)
    {
        super(object.getName(), object.getPicture(), SwingConstants.CENTER);
        caption = inicalize(object);
    }


    /***************************************************************************
     * Vytvoří komponentu zobrazující název zadaného pojemnovaného objektu
     * (většinou místnosti).
     *
     * @param object Zobrazovaný objekt
     */
    public PaneItem(INamed object)
    {
        super(object.getName(), SwingConstants.CENTER);
        caption = inicalize(object);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí název zobrazovaného objetku.
     *
     * @return Název zobrazovaného objetku
     */
    public String getName()
    {
        return caption;
    }


    /***************************************************************************
     * Inicializuje vytvářenou komponentu a vrátí název zobrazovaného objektu.
     * Přestavuje společnou část kódu obou verzí konstruktoru.
     *
     * @param object Zobrazovaný objekt
     * @return Název zobrazovaného objetku
     */
    private String inicalize(INamed object)
    {
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.TOP);
        return object.getName();
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
