/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_txt;

import java.util.Collection;



/*******************************************************************************
 * Instance interfejsu {@code IItemContainer} představují kontejnery,
 * které mohou obsahovat objekty vystupující ve hře.
 * Speciálními případy těchto kontejnerů jsou prostory a batoh.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IItemContainer
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí kolekci objektů nacházejících se v daném kontejneru.
     *
     * @return Kolekce objektů nacházejících se v daném kontejneru
     */
//    @Override
    public Collection<? extends IItem> getItems()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
