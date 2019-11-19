/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.game_txt_test;

import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.scenario.AScenarioManager;
import eu.pedu.adv19s_fw.scenario.Scenario;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;



/*******************************************************************************
 * Instance třídy {@code ASolutionTester} představují rodičovské podobjekty
 * testovacích objektů prověřujících správnou realizaci daného zadání.
 * Toto zadání může být jak základního zadání semestrální práce,
 * tak modifikované zadání, které studenti řeší v rámci obhajoby své práce.
 * <p>
 * Detaily toho,
 * jak a co testovat při ověřování správného naprogramování příslušného zadání,
 * jsou definovány prostřednictvím objektu návštěvníka předaného testům.
 * Testy pak v kritických chvílích volají jeho předem definované metody,
 * a ty pak ověří správnost zapracování příslušných modifikací.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public abstract class ASolutionTester
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Mapa konvertující ID jednotlivých zadání na odpovídající testery,
     *  které jsou schopny zkontrolovat příslušné řešení. */
    private static final Map<String, ASolutionTester> ID_2_TESTER =
                                                      new HashMap<>();

    /** Implicitní sada názvů scénářů, podle nichž se testuje. */
    private static final String[] DEFAULT_TESTING_SCENARIO_NAMES =
                                { AScenarioManager.HAPPY_SCENARIO_NAME,
                                  AScenarioManager.HAPPY_SCENARIO_NAME,
                                  AScenarioManager.MISTAKE_SCENARIO_NAME };



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Vrátí tester schopný prověřit zadání s příslušným ID
     */
    static ASolutionTester getTesterFor(String id)
    {
        ASolutionTester tester = ID_2_TESTER.get(id);
        if (tester == null) {
            throw new RuntimeException(
                "\nThere is no tester with the ID=" + id);
        }
        return tester;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Identifikační řetězec zadání sestávající z indikátoru semestru
     *  následovaného dvojmístným číslem modifikačního zadání pro obhajobu.
     *  Indikátor výchozího řešení bez modifikací má číslo 00.
     */
    private final String id;

    /** Popis požadovaného řešení. */
    private final String description;

    /** Návštěvník zabezpečující prověření specifik daného testu. */
    protected final BiFunction<ASolutionTester, IGSMFactory,
                               ATestVisitor> visitorFactory;

    /** Názvy scénářů, podle nichž se řešení testuje. */
    protected final List<String> testingScenarioNames;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Přepravka na informace o testu a jeho výsledcích. */
    protected GameSummary gameSummary;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================
//
//    /***************************************************************************
//     * Vytvoří rodičovský podobjekt pro zadaného návštěvníka a sadu scénářů.
//     *
//     * @param id             Identifikátor testovaného zadání sestávající
//     *                       z označení semestru a čísla daného zadání
//     * @param visitorFactory Továrna na návštěvníky schopné prověřit správnost
//     *                       testovaného řešení
//     * @param scenarioNames  Názvy scénářů, podle nichž bude testováno
//     */
//    protected ASolutionTester(String id,
//                              BiFunction<ASolutionTester, IGSMFactory,
//                                         ATestVisitor> visitorFactory,
//                             String... scenarioNames)
//    {
//        this(id, "", visitorFactory, scenarioNames);
//    }
//

    /***************************************************************************
     * Vytvoří rodičovský podobjekt pro zadaného návštěvníka a sadu scénářů.
     *
     * @param id             Identifikátor testovaného zadání sestávající
     *                       z označení semestru a čísla daného zadání
     * @param description    Popis testovaného zadání
     * @param visitorFactory Továrna na návštěvníky schopné prověřit správnost
     *                       testovaného řešení
     * @param scenarioNames  Názvy scénářů, podle nichž bude testováno
     */
    protected ASolutionTester(String id, String description,
                              BiFunction<ASolutionTester, IGSMFactory,
                                         ATestVisitor> visitorFactory,
                             String... scenarioNames)
    {
        ID_2_TESTER.put(id, this);
        this.id             = id;
        this.description    = description;
        this.visitorFactory = visitorFactory;

        String[] names = ((scenarioNames==null)  ||  (scenarioNames.length==0))
                       ? DEFAULT_TESTING_SCENARIO_NAMES
                       : scenarioNames;
        this.testingScenarioNames = Arrays.asList(names);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí identifikační řetězec zadání testovaného daným, testerem.
     *
     * @return Identifikační řetězec zadání testovaného daným, testerem
     */
    public String getId()
    {
        return id;
    }


    /***************************************************************************
     * Vrátí popis testovaného zadání.
     *
     * @return Popis testovaného zadání
     */
    public String getDescription()
    {
        if (description.isEmpty()) {
            return "Podrobný popis zadání je uveden "
                 + "v dokumentačním komentáři třídy";
        }
        else {
            return description;
        }
    }


    /***************************************************************************
     * Vrátí seznam scénářů, podle nichž se má dané řešení testovat.
     *
     * @param factory Tovární objekt poskytující testovanou hru
     * @return Seznam scénářů, podle nichž se má dané řešení testovat
     */
    public List<Scenario> getScenarios(IGSMFactory factory)
    {
        AScenarioManager manager = factory.getScenarioManager();
        List<Scenario> result;
        result = testingScenarioNames
                .stream()
                .map(name -> manager.getScenario(name))
                .collect(Collectors.toList());
        return result;
    }


    /***************************************************************************
     * Vrátí seznam názvů scénářů, podle nichž se má dané řešení testovat.
     *
     * @return Seznam názvů scénářů, podle nichž se má dané řešení testovat
     */
    protected List<String> getScenarioNames()
    {
        return Collections.unmodifiableList(testingScenarioNames);
    }


    /***************************************************************************
     * Nastaví názvy scénářů, podle nichž se má dané řešení testovat.
     *
     * @param scenarioNames Seznam názvů scénářů,
     *                      podle nichž se má dané řešení testovat
     */
    protected void setScenarioNames(List<String> scenarioNames)
    {
        testingScenarioNames.clear();
        testingScenarioNames.addAll(scenarioNames);
    }


    /***************************************************************************
     * Vrátí návštěvníka testujícího hru poskytovanou zadaným továrním objektem.
     *
     * @param factory Tovární objekt poskytující testovanou hru
     * @return Požadovaný návštěvník
     */
    public ATestVisitor getVisitor(IGSMFactory factory)
    {
        return visitorFactory.apply(this, factory);
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Nastaví názvy scénářů, podle nichž se má dané řešení testovat.
     *
     * @param name Název scénáře, jenž se má přidat na konec seznamu názvů
     *             scénářů, podle nichž se má dané řešení testovat
     */
    protected void addScenarioName(String name)
    {
        testingScenarioNames.add(name);
    }


    /***************************************************************************
     * Vypíše zadání na standardní výstup a poté
     * zobrazí dialogové okno, v němž je vypíše také.
     */
    public void showDescription()
    {
        System.out.println("Požadovaná modifikace:\n" + description);
        JOptionPane.showMessageDialog(null,
                   "<html>" + description + "</html>",
                   id + " – Solution description",
                   JOptionPane.INFORMATION_MESSAGE);
//        JFrame frame = new JFrame(id + " – Solution description");
    }


    /***************************************************************************
     * Otestuje práci zadanou prostřednictvím class-objektu její tovární třídy
     * podle základní sady scénářů.
     *
     * @param factoryClass  Class-objekt tovární třídy testované práce
     */
    final
    public void test(Class<? extends IGSMFactory> factoryClass)
    {
        IGSMFactory factory = IGSMFactory.getInstanceOfFactory(factoryClass);
        test(factory);
    }


    /***************************************************************************
     * Otestuje práci zadanou prostřednictvím jejího továrního objektu
     * podle sady scénářů odpovídajících danému testu.
     *
     * @param factory  Tovární objekt testované práce
     */
    public void test(IGSMFactory factory)
    {
        TGameTester tester = new TGameTester(this, factory);
        tester.testGame();
    }


    /***************************************************************************
     * Otestuje práci zadanou prostřednictvím jejího továrního objektu
     * podle sady scénářů odpovídajících danému testu.
     */
    public void test()
    {
        throw new IllegalArgumentException(
            "\nNebyla zadána tovární třída, resp. její instance!");
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    public static  class MockFactory
              implements IGSMFactory
    {
        @Override public String getAuthorID  () { return ""; }
        @Override public String getAuthorName() { return ""; }
    }
    public static final IGSMFactory NOTHING = new MockFactory();

//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

}
