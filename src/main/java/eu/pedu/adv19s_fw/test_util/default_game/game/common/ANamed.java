/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.common;

import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.INamed;



/*******************************************************************************
 * Instances of the abstract class {@code ANamed} represent
 * parent sub-objects of named objects,
 * i.e. of instances of classes implementing the {@link INamed} interface.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public abstract class ANamed
           implements INamed
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

    /** Name of this instance. */
    private final String name;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates a parent sub-object of the instance with the given name.
     * The constructor checks that the given name is neither {@code null}
     * nor the empty string, and if the named object is not the game itself,
     * its name does not contain any whiteplaces.
     *
     * @param name Name of the creating instance
     */
    public ANamed(String name)
    {
        if ((name == null)  ||  name.isEmpty()) {
            throw new IllegalArgumentException(
                        "\nThe object name may be neither null "
                      + "nor an empty string");
        }
        if (! (this instanceof IGame)         &&
            ( (! name.equals(name.trim())) ||
              (name.split("\\s").length > 1)  ))
        {
            throw new IllegalArgumentException(
                      "\nNames may not contain any whiteplaces - Entered: «"
                    + name + '»');
        }
        this.name = name;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the name of the instance.
     *
     * @return Name of the instance
     */
    @Override
    public String getName()
    {
        return name;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Returns the textual signature of this instance
     * which is equal to its name.
     *
     * @return Name of the instance
     */
    @Override
    public String toString()
    {
        return name;
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
