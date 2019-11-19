/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.game;

import eu.pedu.adv19s_fw.game_txt.IPlace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;



/*******************************************************************************
 * Instance třídy {@code Room} představují prostory ve hře.
 * <p>
 * V tomto programu jsou prostory místnosti v bytě (a lednička).
 *
 * @author  Dominik BENEŠ
 * @version 2019 léto
 */
class Room extends AItemContainer implements IPlace
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

    /** Názvy sousedů daného prostoru na počátku hry. */
    private final String[] neighborNames;

    /** Aktuální sousedé daného prostoru. */
    private final Collection<Room> neighbors;

    /** Nezměnitelná kolekce aktuálních sousedů daného prostoru,
     * která však průběžně mapuje obsah kolekce {@link #neighbors}. */
    private final Collection<Room> exportedNeighbors;



//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    
    /***************************************************************************
     * Vytvoří nový prostor se zadaným názvem a
     * zadanými názvy jeho počátečních sousedů a předmětů.
     *
     * @param name          Název daného prostoru
     * @param neighborNames Názvy sousedů prostoru na počátku hry
     * @param itemNames     Názvy předmětů v prostoru na počátku hry
     */
    Room(String name, String[] neighborNames, String... itemNames)
    {
        super(name, itemNames);
        this.neighborNames = neighborNames;
        this.neighbors = new ArrayList<>();
        this.exportedNeighbors = Collections.unmodifiableCollection(neighbors);
    }

    /**
     * Vrátí kolekci sousedů daného prostoru, tj. kolekci prostorů,
     * do nichž je možno se z tohoto prostoru přesunout příkazem typu
     * {@link eu.pedu.adv15p_fw.scenario.TypeOfStep#tsMOVE
     * TypeOfStep.tsMOVE}.
     *
     * @return Kolekce sousedů
     */
    @Override
    public Collection<Room> getNeighbors()
    {
        return exportedNeighbors;
    }
    
      
    /***************************************************************************
     * Metoda inicializující daný prostor.
     * Na základě konstruktorem zapamatovaných jmen
     * inicializuje sousedy daného prostoru a přítomné předměty.
     */
    void initialize()
    {
        initializeItems();
        initializeNeighbors();
    }




    /***************************************************************************
     * Vyčistí kolekci {@link #neighbors} a uloží do ní předměty reprezentující
     * prostory sousedící s daným prostorem na počátku hry.
     */
    private void initializeNeighbors()
    {
        Dum apartment = Dum.getInstance();
        neighbors.clear();
        Arrays.stream(neighborNames)
              .map(apartment::getORoom)
              .map(Optional<Room>::get)
              .forEach(neighbors::add);
    }
}
//##############################################################################
//== NESTED DATA TYPES =========================================================

