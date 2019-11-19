/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.empty_classes;

import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.scenario.AScenarioManager;
import eu.pedu.adv19s_fw.scenario.ScenarioStep;
import eu.pedu.adv19s_fw.scenario.TypeOfScenario;

import static eu.pedu.adv19s_fw.scenario.TypeOfStep.*;



/*******************************************************************************
 * Instance třídy {@code EmptyScenarioManager} slouží jako
 * šablona (nebo chcete-li kostra) správce scénářů, jejichž prostřednictvím
 * budou testovací programy prověřovat správnou funkci plánované hry.
 * <p>
 * Každý správce musí definovat:
 * <ul>
 *   <li><b>Základní úspěšný scénář</b> nazvaný <b><code>_HAPPY_"</code></b>
 *     definující některý z úspěšných postupů jak dosáhnout cíle hry.
 *   </li>
 *   <li><b>Základní chybový scénář</b> nazvaný <b><code>_MISTAKE_</code></b>
 *     definující reakce hry na typické chyby při zadávání příkazů.
 *   </li>
 * </ul>
 * <p>
 * Jednotlivé spravované scénáře se musí lišit svým názvem,
 * přičemž názvy základního úspěšného a základního chybového scénáře
 * jsou předem pevně dány a není je možno změnit.
 * <p>
 * Jednotlivé scénáře jsou iterovatelné a "streamovatelné" posloupností kroků
 * specifikovaných instancemi třídy
 * {@link eu.pedu.adv19s_fw.scenario.ScenarioStep}
 * z frameworku, do nějž je třeba vyvíjenou hru hra začlenit.
 * <p>
 * Správce scénářů je jedináček, který má na starosti všechny scénáře
 * týkající se s ním sdružené hry.
 * <p>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public   class EmptyScenarioManager
       extends AScenarioManager
    implements IAuthorPrototype
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /***************************************************************************
     * Počáteční krok hry, který je pro všechny scénáře shodný.
     * <p>
     * Konstruktor plnohodnotné instance třídy {@link ScenarioStep}
     * vyžaduje následující parametry:
     <pre> {@code
TypeOfStep typeOfStep; //Typ daného kroku scénáře
String     command;    //Příkaz realizující tento krok scénáře
String     message;    //Zpráva vypsaná po zadání příkazu
String     place;      //Prostor, v němž skončí hráč po zadání příkazu
String[]   neighbors;  //Sousedé aktuálního prostoru (= východy)
String[]   items;      //Objekty vyskytující se v daném prostoru
String[]   bag;        //Aktuální obsah batohu
     }</pre>
     =======================================================================<br>
     * Kroky scénáře musejí navíc vyhovovat následujícím požadavkům:
     * <ul>
     *   <li>Žádná z položek nesmí obsahovat prázdný odkaz.</li>
     *   <li>S výjimkou položky {@code command} nesmí být žádný řetězec
     *     prázdný (tj. {@code ""})</li>
     *   <li>Prázdný řetězec se nesmí objevit ani jako položka některého
     *     z polí {@code neighbors}, {@code items} či {@code bag}</li>
     * </ul>
     * <br>
     **************************************************************************/
     public static final ScenarioStep START_STEP =
        new ScenarioStep(0, //Počáteční krok má zadán index, další jej následují
            tsSTART, "", //Název spouštěcího příkazu = ""
            "Uvítání"
            ,
            "Výchozí_prostor",
            new String[] { "Soused1", "Soused2" },
            new String[] { "Objekt1", "Objekt2" },
            new String[] { "ObjektA", "ObjektB" }
        );


    /***************************************************************************
     * Kroky základního úspěšného scénáře
     * popisující očekávatelný úspěšný průběh hry.
     * Z těchto kroků sestavený scénář nemusí být nutně nejkratším možným
     * (takže to vlastně ani nemusí být základní úspěšný scénář),
     * ale musí vyhovovat všem okrajovým podmínkám zadání,
     * tj. musí obsahovat minimální počet kroků,
     * projít požadovaný.minimální počet prostorů
     * a demonstrovat použití všech požadovaných příkazů.
     */
    private static final ScenarioStep[] HAPPY_SCENARIO_STEPS =
    {
        START_STEP,

        new ScenarioStep(tsNOT_SET, "příkaz",
            "Zpráva_vypsaná_v_reakci_na_příkaz"
            ,
            "Aktuální_prostor_po_vykonání_příkazu",
            new String[] { "Soused1", "Soused2" },
            new String[] { "Objekt_v_prostoru", "Objekt_v_prostoru" },
            new String[] {  }
        )
        ,

    };


    /** Krok testující špatné spuštění hry je definován zvlášť,
     *  aby bylo možno správně nastavit indexy následujících kroků. */
    private static final ScenarioStep WRONG_START =
    new ScenarioStep(-1,
            tsNOT_START, "Start",
            "\nPrvním příkazem není startovací příkaz." +
            "\nHru, která neběží, lze spustit pouze startovacím příkazem.\n"
            ,
            "",
            new String[] {},
            new String[] {},
            new String[] {}
        );


    static { ScenarioStep.setIndex(1); }


    /***************************************************************************
     * Základní chybový scénář definující reakce
     * na povinnou sadu typů chybně zadaných příkazů.
     */
    private static final ScenarioStep[] MISTAKE_SCENARIO_STEPS =
    {
        WRONG_START,

        START_STEP,

        new ScenarioStep(tsNOT_SET, "příkaz",
            "Zpráva_vypsaná_v_reakci_na_příkaz"
            ,
            "Aktuální_prostor_po_vykonání_příkazu",
            new String[] { "Soused1", "Soused2" },
            new String[] { "Objekt_v_prostoru", "Objekt_v_prostoru" },
            new String[] {  }
        )
        ,
        new ScenarioStep(tsEND, "konec",
            "Zpráva_o_předčasném_ukončení_hry."
            ,
            "Prostor",
            new String[] { "Sousedé" },
            new String[] { "Objekty" },
            new String[] { "Batoh"   }
        )
    };


    /** Jediná instance této třídy. Spravuje všechny scénáře sdružené hry. */
    private static final EmptyScenarioManager MANAGER =
                                          new EmptyScenarioManager();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Vrátí správce scénářů - jedinou instanci této třídy.
     *
     * @return Správce scénářů
     */
    public static EmptyScenarioManager getInstance()
    {
        return MANAGER;
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
     * Vytvoří instanci představující správce scénářů hry.
     * V rámci konstruktoru je vhodné vytvořit všechny scénáře
     * a správce scénářů poté zalepit.
     * <p>
     * Jednotlivé spravované scénáře se musí lišit svým názvem,
     * přičemž názvy základního úspěšného a základního chybového scénáře
     * jsou předem pevně dány a není je možno změnit.
     * Jim zadávané názvy jsou proto pouze formální, protože
     * jim volaná metoda stejně přiřadí ty předem definované.
     * <p>
     *´Kontrakt metody
     * {@link #addScenario(String, TypeOfScenario, ScenarioStep...) }
     * vyžaduje, aby byl jak první přidán úspěšný scénář, tj. scénář typu
     * {@link TypeOfScenario.scHAPPY}, a jako druhý chybový scénář,
     * tj. scénář typu {@link MISTAKE_SCENARIO_NAME}.
     * Na pořadí následně přidávaných scénářů nezáleží.
      */
    private EmptyScenarioManager()
    {
        super(FACTORY_CLASS);

        addScenario(HAPPY_SCENARIO_NAME,
                    TypeOfScenario.scHAPPY,    HAPPY_SCENARIO_STEPS);
        addScenario(MISTAKE_SCENARIO_NAME,
                    TypeOfScenario.scMISTAKES, MISTAKE_SCENARIO_STEPS);
        seal();
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================



//##############################################################################
//== TEST METHODS AND CLASSES ==================================================

    /***************************************************************************
     * Metoda prověřující daného správce scénářů
     * nebo hru prověřovanou kroky scénářů tohoto správce.
     * <p>
     * U správce scénářů se prověřuje, zda vyhovuje zadaným okrajovým podmínkám,
     * tj. jestli:
     * <ul>
     *   <li>Umí vrátit správně naformátované jméno autora/autorky hry
     *       a jeho/její ID.</li>
     *   <li>Definuje základní úspěšný a základní chybový scénář.</li>
     *   <li>Základní chybový scénář má následující vlastnosti:
     *     <ul>
     *       <li>Startovní příkaz, jehož název je prázdný řetězec</li>
     *       <li>Minimální požadovaný počet kroků</li>
     *       <li>Minimální počet navštívených prostorů</li>
     *       <li>Minimální počet "zahlédnutých" prostorů</li>
     *       <li>Minimální počet vlastních (nepovinných) akcí</li>
     *       <li>Použití příkazů pro přechod z prostoru do prostoru
     *         zvednutí nějakého objektu a položení nějakého objektu</li>
     *       <li>Křížová konzistence příkazů a stavů po jejich zadání</li>
     *     </ul>
     *   </li>
     *   <li>Základní chybový scénář má následující vlastnosti:
     *     <ul>
     *       <li>Chybné odstartování příkazem,
     *           jehož název není prázdný řetězec</li>
     *       <li>Startovní příkaz, jehož název je prázdný řetězec</li>
     *       <li>Použití všech povinných chybových typů kroku definovaných
     *         ve třídě<br>
     *         {@link eu.pedu.adv19s_fw.scenario.TypeOfStep}</li>
     *       <li>Vyvolání nápovědy</li>
     *       <li>Ukončení příkazem pro nestandardní ukončení hry</li>
     *     </ul>
     *   </li>
     * </ul>
     * <p>
     * U hry se prověřuje, zda je možno ji zahrát přesně tak,
     * jak je naplánováno ve scénářích.
     *
     * @param args Parametry příkazového řádku - nepoužívané.
     */
    public static void main(String[] args)
    {
        //Otestuje, zda správce scénářů a jeho scénáře vyhovují požadavkům
        MANAGER.autoTest();

        //Vypíše na standardní výstup simulovaný průběh hry
        //odehrané podle základního úspěšného scénáře
//        MANAGER.getHappyScenario().simulate();

        //Testování hry prováděné postupně obou povinných scénářů,
        //přičemž základní úspěšný scénář se prochází dvakrát za sebou
//        MANAGER.testGame();

        //Testování hry dle scénáře se zadaným názvem
//        MANAGER.testGameByScenarios("???");

        //Odehrání hry dle scénáře se zadaným názvem
//        MANAGER.playGameByScenario("???");

        System.exit(0);
    }

}
