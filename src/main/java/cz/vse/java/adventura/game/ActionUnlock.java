
package cz.vse.java.adventura.game;

import java.util.Optional;


/**
 * 
 * @author Dominik BENEŠ
 */
class ActionUnlock extends AAction
{

    ActionUnlock()
    {
        super (Texts.ODEMKNI,
               "Odemkne zavřený trezor.");
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
            return Texts.zNEVÍM_CO_ODEMKNOUT;
        }
        String      itemName = arguments[1];
        Dum  dum = Dum.getInstance();
        Room currentRoom = dum.getCurrentPlace();
        Optional<Item> oItem = currentRoom.getOItem(itemName);
        if (! oItem.isPresent()) {
            return Texts.zNENÍ_PŘÍTOMEN;
        }
        Optional<Room> oDestinationRoom = dum.getORoom(itemName);
        if (! oDestinationRoom.isPresent()) {
            return Texts.zNENÍ_OTEVÍRATELNÝ;
        }
        if (! State.isKey()) {
            return Texts.zTREZOR_NEJDE_OTEVRIT;
        }
        State.setTrezorOpened(true);
        return Texts.zODEMKNOUT;
      }
}
