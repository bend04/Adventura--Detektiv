/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.scenario;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;



/*******************************************************************************
 * Instance třídy {@code TypeOfStep} představují
 * možné typy kroků zadávaných ve scénáři.
 * Znalost typu kroku umožňuje zkontrolovat správnost zadání dat
 * v jednotlivých krocích scénáře.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public enum TypeOfStep
{
//\CE== VALUES OF THE ENUMERATION TYPE =========================================

    /** Typ kroku neurčeného pro zařazení do nějakého scénáře, ale pouze pro
     *  doplnění chybového hlášení a pomocné akce.*/tsNOT_SET(6,-1, null),

//Typy řádných kroků, které se musí všechny objevit v základním úspěšném scénáři
    /** Startovací krok s prázdným názvem.      */tsSTART    (0,-1, null),
    /** Hráč se přesune do sousedního prostoru. */tsMOVE     (0, 1, null),
    /** Úspěšné "zvednutí" objektu v prostoru.  */tsTAKE     (0, 1, null),
    /** Úspěšné položení objektu z batohu.      */tsPUT_DOWN (0, 1, null),

//Na následující typy kroků musí hra umět zareagovat =>
//   musí být otestovány v základním chybovém scénáři

    /** Příkaz okamžitě ukončující hru.         */tsEND      (1, 0, null),
    /** Nápověda.                               */tsHELP     (1, 0, null),

//Problémy se správným zadáním příkazu
    /** Startovací příkaz není prázdný řetězec. */tsNOT_START(2,-1, null),
    /** Nestartovací zadání prázdného řetězce.  */tsEMPTY    (2,-1, tsSTART),
    /** Hra nezná zadanou akci.                 */tsUNKNOWN  (2,-1, null),

//Vyvolání některé ze základních akcí bez povinného parametru
    /** Nebylo zadáno, kam se přesunout. */tsMOVE_WA         (2, 0, tsMOVE),
    /** Nebylo zadáno, co se má zvednout.*/tsTAKE_WA         (2, 0, tsTAKE),
    /** Nebylo zadáno, co se má položit. */tsPUT_DOWN_WA     (2, 0, tsPUT_DOWN),

//Problémy se změnou místnosti
    /** Cílový prostor není sousedem aktuálního.*/tsBAD_NEIGHBOR(2, 1, tsMOVE),

//Problémy se zvednutim či položením předmětu
    /** Zadaný předmět v prostoru není.      */tsBAD_ITEM    (2, 1, tsTAKE),
    /** Zadaný předmět nelze zvednout.       */tsUNMOVABLE   (2, 1, tsTAKE),
    /** Další předmět se nevejde do batohu.  */tsBAG_FULL    (2, 1, tsTAKE),
    /** Zadaný předmět v batohu není.        */tsNOT_IN_BAG  (2, 1, tsPUT_DOWN),

//Nepovinné typy kroků
    /** Bezparametrická akce, která nepatří mezi základní povinné akce,
     *  mění pouze nějaký vnitřní stav hry.      */tsNON_STANDARD0(3, 0, null),

    /** Jednoparametrická akce, která nepatří mezi základní povinné akce,
     *  mění pouze nějaký vnitřní stav hry.      */tsNON_STANDARD1(3, 1, null),

    /** Dvouparametrická akce, která nepatří mezi základní povinné akce,
     *  mění pouze nějaký vnitřní stav hry.      */tsNON_STANDARD2(3, 2, null),

    /** Tříparametrická akce, která nepatří mezi základní povinné akce,
     *  mění pouze nějaký vnitřní stav hry.      */tsNON_STANDARD3(3, 3, null),

//Wrogly entered commands for facultative additional actions. */
    /** Unary action without arg.          */tsNS1_0Args
                                             (4, -1, tsNON_STANDARD1),
    /** Too few args for binary action.    */tsNS2_1Args
                                             (4, -2, tsNON_STANDARD2),
//    /** Too few args for ternary action.   */tsNS3_012Args
//                                             (4, 2, tsNON_STANDARD3),
    /** Unary action with wrong arg.       */tsNS1_WRONG_ARG
                                             (4, 1, tsNON_STANDARD1),
    /** Binary action with wrong 1st arg.  */tsNS2_WRONG_1stARG
                                             (4, 2, tsNON_STANDARD2),
    /** Binary action with wrong 2nd arg.  */tsNS2_WRONG_2ndARG
                                             (4, 2, tsNON_STANDARD2),
//    /** Ternary action with wrong 1st arg. */tsNS3_WRONG_1stARG
//                                             (4, 3, tsNON_STANDARD3),
//    /** Ternary action with wrong 2nd arg. */tsNS3_WRONG_2ntARG
//                                             (4, 3, tsNON_STANDARD3),
//    /** Ternary action with wrong 3rd arg. */tsNS3_WRONG_3rdARG
//                                             (4, 3, tsNON_STANDARD3),


    /** Zadaný krok nepopisuje klasickou akci,
     *  ale je součástí rozhovoru hráče s nějakou postavou či zařízením hry
     *  nebo nějaké obdobné činnosti.            */     tsDIALOG  (5,-1, null),

