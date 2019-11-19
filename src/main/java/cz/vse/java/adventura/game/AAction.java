/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.game;

import eu.pedu.adv19s_fw.game_txt.IAction;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IPlace;
import eu.pedu.adv19s_fw.game_txt.IWorld;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



/*******************************************************************************
 * Třída {@code AAction} je společným rodičem všech tříd, jejichž instance
 * mají na starosti interpretaci příkazů zadávaných uživatelem hrajícím hru.
 * Název spouštěné akce bývá většinou první slovo řádku zadávaného
 * z klávesnice a další slova pak bývají interpretována jako parametry.
 * <p>
 * Můžete ale definovat příkaz, který odstartuje konverzaci
 * (např. s osobou přítomnou v místnosti) a tím přepne systém do režimu,
 * v němž se zadávané texty neinterpretují jako příkazy,
 * ale předávají se definovanému objektu až do chvíle,
 * kdy uživatel rozhovor ukončí a objekt rozhovoru přepne hru zpět
 * do režimu klasických příkazů.
 *
 * @author  Dominik Beneš

 */
abstract class AAction extends ANamed implements IAction
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    /** Mapa zprostředkovávající převod názvu akce na její instanci. */
    public static final Map<String, AAction> NAME_2_ACTION;



//== VARIABLE CLASS ATTRIBUTES =================================================

    /** Uchovává informaci o tom, zda se hra právě hraje
     *  nebo jen čeká na spuštění. */
    public static boolean isAlive;


//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================

    static {
        isAlive = false;
        NAME_2_ACTION = new HashMap<>();

        new ActionHelp();
        new ActionMove();
        new ActionPickUp();
        new ActionPutDown();
        new ActionExit();

        new ActionInspect();
        new ActionSearch();
        new ActionUnlock();
        new ActionAccuse();
        new ActionOpen();
        new ActionClose();
    }



//== CLASS GETTERS AND SETTERS =================================================
    /***************************************************************************
     * Vrátí informaci o tom, je-li hra aktuálně spuštěná.
     * Spuštěnou hru není možno pustit znovu.
     * Chceme-li hru spustit znovu, musíme ji nejprve ukončit.
     *
     * @return Je-li hra spuštěná, vrátí {@code true},
     *         jinak vrátí {@code false}
     */
    public static boolean isAlive()
    {
        return isAlive;
    }

    /**
     * Vrátí kolekci všech akcí použitelných ve hře.
     *
     * @return Kolekce všech akcí použitelných ve hře
     */
    static Collection<AAction> getAllActions(){
        Collection<AAction> collection, result;
        collection = NAME_2_ACTION.values();
        result = Collections.unmodifiableCollection(collection);
        return result;
    }


//== OTHER NON-PRIVATE CLASS METHODS ===========================================

    /***************************************************************************
     * Zpracuje zadaný příkaz a vrátí text zprávy pro uživatele.
     *
     * @param command Zadávaný příkaz
     * @return Textová odpověď hry na zadaný příkaz
     */
    public static String executeCommand(String command){
        command = command.trim();
        String answer;
        if (isAlive) {
            answer = executeCommonComand(command);
        }
        else {
            answer = startGame(command);
        }
        return answer;
    }

    /* Zjistí, jaká akce má být zadaným příkazem aktivována,
     * a jedná-li se o známou akci, provede ji.
     *
     * @param command Zadávaný příkaz
     * @return  Odpověď hry na zadaný příkaz
     */
    public static String executeCommonComand(String command){
        if (command.isEmpty()) {
            return Texts.zPRÁZDNÝ_PŘÍKAZ
                    ;
        }
        String[] words = command.toLowerCase().split("\\s+");
        String   acttionName = words[0];
        AAction  action = NAME_2_ACTION.get(acttionName);
        String   answer;
        if (action == null) {
            answer = Texts.zNEZNÁMÝ_PŘÍKAZ
            ;
        }
        else {
            answer = action.execute(words)
            ;
        }
        return answer;
    }



    /** Zabezpečí korektní ukončení hry.
     * Zapamatuje si, že hra je již ukončena.
     */
    static void stopGame()		{
        isAlive = false;
    }

    /***************************************************************************
     * Metoda postupně inicializuje všechny klíčové objekty hry.
     */
    private static void initialize()
    {
        Dum.getInstance().initialize();
        Hands.getInstance().initialize();
        State.initialize();
    }

    /***************************************************************************
     * Zjistí, jaká akce má být zadaným příkazem aktivována,
     * a jedná-li se o známou akci, provede ji.
     *
     * @param command Zadávaný příkaz
     * @return  Odpověď hry na zadaný příkaz
     */



    /***************************************************************************
     * Ověří, jestli je hra spouštěna správným (= prázdným) příkazem,
     * a pokud ano, spustí hru.
     *
     * @param command Příkaz spouštějící hru
     * @return  Odpověď hry na zadaný příkaz
     */



    /**
     * Ověří, jestli je hra spouštěna správným (= prázdným) příkazem,
     * a pokud ano, spustí hru.
     *
     * @param command Příkaz spouštějící hru
     * @return  Odpověď hry na zadaný příkaz
     */
    public static String startGame(String command){
        Room currentPlace = Dum.getInstance().getCurrentPlace();
        String answer;
        if (command.isEmpty()) {
            initialize();
            answer  = Texts.zCELÉ_UVÍTÁNÍ;

            isAlive = true;
        }
        else {
            answer = Texts.zNENÍ_START ;

        }
        return answer;
    }




//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    /** Stručný popis dané akce. */
    private final String description;



//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří rodičovský podobjekt vytvářené akce.
     *
     * @param name  Název vytvářené akce = text, který musí hráč zadat
     *              jako počáteční slovo zadávaného příkazu
     * @param description Stručný popis vytvářené akce
     */
    AAction(String name, String description)
    {
        super(name);
        this.description = description;
        AAction previous = NAME_2_ACTION.put(name.toLowerCase(), this);
        if (previous != null) {
            throw new IllegalArgumentException(
                    "\nAkce s názvem «" + name + "» byl již vytvořena");
        }
    }




//== ABSTRACT METHODS ==========================================================

    /***************************************************************************
     * Metoda realizující reakci hry na zadání daného příkazu.
     * Počet parametrů je závislý na konkrétní akci,
     * např. akce typu <i>konec</i> a <i>nápověda</i> nemají parametry,
     * akce typu <i>jdi</i> a <i>seber</i> mají jeden parametr
     * akce typu <i>použij</i> muže mít dva parametry atd.
     *
     * @param arguments Parametry příkazu – akce;
     *                  jejich počet muže byt pro každou akci jiný,
     *                  ale pro všechna spuštění stejné akce je stejný
     * @return Text zprávy vypsané po provedeni příkazu
     */
    @Override
    abstract
    public String execute(String... arguments)
    ;



//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí popis akce s vysvětlením její funkce
     * a významu jednotlivých parametru.
     *
     * @return Popis akce
     */
    @Override
    public String getDescription()
    {
        return description;
    }


//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}