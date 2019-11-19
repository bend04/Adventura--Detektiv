/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.empty_classes;

import eu.pedu.adv19s_fw.scenario.AScenarioManagerG;
import eu.pedu.adv19s_fw.game_gui.IGSMFactoryG;
import eu.pedu.adv19s_fw.game_gui.IGameG;
import eu.pedu.adv19s_fw.game_gui.IUIG;
import eu.pedu.adv19s_fw.game_txt.IGSMFactory;



/*******************************************************************************
 * Instance třídy {@code EmptyGSMFactory} představují tovární objekty,
 * které jsou schopny na požádání dodat instanci hry,
 * instanci správce scénářů této hry
 * a instanci grafického uživatelského rozhraní.
 * Dokud ještě některé z těchto objektů nejsou definovány,
 * vyhazují metody výjimku
 * {@link eu.pedu.adv19s_fw.utilities.UncompletedMethodException}.
 * <p>
 * <b>Tovární třídy musí povinně definovat veřejný bezparametrický
 * konstruktor svých instancí</b>.
 * <p>
 * V první fázi vývoje celé aplikace bývá aktivní pouze metoda
 * {@link #getScenarioManager()} umožňující získání instance správce scénářů
 * vyvíjené hry a těla zbylých metod bývají zakomentovaná.
 * Posléze po odkomentování zakomentovaných těl metod lze postupně získat
 * také vlastní textové i grafické verze hry
 * a jejího textového, resp. grafického uživatelského rozhraní.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class EmptyGSMFactoryG
  implements IGSMFactoryG, IAuthorPrototypeG
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
     * Povinný konstruktor – vytvoří tovární objekt
     * poskytující klíčové objekty aplikace podporující GUI.
     */
    public EmptyGSMFactoryG()
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí odkaz na instanci správce scénářů hry podporující GUI.
     *
     * @return Požadovaný odkaz
     */
//    @Override
//    public AScenarioManagerG getScenarioManager()
//    {
//        return ScenarioManager.getInstance();
//    }


    /***************************************************************************
     * Vrátí odkaz na instanci verze hry podporující GUI.
     *
     * @return Požadovaný odkaz
     */
//    @Override
//    public IGameG getGame()
//    {
//        return GameClass.getInstance();
//    }


    /***************************************************************************
     * Vrátí odkaz na instanci třídy realizující grafické uživatelské rozhraní.
     *
     * @return Požadovaný odkaz
     */
//    @Override
//    public IUIG getUI()
//    {
//        return TextUI_Instance;
//    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
