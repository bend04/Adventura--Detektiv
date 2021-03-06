/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smil
 */
package cz.vse.java.adventura;

import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IGSMFactoryProduct;



/*******************************************************************************
 * Instance interfejsu {@code IAuthorPrototype} umějí na požádání vrátit
 * jméno a identifikační řetězec autora/autorky své třídy;
 * tyto hodnoty mají uloženy ve svých statických konstantách
 * {@link #AUTHOR_NAME} a {@link #AUTHOR_ID}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IAuthorDB
         extends IGSMFactoryProduct
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================

    /** Jméno autora/autorky ve formátu <b>P�?ÍJMENÍ Křestní</b>,
     *  tj. nejprve příjmení psané velkými písmeny a za ním křestní jméno,
     *  u nějž bude velké pouze první písmeno a ostatní písmena budou malá.
     *  Má-li autor programu více křestních jmen, může je uvést všechna. */
    String AUTHOR_NAME = "PECINOVSKÝ Rudolf";

    /** Identifikační řetězec autora/autorky zapsaný VELKÝMI PÍSMENY.
     *  Tímto řetězcem bývá většinou login do informačního systému školy. */
    String AUTHOR_ID = "PECR920";

    /** Class-objekt tovární třídy, jejíž instance slouží jako tovární objekty
     *  zprostředkující přístup ke klíčovým instancím aplikace
     *  (správci scénářů, vlastní hře a objektu realizujícímu uživatelské
     *  rozhraní) a schopná prozradit jméno a ID autora dané aplikace. */
    Class<? extends IGSMFactory> FACTORY_CLASS = DetGSMFactory.class;



//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí jméno autora/autorky programu ve formátu <b>P�?ÍJMENÍ Křestní</b>,
     * tj. nejprve příjmení psané velkými písmeny a za ním křestní jméno,
     * u nějž bude velké pouze první písmeno a ostatní písmena budou malá.
     * Má-li autor programu více křestních jmen, může je uvést všechna.
     *
     * @return Jméno autora/autorky programu ve tvaru P�?ÍJMENÍ Křestní
     */
    @Override
    default
    public String getAuthorName()
    {
        return "BENEŠ Dominik";
    }


    /***************************************************************************
     * Vrátí identifikační řetězec autora/autorky programu
     * zapsaný VELKÝMI PÍSMENY.
     * Tímto řetězcem bývá většinou login do informačního systému školy.
     *
     * @return Identifikační řetězec autora/autorky programu
     */
    @Override
    default
    public String getAuthorID()
    {
        return "bend04";
    }


    /***************************************************************************
     * Vrátí Class-objekt tovární třídy, jejíž instance jsou schopné
     * prozradit jméno a ID autora dané aplikace a současně slouží jako
     * tovární objekty zprostředkující přístup ke klíčovým instancím aplikace
     *
     * @return Požadovaný class-objekt
     */
    @Override
    default
    public Class<? extends IGSMFactory> getFactoryClass()
    {
        return FACTORY_CLASS;
    }



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
