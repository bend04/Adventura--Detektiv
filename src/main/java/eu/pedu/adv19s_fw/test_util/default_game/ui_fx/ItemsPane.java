/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_fx;

import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_gui.IPlaceG;
import eu.pedu.adv19s_fw.game_gui.IWorldG;
import eu.pedu.adv19s_fw.game_txt.INamed;

import java.util.Collection;



/*******************************************************************************
 * Instance třídy {@code ItemsPane} představují panely tlačítek
 * reprezentujících objekty v aktuálním prostoru.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class       ItemsPane
    extends AButtonPane
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
     * Pro zadané GUI vytvoří vytvoří panel tlačítek
     * reprezentujících h-objekty v aktuálním prostoru.
     *
     * @param gui   GUI, pro něž je panel vytvářen
     */
    ItemsPane(IMyGUI gui)
    {
        super(gui, "H-objekty v prostoru");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí název akce realizující zvenutí h-objektu.
     *
     * @return Název akce realizující zvenutí h-objektu
     */
    @Override
    String getCommandName()
    {
        return gui.getGame().getBasicActions().TAKE_CMD_NAME;
    }

//
//    /***************************************************************************
//     * Zjistí od zadaného informátora nový stav zobrazovaného kontejneru
//     * a vrátí kolekci jeho prvků.
//     *
//     * @param place Informátor znalý aktuálního stavu.
//     * @return Aktuální kolekce prvků zobrazovaného kontejneru
//     */
//    @Override
//    Collection<? extends INamed> getItems(IPlaceG place)
//    {
//        Collection<? extends INamed> objects = place.getItems();
//        return  objects;
//    }
//

    /***************************************************************************
     * Zjistí nový stav zobrazovaného kontejneru a vrátí kolekci jeho prvků,
     * v tomto případě h-objekty v aktuálním prostoru.
     *
     * @return Aktuální kolekce prvků zobrazovaného kontejneru
     */
    @Override
    Collection<? extends INamed> getObjects()
    {
        IWorldG world = gui.getGame().getWorld();
        IPlaceG place = world.getCurrentPlace();
        Collection<? extends INamed> objects = place.getItems();
        return objects;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Inicializuje se pro zadanou (nově spuštěnou) hru —
     * přihlásí se u ní jako posluchač objektů v batohu a
     * zapamatuje si název příkazu zadávaného stiskem tlačítka.
     *
     * @param game Nově spouštěná hra
     */
    @Override
    public void initializeForGame(IGameG game)
    {
        String commandName = game.getBasicActions().TAKE_CMD_NAME;
        initializationForGame(game, commandName);
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
