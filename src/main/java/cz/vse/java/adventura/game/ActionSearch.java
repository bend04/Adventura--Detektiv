/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.game;


import java.util.Optional;


/*******************************************************************************
 * The {@code ActionSearch} class instances represent ...
 *
 * @author  Dominik Beneš
 *
 */
/**
 * 
 * @author Dominik BENEŠ
 */
class ActionSearch extends AAction
{

    ActionSearch()
    {
        super (Texts.PROHLEDEJ,
               "Prohledá daný objekt");
    }

    /**
     * 
     * @param arguments
     * @return 
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return Texts.zNEVÍM_CO_PROHLEDAT;
        }
        String itemName = arguments[1];
        Dum  dum = Dum.getInstance();
        Room currentRoom = dum.getCurrentPlace();
        Optional<Item> oItem = currentRoom.getOItem(itemName);
        
        if (! oItem.isPresent()) {
            return Texts.zNEJDE_PROHLEDAT;
        }
        
        if (Texts.MRTVOLA.equalsIgnoreCase(itemName)) {
            State.setSearch1(true);
            return  Texts.zINFO_MRTVOLA;
        }
         
        if (Texts.POCITAC.equalsIgnoreCase(itemName)) {
             if (! State.isCard()){
                     return Texts.zPOTŘEBA_KARTA;
                     }                          
             State.setSearch2(true);
            return Texts.zINFO_POCITAC;
        }
        
        return Texts.zNIC;
       
    }
}

