/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.empty_classes;

import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.INamed;



/*******************************************************************************
 * Instance abstraktní třídy {@code ANamed} představují rodičovské podobjekty
 * instancí tříd pojmenovaných objektů,
 * tj. tříd implementujících interfejs {@link INamed}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public abstract class ANamed
           implements INamed
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Název dané instance. */
    private final String name;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří rodičovský podobjekt instance objektu se zadaným názvem.
     * Konstruktor přitom zkontroluje, že zadávaný název není prázdný odkaz
     * ani prázdný řetězec, a že není-li pojmenovávaný objekt objektem hry,
     * tak je jednoslovný, tj. neobsahuje bílé znaky.
     *
     * @param name Název dané instance
     */
    public ANamed(String name)
    {
        if ((name == null)  ||  name.isEmpty()) {
            throw new IllegalArgumentException(
                        "\nJako název objektu nesmí být zadán "
                      + "prázdný odkaz ani prázdný řetězec");
        }
        if (! (this instanceof IGame)         &&
            ( (! name.equals(name.trim())) ||
              (name.split("\\s").length > 1)  ))
        {
            throw new IllegalArgumentException(
                        "\nNázvy objektů musejí být jednoslovné, "
                      + "tj. nesmějí obsahovat bílé znaky - Zadáno: «"
                      + name + '»');
        }
        this.name = name;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí název dané instance.
     *
     * @return Název instance
     */
    @Override
    public String getName()
    {
        return name;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Vrátí textový podpis dané instance tvořený názvem instance.
     *
     * @return Název instance
     */
    @Override
    public String toString()
    {
        return name;
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
