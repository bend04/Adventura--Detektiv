/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;

import eu.pedu.adv19s_fw.game_txt.INamed;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;



/*******************************************************************************
 * Třída {@code Util} je knihovní třídou s užitečnými metodami.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class Util
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Komparátor stringů neberoucí v úvahu velikost písmen. */
    public static final Comparator<String> CIC = String::compareToIgnoreCase;

    /** Prázdné pole stringů pro předání parametru metodě
     *  {@link List#toArray() toArray(T[])}. */
    public static final String[] EMPTY_STRING_ARR = new String[0];

    /** Uvozovky či jiný znak, kterým označujeme začátek vypisovaného řetězce,
     *  aby byly vidět i případné úvodní mezery. */
    public static final String A_ = "«";

    /** Uvozovky či jiný znak, kterým označujeme konec vypisovaného řetězce,
     *  aby byly vidět i případné závěrečné mezery. */
    public static final String _Z = "»";



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Převede zadaný vektor na řetězec znaků přičemž
     * každou z hodnot uzavře do francouzských uvozovek
     * jednotlivé takto převedené hodnoty oddělí čárkami
     * a celý vektor uzavře do kulatých závorek
     *
     * @param arr   Pole, jehož hodnoty chceme vypsat
     * @return      Požadovaný řetězec
     */
    public static String objArr2String(Object[] arr)
    {
        if (arr == null) {
            return "### Byl zadán prázdný odkaz null ###";
        }
        String result = Arrays.stream(arr)
            .map(s -> A_ + s + _Z)
            .collect(Collectors.joining(", ", "(", ")"));
        return result;
    }


    /***************************************************************************
     * Zadanou kolekci objektů typu {@link INamed}
     * převede na jednorozměrné pole řetězců.
     *
     * @param collection  Převáděná kolekce pojmenovaných
     * @return Požadované pole stringů
     */
    public static String[] colINamed2StringArr(
                           Collection<? extends INamed> collection)
    {
        if (collection == null) {
            return new String[0];
        }
        String[] result = collection.stream()
            .map(INamed::getName)
            .collect(Collectors.toList())
            .toArray(EMPTY_STRING_ARR);
        return result;
    }


    /***************************************************************************
     * Převede kolekci pojmenovaných objektů
     * na seřazenou množinu jejich názvů.
     * Zdvojené objekty z původní kolekce se v nové kolekci objeví jen jednou.
     *
     * @param iNamed Převáděná kolekce
     * @return Seřazená množina jejich názvů
     */
//    public static SortedSet<String> colINamed2sortedSetString
//                                    (Collection<? extends INamed> iNamed)
//    {
//        SortedSet<String> tsString = new TreeSet<>();
//        iNamed.stream()
//              .forEach(named -> tsString.add(named.getName()));
//        return tsString;
//    }


    /***************************************************************************
     * Vrátí řetězec s textovým podpisem zadané kolekce.
     *
     * @param collection Podepisující se kolekce
     * @return Požadovaný podpis
     */
    public static String colINamedToString(
                         Collection<? extends INamed> collection)
    {
        String result = collection.stream()
            .map(named -> A_ + named.getName() + _Z)
            .collect(Collectors.joining(", ", "(", ")"));
        return result;
    }


    /***************************************************************************
     * Vrátí informaci o tom, zda zadané pole obsahuje zadaný řetězec.
     *
     * @param member Hledaný řetězec
     * @param array  Prohledávané pole
     * @return  Výsledek hledání: {@code true}=našel, {@code false}=nenašel
     */
//    public static boolean  containsIgnoreCase(String member, String[] array)
//    {
//        if (member == null) {
//            throw new TestException(
//                "\nHledaný řetězec nesmí být zadán jako null");
//        }
//        boolean result = Arrays.stream(array)
//            .anyMatch(string -> member.equalsIgnoreCase(string));
//        return result;
//    }


    /***************************************************************************
     * Vrátí informaci o tom, zda se obsah většího z vektorů liší od menšího
     * opravdu pouze o zadaný řetězec.
     *
     * @param member  Řetězec, v němž se mají oba vektory odlišovat
     * @param bigger  Větší z obou řetězců
     * @param smaller Menší z obou řetězců
     * @return  Je-li předpoklad pravdivý, vrátí {@code true},
     *          jinak vrátí {@code false}
     */
