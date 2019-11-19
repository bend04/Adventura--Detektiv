/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.version_t;

import eu.pedu.adv19s_fw.game_txt.IBag;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyBag;



/*******************************************************************************
 * {@code Hands} instances represents the repository,
 * to which the players store the items picked up in individual places,
 * so that they could be moved to other places and/or used.
 * The disposal site has a final capacity defining the maximal permitted
 * sum of weights of items occuring in the repository.
 * * <p>
 * In this game the disposal site are the player hands with capacity 2.
 * The item weight represents the number of hands needed for its taking.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public    class BagT_Hands
        extends AItemContainerT
    implements IMyBag<ItemT>
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Capacity of the bag. */
    static final int CAPACITY = 2;

    /** The only instance of the bag in the game. */
    private static final BagT_Hands SINGLETON = new BagT_Hands();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Factory method returning the only existing instance of the game.
     *
     * @return The instance of the given game
     */
    public static BagT_Hands getInstance()
    {
        return SINGLETON;
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Free capacity of the bag. */
    private int remains;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     */
    private BagT_Hands()
    {
        super("Bag-Hands");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the bag capacity, i.e. the maximal permitted sum
     * of weights of items, that can be put into the bag at the same time.
     *
     * @return Capacity of the bag
     */
    @Override
    public int getCapacity()
    {
        return CAPACITY;
    }


    /***************************************************************************
     * Returns information if the bag is full,
     * or if some item can be still put in.
     *
     * @return {@code true} if it is full, {@code false} otherwise
     */
    @Override
    public boolean isFull()
    {
        return remains == 0;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * The method initializing the bag.
     * As the player of this game has an empty bag at the game beginning,
     * this method can only clean the {@link #items} collection.
     */
    @Override
    public void initialize()
    {
        initializeItems();
        remains = CAPACITY;
    }


    /***************************************************************************
     * Removes the given item from the bag
     * and increases the free capacity of the bag.
     *
     * @param item Item taken away
     */
    @Override
    public void removeItem(ItemT item)
    {
        super.removeItem(item);
        remains += item.getWeight();
    }


    /***************************************************************************
     * If the given item fits to the bag, it adds it;
     * after that it returns the message on the result.
     *
     * @param item The item that has to be added into the bag
     * @return The message on the result: {@code true} = was added,
     *         {@code false} = was not added
     */
    @Override
    public boolean tryAddItem(ItemT item)
    {
        if (item.getWeight() > remains) {
            return false;
        }
        addItem(item);
        remains -= item.getWeight();
        return true;
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
