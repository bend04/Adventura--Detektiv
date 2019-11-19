/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.game_txt_test;

import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.scenario.AScenarioManager;
import eu.pedu.adv19s_fw.test_util.common.FrameworkException;
import eu.pedu.adv19s_fw.test_util.common.TestException;
import eu.pedu.adv19s_fw.test_util.scenario_test.SMSummary;



/*******************************************************************************
 * Instance třídy {@code GameSummary} představují přepravky
 * uchovávající informace o výsledcích testu hry.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class GameSummary
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

    /** Objekt vystupující jako zadavatel poskytující pomocné objekty –
     *  testovací scénáře a návštěvníka. */
    public final ASolutionTester solutionTester;

    /** Tovární objekt poskytující klíčové objekty aplikace. */
    public final IGSMFactory factory;

    /** Správce scénářů hry. */
    public final AScenarioManager manager;

    /** Instance testované hry. */
    public final IGame game;

    /** Přepravka se souhrnem informací zjištěných ze scénářů spravovaných
     *  správcem scénářů. */
    public final SMSummary smSummary;

    /** Kompletní zpráva o průběhu testu. */
    public final StringBuilder verboseMessageBuilder;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Objekt případné vyhozené chyby či výjimky. */
    private Throwable throwable;

    /** Celkový bodový zisk. */
    private double score;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Přepravka schraňující informace zjištěné v průběhu testu hry.
     *
     * @param solutionTester  Objekt vystupující jako zadavatel
     * @param factory Tovární objekt, poskytující klíčové objekty aplikace
     */
    public GameSummary(ASolutionTester solutionTester, IGSMFactory factory)
    {
        this.solutionTester = solutionTester;
        this.factory        = factory;
        this.manager        = factory.getScenarioManager();
        this.game           = factory.getGame();
        SMSummary sms;
        try {
            sms = manager.prepareSummary();
        }
        catch (TestException tex) {
            throw tex;
        }
        catch (Exception ex) {
            System.out.println("Byla vyhozena výjimka: " + ex);
            sms = new SMSummary();
        }
        this.smSummary = sms;

        this.verboseMessageBuilder = new StringBuilder();

        this.throwable = null;
        this.score     = 0;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí informaci o tom, zda dosavadní testy proběhly bez chyb.
     *
     * @return Informace, zda dosavadní testy proběhly bez chyb
     */
    public boolean isOk()
    {
        return (throwable == null);
    }


    /***************************************************************************
     * Vrátí aktuální bodové hodnocení testovaného programu.
     *
     * @return Informace, zda dosavadní testy proběhly bez chyb
     */
    public double getScore()
    {
        return score;
    }


    /***************************************************************************
     * Přidá k dosavadnímu bodovému hodnocení zadaný přírůstek.
     *
     * @param increment Přírůstek bodového hodnocení
     */
    public void addToScore(double increment)
    {
        this.score += increment;
    }


    /***************************************************************************
     * Vrátí vyhozenou chybu či výjimku; není-li taková, vrátí {@code null}.
     *
     * @return Vyhozená chyba či výjimka nebo {@code null}
     */
    public Throwable getThrowable()
    {
        return throwable;
    }


    /***************************************************************************
     * Nastaví vyhazovanou chybu či výjimku.
     *
     * @param throwable Vyhazovaná chyba či výjimka
     */
    public void setThrowable(Throwable throwable)
    {
        if (throwable == null)  {
            throw new FrameworkException("Do GameSummary nelze zadat "
                                       + "prázdný odkaz na vyhazovaný objekt ");
        }
        this.throwable = throwable;
    }


    /***************************************************************************
     * Vrátí textový řetězec s aktuální podobou podrobné zprávy.
     *
     * @return Textový řetězec s aktuální podobou podrobné zprávy
     */
    public String getVerboseMessage()
    {
        return verboseMessageBuilder.toString();
    }
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================
//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
