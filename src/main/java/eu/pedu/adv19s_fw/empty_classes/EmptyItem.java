/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.empty_classes;

import eu.pedu.adv19s_fw.game_txt.IItem;
import eu.pedu.adv19s_fw.utilities.UncompletedMethodException;



/*******************************************************************************
 * Instance třídy {@code EmptyItem} přestavují objekty v prostorech.
 * Objekty mohou být jak věci, tak i osoby či jiné skutečnosti (vůně,
 * světlo, fluidum, ...).
 * <p>
 * Některé z objektů mohou charakterizovat stav prostoru (např. je rozsvíceno),
 * jiné mohou být určeny k tomu, aby je hráč "zvednul" a získal tím nějakou
 * schopnost či možnost projít nějakým kritickým místem hry
 * (např. klíč k odemknutí dveří).
 * <p>
 * Rozhodnete-li se použít ve hře objekty, které budou obsahovat jiné objekty,
 * (truhla, stůl, ...), můžete je definovat paralelně jako zvláštní druh
 * prostoru, do kterého se "vstupuje" např. příkazem "<i>prozkoumej truhlu</i>"
 * a který se opouští např. příkazem "<i>zavři truhlu</i>".
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public   class EmptyItem
       extends ANamed
    implements IItem
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
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří objekt se zadaným názvem a dalšími zadanými vlastnostmi.
     * Tyto dodatečné vlastnosti se zadávají prostřednictvím předpony
     * vkládané před vlastní název objektu
     *
     * @param name Název vytvářeného objektu
     */
    EmptyItem(String name)
    {
        super(name);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí váhu předmětu, resp. charakteristiku jí odpovídající.
     * Objekty, které není možno zvednout,
     * mají váhu větší, než je kapacita batohu.
     *
     * @return Váha objektu
     */
    @Override
    public int getWeight()
    {
        //TODO EmptyItem.getWeight - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
