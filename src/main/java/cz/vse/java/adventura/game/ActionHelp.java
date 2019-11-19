/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */
package cz.vse.java.adventura.game;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 
 * @author Dominik BENEŠ
 */
class ActionHelp extends AAction
{
    /**
     * Vytvoří instanci akce pro
     * vypsání nápovědy s názvy a stručnými popisy dostupných příkazů.
     */
    ActionHelp()
    {
        super(Texts.HELP,"Vypíše dostupné příkazy, které se v průběhu hry\n"
                + "dají zadat.\n")
              ;
    }

    /**
     * Metoda vrátí text nápovědy sestávající
     * z názvů a stručných popisů dostupných příkazů.
     *
     * @param arguments Parametry příkazu - zde se nepoužívají
     * @return Text zprávy vypsané po provedeni příkazu
     */
    @Override
    public String execute(String... arguments)
    {
        Collection<AAction> actions = getAllActions();
        String result = actions.stream()
            .map(action -> action.getName() + '\n'
                         + action.getDescription())
            .collect(Collectors.joining("\n\n", Texts.zNÁPOVĚDA, ""));
        return result;
    }
}
