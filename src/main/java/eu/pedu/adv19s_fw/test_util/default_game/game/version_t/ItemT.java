/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.version_t;

import eu.pedu.adv19s_fw.game_txt.IItem;
import eu.pedu.adv19s_fw.test_util.default_game.I18n;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyItem;
import eu.pedu.adv19s_fw.test_util.default_game.game.common.ANamed;



/*******************************************************************************
 * {@code Item} instances represent the items in places.
 * In this game the item <i>ice-box</i> is at the same time the place.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public    class ItemT
        extends ANamed
    implements IMyItem
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** The weight of non-portable items. */
    public static final int HEAVY = Integer.MAX_VALUE;

    /** The indicator of a standard portable item. */
    public static final char STANDARD = '1';

    /** The indicator of necessity to use both hands for raising the item up. */
    public static final char DOUBLE_HAND = '2';

    /** The indicator of non-portability of the item. */
    public static final char NOT_MOVABLE = '#';

    /** The indicator of alcoholic drink. */
    public static final char ALCOHOL = '@';



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Weight of the item. */
    private final int weight;

    /** If it is the alcoholic drink. */
    private final boolean isAlcoholic;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates the item with the given name and other given properties.
     * These additional properties are entered through a prefix,
     * that is the first character of the given name.
     * The name of the item itself is created by the remaining letters.<br>
     * the prefix sings have the following meaning:<br>
     *
     *
     * @param name The name of the created item
     */
    public ItemT(String name)
    {
        super(name.substring(1));

        boolean alcoholic      = false;
        int     estimatedWight = 1;
        char    prefix         = name.charAt(0);
        switch (prefix)
        {
            case STANDARD:
                break;

            case DOUBLE_HAND:
                estimatedWight = 2;
                break;

            case NOT_MOVABLE:
                estimatedWight = HEAVY;
                break;

            case ALCOHOL:
                alcoholic = true;
                break;

            default:
                throw new RuntimeException(I18n.L("eITEM_PREFIX") + prefix +
                                           I18n.L("eITEM_SUFFIX"));
//                    "\nUnknown prefix value: «" + prefix + '»');
        }
        this.weight      = estimatedWight;
        this.isAlcoholic = alcoholic;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the item weight, and/or the corresponding characteristics.
     * The items that cannot be raised
     * have higher weight than the bag capacity is.
     *
     * @return Weight of the item
     */
    @Override
    public int getWeight()
    {
        return weight;
    }


    /***************************************************************************
     * Returns information if it is the alcoholic drink.
     *
     * @return If it is the alcoholic drink, it returns {@code true},
     *         otherwise it returns {@code false}
     */
    @Override
    public boolean isAlcoholic()
    {
        return isAlcoholic;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
