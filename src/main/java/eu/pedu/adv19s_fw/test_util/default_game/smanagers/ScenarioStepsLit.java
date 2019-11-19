/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.smanagers;

import eu.pedu.adv19s_fw.scenario.ScenarioStep;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsBAD_ITEM;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsBAD_NEIGHBOR;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsBAG_FULL;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsDIALOG;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsEMPTY;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsEND;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsHELP;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsMOVE;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsMOVE_WA;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsNON_STANDARD1;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsNON_STANDARD2;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsNOT_IN_BAG;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsNOT_START;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsNS1_0Args;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsNS1_WRONG_ARG;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsNS2_1Args;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsNS2_WRONG_1stARG;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsPUT_DOWN;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsPUT_DOWN_WA;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsSTART;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsTAKE;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsTAKE_WA;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsUNKNOWN;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsUNMOVABLE;



/*******************************************************************************
//%L+ CZ
 * Třída {@code ScenarioStepsLit} slouží jako přepravka objektů
 * definujících kroky šťastného a chybového scénáře, přičemž hodnoty
 * atributů těchto kroků jsou zadávány prostřednictvím literálů.
//%Lx EN
 * The {@code ScenarioStepsLit} class serves as a crate of objects
 * defining the happy and mistake scenarios steps, where the values
 * of the attributes of these steps are entered as literals.
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class ScenarioStepsLit
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Auxiliary constants for conversation with the ice-box. */
    private static final int AGE = 20;
    private static final int THIS_YEAR;
    private static final int BORN_YEAR;
    static {
        THIS_YEAR = LocalDate.now().getYear();
        BORN_YEAR = THIS_YEAR - AGE;
    }


    /***************************************************************************
     * The starting step identical for all scenarios.
     * <p>
     * Constructor of the full-fledged {@link ScenarioStep} class instance
     * requires the following parameters:
     <pre> {@code
TypeOfStep typeOfStep; //Type of the given scenario step
String     command;    //Command realizing this scenario step
String     message;    //Message written after entering the command
String     place;      //Current place after entering the command
String[]   neighbors;  //Neighbors of the current place (= exits)
String[]   items;      //Items occuring in the current place
String[]   bag;        //Current bag content
     }</pre>
     =======================================================================<br>
     * Scenario steps have to comply with the following requirements:
     * <ul>
     *   <li>None of the items may contain the {@code null} value.</li>
     *   <li>With the exception of {@code command} no string may be
     *     empty (i.e. {@code ""})</li>
     *   <li>Empty string may occur neither as an item in the array
     *     {@code neighbors}, nor {@code items} nor {@code bag}</li>
     * </ul>
     * <br>
     ***************************************************************************
     */
    private static final ScenarioStep START_STEP =
        new ScenarioStep(0, tsSTART,  "", //Starting command
            "Welcome in the official apartment. You are surely hungry.\n"
          + "Find the ice-box - refreshment is waiting you there.\n"
//          + "However, the room is too dark. You should first switch lights up."
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Shoe-rack", "Umbrella" },
            new String[] {}
        );


    /***************************************************************************
     * Steps of the happy scenario
     * describing the expectable successful game running. It is not necessary
     * for the scenario compiled of these steps to be the shortest possible
     * (it implies, that it has not to be the true basic successful scenario),
     * but it has to comply with all marginal conditions of the assignment,
     * i.e. it has to contain minimal number of steps,
     * pass through the required minimal number of places
     * and demonstrate using of all required actions.
     */
    private static final ScenarioStep[] HAPPY_SCENARIO_STEPS =
    {
        START_STEP
        ,
//        new ScenarioStep(tsNON_STANDARD0, "Light_up",
//            "You have switched the lights up"
//            ,
//            "Hall",
//            new String[] { "Bedroom", "Living-room", "Bathroom" },
//            new String[] { "Shoe-rack", "Umbrella" },
//            new String[] {}
//        )
//        ,
        new ScenarioStep(tsMOVE, "goto bathroom",
            "You have moved to a room: Bathroom"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Glasses", "Sink", "Magazine" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, "take glasses",
            "You have taken an item: Glasses"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Sink", "Magazine" },
            new String[] { "Glasses" }
        )
        ,
        new ScenarioStep(tsTAKE, "take magazine",
            "You have taken an item: Magazine"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Sink" },
            new String[] { "Glasses", "Magazine" }
        )
        ,
        new ScenarioStep(tsMOVE, "goto hall",
            "You have moved to a room: Hall"
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Shoe-rack", "Umbrella" },
            new String[] { "Glasses", "Magazine" }
        )
        ,
        new ScenarioStep(tsMOVE, "goto living-room",
            "You have moved to a room: Living-room"
            ,
            "Living-room",
            new String[] { "Kitchen", "Hall" },
            new String[] { "TV" },
            new String[] { "Glasses", "Magazine" }
        )
        ,
        new ScenarioStep(tsMOVE, "goto kitchen",
            "You have moved to a room: Kitchen"
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Glasses", "Magazine" }
        )
        ,
        new ScenarioStep(tsBAG_FULL, "take paper",
            "The entered action has not been carried out.\n"
          + "You cannot take the entered item, "
          + "both your hands are already full."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Glasses", "Magazine" }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, "put_down magazine",
            "You have put down an item: Magazine"
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper", "Magazine" },
            new String[] { "Glasses" }
        )
        ,
        new ScenarioStep(tsTAKE, "take paper",
            "You have taken an item: Paper"
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Magazine" },
            new String[] { "Glasses", "Paper" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "read paper",
            "You have decided to read the paper.\n"
          + "However, text is written with too small letters that are blurry.\n"
          + "You have to take on glasses."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Magazine" },
            new String[] { "Glasses", "Paper" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "put_on glasses",
            "You have put glasses on."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Magazine" },
            new String[] { "Paper" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "read paper",
            "You have decided to read the paper.\n"
          + "It is written at the paper:\n"
          + "Ice-box stands askew and therefore it opens with difficulty.\n"
          + "In case of problems, underlay it with something."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Magazine" },
            new String[] { "Paper" }
        )
        ,
        new ScenarioStep(tsTAKE, "take magazine",
            "You have taken an item: Magazine"
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box" },
            new String[] { "Magazine", "Paper" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2, "underlay ice-box magazine",
            "You have decided to underlay ice-box with magazine.\n"
          + "The entered action has not been carried out.\n"
          + "Unfortunately your both hands are full\n"
          + "and thus you have no empty hand for execution."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box" },
            new String[] { "Magazine", "Paper" }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, "put_down paper",
            "You have put down an item: Paper"
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Magazine" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2, "underlay ice-box magazine",
            "You have decided to underlay ice-box with magazine.\n"
          + "Ice-box is successfully underlaid - now it could be open."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "open ice-box",
            "You have successfully opened the ice-box."
            ,
            "Ice-box",
            new String[] {},
            new String[] { "Beer",  "Beer",   "Beer",
                           "Salami", "Bun", "Wine", "Rum" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsUNMOVABLE, "take beer",
            "You try to take Beer.\n"
          + "This is an intelligent ice-box which does not enable\n"
          + "serving of alcoholic drinks to under-aged.\n"
          + "How old are you?"
            ,
            "Ice-box",
            new String[] {},
            new String[] { "Beer", "Beer", "Beer",
                           "Salami", "Bun", "Wine", "Rum" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsDIALOG, "" + AGE,
            "What is your year of birth?"
            ,
            "Ice-box",
            new String[] {},
            new String[] { "Beer", "Beer", "Beer",
                           "Salami", "Bun", "Wine", "Rum" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsDIALOG, "" + BORN_YEAR,
            "I believe you and pass you the required drink.\n"
          + "You have taken from ice-box: Beer\n"
          + "Bon appetit. Do not forget to close the ice-box."
            ,
            "Ice-box",
            new String[] {},
            new String[] { "Beer", "Beer",
                           "Salami", "Bun", "Wine", "Rum" },
            new String[] { "Beer" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "close ice-box",
            "You have successfully closed the ice-box."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Beer" }
        )
        ,
        //===== The last happy scenario step =====
        new ScenarioStep(tsEND, "end",
            "Game end.\n"
          + "Thank you that you have tried our game."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Beer" }
        )

    };


    /** Step testing the incorrect game starting is defined separately,
     *  so that the indexes of the following steps could be simply set. */
    private static final ScenarioStep WRONG_START =
    new ScenarioStep(-1,
            tsNOT_START, "Start",
            "The first command is not the starting one.\n"
          + "Not running game can be started only by the starting command."
            ,
            "",
            new String[] {},
            new String[] {},
            new String[] {}
        );


    static { ScenarioStep.setIndex(1); }


    /***************************************************************************
     * Steps of mistake scenario defining reactions
     * to incorrectly entered commands of the mandatory set of types.
     */
    private static final ScenarioStep[] MISTAKE_SCENARIO_STEPS =
    {
        WRONG_START
        ,
        START_STEP
        ,
        new ScenarioStep(tsTAKE, "take Shoe-rack",
            "You have taken an item: Shoe-rack"
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Umbrella" },
            new String[] { "Shoe-rack" }
        )
        ,
        new ScenarioStep(tsBAG_FULL, "take umbrella",
            "The entered action has not been carried out.\n"
          + "You cannot take the entered item, "
          + "both your hands are already full."
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Umbrella" },
            new String[] { "Shoe-rack" }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, "put_down Shoe-rack",
            "You have put down an item: Shoe-rack"
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Shoe-rack", "Umbrella" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, "take umbrella",
            "You have taken an item: umbrella"
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Shoe-rack" },
            new String[] { "Umbrella" }
        )
        ,
        new ScenarioStep(tsBAG_FULL, "take Shoe-rack",
            "The entered action has not been carried out.\n"
          + "You cannot take the entered item, "
          + "both your hands are already full."
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Shoe-rack" },
            new String[] { "Umbrella" }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, "put_down Umbrella",
            "You have put down an item: Umbrella"
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Shoe-rack", "Umbrella" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsUNKNOWN, "xxx",
            "The entered action has not been carried out.\n"
          + "I do not know this command.\n"
          + "If you need a help, enter a command ?"
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Shoe-rack", "Umbrella" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsEMPTY, "",
            "You have entered an empty command.\n"
          + "It can be used only for starting the game.\n"
          + "If you need a help, enter a command ?"
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Shoe-rack",  "Umbrella" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, "take umbrella",
            "You have taken an item: Umbrella"
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Shoe-rack" },
            new String[] { "Umbrella" }
        )
        ,
        new ScenarioStep(tsMOVE, "goto bathroom",
            "You have moved to a room: Bathroom"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Glasses", "Sink", "Magazine" },
            new String[] { "Umbrella" }
        )
        ,
        new ScenarioStep(tsBAD_NEIGHBOR, "goto toilet",
            "The entered action has not been carried out.\n"
          + "The destination room is not a neighbor of the current room: toilet"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Glasses", "Sink", "Magazine" },
            new String[] { "Umbrella" }
        )
        ,
        new ScenarioStep(tsBAD_ITEM, "take bathroom",
            "The entered action has not been carried out.\n"
          + "The entered item is not in the room: Bathroom"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Glasses", "Sink", "Magazine" },
            new String[] { "Umbrella" }
        )
        ,
        new ScenarioStep(tsUNMOVABLE, "take sink",
            "The entered action has not been carried out.\n"
          + "The entered item cannot be taken: Sink"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Glasses", "Sink", "Magazine" },
            new String[] { "Umbrella" }
        )
        ,
        new ScenarioStep(tsNOT_IN_BAG, "put_down paper",
            "The entered action has not been carried out.\n"
          + "The item is not in the bag: Paper"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Glasses", "Sink", "Magazine" },
            new String[] { "Umbrella" }
        )
        ,
        new ScenarioStep(tsTAKE, "take glasses",
            "You have taken an item: Glasses"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Sink", "Magazine" },
            new String[] { "Umbrella", "Glasses" }
        )
        ,
        new ScenarioStep(tsBAG_FULL, "take magazine",
            "The entered action has not been carried out.\n"
          + "You cannot take the entered item, "
          + "both your hands are already full."
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Sink", "Magazine" },
            new String[] { "Umbrella", "Glasses" }
        )
        ,
        new ScenarioStep(tsMOVE_WA, "goto",
            "The entered action has not been carried out.\n"
          + "You did not enter where to go"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Sink", "Magazine" },
            new String[] { "Umbrella", "Glasses" }
        )
        ,
        new ScenarioStep(tsTAKE_WA, "take",
            "The entered action has not been carried out.\n"
          + "You did not enter what should be taken"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Sink", "Magazine" },
            new String[] { "Umbrella", "Glasses" }
        )
        ,
        new ScenarioStep(tsPUT_DOWN_WA, "put_down",
            "The entered action has not been carried out.\n"
          + "You did not enter what should be put down"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Sink", "Magazine" },
            new String[] { "Umbrella", "Glasses" }
        )
        ,
        new ScenarioStep(tsHELP, "?",
              "Commands that can be entered during the game course:\n"
            + "===================================================="
            //Text continues with the list of actions and their descriptions
            //and ends with the standard description of current situation
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Sink", "Magazine" },
            new String[] { "Umbrella", "Glasses" }
        )
        ,
        //===== The last mistake scenario step =====
        new ScenarioStep(tsEND, "end",
            "Game end. \nThank you that you have tried our game."
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Sink", "Magazine" },
            new String[] { "Umbrella", "Glasses" }
        )
        ,
    };


    static { ScenarioStep.setIndex(1); }


    /***************************************************************************
     * Steps of mistake scenario defining reactions
     * to incorrectly entered commands of the non-standard set of types.
     */
    private static final ScenarioStep[] MISTAKE_NS_SCENARIO_STEPS =
    {
        START_STEP
        ,
        new ScenarioStep(tsMOVE, "goto bathroom",
            "You have moved to a room: Bathroom"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Glasses", "Sink", "Magazine" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, "take glasses",
            "You have taken an item: Glasses"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Sink", "Magazine" },
            new String[] { "Glasses" }
        )
        ,
        new ScenarioStep(tsTAKE, "take magazine",
            "You have taken an item: Magazine"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Sink" },
            new String[] { "Glasses", "Magazine" }
        )
        ,
        new ScenarioStep(tsMOVE, "goto hall",
            "You have moved to a room: Hall"
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Shoe-rack", "Umbrella" },
            new String[] { "Glasses", "Magazine" }
        )
        ,
        new ScenarioStep(tsMOVE, "goto living-room",
            "You have moved to a room: Living-room"
            ,
            "Living-room",
            new String[] { "Kitchen", "Hall" },
            new String[] { "TV" },
            new String[] { "Glasses", "Magazine" }
        )
        ,
        new ScenarioStep(tsMOVE, "goto kitchen",
            "You have moved to a room: Kitchen"
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Glasses", "Magazine" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "open ice-box",
            "The entered action has not been carried out.\n"
          + "Ice-box cannot be open. Paper with writing lies on it."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Glasses", "Magazine" }
        )
        ,
        new ScenarioStep(tsBAG_FULL, "take paper",
            "The entered action has not been carried out.\n"
          + "You cannot take the entered item, "
          + "both your hands are already full."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Glasses", "Magazine" }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, "put_down magazine",
            "You have put down an item: Magazine"
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper", "Magazine" },
            new String[] { "Glasses" }
        )
        ,
        new ScenarioStep(tsTAKE, "take paper",
            "You have taken an item: Paper"
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Magazine" },
            new String[] { "Glasses", "Paper" }
        )
        ,
        new ScenarioStep(tsNS1_0Args, "read",
            "The entered action has not been carried out.\n"
          + "You did not enter what should be read."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Magazine" },
            new String[] { "Glasses", "Paper" }
        )
        ,
        new ScenarioStep(tsNS1_WRONG_ARG, "read glasses",
            "The entered action has not been carried out.\n"
          + "The item Glasses cannot be read."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Magazine" },
            new String[] { "Glasses", "Paper" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "read paper",
            "You have decided to read the paper.\n"
          + "However, text is written with too small letters that are blurry.\n"
          + "You have to take on glasses."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Magazine" },
            new String[] { "Glasses", "Paper" }
        )
        ,
        new ScenarioStep(tsNS1_0Args, "put_on",
            "The entered action has not been carried out.\n"
          + "You did not enter what should be put_on."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Magazine" },
            new String[] { "Glasses", "Paper" }
        )
        ,
//        new ScenarioStep(tsNS1_WRONG_ARG, "put_on paper",
//            "The entered action has not been carried out.\n"
//          + "The item Paper cannot be put_on."
//            ,
//            "Kitchen",
//            new String[] { "Living-room", "Bedroom" },
//            new String[] { "Ice-box", "Magazine" },
//            new String[] { "Glasses", "Paper" }
//        )
//        ,
        new ScenarioStep(tsNON_STANDARD1, "put_on glasses",
            "You have put glasses on."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Magazine" },
            new String[] { "Paper" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "read paper",
            "You have decided to read the paper.\n"
          + "It is written at the paper:\n"
          + "Ice-box stands askew and therefore it opens with difficulty.\n"
          + "In case of problems, underlay it with something."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Magazine" },
            new String[] { "Paper" }
        )
        ,
        new ScenarioStep(tsTAKE, "take magazine",
            "You have taken an item: Magazine"
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box" },
            new String[] { "Magazine", "Paper" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2, "underlay ice-box magazine",
            "You have decided to underlay ice-box with magazine.\n"
          + "The entered action has not been carried out.\n"
          + "Unfortunately your both hands are full\n"
          + "and thus you have no empty hand for execution."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box" },
            new String[] { "Magazine", "Paper" }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, "put_down paper",
            "You have put down an item: Paper"
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Magazine" }
        )
        ,
        new ScenarioStep(tsNS2_1Args, "underlay ice-box",
            "The entered action has not been carried out.\n"
          + "You did not enter both needed arguments."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Magazine" }
        )
        ,
        new ScenarioStep(tsNS2_WRONG_1stARG, "underlay magazine ice-box",
            "The entered action has not been carried out.\n"
          + "The item Magazine cannot be underlaied."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Magazine" }
        )
        ,
        new ScenarioStep(tsNS2_WRONG_1stARG, "underlay ice-box paper",
            "The entered action has not been carried out.\n"
          + "The item Ice-box cannot be underlaied by item Paper."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Magazine" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2, "underlay ice-box magazine",
            "You have decided to underlay ice-box with magazine.\n"
          + "Ice-box is successfully underlaid - now it could be open."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsNS1_0Args, "open",
            "The entered action has not been carried out.\n"
          + "You did not enter what should be open."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsNS1_WRONG_ARG, "open paper",
            "The entered action has not been carried out.\n"
          + "The item Paper cannot be open."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "open ice-box",
            "You have successfully opened the ice-box."
            ,
            "Ice-box",
            new String[] {},
            new String[] { "Beer",  "Beer",   "Beer",
                           "Salami", "Bun", "Wine", "Rum" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsUNMOVABLE, "take beer",
            "You try to take Beer.\n"
          + "This is an intelligent ice-box which does not enable\n"
          + "serving of alcoholic drinks to under-aged.\n"
          + "How old are you?"
            ,
            "Ice-box",
            new String[] {},
            new String[] { "Beer", "Beer", "Beer",
                           "Salami", "Bun", "Wine", "Rum" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsDIALOG, "" + AGE,
            "What is your year of birth?"
            ,
            "Ice-box",
            new String[] {},
            new String[] { "Beer", "Beer", "Beer",
                           "Salami", "Bun", "Wine", "Rum" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsDIALOG, "" + BORN_YEAR,
            "I believe you and pass you the required drink.\n"
          + "You have taken from ice-box: Beer\n"
          + "Bon appetit. Do not forget to close the ice-box."
            ,
            "Ice-box",
            new String[] {},
            new String[] { "Beer", "Beer",
                           "Salami", "Bun", "Wine", "Rum" },
            new String[] { "Beer" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "close ice-box",
            "You have successfully closed the ice-box."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Beer" }
        )
        ,
        //===== The last happy scenario step =====
        new ScenarioStep(tsEND, "end",
            "Game end.\n"
          + "Thank you that you have tried our game."
            ,
            "Kitchen",
            new String[] { "Living-room", "Bedroom" },
            new String[] { "Ice-box", "Paper" },
            new String[] { "Beer" }
        )

    };


    static { ScenarioStep.setIndex(1); }


    /***************************************************************************
     * Scenario steps determined for verification of mandatory actions,
     * precisely actions for moving to a place, taking and putting an item,
     * writing the help and prematured game termination.
     */
    private static final ScenarioStep[] REQUIRED_STEPS =
    {
        START_STEP
        ,
        new ScenarioStep(tsHELP, "?",
              "Commands that can be entered during the game course:\n"
            + "===================================================="
            //Text continues with the list of actions and their descriptions
            //and ends with the standard description of current situation
            ,
            "Hall",
            new String[] { "Bedroom", "Living-room", "Bathroom" },
            new String[] { "Shoe-rack", "Umbrella" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsMOVE, "goto bathroom",
            "You have moved to a room: Bathroom"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Glasses", "Sink", "Magazine" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, "take glasses",
            "You have taken an item: Glasses"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Sink", "Magazine" },
            new String[] { "Glasses" }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, "put_down glasses",
            "You have put down an item: Glasses"
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Glasses", "Sink", "Magazine" },
            new String[] {}
        )
        ,
        new ScenarioStep(tsEND, "end",
            "Game end.\nThank you that you have tried our game."
            ,
            "Bathroom",
            new String[] { "Hall" },
            new String[] { "Glasses", "Sink", "Magazine" },
            new String[] {}
        )
        ,
    };



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================

    /***************************************************************************
     * Static constructor is placed just behind the declarations of
     * {@link #AGE}, {@link #THIS_YEAR} and {@link #BORN_YEAR} constants
     * and then before declaration of {@link MISTAKE_SCENARIO_STEPS}
     * and each further array with scenario steps
     * for reinitializing of the step's index.
     */



//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Returns a list of arrays of scenario steps.
     *
     * @return A list of arrays of scenario steps
     */
    public static List<ScenarioStep[]> getScenarioSteps()
    {
        List<ScenarioStep[]> result = new ArrayList<>();
        result.add(HAPPY_SCENARIO_STEPS);
        result.add(MISTAKE_SCENARIO_STEPS);
        result.add(REQUIRED_STEPS);
        return result;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     */
    private ScenarioStepsLit()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
