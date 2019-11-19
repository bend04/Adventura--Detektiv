/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;



/*******************************************************************************
 * Instance třídy {@code DBG_Logger} představuje logger,
 * prostřednictvím nějž zaznamenáváme potřebné informace.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class DBG_Logger extends Logger
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Jediná instance daného loggeru. */
    private static final DBG_Logger SINGLETON = new DBG_Logger();

    private static final int levelValue = Level.INFO.intValue();
    private static final int offValue   = Level.OFF.intValue();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Vrátí odkaz na jedináčka.
     *
     * @return Odkaz na jedináčka
     */
    public static DBG_Logger getInstance()
    {
        return SINGLETON;
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Soukromý konstruktor zabraňující vytvoření více instancí.
     */
    private DBG_Logger()
    {
        super("DBG", null);
        super.setUseParentHandlers(false);
        Handler handler = new DBG_Handler();
        super.addHandler(handler);
        LogManager lm = LogManager.getLogManager();
        lm.addLogger(this);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Vypíše formátovanou chybovou zprávu s jedním parametrem.
     *
     * @param format    Formát zprávy
     * @param parametr  Parametr, jehož hodnota bude součástí zprávy
     */
    public void severe(String format, Object parametr)
    {
        log(Level.SEVERE, format, parametr);
    }


    /***************************************************************************
     * Vypíše formátovanou chybovou zprávu s více parametry.
     *
     * @param formát    Formát zprávy
     * @param parametry Parametry, jejichž hodnoty budou součástí zprávy
     */
    public void severe(String formát, Object... parametry)
    {
        log(Level.SEVERE, formát, parametry);
    }


    /***************************************************************************
     * Vypíše formátovanou chybovou zprávu s více parametry.
     *
     * @param throwable Výjimka, jejíž zpráva se budem vypisovat
     */
    public void severe(Throwable throwable)
    {
        log(Level.SEVERE, stackTrace(throwable));
    }


    /***************************************************************************
     * Vypíše formátovanou varovnou zprávu s jedním parametrem.
     *
     * @param formát    Formát zprávy
     * @param parametr  Parametr, jehož hodnota bude součástí zprávy
     */
    public void warning(String formát, Object parametr)
    {
        log(Level.WARNING, formát, parametr);
    }


    /***************************************************************************
     * Vypíše formátovanou varovnou zprávu s více parametry.
     *
     * @param formát    Formát zprávy
     * @param parametry Parametry, jejichž hodnoty budou součástí zprávy
     */
    public void warning(String formát, Object... parametry)
    {
        log(Level.WARNING, formát, parametry);
    }


    /***************************************************************************
     * Vypíše formátovanou informační zprávu s jedním parametrem.
     *
     * @param formát    Formát zprávy
     * @param parametr  Parametr, jehož hodnota bude součástí zprávy
     */
    public void info(String formát, Object parametr)
    {
        log(Level.INFO, formát, parametr);
    }


    /***************************************************************************
     * Vypíše formátovanou informační zprávu s více parametry.
     *
     * @param formát    Formát zprávy
     * @param parametry Parametry, jejichž hodnoty budou součástí zprávy
     */
    public void info(String formát, Object... parametry)
    {
        log(Level.INFO, formát, parametry);
    }


    /***************************************************************************
     * Vypíše formátovanou informační zprávu s jedním parametrem.
     *
     * @param formát    Formát zprávy
     * @param parametr  Parametr, jehož hodnota bude součástí zprávy
     */
    public void fine(String formát, Object parametr)
    {
        log(Level.FINE, formát, parametr);
    }


    /***************************************************************************
     * Vypíše formátovanou informační zprávu s více parametry.
     *
     * @param formát    Formát zprávy
     * @param parametry Parametry, jejichž hodnoty budou součástí zprávy
     */
    public void fine(String formát, Object... parametry)
    {
        log(Level.FINE, formát, parametry);
    }


    /***************************************************************************
     * Vypíše formátovanou informační zprávu s jedním parametrem.
     *
     * @param formát    Formát zprávy
     * @param parametr  Parametr, jehož hodnota bude součástí zprávy
     */
    public void finer(String formát, Object parametr)
    {
        log(Level.FINER, formát, parametr);
    }


    /***************************************************************************
     * Vypíše formátovanou informační zprávu s více parametry.
     *
     * @param formát    Formát zprávy
     * @param parametry Parametry, jejichž hodnoty budou součástí zprávy
     */
    public void finer(String formát, Object... parametry)
    {
        log(Level.FINER, formát, parametry);
    }


    /***************************************************************************
     * Vypíše formátovanou informační zprávu s jedním parametrem.
     *
     * @param formát    Formát zprávy
     * @param parametr  Parametr, jehož hodnota bude součástí zprávy
     */
    public void finest(String formát, Object parametr)
    {
        log(Level.FINEST, formát, parametr);
    }


    /***************************************************************************
     * Vypíše formátovanou informační zprávu s více parametry.
     *
     * @param formát    Formát zprávy
     * @param parametry Parametry, jejichž hodnoty budou součástí zprávy
     */
    public void finest(String formát, Object... parametry)
    {
        log(Level.FINEST, formát, parametry);
    }


    /**
     * Log a message, with no arguments.
     * <p>
     * If the logger is currently enabled for the given message
     * level then the given message is forwarded to all the
     * registered output Handler objects.
     * <p>
     * @param	level	One of the message level identifiers, e.g. SEVERE
     * @param   msg	The string message (or a key in the message catalog)
     */
    @Override
    public void log(Level level, String msg) {
	super.log(level, msg);
        flushHandlers();
    }


    /**
     * Log a message, with one object parameter.
     * <p>
     * If the logger is currently enabled for the given message
     * level then a corresponding LogRecord is created and forwarded
     * to all the registered output Handler objects.
     * <p>
     * @param	level   One of the message level identifiers, e.g. SEVERE
     * @param   msg	The string message (or a key in the message catalog)
     * @param   param1	parameter to the message
     */
    @Override
    public void log(Level level, String msg, Object param1) {
        super.log(level,msg, param1);
        flushHandlers();
    }


    /**
     * Log a message, with an array of object arguments.
     * <p>
     * If the logger is currently enabled for the given message
     * level then a corresponding LogRecord is created and forwarded
     * to all the registered output Handler objects.
     * <p>
     * @param	level   One of the message level identifiers, e.g. SEVERE
     * @param   msg	The string message (or a key in the message catalog)
     * @param   params	array of parameters to the message
     */
    @Override
    public void log(Level level, String msg, Object params[]) {
        super.log(level,msg, params);
        flushHandlers();
    }


    /***************************************************************************
     * Vrátí řetězec s výpisem zásobníku po vyhozené výjimce.
     *
     * @param e Vyhozená výjimka
     * @return Výpis zásobníku
     */
    public String stackTrace(Throwable e)
    {
        StringBuilder sb = new StringBuilder(512);
        while (e != null) {
            sb.append(e).append('\n');
            StackTraceElement[] stee = e.getStackTrace();
            for (StackTraceElement ste : stee) {
                sb.append("\tat ").append(ste).append('\n');
            }

            e = e.getCause();
            if (e != null) {
                sb.append("\nCaused by\n");
            }
        }
        return sb.toString();
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     *
     */
    private void flushHandlers()
    {
        Handler[] hh = getHandlers();
        for (Handler h : hh) {
            h.flush();
        }
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
