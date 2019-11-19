/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_txt;

import java.util.Collection;



/*******************************************************************************
 * Instance interfejsu {@code IBag} představuje úložiště,
 * do nějž hráči ukládají objekty sebrané v jednotlivých prostorech,
 * aby je mohli přenést do jiných prostorů a/nebo použít.
 * Úložiště má konečnou kapacitu definující maximální povolený
 * součet vah objektů vyskytujících se v úložišti.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IBag
         extends IItemContainer
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí kapacitu batohu, tj. maximální povolený součet vah objektů,
     * které je možno současně uložit do batohu.
     *
     * @return Kapacita batohu
     */
//    @Override
    public int getCapacity()
    ;

     
//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
