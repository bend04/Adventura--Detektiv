/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */
package cz.vse.java.adventura.game;

import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.scenario.ScenarioStep;
import eu.pedu.adv19s_fw.scenario.TypeOfScenario;

import static eu.pedu.adv19s_fw.scenario.TypeOfStep.*;
import static cz.vse.java.adventura.game.Texts.*;
import cz.vse.java.adventura.*;
import eu.pedu.adv19s_fw.scenario.AScenarioManager;


/*******************************************************************************
 * Instance třídy {@code RUPScenarioManagerLit} slouží jako správce scénářů,
 * které mají prověřit a/nebo demonstrovat správnou funkci plánované hry,
 * přičemž texty jsou v této třídě definovány pomocí literálů.
 * <p>
 * Jednotlivé scénáře jsou iterovatelné a "streamovatelné" posloupností kroků
 * specifikovaných instancemi třídy
 * {@link eu.pedu.adv15p_fw.scenario.ScenarioStep}
 * z frameworku, do nějž je třeba vyvíjenou hru hra začlenit.
 * <p>
 * Správce scénářů je jedináček, který má na starosti všechny scénáře
 * týkající se s ním sdružené hry.
 * <p>
 * Jednotlivé spravované scénáře se musí lišit svým názvem,
 * přičemž názvy základního úspěšného a základního chybového scénáře
 * jsou předem pevně dány a není je možno změnit.
 *
 * @author  Dominik BENEŠ
 * @version 2015-Podzim — 15.55.5837 — 2015-11-25
 */
