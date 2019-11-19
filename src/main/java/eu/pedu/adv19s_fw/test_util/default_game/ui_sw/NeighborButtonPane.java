/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IGameG;

import java.util.ArrayList;
import java.util.Collection;
import eu.pedu.adv19s_fw.game_gui.IPlaceG;



/*******************************************************************************
 * Instance třídy {@code PanelSousedů} představují panely,
 * v nichž se v průběhu hry zobrazují sousedé aktuální místnosti,
 * tj místností, do nichž je možno z aktuální mísntosti přímo přejít.
 * <br><br>
 * Třída je upravena tak, aby její instance uměly pracovat s prvky
 * definovanými jako tlačítka, jejichž stiskem lze hráče přesunout
 * do příslušné místnosti.
 * K tomu bylo třeba přidat atributy pro zapamatování si příkazu k přejítí
 * a také GUI, jehož prostřednictvím bude příkaz hře zadán.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
@SuppressWarnings("serial")
class NeighborButtonPane extends AButtonPane<IPlaceG>
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



//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    /** Příkaz, jímž se v aktuálně zpracovávané hře
     *  přesouvá do zadané místnosti. */
    private String actionMove;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří nový panel pro zobrazování sousedů aktuální místnosti.
     *
     * @param gui  GUI, prostřednictvím nějž je hra ovládána
     */
    NeighborButtonPane(IMyGUI gui)
    {
        super("Aktuální sousedé");
        this.gui = gui;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Aktualizuje obsah panelu tak,
     * aby tlačítka reprezentovala sousedy (prostory) ze zadané kolekce.
     *
     * @param source Kolekce prostorů, jež mají být reprezentovány tlačítky
     */
    @Override
    public void update(Collection<IPlaceG> source)
    {
        Collection<GButton> buttons = new ArrayList<>();
        for (IPlaceG neighbor : source) {
            GButton gButton = new GButton(neighbor, actionMove, gui);
            buttons.add(gButton);
        }
        processNotice(buttons);
    }


    /***************************************************************************
     * Inicializuje panel pro práci s novou hrou.
     *
     * @param hra Hra, s níž bude panel od této chvíle komunikovat
     */
    @Override
    @SuppressWarnings("unchecked")
    public void inicialize(IGameG hra)
    {
        actionMove = hra.getBasicActions().MOVE_CMD_NAME;
        //Přetypování má pouze odstranit námitky překladače
        update((Collection)(hra.getWorld().getAllPlaces()));
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
