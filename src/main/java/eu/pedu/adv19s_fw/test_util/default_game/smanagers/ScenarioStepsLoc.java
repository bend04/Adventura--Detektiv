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
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsPUT_DOWN;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsPUT_DOWN_WA;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsSTART;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsTAKE;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsTAKE_WA;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsUNKNOWN;
import static eu.pedu.adv19s_fw.scenario.TypeOfStep.tsUNMOVABLE;
import static eu.pedu.adv19s_fw.test_util.default_game.I18n.L;



/*******************************************************************************
//%L+ CZ
 * Třída {@code ScenarioStepsLit} slouží jako přepravka objektů
 * definujících kroky šťastného a chybového scénáře, přičem hodnoty
 * atributů těchto kroků jsou zadávány prostřednictvím lokalizovaných konstant.
//%Lx EN
 * The {@code ScenarioStepsLit} class serves as a crate of objects
 * defining the happy and mistake scenarios steps, where the values
 * of the attributes of these steps are entered as localized constants.
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class ScenarioStepsLoc
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
            L("mWELCOME_MSG")//L("zFULL_WELCOMING")
            ,
            L("HALL"),
            new String[] { L("BEDROOM"), L("LIVING_ROOM"), L("BATHROOM") },
            new String[] { L("SHOE_RACK"), L("UMBRELLA") },
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
        new ScenarioStep(tsMOVE, L("aGOTO") + " " + L("BATHROOM"),
            L("mMOVED") + L("BATHROOM")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("GLASSES"), L("SINK"), L("MAGAZINE") },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, L("aTAKE") + " " + L("GLASSES"),
            L("mTAKEN") + L("GLASSES")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("SINK"), L("MAGAZINE") },
            new String[] { L("GLASSES") }
        )
        ,
        new ScenarioStep(tsTAKE, L("aTAKE") + " " + L("MAGAZINE"),
            L("mTAKEN") + L("MAGAZINE")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("SINK") },
            new String[] { L("GLASSES"), L("MAGAZINE") }
        )
        ,
        new ScenarioStep(tsMOVE, L("aGOTO") + " " + L("HALL"),
            L("mMOVED") + L("HALL")
            ,
            L("HALL"),
            new String[] { L("BEDROOM"), L("LIVING_ROOM"), L("BATHROOM") },
            new String[] { L("SHOE_RACK"), L("UMBRELLA") },
            new String[] { L("GLASSES"), L("MAGAZINE") }
        )
        ,
        new ScenarioStep(tsMOVE, L("aGOTO") + " " + L("LIVING_ROOM"),
            L("mMOVED") + L("LIVING_ROOM")
            ,
            L("LIVING_ROOM"),
            new String[] { L("KITCHEN"), L("HALL") },
            new String[] { L("TV") },
            new String[] { L("GLASSES"), L("MAGAZINE") }
        )
        ,
        new ScenarioStep(tsMOVE, L("aGOTO") + " " + L("KITCHEN"),
            L("mMOVED") + L("KITCHEN")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("ICE_BOX"), L("PAPER") },
            new String[] { L("GLASSES"), L("MAGAZINE") }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, L("aOPEN") + " " + L("ICE_BOX"),
            L("mICEBOX_CANNOT_BE_OPENED")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("ICE_BOX"), L("PAPER") },
            new String[] { L("GLASSES"), L("MAGAZINE") }
        )
        ,
        new ScenarioStep(tsBAG_FULL, L("aTAKE") + " " + L("PAPER"),
            L("mBAG_FULL")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("ICE_BOX"), L("PAPER") },
            new String[] { L("GLASSES"), L("MAGAZINE") }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, L("aPUT_DOWN") + " " + L("MAGAZINE"),
            L("mPUT_DOWN") + L("MAGAZINE")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("ICE_BOX"), L("PAPER"), L("MAGAZINE") },
            new String[] { L("GLASSES") }
        )
        ,
        new ScenarioStep(tsTAKE, L("aTAKE") + " " + L("PAPER"),
            L("mTAKEN") + L("PAPER")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("ICE_BOX"), L("MAGAZINE") },
            new String[] { L("GLASSES"), L("PAPER") }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, L("aREAD") + " " + L("PAPER"),
            L("mWANT_READ") + L("PAPER")
            + L("mNO_GLASS")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("ICE_BOX"), L("MAGAZINE") },
            new String[] { L("GLASSES"), L("PAPER") }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, L("aPUT_ON") + " " + L("GLASSES"),
            L("mGLASS_PUT_ON")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("ICE_BOX"), L("MAGAZINE") },
            new String[] { L("PAPER") }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, L("aREAD") + " " + L("PAPER"),
            L("mWANT_READ") + L("PAPER")
            + L("mWRITTEN_ON_PAPER")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("ICE_BOX"), L("MAGAZINE") },
            new String[] { L("PAPER") }
        )
        ,
        new ScenarioStep(tsTAKE, L("aTAKE") + " " + L("MAGAZINE"),
            L("mTAKEN") + L("MAGAZINE")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("ICE_BOX") },
            new String[] { L("PAPER"), L("MAGAZINE") }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2,
            L("aUNDERLAY") + " " + L("ICE_BOX") + " " + L("MAGAZINE"),
            L("mWANT_UNDERLAY") + L("ICE_BOX")  + L("mWITH_ITEM")
                                + L("MAGAZINE") + ".\n" +
            L("mNOT_CAPABLE")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("ICE_BOX") },
            new String[] { L("PAPER"), L("MAGAZINE")  }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, L("aPUT_DOWN") + " " + L("PAPER"),
            L("mPUT_DOWN") + L("PAPER")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("PAPER"), L("ICE_BOX") },
            new String[] { L("MAGAZINE") }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2,
            L("aUNDERLAY") + " " + L("ICE_BOX") + " " + L("MAGAZINE"),
            L("mWANT_UNDERLAY")  + L("ICE_BOX")  + L("mWITH_ITEM")
                                 + L("MAGAZINE") + "." +
            L("mICEBOX_UNDERLAID")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("PAPER"), L("ICE_BOX") },
            new String[] {}
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, L("aOPEN") + " " + L("ICE_BOX"),
            L("mICEBOX_OPENED")
            ,
            L("ICE_BOX"),
            new String[] {},
            new String[] { L("BEER"),  L("BEER"),   L("BEER"),
                           L("SALAMI"), L("BUN"), L("WINE"), L("RUM") },
            new String[] {}
        )
        ,
        new ScenarioStep(tsUNMOVABLE, L("aTAKE") + " " + L("BEER"),
            L("mTAKES_ALCOHOL") + L("BEER") + "."
            + L("mHOW_OLD")
            ,
            L("ICE_BOX"),
            new String[] {},
            new String[] { L("BEER"),   L("BEER"),  L("BEER"),
                           L("SALAMI"), L("BUN"),   L("WINE"),  L("RUM") },
            new String[] {}
        )
        ,
        new ScenarioStep(tsDIALOG, ""+AGE,
            L("mBIRTH")
            ,
            L("ICE_BOX"),
            new String[] {},
            new String[] { L("BEER"),   L("BEER"),  L("BEER"),
                           L("SALAMI"), L("BUN"),   L("WINE"),  L("RUM") },
            new String[] {}
        )
        ,
        new ScenarioStep(tsDIALOG, "" + BORN_YEAR,
            L("mBELIEVE")
            + L("mTAKEN_FROM_IB") + L("BEER")
            + L("mDO_NOT_FORGET")
            ,
            L("ICE_BOX"),
            new String[] {},
            new String[] { L("BEER"),   L("BEER"),
                           L("SALAMI"), L("BUN"),   L("WINE"),  L("RUM") },
            new String[] { L("BEER") }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, L("aCLOSE") + " " + L("ICE_BOX"),
            L("mICEBOX_CLOSED")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("ICE_BOX"), L("PAPER") },
            new String[] { L("BEER") }
        )
        ,
        //===== The last happy scenario step =====
        new ScenarioStep(tsEND, L("aEXIT"),
            L("mGOOD_BYE")
            ,
            L("KITCHEN"),
            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
            new String[] { L("ICE_BOX"), L("PAPER") },
            new String[] { L("BEER") }
        )

    };


    /** Step testing the incorrect game starting is defined separately,
     *  so that the indexes of the following steps could be simply set. */
    private static final ScenarioStep WRONG_START =
    new ScenarioStep(-1, tsNOT_START, "Start",
            L("mNOT_START")
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
        new ScenarioStep(tsUNKNOWN, "xxx",
            L("mNO_CMD")
            ,
            L("HALL"),
            new String[] { L("BEDROOM"), L("LIVING_ROOM"), L("BATHROOM") },
            new String[] { L("SHOE_RACK"),  L("UMBRELLA") },
            new String[] {}
        )
        ,
        new ScenarioStep(tsEMPTY, "",
            L("mEMPTY_CMD")
            ,
            L("HALL"),
            new String[] { L("BEDROOM"), L("LIVING_ROOM"), L("BATHROOM") },
            new String[] { L("SHOE_RACK"),  L("UMBRELLA") },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, L("aTAKE") + " " + L("UMBRELLA"),
            L("mTAKEN") + L("UMBRELLA")
            ,
            L("HALL"),
            new String[] { L("BEDROOM"), L("LIVING_ROOM"), L("BATHROOM") },
            new String[] { L("SHOE_RACK") },
            new String[] { L("UMBRELLA") }
        )
        ,
        new ScenarioStep(tsMOVE, L("aGOTO") + " " + L("BATHROOM"),
            L("mMOVED") + L("BATHROOM")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("GLASSES"), L("SINK"), L("MAGAZINE") },
            new String[] { L("UMBRELLA") }
        )
        ,
        new ScenarioStep(tsBAD_NEIGHBOR, L("aGOTO") + " " + "xxx",
            L("mNOT_NEIGHBOR") + "xxx"
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("GLASSES"), L("SINK"), L("MAGAZINE") },
            new String[] { L("UMBRELLA") }
        )
        ,
        new ScenarioStep(tsBAD_ITEM, L("aTAKE") + " " + L("BATHROOM"),
            L("mNOT_HERE") + L("BATHROOM")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("GLASSES"), L("SINK"), L("MAGAZINE") },
            new String[] { L("UMBRELLA") }
        )
        ,
        new ScenarioStep(tsUNMOVABLE, L("aTAKE") + " " + L("SINK"),
            L("mHEAVY_ITEM") + L("SINK")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("GLASSES"), L("SINK"), L("MAGAZINE") },
            new String[] { L("UMBRELLA") }
        )
        ,
        new ScenarioStep(tsNOT_IN_BAG, L("aPUT_DOWN") + " " + L("PAPER"),
            L("mNOT_IN_BAG") + L("PAPER")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("GLASSES"), L("SINK"), L("MAGAZINE") },
            new String[] { L("UMBRELLA") }
        )
        ,
        new ScenarioStep(tsTAKE, L("aTAKE") + " " + L("GLASSES"),
            L("mTAKEN") + L("GLASSES")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("SINK"), L("MAGAZINE") },
            new String[] { L("UMBRELLA"), L("GLASSES") }
        )
        ,
        new ScenarioStep(tsBAG_FULL, L("aTAKE") + " " + L("MAGAZINE"),
            L("mBAG_FULL")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("SINK"), L("MAGAZINE") },
            new String[] { L("UMBRELLA"), L("GLASSES") }
        )
        ,
        new ScenarioStep(tsMOVE_WA, L("aGOTO"),
            L("mNO_TARGET")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("SINK"), L("MAGAZINE") },
            new String[] { L("UMBRELLA"), L("GLASSES") }
        )
        ,
        new ScenarioStep(tsTAKE_WA, L("aTAKE"),
            L("mNO_TAKE_ITEM")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("SINK"), L("MAGAZINE") },
            new String[] { L("UMBRELLA"), L("GLASSES") }
        )
        ,
        new ScenarioStep(tsPUT_DOWN_WA, L("aPUT_DOWN"),
            L("mNO_PUT_ITEM")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("SINK"), L("MAGAZINE") },
            new String[] { L("UMBRELLA"), L("GLASSES") }
        )
        ,
        new ScenarioStep(tsHELP, L("aHELP"),
            L("mHELP")
            //Text continues with the list of actions and their descriptions
            //and ends with the standard description of current situation
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("SINK"), L("MAGAZINE") },
            new String[] { L("UMBRELLA"), L("GLASSES") }
        )
        ,
        //===== The last mistake scenario step =====
        new ScenarioStep(tsEND, L("aEXIT"),
            L("mGOOD_BYE")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("SINK"), L("MAGAZINE") },
            new String[] { L("UMBRELLA"), L("GLASSES") }
        )
        ,
    };


    static { ScenarioStep.setIndex(1); }

