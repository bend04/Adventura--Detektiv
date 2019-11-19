/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game;

import eu.pedu.adv19s_fw.game_txt.IGSMFactory;





/*******************************************************************************
//%L+ CZ
 * Instance interfejsu {@code IAuthorTDemo} představují ...
//%Lx EN
 * The {@code IAuthorTDemo} interface instances represent ...
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IAuthorTDemo
         extends IAuthorDemo
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================
//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================

    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which also the mother class of this instance is.
     *
     * @return The class-object of the factory class
     */
    @Override
    default public Class<? extends IGSMFactory> getFactoryClass()
    {
        return GSMFactoryTLit_Apartment.class;
    }



//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
