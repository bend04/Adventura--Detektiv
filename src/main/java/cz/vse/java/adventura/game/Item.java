/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.game;

import eu.pedu.adv19s_fw.game_txt.IItem;



/*******************************************************************************
 * Instance třídy {@code Item} přestavují předměty v prostorech.
 * Předmět <i>lednička</i> je v této hře současně prostorem.
 *
 * @author  Dominik BENEŠ
 * @version 2019 léto
 */
class Item extends ANamed implements IItem
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    /** Váha nepřenositelných předmětů. */
    private static final int HEAVY = Hands.CAPACITY + 1;

    /** Příznak standardního přenositelného předmětu. */
    static final char STANDARD = '1';

    /** Příznak nepřenositelnosti předmětu. */
    static final char NOT_MOVABLE = '#';
    
    
    /** Váha předmětu. */
    private final int weight;

    private boolean prenositelna;




//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří předmět se zadaným názvem a dalšími zadanými vlastnostmi.
     * Tyto dodatečné vlastnosti se zadávají prostřednictvím prefixu,
     * kterým je první znak zadaného názvu.
     * Vlastní název předmětu tvoří až zbylé znaky.<br>
     * znaky prefixu mají následující význam:<br>
     *
     *
     * @param name Název vytvářeného předmětu
     */
    Item(String name)
    {
        super(name.substring(1));
        prenositelna = false;
        int     estimatedWight = 1;
        char    prefix         = name.charAt(0);
        switch (prefix)
        {
            case STANDARD:
                prenositelna = true;
                break;

            
            case NOT_MOVABLE:
                estimatedWight = HEAVY;
                break;

            default:
                throw new RuntimeException(
                    "\nNeznámá hodnota prefixu: «" + prefix + '»');
           
        }
        weight      = estimatedWight;

    }




//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí váhu předmětu, resp. charakteristiku jí odpovídající.
     * Předměty, které není možno zvednout,
     * mají váhu větší, než je kapacita batohu.
     *
     * @return Váha předmětu
     */
    @Override
    public int getWeight()
    {
        return weight;
    }


    public boolean jePrenositelna() {
        return prenositelna;
    }

}



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================

