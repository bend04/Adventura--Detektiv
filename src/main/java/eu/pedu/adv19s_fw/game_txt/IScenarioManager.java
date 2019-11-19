/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_txt;

import eu.pedu.adv19s_fw.scenario.Scenario;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;



/*******************************************************************************
 * Instance interfejsu {@code IScenarioManager} představují správce scénářů
 * své hry předpokládající textové uživatelské rozhraní.
 * <p>
 * Správce scénářů musí být (stejně jako hra) definován jako jedináček
 * (singleton) a kromě metod deklarovaných v tomto interfejsu musí jeho třída
 * definovat statickou tovární metodu {@code getInstance()}
 * vracející instanci tohoto jedináčka.<br>
 * Splnění této podmínky nemůže prověřit překladač,
 * ale prověří ji až následné testy hry.
 * <p>
 * Každý správce scénářů musí mít k dispozici minimálně:
 * <ul>
 *   <li><b>základní úspěšný scénář</b>, demonstrující některou z možných
 *     cest úspěšného dokončení hry,</li>
 *   <li><b>chybový scénář</b>, specifikující reakci hry na chybně zadané
 *     příkazy</li>
 * </ul>
 * <p>
 * Jednotlivé scénáře se musí lišit svým názvem,
 * přičemž názvy základního úspěšného a základního chybového scénáře
 * jsou předem pevně dány a není je možno změnit.
 * <p>
 * Scénáře jsou iterovatelné a streamovatelné posloupnosti
 * kroků scénáře, jež jsou instancemi třídy
 * {@link eu.pedu.adv19s_fw.scenario.ScenarioStep}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IScenarioManager
         extends IAuthor, IGSMFactoryProduct, Iterable<Scenario>
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================

    /** Název základního úspěšného scénáře. */
    public static final String HAPPY_SCENARIO_NAME = "_HAPPY_";

    /** Název základního chybového scénáře. */
    public static final String MISTAKE_SCENARIO_NAME = "_MISTAKE_";



//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí kolekci názvů všech spravovaných scénářů.
     *
     * @return Kolekce s názvy scénářů
     */
    public Collection<String> getAllScenarioNames()
    ;


    /***************************************************************************
     * Vrátí scénář se zadaným názvem.
     *
     * @param name Název požadovaného scénáře
     * @return Scénář se zadaným názvem; není-li, vrátí {@code null}
     */
    public Scenario getScenario(String name)
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================

    /***************************************************************************
     * Základní test ověřující, jestli správce scénářů vyhovuje
     * zadaným okrajovým podmínkám.
     * Správce scénáře se automaticky prověří a výsledky vytiskne
     * již při provádění testu.
     */
    public void autoTest()
    ;


    /***************************************************************************
     * Prověří, že hra pracuje podle obou povinných scénářů,
     * tj. podle základního úspěšného, ještě jednou podle základního úspěšného
     * a pak podle základního chybového.
     */
    public void testGame()
    ;


    /***************************************************************************
     * Prověří hru tak, že ji "zahraje" dle scénářů se zadanými názvy.
     * Zadaný scénář musí být testovací. Je-li scénář uveden vícekrát,
     * tak se vícekrát také spustí.
     *
     * @param names Názvy testovacích scénářů
     */
    public void testGameByScenarios(String... names)
    ;


    /***************************************************************************
     * Vrátí iterátor, který bude postupně poskytovat
     * jednotlivé spravované scénáře v pořadí, v jakém byly zadány.
     *
     * @return Iterátor přes spravované scénáře
     */
    @Override
    public Iterator<Scenario> iterator()
    ;


    /***************************************************************************
     * Vrátí datovod, který bude postupně poskytovat
     * jednotlivé spravované scénáře v pořadí, v jakém byly zadány.
     *
     * @return Datovod spravovaných scénářů
     */
    public Stream<Scenario> stream()
    ;


//\DG== DEFAULT GETTERS AND SETTERS ============================================

    /***************************************************************************
     * Vrátí základní úspěšný scénář &ndash; happy scenario.
     *
     * @return Základní úspěšný scénář
     */
    default Scenario getHappyScenario()
    {
        return getScenario(HAPPY_SCENARIO_NAME);
    }


    /***************************************************************************
     * Vrátí základní chybový scénář.
     *
     * @return Základní chybový scénář
     */
    default Scenario getMistakeScenario()
    {
        return getScenario(MISTAKE_SCENARIO_NAME);
    }



//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
