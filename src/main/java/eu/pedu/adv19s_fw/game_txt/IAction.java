/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_txt;



/*******************************************************************************
 * Instance interfejsu {@code IAction}
 * mají na starosti interpretaci příkazů zadávaných uživatelem hrajícím hru.
 * Název spouštěného příkazu je první slovo řádku zadávaného z klávesnice;
 * další slova pak jsou interpretována jako parametry.
 * <p>
 * Můžete ale definovat příkaz, který odstartuje konverzaci
 * (např. s osobou přítomnou v místnosti) a tím systém přepne do režimu,
 * v němž se zadávané texty neinterpretují jako příkazy,
 * ale předávají se definovanému objektu až do chvíle,
 * kdy uživatel rozhovor ukončí a objekt rozhovoru přepne hru zpět
 * do režimu klasických příkazů.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IAction extends INamed
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí popis příkazu s vysvětlením jeho funkce,
     * významu jednotlivých parametrů
     * a možností (resp. účelu) použití daného příkazu.
     * Tento popis tak může sloužit jako nápověda k použití daného příkazu.
     *
     * @return Popis příkazu
     */
//    @Override
    public String getDescription()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================

    /***************************************************************************
     * Metoda realizující reakci hry na zadání daného příkazu.
     * Předávané pole je vždy neprázdné,
     * protože jeho nultý prvek je zadaný název vyvolaného příkazu.
     * Počet parametrů je závislý na konkrétním příkazu,
     * např. příkazy <i>konec</i> a <i>nápověda</i> nemají parametry,
     * příkazy <i>jdi</i> a <i>seber</i> mají jeden parametr
     * příkaz <i>použij</i> muže mít dva parametry atd.
     *
     * @param arguments Parametry příkazu;
     *                  jejich celkový počet muže byt pro každý příkaz jiný,
     *                  ale nultý prvek vždy obsahuje název příkazu
     * @return Text zprávy vypsané po provedeni příkazu
     */
//    @Override
    public String execute(String... arguments)
    ;



//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
