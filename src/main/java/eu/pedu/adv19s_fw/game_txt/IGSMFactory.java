/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_txt;

import eu.pedu.adv19s_fw.scenario.AScenarioManager;
import eu.pedu.adv19s_fw.utilities.UncompletedMethodException;



/*******************************************************************************
*  Instance interfejsu {@code IGSMFactory} představují tovární objekty,
 * které jsou schopny na požádání dodat instance klíčových objektů aplikace,
 * konkrétně aktuální hry, jejího správce scénářů a uživatelského rozhraní.
 * Název interfejsu je odvozen z (Game + ScenarioManager Factory).
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IGSMFactory
         extends IAuthor
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================

    /***************************************************************************
     * Vrátí odkaz na instanci zadané tovární třídy. Předpokládá přitom,
     * že tato třída má dostupný nulární (= bezparametrický) konstruktor.
     *
     * @param <T>           Typ tovární třídy
     * @param factoryClass  Class-objekt tovární třídy
     * @return Požadovaný odkaz
     * @throws IllegalArgumentException Instanci zadané třídy
     *                                  se nepodařilo vytvořit
     */
    public static <T extends IGSMFactory>
           T getInstanceOfFactory(Class<T> factoryClass)
    {
        T result;
        try {
            result = factoryClass.newInstance();
        }
        catch (InstantiationException | IllegalAccessException ex) {
            throw new IllegalArgumentException(
                "\nNepodařilo se vytvořit instanci třídy " + factoryClass, ex);
        }
        return result;
    }



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================
//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================

    /***************************************************************************
     * Vrátí odkaz na (jedinou) instanci správce scénářů.
     * Dokud potomek metodu nepřebije, metoda vyhazuje výjimku
     * {@link UncompletedMethodException}.
     *
     * @return Požadovaný odkaz
     * @throws UncompletedMethodException Potomek metodu korektně nepřebil
     */
//    @Override
    public default AScenarioManager getScenarioManager()
    {
        throw new UncompletedMethodException("Není korektně definována "
                + "tovární metoda vracející správce scénářů");
    }


    /***************************************************************************
     * Vrátí odkaz na (jedinou) instanci textové verze hry;
     * dokud ještě hra neexistuje, vyhazuje po zavolání výjimku
     * {@link UncompletedMethodException}.
     *
     * @return Požadovaný odkaz
     * @throws UncompletedMethodException Potomek metodu korektně nepřebil
     */
//    @Override
    public default IGame getGame()
    {
        throw new UncompletedMethodException("Není korektně definována "
                + "tovární metoda vracející instanci hry");
    }


    /***************************************************************************
     * Vrátí odkaz na instanci třídy realizující uživatelské rozhraní
     * textové verze hry;
     * dokud tato třída neexistuje, vyhazuje po zavolání výjimku
     * {@link UncompletedMethodException}.
     *
     * @return Požadovaný odkaz
     * @throws UncompletedMethodException Potomek metodu korektně nepřebil
     */
//    @Override
    public default IUI getUI()
    {
        throw new UncompletedMethodException( "Není korektně definována "
                + "tovární metoda vracející uživatelské rozhraní");
    }



//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
