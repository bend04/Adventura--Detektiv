/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */
package cz.vse.java.adventura;

import cz.vse.java.adventura.game.BenDetektivGame;

import cz.vse.java.adventura.game.DetScenarioManagerCon;
import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IUI;
import eu.pedu.adv19s_fw.scenario.AScenarioManager;
import cz.vse.java.adventura.textui.*;



/*******************************************************************************
 * Instance třídy {@code EmptyGSMFactory} představují tovární objekty,
 * které jsou schopny na požádání dodat instanci hry,
 * instanci správce scénářů této hry
 * a instanci textového uživatelského rozhraní.
 * Dokud ještě některé z těchto objektů nejsou definovány,
 * vyhazují metody výjimku
 * {@link eu.pedu.adv19s_fw.utilities.UncompletedMethodException}.
 * <p>
 * <b>Tovární třídy musí povinně definovat veřejný bezparametrický
 * konstruktor svých instancí</b>.
 * <p>
 * V první fázi vývoje celé aplikace bývá aktivní pouze metoda
 * {@link #getScenarioManager()} umož�?ující získání instance správce scénářů
 * vyvíjené hry a těla zbylých metod bývají zakomentovaná.
 * Posléze po odkomentování zakomentovaných těl metod lze postupně získat
 * také vlastní textové i grafické verze hry
 * a jejího textového, resp. grafického uživatelského rozhraní.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public   class DetGSMFactory
    implements IGSMFactory, IAuthorDB
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
     * poskytující klíčové objekty aplikace.
     * @param game
     */
    public DetGSMFactory(BenDetektivGame game)
    {
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí odkaz na instanci správce scénářů.
     *
     * @return Požadovaný odkaz
     */
    @Override
    public AScenarioManager getScenarioManager()
    {
        return DetScenarioManagerCon.getInstance();
    }


    /***************************************************************************
     * Vrátí odkaz na instanci textové verze hry.
     *
     * @return Požadovaný odkaz
     */
    @Override
     public BenDetektivGame getGame()
    {
       return BenDetektivGame.getInstance();
   }


    /***************************************************************************
     * Vrátí odkaz na instanci třídy realizující uživatelské rozhraní.
     *
     * @return Požadovaný odkaz
     */
    @Override
    public IUI getUI()
    {
        IUI ui =
 //                new UIA_JOptionPane();
                new Start();
//                 new UIC_GamePlayer(new UIC_GamePlayer.ByJOptionPane());
//                 new UIC_GamePlayer(new UIC_GamePlayer.ByScanner());
//                 new UID_Multiplayer(new UID_Multiplayer.ByJOptionPane());
//                 new UID_Multiplayer(new UID_Multiplayer.ByScanner());
        return ui;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
