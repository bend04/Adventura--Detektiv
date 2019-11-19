/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.empty_classes;

import eu.pedu.adv19s_fw.game_txt.IBag;
import eu.pedu.adv19s_fw.game_txt.IItem;
import eu.pedu.adv19s_fw.utilities.UncompletedMethodException;

import java.util.Collection;



/*******************************************************************************
 * Instance třídy {@code EmptyBag} představuje úložiště,
 * do nějž hráči ukládají objekty sebrané v jednotlivých prostorech,
 * aby je mohli přenést do jiných prostorů a/nebo použít.
 * Úložiště má konečnou kapacitu definující maximální povolený
 * součet vah objektů vyskytujících se v úložišti.
 * <p>
 * V této hře je tímto úložištěm ...
 * s kapacitou ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public   class EmptyBag
    implements IBag
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Jediná instance batohu ve hře. */
    private static final EmptyBag SINGLETON = new EmptyBag();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Tovární metoda vracející odkaz na jedninou existující instanci dané hry.
     *
     * @return Instance dané hry
     */
    static EmptyBag getInstance()
    {
        return SINGLETON;
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     */
    EmptyBag()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí kapacitu batohu, tj. maximální povolený součet vah objektů,
     * které je možno současně uložit do batohu.
     *
     * @return Kapacita batohu
     */
    @Override
    public int getCapacity()
    {
        //TODO EmptyBag.getCapacity - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Vrátí kolekci objektů uložených v batohu.
     *
     * @return Kolekce objektů v batohu
     */
    @Override
    public Collection<? extends IItem> getItems()
    {
        //TODO EmptyBag.getItems - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
