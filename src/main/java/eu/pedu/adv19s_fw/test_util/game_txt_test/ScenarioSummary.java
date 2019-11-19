/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.game_txt_test;

import eu.pedu.adv19s_fw.scenario.Scenario;



/*******************************************************************************
 * Instance třídy {@code Result} představují přepravky s informacemi
 * o výsledku testu provedeného nad konkrétním scénářem.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class ScenarioSummary
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Scénář, podle kterého se testovalo. */
    public final Scenario scenario;

    /** Informace o tom, zda test dopadl úspěšně. */
    public final boolean success;

    /** Odkaz na případnou vyhozenou výjimku. */
    public final Exception exception;

    /** Stručná zpráva o průběhu testu. */
    public final String shortMessage;

    /** Kompletní zpráva o průběhu testu. */
    public final String verboseMessage;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří přepravku s informacemi o výsledku testu.
     *
     * @param scenario  Scénář, podle kterého se testovalo
     * @param success   Informace o tom, zda test dopadl úspěšně
     * @param exception Odkaz na případnou vyhozenou výjimku
     * @param shortMessage   Stručná zpráva o průběhu testu
     * @param verboseMessage Kompletní zpráva o průběhu testu
     */
    public ScenarioSummary(Scenario scenario, boolean success,
                           Exception exception,
                           String shortMessage, String verboseMessage)
        {
            this.scenario       = scenario;
            this.success        = success;
            this.exception      = exception;
            this.shortMessage   = shortMessage;
            this.verboseMessage = verboseMessage;
        }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================

//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================
//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
}
