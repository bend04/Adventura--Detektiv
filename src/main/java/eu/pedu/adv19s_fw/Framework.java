/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw;

import eu.pedu.adv19s_fw.empty_classes.IAuthorPrototype;
import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.test_util.default_game.GSMTMultiFactory;

import java.awt.Point;



/*******************************************************************************
 * Knihovní třída {@code Framework} obsahuje konstanty a metody
 * sdílené různými třídami napříč aplikací.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class Framework
  implements IAuthorPrototype
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Framework version. */
    public static final String VERSION = "17.02.6637 — 2017-02-24";

    /** Index of the Czech language. */
    public static final int CZECH = 1;

    /** Index of the Czech language. */
    public static final int ENGLISH = 2;

    /** Language of the printed texts. */
    public static final int LANGUAGE = CZECH;

    /** Maximální povolená délka řádku. */
    public static final int MAX_LINE_LENGTH = 80;

    /** Výchozí pozice dotazovacího okna. */
    public static final Point CONTROL_WINDOW_POSITION = new Point(1000, 000);

    /** Pozice aplikačního okna
     *  připravená práci na počítači s více monitory. */
    public static final Point START_WINDOW_POSITION = new Point(0, -00);

    /** ClassLoader definující umístění kořenového balíčku. */
    public static final ClassLoader CLASS_LOADER =
                                    Framework.class.getClassLoader();



//
//    /** Adresa složky se zdroji relativně vůči složce s kořenovým balíčkem. */
//    public static final String PATH_TO_DATA;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//
//    static {
//        Package pkg  = Framework.class.getPackage();
//        String  path = pkg.getName().replace(".", "/");
//        PATH_TO_DATA = path + "/data/";
//    }
//
//
//
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Vrátí instanci implicitní hry implementující interfejs {@link IGame},
     * tj. hry předpokládající použití textového uživatelského rozhraní.
     *
     * @return Instance implicitní textové hry
     */
    public static IGSMFactory getDefaultFactory()
    {
        return new GSMTMultiFactory();
    }


    /***************************************************************************
     * Metoda vrací textový řetězec definující aktuální verzi frameworku.
     *
     * @return Řetězec definující aktuální verzi frameworku
     */
    public static String getVersion()
    {
        return VERSION;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Konstruktor není definován jako soukromý, aby bylo možno definovat
     * potomka specializovaného na podporu tvorby a testování GUI.
     */
    protected Framework() {}



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
