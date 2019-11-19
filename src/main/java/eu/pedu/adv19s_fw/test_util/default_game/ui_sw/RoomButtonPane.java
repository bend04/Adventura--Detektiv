/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IGameG;

import javax.swing.Box;
import javax.swing.BoxLayout;



/*******************************************************************************
 * Instance třídy {@code RoomButtonPane} představují panely,
 * v nichž se v průběhu hry zobrazují předměty v aktuální místnosti
 * a sousedé této místnosti
 * <br><br>
 * Třída je oproti třídě {@code RoomPane} upravena tak,
 * aby pracovala s panely používajícími tlačítka.
 * K tomu bylo třeba přidat atributy pro zapamatování si příkazu k položení
 * předmětu a také GUI, jehož prostřednictvím bude příkaz hře zadán.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
@SuppressWarnings("serial")
public class RoomButtonPane extends Box
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

    /** Panel zobrazující předměty v aktuální místnosti. */
    private final ThingButtonPane objectPane;

    /** Panel zobrazující předměty v aktuální místnosti. */
    private final NeighborButtonPane neighborPane;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří nový panel obsahující panely předmětů a sousedů
     * aktuální místnosti.
      *
     * @param gui  GUI, prostřednictvím nějž je hra ovládána
    */
    public RoomButtonPane(IMyGUI gui)
    {
        super(BoxLayout.Y_AXIS);
        objectPane    = new ThingButtonPane(gui);
        neighborPane  = new NeighborButtonPane(gui);

        add(objectPane);
        add(Box.createGlue());  //Odtlačí oba panely ke krajům pole
        add(neighborPane);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Inicializuje panel pro práci s novou hrou.
     *
     * @param game Hra, s níž bude panel od této chvíle komunikovat
     */
    public void inicialize(IGameG game)
    {
        objectPane   .inicialize(game);
        neighborPane .inicialize(game);
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
