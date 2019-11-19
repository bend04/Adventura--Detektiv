/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.scenario_test;

import eu.pedu.adv19s_fw.game_txt.BasicActions;
import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.test_util.ATester;
import eu.pedu.adv19s_fw.scenario.AScenarioManager;
import eu.pedu.adv19s_fw.scenario.Limits;
import eu.pedu.adv19s_fw.scenario.Scenario;
import eu.pedu.adv19s_fw.scenario.ScenarioStep;
import eu.pedu.adv19s_fw.scenario.TypeOfScenario;
import eu.pedu.adv19s_fw.scenario.TypeOfStep;
import eu.pedu.adv19s_fw.test_util.common.FrameworkException;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static eu.pedu.adv19s_fw.game_txt.IScenarioManager.*;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.*;
import static eu.pedu.adv19s_fw.utilities.ConditionalPrinter.*;
import static eu.pedu.adv19s_fw.utilities.FormatStrings.*;
import static eu.pedu.adv19s_fw.utilities.Util.*;



/*******************************************************************************
 * Instance třídy <b>{@code ScenarioTester}</b> testují scénáře hry,
 * tj. prověřují, nakolik scénář odpovídá požadavkům na něj kladeným.
 * Kontrolují zejména vzájemnou konzistenci jednotlivých kroků scénářů,
 * a to včetně křížové konzistence kroků z různých scénářů.
 * Souhrn výsledků předávají volajícímu objektu
 * v přepravce typu {@link SMSummary}.
 * Testují však pouze obsah scénářů,
 * tj. nepokouší se aplikovat scénáře na hru a testovat tak hru.
 * To mají na starosti instance třídy
 * {@link eu.pedu.adv19s_fw.test_util.game_txt_test.TGameRunTester}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class ScenarioTester extends ATester
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Otestuje zadané scénáře zadaného správce a vrátí přepravku
     * se souhrnnou informaci o základních charakteristikách hry
     * odvozenou z testovaných scénářů.
     *
     * @param manager Správce, jehož scénáře se budou testovat
     * @return Přepravka se souhrnnou informaci o charakteristikách hry
     */
    public static SMSummary testAllScenarios(AScenarioManager manager)
    {
        return testGivenScenarios(manager,
                                  manager.getAllScenarioNames()
                                         .toArray(new String[0]));
    }


    /***************************************************************************
     * Otestuje základní úspěšný a základní chybový scénář
     * zadaného správce a vrátí přepravku se souhrnnou informací
     * o základních charakteristikách hry odvozenou z testovaných scénářů.
     *
     * @param manager Správce, jehož scénáře se budou testovat
     * @return Přepravka se souhrnnou informaci o charakteristikách hry
     */
    public static SMSummary testBasicScenarios(AScenarioManager manager)
    {
        return testGivenScenarios(manager, HAPPY_SCENARIO_NAME,
                                           HAPPY_SCENARIO_NAME,
                                           MISTAKE_SCENARIO_NAME);
    }


    /***************************************************************************
     * Otestuje zadané scénáře zadaného správce a vrátí přepravku
     * se souhrnnou informaci o základních charakteristikách hry
     * odvozenou z testovaných scénářů.
     *
     * @param manager  Správce, jehož scénáře se budou testovat
     * @param names    Názvy scénářů, které se mají otestovat
     * @return Přepravka se souhrnnou informaci o charakteristikách hry
     */
    @SuppressWarnings("empty-statement")
    public static SMSummary testGivenScenarios(AScenarioManager manager,
                                               String... names)
    {
        List<Scenario> scenarios= new ArrayList<>();
        boolean[]      results  = new boolean[names.length];
        IGSMFactory    factory  = manager.getFactory();
        ScenarioTester tester   = new ScenarioTester(factory);
        boolean        ok       = true;

        int i = 0;
        for (String name : names) {
            Scenario scenario = manager.getScenario(name);
            boolean  result   = tester.verify(scenario);
            scenarios.add(scenario);
            results[i++] = result;
            ok &= result;
        }
        Scenario happy = manager.getHappyScenario();

        ScenarioStep start = happy.iterator().next();
        ScenarioStep stop  = start;
        for (ScenarioStep ss : happy) {
            stop = ss;
        }

        return new SMSummary(ok,
                              tester.allMentionedPlaces,
                              tester.allEnteredActions,
                              tester.allSeenItems,
                              start, stop,
                              tester.deriveBasicActions(),
                              tester.name2typeGroup,
                              scenarios,
                              results);
    }




