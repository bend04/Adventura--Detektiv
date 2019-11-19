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
class ActionInspect extends AAction
{

    ActionInspect()
    {
        super (Texts.PROHLEDNI,
               "Prohlédne danou osobu a dá nám info o osobě");
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
            return Texts.zNEVÍM_KOHO_PROHLEDNOUT;
        }
        String itemName = arguments[1];
        Dum  dum = Dum.getInstance();
        Room currentRoom = dum.getCurrentPlace();
        Optional<Item> oItem = currentRoom.getOItem(itemName);
        
        if (! oItem.isPresent()) {
            return Texts.zOSOBA_TU_NENÍ;
        }
        
        if (Texts.MANZELKA.equalsIgnoreCase(itemName)) {
            State.setEvidenceInspect(true);
            return  Texts.zCHCE_PROHLEDNOUT_MANZELKA;
        }
        
        if (Texts.ZAHRADNIK.equalsIgnoreCase(itemName)) {
            return  Texts.zCHCE_PROHLEDNOUT_ZAHRADNIK;
        }
        
        if (Texts.SLUZKA.equalsIgnoreCase(itemName)) {
            return  Texts.zCHCE_PROHLEDNOUT_SLUZKA;
        }
         
                
        return Texts.zPROHLEDNOUT_JEN_OSOBY;
       
    }
}

