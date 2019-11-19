/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;

import java.util.HashMap;
import java.util.Map;



/*******************************************************************************
 * Třída slouží k převodů znaků a řetězců do znakové sady ASCII.
 * Její metody konvertují zadané texty či znaky na řetězce
 * obsahující pouze znaky s kódem menším než 128.
 * Znaky s kódem větším než 127 převedou na jejich ASCII ekvivalenty
 * (včetně možnosti rozepsat znak na více znaků,
 * např. ß -&gt; ss, « -&gt; &lt;&lt;)
 * nebo na tvar {@code \}{@code uXXXX}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public final class ToASCII
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Mapa s převody znaků do ASCII. */
    private static final Map<Character,String> CONVERSION =
                                           new HashMap<Character, String>(64);
    static {
        String[][] pairs = {
            {"Á", "A"},  {"á", "a"},    {"Ä", "AE"}, {"ä", "ae"},
            {"Č", "C"},  {"č", "c"},    {"Ď", "D"},  {"ď", "d"},
            {"É", "E"},  {"é", "e"},    {"Ě", "E"},  {"ě", "e"},
            {"Í", "I"},  {"í", "i"},
            {"Ĺ", "L"},  {"ĺ", "l"},    {"Ľ", "L"},  {"ľ", "l"},
            {"Ň", "N"},  {"ň", "n"},    {"Ó", "O"},  {"ó", "o"},
            {"Ô", "O"},  {"ô", "o"},    {"Ö", "OE"}, {"ö", "oe"},
            {"Ŕ", "R"},  {"ŕ", "r"},    {"Ř", "R"},  {"ř", "r"},
            {"Š", "S"},  {"š", "s"},    {"Ť", "T"},  {"ť", "t"},
            {"Ú", "U"},  {"ú", "u"},    {"Ů", "U"},  {"ü", "ue"},
            {"Ü", "UE"}, {"ü", "ue"},
            {"Ý", "Y"},  {"ý", "y"},
            {"Ž", "Z"},  {"ž", "z"},
            {"ß", "ss"}, {"©", "(c)"},  {"®", "(R)"},
            {"‹", "<"},  {"›", ">"},    {"«", "<<"}, {"»", ">>"},
            {"„", "\""}, {"“", "\""},   {"”", "\""},
            {"‚", "\'"}, {"‘", "\'"},   {"’", "\'"},
            {"×", "x"},  {"÷", ":"},
            {"–", "-"},  {"—", "-"},    //ndash, mdash
            {"¦", "|"},
        };
        for (String[] ss : pairs) {
            CONVERSION.put(new Character(ss[0].charAt(0)),  ss[1]);
        }
    }



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Zkonvertuje zadaný řetězec na jeho ekvivalent
     * obsahující pouze znaky s kódem menším než 128.
     * Znaky s kódem větším než 127 převede na jejich ASCII ekvivalenty
     * (včetně možnosti rozepsat znak na více znaků,
     * např. ß -&gt; ss, « -&gt; &lt;&lt;)
     * nebo na tvar {@code \}{@code uXXXX}.
     *
     * @param text Text určený k převodu
     * @return  Převedený text
     */
    public static String text(CharSequence text)
    {
        final int length = text.length();
        final StringBuilder sb = new StringBuilder(length);
        for (int i = 0;   i < length;   i++) {
            char c = text.charAt(i);
            if (c < 128) {
                sb.append(c);
            }else if (CONVERSION.containsKey(c)) {
                sb.append(CONVERSION.get(c));
            }else {
                sb.append(expand(c));
            }
        }
        return sb.toString();
    }


    /***************************************************************************
     * Zkonvertuje zadaný znak na jeho ekvivalent s kódem menším než 128.
     * Znaky s kódem větším než 127 převede na jejich ASCII ekvivalenty
     * včetně možnosti rozepsat znak na více znaků,
     * (např. (ß) -&gt; (ss), («) -&gt; (&lt;&lt;))
     * nebo na tvar {@code \}{@code uXXXX}.
     * Proto také nemůže vracet pouhý znak, ale musí vracet {@link String}.
     *
     * @param c Konvertovaný znak
     * @return ekvivalent zadaného znaku bez diakritiky
     */
    public static String character(char c)
    {
            if (c < 128) {
                return (Character.toString(c));
            }else if (CONVERSION.containsKey(c)) {
                return CONVERSION.get(c);
            }else {
                return expand(c);
            }
    }




//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================


    /***************************************************************************
     *
     */
    private static String expand(char c) {
        return String.format("\\u%04x", (int)c);
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /** Soukromý konstruktor bránící vytvoření instance. */
    private ToASCII() {}



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
