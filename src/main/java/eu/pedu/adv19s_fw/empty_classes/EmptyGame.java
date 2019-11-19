/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.empty_classes;

import eu.pedu.adv19s_fw.game_txt.BasicActions;
import eu.pedu.adv19s_fw.game_txt.IAction;
import eu.pedu.adv19s_fw.game_txt.IBag;
import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IWorld;
import eu.pedu.adv19s_fw.utilities.UncompletedMethodException;

import java.util.Collection;



/*******************************************************************************
 * Instance třídy {@code EmptyGame} mají na starosti logiku hry.
 * Jsou schopny akceptovat jednotlivé příkazy a poskytovat informace
 * o průběžném stavu hry a jejích součástí.
 * <p>
 * Třída hry musí být definována jako jedináček (singleton)
 * a kromě metod deklarovaných v interfejsu {@link IGame} musí definovat
 * statickou tovární metodu {@code getInstance()}.
 * Splnění této podmínky nemůže prověřit překladač,
 * ale prověří ji až následné testy hry.
 * <p>
 * Instance třídy {@code EmptyGame} představují prototypy instancí hry,
 * které ještě nejsou schopny plnohodnotného spuštění a slouží pouze
 * ke kompletaci těch vlastností správce scénářů, které bude v budoucnu
 * plnit ve spolupráci s plnohodnotnou hrou.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public   class EmptyGame
       extends ANamed
    implements IGame, IAuthorPrototype
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Odkaz na jedinou instanci (jedináčka) této hry. */
    private static final EmptyGame SINGLETON = new EmptyGame();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Tovární metoda vracející odkaz na jedninou existující instanci dané hry.
     *
     * @return Instance dané hry
     */
    public static EmptyGame getInstance()
    {
        return SINGLETON;
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Soukromý konstruktor definující jedinou instanci.
     * Protože je soukromý, musí být definován, i když má prázdné tělo.
     */
    private EmptyGame()
    {
        super("Název hry");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí informaci o tom, je-li hra aktuálně spuštěná.
     * Spuštěnou hru není možno pustit znovu.
     * Chceme-li hru spustit znovu, musíme ji nejprve ukončit.
     *
     * @return Je-li hra spuštěná, vrátí {@code true},
     *         jinak vrátí {@code false}
     */
    @Override
    public boolean isAlive()
    {
        //TODO EmptyGame.isAlive - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Vrátí odkaz na batoh, do nějž bude hráč ukládat sebrané předměty.
     *
     * @return Batoh, do nějž hráč ukládá sebrané předměty
     */
    @Override
    public IBag getBag()
    {
        //TODO EmptyGame.getBag - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Vrátí kolekci všech příkazů použitelných ve hře.
     *
     * @return Kolekce všech příkazů použitelných ve hře
     */
    @Override
    public Collection<? extends IAction> getAllActions()
    {
        //TODO EmptyGame.getAllActions - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Vrátí odkaz na přepravku s názvy povinných příkazů, tj. příkazů pro
     * <ul>
     *   <li>přesun hráče do jiného prostoru,</li>
     *   <li>zvednutí objektu (odebrání z prostoru a vložení do batohu),</li>
     *   <li>položení objektu (odebrání z batohu a vložení do prostoru),</li>
     *   <li>vyvolání nápovědy,</li>
     *   <li>okamžité ukončení hry.</li>
     * </ul>
     *
     * @return Přepravka názvy povinných příkazů
     */
    @Override
    public BasicActions getBasicActions()
    {
        //TODO EmptyGame.getBasicActions - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Vrátí odkaz na svět, v němž se hra odehrává.
     *
     * @return Svět, v němž se hra odehrává
     */
    @Override
    public IWorld getWorld()
    {
        //TODO EmptyGame.getWorld - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Zpracuje zadaný příkaz a vrátí text zprávy pro uživatele.
     *
     * @param command Zadávaný příkaz
     * @return Textová odpověď hry na zadaný příkaz
     */
    @Override
    public String executeCommand(String command)
    {
        //TODO EmptyGame.executeCommand(String) - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Ukončí celou hru a vrátí alokované prostředky.
     */
    @Override
    public void stop()
    {
        //TODO EmptyGame.stop - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
