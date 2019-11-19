/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.empty_classes;

import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IUI;
import eu.pedu.adv19s_fw.scenario.AScenarioManager;



/*******************************************************************************
 * Instance třídy {@code EmptyGUI4txt} představují dekorátory obalující
 * grafické uživatelské rozhraní (GUI) zabudované v rámci,
 * a to tak, aby je bylo možno použít jako GUI pro hru vyhovující rámci<br>
 * definovanému v balíčku <b>{@code eu.pedu.adv19s_fw.game_txt}</b>.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class EmptyGUI4txt
  implements IUI, IAuthorPrototype
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

    /** Dekorované GUI z rámce. */
    private final IUI gui;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří GUI obalující GUI zabudované v rámci.
     */
    public EmptyGUI4txt()
    {
        gui = AScenarioManager.getDefaultFactory().getUI();
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí aktuálně hranou hru.
     *
     * @return Aktuálně hraná hra
     */
    @Override
    public IGame getGame()
    {
        return gui.getGame();
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Spustí komunikaci mezi zadanou hrou a danou instancí GUI
     * mající na starosti komunikaci s uživatelem.
     *
     * @param game Hra, kterou ma dané UI spustit
     */
    @Override
    public void startGame(IGame game)
    {
        gui.startGame(game);
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
