/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game;

import static eu.pedu.adv19s_fw.test_util.default_game.DATA
                .TextsL.eLOCALE_SET;

import java.util.Locale;



/*******************************************************************************
//%L+ CZ
 * Knihovní třída {@code GetSetLocale} umožňuje na počátku práce nastavit
 * {@link Locale}, podle nějž se nastaví hodnoty konstant ve třídě
 * {@code TextsL}.
//%Lx EN
 * The library class {@code GetSetLocale} allows to set the {@link Locale}
 * for setting the constant values in the {@code TextsL} class.
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class GetSetLocale
//todo Dokumetace třídy se odkazuje na neznámou třídu
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    private static Locale locale;


//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Vrátí nastavené {@link Locale}.
     * Ptám-li se dříve, než bylo nastaveno, vyhodí výjimku.
     *
     * @return Nastavené {@link Locale}
     * @throws IllegalStateException Při dotazu před nastavením {@link Locale}
     */
    public static Locale getLocale()
           throws IllegalStateException
    {
        if (locale == null) {
            throw new IllegalArgumentException("Locale did not set yet");
        }
        return locale;
    }


    /***************************************************************************
     * Nastaví zadané {@link Locale}.
     * To se však smí pro danou třídu nastavit pouze jednou.
     *
     * @param ID Mezinárodní identifikátor zadávaného {@link Locale}
     * @throws IllegalArgumentException Při opakovaném pokusu o nastavení
     */
    public static void setLocale(String ID)
           throws IllegalArgumentException
    {
        if (locale == null) {
            locale = Locale.forLanguageTag(ID);
        }
        else {
            throw new IllegalArgumentException(eLOCALE_SET);
        }
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Soukromý konstruktor zabraňující vytvoření instance.
     */
    private GetSetLocale()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