public class DetScenarioManagerCon extends AScenarioManager
                                implements IAuthorDB
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    /** Tovární třída, jejíž instancemi jsou tovární objekty poskytující
     *  instanci správce scénářů i hry, jejíž scénáře daný správce spravuje. */
    private final static Class<? extends IGSMFactory> FACTORY_CLASS =
                                                      DetGSMFactory.class;

 private static final String REQUIRED_STEPS_SCENARIO_NAME = "REQUIRED";


    /***************************************************************************
     * Počáteční krok hry, který je pro všechny scénáře shodný.
     * <p>
     * Konstruktor plnohodnotné instance třídy {@link ScenarioStep}
     * vyžaduje následující parametry:
     <pre> {@code
TypeOfStep typeOfStep; //Typ daného kroku scénáře
String     command;    //Příkaz realizující tento krok scénáře
String     message;    //Zpráva vypsaná po zadání příkazu
String     area;       //Prostor, v němž skončí hráč po zadání příkazu
String[]   neighbors;  //Sousedé aktuálního prostoru (= východy)
String[]   items;      //Předměty vyskytující se v daném prostoru
String[]   bag;        //Aktuální obsah batohu
     }</pre>
     =======================================================================<br>
     * Kroky scénáře musejí navíc vyhovovat následujícím požadavkům:
     * <ul>
     *   <li>Žádná z položek nesmí obsahovat prázdný odkaz.</li>
     *   <li>S výjimkou položky {@code command} nesmí být žádný řetězec
     *     prázdný (tj. {@code ""})</li>
     *   <li>Prázdný řetězec se nesmí objevit ani jako položka některého
     *     z vektorů {@code neighbors}, {@code items} či {@code bag}</li>
     * </ul>
     * <br>
     ***************************************************************************
     */
    private static final ScenarioStep START_STEP =
        new ScenarioStep(0, tsSTART,  "", //Spouštěcí příkaz
            zUVÍTÁNÍ   //zCELÉ_UVÍTáNÍ
            //=== Pomocný řádek pro synchronizaci porovnání se správcem LIT ===
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON, BOTA, BUNDA },
            new String[] {}
                   )
        ;


    /***************************************************************************
     * Kroky základního úspěšného scénáře
     * popisující očekávatelný úspěšný průběh hry.
     * Z těchto kroků sestavený scénář nemusí být nutně nejkratším možným
     * (takže to vlastně ani nemusí být základní úspěšný scénář),
     * ale musí vyhovovat všem okrajovým podmínkám zadání,
     * tj. musí obsahovat minimální počet kroků,
     * projít požadovaný.minimální počet prostorů
     * a demonstrovat použití všech požadovaných akcí.
     */
    private static final ScenarioStep[] HAPPY_SCENARIO_STEPS =
    {
        START_STEP
        ,
        new ScenarioStep(tsMOVE, JDI + " " + KOUPELNA,
            zPŘESUN + KOUPELNA
            ,
            KOUPELNA,
            new String[] { OBYVAK, PREDSIN },
            new String[] { KOS, SKRINKA, LAHVICKA },
            new String[] {}
        )
                ,
        new ScenarioStep(tsTAKE, VEZMI + " " + LAHVICKA,
            zZVEDNUTO + LAHVICKA
            ,
            KOUPELNA,
            new String[] { OBYVAK, PREDSIN},
            new String[] { KOS, SKRINKA },
            new String[] { LAHVICKA }
        )
        ,
        
        new ScenarioStep(tsMOVE, JDI + " " + OBYVAK,
            zPŘESUN + OBYVAK
            ,
            OBYVAK,
            new String[] { KUCHYN, KOUPELNA },
            new String[] { MRTVOLA, KLIC },
            new String[] { LAHVICKA }
        )
        ,
        new ScenarioStep(tsTAKE, VEZMI + " " + KLIC,
            zZVEDNUTO + KLIC
            ,
            OBYVAK,
            new String[] { KUCHYN, KOUPELNA },
            new String[] { MRTVOLA },
            new String[] { LAHVICKA, KLIC}
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, PROHLEDEJ + " " + MRTVOLA,
            zINFO_MRTVOLA + MRTVOLA
            ,
            OBYVAK,
            new String[] { KUCHYN, KOUPELNA },
            new String[] { MRTVOLA},
            new String[] { LAHVICKA, KLIC }
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + KUCHYN,
            zPŘESUN + KUCHYN
            ,
            KUCHYN,
            new String[] { OBYVAK, PREDSIN, PRACOVNA, ZAHRADA },
            new String[] { SLUZKA, KRVAVY_NUZ },
            new String[] { LAHVICKA, KLIC }
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + PRACOVNA,
            zPŘESUN + PRACOVNA
            ,
            PRACOVNA,
            new String[] { KUCHYN },
            new String[] { TREZOR, STUL },
            new String[] { LAHVICKA, KLIC }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, ODEMKNI + " " + TREZOR,
            zODEMKNOUT 
            ,
            PRACOVNA,
            new String[] { KUCHYN },
            new String[] { TREZOR, STUL },
            new String[] { LAHVICKA, KLIC }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, POLOZ + " " + KLIC,
            zPOLOŽIT + KLIC
            
            ,
            PRACOVNA,
            new String[] { KUCHYN },
            new String[] { TREZOR, STUL, KLIC },
            new String[] { LAHVICKA }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, OTEVRI + " " + TREZOR,
            zOTEVREL_TREZOR
            ,
            TREZOR,
            new String[] { },
            new String[] { PRISTUPOVA_KARTA, PENIZE, DOKUMENTY },
            new String[] { LAHVICKA }
        )
        ,
        new ScenarioStep(tsTAKE, VEZMI + " " + PRISTUPOVA_KARTA,
            zZVEDNUTO + PRISTUPOVA_KARTA
            
            ,
            TREZOR,
            new String[] { },
            new String[] { PENIZE, DOKUMENTY },
            new String[] { LAHVICKA, PRISTUPOVA_KARTA }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, ZAVRI + " " + TREZOR,
            zZAVŘEL_TREZOR
            ,
            PRACOVNA,
            new String[] { KUCHYN },
            new String[] { TREZOR, STUL, KLIC },
            new String[] { LAHVICKA, PRISTUPOVA_KARTA }
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + KUCHYN,
            zPŘESUN + KUCHYN
            ,
            KUCHYN,
            new String[] { OBYVAK, PREDSIN, PRACOVNA, ZAHRADA },
            new String[] { SLUZKA, KRVAVY_NUZ },
            new String[] { LAHVICKA, PRISTUPOVA_KARTA }
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + ZAHRADA,
            zPŘESUN + ZAHRADA
            ,
            ZAHRADA,
            new String[] { KUCHYN, KULNA },
            new String[] { ZAHRADNIK, ZLOMENE_HRABE },
            new String[] { LAHVICKA, PRISTUPOVA_KARTA }
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + KULNA,
            zPŘESUN  + KULNA 
            ,
            KULNA,
            new String[] { ZAHRADA, BEZPECNOSTNI_MISTNOST },
            new String[] { KLADIVO, SEKACKA },
            new String[] {LAHVICKA, PRISTUPOVA_KARTA}
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + BEZPECNOSTNI_MISTNOST,
            zPŘESUN + BEZPECNOSTNI_MISTNOST
            
            ,
            BEZPECNOSTNI_MISTNOST,
            new String[] {KULNA},
            new String[] { POCITAC },
            new String[] {LAHVICKA, PRISTUPOVA_KARTA}
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, PROHLEDEJ + " " + POCITAC,
            zCHCE_PROHLEDAT_POCITAC
            ,
            BEZPECNOSTNI_MISTNOST,
            new String[] { KULNA},
            new String[] { POCITAC },
            new String[] {LAHVICKA, PRISTUPOVA_KARTA}
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + KULNA,
            zPŘESUN  + KULNA 
            ,
            KULNA,
            new String[] { ZAHRADA, BEZPECNOSTNI_MISTNOST },
            new String[] { KLADIVO, SEKACKA },
            new String[] {LAHVICKA, PRISTUPOVA_KARTA}
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + ZAHRADA,
            zPŘESUN + ZAHRADA
            ,
            ZAHRADA,
            new String[] { KUCHYN, KULNA },
            new String[] { ZAHRADNIK, ZLOMENE_HRABE },
            new String[] { LAHVICKA, PRISTUPOVA_KARTA }
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + KUCHYN,
            zPŘESUN + KUCHYN
            ,
            KUCHYN,
            new String[] { OBYVAK, PREDSIN, PRACOVNA, ZAHRADA },
            new String[] { SLUZKA, KRVAVY_NUZ },
            new String[] { LAHVICKA, PRISTUPOVA_KARTA }
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + PREDSIN,
            zPŘESUN + PREDSIN
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON, BOTA, BUNDA },
            new String[] { LAHVICKA, PRISTUPOVA_KARTA }
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + LOZNICE,
            zPŘESUN + LOZNICE
            ,
            LOZNICE,
            new String[] { PREDSIN, PRACOVNA },
            new String[] { MANZELKA, NOCNI_STOLEK, SATNIK },
            new String[] { LAHVICKA, PRISTUPOVA_KARTA }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, PROHLEDNI + " " + MANZELKA,
            zCHCE_PROHLEDNOUT_MANZELKA
            ,
            LOZNICE,
            new String[] { PREDSIN, PRACOVNA },
            new String[] { MANZELKA, NOCNI_STOLEK, SATNIK },
            new String[] { LAHVICKA, PRISTUPOVA_KARTA }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, OBVINIT + " " + MANZELKA,
            zOBVINIT_SPRÁVNĚ
            ,
            LOZNICE,
            new String[] { PREDSIN, PRACOVNA },
            new String[] { MANZELKA, NOCNI_STOLEK, SATNIK },
            new String[] { LAHVICKA, PRISTUPOVA_KARTA }
        )
        ,
        new ScenarioStep(tsEND, KONEC,
            zKONEC
            ,
            LOZNICE,
            new String[] { PREDSIN, PRACOVNA },
            new String[] { MANZELKA, NOCNI_STOLEK, SATNIK },
            new String[] { LAHVICKA, PRISTUPOVA_KARTA }
        )

    };


    /** Krok testující špatné spuštění hry je definován zvlášť,
     *  aby bylo možno správně nastavit indexy následujících kroků. */
    private static final ScenarioStep WRONG_START =
    new ScenarioStep(-1, tsNOT_START, "Start",
            zNENÍ_START
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
        WRONG_START
        ,
        START_STEP
        ,
        new ScenarioStep(tsUNKNOWN, "dveře",
            zNEZNÁMÝ_PŘÍKAZ
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON, BOTA, BUNDA },
            new String[] {}
        )
        ,
        new ScenarioStep(tsEMPTY, "",
            zPRÁZDNÝ_PŘÍKAZ
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON, BOTA, BUNDA },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, VEZMI + " " + BOTA,
            zZVEDNUTO + BOTA
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON, BUNDA},
            new String[] {BOTA}
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + KOUPELNA,
            zPŘESUN + KOUPELNA
            ,
            
            KOUPELNA,
            new String[] { OBYVAK, PREDSIN },
            new String[] { KOS, SKRINKA, LAHVICKA },
            new String[] { BOTA }
        )
        ,
        new ScenarioStep(tsBAD_NEIGHBOR, JDI + " " + "loznice",
            zNENÍ_SOUSEDEM + "loznice"
            ,
           KOUPELNA,
            new String[] { OBYVAK, PREDSIN },
            new String[] {KOS, SKRINKA, LAHVICKA  },
            new String[] { BOTA }
        )
        ,
        new ScenarioStep(tsBAD_ITEM, VEZMI + " " + KOUPELNA,
            zNENÍ_PŘÍTOMEN + KOUPELNA
            ,
            KOUPELNA,
            new String[] { OBYVAK, PREDSIN },
            new String[] { KOS, SKRINKA, LAHVICKA },
            new String[] { BOTA }
        )
        ,
        new ScenarioStep(tsUNMOVABLE, VEZMI + " " + SKRINKA,
            zTĚŽKÝ_PŘEDMĚT + SKRINKA
            ,
            KOUPELNA,
            new String[] { OBYVAK, PREDSIN },
            new String[] { KOS, SKRINKA, LAHVICKA },
            new String[] { BOTA }
        )
        ,
        new ScenarioStep(tsNOT_IN_BAG, POLOZ + " " + KLADIVO,
            zNENÍ_V_BATOHU + KLADIVO
            ,
            KOUPELNA,
            new String[] { OBYVAK, PREDSIN },
            new String[] { KOS, SKRINKA, LAHVICKA },
            new String[] { BOTA }
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + PREDSIN,
            zPŘESUN + PREDSIN
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON, BUNDA },
            new String[] {BOTA}
        )
        ,
        new ScenarioStep(tsTAKE, VEZMI + " " + BUNDA,
            zZVEDNUTO + BUNDA
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON},
            new String[] { BOTA, BUNDA }
        )
        ,
        new ScenarioStep(tsBAG_FULL, VEZMI + " " + TELEFON,
            zBATOH_PLNÝ
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON},
            new String[] { BOTA, BUNDA }
        )
        ,
        new ScenarioStep(tsMOVE_WA, JDI,
            zNEVÍM_KAM_JÍT
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON},
            new String[] { BOTA, BUNDA }
        )
        ,
        new ScenarioStep(tsTAKE_WA, VEZMI,
            zNEVÍM_CO_VZÍT
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON},
            new String[] { BOTA, BUNDA }
        )
        ,
        new ScenarioStep(tsPUT_DOWN_WA, POLOZ,
            zNEVÍM_CO_POLOŽIT
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON},
            new String[] { BOTA, BUNDA }
        )
        ,
        new ScenarioStep(tsHELP, HELP,
            zNÁPOVĚDA
            //Text pokračuje vyjmenováním akcí a jejich popisů
            //a končí standardním popisem aktuální situace
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON},
            new String[] { BOTA, BUNDA }
        )
        ,
        new ScenarioStep(tsEND, KONEC,
            zKONEC
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON},
            new String[] { BOTA, BUNDA }
        )
        ,
    };

