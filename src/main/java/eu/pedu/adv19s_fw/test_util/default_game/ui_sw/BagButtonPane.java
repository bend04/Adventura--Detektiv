/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IGameG;

import java.util.Collection;



/*******************************************************************************
 * Instance třídy {@code BagButtonPane} představují panely,
 * v nichž se v průběhu hry zobrazuje obsah batohu hráče,
 * tj. h-objekty, které jsou v něm uloženy.
 * <br><br>
 * Třída je upravena tak, aby její instance uměly pracovat s prvky
 * definovanými jako tlačítka, jejichž stiskem lze daný předmět položit.
 * K tomu bylo třeba přidat atributy pro zapamatování si příkazu k položení
 * předmětu a také GUI, jehož prostřednictvím bude příkaz hře zadán.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
@SuppressWarnings("serial")
class BagButtonPane extends AItemContainerButtonPane
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
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří nový panel pro zobrazení obsahu batohu,
     * přičemž předpokládá, že h-objekty budou na panelu zobrazovány
     * jako tlačítka, jejichž stiskem můžeme daný h-objekt položit.
     *
     * @param gui  GUI, prostřednictvím nějž je hra ovládána
     */
    BagButtonPane(IMyGUI gui)
    {
        super("Obsah batohu", gui);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Inicializuje panel pro práci s novou hrou.
     *
     * @param game Hra, s níž bude panel od této chvíle komunikovat
     */
    @Override
    @SuppressWarnings("unchecked")
    public void inicialize(IGameG game)
    {
        action = game.getBasicActions().PUT_DOWN_CMD_NAME;
        //Kdyby hráč začínal s neprázným batohem
        //Přetypování má pouze odstranit námitky překladače
        update((Collection)(game.getBag().getItems()));
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
