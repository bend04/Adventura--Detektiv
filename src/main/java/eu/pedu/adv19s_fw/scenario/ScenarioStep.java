
/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.scenario;

import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.utilities.Util;

import java.util.Arrays;
import java.util.Objects;

import static eu.pedu.adv19s_fw.utilities.ConditionalPrinter.*;
import static eu.pedu.adv19s_fw.utilities.FormatStrings.*;
import static eu.pedu.adv19s_fw.utilities.Util.*;



/*******************************************************************************
 * Třída {@code ScenarioStep} slouží jako přepravka k uchovávání informaci
 * o jednotlivých zadávaných příkazech scénáře
 * a o očekávaných stavech programu po jejich provedení.
 * <p>
 * Kroky scénáře obsahují informace sloužící k ověření
 * správné reakce hry na příkaz zadávaný v příslušném kroku scénáře.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class ScenarioStep
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Zpráva v kroku scénáře, u níž se nebude testovat shoda
     *  se zprávou vrácenou hrou v reakci na zadaný příkaz.  */
    public static final String IGNORED_MESSAGE = "¤¤¤ TEST ¤¤¤";

    /** Fiktivní předstartovní krok používaný některými testy. */
    public static final ScenarioStep NOT_START_STEP;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Index posledního vytvořeného kroku scénáře -
     *  z něj se odvozuje index příštího kroku. */
    private static int lastIndex = 0;



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================

    static {
        String[] EMPTY_STRING_ARRAY = {};
        String   TEXT = "NOT_START_STEP";
        NOT_START_STEP = new ScenarioStep(-1, TypeOfStep.tsNOT_START,
                         TEXT, TEXT, TEXT,
                         EMPTY_STRING_ARR, EMPTY_STRING_ARR, EMPTY_STRING_ARR);
    }



//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================

    /***************************************************************************
     * Vrátí hodnotu, na níž bude navazovat index následně vytvořeného kroku.
     *
     * @return Požadovaná hodnota
     */
    public static int getLastIndex()
    {
        return lastIndex;
    }


    /***************************************************************************
     * Nastaví index příštího kroku a vrátí
     * index posledního vytvořeného kroku.
     *
     * @param next Index příštího kroku
     * @return Index posledního vytvořeného kroku
     */
    public static int setIndex(int next)
    {
        int   ret = lastIndex;
        lastIndex = next - 1;
        return ret;
    }


    /***************************************************************************
     * Vynuluje počítaný index kroků testu =&gt;
     * nebude-li zadáno jinak, bude příští vytvořený krok označen jako první.
     */
    public static void clearIndex()
    {
        lastIndex = 0;
    }