static { ScenarioStep.setIndex(1); }



    /***************************************************************************
     * Kroky scénáře určeného pro prověření povinných akcí,
     * přesněji akcí pro přechod do prostoru, zvednutí a položení objektu,
     * vypsání nápovědy a pro předčasné ukončení hry.
     */
    private static final ScenarioStep[] REQUIRED_STEPS =
    {
        START_STEP
        ,
        new ScenarioStep(tsHELP, HELP,
            zPORADÍM
                          //Text pokračuje vyjmenováním příkazů a jejich popisů
            //a končí standardním popisem aktuální situace
            ,
            PREDSIN,
            new String[] { KUCHYN, KOUPELNA, LOZNICE },
            new String[] { SATNIK, TELEFON, BOTA, BUNDA },
            new String[] {}
        )
        ,
        new ScenarioStep(tsMOVE, JDI + " " + KOUPELNA,
            zPŘESUN + KOUPELNA
            ,
            KOUPELNA,
            new String[] { OBYVAK, PREDSIN },
            new String[] { KOS, SKRINKA, LAHVICKA },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, VEZMI + " " + LAHVICKA,
            zZVEDNUTO + LAHVICKA
            ,
            KOUPELNA,
            new String[] { OBYVAK, PREDSIN },
            new String[] { KOS, SKRINKA},
            new String[] { LAHVICKA }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, POLOZ + " " + LAHVICKA,
            zPOLOŽIT + LAHVICKA
            ,
            KOUPELNA,
            new String[] { OBYVAK, PREDSIN },
            new String[] { KOS, SKRINKA, LAHVICKA },
            new String[] {}
        )
        ,
        new ScenarioStep(tsEND, KONEC,
            zKONEC
            ,
            KOUPELNA,
            new String[] { OBYVAK, PREDSIN },
            new String[] { KOS, SKRINKA, LAHVICKA },
            new String[] {}
        )
        ,
    };
    
    
    /** Jediná instance této třídy. Spravuje všechny scénáře sdružené hry. */
    private static final DetScenarioManagerCon MANAGER =
                                          new DetScenarioManagerCon();



