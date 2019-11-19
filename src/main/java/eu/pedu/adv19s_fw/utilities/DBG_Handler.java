/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;

import java.io.OutputStream;
import java.util.logging.StreamHandler;



/*******************************************************************************
 * Instance třídy {@code DBG_Handler} představují ovladače (handlery)
 * zpracovávající výstup z logovacích objektů, tj. potomků třídy
 * {@link java.util.logging.Logger}.
 * Tyto instance umožňují nastavit výstupní datový proud,
 * do nějž budou všechny výstupy směrovány, přičemž tento proud může být
 * i násobný, tj. výstupy půjdou do několika výstupních proudů současně.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class DBG_Handler extends StreamHandler
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    //Výstupní proud, kam bude logger zapisovat. */
    private static OutputStream outputStream = System.out;



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Nastaví implicitní výstupní proud, který bude od této chvíle
     * nastavován všem instancím.
     * Dříve vytvořené instance nové nastavení neovlivní.
     *
     * @param output Nastavovaný výstupní proud
     */
    public static void setDefaultOutputStream(OutputStream output)
    {
        outputStream = output;
    }



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
    public DBG_Handler()
    {
        super(outputStream, new DBG_Formater());
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
