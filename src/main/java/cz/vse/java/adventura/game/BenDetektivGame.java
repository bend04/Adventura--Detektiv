/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.game;

import cz.vse.java.adventura.DetGSMFactory;
import cz.vse.java.adventura.IAuthorDB;
import eu.pedu.adv19s_fw.game_txt.BasicActions;
import eu.pedu.adv19s_fw.game_txt.IGame;

import java.util.Collection;



/*******************************************************************************
 * Instance třídy {@code RUPApartmentGame} mají na starosti logiku hry.
 * Jsou schopny akceptovat jednotlivé příkazy a poskytovat informace
 * o průběžném stavu hry a jejích součástí.
 * <p>
 * Třída hry musí být definována jako jedináček (singleton)
 * a kromě metod deklarovaných v interfejsu {@link IGame} musí definovat
 * statickou tovární metodu {@code getInstance()}.
 * Splnění této podmínky nemůže prověřit překladač,
 * ale prověří ji až následné testy hry.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2015-Podzim — 15.55.5837 — 2015-11-25
 */
public class BenDetektivGame extends ANamed implements IGame, IAuthorDB
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    /** Tovární třída, jejíž instancemi jsou tovární objekty poskytující
     *  instanci správce scénářů i hry, jejíž scénáře daný správce spravuje. */
    private final static Class<DetGSMFactory> FACTORY_CLASS =
                                                      DetGSMFactory.class;
                                                      
private static final BasicActions BASIC_ACTIONS =
new BasicActions(Texts.JDI,  Texts.VEZMI, Texts.POLOZ,
Texts.HELP, Texts.KONEC);                                                  

    /** Odkaz na jedinou instanci (jedináčka) této hry. */
    private static final BenDetektivGame SINGLETON = new BenDetektivGame();



//== VARIABLE CLASS ATTRIBUTES =================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Tovární metoda vracející odkaz na jedninou existující instanci dané hry.
     *
     * @return Instance dané hry
     */
    public static BenDetektivGame getInstance()
    {
        return SINGLETON;
    }


    /***************************************************************************
     * Soukromý konstruktor definující jedinou instanci třídy hry.
     */
    public BenDetektivGame()
    {
        super("Ukázková hra: Deketivní hra");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí class-objekt tovární třídy, jejíž instance umějí zprostředkovat
     * získání všech klíčových objektů aplikace.
     *
     * @return Class-objekt tovární třídy
     */
    @Override
    public Class<DetGSMFactory> getFactoryClass()
    {
        return FACTORY_CLASS;
    }


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
        return AAction.isAlive();
    }


     /** Vrátí odkaz na batoh, do nějž bude hráč ukládat sebrané předměty.
     *
     * @return Batoh, do nějž hráč ukládá sebrané předměty
     */
    @Override
    public Hands getBag()
    {
        return Hands.getInstance();
    }




    /** Vrátí kolekci všech akcí použitelných ve hře.
     *
     * @return Kolekce všech akcí použitelných ve hře
     */
    @Override
    public Collection<AAction> getAllActions()
    {
        return AAction.getAllActions();
    }
    /***************************************************************************
     * Vrátí odkaz na přepravku s názvy povinných akcí, tj. akcí pro
     * <ul>
     *   <li>přesun hráče do jiného prostoru,</li>
     *   <li>zvednutí předmětu (odebrání z prostoru a vložení do batohu),</li>
     *   <li>položení předmětu (odebrání z batohu a vložení do prostoru),</li>
     *   <li>vyvolání nápovědy,</li>
     *   <li>okamžité ukončení hry.</li>
     * </ul>
     *
     * @return Přepravka názvy povinných akcí
     */

    /***************************************************************************
     * Vrátí odkaz na svět, v němž se hra odehrává.
     *
     * @return Svět, v němž se hra odehrává
     */    
    @Override
    public BasicActions getBasicActions()
    {
        return BASIC_ACTIONS;
    }


    /***************************************************************************
     * Vrátí odkaz na svět, v němž se hra odehrává.
     *
     * @return Svět, v němž se hra odehrává
     */
    @Override
    public Dum getWorld()
    {
        return Dum.getInstance();
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Zpracuje zadaný příkaz a vrátí text zprávy pro uživatele.
     * Vlastní zpracování příkazu ale deleguje na správce akcí,
     * kterým je objekt třídy {@link AAction}.
     *
     * @param command Zadávaný příkaz
     * @return Textová odpověď hry na zadaný příkaz
     */
    @Override
    public String executeCommand(String command)
    {
        return AAction.executeCommand(command);
    }


    /***************************************************************************
     * Ukončí celou hru a vrátí alokované prostředky.
     */
    @Override
    public void stop()
    {
         AAction.stopGame();
    }




//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