//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    //-- Následující konstanty si instance pamatuje mezi jednotlivými testy
    //   scénářů a používá je ke kontrole duplicit -----------------------------

    /** Mapa uchovávající typ každé zadané akce.
     *  Slouží ke kontrole konzistentnosti používání příkazů,
     *  tj. že nebude stejná akce omylem použita
     * v navzájem nekonzistentních typech kroků.
     *  Názvy akcí jsou převedeny na velká písmena. */
    private final Map<String, TypeOfStep> action2type = new HashMap<>();

    /** Mapa převádějící typ povinného kroku na název akce,
     *  kterou krok realizuje.
     *  Slouží ke kontrole konzistentnosti používání akcí,
     *  tj. že pro daný typ standardního kroku nebudou použité různé příkazy.
     *  Názvy příkazů jsou převedeny na velká písmena. */
    private final Map<TypeOfStep, String> type2group =
                                          new EnumMap<>(TypeOfStep.class);

    /** Mapa mapující názvy příkazů na skupinu jejich typu. */
    private final Map<String, TypeOfStep> name2typeGroup = new HashMap<>();

    /** Kolekce všech nestandardních akcí. */
    private final Collection<String> nonStandardActions = new TreeSet<>();


    //-- Texty zapamatované ze všech scénářů testovaných touto instancí --------

        /** Množina akcí použitých v dosud prověřených scénářích. */
        private final Set<String> allEnteredActions = new TreeSet<>();

        /** Množina názvů prostorů zmíněných v dosud prověřených scénářích
         *  (např. jako sousedé). */
        private final Set<String> allMentionedPlaces = new TreeSet<>();

        /** Množina názvů objektů zmíněných v navštívených či
         * zmíněných prostorech v dosud prověřených scénářích. */
        private final Set<String> allSeenItems = new TreeSet<>();


    //-- Kolekce dat zapamatovaných pro testovaný scénář -----------------------

        /** Množina akcí použitých v testu. */
        private final Set<String> enteredActions = new TreeSet<>();

        /** Množina názvů navštívených prostorů. */
        private final Set<String> visitedPlaces = new TreeSet<>();

        /** Množina názvů prostorů zmíněných ve scénáři (např. jako sousedé). */
        private final Set<String> mentionedPlaces = new TreeSet<>();

        /** Množina použitých objektů. */
        private final Set<String> seenItems = new TreeSet<>();



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Testovaný scénář. */
    private Scenario scenarioInTest;

    /** Označení testu zapisované do preambule a postambule. */
    private String descriptonOfScenarioInTest;

    /** Přepravka s názvy základních (=povinných) příkazů. */
    private BasicActions basicActions;

    /** Doposud nezrealizované typy akcí. */
    private Set<TypeOfStep> notUsedActions;

    /** Počet doposud prověřených kroků scénáře. */
    private int numOfStep;

    /** Atribut, v němž si metody předávají informaci o tom,
     *  zda daný scénář vyhovuje požadavkům.
     *  Při testu každého scénáře je atribut inicializován v metodě
     *  {@link #initialization()}. */
    private boolean scenarioOK;


    //-- Proměnné použité pro test jednoho kroku -------------------------------