//    public static boolean  differOnlyIn(String member,
//                                        String[] bigger, String[] smaller)
//    {
//        if ((bigger.length - smaller.length)  !=  1)  {
//            return false;
//        }
//        member = member.toLowerCase();
//        List<String> bigList   = toLowerCaseList(bigger);
//        List<String> smallList = toLowerCaseList(smaller);
//
//        for (String string : smallList) {
//            if (! bigList.remove(string)) {
//                return false;
//            }
//        }
//        if (bigList.remove(member)  &&  bigList.isEmpty()) {
//            return true;
//        }
//        return false;
//    }


//    /***************************************************************************
//     * Převede zadaný vektor na řetězec znaků přičemž
//     * každou z hodnot uzavře do francouzských uvozovek,
//     * jednotlivé takto převedené hodnoty oddělí čárkami
//     * a celý vektor uzavře do kulatých závorek
//     *
//     * @param arr   Pole, jehož hodnoty chceme vypsat
//     * @return      Požadovaný řetězec
//     */
//    public static String intArr2String(int[] arr)
//    {
//        if (arr == null) {
//            return "### Byl zadán prázdný odkaz null ###";
//        }
//        String result = Arrays.stream(arr)
//            .mapToObj(s -> A_ + s + _Z)
//            .collect(Collectors.joining(", ", "(", ")"));
//        return result;
//    }


    /***************************************************************************
     * Vrátí instanci představující cestu k souboru se zadaným názvem
     * umístěným v kořenové složce projektu NetBeans obsahujícího zadanou třídu.
     * Není-li kořenový balíček ve složce {@code <něco>/build/classes},
     * vrátí prázdný odkaz {@code null}.
     *
     * @param clazz Třídu do jejíhož projektu se má soubor umístit
     * @param fileName Název umisťovaného souboru
     * @return Požadovaný soubor
     */
//    public static File newFileInPRJ(Class<?> clazz, String fileName)
//    {
//        URL url = clazz.getResource("bluej.pkg");
//        if (url == null) { return null; }           //==========>
//        URI uri;
//        try {
//            uri = url.toURI();
//        }
//        catch(URISyntaxException ex) {
//            throw new TestException(
//                    "\nNepodařilo se převést URL na URI - URL=" + url, ex);
//        }
//        File file = new File(uri);
//        String path = file.getAbsolutePath();
//        int    index = path.indexOf("build\\classes\\");
//        if (index < 0) { return null; }             //==========>
//        path = path.substring(0, index);
//        file = new File(path, fileName);
//        return file;
//    }


    /***************************************************************************
     * Převede zadané pole řetězců na seznam těchže řetězců
     * převedených na malá písmena.
     *
     * @param array Převáděné pole
     * @return Výsledný seznam
     */
//    public static List<String> toLowerCaseList(String[] array)
//    {
//        List<String> list = new ArrayList<String>(array.length);
//        for (String string : array) {
//            list.add(string.toLowerCase());
//        }
//        return list;
//    }


    /***************************************************************************
     * Vrátí informaci o tom, jsou-li zadané dva pole řetězců shodné
     * neuvažujeme-li velikost písmen, tj. jsou-li obě pole shodně velká a
     * jsou-li příslušně shodné jejich vzájemně si odpovídající položky.
     *
     * @param arr1 První pole
     * @param arr2 Druhý pole
     * @return Požadovaná informace
     */
//    public static boolean strArrEqualsIgnoreCase(String[] arr1, String[] arr2)
//    {
//        if (arr1.length != arr2.length) {
//            return false;
//        }
//
//        for (int i=0;   i < arr1.length;   i++) {
//            if (! arr1[i].equalsIgnoreCase(arr2[i])) {
//                return false;
//            }
//        }
//        return true;
//    }




//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /** */
//    private
    protected
    Util() {}



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
