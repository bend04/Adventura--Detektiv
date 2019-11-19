/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_txt;

import java.util.Collection;



/*******************************************************************************
 * Instance interfejsu {@code IGame} mají na starosti logiku hry.
 * Jsou schopny akceptovat jednotlivé příkazy a poskytovat informace
 * o průběžném stavu hry a jejích součástí.
 * <p>
 * <b>Hra musí být definována jako jedináček (singleton)</b>
 * a kromě metod deklarovaných v tomto interfejsu musí její třída definovat
 * statickou tovární metodu <b>{@code getInstance()}</b>
 * vracející instanci tohoto jedináčka.<br>
 * Splnění této podmínky nemůže prověřit překladač,
 * ale prověří ji až následné testy hry.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IGame
         extends IGSMFactoryProduct, INamed
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí informaci o tom, je-li hra aktuálně spuštěná.
     * Spuštěnou hru není možno pustit znovu.
     * Chceme-li hru spustit znovu, musíme ji nejprve ukončit.
     *
     * @return Je-li hra spuštěná, vrátí {@code true},
     *         jinak vrátí {@code false}
     */
//    @Override
    public boolean isAlive()
    ;


    /***************************************************************************
     * Vrátí odkaz na batoh, do nějž bude hráč ukládat sebrané objekty.
     *
     * @return Batoh, do nějž hráč ukládá sebrané objekty
     */
//    @Override
    public IBag getBag()
    ;


    /***************************************************************************
     * Vrátí kolekci všech příkazů použitelných ve hře.
     *
     * @return Kolekce všech příkazů použitelných ve hře
     */
//    @Override
    public Collection<? extends IAction> getAllActions()
    ;


    /***************************************************************************
     * Vrátí odkaz na přepravku s názvy povinných příkazů, tj. příkazů pro
     * <ul>
     *   <li>přesun hráče do jiného prostoru,</li>
     *   <li>zvednutí objektu (odebrání z prostoru a vložení do batohu),</li>
     *   <li>položení objektu (odebrání z batohu a vložení do prostoru),</li>
     *   <li>vyvolání nápovědy,</li>
     *   <li>okamžité ukončení hry.</li>
     * </ul>
     *
     * @return Přepravka s názvy povinných příkazů
     */
//    @Override
    public BasicActions getBasicActions()
    ;


    /***************************************************************************
     * Vrátí odkaz na objekt reprezentující svět, v němž se hra odehrává.
     *
     * @return Svět, v němž se hra odehrává
     */
//    @Override
    public IWorld getWorld()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================

    /***************************************************************************
     * Zpracuje zadaný příkaz a vrátí text zprávy pro uživatele.
     *
     * @param command Zadávaný příkaz
     * @return Textová odpověď hry na zadaný příkaz
     */
//    @Override
    public String executeCommand(String command)
    ;


    /***************************************************************************
     * Ukončí celou hru a vrátí alokované prostředky.
     */
//    @Override
    public void stop()
    ;



//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
