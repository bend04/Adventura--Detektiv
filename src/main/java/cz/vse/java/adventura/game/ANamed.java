package cz.vse.java.adventura.game;



/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */


import eu.pedu.adv19s_fw.game_txt.INamed;



/*******************************************************************************
 * Instance třídy {@code ANamed} představují rodičovské podobjekty
 * instancí tříd pojmenovaných objektů,
 * tj. tříd implementujících interfejs {@link INamed}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2015-Podzim — 15.55.5837 — 2015-11-25
 */
public abstract class ANamed implements INamed
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    /** Název dané instance. */
    private final String name;



//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří rodičovský podobjekt instance objektu se zadaným názvem.
     *
     * @param name Název dané instance
     */
    public ANamed(String name)
    {
        this.name = name;
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

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



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

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




//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
