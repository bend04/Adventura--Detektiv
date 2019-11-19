/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IBagG;
import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_gui.IItemG;

import java.util.ArrayList;
import java.util.Collection;



/*******************************************************************************
 * Instance třídy {@code AItemContainerButtonPane} představují panely,
 * v nichž se v průběhu hry zobrazují tlačítka reprezentující h-objekty,
 * které jsou uloženy v příslušném kontejneru &ndash; prostoru či batohu.
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
abstract class AItemContainerButtonPane extends AButtonPane<IItemG>
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

    /** GUI, prostřednictvím nějž je hra ovládána. */
    private final IMyGUI gui;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Příkaz, jímž se v aktuálně zpracovávané hře h-objekt
     *  zvedá, resp pokládá (to závisí na druhu kontejneru). */
    protected String action;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří nový panel pro zobrazení obsahu kontejneru, přičemž předpokládá,
     * že předměty v kontejneru budou na panelu zobrazovány jako tlačítka,
     * jejichž stiskem můžeme daný předmět v závislosti na typu kontejneru
     * položit nebo zvednout.
     *
     * @param gui       GUI, prostřednictvím nějž je hra ovládána
     * @param paneName  Název umisťovaný v záhlaví panelu
     */
    AItemContainerButtonPane(String paneName, IMyGUI gui)
    {
        super(paneName);
        this.gui = gui;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Aktualizuje obsah panelu tak,
     * aby tlačítka reprezentovala h-objekty ze zadané kolekce.
     *
     * @param source Kolekce h-objektů, jež mají být reprezentovány tlačítky
     */
    @Override
    public void update(Collection<IItemG> source)
    {
        Collection<GButton> buttons = new ArrayList<>();
        for (IItemG item : source) {
            GButton gButton = new GButton(item, action, gui);
            buttons.add(gButton);
        }
        processNotice(buttons);
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
