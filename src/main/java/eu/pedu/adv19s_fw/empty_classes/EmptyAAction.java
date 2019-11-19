/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.empty_classes;

import eu.pedu.adv19s_fw.game_txt.IAction;



/*******************************************************************************
 * Třída {@code EmptyAAction} je společným rodičem všech tříd, jejichž instance
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
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
abstract class EmptyAAction
       extends ANamed
    implements IAction
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Stručný popis dané akce. */
    private final String description;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří rodičovský podobjekt vytvářené akce.
     *
     * @param name  Název vytvářené akce = text, který musí hráč zadat
     *              jako počáteční slovo zadávaného příkazu
     * @param description Stručný popis vytvářené akce
     */
    EmptyAAction(String name, String description)
    {
        super(name);
        this.description = description;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================

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



//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí popis příkazu s vysvětlením jeho funkce
     * a významu jednotlivých parametru.
     *
     * @return Popis příkazu
     */
    @Override
    public String getDescription()
    {
        return description;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
