
package cz.vse.java.adventura.game;

import eu.pedu.adv19s_fw.game_txt.IItemContainer;
import eu.pedu.adv19s_fw.game_txt.INamed;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Třída vystupující jako kontejner předmětů
 * 
 * @author Dominik BENEŠ
 * @version 2019 léto
 */
abstract class AItemContainer extends ANamed implements IItemContainer{

    // Názvy předmětů v daném prostoru na počátku hry.
    private final String[] itemNames;

    // Názvy předmětů aktuálně přítomných v daném prostoru.
    private final Collection<Item> items;

    /** Nezměnitelná kolekce předmětů aktuálně přítomných v daném prostoru,
     * která však průběžně mapuje obsah kolekce {@link #items}. */
    private final Collection<Item> exportedItems;



    /**
     * Vytvoří rodičovský podobjekt kontejneru předmětů.
     *
     * @param name Název dceřiného objektu
     * @param itemNames Názvy předmětů v prostoru na počátku hry
     */
    AItemContainer(String name, String... itemNames){
        super(name);
        this.itemNames = itemNames;
        this.items = new ArrayList<>();
        this.exportedItems = Collections.unmodifiableCollection(items);

    }







    /**
     * Vrátí předmět se zadaným názvem zabalený do objektu typu
     * {@link Optional}{@code <}{@link Item}{@code >}.
     *
     * @return Předmět se zadaným názvem zabalený do objektu typu
     *         {@link Optional}{@code <}{@link Item}{@code >}
     */
    public Optional<Item> getOItem(String name){
        return INamed.getO(name, items);
    }

    /**
     * Vrátí kolekci předmětů nacházejících se v daném prostoru.
     *
     * @return Kolekce předmětů nacházejících se v daném prostoru
     */
    @Override
    public Collection<Item> getItems(){
        return exportedItems;
    }

    /**
     * Přidá zadaný předmět do kontejneru.
     *
     * @param item Předmět, který se má přidat do kontejneru
     */
    protected void addItem(Item item){
        items.add(item);
    }


    /**
     * Vyčistí kontejner a uloží do něj objekty reprezentující
     * předměty vyskytující se v daném kontejneru na počátku hry.
     */
    protected void initializeItems(){
        items.clear();
        Arrays.stream(itemNames).map(Item::new).forEach(items::add);
    }


    /**
     * Odebere zadaný předmět ze své kolekce předmětů.
     *
     * @param item Odebíraný předmět
     */
    void removeItem(Item item){
        items.remove(item);
    }
}

