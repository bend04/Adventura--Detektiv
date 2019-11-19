/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_txt;



/*******************************************************************************
 * Instance interfejsu {@code IAuthor} umějí na požádání vrátit
 * jméno a identifikační řetězec autora/autorky své třídy.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IAuthor
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí jméno autora/autorky programu ve formátu <b>PŘÍJMENÍ Křestní</b>,
     * tj. nejprve příjmení psané velkými písmeny a za ním křestní jméno,
     * u nějž bude velké pouze první písmeno a ostatní písmena budou malá.
     * Má-li autor programu více křestních jmen, může je uvést všechna.
     *
     * @return Jméno autora/autorky programu ve tvaru PŘÍJMENÍ Křestní
     */
//    @Override
    public String getAuthorName()
    ;


    /***************************************************************************
     * Vrátí identifikační řetězec autora/autorky programu
     * zapsaný VELKÝMI PÍSMENY.
     * Tímto řetězcem bývá většinou login do informačního systému školy.
     *
     * @return Identifikační řetězec autora/autorky programu
     */
//    @Override
    public String getAuthorID()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================

    /***************************************************************************
     * Vrátí řetězec skládající se z ID autora následovaného jeho jménem.
     * Metoda slouží jako obejití nemožnosti implementace implicitní metody
     * přebíjející metodu {@code toString()}.
     *
     * @return ID a jméno autora
     */
    public default String getAuthorString()
    {
        return getAuthorID() + " — " + getAuthorName();
    }




//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
