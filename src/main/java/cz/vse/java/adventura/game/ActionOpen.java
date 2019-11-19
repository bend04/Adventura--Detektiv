/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */
package cz.vse.java.adventura.game;

import java.util.Optional;



/*******************************************************************************
 * Instance třídy {@code ActionOpen} zpracovávají příkazy, které
 * mají za úkol otevřít nějaký předmět.
 * V dané hře je tímto předmětem vždy lednička v kuchyni,
 * ale obecně lze hru rozšířit o možnost otevření dalších předmětů
 * v dalších prostorech.
 *
 * @author  Dominik BENEŠ
 * @version 2019 léto
 */
class ActionOpen extends AAction
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
//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří instanci akce pro
     * přesunutí otevření předmětu, který je možno otevřít
     * (po zkontrolování definovaných podmínek)
     * a po otevření se stane aktuálním prostorem.
     */
    ActionOpen()
    {
        super (Texts.OTEVRI,
               "Ověří, že hráč chce opravdu otevřít otevřitelný předmět,\n"
             + "zadaný jako parametr příkazu.\n"
             + "Otevírá-li trezor, tak zkontroluje, je-li odemčený.\n"
             + "Jsou-li všechny podmínky splněny,"
             + "přesune hráče do prostoru daného předmětu.\n"
             + "Nebudou-li podmínky splněny, bude příkaz považovat za chybný");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Otevře zadaný předmět a přesune do něj hráče jako do nového prostoru.
     * Před tím však zkontroluje,
     * <ul>
     * <li>zda je zadán předmět, který se má otevřít,</li>
     * <li>zda je tento předmět v aktuálním prostoru,</li>
    * <li>zda je tento předmět otevíratelný, tj. zda je současně prostorem,</li>
     * <li>zda je trezor již odemknut.</li>
     * </ul>
     *
     * @param parametry Jediným povoleným parametrem je zatím lednička
     * @return Text zprávy vypsané po provedeni příkazu
     */
    @Override
        public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return Texts.zNEVÍM_CO_OTEVŘÍT;
        }
        String      itemName = arguments[1];
        Dum  dum = Dum.getInstance();
        Room     currentRoom = dum.getCurrentPlace();
        Optional<Item> oItem = currentRoom.getOItem(itemName);
        if (! oItem.isPresent()) {
            return Texts.zNENÍ_PŘÍTOMEN;
        }
        Optional<Room> oDestinationRoom = dum.getORoom(itemName);
        if (! oDestinationRoom.isPresent()) {
            return Texts.zNENÍ_OTEVÍRATELNÝ;
        }
        if (! State.isTrezorOpened()) {
            return Texts.zTREZOR_NEJDE_OTEVRIT;
        }
        dum.setCurrentPlace(oDestinationRoom.get());
        return Texts.zOTEVREL_TREZOR;
    }
        }
       



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================

