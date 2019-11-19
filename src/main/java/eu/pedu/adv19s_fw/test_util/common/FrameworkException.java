/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.common;



/*******************************************************************************
 * Instance třídy {@code TestException} představují výjimky vyhazované
 * při odhalení nesrovnalostí zapříčiněných chybou ve frameworku.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
@SuppressWarnings("serial")
public class FrameworkException extends TestException
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
     * Vytvoří novou výjimku bez explicitně zadané zprávy a příčiny (cause).
     * Příčinu lze dodatečně zadat zavoláním metody {@link #initCause}.
     */
    public FrameworkException()
    {
        this("");
    }


    /***************************************************************************
     * Vytvoří novou výjimku se zadanou detailní zprávou,
     * avšak bez zadané příčiny (cause).
     * Příčinu lze dodatečně zadat zavoláním metody {@link #initCause}.
     *
     * @param message Detailní zpráva, kterou lze následně získat zavoláním
     *                metody {@link #getMessage()}.
     */
    public FrameworkException(String message) {
        this(message, null);
    }


    /***************************************************************************
     * Vytvoří novou s explicitně zadanou příčinou a detailní zprávou
     * zadanou podle vzorce {@code (příčina==null ? null : příčina.toString())}.
     * Tento konstruktor je vhodný pro výjimky, které jsou ve skutečnosti
     * pouhými obaly (wrappers) výjimky <tt>příčina</tt>.
     *
     * @param příčina Výjimka, která typicky způsobila vyhození dané výjimky
     *                a kterou lze později získat voláním {@link #getCause()}.
     *                Hodnota {@code null} je povolena a indikuje, že příčinu
     *                vyhození dané výjimky neznáme a nebo dokonce není.
     */
    public FrameworkException(Throwable příčina)
    {
        this("", příčina);
    }


    /***************************************************************************
     * Vytvoří novou výjimku s explicitně zadanou zprávou i příčinou.
     *
     * @param message  Detailní zpráva, kterou lze následně získat zavoláním
     *                 metody {@link #getMessage()}.
     * @param příčina Výjimka, která typicky způsobila vyození dané výjimky
     *                a kterou lze později získat voláním {@link #getCause()}.
     *                Hodnota {@code null} je povolena a indikuje, že příčinu
     *                vyhození dané výjimky neznáme a nebo dokonce není.
     */
    public FrameworkException(String message, Throwable příčina) {
        super("\nCHYBA FRAMEWORKU:\n" + message, příčina);
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
