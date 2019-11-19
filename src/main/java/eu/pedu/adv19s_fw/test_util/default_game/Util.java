/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game;

import eu.pedu.adv19s_fw.test_util.default_game.DATA.DataPkg;

import java.net.URL;

//import javax.swing.Icon;
//import javax.swing.ImageIcon;
import eu.pedu.adv19s_fw.game_gui.Icon;



/*******************************************************************************
//%L+ CZ
 * {@code Util} Je tovární třída obsahující metodz užitečné pro více tříd.
//%Lx EN
 * {@code Util} represent ...
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class Util
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================

//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

    /***************************************************************************
     * Načte obrázek se zadaným názvem (předpokládá příponu {@code .png})
     * a vytiskne zprávu o úspěšnosti tohoto načtení.
     *
     * @param name Název obrázku, který cheme načíst
     * @return Načtený obrázek nebo {@code null}, pokud takový neexistuje
     */
    public static Icon getImage(String name)
    {
        Icon icon;
        String result;
        URL url = DataPkg.class.getResource(name + ".png");
        if (url == null) {
            icon = null;
            result = "D_NENÍ_";
        }
        else {
            icon = new Icon(url);   //ImageIcon(url);
            result = "Načten";
        }
        System.out.println("   " + result + " obrázek předmětu: " + name);
        return icon;
    }



//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /** Soukromý konstruktor zabraňující vytvoření instance. */
    private Util() {}



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== EMBEDDED TYPES AND INNER CLASSES ==========================================
}
