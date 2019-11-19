/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util;

import eu.pedu.adv19s_fw.Framework;
import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.test_util.common.TestException;
import eu.pedu.adv19s_fw.test_util.factory_test.FactoryTester;

import java.util.ArrayList;
import java.util.List;

import static eu.pedu.adv19s_fw.utilities.ConditionalPrinter.*;
import static eu.pedu.adv19s_fw.utilities.FormatStrings.*;



/*******************************************************************************
 * Třída {@code ATester} slouží jako rodičovská třída různých testovacích tříd,
 * jejichž instancím poskytuje metody testující některé základní vlastnosti
 * spolu s metodou definující reakci na chybu;
 * její instance si navíc pamatuje tovární objekt testované aplikace.
 * <p>
 * Třída nedefinuje abstraktní testovací metodu,
 * protože každý z jejich potomků může testovat něco jiného
 * s jinými požadovanými vstupními informacemi.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public abstract class ATester
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Hladina, při níž se testují
     *  pouze základní vlastnosti továrního objektu. */
    public static final int FACTORY_ONLY = 0;

    /** Hladina, při níž se testuje tovární objekt plus
     *  jím poskytovaný správce scénářů. */
    public static final int SM_LEVEL = 1;

    /** Hladina, při níž se testuje tovární objekt plus
     *  jím poskytovaný správce scénářů a hra. */
    public static final int GAME_LEVEL = 2;

    /** Hladina, při níž se testuje tovární objekt plus
     *  jím poskytovaný správce scénářů, hra a textové uživatelské rozhraní. */
    public static final int TEXTUI_LEVEL = 3;

    /** Hladina, při níž se testuje tovární objekt plus
     *  jím poskytovaný správce scénářů, hra, textové uživatelské rozhraní
     *  a grafické uživatelské rozhraní. */
    public static final int GUI_LEVEL = 4;


    //===== Konstanty související s lokalizací vypisovaných textů =====

    /** Seznam lokalizovaných zpráv. */
    private static final List<String> I18N;

    /** Index zprávy o vyhození výjimky. */
    private static final int EXC_THROWN_MSG;

    /** Index zprávy o nepovolené délce řádku. */
    private static final int LINE_LENGTH_MSG;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Statický konstruktor nastavuje:<br>
     *  - jazykovou verzi textů chybových hlášení
     *  - implicitní ošetření nezachycených přerušení
     */
    static {
        List<String> CZ = new ArrayList<>();
        List<String> EN = new ArrayList<>();

        EXC_THROWN_MSG = CZ.size();
        CZ.add("Výjimka byla vyhozena ve vláknu %s:\n%s\n");
        EN.add("An exception was thrown in the thread %s:\n%s\n");

        LINE_LENGTH_MSG = CZ.size();
        CZ.add("Délka řádku je %s znaků, což je více než povolených "
             + "%d znaků.");
        EN.add("Line length is %s characters, which is more than "
             + "%d allowed characters.");

        switch (Framework.LANGUAGE)
        {
            case Framework.CZECH:   I18N = CZ;  break;
            case Framework.ENGLISH: I18N = EN;  break;
            default:
                throw new RuntimeException(
                    "\nThe text language is wrong.");
        }

        Thread.setDefaultUncaughtExceptionHandler(
            (Thread t, Throwable e) ->
            {
                prf(N_DOUBLELINE_N +
                    I18N.get(EXC_THROWN_MSG),
                    t, stackTrace(e));
            });
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Tovární objekt schopný dodat objekty, které se mají testovat. */
    protected final IGSMFactory gsmFactory;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Rodičovský konstruktor potomků, které nepředstavují kořenové testery,
     * a počítají proto s tím, že základní prvky aplikace jsou již otestovány.
     *
     * @param factory Tovární objekt schopný dodat objekty,
     *                které se mají testovat
     */
    protected ATester(IGSMFactory factory)
    {
        this.gsmFactory = factory;
    }


    /***************************************************************************
     * Rodičovský konstruktor kořenových testerů, které zahajují testování
     * a prověřují proto na počátku základní objekty celé aplikace.
     *
     * @param factory   Tovární objekt schopný dodat objekty,
     *                  které se mají testovat
     * @param testLevel Hladina dokončenosti aplikace
     *                  a s ní spojená hladina testování
     */
    protected ATester(IGSMFactory factory, int testLevel)
    {
        this(factory);
        checkFactory(factory, testLevel);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Vytiskne chybové hlášení a vyhodí výjimku {@code TestException}.
     *
     * @param format Formát tisku chybového hlášení ve stylu {@code printf}.
     *               Bude ještě doplněn společnou úvodní a závěrečnou sekvencí.
     * @param arguments Případné další parametry k tisku
     */
    protected void ERROR(String format, Object... arguments)
    {
        String        message   = String.format('\n' + format, arguments);
        String        complet   = N_BERFORE + message + N_AFTER_N;
        TestException exception = new TestException(complet);
        throw exception;
    }


    /***************************************************************************
     * Prověří délku řádků zadaného textu.
     * Tento test využívají testery scénářů spolu s testery běhu hry.
     *
     * @param description Popis prověřovaného textu
     * @param text        Prověřovaný text
     */
    protected void checkMessageLength(String description, String text)
    {
        String[] lines = text.split("\\n");
        for (String line : lines) {
            if (line.length() > Framework.MAX_LINE_LENGTH) {
                ERROR(I18N.get(LINE_LENGTH_MSG),
                      description, Framework.MAX_LINE_LENGTH);
                return;
            }
        }
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Prověří tovární objekt a jím generované objekty.
     *
     * @param factory   Tovární objekt schopný dodat objekty,
     *                  které se mají testovat
     * @param testLevel Hladina dokončenosti aplikace
     *                  a s ní spojená hladina testování
     */
    private void checkFactory(IGSMFactory factory, int testLevel)
    {
        FactoryTester factoryTester = new FactoryTester(factory);
        factoryTester.testForLevel(testLevel);
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
