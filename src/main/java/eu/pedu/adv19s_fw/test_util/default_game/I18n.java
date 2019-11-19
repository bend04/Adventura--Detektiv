/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;



/*******************************************************************************
//%L+ CZ
 * Třída Instance třídy {@code I18n} představují ...
//%Lx EN
 * The {@code I18n} class instances represent ...
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public final class I18n extends ResourceBundle
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Název souborů s lokalizovanými texty. */
    private static final String RB_NAME =
            "eu/pedu/adv19s_fw/test_util/default_game/DATA/Texts";
//          "eu.pedu.adv19s_fw.test_util.default_game.DATA.Texts";

    /** Řetězec označující začátek textu s názvem klíče
     *  označujícího vkládaný text. */
    private static final String MARKER = "${";

    /** Délka řetězce {@link #MARKER}. */
    private static final int ML = MARKER.length();

    /** Řetězec označující začátek textu s názvem klíče
     *  označujícího vkládaný text. */
    private static final String CLOSER = "}";

    /** Délka řetězce {@link #CLOSER}. */
    private static final int CL = CLOSER.length();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Aktuálně nastavený zdroj lokalizovaných textů. */
    private static I18n currentI18n = new I18n("cz");



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Vrátí aktuálně nastavené {@link Locale}.
     *
     * @return Aktuálně nastavené {@link Locale}
     */
    public static Locale getCurrentLocale()
    {
        return currentI18n.getLocale();
    }


    /***************************************************************************
     * Nastaví zadané {@link Locale}.
     * V případě potřeby načte příslušné soubory.
     *
     * @param localeName Nastavované {@link Locale}
     */
    public static void setCurrentLocale(String localeName)
    {
        I18n.currentI18n = new I18n(localeName);
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Vrátí lokalizovaný text se zadaným názvem.
     *
     * @param key Název požadovaného lokalizovaného textu
     * @return Požadovaný lokalizovaný text
     */
    public static String L(String key)
    {
        return currentI18n.getString(key);
    }



//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Mapa složených názvů. */
    private final Map<String, String> name2text;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří {@link ResourceBundle} pro zadané {@link Locale} s tím,
     * že předem připraví složené texty.
     *
     * @param localeName Označení {@link Locale}, pro něž je instance vytvářena
     */
    public I18n(String localeName)
    {
        this(localeName, RB_NAME);
    }


    /***************************************************************************
     * Pomocný konstruktor pro testování vytvoří {@link ResourceBundle}
     * pro zadané {@link Locale} a zadaný soubor prostředků s tím,
     * že předem připraví složené texty.
     *
     * @param localeName Označení {@link Locale}, pro něž je instance vytvářena
     * @param rbName     Relativní cesta k souboru prostředků zakončení jeho
     *                   názvem bez uvedení jazyků a přípony
     */
    I18n(String localeName, String rbName)
    {
        super();
        Locale         locale   = Locale.forLanguageTag(localeName);
        ResourceBundle myParent = ResourceBundle.getBundle(rbName, locale);
        name2text = prepareTexts(myParent);
        setParent(myParent);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Returns an enumeration of the keys.
     *
     * @return an <code>Enumeration</code> of the keys contained in
     *         this <code>ResourceBundle</code> and its parent bundles.
     */
    @Override
    public Enumeration<String> getKeys()
    {
        return parent.getKeys();
    }


    /***************************************************************************
     * Gets an object for the given key from this resource bundle.
     * Returns null if this resource bundle does not contain an
     * object for the given key.
     *
     * @param key the key for the desired object
     * @exception NullPointerException if <code>key</code> is <code>null</code>
     * @return the object for the given key, or null
     */
    @Override
    protected Object handleGetObject(String key)
    {
        return name2text.get(key);
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Vytvoří mapu převádějící název textu na jeho hodnotu.
     * Dvojice (název;hodnota) přitom převezme ze zadaného zdroje s tím,
     * že přebírá pouze texty, které je třeba nejprve upravit.
     *
     * @param parent Zadaný zdroj textů
     * @return Vytvořená mapa
     */
    private Map<String, String> prepareTexts(ResourceBundle source)
    {
        Map<String, String> map = new HashMap<>();
        for(String key : source.keySet()) {
            String text = source.getString(key);
            if (text.contains(MARKER)) {
                map.put(key, process(text, source));
            }
        }
        return map;
    }


    /***************************************************************************
     * Zpracuje text obsahující odkaz na další text zadaného zdroje.
     *
     * @param text      Zpracovávaný text
     * @param source    Zdroj odkazovaného textu
     * @return Výsledný poskládaný text
     */
    private String process(String text, ResourceBundle source)
    {
        int index1, i1pl, index2, i2pl;
        String name, incl, rest, result;

        index1 = text.indexOf(MARKER);
        if  (index1 < 0) { return text; }
        i1pl   = index1 + ML;
        index2 = text.indexOf(CLOSER, i1pl);
        i2pl   = index2 + CL;
        name   = text.substring(i1pl, index2);
        incl   = process(source.getString(name), source);
        rest   = (text.length() > i2pl)
               ? process(text.substring(i2pl), source)
               : "";
        result = text.substring(0, index1) + incl + rest;
        return result;
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

}
