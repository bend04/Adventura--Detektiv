/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.game.common;

import eu.pedu.adv19s_fw.game_gui.Icon;
import eu.pedu.adv19s_fw.test_util.default_game.DATA.DataPkg;

import java.net.URL;




/*******************************************************************************
 * Třída {@code Util} je knihovní třídou poskytující užitečné metody.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class Util
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Načte obrázek se zadaným názvem (předpokládá příponu {@code .png})
     * a vytiskne zprávu o úspěšnosti tohoto načtení.
     *
     * @param name Název obrázku, který chceme načíst
     * @return Načtený obrázek nebo {@code null}, pokud takový neexistuje
     */
    public static Icon getImage(String name)
    {
        Icon icon;
        String result;
        URL url = DataPkg.class.getResource(name + ".png");
        if (url == null) {
            icon = null;
            result = "G_NENÍ_";
        }
        else {
            icon = new Icon(url);   //ImageIcon(url);
            result = "Načten";
        }
        System.out.println("   " + result + " obrázek předmětu: " + name);
        return icon;
    }



//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /** Soukromý konstruktor zabraňující vytvoření instance. */
    private Util() {}



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
