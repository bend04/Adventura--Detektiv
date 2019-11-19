/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_gui;

import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IUI;



/*******************************************************************************
 * Interfejs {@code IUIG} deklaruje požadavky na třídu definující uživatelské
 * rozhraní (User Interface); vedlejším požadavkem je,
 * <b>ABY TŘÍDA MĚLA IMPLICITNÍ, TJ. VEŘEJNÝ BEZPARAMETRICKÝ KONSTRUKTOR</b>.
 * Tento požadavek sice nemůže být kontrolován překladačem,
 * ale je kontrolován testovacími programy.
 * <p>
 * Metody {@link #startGame(IGame)} a {@link #startGame()} deklarované v tomto
 * interfejsu slouží k zadání a spouštění hry,
 * s níž má grafické uživatelské rozhraní komunikovat.
 * <p>
 * Vlastní komunikace s touto hrou může probíhat nejenom
 * přímým zadáváním příkazů prostřednictvím daného GUI,
 * ale pro účely testování také nepřímo prostřednictvím programu
 * opakovaně volajícího metodu {@link IGame#executeCommand(String)},
 * které program předá text příkazu simulovaně zadaného uživatelem
 * a od niž pak převezme zprávu, kterou následné vypíše testerovi.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IUIG
         extends IUI, IGSMFactoryProductG
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí aktuálně hranou hru.
     *
     * @return Aktuálně hraná hra
     */
    @Override
    public IGameG getGame()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================

    /***************************************************************************
     * Zpracuje příkaz předaný v parametru jako by byl zadán standardním
     * postupem z klávesnice. Metoda umožňuje testovat reakce daného
     * uživatelského rozhraní na příkazy zadávané z klávesnice.
     *
     * @param command Zadávaný příkaz
     */
    public void executeCommand(String command)
    ;


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
    ;



//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
