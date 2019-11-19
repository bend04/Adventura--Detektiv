/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_txt;



/*******************************************************************************
 * Interfejs {@code IUI} deklaruje požadavky na třídu
 * definující uživatelské rozhraní (User Interface).
 * <p>
 * Zde deklarovaná metoda {@link #startGame(IGame)} slouží pouze k zadání hry,
 * která se má spustit a s níž má uživatelské rozhraní komunikovat.
 * Vlastní komunikace s hrou pak probíhá prostřednictvím opakovaných volání
 * metody {@link IGame#executeCommand(String)},
 * které UI předá příkaz zadaný uživatelem a od níž pak převezme zprávu,
 * kterou následné vypíše uživateli.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IUI
         extends IGSMFactoryProduct
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
//    @Override
    public IGame getGame()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================

    /***************************************************************************
     * Spustí komunikaci mezi zadanou hrou a danou instancí
     * mající na starosti komunikaci s uživatelem.
     *
     * @param game Hra, kterou ma dané UI spustit
     */
//    @Override
    public void startGame(IGame game)
    ;



//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================

    /***************************************************************************
     * Spustí komunikaci mezi implicitní hrou
     * a danou instancí uživatelského rozhraní.
     */
//    @Override
    default
    void startGame()
    {
        IGSMFactory factory = getFactory();
        IGame       game    = factory.getGame();
        startGame(game);
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
