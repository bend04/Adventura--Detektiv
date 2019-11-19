/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_gui;

import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.scenario.AScenarioManagerG;
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
public interface IGSMFactoryG
         extends IGSMFactory
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================

    /***************************************************************************
     * Returns the instance of the given factory class with a nullary
     * (=&nbsp;parameterless) constructor.
     *
     * @param <T>           Factory class type
     * @param factoryClass  Factory class class-object
     * @return Factory object
     * @throws IllegalArgumentException Creation of the instance failed
     */
    public static <T extends IGSMFactoryG>
           T getInstanceOfFactory(Class<T> factoryClass)
    {
        T result;
        try {
            result = factoryClass.newInstance();
        }
        catch (InstantiationException | IllegalAccessException ex) {
            throw new IllegalArgumentException(
                "\nFailed creation of instance of the " + factoryClass, ex);
        }
        return result;
    }



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================
//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================

    /***************************************************************************
     * Vrátí odkaz na instance správce scénářů.
     *
     * @return Požadovaný odkaz
     * @throws UncompletedMethodException Metoda ještě není plnohodnotná
     */
    @Override
    public default AScenarioManagerG getScenarioManager()
    {
        throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Vrátí odkaz na (jedinou) instanci textové verze hry;
     * dokud ještě hra neexistuje, vyhazuje po zavolání výjimku
     * {@link UncompletedMethodException}.
     *
     * @return Požadovaný odkaz
     * @throws UncompletedMethodException Metoda ještě není plnohodnotná
     */
    @Override
    public default IGameG getGame()
    {
        throw new UncompletedMethodException();
    }


    /***************************************************************************
     * Vrátí odkaz na instanci třídy realizující uživatelské rozhraní
     * textové verze hry;
     * dokud tato třída neexistuje, vyhazuje po zavolání výjimku
     * {@link UncompletedMethodException}.
     *
     * @return Požadovaný odkaz
     * @throws UncompletedMethodException Metoda ještě není plnohodnotná
     */
    @Override
    public default IUIG getUI()
    {
        throw new UncompletedMethodException();
    }



//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
