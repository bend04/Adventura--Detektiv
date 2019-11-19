/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */
package cz.vse.java.adventura.game;

import java.util.Optional;

/**
 * 
 * @author Dominik BENEŠ
 */
class ActionPutDown extends AAction
{
    /**
     * Vytvoří instanci akce pro
     * odebrání předmětu z batohu a jeho vložení do aktuálního prostoru.
     */
    ActionPutDown()
    {
        super (Texts.POLOZ,
               "Přesune zadaný předmět z kapes do aktuálního prostoru.");
    }

    /**
     * Odebere předmět zadaný v parametru z batohu
     * a vloží jej do aktuálního prostoru (místnosti).
     * Vyžaduje však, aby se předmět se zadaným názvem nacházel v batohu.
     * Jinak přesun neprovede a bude příkaz považovat za chybný.
     *
     * @param arguments Parametry příkazu
     * @return Text zprávy vypsané po provedeni příkazu
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return Texts.zNEVÍM_CO_POLOŽIT;
        }
        String itemName = arguments[1];
        Hands bag = Hands.getInstance();
        Optional<Item> oItem = bag.getOItem(itemName);
        
        if (! oItem.isPresent()) {
            return Texts.zNENÍ_V_BATOHU  + itemName;
        }
             Item item = oItem.get();
        bag.removeItem(item);      
         {
        if(itemName.toLowerCase().equals(Texts.KLIC.toLowerCase())){
              State.setKey(false);  
            }
        if(itemName.toLowerCase().equals(Texts.PRISTUPOVA_KARTA.toLowerCase())){
               State.setCard(false); 
            }
        if(itemName.toLowerCase().equals(Texts.LAHVICKA.toLowerCase())){
               State.setEvidence(false); 
            } 
              
                Room  currentRoom   = Dum.getInstance().getCurrentPlace();
        currentRoom.addItem(item);
        return Texts.zPOLOŽIT + item.getName();
    }
}
}

