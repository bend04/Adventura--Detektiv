
package cz.vse.java.adventura.game;
import eu.pedu.adv19s_fw.game_txt.INamed;

import java.util.Optional;

/**
 * 
 * @author Dominik BENEŠ
 */
class ActionMove extends AAction
{
    /**
     * Vytvoří instanci akce pro
     * přesunutí hráče z aktuálního prostoru do zadaného sousedního prostoru.
     */
    ActionMove()
    {
        super (Texts.JDI,
               "Přesune hráče do sousední místnosti zadané v parametru.\n"
             + "Vyžaduje však, aby tato místnost byla sousedem aktuální "
             + "místnosti,\njinak bude příkaz považovat za chybný");
    }

    /***************************************************************************
     * Přesune hráče prostoru (místnosti) zadaného v parametru.
     * Vyžaduje však, aby tento prostoru byl sousedem aktuálního prostoru,
     * protože jinak přesun neprovede a bude příkaz považovat za chybný.
     *
     * @param arguments Parametry příkazu
     * @return Text zprávy vypsané po provedeni příkazu
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return Texts.zNEVÍM_KAM_JÍT;
        }
        String destinationName = arguments[1];
        Room   currentRoom     = Dum.getInstance().getCurrentPlace();
        Optional<Room> oDestination = INamed.getO(destinationName,
                                                  currentRoom.getNeighbors());
        if (! oDestination.isPresent()) {
            return Texts.zNENÍ_SOUSEDEM + destinationName;
        }
        Room destinationRoom = oDestination.get();
        Dum.getInstance().setCurrentPlace(destinationRoom);
        return Texts.zPŘESUN + destinationRoom.getName() +
                "\r\nPředměty v této místnosti: "  + 
                Dum.getInstance().getCurrentPlace().getItems()+
                "\r\nSousední místnosti: " + 
                Dum.getInstance().getCurrentPlace().getNeighbors();
                
    }
}