//== VARIABLE CLASS ATTRIBUTES =================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================

    /***************************************************************************
     * Statický konstruktor je u definice konstant AGE, THIS_YEAR a BORN_YEAR
     * a pak ještě jednou před definicí konstanty MISTAKE_SCENARIO_STEPS
     * Takováto inicializace by měla být před každou další konstantou
     * definující kroky dalšího scénáře.
     */



//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vrátí správce scénářů - jedinou instanci této třídy.
     *
     * @return Správce scénářů
     */
    public static DetScenarioManagerCon getInstance()
    {
        return MANAGER;
    }


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
    private DetScenarioManagerCon()
    {
        super(FACTORY_CLASS);

       addScenario(HAPPY_SCENARIO_NAME,
                    TypeOfScenario.scHAPPY,    HAPPY_SCENARIO_STEPS);
        addScenario(MISTAKE_SCENARIO_NAME,
                    TypeOfScenario.scMISTAKES, MISTAKE_SCENARIO_STEPS);
        addScenario(REQUIRED_STEPS_SCENARIO_NAME,
                    TypeOfScenario.scGENERAL,  REQUIRED_STEPS);
        seal();
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================



//##############################################################################
//== TEST METHODS AND CLASSES ==================================================

    /***************************************************************************
     * Metoda prověřující daného správce scénářů
     * či hru definovanou scénáři tohoto správce.
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
     *       <li>Použití akcí pro přechod z prostoru do prostoru
     *         zvednutí nějakého předmětu a položení nějakého předmětu</li>
     *       <li>Křížová konzistence akcí a stavů po jejich vyvolání</li>
     *     </ul>
     *   </li>
     *   <li>Základní chybový scénář má následující vlastnosti:
     *     <ul>
     *       <li>Chybné odstartování příkazem, jenž není prázdný řetězec</li>
     *       <li>Startovní příkaz, jehož název je prázdný řetězec</li>
     *       <li>Použití všech povinných chybových typů kroku definovaných
     *         ve třídě<br>
     *         {@link eu.pedu.adv15p_fw.scenario.TypeOfStep}</li>
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
        //MANAGER.autoTest();

        //Vypíše na standardní výstup simulovaný průběh hry
        //odehrané podle základního úspěšného scénáře
        //MANAGER.getHappyScenario().simulate();
        

        //Testování hry prováděné postupně podle obou povinných scénářů,
        //přičemž základní úspěšný scénář se prochází dvakrát za sebou
       MANAGER.testGame();

        //Testování hry dle scénářů se zadanými názvy
      //MANAGER.testGameByScenarios(MISTAKE_SCENARIO_NAME);

        //Odehrání hry dle scénáře se zadaným názvem
      // MANAGER.playGameByScenario("???");

        System.exit(0);
    }

}
