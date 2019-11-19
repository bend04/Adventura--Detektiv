/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_fx;

import eu.pedu.adv19s_fw.game_gui.IGameG;
import javafx.scene.control.TextArea;



/*******************************************************************************
 * Instance třídy {@code OutputPane} představují panel obsahující
 * výstupní textovou oblast, do níž se zapisují zadávané příkazy
 * spolu s na ně reagujícími odpověďmi hry.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class       OutputPane
    extends TextArea
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
    private final IMyGUI gui;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Pro zadané GUI vytvoří vytvoří výstupní panel.
     *
     * @param gui GUI, pro něž je panel vytvářen
     */
    OutputPane(IMyGUI gui)
    {
        this.gui = gui;
        //Je třeba zabezpečit, aby uživatel nemohl ani omylem
        //psát do výstupního pole
        setEditable(false);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Inicializuje se pro zadanou (nově spuštěnou) hru —
     * vytiskne zprávu o ukončení té přeedchozí a spuštění nové.
     *
     * @param game Nově spouštěná hra
     */
//    @Override
    public void initializeForGame(IGameG game)
    {
        appendText("\n\n" +
                   "======================================================\n" +
                   "=================== START NOVÉ HRY ===================\n" +
                   game.getClass().getName() + "\n" +
                   "======================================================\n");
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