//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================

    /***************************************************************************
     * Vrátí textový podpis kroku se zadanými parametry.
     *
     * @param index      Číslo, které by mělo určovat pořadí daného kroku
     *                   v rámci jeho scénáře
     * @param typeOfStep O který typ kroku se jedná
     * @param prologue   Text, kterým se uvede celý výpis
     * @param command    Zadaný příkaz
     * @param message    Zprava vydaná v reakci na zadaný příkaz
     * @param place      Název prostoru, v němž bude hráč vykonání příkazu
     * @param neighbors  Názvy prostorů, kam se je odsud možno přesunout
     * @param items      Názvy objektů v aktuálním prostoru
     * @param bag        Názvy objektů v batohu
     * @return  Textový podpis kroku se zadanými parametry
     */
    private static String toString(int index, TypeOfStep typeOfStep,
            String   prologue,  String command, String message,
            String   place,
            String[] neighbors,  String[] items,  String[] bag)
    {
        return  N_LINE_N + prologue +
                "\n" + index + ". krok" +
                "\nTyp kroku: " + typeOfStep +
                "\nPříkaz:    " + command +
                "\nProstor:   " + place +
                "\nVýchody:   " + objArr2String(neighbors) +
                "\nPředměty:  " + objArr2String(items ) +
                "\nBatoh:     " + objArr2String(bag     ) +
                N_LINE +
                "\nMessage:"     +
                N_CIRCUMFLEXES_N +
                message   +
                N_LINE;
    }


    /***************************************************************************
     * Vrátí dvojznakový řetězec představující zadané jedno- až dvojmístné
     * číslo; je-li zadané číslo menši nez 10, vloží před číslici mezeru.
     * Nekontroluje, zda je číslo nezáporné a menši než 100.
     *
     * @param  i Číslo převáděné na řetězec
     * @return Požadovaný řetězec
     */
    private static String twoDigits(int i)
    {
        return ((i > 9) ? "" : " ") + i;
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Index daného kroku scénáře - typicky jeho pořadí v rámci scénáře. */
    public final int index;

    /** Typ daného kroku scénáře. */
    public final TypeOfStep typeOfStep;

    /** Zadávaný příkaz. */
    public final String command;

    /** Zpráva vydaná v reakci na zadaný příkaz;
     *  obsahuje-li {@code null}, nepočítá se s ověřováním reakce hry. */
    public final String message;

    /** Prostor, v němž se bude hráč nacházet po vykonaní příkazu. */
    public final String place;

    /** Názvy prostorů, které bezprostředně sousedí s prostorem,
     *  v němž se bude hráč nacházet po vykonaní příkazu,
     *  a které jsou proto z tohoto prostoru přímo dostupné. */
    public final String[] neighbors;

    /** Názvy objektů aktuálně se nacházejících v prostoru,
     *  v němž se bude hráč nacházet po vykonaní příkazu. */
    public final String[] items;

    /** Názvy objektů v batohu po vykonání příkazu. */
    public final String[] bag;

    /** Příznak toho, že daný krok je posledním krokem scénáře. */
    public final boolean theLast;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Příčina vyhození výjimky při konstrukci kroku scénáře. */
    private String errorMsg;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří instanci založenou na aktuálním stavu zadané hry.
     * Index tohoto kroku bude o jedničku větší než index kroku vytvořeného
     * před ním, přesněji než poslední zapamatovaný index.
     *
     * @param command  Zadaný příkaz
     * @param message  Zprava vydaná v reakci na zadaný příkaz
     * @param game     Hra, z jejíhož aktuálního stavu se vytváří krok
     */
    public ScenarioStep(String command,  String message,  IGame game)
    {
        this(++lastIndex, TypeOfStep.tsNOT_SET, command, message,
             game.getWorld().getCurrentPlace().getName(),
             Util.colINamed2StringArr(
                     game.getWorld().getCurrentPlace().getNeighbors()),
             Util.colINamed2StringArr(
                     game.getWorld().getCurrentPlace().getItems()),
             Util.colINamed2StringArr(game.getBag().getItems()));
    }


    /***************************************************************************
     * Vytvoří krok umožňující otestování správné funkce hry.
     * Index tohoto kroku bude o jedničku větší než index kroku vytvořeného
     * před ním, přesněji než poslední zapamatovaný index.
     *
     * @param typeOfStep O který typ kroku se jedná
     * @param command    Zadaný příkaz
     * @param message    Zprava vydaná v reakci na zadaný příkaz
     * @param place      Název prostoru, v němž bude hráč vykonání příkazu
     * @param neighbors  Názvy prostorů, kam se je odsud možno přesunout
     * @param items      Názvy objektů v aktuálním prostoru
     * @param bag        Názvy objektů v batohu
     */
    public ScenarioStep(TypeOfStep typeOfStep, String command, String message,
                        String place,
                        String[] neighbors,  String[] items,  String[] bag)
    {
        this(++lastIndex, typeOfStep,
             command, message, place, neighbors, items, bag);
    }


    /***************************************************************************
     * Vytvoří krok umožňující otestování správné funkce hry.
     * Index tohoto kroku bude o jedničku větší než index kroku vytvořeného
     * před ním, přesněji než poslední zapamatovaný index.
     *
     * @param typeOfStep O který typ kroku se jedná
     * @param command    Zadaný příkaz
     * @param message    Zprava vydaná v reakci na zadaný příka
     * @param place      Název prostoru, v němž bude hráč vykonání příkazu
     * @param neighbors  Názvy prostorů, kam se je odsud možno přesunout
     * @param items      Názvy objektů v aktuálním prostoru
     * @param bag        Názvy objektů v batohu
     * @param theLast    Příznak toho, že daný krok je posledním krokem scénáře
     */
    public ScenarioStep(TypeOfStep typeOfStep, String command, String message,
                        String   place,
                        String[] neighbors,  String[] items,  String[] bag,
                        boolean  theLast)
    {
        this(++lastIndex, typeOfStep,
             command, message, place, neighbors, items, bag, theLast);
    }


    /***************************************************************************
     * Vytvoří krok umožňující otestování správné funkce hry,
     * přičemž tento krok bude mít zadaný index, na nějž budou navazovat
     * indexy následně vytvořených kroků bez explicitně zadaného indexu.
     *
     * @param index      Číslo, které by mělo určovat pořadí daného kroku
     *                   v rámci jeho scénáře
     * @param typeOfStep O který typ kroku se jedná
     * @param command    Zadaný příkaz
     * @param message    Zprava vydaná v reakci na zadaný příkaz
     * @param place      Název prostoru, v němž bude hráč vykonání příkazu
     * @param neighbors  Názvy prostorů, kam se je odsud možno přesunout
     * @param items      Názvy objektů v aktuálním prostoru
     * @param bag        Názvy objektů v batohu
     */
    public ScenarioStep(int     index,      TypeOfStep typeOfStep,
                       String   command,    String   message,  String   place,
                       String[] neighbors,  String[] items,  String[] bag)
    {
        this(index, typeOfStep, command, message, place, neighbors, items,
             bag,   false);
    }


    /***************************************************************************
     * Vytvoří další krok scénáře totožný s krokem zadaným v parametru.
     *
     * @param step "Kopírovaný" krok scénáře
     */
    public ScenarioStep(ScenarioStep step)
    {
        this(step.index, step);
    }


   /***************************************************************************
     * Vytvoří další krok scénáře totožný s krokem zadaným v parametru
     * s výjimkou hodnoty indexu,
     * která bude v novém kroku nastavena na zadanou hodnotu.
     *
     * @param index Index vytvářeného kroku
     * @param step "Kopírovaný" krok scénáře
     */
    public ScenarioStep(int index, ScenarioStep step)
    {
        this(index, step, step.theLast);
    }


    /***************************************************************************
     * Vytvoří další krok scénáře totožný s krokem zadaným v parametru
     * s výjimkou hodnoty {@link #theLast},
     * která bude v novém kroku nastavena na zadanou hodnotu.
     *
     * @param step    "Kopírovaný" krok scénáře
     * @param theLast Příznak toho, že daný krok je posledním krokem scénáře
     */
    public ScenarioStep(ScenarioStep step, boolean theLast)
    {
        this(step.index, step, theLast);
    }


    /***************************************************************************
     * Vytvoří další krok scénáře totožný s krokem zadaným v parametru
     * s výjimkou hodnoty indexu a hodnoty {@link #theLast},
     * které budou v novém kroku nastaveny na zadanou hodnotu.
     *
     * @param index   Index vytvářeného kroku
     * @param step    "Kopírovaný" krok scénáře
     * @param theLast Příznak toho, že daný krok je posledním krokem scénáře
     */
    public ScenarioStep(int index, ScenarioStep step, boolean theLast)
    {
        this(index, step.typeOfStep, step.command, step.message,
             step.place, step.neighbors,  step.items, step.bag, theLast);
    }


    /***************************************************************************
     * Vytvoří krok umožňující otestování správné funkce hry,
     * přičemž tento krok bude mít zadaný index, na nějž budou navazovat
     * indexy následně vytvořených kroků bez explicitně zadaného indexu.
     *
     * @param index      Číslo, které by mělo určovat pořadí daného kroku
       *                 v rámci jeho scénáře
     * @param typeOfStep O který typ kroku se jedná
     * @param command    Zadaný příkaz
     * @param message    Zprava vydaná v reakci na zadaný příkaz
     * @param place      Název prostoru, v němž bude hráč vykonání příkazu
     * @param neighbors  Názvy prostorů, kam se je odsud možno přesunout
     * @param items      Názvy objektů v aktuálním prostoru
     * @param bag        Názvy objektů v batohu
     * @param theLast    Příznak toho, že daný krok je posledním krokem scénáře
     */
    public ScenarioStep(int     index,      TypeOfStep typeOfStep,
                       String   command,    String   message,  String   place,
                       String[] neighbors,  String[] items,    String[] bag,
                       boolean  theLast)
    {
        this.index     = lastIndex = index;
        this.command   = command;
        this.message   = message;
        this.typeOfStep= typeOfStep;
        this.theLast   = theLast;

        if (invalidCommand()         ||
            invalidMessage()         ||
            (typeOfStep != TypeOfStep.tsNOT_START)  &&
               (invalidName (place)  ||
                invalidNames(neighbors, items, bag)) )
        {
            String error =
                    "Při konstrukci kroku scénáře byla vyhozena výjimka " +
                    "způsobená tím, že:\n" + errorMsg +  "\nZadáno:";
            String notification = toString(index, typeOfStep, error, command,
                                         message, place,
                                         neighbors, items, bag);
            prln(notification);
            throw new IllegalArgumentException(notification);
        }
        if (typeOfStep == TypeOfStep.tsNOT_START) {
            this.place      = "";
            this.neighbors =
            this.items     =
            this.bag       = new String[]{};
        }
        else {
            this.place      = place;
            Arrays.sort(this.neighbors = neighbors, CIC);
            Arrays.sort(this.items     = items,     CIC);
            Arrays.sort(this.bag       = bag,       CIC);
        }
    }


    /***************************************************************************
     * Vytvoří další krok scénáře NEsloužícího k testování funkce hry, ale ke
     * kontrole funkce některých doprovodných programů či dodržení kontraktu.
     *
     * @param index   Pořadí daného kroku v rámci scénáře
     * @param game    Hra, jejíž stav scénář popisuje
     * @param command Příkaz, po jehož zadání se hra dostala do daného stavu
     * @param message Zpráva, kterou hra vypsala v odpovědi na zadaný příkaz
     */
    public ScenarioStep(int index, IGame game, String command, String message)
    {
        this(index, TypeOfStep.tsUNKNOWN, command, message,
             game.getWorld().getCurrentPlace().getName(),
             Util.colINamed2StringArr(
                     game.getWorld().getCurrentPlace().getNeighbors()),
             Util.colINamed2StringArr(
                     game.getWorld().getCurrentPlace().getItems()),
             Util.colINamed2StringArr(game.getBag().getItems()));
    }


    /***************************************************************************
     * Vytvoří další krok zjednodušeného scénáře NEsloužícího k testování,
     * ale pouze k demonstraci funkce hry a jejího rozhraní, přičemž jeho index
     * bude o jedničku větší než index kroku vytvořeného před ním.
     *
     * @param command Zadaný příkaz
     */
    public ScenarioStep(String command)
    {
        this(++lastIndex, command);
    }


    /***************************************************************************
     * Vytvoří krok zjednodušeného scénáře NEsloužícího k testování,
     * ale pouze k demonstraci funkce hry a jejího rozhraní,
     * přičemž tento krok bude mít zadaný index, na nějž budou navazovat
     * indexy následně vytvořených kroků bez explicitně zadaných indexů.
     *
     * @param index   Číslo, které by mělo určovat pořadí daného kroku
     *                v rámci jeho scénáře
     * @param command Zadaný příkaz
     */
    public ScenarioStep(int index, String command)
    {
        if (command == null)  {
            throw new NullPointerException(
                      "\nAkce musí být platný neprázdný řetězec");
        }
        this.index     = lastIndex = index;
        this.command   = command;
        this.typeOfStep= TypeOfStep.tsDEMO;

        this.message   = null;
        this.place      = null;
        this.neighbors = null;
        this.items     = null;
        this.bag       = null;
        this.theLast   = false;
    }

    public ScenarioStep(int i, TypeOfStep typeOfStep, String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ScenarioStep(TypeOfStep typeOfStep, String dveře, String tento_příkaz_neznám_dveře, String předsíň, String[] string, String[] string0, String[] string1, ScenarioStep scenarioStep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ScenarioStep(TypeOfStep typeOfStep, String jdi, String nevím_kam_mám_jít_Je_třeba_zadat_jméno_cí, ScenarioStep scenarioStep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ScenarioStep(TypeOfStep typeOfStep, String vezmi_Krvavý_nůž, String sebral_jsi_krvavý_nůž, String kuchyně) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí hodnotu indexu daného kroku.
     *
     * @return Hodnota indexu daného kroku
     */
    public int getIndex()
    {
        return index;
    }


    /***************************************************************************
     * Vrátí text příkazu zadávaného v daném kroku scénáře.
     *
     * @return Zadávaný příkaz
     */
    public String getCommand()
    {
        return command;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Vrátí řetězec obsahující příkaz realizovaný daným krokem
     * následovaný zprávou, kterou hra vrátí jako odpověď.
     *
     * @return  Požadovaný text
     */
    public String commandAndMessage()
    {
        return "\n" + command + "\n" + message + "\n";
    }


    /***************************************************************************
     * Porovná daný krok scénáře se zadným objektem na totální shodu.
     *
     * @param obj Porovnávaný objekt
     * @return Jedná-li se o stejný krok scénáře, vrátí {@code true},
     *         jinak vrátí {@code false}.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ScenarioStep other = (ScenarioStep) obj;
//        if (this.index != other.index) {
//            return false;
//        }
        if (this.typeOfStep != other.typeOfStep) {
            return false;
        }
        if (stringDiffers(this.command, other.command))
        {
            return false;
        }
        if (stringDiffers(this.message, other.message)) {
            return false;
        }
        if (stringDiffers(this.place, other.place)) {
            return false;
        }
        if (!Arrays.deepEquals(this.neighbors, other.neighbors)) {
            return false;
        }
        if (!Arrays.deepEquals(this.items, other.items)) {
            return false;
        }
        if (!Arrays.deepEquals(this.bag, other.bag)) {
            return false;
        }
        if (this.theLast != other.theLast) {
            return false;
        }
        return true;
    }


    private boolean stringDiffers(String first, String second)
    {
        return (first == null)  ||  (second == null)  ||
               !first.equalsIgnoreCase(second);
    }


    /***************************************************************************
     * Vrátí hash kód daného kroku scénáře.
     *
     * @return Hash kód daného kroku scénáře
     */
    @Override
    public int hashCode()
    {
        int hash =
            7;
        hash = 97 * hash + this.index;
        hash = 97 * hash + Objects.hashCode(this.typeOfStep);
        hash = 97 * hash + Objects.hashCode(this.command);
        hash = 97 * hash + Objects.hashCode(this.message);
        hash = 97 * hash + Objects.hashCode(this.place);
        hash = 97 * hash + Arrays.deepHashCode(this.neighbors);
        hash = 97 * hash + Arrays.deepHashCode(this.items);
        hash = 97 * hash + Arrays.deepHashCode(this.bag);
        hash = 97 * hash + (this.theLast ? 1 : 0);
        return hash;
    }


    /***************************************************************************
     * Podrobný podpis daného kroku scénáře;
     * obsahuje-li atribut {@link #message} hodnotu {@code null},
     * vypíše se pouze index kroku a zadaný příkaz.
     *
     * @return Textový podpis dané instance
     */
    @Override
    public String toString()
    {
        String ret =
        "\n" + twoDigits(index) + ". krok:  " + A_ + command   + _Z +
            (
            toString(index, typeOfStep, "Očekávaný stav po provedení akce:",
                            command, message, place, neighbors, items, bag)
            ) +
            "\n";
        return ret;
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Otestuje, zda zadaný příkaz je korektní a pokud ne,
     * uloží příslušné chybové hlášení.
     * Příkaz musí splňovat následující omezení:
     * <ul>
     *    <li>nesmí být zadán jako prázdný odkaz ({@code null}),</li>
     *    <li>není-li to startovní příkaz (byť falešný), nesmí to být
     *        prázdný řetězec ({@code ""}),</li>
     *    <li>nesmí obsahovat více jak 3 parametry,</li>
     *    <li>nesmí být delší než 80 znaků.</li>
     * </ul>
     *
     * @return Nalezne-li problém, vrátí {@code true}, jinak vrátí {@code false}
     */
    private boolean invalidCommand()
    {
        if (invalidNull("Příkaz", command)) {
        }
        else if (((typeOfStep == TypeOfStep.tsSTART) ||
                  (typeOfStep == TypeOfStep.tsEMPTY) ) && !command.isEmpty())
        {
            errorMsg = "Příkaz typu " + typeOfStep
                     + " musí být zadán jako prázdný řetězec";
        }
        else if ((typeOfStep != TypeOfStep.tsSTART)    &&
                 (typeOfStep != TypeOfStep.tsEMPTY)    &&
                 (typeOfStep != TypeOfStep.tsNOT_SET)  &&
                 command.trim().isEmpty())
        {
            errorMsg = "Nestartovní příkaz byl zadán jako prázdný řetězec";
        }
        else if (command.length() > 80) {
            errorMsg = "Příkaz je příliš dlouhý, má víc než 80 znaků";
        }
        else {
            int arguments = command.split("\\s+").length - 1;
            if ((typeOfStep.PARAM_COUNT >= 0)        &&
                (arguments != typeOfStep.PARAM_COUNT))
            {
                errorMsg =   "Příkaz má špatný počet parametrů"
                         + "\n  Požadováno: " + typeOfStep.PARAM_COUNT
                         + "\n  Zadáno: "     + arguments;
            }
            else {
                //Nebyla odhalena žádná chyba
                return false;
            }
        }
        return true;
    }


    /***************************************************************************
     * Zjistí, zda zadaný parametr je neprázdný řetězec,
     * resp. řetězec neobsahující jenom bílé znaky.
     * Při odhalení chyby nastaví chybové hlášení.
     *
     * @param textTypeDecription Popis typu testovaného textu
     * @param text               Testovaný text
     * @return Je-li parametrem prázdný řetězec
     *         nebo obsahuje-li pouze bílé znaky, vrátí {@code true},
     *         v opačném případě vrátí {@code false}
     */
    private boolean invalidEmpty(String textTypeDecription, String text)
    {
        if ("".equals(text.trim())) {
            errorMsg = textTypeDecription + " byl zadán jako prázdný řetězec";
            return true;
        }
        return false;
    }


    /***************************************************************************
     * Otestuje, zda zadaná odpověď hry je korektní a pokud ne,
     * uloží příslušné chybové hlášení.
     * Text odpovědi musí splňovat následující omezení:
     * <ul>
     *    <li>nesmí být zadán jako prázdný odkaz ({@code null}),</li>
     *    <li>nesmí to být prázdný řetězec ({@code ""}),</li>
     *    <li>nesmí být delší než 80 znaků.</li>
     * </ul>
     *
     * @return Nalezne-li problém, vrátí {@code true}, jinak vrátí {@code false}
     */
    private boolean invalidMessage()
    {
        String type = "Text odpovědi na příkaz";
        if (!invalidNull (type, message)  &&
            !invalidEmpty(type, message))
        {
            String[] lines      = message.split("\\\n");
            boolean  shortLines = Arrays.stream(lines)
                                  .noneMatch(line -> (line.length() > 80));
            if (shortLines) {
                return false;
            }
            errorMsg = type + " obsahuje řádek delší než 80 znaků";
        }
        return true;
    }


    /***************************************************************************
     * Zjistí, zda zadaný parametr může být název,
     * tj. zda je to neprázdný řetězec obsahující jediné slovo.
     * Při odhalení chyby nastaví chybové hlášení.
     *
     * @param text Testovaný řetězce
     * @return Je-li parametrem prázdný řetězec
     *         nebo neobsahuje-li právě jedno slovo, vrátí {@code true},
     *         v opačném případě vrátí {@code false}.
     */
    private boolean invalidName(String text)
    {
        String type = "Název prostoru či objektu";
        if (invalidNull(type, text)  ||  invalidEmpty(type, text)) {
            return true;
        }
        String[] lines = text.trim().split("\\s");
        if (lines.length != 1) {
            errorMsg = type + " není tvořen jediným slovem: «" + text + "»";
            return true;
        }
        return false;
    }


    /***************************************************************************
     * Zjistí, zda jsou všechny parametry neprázdné řetězce
     * obsahující právě jedno slovo.
     *
     * @param texts Testované řetězce
     * @return Poruší-li některý z parametrů uvedenou podmínku, vrátí
     *         {@code true}, v opačném případě vrátí {@code false}.
     */
    private boolean invalidNames(String... texts)
    {
        for (String text : texts) {
            if (invalidName(text)) {
                return true;
            }
        }
        return false;
    }


    /***************************************************************************
     * Zjistí, zda všechna zadaná pole textových řetězců obsahují
     * pouze řetězce obsahující právě jedno slovo.
     *
     * @param sets Testovaná pole řetězců
     * @return Poruší-li některý z řetězců uvedenou podmínku, vrátí
     *         {@code true}, v opačném případě vrátí {@code false}.
     */
    private boolean invalidNames(String[]... sets)
    {
        for (String[] set : sets) {
            if (set == null) {
                errorMsg = "Pole názvů je zadáno jako prázdný odkaz";
                return true;
            }
            for (String text : set) {
                if (invalidName(text)) {
                    return true;
                }
            }
        }
        return false;
    }


    /***************************************************************************
     * Zjistí, zda zadaný parametr je neprázdný řetězec
     * a neobsahuje řádky delší než 80 znaků.
     *
     * @param textTypeDecription Popis typu testovaného textu
     * @param text               Testovaný text
     * @return Je-li parametrem prázdný odkaz {@code null}, prázdný řetězec
     *         nebo obsahuje-li pouze bílé znaky, vrátí {@code true},
     *         v opačném případě vrátí {@code false}.
     */
    private boolean invalidNull(String textTypeDecription, String text)
    {
        if (text == null) {
            errorMsg = textTypeDecription + " byl zadán jako prázdný odkaz";
            return true;
        }
        return false;
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
