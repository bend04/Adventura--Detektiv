/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.scenario_test;

import eu.pedu.adv19s_fw.game_txt.BasicActions;
import eu.pedu.adv19s_fw.scenario.Scenario;
import eu.pedu.adv19s_fw.scenario.ScenarioStep;
import eu.pedu.adv19s_fw.scenario.TypeOfStep;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;



/***************************************************************************
 * Instance třídy {@code SMSummary} představují přepravky
 * pro uchování informacích charakterizujících svět hry
 * na základě vyhodnocení scénářů a jejich kroků.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class SMSummary
{
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

    /** Informace, zda je scénář bez chyb. */
    public final boolean ok;

    /** Množina názvů všech dosud zmíněných prostorů. */
    public final Set<String> mentionedPlaces;

    /** Množina názvů všech doposud zadaných akcí. */
    public final Set<String> enteredActions;

    /** Množina názvů všech dosud zahlédnutých objektů. */
    public final Set<String> seenItems;

    /** Počáteční krok v základním úspěšném scénáři. */
    public final ScenarioStep startStep;

    /** Poslední krok základního úspěšného scénáře. */
    public final ScenarioStep endStep;

    /** Mapa mapující typy základních (povinných) příkazů na jejich názvy. */
    public final BasicActions basicActions;

    /** Mapa mapující názvy příkazů na skupinu jejich typu. */
    public final Map<String, TypeOfStep> name2typeGroup;

    /** Seznam prověřovaných scénářů. */
    public final List<Scenario> scenarios;

    /** Výsledky prověření jednotlivých scénářů:
     *  {@code true} vyhověl, {@code false} nevyhověl. */
    public final boolean[] results;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***********************************************************************
     * Konstruktor inicializující atributy přepravky.
     *
     * @param ok              Informace, zda je scénář bez chyb
     * @param mentionedPlaces  Množina názvů všech dosud zmíněných prostorů
     * @param enteredActions  Množina názvů všech doposud zadaných příkazů
     * @param seenItems       Množina názvů všech dosud zahlédnutých objektů
     * @param startStep       Počáteční krok v základním úspěšném scénáři
     * @param endStep         Poslední krok základního úspěšného scénáře
     * @param basicActions    Mapa mapující typy příkazů na jejich názvy
     * @param name2typeGroup  Mapa mapující názvy příkazů na skupinu jejich typu
     * @param scenarios       Seznam prověřovaných scénářů
     * @param results         Výsledky prověření jednotlivých scénářů:
     *                        {@code true} vyhověl, {@code false} nevyhověl
     */
    public SMSummary(boolean ok,
                     Set<String>  mentionedPlaces,
                     Set<String>  enteredActions,
                     Set<String>  seenItems,
                     ScenarioStep startStep,
                     ScenarioStep endStep,
                     BasicActions basicActions,
                     Map<String,TypeOfStep> name2typeGroup,
                     List<Scenario>         scenarios,
                     boolean[]              results)
    {
        this.ok = ok;
        this.mentionedPlaces= mentionedPlaces;
        this.enteredActions = enteredActions;
        this.seenItems      = seenItems;
        this.startStep      = startStep;
        this.endStep        = endStep;
        this.basicActions   = basicActions;
        this.name2typeGroup = name2typeGroup;
        this.scenarios      = Collections.unmodifiableList(scenarios);
        this.results        = results;
    }


    /***********************************************************************
     * Konstruktor vytvářející prázdnou přepravku pro dočasné použití.
     */
    @SuppressWarnings("unchecked")
    public SMSummary()
    {
        this.ok = false;
        this.mentionedPlaces= Collections.EMPTY_SET;
        this.enteredActions = Collections.EMPTY_SET;
        this.seenItems      = Collections.EMPTY_SET;
        this.startStep      = new ScenarioStep("AUXILIARY-START");
        this.endStep        = new ScenarioStep("AUXILIARY-STOP");
        this.basicActions   = new BasicActions("M", "D", "U", "H", "E");
        this.name2typeGroup = Collections.EMPTY_MAP;
        this.scenarios      = Collections.EMPTY_LIST;
        this.results        = new boolean[] {};
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
