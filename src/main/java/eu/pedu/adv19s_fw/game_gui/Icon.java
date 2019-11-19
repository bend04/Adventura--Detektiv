/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_gui;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.ImageIcon;



/*******************************************************************************
 * Instance třídy {@code Icon} představují obrázky definované tak,
 * aby je bylo možno použít nezávisle na tom, pracuje-li aplikace
 * nad platformou <i>Swing</i> nebo <i>JavaFX</i>.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class Icon
     extends ImageView
  implements javax.swing.Icon
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================

    static {
        new JFXPanel();
    }



//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** */
    private final URL url;

    /** */
    private final ImageIcon swingIcon;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

//    /***************************************************************************
//     * @todo from - Je třeba ještě doplnit komentář
//     */
//    public static Icon from(URL url)
//    {
//        String path = url.toString();
//        Icon   icon;
//        try {
//            icon = new Icon(url);
//        }
//        catch (Exception ex) {
//
//        }
//    }

    /***************************************************************************
     * Vytvoří objekt, který může vystupovat v roli obrázku v obou knihovnách,
     * tj. jak v knihovně swing, tak v knihovně JavaFX.
     *
     * @param url URL adresa souboru s obrázkem
     */
    public Icon(URL url)
    {
        super();
        this.url       = url;
        this.swingIcon = new ImageIcon(url);
        try {
            Image image = new Image(url.toString());
            this.setImage(image);
        }
        catch (Exception ex) {
            System.out.println("   " + ex.getMessage());
        }
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the icon's height.
     *
     * @return an int specifying the fixed height of the icon.
     */
    @Override
    public int getIconHeight()
    {
        return swingIcon.getIconHeight();
    }


    /***************************************************************************
     * Returns the icon's width.
     *
     * @return an int specifying the fixed width of the icon.
     */
    @Override
    public int getIconWidth()
    {
        return swingIcon.getIconWidth();
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Draw the icon at the specified location.
     * Icon implementations may use the {@link Component} argument
     * to get properties useful for painting,
     * e.g. the foreground or background color.
     *
     * @param c  Případná komponenta s pomocnými informacemi
     * @param g  Graphic context
     * @param x  Horizontal coordinate
     * @param y  Veritcal coordinate
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        swingIcon.paintIcon(c, g, x, y);
    }


    @Override
    public String toString()
    {
        return url.toString();
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
