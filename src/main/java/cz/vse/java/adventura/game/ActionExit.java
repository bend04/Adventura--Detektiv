
package cz.vse.java.adventura.game;

/**
 * 
 * @author Dominik BENEŠ
 */
class ActionExit extends AAction
{
    /**
     * Vytvoří instanci akce pro
     * předčasného ukončení hry.
     */
    ActionExit()
    {
        super (Texts.KONEC,
               "Poděkuje, rozloučí se a ukončí hru.");
    }

    /***************************************************************************
     * Metoda ukončí hru a poděkuje hráči.
     *
     * @param arguments Parametry příkazu - zde se nepoužívají
     * @return Text zprávy vypsané po provedeni příkazu
     */
    @Override
    public String execute(String... arguments)
    {
        AAction.stopGame();
        return Texts.zKONEC;
    }
}

