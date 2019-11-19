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
class ActionAccuse extends AAction
{

    ActionAccuse()
    {
        super (Texts.OBVINIT,
               "Na základě Vámi nalezených důkazů obviní osobu v domě.\n"
                +"Rozhodne zda jste se rozhodl správně či nikoli.");
        
        
        
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
            return Texts.zNEVÍM_KOHO_OBVINIT;
        }
        String itemName = arguments[1];
        Dum  dum = Dum.getInstance();
        Room currentRoom = dum.getCurrentPlace();
        Optional<Item> oItem = currentRoom.getOItem(itemName);
        
        if (! oItem.isPresent()) {
            return Texts.zOSOBA_TU_NENÍ;
        }
        
        if (Texts.MANZELKA.equalsIgnoreCase(itemName)) {
            if(State.isEvidence() && State.isSearch1() && State.isSearch2() 
                    && State.isEvidenceInspect() ){
                AAction.stopGame();
                return Texts.zOBVINIT_SPRÁVNĚ;

                                 
                
                
        }
        }
        
        if (Texts.ZAHRADNIK.equalsIgnoreCase(itemName)) {
            if(State.isEvidence() && State.isSearch1() && State.isSearch2() 
                    && State.isEvidenceInspect() ){
                AAction.stopGame();
                return Texts.zOBVINIT_ŠPATNĚ;
                
        }
        }
        
        if (Texts.SLUZKA.equalsIgnoreCase(itemName)) {
            if(State.isEvidence() && State.isSearch1() && State.isSearch2() 
                    && State.isEvidenceInspect() ){
                AAction.stopGame();
                return Texts.zOBVINIT_ŠPATNĚ;
                
        }
        }
                       
        return Texts.zNEDOSTATEK_DUKAZU;
            }
}

   

        
            
    

