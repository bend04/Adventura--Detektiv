/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;



/*******************************************************************************
 * Instance třídy {@code DBG_Formater} představují formátovače zpráv
 * zaznamenávaných příslušným loggerem.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class DBG_Formater extends Formatter
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
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     *
     */
    public DBG_Formater()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Vrátí zformátovanou obdrženou zprávu,
     * avšak na rozdíl od standardního formátovače k ní nepřidává
     * žádné dodatečné texty (čas, zdroj apod.).
     *
     * @param record Záznam se zprávou
     * @return Výsledný formátovaný text
     */
    @Override
    public String format(LogRecord record)
    {
        return formatMessage(record) + '\n';
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
