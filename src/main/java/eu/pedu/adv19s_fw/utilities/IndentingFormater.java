/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;



/*******************************************************************************
 * Knihovní třída {@code IndentingFormater} poskytuje metody
 * pro tisk využívající vhodné odsazování.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class IndentingFormater
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Mezery, z nichž se vybírá podřetězec vkládaný jako odsazení. */
    private static final String PLACES =
    "                                                                        ";



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Metoda vrátí řetězec konstruovaný tak,
     * že za text obdržený v prvním parametru přidá text z druhého parametru,
     * avšak každé odřádkování tohoto textu upraví tak,
     * že začátek následujícího řádku začíná pod začátkem předchozího řádku
     * tohoto textu.
     *
     * @param title Úvodní text jehož délka definuje odsazení
     *              pokračovacích řádků dalšího parametru
     * @param text  Text odsazovaný podle délky předchozího parametru
     * @return Výsledný textový řetězec
     */
    public static String indent(String title, String text)
    {
        StringBuilder sb     = new StringBuilder(title);
        String        indent = PLACES.substring(0, title.length());
        final int     limit  = text.length();
        for (int i = 0;   i < limit;   i++) {
            char c = text.charAt(i);
            sb.append(c);
            if (c == '\n') {
                sb.append(indent);
            }
        }
        return sb.toString();
    }





//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Soukromý konstruktor zabraňující vytvoření instance.
     */
    private IndentingFormater()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
