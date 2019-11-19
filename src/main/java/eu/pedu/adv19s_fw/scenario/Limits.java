/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.scenario;



/*******************************************************************************
 * Instance třídy {@code Limits} představují přepravky
 * s minimálními požadovanými "rozměry" úspěšného scénáře.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class Limits
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

    /** Minimální počet kroků úspěšného scénáře. */
    public final int minSteps;

    /** Minimální počet prostorů hry. */
    public final int minPlaces;

    /** Minimální počet navštívených prostorů. */
    public final int minVisited;

    /** Minimální počet definic vlastních příkazů. */
    public final int minOwnActions;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří přepravku s minimálními požadovanými "rozměry"
     * úspěšného scénáře.
     *
     * @param minSteps      Minimální počet kroků scénáře
     * @param minPlaces     Minimální počet prostorů ve světě hry
     * @param minVisited    Minimální počet navštívených prostorů
     * @param minOwnActions Minimální počet definic vlastních příkazů
     */
    public Limits(int minSteps, int minPlaces, int minVisited,
                  int minOwnActions)
    {
        this.minSteps      = minSteps;
        this.minPlaces     = minPlaces;
        this.minVisited    = minVisited;
        this.minOwnActions = minOwnActions;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Textový podpis dané instance srozumitelně oznamující požadavky.
     *
     * @return Text s popisem požadavků
     */
    @Override
    public String toString()
    {
        return "Minimální požadované \"rozměry\" úspěšného scénáře:"
           + "\n   Minimální počet kroků = "                  + minSteps
           + "\n   Minimální počet prostorů hry = "           + minPlaces
           + "\n   Minimální počet navštívených prostorů = "  + minVisited
           + "\n   Minimální počet definic vlastních akcí = " + minOwnActions;
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