//        /** Příznak toho, že byl zadán startovací prázdný příkaz. */
//        private boolean startovacíAkce;

        /** {@code true} před startem prvního kroku, anebo byl-li již vydán
         *  příkaz k ukončení hry a nebyla ještě odstartována hra další. */
        private boolean finished;

        /** Posledně vyhodnocovaný krok scénáře. */
        private ScenarioStep previousStep;

        /** Aktuálně vyhodnocovaný krok scénáře. */
        private ScenarioStep currentStep;

        /** Jednotlivá slova příkazu v testovaném kroku scénáře. */
        private String[] wordsInCommand;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří tester pro scénáře spravované správcem dodaným zadanou továrnou.
     *
     * @param factory Tovární objekt schopný dodat objekty,
     *                které se mají testovat
     */
    private ScenarioTester(IGSMFactory factory)
    {
        super(factory);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Vytiskne chybové hlášení a označí celý scénář za nevyhovující.
     *
     * @param format    Formát tisku chybového hlášení. Bude ještě doplněn
     *                  společnou úvodní a závěrečnou sekvencí.
     * @param arguments Případné další parametry k tisku
     */
    @Override
    public void ERROR(String format, Object... arguments)
    {
        scenarioOK = false;
        super.ERROR(format, arguments);
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Z hodnot uložených v mapě {@link #type2group} odvodí
     * názvy povinných akcí.
     *
     * @return Přepravka s názvy povinných akcí odvozených ze scénářů
     */
    private BasicActions deriveBasicActions()
    {
        String move = type2group.get(TypeOfStep.tsMOVE);
        String take = type2group.get(TypeOfStep.tsTAKE);
        String put  = type2group.get(TypeOfStep.tsPUT_DOWN);
        String help = type2group.get(TypeOfStep.tsHELP);
        String end  = type2group.get(TypeOfStep.tsEND);

        BasicActions result = new BasicActions(move, take, put, help, end);
        return result;
    }


    /***************************************************************************
     * Vrátí parametr zadaného příkazu.
     *
     * @return Parametr zadaného příkazu
     */
    private String getCommandArgument()
    {
        if (wordsInCommand.length < 2) {
            ERROR("Příkaz «%s» vyžadující parametr byl zadán bez parametrů",
                  wordsInCommand[0]);
            return "";
        }
        return wordsInCommand[1];
    }


    /***************************************************************************
     * Inicializuje atributy používané v průběhu testu
     * a vytiskne zprávu o zahájení testu daného scénáře.
     */
    private void initialization()
    {
        AScenarioManager ss = scenarioInTest.getManager();
        descriptonOfScenarioInTest =
               "Author:                 " + ss.getAuthorName() +
             "\nScenario manager class: " + ss.getClass() +
             "\nScenario:               " + scenarioInTest.getName();
        enteredActions.clear();
        visitedPlaces  .clear();
        mentionedPlaces.clear();
        seenItems     .clear();
        notUsedActions = EnumSet.allOf(TypeOfStep.class);
        numOfStep    = 0;
        scenarioOK   = true;
        finished     = true;
        previousStep = null;
        prf("%s\n===== Start of the test ===== %2$tF — %2$tR\n",
            N_HASHES_N + descriptonOfScenarioInTest, new Date());
    }


    /***************************************************************************
     * Připraví a vytiskne závěrečnou zprávu o testu zadaného scénáře.
     */
    private void finalization()
    {
        allEnteredActions.addAll(enteredActions);
        allMentionedPlaces.addAll(mentionedPlaces);
        allSeenItems     .addAll(seenItems);

        //Zjistí zmíněné, ale nenavštívené prostory
        Set<String> notVisited = discoverNotVisitedPlaces();

        //Zjistí, které typy akcí nebyly použity a které měly být použity
        Set<String> notUsed = discoverNotUsedStepTypes();

        showResults(notVisited, notUsed);
    }


    /***************************************************************************
     * Prověří zadaný scénář, zapíše výsledek na standardní výstup
     * a vrátí logickou hodnotu oznamující, zda scénář úspěšně prošel testem.
     *
     * @param scenario  Prověřovaný scénář
     * @return Informace, zda scénář úspěšně prošel testem
     */
    private boolean verify(Scenario scenario)
    {
        scenarioInTest = scenario;
        initialization();
        verifyAuthor();
        if (TypeOfScenario.scDEMO.equals(scenario.getType())) {
            verifyDemoScenario(scenario);
        }
        else {
            verifyTestableScenario(scenario);
        }
        finalization();
        return scenarioOK;
    }


    /***************************************************************************
     * Ověří, že udávané jméno autora odpovídá zadaným konvencím,
     * tj. že obsahuje nejméně dvě slova, první z nich je velkými písmeny
     * a druhé začíná velkým písmenem.
     *
     * @return Vektor stringů s jednotlivými slovy jména autora
     *         zbavenými diakritiky
     */
    private String[] verifyAuthor()
    {
        //Jméno zbavíme diakritiky pro snazší následnou kontrolu
        String authorASCII = Normalizer.normalize( //Java 6+
               scenarioInTest.getManager().getAuthorName(),
               Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        String[] wordsInName = authorASCII.split(" ");
        String[] check       = authorASCII.split("\\s+");
        if (wordsInName.length != check.length) {
            ERROR("Špatně použité bílé znaky ve jméně autora.");
        }
        if ((wordsInName   .length    < 2)  ||
            (wordsInName[0].length() == 0)  ||
            (wordsInName[1].length() == 0))
        {
            ERROR("Autor nemá uvedeno příjmení + křestní jméno");
        }
        String surname   = wordsInName[0];
        String firstName = wordsInName[1];
        if (! surname.matches("[A-Z]+")) {
            ERROR("Prvním slovem jména autora není příjmení " +
                  "zapsané velkými písmeny");
        }
        if (! firstName.matches("[A-Z][a-z]+")) {
            ERROR("Druhé slovo jména autora nemá " +
                  "první písmeno velké a ostatní malá");
        }
        return wordsInName;
    }


    /***************************************************************************
     * Ověří, zda zadaný scénář obsahuje všechny typy akcí
     * obsažené v zadané množině.
     *
     * @param scenario Prověřovaný scénář
     * @param required Požadované typy testů
     */
    private void verifyCompleteness(Scenario scenario, Set<TypeOfStep> required)
    {
        EnumSet<TypeOfStep> notUsed = EnumSet.copyOf(required);
        for (ScenarioStep step : scenario) {
            TypeOfStep typKroku = step.typeOfStep;
            notUsed.remove(typKroku);
        }
        if (notUsed.size() > 0) {
            scenarioOK = false;
            prf("Nepokryté typy akcí: %s\n", notUsed);
        }
    }


    /***************************************************************************
     * Vypíše typy jednotlivých kroků a jejich příkazy; nic nekontroluje.
     *
     * @param scenario Prověřovaný scénář
     */
    private void verifyDemoScenario(Scenario scenario)
    {
        int index = 1;
        for (ScenarioStep step : scenario) {
            String message = String.format("%2d. %14s - %s", index,
                             step.typeOfStep, step.command);
            prln(message);
        }
    }


    /***************************************************************************
     * Prověří zadaný scénář a připraví podklady pro zapsání výsledku.
     * Při odhalení chyby vytiskne zprávu
     * a nastaví proměnnou {@link #scenarioOK} na {@code false};
     *
     * @param scenario  Prověřovaný scénář
     */
    private void verifyTestableScenario(Scenario scenario)
    {
        boolean start = true;
        for (ScenarioStep step : scenario) {
            currentStep = step;
            if (start) {
                start = false;
                if (TypeOfScenario.scMISTAKES.equals(scenario.getType())  &&
                   (step.typeOfStep != TypeOfStep.tsNOT_START))
                {
                    ERROR("Chybový scénář musí začínat testem definujícím " +
                          "reakci na \nnestartovní (= neprázdný) příkaz " +
                          "zadaný neběžící hře: %s",
                              currentStep);
                }
                else {
                    notUsedActions.remove(tsNOT_START);
                }
            }
            if (finished  &&
                    ! (TypeOfStep.tsSTART    .equals(currentStep.typeOfStep) ||
                       TypeOfStep.tsNOT_START.equals(currentStep.typeOfStep)) )
            {
                ERROR("Zadaný krok scénáře je před začátkem nebo " +
                        "za koncem hry\ntj. hra ještě není odstartovaná " +
                        "anebo je již ukončená %s",
                              currentStep);
                break;
            }
            processStep();
            previousStep = currentStep;
        }
        if (!finished   &&
                TypeOfScenario.scMISTAKES.equals(scenario.getType()))
        {
            ERROR("Chybový scénář hru explicitně neukončil.");
        }
        switch (scenario.getName()) {
            case AScenarioManager.HAPPY_SCENARIO_NAME:
                verifyCompleteness(scenario, TypeOfStep.REGULAR_ACTIONS);
                break;
            case AScenarioManager.MISTAKE_SCENARIO_NAME:
                verifyCompleteness(scenario, TypeOfStep.MISTAKE_ACTIONS);
                break;
        }
    }


    /***************************************************************************
     * Ohlásí chybu, protože demonstrační typ kroku se v testovacím scénáři
     * nesmí objevit.
     */
    private void verifyType_DEMO()
    {
        ERROR("Demonstrační typ kroku nepatří do testovacího scénáře\n%s",
              currentStep);
    }


    /***************************************************************************
     * Nastaví příznak konce, aby bylo možno ověřit,
     * že zadaný krok krok testu je skutečně poslední
     * (každý další krok by způsobil chybu),
     * a současně ověří, že se zadáním konce hry nezmění ostatní stavy hry.
     */
    private void verifyType_END()
    {
        verifyActionName(tsEND);
        verifyEqualityOfAllFields();
        finished = true;
    }


    /***************************************************************************
     * Ověří, že stav před zadáním příkazu a po jeho vykonání je shodný.
     */
    private void verifyType_HELP()
    {
        verifyActionName(tsHELP);
        verifyEqualityOfAllFields();
    }


    /***************************************************************************
     * Ověří, že tuto akci lze konzistentně zařadit mezi nestandardní,
     * a že příkaz má požadovaný počet parametrů.
     *
     * @param arguments Požadovaný počet parametrů
     */
    private void verifyType_NON_STANDARDx(int arguments)
    {
        TypeOfStep argType = null;
        switch(arguments)
        {
            case 0: argType = tsNON_STANDARD0;  break;
            case 1: argType = tsNON_STANDARD1;  break;
            case 2: argType = tsNON_STANDARD2;  break;
//            case 3: argType = tsNON_STANDARD3;  break;

            default:
                ERROR(LINE, (wordsInCommand.length-1));
        }
        verifyActionName(argType);

        if (wordsInCommand.length != (arguments+1)) {
            ERROR("Požadovaný počet parametrů je %d, " +
                  "avšak příkaz má %d parametrů",
                  arguments, (wordsInCommand.length-1));
        }
    }


    /***************************************************************************
     * Ověří, že tuto akci lze konzistentně zařadit mezi nestandardní,
     * že příkaz má požadovaný počet parametrů
     * a že se stav hry nezměnil, protože příkaz byl uživatelem zadán špatně.
     *
     * @param i Požadovaný počet parametrů
     */
    @SuppressWarnings("fallthrough")
    private void verifyType_WRONG_NS(int arguments)
    {
        TypeOfStep argType = null;
        switch(arguments)
        {
            case 0: ERROR("Pro bezparametrický příkaz není možno zadat "
                        + "špatné parametry");  break;

            case-1: arguments = 0;
            case 1: argType = tsNON_STANDARD1;  break;

            case-2: arguments = 1;
            case 2: argType = tsNON_STANDARD2;  break;
//            case 3: argType = tsNON_STANDARD3;  break;

            default:
                ERROR(LINE, (wordsInCommand.length-1));
        }
        verifyActionName(argType);

        if (wordsInCommand.length != (arguments+1)) {
            ERROR("Požadovaný počet parametrů je %d, " +
                  "avšak příkaz má %d parametrů",
                  arguments, (wordsInCommand.length-1));
        }
    }


    /***************************************************************************
     * Ověří, že byl zadána příkaz pro položení objektu
     * a že zadaný objekt v batohu doopravdy není.
     * Při zpracování příkazu se nesmí změnit
     * aktuální prostor ani objekty tomto v prostoru a ani obsah batohu.
     */
    private void verifyType_NOT_IN_BAG()
    {
        verifyActionName(tsPUT_DOWN);
        verifyEqualityOfAllFields();
        if (verifyArrayContent(currentStep.bag, getCommandArgument(), true)) {
            ERROR("Zadaný objekt v batohu je\n%s", currentStep);
        }
    }


    /***************************************************************************
     * Ověří, že byl zadána příkaz pro zvednutí objektu
     * a že v aktuálním prostoru zadaný objekt doopravdy není.
     * Při zpracování příkazu se nesmí změnit
 aktuální prostor ani items v tomto prostoru a ani obsah batohu.
     */
    private void verifyType_BAD_ITEM()
    {
        verifyActionName(tsTAKE);
        verifyEqualityOfAllFields();
        if (verifyArrayContent(currentStep.items, getCommandArgument(), true))
        {
            ERROR("Zadaný předmět v aktuálním prostoru je\n%s", currentStep);
        }
    }


    /***************************************************************************
     * Ověří reakci hry na pokus přejít do prostoru,
     * který v daném okamžiku není sousedem aktuálního prostoru.
     * Při zpracování příkazu se nesmí změnit
     * aktuální prostor ani objekty v tomto prostoru a ani obsah batohu.
     */
    private void verifyType_BAD_NEIGHBOR()
    {
        verifyActionName(tsMOVE);
        verifyEqualityOfAllFields();
        if (verifyArrayContent(currentStep.neighbors,
                               getCommandArgument(),true))
        {
            ERROR("Zadaného souseda místnost má\n%s", currentStep);
        }
    }


    /***************************************************************************
     * Ověří reakci hry na neznámý příkaz.
     * Při zpracování příkazu se nesmí změnit
     * aktuální prostor ani objekty v tomto prostoru a ani obsah batohu.
     */
    private void verifyType_UNKNOWN()
    {
        verifyActionName(tsUNKNOWN);
        verifyEqualityOfAllFields();
    }


    /***************************************************************************
     * Ověří reakci hry na zadaný prázdný text.
     * Při zpracování příkazu se nesmí změnit
     * aktuální prostor ani objekty v tomto prostoru a ani obsah batohu.
     */
    private void verifyType_EMPTY()
    {
        verifyActionName(tsEMPTY);
        verifyEqualityOfAllFields();
    }


    /***************************************************************************
     * Ověří reakci na pokus zvednout předmět v situaci, kdy je batoh již plný.
     * Při zpracování příkazu se nesmí změnit
     * aktuální prostor ani objekty v tomto prostoru a ani obsah batohu.
     */
    private void verifyType_BAG_FULL() {
        verifyActionName(tsTAKE);
        verifyEqualityOfAllFields();
    }


    /***************************************************************************
     * Ověří reakci na položení předmětu.
     * Při zpracování příkazu se nesmí změnit aktuální prostor.
     */
    private void verifyType_PUT_DOWN() {
        verifyActionName(tsPUT_DOWN);
        verifySamePlace();
        verifyFieldEquality(Pair.NEIGHBORS);
        String toPut = getCommandArgument();
        if (toPut.length() > 0) { //Byl zadán parametr
            verifyAbsence(Pair.ITEMS, toPut, false);
            verifyAbsence(Pair.BAG,   toPut, true);
        }
    }


    /***************************************************************************
     * Ověří reakci na příkaz k položení předmětu zadaný bez parametru.
     * Při zpracování příkazu se nesmí nic změnit.
     */
    private void verifyType_PUT_DOWN_WA()
    {
        verifyActionName(tsPUT_DOWN);
        verifyEqualityOfAllFields();
    }


    /***************************************************************************
     * Ověří reakci na přesun hráče z prostoru do prostoru.
     * Při zpracování příkazu se nesmí změnit obsah batohu.
     */
    private void verifyType_MOVE() {
        verifyActionName(tsMOVE);
        verifyPlaceReached();
        verifyFieldEquality(Pair.BAG);
    }


    /***************************************************************************
     * Ověří reakci na příkaz k přesunu do jiného prostoru zadaný bez parametru.
     * Při zpracování příkazu se nesmí nic změnit.
     */
    private void verifyType_MOVE_WA()
    {
        verifyActionName(tsMOVE);
        verifyEqualityOfAllFields();
    }


    /***************************************************************************
     * Všechny kontroly tohoto typu kroku již byl provedeny v metodě
     * {@link #test(Scenario)}
     */
    private void verifyType_DIALOG()
    {
    }


    /***************************************************************************
     * Ověří, že tento příkaz byl zadán jako před spuštěním hry
     * a že jeho název není prázdný.
     */
    private void verifyType_NOT_START()
    {
        if (! finished) {
            ERROR("Předchozí hra ještě nebyla ukončena.\n" +
                  "Tento typ testovacího kroku se vkládá před start hry.");
        }
        else if ("".equals(currentStep.command))  {
            ERROR("Příkaz v testu špatného odstartování hry nesmí být prázdný"
                  + ".");
        }
    }


    /***************************************************************************
     * Ověří, že tento příkaz byl zadán jako první a že jeho název je prázdný.
     */
    private void verifyType_START()
    {
        if (! finished) {
            ERROR("Předchozí hra ještě nebyla ukončena.\n" +
                  "Nová hra může odstartovat až po ukončení té předchozí.");
        }
        else if (! "".equals(currentStep.command))  {
            ERROR("Úvodní akce každého scénáře musí mít prázdný název \n" +
                  "a musí definovat zprávu a stav hry po odstartování,"
                  + ".");
        }
        else {
            finished = false;
        }
    }


    /***************************************************************************
     * Ověří reakci na pokus zvednout nezvednutelný objekt.
     * Při zpracování příkazu se nesmí změnit
     * aktuální prostor ani objekty v tomto prostoru a ani obsah batohu.
     */
    private void verifyType_UNMOVABLE()
    {
        verifyActionName(tsTAKE);
        verifyEqualityOfAllFields();
    }


    /***************************************************************************
     * Ověří reakci na příkaz ke zvednutí objektu.
     * Při zpracování příkazu se nesmí změnit aktuální prostor.
     */
    private void verifyType_TAKE()
    {
        verifyActionName(tsTAKE);
        verifySamePlace();
        verifyFieldEquality(Pair.NEIGHBORS);
        String toTake = getCommandArgument();
        if (toTake.length() > 0) { //Byl zadán parametr
            verifyAbsence(Pair.ITEMS, toTake, true );
            verifyAbsence(Pair.BAG,   toTake, false);
        }
    }


    /***************************************************************************
     * Ověří reakci na příkaz k zvednutí předmětu zadaný bez parametru.
     * Při zpracování příkazu se nesmí nic změnit.
     */
    private void verifyType_TAKE_WA()
    {
        verifyActionName(tsTAKE);
        verifyEqualityOfAllFields();
    }


    /***************************************************************************
     * Ověří, že po vykonání příkazu hráč skončil v prostoru,
     * který byl zadán jako parametr.
     */
    private void verifyPlaceReached()
    {
        String place = getCommandArgument();
        if ((place.length() > 0)   && //Byl zadán parametr
            (! place.equalsIgnoreCase(currentStep.place)))
        {
            ERROR("Hráč se nepřesunul do prostoru zadaného v příkazu");
        }
    }


    /***************************************************************************
     * Ověří, že název právě ohlášené akce je konzistentní
     * s doposud zadanými akcemi a přidá jej do seznamu názvů
     * spolu s typem kroku pro kontroly konzistence akcí zadaných v budoucnu.
     *
     * @param typeOfStep Typ korektně provedeného kroku odpovídající zadanému
     *                   typu kroku (např. chybovému typu {@code tsNOT_IN_BAG}
     *                   odpovídá "řádný" typ kroku {@code tsPUT_DOWN}).
     */
    private void verifyActionName(TypeOfStep typeOfStep)
    {
        String actionName;
        if (wordsInCommand.length >0) {
            actionName = wordsInCommand[0];
        }
        else {
//            actionName = "";
            String rival = type2group.get(typeOfStep);
            ERROR("Prázdný příkaz není možno zadat jako nestandardní: %s.",
                  typeOfStep);
            return; //Jen kvůli překladači, sem se program nedostane
        }
        String upperName = actionName.toUpperCase();
        TypeOfStep type  = action2type.get(upperName);
        if (type == null)  {    //Ještě nebyl zaznamenán
            action2type.put(upperName,   typeOfStep);
            if (typeOfStep.SUBTYPE == TypeOfStep.Subtype.stNONSTANDARD) {
                nonStandardActions.add(upperName);
            }
            else if ((type2group.get(typeOfStep) != null) &&
                     (typeOfStep != TypeOfStep.tsUNKNOWN) )
            {
                String rival = type2group.get(typeOfStep);
                ERROR("Pro akci typu %s byl již použit příkaz %s\n" +
                      "    nyní se pokoušíte použít příkaz %s",
                      typeOfStep, rival, actionName);
            }
            else {
                type2group.put(typeOfStep, upperName);
            }
        }
        else if (type != typeOfStep)  {
            ERROR("Akce «%s» již byla použita v kroku typu %s",
                  actionName, type);
        }

        if (name2typeGroup.get(upperName) == null) {
            name2typeGroup.put(upperName, typeOfStep.GROUP);
        }
        else if ((name2typeGroup.get(upperName) != typeOfStep.GROUP) )
        {
            ERROR("Příkaz %s byl již použit jako příkaz typu %s\n" +
                  "    nyní jej však používáte jako příkaz typu %s",
                  actionName, name2typeGroup.get(upperName), typeOfStep.GROUP);
        }
    }


    /***************************************************************************
     * Zjistí, zda zadané pole textů obsahuje/neobsahuje zadaný text.
     *
     * @param array          Prověřované pole textů
     * @param text           Text, jehož přítomnost zjišťujeme
     * @param shouldContain  {@code true} ptáme-li se, zda pole text obsahuje,
     *                       {@code false} zjišťujeme-li, zda text neobsahuje
     * @return Odpověď na zadanou otázku
     */
    private boolean verifyArrayContent(String[] array, String text,
                                       boolean  shouldContain)
    {
        boolean found = false;
        for (String s : array)  {
            if (s.equalsIgnoreCase(text)) {
                found = true;
                break;
            }
        }
        return (found == shouldContain);
    }


    /***************************************************************************
     * Ověří, že se texty v zadaných polích (tj. názvy pojmenovaných objektů)
     * v minulém a tomto testovacím kroku shodují
     * (např. že se při přechodu do jiného prostoru nezměnil stav batohu).
     *
     * @param pairs Přepravky s porovnávanými poli textů
     *              v minulém a tomto kroku testu
     */
    private void verifyFieldEquality(Pair... pairs)
    {
        for (Pair pair : pairs) {
            verifyFieldEquality(pair);
        }
    }


    /***************************************************************************
     * Ověří, že se seznamy názvů v zadané přepravce
     * v minulém a tomto kroku testu shodují.
     *
     * @param pair Přepravka s porovnávanými poli názvů
     *             v minulém a tomto kroku testu
     */
    private void verifyFieldEquality(Pair pair)
    {
        Names    names   = pair.pair(this);
        String[] last    = names.last;
        String[] current = names.current;
        Arrays.sort(last,    CIC);
        Arrays.sort(current, CIC);

        boolean equals = true;
        if (last.length != current.length) {
            equals = false;
        }
        else {
            for (int i=0;   i < current.length;   i++) {
                if (! last[i].equalsIgnoreCase(current[i])) {
                    equals = false;
                    break;
                }
            }
        }
        if (equals)  { return; }

        ERROR("Seznamy objektů typu %s v minulém a tomto kroku " +
              "si neodpovídají.\nMinule: %s\nNyní:   %s",
              pair.name(), Arrays.asList(last), Arrays.asList(current));
    }


    /***************************************************************************
     * Ověří, že se v minulém a tomto testovacím kroku shoduje
     * aktuální prostor a s ním i názvy v polích reprezentujících
     * sousedy prostoru, předměty v prostoru a obsah batohu.
     */
    private void verifyEqualityOfAllFields()
    {
        verifySamePlace();
        verifyFieldEquality(Pair.NEIGHBORS, Pair.ITEMS, Pair.BAG);
    }


    /***************************************************************************
     * Ověří, že se nezměnil aktuální prostor.
     */
    private void verifySamePlace()
    {
        if (! previousStep.place.equalsIgnoreCase(currentStep.place)) {
            ERROR("Při vykonvání tohoto příkazu se nesmí změnit " +
                  "aktuální prostor.");
        }
    }


    /***************************************************************************
     * Ověří, že se texty v zadaných polích v minulém a tomto zkušebním
     * kroku shodují s výjimkou zadaného názvu, který v jedné verzi chybí.
     *
     * @param pair    Přepravka s dvojicí polí s názvy entit platnými
     *                v minulém a současném kroku scénáře
     * @param absent  Text, který má v jednom z polí chybět
     * @param nowLess {@code true} má-li chybět v současném kroku,
     *                {@code false} má-li chybět v minulém kroku
     */
    private void verifyAbsence(Pair pair, String absent, boolean nowLess)
    {
        Names    names   = pair.pair(this);
        String[] last    = names.last;
        String[] current = names.current;
        Arrays.sort(last,    CIC);
        Arrays.sort(current, CIC);

        boolean agree = true;
        int difference = nowLess ? -1 : 1;
        try {
            if ((current.length - last.length)  !=  difference) {
                agree = false;
            }
            else if ((current.length == 0)  ||  (last.length == 0)) {
                agree = absent.equalsIgnoreCase(nowLess  ?  last[0]
                                                          :  current[0]);
            }
            else {
                for (int m=0, n=0;
                    (n < current.length)  &&  (m < last.length);
                    m++, n++)
                {
                    if (! last[m].equalsIgnoreCase(current[n])) {
                        String divergence;
                        if (nowLess) {
                            divergence = last[m];
                            n--;
                        }
                        else {
                            divergence = current[n];
                            m--;
                        }
                        agree = absent.equalsIgnoreCase(divergence);
                    }
                }
            }
            if (agree)  {
                return;                                 //==========>
            }
        } catch(Exception e) {
            //Nepotřebuje ošetřit - prostě ohlásí chybu
        }
        ERROR("Rozdíly v seznamech objektů typu %s mezi minulým a tímto " +
              "krokem neodpovídají akci typu %s\nMá chybět: %s\n" +
              "Minule: %s\nNyní:%s",
              pair.name(), this.currentStep.typeOfStep,
              absent, Arrays.asList(last), Arrays.asList(current));
    }


    /***************************************************************************
     * Vytiskne informaci o splnění požadovaného počtu objektů daného typu.
     * U scénáře zjišťuje počet kroků, použitých příkazů,
     * navštívených místností, zmíněných místností apod.
     *
     * @param anounced Počet objektů vyskytujících se ve scénáři
     * @param required Požadovaný minimální počet objektů
     * @return Text zprávy oznamující nalezený počet objektů doplněný v případě
     *         nesplnění podmínky minimálním požadovaným počtem objektů
     */
    private String satisfy(int anounced, int required)
    {
        if (TypeOfScenario.scHAPPY.equals(scenarioInTest.getType())) {
            boolean yes = (anounced >= required);
            scenarioOK = (scenarioOK && yes);
            return  yes  ?   "suit "
                         :  ("DOES NOT SUIT  (min = " + required + ") ");
        }
        return "";
    }


    /***************************************************************************
     * Převede číslo na dvouznakový textový řetězec.
     *
     * @param number Převáděné číslo
     * @return Výsledný textový řetězec
     */
     private String z2(int number)
    {
        if (number < 10)  {
            return " " + number;
        }
        else {
            return Integer.toString(number);
        }

    }


    /***************************************************************************
     * Zjistí, které typy akcí ještě nebyly použity ačkoliv být použity měly.
     * Základní úspěšný scénář by totiž měl prověřit reakce na všechny typy
     * základních úspěšných akcí (přechody mezi prostory, zvedání a pokládání
     * objektů), základní chybový scénář by měl prověřit reakce na všechny typy
     * chybových akcí.
     *
     * @return Množina názvů neprověřených povinných typů akcí
     */
    private Set<String> discoverNotUsedStepTypes()
    {
        Set<String> absent = new HashSet<>();
        TypeOfStep.Subtype required;
        TypeOfScenario typeOfScenario = scenarioInTest.getType();
        if (null == typeOfScenario) {
            return absent;    //Nejsou povinné akce     //==========>
        }
        else {
            switch (typeOfScenario) {
                case scHAPPY:
                    required = TypeOfStep.Subtype.stCORRECT;
                    break;
                case scMISTAKES:
                    required = TypeOfStep.Subtype.stMISTAKE;
                    break;
                default:
                    return absent;    //Nejsou povinné akce     //==========>
            }
        }

        notUsedActions.stream()
            .filter (typeOfStep -> required.equals(typeOfStep.getSubtype()))
            .forEach(typeOfStep -> absent.add(typeOfStep.name()));
        if (absent.size() > 0)  {
            scenarioOK = false;  //Nebyly vyzkoušeny všechny povinné akce
        }
        return absent;
    }


    /***************************************************************************
     * Vrátí množinu názvů zmíněných, avšak nenavštívených místností.
     *
     * @return Množina názvů nenavštívených místností
     */
    @SuppressWarnings("AssignmentToForLoopParameter")
    private Set<String> discoverNotVisitedPlaces()
    {
        Set<String> notVisited = new LinkedHashSet<>();
        mentionedPlaces.stream().
                map    ((s) -> s.toLowerCase()).
                filter ((s) -> (!visitedPlaces.contains(s))).
                forEach((s) -> {notVisited.add(s);});
        return notVisited;
    }


    /***************************************************************************
     * Zobrazí výsledky provedeného testu spolu se základní charakteristikou
     * testovaného scénáře.
     *
     * @param notVisited Zmíněné, avšak nenavštívené prostory
     * @param absent     Neprovedené akce, které však měly být provedeny
     */
    private void showResults(Set<String> notVisited, Set<String> absent)
    {
        int numOwnActions = nonStandardActions.size();
        int numVisited    = visitedPlaces      .size();
        int numMentioned  = mentionedPlaces    .size();
        int numEntered    = enteredActions    .size();
        int numNotUsed    = notUsedActions    .size();
        int numAbsent     = absent            .size();

        Limits limits = AScenarioManager.LIMITS;

        String s =
             "===== End of the test =====" +
           "\nNumber of steps:            " + z2(numOfStep) + " - " +
                                satisfy(numOfStep, limits.minSteps) +
           "\nNonstandard actions:        " + z2(numOwnActions) + " - " +
                                satisfy(numOwnActions, limits.minOwnActions) +
                                nonStandardActions +
           "\nMentioned places:           " + z2(numMentioned) + " - " +
                                satisfy(numMentioned, limits.minPlaces) +
                                mentionedPlaces +
           "\nVisited places:             " + z2(numVisited) + " - " +
                                satisfy(numVisited, limits.minVisited) +
                                visitedPlaces +
           "\nEntered actions:            " + z2(numEntered) + " - " +
                                           enteredActions +
           "\nNot enteredy types of step: " + z2(numNotUsed) + " - " +
                                           notUsedActions +
           "\nThe mandatory ones:         " + z2(numAbsent) + " - " +
                                           absent +
           "\nVisited places:             " + visitedPlaces +
           "\nNot visited places:         " + notVisited +
           "\nMentioned items:            " + seenItems +
           "\n===== Test finished\n"        + descriptonOfScenarioInTest +
           "\n===== Scenario test " + (scenarioOK ? "passed" : "NOT PASSED") +
           N_HASHES_N;
        prln(s);
    }


    /***************************************************************************
     * Vytvoří seznam obsahující všechny názvy ze zadaného pole (vektoru)
     * převedené na malá písmena.
     *
     * @param names
     * @return Seznam se zadanými názvy v malých písmenech
     */
    private Collection<? extends String> arr2lstInLC(String[] names)
    {
        List<String> list = new ArrayList<>(names.length);
        for (String name : names) {
            list.add(name.toLowerCase());
        }
        return list;
    }


    /***************************************************************************
     * Zpracuje aktuální krok scénáře.
     * Odkaz na něj je uložen v atributu {@link #currentStep}.
     *
     * @throws TestException Chyba v testované aplikaci
     * @throws FrameworkException Objevena chyba frameworku
     */
    private void processStep()
    {
        String message = String.format("%2d. %15s - %s",
                         currentStep.index,
                         currentStep.typeOfStep, currentStep.command);
        prln(message);
        if (currentStep.typeOfStep == tsNOT_START)
        {
            return;
        }
        numOfStep++;
        boolean modifiedStep = (currentStep.typeOfStep.SUBTYPE ==
                                Subtype.stMODIFIED);

        wordsInCommand = currentStep.command.split("\\s+");
        if (! modifiedStep                           &&
            (currentStep.typeOfStep != tsDIALOG)     &&
            (currentStep.typeOfStep != tsUNKNOWN)    &&
            (currentStep.typeOfStep != tsSTART)      &&
            (currentStep.typeOfStep != tsEMPTY)      &&
            (wordsInCommand.length > 0))
        {
            enteredActions.add(wordsInCommand[0].toLowerCase());
        }
        checkMessageLength("zprávy v " + currentStep.index + ". kroku",
                            currentStep.message);
        visitedPlaces  .add(currentStep.place.toLowerCase());
        mentionedPlaces.add(currentStep.place.toLowerCase());
        mentionedPlaces.addAll(arr2lstInLC(currentStep.neighbors));
        seenItems      .addAll(arr2lstInLC(currentStep.items));
        seenItems      .addAll(arr2lstInLC(currentStep.bag));
        notUsedActions .remove(currentStep.typeOfStep);

        if (! modifiedStep) switch (currentStep.typeOfStep)
        {
            case tsNOT_SET:
                String txt = "\nTyp kroku " + currentStep.typeOfStep +
                             " nesmí být používán v testovaných scénářích.";
                ERROR(txt);
                break;

            case tsNOT_START:        verifyType_NOT_START     ( ); break;

            case tsSTART:            verifyType_START         ( ); break;

            case tsEMPTY:            verifyType_EMPTY         ( ); break;
            case tsHELP:             verifyType_HELP          ( ); break;
            case tsUNKNOWN:          verifyType_UNKNOWN       ( ); break;

            case tsMOVE:             verifyType_MOVE          ( ); break;
            case tsMOVE_WA:          verifyType_MOVE_WA       ( ); break;
            case tsBAD_NEIGHBOR:     verifyType_BAD_NEIGHBOR  ( ); break;

            case tsTAKE:             verifyType_TAKE          ( ); break;
            case tsTAKE_WA:          verifyType_TAKE_WA       ( ); break;
            case tsBAD_ITEM:         verifyType_BAD_ITEM      ( ); break;
            case tsUNMOVABLE:        verifyType_UNMOVABLE     ( ); break;
            case tsBAG_FULL:         verifyType_BAG_FULL      ( ); break;

            case tsPUT_DOWN:         verifyType_PUT_DOWN      ( ); break;
            case tsPUT_DOWN_WA:      verifyType_PUT_DOWN_WA   ( ); break;
            case tsNOT_IN_BAG:       verifyType_NOT_IN_BAG    ( ); break;

            case tsNON_STANDARD0:    verifyType_NON_STANDARDx (0); break;

            case tsNON_STANDARD1:    verifyType_NON_STANDARDx (1); break;
            case tsNS1_WRONG_ARG:    verifyType_WRONG_NS      (1); break;
            case tsNS1_0Args:        verifyType_WRONG_NS     (-1); break;

            case tsNON_STANDARD2:    verifyType_NON_STANDARDx (2); break;
            case tsNS2_WRONG_1stARG: verifyType_WRONG_NS      (2); break;
            case tsNS2_WRONG_2ndARG: verifyType_WRONG_NS      (2); break;
            case tsNS2_1Args:        verifyType_WRONG_NS     (-2); break;

//            case tsNON_STANDARD3:    verifyType_NON_STANDARDx (3); break;

            case tsDIALOG:           verifyType_DIALOG        ( ); break;

            case tsDEMO:             verifyType_DEMO          ( ); break;
            case tsEND:              verifyType_END           ( ); break;

            default:
                throw new FrameworkException("Nepokrytý typ kroku scénáře: "
                                           + currentStep.typeOfStep);
        }
        previousStep = currentStep;
    }



//== EMBEDDED TYPES AND INNER CLASSES ==========================================

    /***************************************************************************
     * Přepravka pro uchování vektoru názvů některého z objektů
     * v minulém a aktuálním kroku testu.
     */
    private static class Names
    {
        final String[] last;
        final String[] current;

        Names(String[] last, String[] actual) {
            this.last   = last;
            this.current = actual;
        }
    }


    /***************************************************************************
     * Instance třídy {@code Pair} jsou schopny vrátit odpovídající dvojice
     * polí s názvy příslušných objektů (sousedé prostoru, objekty v prostoru,
     * objekty v batohu).
     */
    @SuppressWarnings("PackageVisibleInnerClass")
    enum Pair
    {
        NEIGHBORS {
            @Override
            @SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
            Names pair(ScenarioTester tester) {
                return new Names(tester.previousStep.neighbors,
                                 tester.currentStep .neighbors);
            }
        },

        ITEMS {
            @Override
            @SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
            Names pair(ScenarioTester tester) {
                return new Names(tester.previousStep.items,
                                 tester.currentStep .items);
            }
        },

        BAG {
            @Override
            @SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
            Names pair(ScenarioTester tester) {
                return new Names(tester.previousStep.bag,
                                 tester.currentStep .bag);
            }
        };

        abstract Names pair(ScenarioTester tester);

    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
//
//    /** Názvy typů příkazů. */
//    public static enum BasicCommandType{
//        /** Příkaz pro přesun do sousedního prostoru. */        MOVE,
//        /** Příkaz položení objektu do aktuálního prostoru. */  PUT_DOWN,
//        /** Příkaz zvednutí objektu v aktuálním prostoru. */    TAKE,
//        /** Příkaz pro vyvolání nápovědy. */                    HELP,
//        /** Příkaz pro předčasné ukončení hry. */               END,
//        ;
//    }
}
