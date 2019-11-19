/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.scenario;

import eu.pedu.adv19s_fw.Framework;
import eu.pedu.adv19s_fw.empty_classes.EmptyGame;
import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IGUI4txt;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IScenarioManager;
import eu.pedu.adv19s_fw.test_util.default_game.GSMFactoryTLit_Apartment;
import eu.pedu.adv19s_fw.test_util.default_game.GSMFactoryTLocCZ_Apartment;
import eu.pedu.adv19s_fw.test_util.default_game.ui_fx4txt.DefaultGUI4txt;
import eu.pedu.adv19s_fw.test_util.factory_test.FactoryTester;
import eu.pedu.adv19s_fw.test_util.game_txt_test.BasicSetTester;
import eu.pedu.adv19s_fw.test_util.game_txt_test.GivenSetTester;
import eu.pedu.adv19s_fw.test_util.scenario_test.ScenarioManagerTester;
import eu.pedu.adv19s_fw.test_util.scenario_test.SMSummary;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import static eu.pedu.adv19s_fw.utilities.ConditionalPrinter.*;
import static eu.pedu.adv19s_fw.utilities.FormatStrings.*;



/*******************************************************************************
 * Instance třídy {@code AScenarioManager} spravují scénáře své hry
 * předpokládající textové uživatelské rozhraní.
 * Tyto scénáře slouží ke snadnému otestování sdružené hry.
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
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public abstract class AScenarioManager
           implements IScenarioManager
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Přepravka s minimálními požadovanými "rozměry" úspěšného scénáře. */
    public static final Limits LIMITS = new Limits(
           10, //Kroků úspěšného scénáře
            6, //Zahlédnutých prostorů
            4, //Navštívených prostorů
            4  //Vlastních (=nepovinných) akcí
    );

    /** Poloprázdná třída hry používaná jako zástupný objekt v situaci,
     *  kdy pravá třída hry ještě nezačala být vyvíjena. */
    private static final Class<EmptyGame> EGS = EmptyGame.class;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Vrátí tovární objekt poskytující klíčové objekty ukázkové hry.
     *
     * @return Požadovaný tovární objekt
     */
    public static IGSMFactory getDefaultFactory()
    {
        IGSMFactory factory = new GSMFactoryTLocCZ_Apartment();
        return factory;
    }


    private static volatile IGUI4txt gui4txt;
    private static final Object GUI_4_TXT_SYNC = new Object();
    /***************************************************************************
     * Vrátí objekt reprezentující grafické uživatelské rozhraní
     * schopné spolupracovat s textovou verzí hry.
     *
     * @return Požadovaný objekt
     */
    public static IGUI4txt getGUI4txtGame()
    {
        if (gui4txt == null) {
            synchronized(GUI_4_TXT_SYNC) {
                if (gui4txt == null) {
                    new JFXPanel();
                    Platform.runLater(() ->
                    {
                        DefaultGUI4txt gui = new DefaultGUI4txt();
                        gui.start(new Stage());
                        gui4txt = gui;
                    });
                }
            }
            while (gui4txt == null) {
                try {
                    Thread.sleep(200);
                }
                catch (InterruptedException ex) {
                    throw new RuntimeException(
                        "\nNeočekávané probuzení ze spánku", ex);
                }
            }
        }
        return gui4txt;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Vytvoří nového správce scénářů na základě správce zadaného v parametru.
     * Vytvořený správce převezme od rozšiřovaného správce jeho data,
     * tj. jméno a xname autora, třídu hry a všechny jeho scénáře.
     * Scénář je vytvořen nezalepený!
     * Je proto připraven doplnit převzatou sadu scénářů o další,
     * které si vytvoří sám na základě zadaných parametrů.
     *
     * @param  extendedSM Rozšiřovaný správce scénářů
     * @return Požadovaný správce scénářů
     */
    public static AScenarioManager extend(AScenarioManager extendedSM)
    {
        ExtendingSM manager = new ExtendingSM(extendedSM);
        return manager;
    }



//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Třída, jejíž instance představují tovární objekty
     *  zprostředkovávající přístup ke klíčovým objektům aplikace. */
    private final Class<? extends IGSMFactory> factoryClass;

    /** Tovární objekt zprostředkující přístup ke klíčovým objektům hry. */
    private final IGSMFactory factory;

    /** Převodník názvu scénáře na daný scénář. */
    private final Map<String, Scenario> name2scenario;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Příznak ukončenosti přidávání dalších scénářů do správy.
     *  Hodnota {@code true} indikuje zalepeného správce,
     *  kterému již není možno přidat další scénář do správy. */
    private boolean sealed;

    /** Počáteční krok testovatelných scénářů. */
    private ScenarioStep START_STEP;

    /** Název scénáře, jehož počáteční krok jsme si zapamatovali. */
    private String START_NAME;

    /** Konkrétní hra (instance třídy {@link #factoryClass}, která je/byla/bude
     *  právě testována prostřednictvím scénářů tohoto správce. */
    private IGame game;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří nového správce scénářů spravujícího všechny scénáře hry,
     * která je instancí třídy zadané svým class-objektem.
     * <p>
     * Správce není po svém vytvoření ještě plně funkční - takovým se stane
     * až poté, co budou do jeho správy předány všechny požadované scénáře
     * a seznam spravovaných scénářů se zalepí.
     *
     * @param factoryClass  Class-objekt tovární třídy, jejíž instance
     *                      představují tovární objekty zprostředkovávající
     *                      přístup ke klíčovým objektům aplikace.
     */
    protected AScenarioManager(Class<? extends IGSMFactory> factoryClass)
    {
        if (factoryClass == null) {
            throw new IllegalArgumentException(
                "\nMísto class-objektu tovární třídy byl.dodán prázdný odkaz");
        }
        this.factoryClass  = factoryClass;
        this.factory       = IGSMFactory.getInstanceOfFactory(factoryClass);
        this.name2scenario = new LinkedHashMap<>();
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí instanci továrního objektu, který umí poskytnout
     * všechny klíčové objekty aplikace.
     *
     * @return Instance továrního objektu
     */
    @Override
    public Class<? extends IGSMFactory> getFactoryClass()
    {
        return factoryClass;
    }


    /***************************************************************************
     * Vrátí seznam názvů všech spravovaných scénářů.
     *
     * @return Seznam s názvy scénářů
     */
    @Override
    public final Collection<String> getAllScenarioNames()
    {
        verifySealed();
        return Collections.unmodifiableCollection(name2scenario.keySet());
    }


    /***************************************************************************
     * Vrátí scénář se zadaným názvem.
     *
     * @param name Název požadovaného scénáře
     * @return Scénář se zadaným názvem; není-li, vrátí {@code null}
     */
    @Override
    public final Scenario getScenario(String name)
    {
        verifySealed();
        Scenario result = name2scenario.get(name);
        if (result == null) {
            throw new IllegalArgumentException(
                    "\nScénář s názvem «" + name + "» nemám ve správě");
        }
        return result;
    }


    /***************************************************************************
     * Vrátí startovací krok testovatelných scénářů,
     * z nějž (přesněji řečeno z jeho zprávy) by měl být odvoditelný
     * námět příslušné hry.
     *
     * @return Startovací krok testovatelných scénářů
     */
    public final ScenarioStep getStartStep()
    {
        return START_STEP;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Vrátí iterátor, který bude postupně poskytovat
     * jednotlivé spravované scénáře v pořadí, v jakém byly zadány.
     *
     * @return Iterátor přes spravované scénáře
     */
    @Override
    public final Iterator<Scenario> iterator()
    {
        verifySealed();
        return name2scenario.values().iterator();
    }


    /***************************************************************************
     * Vrátí datovod, který bude postupně poskytovat
     * jednotlivé spravované scénáře v pořadí, v jakém byly zadány.
     *
     * @return Datovod spravovaných scénářů
     */
    @Override
    public final Stream<Scenario> stream()
    {
        verifySealed();
        return name2scenario.values().stream();
    }


    /***************************************************************************
     * Vytvoří nový scénář se zadaným názvem a posloupností kroků
     * a přidá jej mezi spravované scénáře.
     * Přitom kontroluje dodržení následujících omezení:
     * <ul>
     *   <li>Seznam scénářů ještě není zalepen a je proto ještě možno
     *       přidat další scénář.</li>
     *   <li>Prvním přidaným scénářem musí bý scénář typu
     *       {@link TypeOfScenario#scHAPPY}.
     *       Případný zadaný název je ignorován a scénáři se přiřadí název
     *       uložený v atributu {@link #HAPPY_SCENARIO_NAME}.</li>
     *   <li>Druhým přidaným scénářem musí bý scénář typu
     *       {@link TypeOfScenario#scMISTAKES}.
     *       Případný zadaný název je ignorován a scénáři se přiřadí název
     *       uložený v atributu {@link #MISTAKE_SCENARIO_NAME}.</li>
     *   <li>U každého dalšího přidávaného scénáře se jeho název
     *       musí lišit od názvů všech scénářů přidaných před ním.</li>
     *   <li>Každý přidávaný scénář musí začínat krokem s příkazem
     *       zadaným jako prázdný řetězec, což je startovací příkaz hry.
     *       Hra se po jeho zpracování dostane do svého výchozího stavu.
     * </ul>
     *
     * @param name  Název přidávaného scénáře (u prvního a druhého zadávaného
     *              scénáře se zadané názvy ignorují)
     * @param type  Typ přidávaného scénáře (na typ prvního a druhého
     *              přidávaného scénáře jsou kladeny výše popsané požadavky).
     * @param steps Kroky vytvářeného a následně přidávaného scénáře.
     * @throws IllegalStateException
     *         Přidávání scénářů již bylo uzavřeno
     * @throws IllegalArgumentException
     *         Není-li splněn některý z ostatních požadavků
     */
    protected final void addScenario(String name, TypeOfScenario type,
                                     ScenarioStep... steps)
    {
        Scenario scenario = new Scenario(name, type, this, steps);
        addScenario(scenario);
    }


    /***************************************************************************
     * Vytvoří nový scénář se zadaným názvem a
     * posloupností demonstračních kroků definovaných zadanými příkazy,
     * přiřadí mu typ {@link TypeOfScenario#scDEMO}
     * a přidá tento scénář mezi spravované scénáře.
     * Přitom kontroluje dodržení omezení popsaných v komentáři metody
     * {@link #addScenario(String, TypeOfScenario, ScenarioStep...)},
     * z čehož plyne, že tuto metodu je možno použít nejdříve
     * pro třetí přidávaný scénář.
     *
     * @param name      Název přidávaného scénáře
     * @param commands  Příkazy zadávané v jednotlivých krocích scénáře
     * @throws IllegalStateException
     *         Přidávání scénářů již bylo uzavřeno
     * @throws IllegalArgumentException
     *         Není-li splněn některý z ostatních požadavků
     */
    protected final void addScenario(String name, String... commands)
    {
        Scenario scenario = new Scenario(name, this, commands);
        addScenario(scenario);
    }


    /***************************************************************************
     * Přidá zadaný scénář mezi spravované. Přitom kontroluje, že přidávání
     * scénářů ještě nebylo uzavřeno (seznam scénářů ještě není zalepen)
     * a že scénář se zadaným názvem ještě nebyl přidán.
     *
     * @param scenario Přidávaný scénář
     * @exception IllegalArgumentException Není-li splněn některý z požadavků
     */
    protected final void addScenario(Scenario scenario)
    {
        if (sealed) {
            throw new IllegalStateException(
                "\nPředávání nových scénářů do správy již bylo uzavřeno");
        }
        String name = scenario.getName();
        if ((name == null)  ||  "".equals(name)) {
            throw new IllegalArgumentException(
                    "\nNázev scénáře musí být neprázdný řetězec");
        }
        Scenario previous = name2scenario.put(name, scenario);
        if (previous != null) {
            throw new IllegalArgumentException(
                "\nScénář se zadaným názvem již existuje: " + name);
        }
        ScenarioStep startStep = scenario.stream()
                    .filter(s -> (s.typeOfStep == TypeOfStep.tsSTART))
                    .findFirst()
                    .get();
        if (START_STEP == null) {
            START_STEP = startStep;
            START_NAME = scenario.getName();
        }
        else if (! START_STEP.equals(startStep)) {
            throw new IllegalArgumentException(
                "\nPočáteční kroky scénářů " + START_NAME +
                " a " + scenario.getName() + " se liší");
        }
    }

    /***************************************************************************
     * Zalepí seznam spravovaných scénářů a zavře zavře tak možnost
     * předávání dalších scénářů do správy daného správce.
     * Od této chvíle je správce plně funkční.
     */
    protected final void seal()
    {
        if ((null == name2scenario.get(HAPPY_SCENARIO_NAME  ))  ||
            (null == name2scenario.get(MISTAKE_SCENARIO_NAME))  )
        {
            throw new IllegalStateException(
                  "\nNebyly definovány povinné scénáře "
                + HAPPY_SCENARIO_NAME + " a " + MISTAKE_SCENARIO_NAME);
        }
        sealed = true;
    }


    /***************************************************************************
     * Základní test ověřující, jestli správce scénářů vyhovuje
     * zadaným okrajovým podmínkám.
     * Správce scénáře se automaticky prověří a výsledky vytisknou
     * již při provádění testu.
     */
    @Override
    public final void autoTest()
    {
        prepareSummary();
    }


    /***************************************************************************
     * Základní test ověřující, jestli správce scénářů vyhovuje
     * zadaným okrajovým podmínkám.
     * Správce scénáře se automaticky prověří a výsledky vytisknou
     * již při provádění testu.
     *
     * @return Přepravka s informacemi o hře získanými ze spravovaných scénářů
     */
    public final SMSummary prepareSummary()
    {
        prf("\nVerze frameworku: %s\n%s\n", Framework.VERSION, LIMITS);

        FactoryTester factoryTester = new FactoryTester(getFactory());
        factoryTester.testForLevel(FactoryTester.SM_LEVEL);

        ScenarioManagerTester tester  = new ScenarioManagerTester(this);
        SMSummary             summary = tester.verify();
        String s = (summary.ok)
                 ? "úspěšně PROŠEL autotestem"
                 : "NEPROŠEL testy - nejméně jeden scénář NEVYHOVĚL!";
        prf( N_HASHES_N + "Správce scénářů %s" + N_HASHES_N, s);
        return summary;
    }


    /***************************************************************************
     * Prověří, že hra pracuje podle povinných scénářů,
     * tj. podle základního úspěšného, ještě jednou podle základního úspěšného
     * a pak podle základního chybového.
     */
    @Override
    public final void testGame()
    {
        BasicSetTester tester = new BasicSetTester();
        tester.test(factory);
//        TGameTester tester = new TGameTester(
//                             IGSMFactory.getInstanceOfFactory(factoryClass),
//                             BasicSetTester::new);
//        tester.testGame();
    }


    /***************************************************************************
     * Prověří hru tak, že ji "zahraje" dle scénářů se zadanými názvy.
     * Zadaný scénář musí být testovací.
     *
     * @param names Názvy testovacích scénářů
     */
    @Override
    public final void testGameByScenarios(String... names)
    {
        GivenSetTester tester = new GivenSetTester(names);
        tester.test(factory);
    }


    /***************************************************************************
     * Vrátí textový podpis daného správce scénářů, v němž uvede
     * xname a jméno jeho autora, název třídy hry, k níž se vztahují
     * a seznam názvů všech spravovaných scénářů.
     * Podpis je pro přehlednost rozdělen na několik řádků.
     *
     * @return Textový podpis daného správce scénářů
     */
    @Override
    public String toString()
    {
        return "\nSprávce scénářů autora: " + getAuthorID()   + " - " +
                                               getAuthorName() +
               "\nTovární třída: "      + factory.getClass().getName()  +
               "\nTřída správce: "      + this   .getClass().getName()  +
               "\nSpravované scénáře: " + getAllScenarioNames();
    }


    /***************************************************************************
     * Ověří, že přidávání scénářů do správy ještě nebylo ukončeno,
     * tj. že seznam scénářů ještě není zalepen.
     *
     * @throws IllegalStateException Přidávání scénářů již bylo uzavřeno
     */
    protected final void verifyNotSealed() throws IllegalStateException
    {
        if (sealed) {
            throw new IllegalStateException(
                    "\nSeznam scénářů je již zalepen, => " +
                    "není již možno přidat další scénář");
        }
    }


    /***************************************************************************
     * Ověří, že přidávání scénářů do správy již bylo ukončeno,
     * tj. že seznam scénářů je již zalepen.
     *
     * @throws IllegalStateException Přidávání scénářů ještě nebylo ukončeno
     */
    protected final void verifySealed()
    {
        if (! sealed) {
            throw new IllegalStateException(
                "\nSprávce scénářů ještě není možno použít -" +
                "\nještě nebylo uzavřeno přidávání scénářů do jeho správy" +
                "\n(jinými slovy: správce ještě není \"zalepen\")");
        }
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

    /***************************************************************************
     * Instance třídy {@code ExtendingSM} jsou správci scénářů odvození
     * od existujících scénářů, jejichž seznam spravovaných scénářů rozšiřují.
     * Používají je testovací metody, které doplňují existující scénáře
     * vlastními (většinou syntetickými) scénáři.
     *
     * @author  Rudolf PECINOVSKÝ
     * @version 2018-Winter
     */
    private static class ExtendingSM extends AScenarioManager
    {
        /** Výchozí správce, kterého daný správce rozšiřuje. */
        final AScenarioManager source;


        /***********************************************************************
         * Vytvoří nového správce scénářů,
         * který bude rozšířením správce zadaného v parametru,
         * přesněji bude mít rozšířenou množinu spravovaných scénářů.
         *
         * @param source Výchozí rozšiřovaný správce scénářů
         */
        ExtendingSM(AScenarioManager source)
        {
            super(source.factory.getClass());
            this.source = source;
            //Pomocná proměnná umožňující přístup k soukromé rodičovské metodě
            //Použijeme-li v příkazu v těle cyklu místo aux this,
            //překladač oznámí syntaktickou chybu
            AScenarioManager aux = this;
            for (Scenario scenario : source) {
                //Výchozí scénář má již svůj seznam zkontrolovaný,
                //takže jej nemusím znovu kontrolovat a navíc si ušetřím
                //odvozování původních parametrů konstruktoru scénáře
                aux.addScenario(scenario);
            }
        }


        /***********************************************************************
         * Vrátí identifikační řetězec autora/autorky programu.
         *
         * @return Identifikační řetězec autora/autorky programu
         */
        @Override
        public String getAuthorID()
        {
            return source.getAuthorID();
        }


        /***********************************************************************
         * Vrátí jméno autora/autorky programu.
         *
         * @return Jméno autora/autorky programu
         */
        @Override
        public String getAuthorName()
        {
            return source.getAuthorName();
        }


        /***********************************************************************
         * Vrátí class-objekt tovární třídy, jejíž instance mohou zprostředkovat
         * přístup ke klíčovým objektům aplikace.
         *
         * @return Class-objekt tovární třídy
         */
        @Override
        public Class<? extends IGSMFactory> getFactoryClass()
        {
            return source.getFactoryClass();
        }


        /***********************************************************************
         * Vrátí odkaz na správce scénářů,
         * který se stal zdrojem daného rozšiřujícího správce.
         *
         * @return Zdrojový správce scénářů.
         */
        public AScenarioManager getSource()
        {
            return source;
        }
    }
}
