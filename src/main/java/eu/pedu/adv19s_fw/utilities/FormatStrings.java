/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;



/*******************************************************************************
 * Instance třídy {@code FormatStrings} představují ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class FormatStrings
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    public static final char NL = '\n';

    public static final String

    LINE =
"-----------------------------------------------------------------------------",

    DOUBLELINE =
"=============================================================================",

    HASHES =
"#############################################################################",

    CIRCUMFLEXES =
"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^",

    /** Oddělovač vypisovaný před vlastní chybovou zprávou. */
    BERFORE =
"VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV",

    /** Oddělovač vypisovaný za vlastní chybovou zprávou. */
    AFTER =
"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",

    LINE_N   =        LINE + '\n',
    N_LINE   = '\n' + LINE,
    N_LINE_N = '\n' + LINE + '\n',
    LINE_ARG =        LINE + "%s\n",

    DOUBLELINE_N    =          DOUBLELINE + '\n',
    N_DOUBLELINE    =   '\n' + DOUBLELINE,
    N_DOUBLELINE_N  =   '\n' + DOUBLELINE + '\n',
    NN_DOUBLELINE   = "\n\n" + DOUBLELINE,
    NN_DOUBLELINE_NN= "\n\n" + DOUBLELINE + "\n\n",

    HASHES_N   =        HASHES + '\n',
    N_HASHES   = '\n' + HASHES,
    N_HASHES_N = '\n' + HASHES + '\n',

    CIRCUMFLEXES_N   =        CIRCUMFLEXES + '\n',
    N_CIRCUMFLEXES   = '\n' + CIRCUMFLEXES,
    N_CIRCUMFLEXES_N = '\n' + CIRCUMFLEXES + '\n',

    BERFORE_N   =        BERFORE + '\n',
    N_BERFORE   = '\n' + BERFORE,
    N_BERFORE_N = '\n' + BERFORE + '\n',

    AFTER_N   =        AFTER + '\n',
    N_AFTER   = '\n' + AFTER,
    N_AFTER_N = '\n' + AFTER + '\n',

    EMPTY_STRING=""; //Abych nemusel přemýšlet nad čárkami



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /** Soukromý konstruktor zabraňující vytvoření instance.*/
    private FormatStrings() {}



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
