/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_gui;

import eu.pedu.adv19s_fw.game_txt.IGSMFactoryProduct;



/*******************************************************************************
 * Instances of the {@code IGSMFactoryProduct} interface represent the object
 * that knows the key factory class of their application.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IGSMFactoryProductG
         extends IGSMFactoryProduct
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which is also the mother class of this instance.
     *
     * @return The class-object of the factory class
     */
    public Class<? extends IGSMFactoryG> getFactoryClass()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================

    /***************************************************************************
     * Returns the the factory object, which can mediate receiving this object
     * together with all key objects of the application,
     * the part of which this object is.
     *
     * @return The class-object of the factory class
     */
    default
    public IGSMFactoryG getFactory()
    {
        IGSMFactoryG result;
        result = IGSMFactoryG.getInstanceOfFactory(getFactoryClass());
        return result;
    }



//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
