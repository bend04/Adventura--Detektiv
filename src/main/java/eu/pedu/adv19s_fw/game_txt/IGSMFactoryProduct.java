/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_txt;



/*******************************************************************************
 * Instance interfejsu {@code IGSMFactoryProduct} představují objekty
 * poskytované instancemi tovární třídy implementující interfejs
 * {@link IGSMFactory}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IGSMFactoryProduct
         extends IAuthor
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí class-objekt tovární třídy, jejíž instance umějí zprostředkovat
     * získání všech klíčových objektů aplikace, jejíž součástí je i daná třída.
     *
     * @return Class-objekt tovární třídy
     */
    public Class<? extends IGSMFactory> getFactoryClass()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================

    /***************************************************************************
     * Vrátí tovární objekt schopný zprostředkovat dodání
     * všech klíčových objektů aplikace.
     *
     * @return Požadovaný tovární objekt
     */
    default
    public IGSMFactory getFactory()
    {
        IGSMFactory result;
        result = IGSMFactory.getInstanceOfFactory(getFactoryClass());
        return result;
    }



//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
