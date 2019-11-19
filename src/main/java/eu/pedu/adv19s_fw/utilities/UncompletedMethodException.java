/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;

import eu.pedu.adv19s_fw.utilities.CallerReporter.Level;



/*******************************************************************************
 * Instances of the {@code UncompletedMethodException} class are thrown
 * to indicate that the requested method is not yet completed.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
@SuppressWarnings("serial")
public class UncompletedMethodException extends RuntimeException
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
     * Constructs an {@code UncompletedException} with no detail message.
     */
    public UncompletedMethodException()
    {
        this("", true);
    }


    /***************************************************************************
     * Constructs an {@code UncompletedException}
     * with the specified detail trailing message.
     *
     * @param message The detail message put at the end of the announcement
     *                of the method creating this exception
     */
    public UncompletedMethodException(String message)
    {
        this(message, (message == null) || message.isEmpty());
    }


    /***************************************************************************
     * Constructs an {@code UncompletedException}
     * with the specified detail trailing message.
     *
     * @param message The detail message put at the end of the announcement
     *                of the method creating this exception
     * @param withoutMessage If the empty message is set
     */
    private UncompletedMethodException(String message, boolean withoutMessage)
    {
        super("\nByla volána metoda, která ještě není plnohodnotně definována: "
              + CallerReporter.getCallerName(Level.CLASS, 2)
              + (withoutMessage  ?  ""  :  ('\n' + message)));
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
