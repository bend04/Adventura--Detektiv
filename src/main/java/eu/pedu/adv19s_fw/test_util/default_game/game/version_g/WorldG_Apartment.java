/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.version_g;

import eu.pedu.adv19s_fw.game_txt.INamed;
import eu.pedu.adv19s_fw.game_gui.IWorldG;
import eu.pedu.adv19s_fw.test_util.default_game.I18n;
import eu.pedu.adv19s_fw.test_util.default_game.game.IMyWorld;

import java.awt.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static eu.pedu.adv19s_fw.test_util.default_game.game.version_g.ItemG.*;



/*******************************************************************************
 * {@code Apartment} instance represents the game world.
 * It is responsible for arrangement of individual places and keeps information,
 * in which place the player is just situated.
 * <p>
 * In this game the world is an apartment and its rooms are individual places.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public    class WorldG_Apartment
     implements IWorldG, IMyWorld<ItemG, PlaceG_Room>
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** The only instance (singleton) of this world (apartment). */
    private static final WorldG_Apartment SINGLETON = new WorldG_Apartment();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * The factory method returning the only existing instance of the game.
     *
     * @return The only instance of the given game
     */
    public static WorldG_Apartment getInstance()
    {
        return SINGLETON;
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** The collection of all places (mostly rooms) in this world. */
    private final Collection<PlaceG_Room> rooms;

    /** The immutable collection of all places (mostly rooms) in this world
     *  that continuously maps the {@link #rooms} collection content. */
    private final Collection<PlaceG_Room> exportedRooms;

    /** Room in which the game begins. */
    private final PlaceG_Room startingRoom;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** The place, in which the player is just situated */
    private PlaceG_Room currentPlace;


//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * The private constructor creating the only instance of the place world.
     * Within this manager definition it creates all game places.
     */
    private WorldG_Apartment()
    {
        rooms         = new ArrayList<>();

        exportedRooms = Collections.unmodifiableCollection(rooms);

        startingRoom  = new PlaceG_Room(I18n.L("HALL"),
                            new Point(550, 300),
                            new String[] {I18n.L("BEDROOM"),
                                          I18n.L("LIVING_ROOM"),
                                          I18n.L("BATHROOM")},
                            DOUBLE_HAND+I18n.L("SHOE_RACK"),
                            STANDARD   +I18n.L("UMBRELLA"));

        rooms.add(startingRoom);

        rooms.add(new PlaceG_Room(I18n.L("BATHROOM"),
                           new Point(570, 100),
                           new String[] {I18n.L("HALL")},
                           STANDARD   +I18n.L("GLASSES"),
                           STANDARD   +I18n.L("MAGAZINE"),
                           NOT_MOVABLE+I18n.L("SINK")));

        rooms.add(new PlaceG_Room(I18n.L("BEDROOM"),
                           new Point(460, 100),
                           new String[] {I18n.L("HALL")},
                           NOT_MOVABLE+I18n.L("BED"),
                           NOT_MOVABLE+I18n.L("MIRROR"),
                           STANDARD   +I18n.L("BATHROBE")));

        rooms.add(new PlaceG_Room(I18n.L("LIVING_ROOM"),
                           new Point(200, 300),
                           new String[] {I18n.L("HALL"),
                                         I18n.L("KITCHEN")},
                           DOUBLE_HAND+I18n.L("TV")));

        rooms.add(new PlaceG_Room(I18n.L("KITCHEN"),
                           new Point(200, 100),
                           new String[] {I18n.L("LIVING_ROOM"),
                                         I18n.L("BEDROOM")},
                           DOUBLE_HAND+I18n.L("ICE_BOX"),
                           STANDARD   +I18n.L("PAPER")));

        rooms.add(new PlaceG_Room(I18n.L("ICE_BOX"),
                           new Point(30, 30),
                           new String[] {},
                           ALCOHOL +I18n.L("BEER"),
                           ALCOHOL +I18n.L("BEER"),
                           ALCOHOL +I18n.L("BEER"),
                           STANDARD+I18n.L("SALAMI"),
                           STANDARD+I18n.L("BUN"),
                           ALCOHOL +I18n.L("WINE"),
                           ALCOHOL +I18n.L("RUM")));
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the collection of all places of the game.
     *
     * @return Collection of all places performing in the game
     */
    @Override
    public Collection<PlaceG_Room> getAllPlaces()
    {
        return exportedRooms;
    }


    /***************************************************************************
     * Returns the current place,
     * i.e. to the place in which the player is just situated.
     *
     * @return The place in which the player is just situated
     */
    @Override
    public PlaceG_Room getCurrentPlace()
    {
        return currentPlace;
    }


    /***************************************************************************
     * Sets the given place as the current one,
     * i.e. the place, in which the player is just situated.
     *
     * @param destinationRoom The set place
     */
    @Override
    public void setCurrentPlace(PlaceG_Room destinationRoom)
    {
        currentPlace = destinationRoom;
    }


    /***************************************************************************
     * Returns the place (room) with the given name
     * wrapped in the {@link Optional}.
     *
     * @param name Name of the required place
     * @return The wrapped place with the given name
     */
    @Override
    public Optional<PlaceG_Room> getORoom(String name)
    {
        Optional<PlaceG_Room> result = INamed.getO(name, rooms);
        return result;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * The method initializing the game world.
     * Firstly it initializes all places
     * and then it sets the initial current place.
     */
    @Override
    public void initialize()
    {
        rooms.forEach(PlaceG_Room::initialize);
        currentPlace = startingRoom;
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
