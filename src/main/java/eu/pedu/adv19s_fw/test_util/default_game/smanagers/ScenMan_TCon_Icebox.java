/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.smanagers;

import eu.pedu.adv19s_fw.scenario.AScenarioManager;
import eu.pedu.adv19s_fw.scenario.ScenarioStep;
import eu.pedu.adv19s_fw.scenario.TypeOfScenario;
import eu.pedu.adv19s_fw.test_util.default_game.IAuthorDemo;
import eu.pedu.adv19s_fw.test_util.default_game.InitProperties;

import java.util.List;



/*******************************************************************************
 * {@code ScenMan_TCon_Icebox} instance serves as
 * scenario manager, that has to manage the scenarios of the associated game.
 * These scenarios should allow to test and demonstrate
 * the functionality of the associated game.
 * In this class the texts are defined as literals.
 * <p>
 * Each manager has to offer:
 * <ul>
 *   <li>The <b>happy scenario</b> (the basic successful one)
 *     demonstrating certain successful path through the game possibilities
 *     leading to the game goal.
 *   </li>
 *   <li>The <b>mistake scenario</b>
 *     demonstrating the game reaction to the wrongly entered commands.
 *   </li>
 * </ul>
 * <p>
 * Individual managed scenarios have to differ by their names;
 * the names of the happy scenario and the mistake one
 * are firmly pre-determined and cannot be arbitrarily set.
 * <p>
 * Individual scenarios are iterable and "streamable" sequences of steps
 * specified by instances of the framework class
 * {@link eu.pedu.adv19s_fw.scenario.ScenarioStep},
 * to which the designed game should be associated.
 * <p>
 * Scenario manager is a singleton, that is responsible
 * for all scenarios concerning the game associated with it.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public   class ScenMan_TCon_Icebox
       extends AScenarioManager
    implements IAuthorDemo
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Name of the scenario with steps for testing mandatory actions. */
    private static final String REQUIRED_STEPS_SCENARIO_NAME = "REQUIRED";

    /** The only instance of this class.
     *  It manages all scenarios of the associated game. */
    private static final ScenMan_TCon_Icebox MANAGER =
                                             new ScenMan_TCon_Icebox();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================



//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Returns scenario manager - the only instance of this class.
     *
     * @return Scenario manager
     */
    public static ScenMan_TCon_Icebox getInstance()
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
     * Creates an instance representing the game scenario manager.
     * Within the constructor framework it is suitable to create all scenarios
     * and seal the scenario manager after it.
     * <p>
     * Individual managed scenarios have to differ by their names,
     * the names of the happy scenario and the mistake one
     * are firmly pre-determined and cannot be changed.
     * Names given to them in the
     * {@link #addScenario(String, TypeOfScenario, ScenarioStep...)} method
     * are therefore only formal, because the called method assigns to them
     * the names defined in advance in appropriate constants.
     * <p>
     * Contract of the
     * {@link #addScenario(String, TypeOfScenario, ScenarioStep...)} method
     * requires so that the happy scenario, i.e. scenario of the
     * {@link TypeOfScenario.scHAPPY}) type, would be added as the first one,
     * and the mistake scenario, i.e. the scenario of the
     * {@link MISTAKE_SCENARIO_NAME} type, as the second one.
     * The order of the subsequently added scenarios is not decisive.
      */
    private ScenMan_TCon_Icebox()
    {
        super(InitProperties.getInstance().factoryClass);

        List<ScenarioStep[]> list = ScenarioStepsCon.getScenarioSteps();
        addScenario(HAPPY_SCENARIO_NAME,
                    TypeOfScenario.scHAPPY,  list.get(0));
        addScenario(MISTAKE_SCENARIO_NAME,
                    TypeOfScenario.scMISTAKES, list.get(1));
        addScenario(REQUIRED_STEPS_SCENARIO_NAME,
                    TypeOfScenario.scGENERAL,  list.get(2));
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
     * Method verifying the given scenario manager and the associated game
     * by scenarios of this manager.
     * <p>
     * The scenario manager is verified if it complies
     * with the following conditions:
     * <ul>
     *   <li>It knows to return properly formated name of the game author
     *       and his/her ID.</li>
     *   <li>It defines the happy scenario and the mistake one.</li>
     *   <li>The mistake scenario has the following properties:
     *     <ul>
     *       <li>Starting command, the name of which is an empty string</li>
     *       <li>Minimal required number of steps</li>
     *       <li>Minimal number of visited places</li>
     *       <li>Minimal number of "glimpsed" places</li>
     *       <li>Minimal number of own (optional) actions</li>
     *       <li>Usage of actions for moving from the current place
     *         to a neighboring place, taking item and putting down item</li>
     *       <li>Cross consistence of actions and states after execution
     *         of the actions</li>
     *     </ul>
     *   </li>
     *   <li>The mistake scenario has the following properties:
     *     <ul>
     *       <li>Incorrect starting by a not empty command,</li>
     *       <li>Starting command the name of which is an empty string</li>
     *       <li>Usage of all mandatory error step types defined in the<br>
     *         {@link eu.pedu.adv19s_fw.scenario.TypeOfStep} enum type</li>
     *       <li>Asking for a help</li>
     *       <li>Premature game termination</li>
     *     </ul>
     *   </li>
     * </ul>
     * <p>
     * The game is verified if it can be played exactly
     * as is planned in scenarios.
     *
     * @param args Command line parameters - unused.
     */
    public static void main(String[] args)
    {
        //Tests if the scenario manager and its scenarios
        //comply with requirements
        MANAGER.autoTest();

        //Simulates playing the game according to happy scenario
//        MANAGER.getHappyScenario().simulate();

        //Game testing according to scenarios with the given names
//        MANAGER.testGameByScenarios(REQUIRED_STEPS_SCENARIO_NAME);

        //Game testing made gradually according to both mandatory scenarios,
        //the happy scenario is passed twice one after the other
        MANAGER.testGame();

        //Game testing made gradually according to both mandatory scenarios,
        //the happy scenario is passed twice one after the other
//        MANAGER.testGameG();

        //Playing the game according to the scenario with the given name
//        MANAGER.playGameByScenario("???");

        System.exit(0);
    }

}
