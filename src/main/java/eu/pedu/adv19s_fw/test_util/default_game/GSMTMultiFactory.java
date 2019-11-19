/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game;

import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IUI;
import eu.pedu.adv19s_fw.scenario.AScenarioManager;
import eu.pedu.adv19s_fw.test_util.default_game.game.version_t.GameT_Icebox;
import eu.pedu.adv19s_fw.test_util.default_game.ui_tx.UIB_Console;

import java.util.function.Supplier;



/*******************************************************************************
 * Instance třídy {@code GSMTMultiFactory} představují tovární objekty,
 * které jsou schopny na požádání dodat instanci hry,
 * instanci správce scénářů této hry
 * a instanci textového uživatelského rozhraní.
 * Dokud ještě některé z těchto objektů nejsou definovány,
 * vyhazují příslušné metody výjimku
 * {@link eu.pedu.adv19s_fw.utilities.UncompletedMethodException}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class GSMTMultiFactory
  implements IGSMFactory, IAuthorDemo
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Tovární objekt poskytující instanci správce scénářů
     *  zadatelného z programu. */
    private static Supplier<AScenarioManager> SM_Supplier = null;



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Nastaví metodu, která bude vracet instanci správce scénářů.
     * Metoda není vláknově zabezpečená,
     * protože nepředpokládá volání z více vláken.
     *
     * @param supplier Metoda vracející zadaného správce scénářů
     */
    public static void setScenarioManager(Supplier<AScenarioManager> supplier)
    {
        if (SM_Supplier != null) {
            throw new RuntimeException(
                "\nPokus o opakované nastavení poskytovatele správce scénářů");
        }
        SM_Supplier = supplier;
    }


//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří tovární objekt poskytující klíčové objekty aplikace.
     */
    public GSMTMultiFactory()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which also the mother class of this instance is.
     *
     * @return The class-object of the factory class
     */
    @Override
    public Class<GSMTMultiFactory> getFactoryClass()
    {
        return GSMTMultiFactory.class;
    }


    /***************************************************************************
     * Vrátí odkaz na instanci správce scénářů.
     *
     * @return Požadovaný odkaz
     */
    @Override
    public AScenarioManager getScenarioManager()
    {
        if (SM_Supplier == null) {
            throw new RuntimeException(
                "\nNebyl zadán podkytovatel správce scénářů");
        }
        return SM_Supplier.get();
    }


    /***************************************************************************
     * Vrátí odkaz na instanci textové verze hry.
     *
     * @return Požadovaný odkaz
     */
    @Override
    public GameT_Icebox getGame()
    {
        return GameT_Icebox.getInstance();
    }


    /***************************************************************************
     * Vrátí odkaz na instanci třídy realizující uživatelské rozhraní.
     *
     * @return Požadovaný odkaz
     */
    @Override
    public IUI getUI()
    {
//        return new UIA_JOptionPane();
        return new UIB_Console();
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
