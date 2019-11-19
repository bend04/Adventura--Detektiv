/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.scenario;



/*******************************************************************************
 * Instance výčtového typu {@code TypeOfScenario} představují možné typy
 * scénářů hry.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public enum TypeOfScenario
{
//\CE== VALUES OF THE ENUMERATION TYPE =========================================

    /** Scénář procházející možnou cestu vedoucí k dosažení cíle
     *  a obsahující současně informace pro otestování reakcí hry
     *  na zadávané příkazy. Tento druh scénáře musí každý správce nabízet.
     *  Tento scénář musí vyhovovat sadě požadavků, mezi něž patří např.
     *  <ul>
     *  <li>minimální počet kroků scénáře,</li>
     *  <li>minimální počet navštívených prostorů,</li>
     *  <li>minimální počet různých druhů příkazů,</li>
     *  <li>provedení základních povinných akcí, tj.
     *  <ul><li>přechodu mezi prostory,</li>
     *      <li>zvednutí objektu v aktuálním prostoru,</li>
     *      <li>položení objektu v aktuálního prostoru.</li>
     *  </ul>
     *  <li>úspěšné ukončení hry.</li>
     *  </ul>
     *  Správce scénářů musí spravovat právě jeden scénář tohoto typu.
     */
    scHAPPY,

    /** Scénář sloužící k otestování reakcí hry
     *  na chybně zadané příkazy uživatele.
     *  Scénář musí obsahovat všechny druhy kroků, které jsou ve výčtovém typu
     *  {@code cz.vse.adv_fw.scenario.TypeOfStep}
     *  uvedeny jako povinné, tj. kroků, které mají podtyp
     *  {@code TypeOfStep.Subtype.stMISTAKE}.
     *  Mezi tyto kroky patří vedle kroků definujících reakci programu
     *  na chybně zadané příkazy hráče, také příkaz žádající nápovědu
     *  a příkaz pro explicitní ukončení hry.
     *  Správce scénářů musí spravovat právě jeden scénář tohoto typu.
     */
    scMISTAKES,

    /** Scénář sloužící k otestování reakcí hry na chybně zadané příkazy
     *  spouštějící nestandardní akce. Musí obsahovat všechny typy akcí
     *  s podtypem {@code TypeOfStep.Subtype.stMISTAKE_NS}.
     *  Správce scénářů má poskytovat právě jeden scénář tohoto typu.
     */
    scMISTAKE_NS,

    /** Obecný scénář, podle nějž je možno testovat chod hry,
     *  ale který nepatří do žádného z výše uvedených dvou povinných typů.
     *  Tento scénář musí být testovatelný,
     *  a proto nesmí obsahovat žádný demonstrační krok.
     *  Správce scénářů může spravovat libovolný počet scénářů tohoto typu.
     */
    scGENERAL,

    /** Scénář sloužící pouze k demonstraci možné cesty
     *  a neumožňující testování chodu hry.
     *  Scénář smí obsahovat testovatelné kroky, avšak nesmí se podle nich
     *  testovat, protože mezi nimi mohou být i kroky čistě demonstrační.
     *  Správce scénářů může spravovat libovolný počet scénářů tohoto typu.
     */
    scDEMO;



//##############################################################################
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
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

    /***************************************************************************
     *
     */
    private TypeOfScenario()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
