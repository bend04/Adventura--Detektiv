/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_gui;


import java.awt.Point;
import java.util.Collection;
import eu.pedu.adv19s_fw.game_txt.IPlace;



/*******************************************************************************
 * Instance interfejsu {@code IPlaceG} přestavují místnosti či jejich
 * ekvivalenty. Dosažení místnosti si můžeme představovat jako částečný cíl,
 * kterého se hráč ve hře snaží dosáhnout.
 * Místnosti mohou obsahovat různé předměty,
 * které mohou hráči pomoci v dosažení cíle hry.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IPlaceG
         extends IPlace, IItemContainerG
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí pozici místnosti na plánu hry,
     * přesněji pozici hráče nacházejícího se v dané místnosti.
     * Na této pozici bude hráč při pobytu v dané místnosti zobrazen.
     *
     * @return Požadovaná pozice
     */
//    @Override
    public Point getPosition();


    //Metody místností implementujících tento interfejs musejí vracet
    //kolekce instancí implementujících interfejs {@link IItemG}
    //Proto je třeba je tu deklarovat znovu. protože původně deklarované
    //návratové hodnoty jsou potomky návratových hodnot metod jeho rodiče


    /***************************************************************************
     * Vrátí kolekci prostorů, do nichž je možno se z tohoto prostoru přesunout.
     *
     * @return Požadovaná kolekce
     */
    @Override
    public Collection<? extends IPlaceG> getNeighbors();



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
