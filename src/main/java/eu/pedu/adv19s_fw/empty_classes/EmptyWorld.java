/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.empty_classes;

import java.util.Collection;
import eu.pedu.adv19s_fw.game_txt.IWorld;
import eu.pedu.adv19s_fw.utilities.UncompletedMethodException;
import eu.pedu.adv19s_fw.game_txt.IPlace;



/*******************************************************************************
 * Instance třídy {@code EmptyWorld} reprezentuje svět hry.
 * V dané hře musí být definována jako jedináček.
 * Má na starosti uspořádání jednotlivých prostorů a udržuje informaci o tom,
 * ve kterém z nich se hráč právě nachází.
 * Vzájemné uspořádání prostorů se může v průběhu hry měnit –
 * prostory mohou v průběhu hry získávat a ztrácet sousedy.
 * <p>
 * V této hře je světem hry ...
 * a jednotlivé prostory jsou ....
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public   class EmptyWorld
    implements IWorld
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Odkaz na jedinou instanci (jedináčka) této hry. */
    private static final EmptyWorld SINGLETON = new EmptyWorld();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Tovární metoda vracející odkaz na jedninou existující instanci dané hry.
     *
     * @return Instance dané hry
     */
    static EmptyWorld getInstance()
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
     * Soukromý konstruktor definující jedinou instanci.
     * Protože je soukromý, musí být definován, i když má prázdné tělo.
     */
    private EmptyWorld()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí kolekci odkazů na všechny prostory vystupující ve hře.
     *
     * @return Kolekce odkazů na všechny prostory vystupující ve hře
     */
    @Override
    public Collection<? extends IPlace> getAllPlaces()
    {
        //TODO EmptyGame.getAllPlaces - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Vrátí odkaz na aktuální prostor,
     * tj. na prostor, v němž se hráč pravé nachází.
     *
     * @return Prostor, v němž se hráč pravé nachází
     */
    @Override
    public IPlace getCurrentPlace()
    {
        //TODO EmptyGame.getCurrentPlace - Metoda ještě není hotova
        throw new UncompletedMethodException();
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
