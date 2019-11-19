/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.DATA;

import eu.pedu.adv19s_fw.test_util.default_game.GetSetLocale;
import eu.pedu.adv19s_fw.test_util.default_game.I18n;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;



/*******************************************************************************
 * The {@code TextsL} library class serves as a box for text constants,
 * that are used at various places of the program.
 * By centralizing the definitions of these text strings we can easily reach,
 * that texts, that should be identical at various program places,
 * really will be identical.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class TextsL
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Nastavený zdroj lokalizovaných textů.
     *  Inicializace atributu současně synchronizuje zavedení dané třídy
     *  a inicializaci jejích konstant s nastavením příslušného
     * {@link java.util.Locale Locale}. */
    private static final ResourceBundle TEXT =
                   ResourceBundle.getBundle("Texts", GetSetLocale.getLocale());

    /** Names of used places (rooms in this game) - without prefix. */
    public static final String
        HALL         = TEXT.getString("HALL"),
        BEDROOM      = TEXT.getString("BEDROOM"),
        LIVING_ROOM  = TEXT.getString("LIVING_ROOM"),
        BATHROOM     = TEXT.getString("BATHROOM"),
        KITCHEN      = TEXT.getString("KITCHEN");


    /** Names of used items - without prefix. */
    public static final String
        SHOE_RACK    = TEXT.getString("SHOE_RACK"),
        UMBRELLA     = TEXT.getString("UMBRELLA"),
        GLASSES      = TEXT.getString("GLASSES"),
        SINK         = TEXT.getString("SINK"),
        TV           = TEXT.getString("TV"),
        MAGAZINE     = TEXT.getString("MAGAZINE"),
        ICE_BOX      = TEXT.getString("ICE_BOX"),
        PAPER        = TEXT.getString("PAPER"),
        BEER         = TEXT.getString("BEER"),
        RUM          = TEXT.getString("RUM"),
        SALAMI       = TEXT.getString("SALAMI"),
        BUN          = TEXT.getString("BUN"),
        WINE         = TEXT.getString("WINE"),
        BED          = TEXT.getString("BED"),
        MIRROR       = TEXT.getString("MIRROR"),
        BATHROBE     = TEXT.getString("BATHROBE");


    /** Names of used actions - prefix {@code 'a'}. */
    public static final String
        aHELP        = TEXT.getString("aHELP"),
        aGOTO        = TEXT.getString("aGOTO"),
        aPUT_ON      = TEXT.getString("aPUT_ON"),
        aOPEN        = TEXT.getString("aOPEN"),
        aUNDERLAY    = TEXT.getString("aUNDERLAY"),
        aPUT_DOWN    = TEXT.getString("aPUT_DOWN"),
        aREAD        = TEXT.getString("aREAD"),
        aTAKE        = TEXT.getString("aTAKE"),
        aCLOSE       = TEXT.getString("aCLOSE"),
        aEXIT        = TEXT.getString("aEXIT");


    /** Part of the message supplement informing about the current state. */
    public static final String
        sNEIGHBORS   = TEXT.getString("sNEIGHBORS"),
        sITEMS       = TEXT.getString("sITEMS"),
        sBAG         = TEXT.getString("sBAG");


    /** Messages written as reaction to the command
     * running the mandatory actions - prefix {@code 'm'}. */
    public static final String
        mNOT_START      = TEXT.getString("mNOT_START"),
        mWELCOME_MSG    = TEXT.getString("mWELCOME_MSG"),
        mANCO           = TEXT.getString("mANCO"),
        mASK_HELP       = TEXT.getString("mASK_HELP"),
        mEMPTY_CMD      = TEXT.getString("mEMPTY_CMD"),
        mNO_CMD         = TEXT.getString("mNO_CMD"),

        mMOVED          = TEXT.getString("mMOVED"),
        mNO_TARGET      = TEXT.getString("mNO_TARGET"),
        mNOT_NEIGHBOR   = TEXT.getString("mNOT_NEIGHBOR"),

        mTAKEN          = TEXT.getString("mTAKEN"),
        mNO_TAKE_ITEM   = TEXT.getString("mNO_TAKE_ITEM"),
        mHEAVY_ITEM     = TEXT.getString("mHEAVY_ITEM"),
        mNOT_HERE       = TEXT.getString("mNOT_HERE"),
        mBAG_FULL       = TEXT.getString("mBAG_FULL"),

        mPUT_DOWN       = TEXT.getString("mPUT_DOWN"),
        mNO_PUT_ITEM    = TEXT.getString("mNO_PUT_ITEM"),
        mNOT_IN_BAG     = TEXT.getString("mNOT_IN_BAG"),

        mHELP           = TEXT.getString("mHELP"),

        mGOOD_BYE       = TEXT.getString("mGOOD_BYE");


    /** Texts written in reaction to commands recalling the optional actions. */
    public static final String
        mNO_OPENING     = TEXT.getString("mNO_OPENING"),
        mNOT_OPENABLE   = TEXT.getString("mNOT_OPENABLE"),
        mICEBOX_CANNOT_BE_OPENED = TEXT.getString("mICEBOX_CANNOT_BE_OPENED"),
        mNO_READING     = TEXT.getString("mNO_READING"),
        mNOT_READABLE   = TEXT.getString("mNOT_READABLE"),
        mWANT_READ      = TEXT.getString("mWANT_READ"),
        mNO_GLASS       = TEXT.getString("mNO_GLASS"),
        mNO_PUT_ON      = TEXT.getString("mNO_PUT_ON"),
        mNOT_PUTABLE    = TEXT.getString("mNOT_PUTABLE"),
        mGLASS_PUT_ON   = TEXT.getString("mGLASS_PUT_ON"),
        mWRITTEN_ON_PAPER = TEXT.getString("mWRITTEN_ON_PAPER"),
        mWRITTEN_IN_MAGAZINE = TEXT.getString("mWRITTEN_IN_MAGAZINE"),
        mWHAT_UNDERLAY  = TEXT.getString("mWHAT_UNDERLAY"),
        mUNDERLAY_WITH  = TEXT.getString("mUNDERLAY_WITH"),
        mNO_UNDERLAID   = TEXT.getString("mNO_UNDERLAID"),
        mNO_UNDERLAYMENT= TEXT.getString("mNO_UNDERLAYMENT"),
        mNOT_UNDERLAYABLE = TEXT.getString("mNOT_UNDERLAYABLE"),
        mNOT_USABLE     = TEXT.getString("mNOT_USABLE"),
        mWANT_UNDERLAY  = TEXT.getString("mWANT_UNDERLAY"),
        mWITH_ITEM      = TEXT.getString("mWITH_ITEM"),
        mNOT_CAPABLE    = TEXT.getString("mNOT_CAPABLE"),
        mICEBOX_UNDERLAID = TEXT.getString("mICEBOX_UNDERLAID"),
        mICEBOX_OPENED  = TEXT.getString("mICEBOX_OPENED"),
        mTAKES_ALCOHOL  = TEXT.getString("mTAKES_ALCOHOL"),
        mHOW_OLD        = TEXT.getString("mHOW_OLD"),
        mBIRTH          = TEXT.getString("mBIRTH"),
        mBELIEVE        = TEXT.getString("mBELIEVE"),
        mTAKEN_FROM_IB  = TEXT.getString("mTAKEN_FROM_IB"),
        mDO_NOT_FORGET  = TEXT.getString("mDO_NOT_FORGET"),
        mNO_CLOSE       = TEXT.getString("mNO_CLOSE"),
        mNOT_CURRENT_PLACE = TEXT.getString("mNOT_CURRENT_PLACE"),
        mONLY_ICEBOX    = TEXT.getString("mONLY_ICEBOX"),
        mICEBOX_CLOSED  = TEXT.getString("mICEBOX_CLOSED");


    /** Formats of messages written in reaction to certain commands. */
    public static final String
        fWRONG_INTEGER  = TEXT.getString("fWRONG_INTEGER"),
        fAGE            = TEXT.getString("fAGE"),
        fYEAR           = TEXT.getString("fYEAR"),
        fWRONG_RANGE    = TEXT.getString("fWRONG_RANGE"),
        fONCE_MORE      = TEXT.getString("fONCE_MORE"),
        fTOO_LOW        = TEXT.getString("fTOO_LOW"),
        fNOT_ALLOWED    = TEXT.getString("fNOT_ALLOWED"),
        fDOES_NOT_MATCH = TEXT.getString("fDOES_NOT_MATCH");


    /** Parts of internal errors (exceptions) messages. */

    public static final String
        eAACTION_1  = TEXT.getString("eAACTION_1"),
        eAACTION_2  = TEXT.getString("eAACTION_2"),
        eITEM_PREFIX= TEXT.getString("eITEM_PREFIX"),
        eITEM_SUFFIX= TEXT.getString("eITEM_SUFFIX"),
        eLOCALE_SET = TEXT.getString("eLOCALE_SET"),
        eEND        = null;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Returns a string containing the entered names separated with commas.
     *
     * @param názvy Names that are necessary to be unified
     * @return The resulting string from unified entered names
     */
    static String cm(String... názvy)
    {
        String result = Arrays.stream(názvy)
                              .collect(Collectors.joining(", "));
        return result;
    }




//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /** Private constructor preventing the instance creating.*/
    private TextsL() {}



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
