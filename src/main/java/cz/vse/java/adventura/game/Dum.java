/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */
package cz.vse.java.adventura.game;

import eu.pedu.adv19s_fw.game_txt.IWorld;
import eu.pedu.adv19s_fw.game_txt.INamed;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;


import static cz.vse.java.adventura.game.Texts.*;
import static cz.vse.java.adventura.game.Item.*;




/**
 *
 * @author Dominik BENEŠ
 */
class Dum implements IWorld
{
    /** Odkaz na jedinou instanci (jedináčka) této hry. */
    private static final Dum SINGLETON = new Dum();


    /** Aktuální sousedé daného prostoru. */
    private final Collection<Room> rooms;

    /** Nezměnitelná kolekce aktuálních sousedů daného prostoru,
     * která však průběžně mapuje obsah kolekce {@link #neighbors}. */
    private final Collection<Room> exportedRooms;

    /** Místnost, v níž hra začíná. */
    private final Room startingRoom;


    private Room currentArea;


    /**
     * Tovární metoda vracející odkaz na jedninou existující instanci dané hry.
     *
     * @return Instance dané hry
     */
    static Dum getInstance()
    {
        return SINGLETON;
    }


    /**
     * Soukromý konstruktor definující jedinou instanci správce prostorů.
     * V rámci definice správce vytvoří i všechny prostory hry.
     */
    public Dum()
    {
        rooms         = new ArrayList<>();
        exportedRooms = Collections.unmodifiableCollection(rooms);
        startingRoom = new Room(PREDSIN,
                new String[] {KUCHYN, KOUPELNA, LOZNICE},
                NOT_MOVABLE+SATNIK, STANDARD+BOTA,
                STANDARD+TELEFON, STANDARD+BUNDA);
        rooms.add(startingRoom);
        rooms.add(new Room(KOUPELNA,
                new String[] {OBYVAK, PREDSIN },
                NOT_MOVABLE+KOS, NOT_MOVABLE+SKRINKA, STANDARD+ LAHVICKA));
        rooms.add(new Room(OBYVAK,
                new String[] {KUCHYN, KOUPELNA},
                NOT_MOVABLE+MRTVOLA, STANDARD+KLIC));
        rooms.add(new Room(KUCHYN,
                new String[] {OBYVAK, PREDSIN, PRACOVNA, ZAHRADA},
                NOT_MOVABLE+SLUZKA, STANDARD+KRVAVY_NUZ)
        );
        rooms.add(new Room(PRACOVNA,
                new String[] {KUCHYN},
                NOT_MOVABLE+TREZOR, NOT_MOVABLE+STUL));
        rooms.add(new Room(ZAHRADA,
                new String[] {KUCHYN, KULNA},
                NOT_MOVABLE+ZAHRADNIK, STANDARD+ZLOMENE_HRABE));
        rooms.add(new Room(KULNA,
                new String[] {ZAHRADA, BEZPECNOSTNI_MISTNOST },
                STANDARD+KLADIVO, NOT_MOVABLE+SEKACKA));
        rooms.add(new Room(BEZPECNOSTNI_MISTNOST,
                new String[] {KULNA },
                NOT_MOVABLE+POCITAC));
        rooms.add(new Room(LOZNICE,
                new String[] {PREDSIN, PRACOVNA },
                NOT_MOVABLE+MANZELKA,NOT_MOVABLE+NOCNI_STOLEK,NOT_MOVABLE+SATNIK));
        rooms.add(new Room(TREZOR,
                new String[] {},
                STANDARD+PRISTUPOVA_KARTA, STANDARD+PENIZE,
                STANDARD+DOKUMENTY));
        //         currentArea = startingRoom;
    }

    /**
     * Vrátí kolekci odkazů na všechny prostory vystupující ve hře.
     *
     * @return Kolekce odkazů na všechny prostory vystupující ve hře
     */
    @Override
    public Collection<Room> getAllPlaces()
    {
        return exportedRooms;
    }


    /**
     * Vrátí odkaz na aktuální prostor,
     * tj. na prostor, v němž se hráč pravé nachází.
     *
     * @return Prostor, v němž se hráč pravé nachází
     */
    @Override
    public Room getCurrentPlace()
    {
        return currentArea;
    }

    void setCurrentPlace(Room destinationRoom)
    {
        currentArea = destinationRoom;
    }


    /***************************************************************************
     * Vrátí prostor se zadaným názvem zabalený v objektu typu {@link Optional}.
     *
     * @param name Název požadovaného prostoru
     * @return Zabalený prostor se zadaným názvem
     */
    public Optional<Room> getORoom(String name)
    {
        Optional<Room> result = INamed.getO(name, rooms);
        return result;
    }

    /***************************************************************************
     * Metoda inicializující svět hry.
     * Nejprve inicializuje všechny prostory
     * a pak nastaví výchozí aktuální prostor.
     */
    void initialize()
    {
        rooms.forEach(Room::initialize);
        currentArea = startingRoom;
    }


}
