/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.game;

import eu.pedu.adv19s_fw.game_txt.IBag;
import cz.vse.java.adventura.game.AItemContainer;

import java.util.*;


/*******************************************************************************
 * Instance třídy {@code EmptyBag} představuje úložiště,
 * do nějž hráči ukládají předměty sebrané v jednotlivých prostorech,
 * aby je mohli přenést do jiných prostorů a/nebo použít.
 * Úložiště má konečnou kapacitu definující maximální povolený
 * součet vah předmětů vyskytujících se v úložišti.
 * * <p>
 * V této hře jsou tímto úložištěm ruce hráče s kapacitou 2.
 * Váha předmětu představuje počet rukou, které jsou potřeba k jeho zvednutí.
 *
 * @author  Dominik BENEŠ
 * @version 2019 léto
 */
class Hands  extends AItemContainer implements  IBag
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    /** Kapacita batohu. */
    public static final int CAPACITY = 2;

    /** Jediná instance batohu ve hře. */
    private static final Hands SINGLETON = new Hands();


    private static boolean volno;
    /** Volná kapacit batohu. */
    public int remains;



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Tovární metoda vracející odkaz na jedninou existující instanci dané hry.
     *
     * @return Instance dané hry
     */
    static Hands getInstance()
    {
        return SINGLETON;
    }


   
    /**
     * prázdný konstruktor
     */
    Hands(){
        super("Kapsy");
    }






    @Override
    public int getCapacity(){
        return CAPACITY;
    }

    /**
     * Metoda inicializující batoh.
     * Protože v této hře má hráč na počátku hry prázdný batoh,
     * stačí pouze vyčistit kolekci {@link #
     */
    public void initialize()
    {
        initializeItems();
        remains = CAPACITY;
    }
    
    /***************************************************************************
     * Odebere zadaný předmět z batohu
     * a příslušně zvětší volnou kapacitu batohu.
     *
     * @param item Odebíraný předmět
     */
    @Override
    void removeItem(Item item)
    {
        super.removeItem(item);
        remains += item.getWeight();
    }

    /**
     * Vejde-li se zadaný předmět do batohu, přidá jej;
     * poté vrátí zprávu o výsledku.
     *
     * @param item Předmět, který se má přidat do batohu
     * @return Zpráva o výsledku: {@code true} = byl přidán,
     *         {@code false} = nebyl přidán
     */
    public boolean tryAddItem(Item item)
    {

        if (item.getWeight() > remains) {
            return false;
        }
        addItem(item);
        remains -= item.getWeight();
        volno = true;
        return true;
    }


}
