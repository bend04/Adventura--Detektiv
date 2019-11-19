/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_txt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;



/*******************************************************************************
 * Interfejs {@code INamed} je společným rodičem všech interfejsů
 * implementovaných třídami s pojmenovanými instancemi.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface INamed
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================

    /***************************************************************************
     * Vyhledá v zadaném poli objekt se zadaným názvem a vrátí jej zabalený
     * do objektu typu {@link Optional}{@code <}{@link INamed}{@code >}.
     * Je-li objektů s daným názvem více, vrátí první nalezený.
     * Nenalezne-li jej, vrátí prázdný objekt (<b>NE prázdný odkaz</b>!).
     *
     * @param <E>    Typ prvků prohledávaného pole
     * @param member Název hledaného objektu
     * @param array  Prohledávané pole
     * @return Nalezený objekt zabalený do objektu typu
     *         {@link Optional}{@code <INamed>}
     */
    public static <E extends INamed> Optional<E>
           getO(String member, E[] array)
    {
        Optional<E> result = getO(member, Arrays.stream(array));
        return result;
    }


    /***************************************************************************
     * Vyhledá v kolekci objekt se zadaným názvem a vrátí jej zabalený
     * do objektu typu {@link Optional}{@code <}{@link INamed}{@code >}.
     * Je-li objektů s daným názvem více, vrátí první nalezený.
     * Nenalezne-li jej, vrátí prázdný objekt (<b>NE prázdný odkaz</b>!).
     *
     * @param <E>        Typ prvků prohledávané kolekce
     * @param member     Název hledaného objektu
     * @param collection Prohledávaná kolekce
     * @return Nalezený objekt zabalený do objektu typu
     *         {@link Optional}{@code <}{@link INamed}{@code >}
     */
    public static <E extends INamed> Optional<E>
           getO(String member, Collection<E> collection)
    {
        Optional<E> result = getO(member, collection.stream());
        return result;
    }


    /***************************************************************************
     * Vyhledá v datovodu objekt se zadaným názvem a vrátí jej zabalený
     * do objektu typu {@link Optional}{@code <}{@link INamed}{@code >}.
     * Je-li objektů s daným názvem více, vrátí první nalezený.
     * Nenalezne-li jej, vrátí prázdný objekt (<b>NE prázdný odkaz</b>!).
     *
     * @param <E>    Typ prvků prohledávaného datovodu
     * @param member Název hledaného objektu
     * @param stream Prohledávaný proud
     * @return Nalezený objekt zabalený do objektu typu
     *         {@link Optional}{@code <INamed>}
     */
    public static <E extends INamed> Optional<E>
           getO(String member, Stream<E> stream)
    {
        Optional<E> result =
            stream.filter(iNamed -> iNamed.getName().equalsIgnoreCase(member))
                  .findAny();
        return result;
    }



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí název dané instance.
     *
     * @return Název instance
     */
//    @Override
    public String getName()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================

    /***************************************************************************
     * Vrátí textový podpis dané instance tvořený názvem její mateřské třídy
     * následovaným znakem podtržení a názvem instance.
     *
     * @return Název instance
     */
    default public String toStringWithClass()
    {
        return getClass().getSimpleName() + "_" + getName();
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
