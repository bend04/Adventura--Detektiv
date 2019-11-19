/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.empty_classes;

import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_gui.IUIG;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.scenario.AScenarioManagerG;



/*******************************************************************************
 * Instance třídy {@code EmptyGUI} představují dekorátory obalující
 * grafické uživatelské rozhraní (GUI) zabudované v rámci,
 * a to tak, aby je bylo možno použít jako GUI pro hru vyhovující rámci<br>
 * definovanému v balíčku <b>{@code eu.pedu.adv19s_fw.game_gui}</b>.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class EmptyGUI
  implements IUIG, IAuthorPrototypeG
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
    private final IUIG gui;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří GUI obalující GUI zabudované v rámci.
     */
    public EmptyGUI()
    {
        gui = AScenarioManagerG.getDefaultFactory().getUI();
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí aktuálně hranou hru.
     *
     * @return Aktuálně hraná hra
     */
    @Override
    public IGameG getGame()
    {
        return gui.getGame();
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Zpracuje příkaz předaný v parametru jako by byl zadán standardním
     * postupem z klávesnice. Metoda umožňuje testovat reakce daného
     * uživatelského rozhraní na příkazy zadávané z klávesnice.
     *
     * @param command Zadávaný příkaz
     */
    @Override
    public void executeCommand(String command)
    {
        gui.executeCommand(command);
    }


    /***************************************************************************
     * Spustí komunikaci mezi zadanou hrou a danou instancí GUI
     * mající na starosti komunikaci s uživatelem;
     * nejprve však zkontroluje, že zadaná hra je ve skutečnosti
     * instancí interfejsu {@link IGameG},
     * protože jiné neposkytují metody, které grafické UI očekává.
     * <p>
     * Tato metoda nemůže deklarovat parametr typu {@link IGameG},
     * protože by pak instance tohoto interfejsu nemohly plnohodnotně
     * vystupovat v rolích svého rodiče, jelikož by měly zúžené požadavky
     * na typ svého parametru.
     *
     * @param game Hra, kterou ma dané UI spustit
     * @throws IllegalArgumentException Hra není instancí {@link IGameG}
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
