/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.version_g;

import eu.pedu.adv19s_fw.game_gui.IPlaceG;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyPlace;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;



/*******************************************************************************
//%L+ CZ
 * Instance třídy {@code PlaceG_Room} představují jednotlivé prostory hry;
 * v této hře jsou těmito prostory místnosti bytu a lednička.
//%Lx EN
 * The {@code PlaceG_Room} instances represent the places in the game;
 * in this program the places are the apartment rooms and the ice-box.
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public   class PlaceG_Room
       extends AItemContainerG
    implements IPlaceG, IMyPlace<ItemG, PlaceG_Room>
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

//%L+CZ
    /** Sousedé místnosti na začátku hry. */
    /** Names of neighbors of the given place at the game beginning. */
    private final String[] neighborNames;

    /** Current neighbors of the given place. */
    private final Collection<PlaceG_Room> neighbors;

    /** Immutable collection of current neighbors of the given place,
     *  that continuously maps the {@link #neighbors} collection content. */
    private final Collection<PlaceG_Room> exportedNeighbors;

    /** Pozice místnosti na plánku hry. */
    private final Point position;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates a new place with the given name and
     * with the given names of its initial neighbors and items.
     *
     * @param name          Name of the given place
     * @param neighborNames Names of place neighbors at the game beginning
     * @param itemNames     Names of items in the place at the game beginning
     */
    PlaceG_Room(String name, Point position,
                String[] neighborNames, String... itemNames)
    {
        super(name, itemNames);
        this.position         = position;
        this.neighborNames    = neighborNames;
        this.neighbors        = new ArrayList<>();
        this.exportedNeighbors= Collections.unmodifiableCollection(neighbors);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the collection of current neighbors of this place, i.e. the
     * collection of places, to which we can move from this place with the
     * command of the {@link eu.pedu.adv19s_fw.scenario.TypeOfStep#tsMOVE
     * TypeOfStep.tsMOVE} type.
     *
     * @return Collection of neighbors
     */
    @Override
    public Collection<PlaceG_Room> getNeighbors()
    {
        return exportedNeighbors;
    }


    /***************************************************************************
     * Vrátí pozici místnosti na plánu hry,
     * přesněji pozici hráče nacházejícího se v dané místnosti.
     * Na této pozici bude hráč při pobytu v dané místnosti zobrazen.
     *
     * @return Požadovaná pozice
     */
    @Override
    public Point getPosition()
    {
        return position;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Method initializing the given place.
     * Based on the names remembered by the constructor
     * it initializes the neighbors of the given place and its items.
     */
    @Override
    public void initialize()
    {
        initializeItems();
        initializeNeighbors();
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Cleans the collection {@link #neighbors} and saves into it the items
     * representing the places neighboring with the given place
     * at the game beginning.
     */
    private void initializeNeighbors()
    {
        WorldG_Apartment apartment = WorldG_Apartment.getInstance();
        neighbors.clear();
        Arrays.stream(neighborNames)
              .map(apartment::getORoom)
              .map(Optional<PlaceG_Room>::get)
              .forEach(neighbors::add);
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
