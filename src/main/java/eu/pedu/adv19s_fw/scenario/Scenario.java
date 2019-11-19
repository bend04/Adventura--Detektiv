/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.scenario;

import eu.pedu.adv19s_fw.game_txt.INamed;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static eu.pedu.adv19s_fw.utilities.FormatStrings.*;



/*******************************************************************************
 * Instance třídy {@code Scenario} představují scénáře,
 * podle kterých je možno hrát hru, pro kterou jsou určeny.
 * Aby bylo možno jednotlivé scénáře od sebe odlišit,
 * je každý pojmenován a má přiřazen typ, podle které lze blíže určit,
 * k čemu je možno daný scénář použít.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public   class Scenario
    implements INamed, Iterable<ScenarioStep>
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================

    /***************************************************************************
     * Z pole se zadanou posloupností příkazů
     * vytvoří pole odpovídajících demonstračních kroků scénáře,
     * tj. kroků, které nebudou umožňovat testování,
     * ale budou sloužit pouze k simulaci průběhu hry.
     *
     * @param  commands Pole s posloupností zadávaných příkazů
     * @return Pole demonstračních kroků scénáře
     */
    private static ScenarioStep[] prepareSteps(String[] commands)
    {
        ScenarioStep.clearIndex();
        ScenarioStep[] steps = new ScenarioStep[commands.length+1];
        ScenarioStep step = new ScenarioStep("");
        int i = 0;
        for(;;) {
            steps[i] = step;
            if (i >= commands.length) { break; }     //==========>
            step = new ScenarioStep(commands[i]);
            i++;
        }
        steps[i] = new ScenarioStep(step);
        return steps;
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Název scénáře. */
    private final String name;

    /** Typ scénáře. */
    private final TypeOfScenario type;

    /** Správce spravující všechny scénáře dané hry. */
    private final AScenarioManager scenarioManager;

    /** Jednotlivé kroky daného scénáře. */
    private final List<ScenarioStep> steps;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Scenario signature - it is created on demand. */
    private String toStringValue;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří pro zadaného správce scénářů nový scénář,
     * který bude kopií zadaného scénáře.
     *
     * @param scenarioManager  Správce scénářů, pro nějž je scénář vytvářen
     * @param scenario         Kopírovaný scénář
     */
    public Scenario(AScenarioManager scenarioManager, Scenario scenario)
    {
        this(scenario.name, scenario.type, scenarioManager,
             scenario.steps.toArray(new ScenarioStep[scenario.steps.size()]));
    }


    /***************************************************************************
     * Vytvoří nový scénář zadaného názvu a typu určený pro zadanou hru.
     * Konstruktor prověří, jestli kroky scénáře alespoň formálně
     * vyhovují požadavkům.
     *
     * @param name  Název scénáře. Název musí být neprázdný řetězec
     *              neobsahující úvodní a závěrečné mezery
     * @param type  Typ scénáře
     * @param scenarioManager Správce, jenž bude vytvořený scénář spravovat
     *              (a který si jej pro sebe vytváří)
     * @param steps Jednotlivé kroky realizující daný scénář;
     *              příkaz v prvním (přesněji nultém) kroku
     *              musí být definována jako prázdný řetězec s výjimkou případu,
     *              kdy je testován krok typu {@link TypeOfStep#tsNOT_START}
     */
    public Scenario(String name, TypeOfScenario type,
                    AScenarioManager scenarioManager, ScenarioStep... steps)
    {
        this.name             = name.trim();
        this.type             = type;
        this.scenarioManager  = scenarioManager;

        ScenarioStep[] closedSteps = new ScenarioStep[steps.length];
        int iLast = steps.length-1;
        System.arraycopy(steps, 0, closedSteps, 0, iLast);
        closedSteps[iLast] = new ScenarioStep(steps[iLast], true);
        List<ScenarioStep> resulSteps  = Arrays.asList(closedSteps);
        verifyArguments(resulSteps);
        this.steps = Collections.unmodifiableList(resulSteps);
    }


    /***************************************************************************
     * Vytvoří nový demonstrační scénář zadaného názvu, který bude obsahovat
     * pouze zadání příkazů a nebude umožňovat kontrolu správných reakcí hry.
     * <p>
     * Parametry musí odpovídat kontraktu uvedenému u čtyřparametrické verze.
     *
     * @param name             Název scénáře v rámci scénářů dané hry
     * @param scenarioManager  Správce scénářů, pro nějž je scénář vytvářen
     * @param commands         Posloupnost akcí realizujících daný scénář
     */
    public Scenario(String name, AScenarioManager scenarioManager,
                    String... commands)
    {
        this(name, TypeOfScenario.scDEMO, scenarioManager,
             prepareSteps(commands));
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí název scénáře.
     *
     * @return Název scénáře
     */
    @Override
    public String getName()
    {
        return name;
    }


    /***************************************************************************
     * Vrátí typ scénáře.
     *
     * @return  Typ scénáře
     */
    public TypeOfScenario getType()
    {
        return type;
    }


    /***************************************************************************
     * Vrátí svého správce scénářů.
     *
     * @return  Správce scénářů spravující a zpříástupňující daný scénář
     */
    public AScenarioManager getManager()
    {
        return scenarioManager;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Vrátí iterátor umožňující procházet kroky daného scénáře.
     *
     * @return  Iterátor kroků scénáře
     */
    @Override
    public Iterator<ScenarioStep> iterator()
    {
        return steps.iterator();
    }


    /***************************************************************************
     * Vypíše na standardní výstup simulovaný průběh hry podle daného scénáře.
     */
    public void simulate()
    {
        String managerAndAuthor =
                  "\nSprávce scénářů " + scenarioManager.getClass().getName()
                + "\nAutor: " + scenarioManager.getAuthorName() + " — "
                              + scenarioManager.getAuthorID();

        if (type == TypeOfScenario.scDEMO) {
            throw new IllegalStateException(
                "\nDemonstrační scénář neumožňuje přehrát simulaci");
        }
        System.out.println(N_HASHES_N
            + "Simulace scénáře: " + name
            + managerAndAuthor + N_DOUBLELINE_N);
        stream().forEach(
            (step) -> {
                String s = "Příkaz: " + step.command
                         + N_LINE_N + step.message + N_DOUBLELINE_N;
                System.out.println(s);
            });
        System.out.println(N_DOUBLELINE_N
            + "Konec simulace scénáře: " + name
            + "\nSprávce scénářů " + scenarioManager.getClass().getName()
            + "\nAutor: " + scenarioManager.getAuthorName() + " — "
                          + scenarioManager.getAuthorID()
            + N_HASHES_N);
    }


    /***************************************************************************
     * Vrátí datovod kroků daného scénáře.
     *
     * @return Požadovaný datovod
     */
    public Stream<ScenarioStep> stream()
    {
        return steps.stream();
    }


    /***************************************************************************
     * Vrátí text obsahující název daného scénáře, jeho typ
     * a podpis jeho správce.
     *
     * @return Textový toStringValue daného scénáře
     */
    @Override
    public String toString()
    {
        if (toStringValue == null) {
            AScenarioManager asm = getManager();
            toStringValue = "Scénář(Název="    + getName()
                          + ", Typ="           + getType()
                          + ", Třída správce=" + asm.getClass().getName()
                          + ")";
        }
        return toStringValue;
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Prověří zadané hodnoty parametrů,
     * jestli alespoň formálně vyhovují požadavkům.
     *
     * @param name  Název scénáře. Název musí být neprázdný řetězec
     *              a nesmí obsahovat úvodní ani závěrečné mezery.
     * @param type  Typ scénáře
     * @param scenarioManager Správce, jenž bude vytvořený scénář spravovat
     *              (a který si jej pro sebe vytváří)
     * @param steps Jednotlivé kroky realizující daný scénář;
     *              příkaz v prvním (přesněji nultém) kroku
     *              musí být definována jako prázdný řetězec s výjimkou případu,
     *              kdy je testován krok typu {@link TypeOfStep#tsNOT_START}
     */
    private void verifyArguments(List<ScenarioStep> steps)
            throws IllegalArgumentException
    {
        int     STEPS;      //Počet kroků scénáře
        String  error;
        ERROR:
        {
            if (steps == null) {
                STEPS = 0;
                error = "Místo seznamu kroků byl zadán prázdný odkaz";
                break ERROR;
            }
            STEPS = steps.size();
            if (name == null) {
                error = "Jako názevc scénáře byl zadán prázdný odkaz";
                break ERROR;
            }
            if (! name.trim().equals(name)) {
                error = "Název scénáře obsahuje " +
                        "úvodní a/nebo závěrečné mezery";
                break ERROR;
            }
            if ("".equals(name)) {
                error = "Název scénáře je prázdný řetězec";
                break ERROR;
            }
            if (type == null) {
                error = "Scénář nemá definován svůj typ";
                break ERROR;
            }
            if (scenarioManager == null) {
                error = "Scénář nemá definovaného svého správce";
                break ERROR;
            }
            if (steps.isEmpty()) {
                error = "Scénář nemá definován žádný krok";
                break ERROR;
            }
            if (type == TypeOfScenario.scDEMO) {
                //Kroky demonstračních scénářů netestujeme
                return;
            }
            if ((error = testItems(steps)).length() > 0) {
                break ERROR;
            }
            if ((error = testStartSteps(steps)).length() > 0) {
                break ERROR;
            }
            error = null;
        }
        if ((error != null) && (error.length() > 0)) {
            throw new IllegalArgumentException(
                "\nOdhalená chyba: «" + error + "»"
              + "\nHodnoty parametrů konstruktoru neodpovídají kontraktu."
              + "\n   Název: «" + name + "»"
              + "\n   Počet kroků: " + (steps == null  ?  "NULL"  :  STEPS)
              + "\n   Startovací příkaz: «" + ((STEPS==0) || (steps == null)
                                            ? "NULL"
                                            : steps.get(0).command + "»"));
        }
    }


    /***************************************************************************
     * Prověří, že všechny položky všech kroků jsou formálně platné, tj.:
     * <ul>
     *   <li>položky vektoru neobsahují prázdné odkazy
     *       (tj. žádný krok není zadán jako {@code null}),</li>
     *   <li>atributy odkazovaných kroků neobsahují prázdné odkazy
     *     a s výjimkou položky {@code command } ani prázdné řetězce,</li>
     *   <li>položky vektorů {@code neighbors}, {@code items} a {@code bag}
     *     neobsahují prázdné odkazy ani prázdné řetězce.</li>
     * </ul>
     *
     * @param steps Kroky scénáře
     * @return Je-li vše v pořádku, vrátí prázdný řetězec,
     *         v opačném případě vrátí chybovou zprávu
     */
    private String testItems(List<ScenarioStep> steps)
    {
        Optional<String> obtained = //for (ScenarioStep step : steps) {
            steps.stream()
                 .map   (step -> testItem(step))
                 .filter(msg  -> ! msg.isEmpty())   //Zpráva o chybě
                 .findFirst();
        return obtained.orElse("");
    }


    /***************************************************************************
     * Prověří, že zadaný krok scénáře je formálně platný, tj.:
     * <ul>
     *   <li>neobsahuje prázdné odkazy,</li>
     *   <li>s výjimkou položky {@code command }
     *       neobsahuje ani prázdné řetězce,</li>
     *   <li>položky vektorů {@code neighbors}, {@code items} a {@code bag}
     *     neobsahují prázdné odkazy ani prázdné řetězce.</li>
     * </ul>
     *
     * @param step Krok scénáře
     * @return Je-li vše v pořádku, vrátí prázdný řetězec,
     *         v opačném případě vrátí chybovou zprávu
     */
    private String testItem(ScenarioStep step)
    {
        if (step == null) {
            return "Místo dalšího kroku scénáře je prázdný odkaz";
        }
        if ((step.bag        == null)  ||
            (step.command    == null)  ||
            (step.message    == null)  ||
            (step.neighbors  == null)  ||
            (step.items      == null)  ||
            (step.place       == null)  ||
            (step.typeOfStep == null)  )
        {
            return step.index
                     + ". krok scénáře obsahuje položku s prázdným odkazem";
        }
        String  message;
        boolean correct =
            ((message = testSubItem("obsah batohu",
                                        step.bag      )).isEmpty())  &&
            ((message = testSubItem("sousedy ktuálního prosturu",
                                        step.neighbors)).isEmpty())  &&
            ((message = testSubItem("objekty v aktuálním prostoru",
                                        step.items    )).isEmpty());
        if (! correct) {
            return step.index + ". krok scénáře - " + message;
        }
        return "";
    }


    /***************************************************************************
     * Prověří, že ve vektorových položkách zadaného kroku scénáře
     * nejsou prázdné odkazy ani prázdné řetězce.
     *
     * @param step Testovaný krok scénáře
     * @return Je-li vše v pořádku, vrátí prázdný řetězec, v opačném případě
     *         vrátí část chybové zprávy
     */
    private String testSubItem(String description, String[] vector)
    {
        Optional<String> obtained = Arrays.stream(vector)
            .map(s ->
                {   //Převede položku na chybové hlášení
                    boolean ref;
                    if ((ref = (s == null))  ||  s.isEmpty())
                    {
                        return "vektor definující " + description
                             + " obsahuje prázdný " + (ref ? "odkaz"
                                                           : "řetězec");
                    }
                    return "";
                })
            .filter(s -> !s.isEmpty())
            .findFirst();
        return obtained.orElse("");
    }


    /***************************************************************************
     * Ověří, že scénář začíná prázdným příkazem, před nímž smějí předcházet
     * pouze kroky testující špatně zadané odstartování hry.
     *
     * @param steps Kroky testovaného scénáře
     * @return Chybofá zpráva; je-li vše OK, vrátí prázdný řetězec
     */
    private String testStartSteps(List<ScenarioStep> steps)
    {
        boolean started = false;
        for (ScenarioStep step : steps) {
            //Předstartovní příkazy
            if (started) {
                if ((step.typeOfStep == TypeOfStep.tsNOT_START)  ||
                    (step.typeOfStep == TypeOfStep.tsSTART))
                {
                    return "Po odstartování hry nesmí být zadán příkaz typu " +
                           step.typeOfStep;                     //==========>
                }
            }
            //Není odstartováno
            else if (step.typeOfStep == TypeOfStep.tsNOT_START) {
                if (step.command.isEmpty()) {
                    return "Příkaz, jenž je testem reakce na špatné spuštění," +
                           " má jako příkaz zadán prázdný řetězec";
                }                                               //==========>
                else {
                    continue;       //^^^^^^^^^^
                }
            }
            //Startovní příkaz
            else if (step.typeOfStep == TypeOfStep.tsSTART) {
                if (step.command.isEmpty()) {
                    started = true;
                    continue;       //^^^^^^^^^^
                }
                else {
                    return "Jako startovací příkaz není zadán prázdný řetězec";
                }                                               //==========>
            }
            //Špatný start
            else {
                return "Jako startovací musí být zadán příkaz typu " +
                       TypeOfStep.tsSTART;
            }                                                   //==========>
        }
        return "";
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