//Typy kroku nepoužitelných pro testování reakce hry na zadaný příkaz
    /** Krok tohoto typu nebude možno použít pro test správné funkce hry,
     *  protože neobsahuje data o stavu po provedení příkazu.
     *  Krok je určen pouze pro předvedení funkce hry.
     *  Demonstrační kroky se používají např. při testování funkce
     *  uživatelského rozhraní.                  */     tsDEMO    (6,-1, null),

//Typy kroku používané v modifikačních zadáních při obhajobách
    /** Modifikovaný krok v zadáních při obhajobách.*/  tsMOD_A   (8,-1, null),
    /** Modifikovaný krok v zadáních při obhajobách.*/  tsMOD_B   (8,-1, null),
    /** Modifikovaný krok v zadáních při obhajobách.*/  tsMOD_C   (8,-1, null),
    ;



//##############################################################################
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Základní typy kroků povinně definovaných akcí. */
    public static final Set<TypeOfStep> BASIC_ACTIONS;

    /** Typy kroků, které musí být povinně obsaženy v základním scénáři. */
    public static final Set<TypeOfStep> REGULAR_ACTIONS;

    /** Typy kroků, jež musí být povinně obsaženy v základním chybovém scénáři.
     *  Sem patří nápověda + nestandardní (předčasné) ukončení hry +
     *  různé druhy chybně zadaných příkazů, které nesmí hru rozhodit.
     *  Na všechny tyto typy nestandardních příkazů musí hra umět správně
     *  reagovat, a základní chybový scénář slouží k testu těchto reakcí. */
    public static final Set<TypeOfStep> MISTAKE_ACTIONS;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================

    static {
        Set<TypeOfStep> basic   = EnumSet.noneOf(TypeOfStep.class);
        Set<TypeOfStep> regular = EnumSet.noneOf(TypeOfStep.class);
        Set<TypeOfStep> mistake = EnumSet.noneOf(TypeOfStep.class);
        for (TypeOfStep stepType : TypeOfStep.values()) {
            if (null != stepType.SUBTYPE) {
                switch (stepType.SUBTYPE)
                {
                    case stCORRECT:
                        basic  .add(stepType);
                        regular.add(stepType);
                        break;
                    case stHELPSTOP:
                        basic  .add(stepType);
                        mistake.add(stepType);
                        break;
                    case stMISTAKE:
                        mistake.add(stepType);
                        break;
                    default:
                        break;
                }
            }
        }
        //Definuji všechny množiny jako neměnné
        REGULAR_ACTIONS = Collections.unmodifiableSet(regular);
        MISTAKE_ACTIONS = Collections.unmodifiableSet(mistake);
        BASIC_ACTIONS   = Collections.unmodifiableSet(basic);
    }



//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Podtyp daného typu kroku scénáře. */
    public final Subtype SUBTYPE;

    /** Počet parametrů. */
    public final int PARAM_COUNT;

    /** Skupina ekvivalentních typů = typů aktivujících stejnou akci. */
    public final TypeOfStep GROUP;


//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Definuje nový typ kroku a na základě hodnoty parametru
     * mu přiřadí příslušný podtyp a požadovaný počet parametrů.
     *
     * @param subtype    Ordinální číslo podtypu (aby byl zápis kratší)
     * @param paramCount Požadovaný počet parametrů. <br>
     *                   Hodnota {@code -1} se používá u typů příkazů,
     *                   u nichž nemá smysl zkoumat počet parametrů
     * @param group      Skupina ekvivalentních typů příkazů
     */
    private TypeOfStep(int subtype, int paramCount, TypeOfStep group)
    {
        this.SUBTYPE     = Subtype.values()[subtype];
        this.PARAM_COUNT = paramCount;
        this.GROUP       = (group == null)  ?  this  :  group;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí podtyp příslušného kroku scénáře.
     *
     * @return Podtyp daného kroku scénáře
     */
    public Subtype getSubtype()
    {
        return SUBTYPE;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

    /***************************************************************************
//%L+ CZ
     * Typy kroků scénářů jsou rozděleny do několika podtypů.
     * Tento výčtový typ je definuje.
//%Lx EN
     * The scenario step types are selected into several subtypes.
     * This nested enum type defines them.
//%L-
     */
    /*static */ public enum Subtype
    {
        /** Správně zadaný příkaz povinně zařazený do HAPPY. 0 */ stCORRECT,
        /** Správně zadaný povinný příkaz HELP či STOP.      1 */ stHELPSTOP,
        /** Povinně testovaný chybně zadaný příkaz.          2 */ stMISTAKE,
        /** Nepovinný příkaz.                                3 */ stNONSTANDARD,
        /** Povinné testy nepovinných příkazů.               4 */ stNSMISTAKE,

        /** Součást rozhovoru.                               5 */ stDIALOG,
        /** Demonstrační krok bez doprovodných informací,
         *  který proto nelze použít k testu funkce hry.     6 */ stDEMO,
        /** Krok vytvořený pro pomocné práce.                7 */ stUNDEFINED,
        /** Typ kroku používaný v modifikačních zadáních
         *  v obhajobách.                                    8 */ stMODIFIED;
    }

}
