/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game;

import eu.pedu.adv19s_fw.game_gui.IGSMFactoryProductG;
import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IGSMFactoryProduct;
import java.awt.Point;



/*******************************************************************************
 * {@code IAuthorDemo} instances know to return on request
 * the name and identification string of the author of its class
 * and the class-object of the factory class;
 * these values are saved in their static constants
 * {@link #AUTHOR_NAME}, {@link #AUTHOR_ID} and {@link #FACTORY_CLASS}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IAuthorDemo
         extends IGSMFactoryProduct
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================

    /** Pozice aplikačního okna připravená práci na počítači s více monitory. */
    Point POSITION_0 = new Point(0, 0);

    /** The name of the author in format <b>SURNAME First-name</b>,
     * i.e. firstly the surname in capital letters followed by first name,
     * with only the first capital letter, other letters will be small.
     * If the program author has more first names, he can quote all of them. */
    String AUTHOR_NAME = "PECINOVSKÝ Rudolf";

    /** Author's identification string written in CAPITAL LETTERS.
     *  It is mostly the login into the school information system. */
    String AUTHOR_ID = "DEMO";

    /** Factory class the instances of which are factory objects providing
     *  the instance of the game, its associated scenario manager
     *  and the textual user interface created by this author. */
    Class<? extends IGSMFactory> FACTORY_CLASS = IGSMFactory.class;



//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the program author name in format <b>SURNAME First-name</b>,
     * i.e. firstly the surname in capital letters followed by first name,
     * with only the first capital letter, other letters will be small.
     * If the program author has more first names, he can quote all of them.
     *
     * @return Program author name in format SURNAME First-name
     */
    @Override
    default public String getAuthorName()
    {
        return AUTHOR_NAME;
    }


    /***************************************************************************
     * Returns the program author identification string
     * written in CAPITAL LETTERS.
     * It is mostly the login into the school information system.
     *
     * @return Identification string (login) of the program author
     */
    @Override
    default public String getAuthorID()
    {
        return AUTHOR_ID;
    }

//
//    /***************************************************************************
//     * Returns the class-object of the factory class, the instances of which
//     * can mediate receiving of all key objects of the application,
//     * the part of which also the mother class of this instance is.
//     *
//     * @return The class-object of the factory class
//     */
//    @Override
//    default public Class<DemoGSMFactoryG> getFactoryClass()
//    {
//        return FACTORY_CLASS;
//    }
//


//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
