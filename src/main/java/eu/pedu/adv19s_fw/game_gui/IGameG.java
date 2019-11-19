/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_gui;

import eu.pedu.adv19s_fw.game_txt.IGame;

import java.net.URL;



/*******************************************************************************
 * Instance interfejsu {@code IGameG} mají na starosti logiku hry.
 * Jsou schopny akceptovat jednotlivé příkazy a poskytovat informace
 * o průběžném stavu hry a jejích součástech.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IGameG
         extends IGame, IGSMFactoryProductG
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    //Metody her implementujících tento interfejs musejí vracet instance,
    //resp. kolekce instancí implementujících interfejs v tomto balíčku
    //Proto je třeba je tu deklarovat znovu. protože deklarované
    //návratové hodnoty jsou potomky návratových hodnot metod rodiče

    /***************************************************************************
     * Vrátí odkaz na batoh, do nějž bude hráč ukládat sebrané předměty.
     *
     * @return Požadovaný odkaz
     */
    @Override
    public IBagG getBag()
    ;


    /***************************************************************************
     * Vrátí URL adresu stránky s nápovědou.
     *
     * @return URL adresa stránky s nápovědou
     */
//    @Override
    public URL getHelpURL()
    ;


    /***************************************************************************
     * Vrátí obrázek s plánkem prostoru, v němž se hraje.
     *
     * @return Obrázek s plánkem prostoru
     */
//    @Override
    public Icon getMap()
    ;


    /***************************************************************************
     * Vrátí obrázek hráče, který bude zobrazován na plánku hry,
     * aby uživatel věděl, ve kterém prostoru se jeho hráč aktuálně nachází.
     *
     * @return Obrázek hráče
     */
//    @Override
    public Icon getPlayer()
    ;


    /***************************************************************************
     * Vrátí odkaz na svět, v němž se hra odehrává.
     *
     * @return Svět, v němž se hra odehrává
     */
    @Override
    public IWorldG getWorld()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================



//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
