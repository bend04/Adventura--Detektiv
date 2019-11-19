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
import static eu.pedu.adv19s_fw.test_util.default_game.DATA. TextsL.*;



/*******************************************************************************
//%L+ CZ
 * Třída {@code ScenarioStepsLit} slouží jako přepravka objektů
 * definujících kroky šťastného a chybového scénáře, přičem hodnoty
 * atributů těchto kroků jsou zadávány prostřednictvím konstant.
//%Lx EN
 * The {@code ScenarioStepsLit} class serves as a crate of objects
 * defining the happy and mistake scenarios steps, where the values
 * of the attributes of these steps are entered as constants.
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
class ScenarioStepsConL
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
            mWELCOME_MSG//zFULL_WELCOMING
            ,
            HALL,
            new String[] { BEDROOM, LIVING_ROOM, BATHROOM },
            new String[] { SHOE_RACK, UMBRELLA },
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
        new ScenarioStep(tsMOVE, aGOTO + " " + BATHROOM,
            mMOVED + BATHROOM
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { GLASSES, SINK, MAGAZINE },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, aTAKE + " " + GLASSES,
            mTAKEN + GLASSES
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { SINK, MAGAZINE },
            new String[] { GLASSES }
        )
        ,
        new ScenarioStep(tsTAKE, aTAKE + " " + MAGAZINE,
            mTAKEN + MAGAZINE
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { SINK },
            new String[] { GLASSES, MAGAZINE }
        )
        ,
        new ScenarioStep(tsMOVE, aGOTO + " " + HALL,
            mMOVED + HALL
            ,
            HALL,
            new String[] { BEDROOM, LIVING_ROOM, BATHROOM },
            new String[] { SHOE_RACK, UMBRELLA },
            new String[] { GLASSES, MAGAZINE }
        )
        ,
        new ScenarioStep(tsMOVE, aGOTO + " " + LIVING_ROOM,
            mMOVED + LIVING_ROOM
            ,
            LIVING_ROOM,
            new String[] { KITCHEN, HALL },
            new String[] { TV },
            new String[] { GLASSES, MAGAZINE }
        )
        ,
        new ScenarioStep(tsMOVE, aGOTO + " " + KITCHEN,
            mMOVED + KITCHEN
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { ICE_BOX, PAPER },
            new String[] { GLASSES, MAGAZINE }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, aOPEN + " " + ICE_BOX,
            mICEBOX_CANNOT_BE_OPENED
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { ICE_BOX, PAPER },
            new String[] { GLASSES, MAGAZINE }
        )
        ,
        new ScenarioStep(tsBAG_FULL, aTAKE + " " + PAPER,
            mBAG_FULL
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { ICE_BOX, PAPER },
            new String[] { GLASSES, MAGAZINE }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, aPUT_DOWN + " " + MAGAZINE,
            mPUT_DOWN + MAGAZINE
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { ICE_BOX, PAPER, MAGAZINE },
            new String[] { GLASSES }
        )
        ,
        new ScenarioStep(tsTAKE, aTAKE + " " + PAPER,
            mTAKEN + PAPER
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { ICE_BOX, MAGAZINE },
            new String[] { GLASSES, PAPER }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, aREAD + " " + PAPER,
            mWANT_READ + PAPER
            + mNO_GLASS
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { ICE_BOX, MAGAZINE },
            new String[] { GLASSES, PAPER }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, aPUT_ON + " " + GLASSES,
            mGLASS_PUT_ON
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { ICE_BOX, MAGAZINE },
            new String[] { PAPER }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, aREAD + " " + PAPER,
            mWANT_READ + PAPER
            + mWRITTEN_ON_PAPER
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { ICE_BOX, MAGAZINE },
            new String[] { PAPER }
        )
        ,
        new ScenarioStep(tsTAKE, aTAKE + " " + MAGAZINE,
            mTAKEN + MAGAZINE
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { ICE_BOX },
            new String[] { PAPER, MAGAZINE }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2,
            aUNDERLAY + " " + ICE_BOX + " " + MAGAZINE,
            mWANT_UNDERLAY + ICE_BOX + mWITH_ITEM
                           + MAGAZINE + ".\n" +
            mNOT_CAPABLE
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { ICE_BOX },
            new String[] { PAPER, MAGAZINE  }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, aPUT_DOWN + " " + PAPER,
            mPUT_DOWN + PAPER
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { PAPER, ICE_BOX },
            new String[] { MAGAZINE }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2,
            aUNDERLAY + " " + ICE_BOX + " " + MAGAZINE,
            mWANT_UNDERLAY  + ICE_BOX + mWITH_ITEM
                            + MAGAZINE + "." +
            mICEBOX_UNDERLAID
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { PAPER, ICE_BOX },
            new String[] {}
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, aOPEN + " " + ICE_BOX,
            mICEBOX_OPENED
            ,
            ICE_BOX,
            new String[] {},
            new String[] { BEER,  BEER,   BEER,
                           SALAMI, BUN, WINE, RUM },
            new String[] {}
        )
        ,
        new ScenarioStep(tsUNMOVABLE, aTAKE + " " + BEER,
            mTAKES_ALCOHOL + BEER + "."
            + mHOW_OLD
            ,
            ICE_BOX,
            new String[] {},
            new String[] { BEER,  BEER,   BEER,
                           SALAMI, BUN, WINE, RUM },
            new String[] {}
        )
        ,
        new ScenarioStep(tsDIALOG, ""+AGE,
            mBIRTH
            ,
            ICE_BOX,
            new String[] {},
            new String[] { BEER,  BEER,   BEER,
                           SALAMI, BUN, WINE, RUM },
            new String[] {}
        )
        ,
        new ScenarioStep(tsDIALOG, "" + BORN_YEAR,
            mBELIEVE
            + mTAKEN_FROM_IB + BEER
            + mDO_NOT_FORGET
            ,
            ICE_BOX,
            new String[] {},
            new String[] { BEER,  BEER,
                           SALAMI, BUN, WINE, RUM },
            new String[] { BEER }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, aCLOSE + " " + ICE_BOX,
            mICEBOX_CLOSED
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { ICE_BOX, PAPER },
            new String[] { BEER }
        )
        ,
        //===== The last happy scenario step =====
        new ScenarioStep(tsEND, aEXIT,
            mGOOD_BYE
            ,
            KITCHEN,
            new String[] { LIVING_ROOM, BEDROOM },
            new String[] { ICE_BOX, PAPER },
            new String[] { BEER }
        )

    };


    /** Step testing the incorrect game starting is defined separately,
     *  so that the indexes of the following steps could be simply set. */
    private static final ScenarioStep WRONG_START =
    new ScenarioStep(-1, tsNOT_START, "Start",
            mNOT_START
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
            mNO_CMD
            ,
            HALL,
            new String[] { BEDROOM, LIVING_ROOM, BATHROOM },
            new String[] { SHOE_RACK,  UMBRELLA },
            new String[] {}
        )
        ,
        new ScenarioStep(tsEMPTY, "",
            mEMPTY_CMD
            ,
            HALL,
            new String[] { BEDROOM, LIVING_ROOM, BATHROOM },
            new String[] { SHOE_RACK,  UMBRELLA },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, aTAKE + " " + UMBRELLA,
            mTAKEN + UMBRELLA
            ,
            HALL,
            new String[] { BEDROOM, LIVING_ROOM, BATHROOM },
            new String[] { SHOE_RACK },
            new String[] { UMBRELLA }
        )
        ,
        new ScenarioStep(tsMOVE, aGOTO + " " + BATHROOM,
            mMOVED + BATHROOM
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { GLASSES, SINK, MAGAZINE },
            new String[] { UMBRELLA }
        )
        ,
        new ScenarioStep(tsBAD_NEIGHBOR, aGOTO + " " + "xxx",
            mNOT_NEIGHBOR + "xxx"
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { GLASSES, SINK, MAGAZINE },
            new String[] { UMBRELLA }
        )
        ,
        new ScenarioStep(tsBAD_ITEM, aTAKE + " " + BATHROOM,
            mNOT_HERE + BATHROOM
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { GLASSES, SINK, MAGAZINE },
            new String[] { UMBRELLA }
        )
        ,
        new ScenarioStep(tsUNMOVABLE, aTAKE + " " + SINK,
            mHEAVY_ITEM + SINK
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { GLASSES, SINK, MAGAZINE },
            new String[] { UMBRELLA }
        )
        ,
        new ScenarioStep(tsNOT_IN_BAG, aPUT_DOWN + " " + PAPER,
            mNOT_IN_BAG + PAPER
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { GLASSES, SINK, MAGAZINE },
            new String[] { UMBRELLA }
        )
        ,
        new ScenarioStep(tsTAKE, aTAKE + " " + GLASSES,
            mTAKEN + GLASSES
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { SINK, MAGAZINE },
            new String[] { UMBRELLA, GLASSES }
        )
        ,
        new ScenarioStep(tsBAG_FULL, aTAKE + " " + MAGAZINE,
            mBAG_FULL
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { SINK, MAGAZINE },
            new String[] { UMBRELLA, GLASSES }
        )
        ,
        new ScenarioStep(tsMOVE_WA, aGOTO,
            mNO_TARGET
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { SINK, MAGAZINE },
            new String[] { UMBRELLA, GLASSES }
        )
        ,
        new ScenarioStep(tsTAKE_WA, aTAKE,
            mNO_TAKE_ITEM
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { SINK, MAGAZINE },
            new String[] { UMBRELLA, GLASSES }
        )
        ,
        new ScenarioStep(tsPUT_DOWN_WA, aPUT_DOWN,
            mNO_PUT_ITEM
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { SINK, MAGAZINE },
            new String[] { UMBRELLA, GLASSES }
        )
        ,
        new ScenarioStep(tsHELP, aHELP,
            mHELP
            //Text continues with the list of actions and their descriptions
            //and ends with the standard description of current situation
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { SINK, MAGAZINE },
            new String[] { UMBRELLA, GLASSES }
        )
        ,
        //===== The last mistake scenario step =====
        new ScenarioStep(tsEND, aEXIT,
            mGOOD_BYE
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { SINK, MAGAZINE },
            new String[] { UMBRELLA, GLASSES }
        )
        ,
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
        new ScenarioStep(tsHELP, aHELP,
            mHELP
            //Text continues with the list of actions and their descriptions
            //and ends with the standard description of current situation
            ,
            HALL,
            new String[] { BEDROOM, LIVING_ROOM, BATHROOM },
            new String[] { SHOE_RACK, UMBRELLA },
            new String[] {}
        )
        ,
        new ScenarioStep(tsMOVE, aGOTO + " " + BATHROOM,
            mMOVED + BATHROOM
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { GLASSES, SINK, MAGAZINE },
            new String[] {}
        )
        ,
        new ScenarioStep(tsTAKE, aTAKE + " " + GLASSES,
            mTAKEN + GLASSES
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { SINK, MAGAZINE },
            new String[] { GLASSES }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, aPUT_DOWN + " " + GLASSES,
            mPUT_DOWN + GLASSES
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { GLASSES, SINK, MAGAZINE },
            new String[] {}
        )
        ,
        new ScenarioStep(tsEND, aEXIT,
            mGOOD_BYE
            ,
            BATHROOM,
            new String[] { HALL },
            new String[] { GLASSES, SINK, MAGAZINE },
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
    private ScenarioStepsConL()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