//
//    /***************************************************************************
//     * Steps of mistake scenario defining reactions
//     * to incorrectly entered commands of the non-standard set of types.
//     */
//    private static final ScenarioStep[] MISTAKE_NS_SCENARIO_STEPS =
//    {
//        START_STEP
//        ,
//        new ScenarioStep(tsMOVE, L("aGOTO") + " " + L("BATHROOM"),
//            L("mMOVED") + L("BATHROOM")
//            ,
//            L("BATHROOM"),
//            new String[] { L("HALL") },
//            new String[] { L("GLASSES"), L("SINK"), L("MAGAZINE") },
//            new String[] {}
//        )
//        ,
//        new ScenarioStep(tsTAKE, L("aTAKE") + " " + L("GLASSES"),
//            L("mTAKEN") + L("GLASSES")
//            ,
//            L("BATHROOM"),
//            new String[] { L("HALL") },
//            new String[] { L("SINK"), L("MAGAZINE") },
//            new String[] { L("GLASSES") }
//        )
//        ,
//        new ScenarioStep(tsTAKE, L("aTAKE") + " " + L("MAGAZINE"),
//            L("mTAKEN") + L("MAGAZINE")
//            ,
//            L("BATHROOM"),
//            new String[] { L("HALL") },
//            new String[] { L("SINK") },
//            new String[] { L("GLASSES"), L("MAGAZINE") }
//        )
//        ,
//        new ScenarioStep(tsMOVE, L("aGOTO") + " " + L("HALL"),
//            L("mMOVED") + L("HALL")
//            ,
//            L("HALL"),
//            new String[] { L("BEDROOM"), L("LIVING_ROOM"), L("BATHROOM") },
//            new String[] { L("SHOE_RACK"), L("UMBRELLA") },
//            new String[] { L("GLASSES"), L("MAGAZINE") }
//        )
//        ,
//        new ScenarioStep(tsMOVE, L("aGOTO") + " " + L("LIVING_ROOM"),
//            L("mMOVED") + L("LIVING_ROOM")
//            ,
//            L("LIVING_ROOM"),
//            new String[] { L("KITCHEN"), L("HALL") },
//            new String[] { L("TV") },
//            new String[] { L("GLASSES"), L("MAGAZINE") }
//        )
//        ,
//        new ScenarioStep(tsMOVE, L("aGOTO") + " " + L("KITCHEN"),
//            L("mMOVED") + L("KITCHEN")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("ICE_BOX"), L("PAPER") },
//            new String[] { L("GLASSES"), L("MAGAZINE") }
//        )
//        ,
//        new ScenarioStep(tsNON_STANDARD1, L("aOPEN") + " " + L("ICE_BOX"),
//            L("mICEBOX_CANNOT_BE_OPENED")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("ICE_BOX"), L("PAPER") },
//            new String[] { L("GLASSES"), L("MAGAZINE") }
//        )
//        ,
//        new ScenarioStep(tsBAG_FULL, L("aTAKE") + " " + L("PAPER"),
//            L("mBAG_FULL")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("ICE_BOX"), L("PAPER") },
//            new String[] { L("GLASSES"), L("MAGAZINE") }
//        )
//        ,
//        new ScenarioStep(tsPUT_DOWN, L("aPUT_DOWN") + " " + L("MAGAZINE"),
//            L("mPUT_DOWN") + L("MAGAZINE")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("ICE_BOX"), L("PAPER"), L("MAGAZINE") },
//            new String[] { L("GLASSES") }
//        )
//        ,
//        new ScenarioStep(tsTAKE, L("aTAKE") + " " + L("PAPER"),
//            L("mTAKEN") + L("PAPER")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("ICE_BOX"), L("MAGAZINE") },
//            new String[] { L("GLASSES"), L("PAPER") }
//        )
//        ,
//        new ScenarioStep(tsNON_STANDARD1, L("aREAD") + " " + L("PAPER"),
//            L("mWANT_READ") + L("PAPER")
//            + L("mNO_GLASS")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("ICE_BOX"), L("MAGAZINE") },
//            new String[] { L("GLASSES"), L("PAPER") }
//        )
//        ,
//        new ScenarioStep(tsNON_STANDARD1, L("aPUT_ON") + " " + L("GLASSES"),
//            L("mGLASS_PUT_ON")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("ICE_BOX"), L("MAGAZINE") },
//            new String[] { L("PAPER") }
//        )
//        ,
//        new ScenarioStep(tsNON_STANDARD1, L("aREAD") + " " + L("PAPER"),
//            L("mWANT_READ") + L("PAPER")
//            + L("mWRITTEN_ON_PAPER")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("ICE_BOX"), L("MAGAZINE") },
//            new String[] { L("PAPER") }
//        )
//        ,
//        new ScenarioStep(tsTAKE, L("aTAKE") + " " + L("MAGAZINE"),
//            L("mTAKEN") + L("MAGAZINE")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("ICE_BOX") },
//            new String[] { L("PAPER"), L("MAGAZINE") }
//        )
//        ,
//        new ScenarioStep(tsNON_STANDARD2,
//            L("aUNDERLAY") + " " + L("ICE_BOX") + " " + L("MAGAZINE"),
//            L("mWANT_UNDERLAY") + L("ICE_BOX")  + L("mWITH_ITEM")
//                                + L("MAGAZINE") + ".\n" +
//            L("mNOT_CAPABLE")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("ICE_BOX") },
//            new String[] { L("PAPER"), L("MAGAZINE")  }
//        )
//        ,
//        new ScenarioStep(tsPUT_DOWN, L("aPUT_DOWN") + " " + L("PAPER"),
//            L("mPUT_DOWN") + L("PAPER")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("PAPER"), L("ICE_BOX") },
//            new String[] { L("MAGAZINE") }
//        )
//        ,
//        new ScenarioStep(tsNON_STANDARD2,
//            L("aUNDERLAY") + " " + L("ICE_BOX") + " " + L("MAGAZINE"),
//            L("mWANT_UNDERLAY")  + L("ICE_BOX")  + L("mWITH_ITEM")
//                                 + L("MAGAZINE") + "." +
//            L("mICEBOX_UNDERLAID")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("PAPER"), L("ICE_BOX") },
//            new String[] {}
//        )
//        ,
//        new ScenarioStep(tsNON_STANDARD1, L("aOPEN") + " " + L("ICE_BOX"),
//            L("mICEBOX_OPENED")
//            ,
//            L("ICE_BOX"),
//            new String[] {},
//            new String[] { L("BEER"),  L("BEER"),   L("BEER"),
//                           L("SALAMI"), L("BUN"), L("WINE"), L("RUM") },
//            new String[] {}
//        )
//        ,
//        new ScenarioStep(tsUNMOVABLE, L("aTAKE") + " " + L("BEER"),
//            L("mTAKES_ALCOHOL") + L("BEER") + "."
//            + L("mHOW_OLD")
//            ,
//            L("ICE_BOX"),
//            new String[] {},
//            new String[] { L("BEER"),   L("BEER"),  L("BEER"),
//                           L("SALAMI"), L("BUN"),   L("WINE"),  L("RUM") },
//            new String[] {}
//        )
//        ,
//        new ScenarioStep(tsDIALOG, ""+AGE,
//            L("mBIRTH")
//            ,
//            L("ICE_BOX"),
//            new String[] {},
//            new String[] { L("BEER"),   L("BEER"),  L("BEER"),
//                           L("SALAMI"), L("BUN"),   L("WINE"),  L("RUM") },
//            new String[] {}
//        )
//        ,
//        new ScenarioStep(tsDIALOG, "" + BORN_YEAR,
//            L("mBELIEVE")
//            + L("mTAKEN_FROM_IB") + L("BEER")
//            + L("mDO_NOT_FORGET")
//            ,
//            L("ICE_BOX"),
//            new String[] {},
//            new String[] { L("BEER"),   L("BEER"),
//                           L("SALAMI"), L("BUN"),   L("WINE"),  L("RUM") },
//            new String[] { L("BEER") }
//        )
//        ,
//        new ScenarioStep(tsNS1_WRONG_ARG, L("aCLOSE") + " " + L("RUM"),
//            L("mRUM_NOT_CLOSED")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("ICE_BOX"), L("PAPER") },
//            new String[] { L("BEER") }
//        )
//        ,
//        //===== The last happy scenario step =====
//        new ScenarioStep(tsEND, L("aEXIT"),
//            L("mGOOD_BYE")
//            ,
//            L("KITCHEN"),
//            new String[] { L("LIVING_ROOM"), L("BEDROOM") },
//            new String[] { L("ICE_BOX"), L("PAPER") },
//            new String[] { L("BEER") }
//        )
//
//    };
//

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
        new ScenarioStep(tsHELP, L("aHELP"),
            L("mHELP")
            //Text continues with the list of actions and their descriptions
            //and ends with the standard description of current situation
            ,
            L("HALL"),
            new String[] { L("BEDROOM"), L("LIVING_ROOM"), L("BATHROOM") },
            new String[] { L("SHOE_RACK"), L("UMBRELLA") },
            new String[] {}
        )
        ,
        new ScenarioStep(tsMOVE, L("aGOTO") + " " + L("BATHROOM"),
            L("mMOVED") + L("BATHROOM")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("GLASSES"), L("SINK"), L("MAGAZINE") },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, L("aTAKE") + " " + L("GLASSES"),
            L("mTAKEN") + L("GLASSES")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("SINK"), L("MAGAZINE") },
            new String[] { L("GLASSES") }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, L("aPUT_DOWN") + " " + L("GLASSES"),
            L("mPUT_DOWN") + L("GLASSES")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("GLASSES"), L("SINK"), L("MAGAZINE") },
            new String[] {}
        )
        ,
        new ScenarioStep(tsEND, L("aEXIT"),
            L("mGOOD_BYE")
            ,
            L("BATHROOM"),
            new String[] { L("HALL") },
            new String[] { L("GLASSES"), L("SINK"), L("MAGAZINE") },
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
    private ScenarioStepsLoc()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
