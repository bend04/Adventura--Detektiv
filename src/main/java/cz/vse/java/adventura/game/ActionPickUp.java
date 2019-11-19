
package cz.vse.java.adventura.game;

import eu.pedu.adv19s_fw.game_txt.INamed;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 
 * @author Dominik BENEŠ
 */
class ActionPickUp extends AAction
{
    /**
     * Vytvoří instanci akce pro
     * odebrání předmětu z aktuálního prostoru a jeho vložení do batohu.
     */
    ActionPickUp(){
        super (Texts.VEZMI,
               "Přesune zadaný předmět z aktuálního prostoru do batohu.\n"
             + "Přesouvá přitom pouze zvednutelné předměty.");
    }



    /**
     * Odebere předmět zadaný v parametru z aktuálního prostoru (místnosti)
     * a vloží jej do batohu.
     * Vyžaduje však, aby se předmět se zadaným názvem<br>
     * a) nacházel v aktuálním prostoru,<br>
     * b) byl zvednutelný a<br>
     * c) vešel se do  batohu.<br>
     * Jinak přesun neprovede a bude příkaz považovat za chybný.
     *
     * @param arguments Parametry příkazu
     * @return Text zprávy vypsané po provedeni příkazu
     */
    
    @Override
    
    public String execute(String... arguments)
            { 
               if(arguments.length <2) {
                   return Texts.zNEVÍM_CO_VZÍT;
               }
        String itemName      = arguments[1];
        Room   currentRoom   = Dum.getInstance().getCurrentPlace();
        Optional<Item> oItem = INamed.getO(itemName, currentRoom.getItems());
        
        if (! oItem.isPresent()) {
             return Texts.zNENÍ_PŘÍTOMEN + itemName;
        }
        Item item = oItem.get();
        Hands bag = Hands.getInstance();
        
        if (item.getWeight() >= bag.getCapacity()){
            return Texts.zTĚŽKÝ_PŘEDMĚT + itemName;
        }
        boolean added = bag.tryAddItem(item);
        
        if (added) {
            if(itemName.toLowerCase().equals(Texts.KLIC.toLowerCase())){
              State.setKey(true);               
             }
        if(itemName.toLowerCase().equals(Texts.PRISTUPOVA_KARTA.toLowerCase())){
           State.setCard(true); 
            }
        if(itemName.toLowerCase().equals(Texts.LAHVICKA.toLowerCase())){
           State.setEvidence(true); 
            }
            currentRoom.removeItem(item);
            return Texts.zZVEDNUTO + itemName;
        }
        else {
            return Texts.zBATOH_PLNÝ;
        }
            }


           }
  
    
