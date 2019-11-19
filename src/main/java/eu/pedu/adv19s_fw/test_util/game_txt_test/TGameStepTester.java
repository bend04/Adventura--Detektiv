/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.game_txt_test;

import eu.pedu.adv19s_fw.game_txt.IBag;
import eu.pedu.adv19s_fw.game_txt.IGame;
import eu.pedu.adv19s_fw.game_txt.IItem;
import eu.pedu.adv19s_fw.game_txt.INamed;
import eu.pedu.adv19s_fw.scenario.ScenarioStep;
import eu.pedu.adv19s_fw.scenario.TypeOfStep;
import eu.pedu.adv19s_fw.test_util.ATester;
import eu.pedu.adv19s_fw.test_util.common.FrameworkException;
import eu.pedu.adv19s_fw.test_util.common.TestException;
import eu.pedu.adv19s_fw.utilities.Util;

import java.util.Arrays;
import java.util.Collection;

import static eu.pedu.adv19s_fw.utilities.FormatStrings.*;
import static eu.pedu.adv19s_fw.utilities.IndentingFormater.*;
import static eu.pedu.adv19s_fw.utilities.Util.*;

import eu.pedu.adv19s_fw.game_txt.IPlace;



/*******************************************************************************
 * Instance třídy {@code TGameStepTester} jsou schopny
 * prověřit provedení kroků scénářů zadané hry,
 * tj. zda je hra po vykonání předchozího příkazu
 * ve stavu definovaném příslušným krokem scénáře.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public final class TGameStepTester extends ATester
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================


    /***************************************************************************
     * Ověří, jestli zadaný parametr není prázdným odkazem,
     * a pokud ano, vyhodí výjimku
     *
     * @param object    Parametr, jehož "nullovost" testujeme
     * @param message   Zpráva, kterou vypíše příslušná výjimka
     * @throws TestException Chyba v testované aplikaci
     */
    private static void verifyNull(Object object, String message)
    {
        if (object == null) {
            throw new TestException(message);
        }
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    private final IGame game;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří objekt schopný prověřit provedení kroků scénářů zadané hry.
     *
     * @param gameSummary Testovaná hra
     */
    public TGameStepTester(GameSummary gameSummary)
    {
        super(gameSummary.factory);
        this.game = gameSummary.game;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Vrátí první neprázdný řádek zadaného textu.
     * <p>
     * Dříve:
     * Vrátí začátek zadaného textu až po první
     * tečku, dvojtečku, otazník, vykřičník nebo konec řádku.
     * Pak první dva neprázdné řádky
     *
     * @param text Text, jehož začátek je zjišťován
     * @return Začátek zadaného textu
     */
    private String checkedPart(String text)
    {
        String answer = text.trim();
        String result = "";
        if (answer.length() > 0) {
            String[] lines = answer.split("\\n");  //("[\\.\\n!\\?\\:]");
            boolean  empty = true;
            for (String line : lines) {
                String st = line.trim();
                if (st.length() > 0) {
                    result = st;
                    return result;
                    //Tady je text na vrácení dvou řádků
                    //Ten mohu použít, až bude požadavek deklarován
//                        if (empty) {
//                            result = st;
//                            empty  = false;
//                            //TODO ///////////////////////////////////
//                            return result;
//                        }
//                        else {
//                            result += '\n' + st;
//                            return result;          //==========>
//                        }
                }
            }
        }
        if (result.isEmpty()) {
            ERROR("Empty message entered");
            throw new FrameworkException(
                      "\nThe ERROR method does not thrown exception");
        }
        else {
            return result;
        }
    }


    /***************************************************************************
     * Ověří, ze aktuální stav hry odpovídá informacím zapamatovaným
     * v atributech daného kroku testu a vrátí řetězec s popisem tohoto stavu.
     * Nebude-li aktuální stav odpovídat očekávanému, vyhodí výjimku
     * {@code IllegalStateException}.
     *
     * @param step    Testovaný krok scénáře
     * @param message Zpráva vrácená hrou po zadání posledního příkazu
     *
     * @return Zpráva o provedeném testu pro kontrolní tisk
     */
    public String verify(ScenarioStep step, String message)
    {
        StringBuilder sb = new StringBuilder();

        //TODO Upravit text knihy tak, aby se to dalo vyhodit.
        //Úlitba doposud napsané verzi knihy, v níž se na neživost přišlo dřív,
        //než na to, že není definován Wolrd. Tak jsem to nakoec dal na začátek,
        //než se začne analyzovat stav světa po provedeném kroku.
        //Druhou mžností je, že prohlásim, že by studenti měli mít možnost
        //nejprve dotáhnout živost a teprve pak se bavit o světu hry
        if (step.typeOfStep == TypeOfStep.tsSTART) {
            checkAliveness(sb, step);
        }

        IPlace currentPlace = game.getWorld().getCurrentPlace();

        if (demoStep(sb, step, message, currentPlace)) {
            //U demonstračních kroků vrátí v sb popis daného kroku
            return sb.toString();
        }

        //sb jse stále prázdný - připraví popis kroku
        sb.append(step)
          .append("Received message:")
          .append(N_CIRCUMFLEXES_N)
          .append(message)
          .append(N_LINE_N);

        //Nesedí-li očekávaný a zjištěný stav, vyhodí výjimku
        checkState(step, message, currentPlace);

        //Nesedí-li živost s očekáváním, doplní zprávu
        checkAliveness(sb, step);

        return sb.toString();
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Prověří, že zjištěný aktuální stav hry odpovídá stavu
     * očekávanému podle scénáře.
     *
     * @param step      Testovaný krok
     * @param message   Obdržená zpráva
     * @param curPlace   Aktuální prostor
     * @throws TestException v případě, že očekávaný stav neodpovídá zjištěnému
     */
    private void checkState(final ScenarioStep step,
                            String message, IPlace curPlace)
            throws TestException
    {
        StringBuilder sb = new StringBuilder();
        try {
            verifyMessage (sb, step, message);

            if (step.typeOfStep == TypeOfStep.tsNOT_START) {
                //U kroků prověřujících reakci na zadání příkazu mrtvé hře
                //prověřujeme pouze vrácenou zprávu
                return;
            }
            checkMessageLength("message in the step " + step.index,
                                step.message);
            verifyCurrentPlace(sb, step, curPlace);
            verifyEquality(sb, step, curPlace.getNeighbors(),
                                     step.neighbors, "nighbors");
            verifyEquality(sb, step, curPlace.getItems(),
                                     step.items, "items");
            verifyEquality(sb, step, game.getBag().getItems(),
                                     step.bag, "items in the bag");
        } catch(Exception ise) {
            String errMsg;
            if (step.typeOfStep != TypeOfStep.tsNOT_START) {
                errMsg = describeError(sb, step, curPlace, message, ise);
            }
            else {
                errMsg = "\nUntimely scenario test end caused by error:"
                       + ise.getMessage();
            }
            throw new TestException(errMsg, ise);
        }
    }


    /***************************************************************************
     * Prověří, že zjištěná živost hry odpovídá očekávané, a pokud ne,
     * přidá na konec stringbuilderu příslušnou zprávu.
     *
     * @param step  Testovaný krok
     * @param sb    {@link StringBuilder} pro případnou zprávu
     */
    private void checkAliveness(StringBuilder sb, ScenarioStep step)
    {
        String text;
        if (game.isAlive()) {
            switch (step.typeOfStep)
            {
                case tsEND:
                    text = "The game claims that it is running "
                         + "despite it was ended";
                    break;

                case tsNOT_START:
                    text = "The game claims that it is running "
                         + "despite it was not started";
                    break;

                default:
                    if (step.theLast) {
                        text = "The game claims that it is running "
                             + "despite the scenario was ended";
                        break;
                    }
                    return;     //Správně běží ==========>
            }
        }
        else if ((step.theLast)                         ||
                 (step.typeOfStep == TypeOfStep.tsEND)  ||
                 (step.typeOfStep == TypeOfStep.tsNOT_START))
        {
            return;             //Správně neběží ==========>
        } else {
            text = "The game should be running, but it claims "
                 + "that it is not";
        }
        sb.append("\n##### ").append(text).append(N_DOUBLELINE_N);
        throw new IllegalStateException("\n" + text);
    }


    /***************************************************************************
     * Jedná-li se o demonstrační krok, přidá na konec obdrženého stringbuilderu
     * popis očekávaného stavu a vrátí informaci o tom,
     * byl-li krok demonstrační.
     *
     * @param step      Testovaný krok
     * @param message   Obdržená zpráva
     * @param actPlace  Aktuální prostor
     * @param sb        {@link StringBuilder} pro případnou zprávu
     * @return Informace o typu kroku – byl-li demonstrační, vrátí {@code true},
     *         v opačném případě vrátí {@code false}
     */
    private boolean demoStep(StringBuilder sb, ScenarioStep step,
                             String message, IPlace actPlace)
    {
        if (TypeOfStep.tsDEMO.equals(step.typeOfStep)) {
            //U demonstračních kroků se stav hry neověřuje, ale pouze zjišťuje
            ScenarioStep dStep = new ScenarioStep(
                    step.getIndex(), TypeOfStep.tsDEMO, step.command,
                    message,         actPlace.getName(),
                    Util.colINamed2StringArr(actPlace.getNeighbors()),
                    Util.colINamed2StringArr(actPlace.getItems()),
                    Util.colINamed2StringArr(game.getBag().getItems()));
            sb.append(dStep);
            return true;
        }
        return false;
    }


    /***************************************************************************
     * Ověří shodu názvu očekávaného a zadaného prostoru.
     *
     * @param sb   {@link StringBuilder}, v němž metoda předává chybové hlášení
     * @param step Prověřovaný krok scénáře
     * @param placeInTest Prostor, jehož název testujeme
     * @throws IllegalStateException
     *         Je-li aktuální prostor jiný než očekávaný
     * @throws NullPointerException
     *         Je-li odkaz na prostor k prověření je prázdný
     */
    private void verifyCurrentPlace(StringBuilder sb, ScenarioStep step,
                                   IPlace placeInTest)
    {
        String str;
        if (placeInTest == null) {
            str = DOUBLELINE_N + "The verified place is null" +
                  N_DOUBLELINE_N;
            sb.append(str);
            throw new NullPointerException(str);

        }
        if (! placeInTest.getName().equalsIgnoreCase(step.place)) {
            str = DOUBLELINE +
                  "\nThe current place differs from the expected" +
                  "\n   Expected: " + A_ + step.place + _Z +
                  "\n   Obtained: " + A_ + placeInTest.getName() + _Z +
                  N_DOUBLELINE_N;
            sb.append(str);
            throw new IllegalStateException(str);
        }
    }


    /***************************************************************************
     * Ověří shodu očekávaných a zjištěných pojmenovaných instanci;
     * neshoduji-li se, vyhodí výjimku {@code IllegalStateException}.
     *
     * @param sb   {@link StringBuilder}, v němž metoda předává chybové hlášení
     * @param step      Prověřovaný krok scénáře
     * @param instances Kolekce obdržených instancí
     * @param expected  Pole názvů očekávaných instancí
     * @param kind      Druh testovaných instancí - pouze pro chybovou zprávu
     * @throws IllegalStateException
     *         Názvy obdržených instancí neodpovídají očekávaným názvům
     */
    private void verifyEquality(StringBuilder sb, ScenarioStep step,
                                Collection<? extends INamed> instances,
                                String[] expected, String kind)
    {
        String[] names = colINamed2StringArr(instances);
        Arrays.sort(names, CIC);
        Arrays.sort(expected, CIC);
        int EL = expected.length;
        int NL = names.length;
        try {
            if (EL != NL) {
                throw new IllegalStateException();
            }
            for (int v=0;   v < EL;   v++) {
                if (! expected[v].equalsIgnoreCase(names[v])) {
                    throw new IllegalStateException();
                }
            }
        } catch(IllegalStateException ise) {
            String str = DOUBLELINE_N +
                  "The lists of expected and obtained " + kind + " differs" +
                "\n   Expected: " + objArr2String(expected) +
                "\n   Obtained: " + objArr2String(names) +
                N_DOUBLELINE;
            sb.append(str);
            throw new IllegalStateException(str);
        }
    }


    /***************************************************************************
     * Ověří shodu očekávané a obdržené zprávy;
     * neshoduji-li se, vyhodí výjimku {@code IllegalStateException}
     * která vypíše shodující se počátek zprávy a u neshodujícího se znaku
     * kódy očekávaného a obdrženého znaku.
     *
     * @param sb     {@link StringBuilder}, v němž metoda předává chybové hlášení
     * @param step    Prověřovaný krok scénáře
     * @param obtainedMessage
     *                Zpráva, jejíž obsah je porovnáván s příslušným atributem
     * @throws IllegalStateException
     *         Pokud se očekávaná a obdržená zpráva neshodují
     */
    private void verifyMessage(StringBuilder sb, ScenarioStep step,
                               String obtainedMessage)
    {
        if (ScenarioStep.IGNORED_MESSAGE.equals(step.message)) { return; }
        String expected = checkedPart(step.message);
        String obtained = checkedPart(obtainedMessage);

        //Prověření počátku zprávy
        if (obtained.equalsIgnoreCase(expected)) {
            return;                             //==========>
        }
        //Text skládám před přidáním do StringBuilderu, aby byl přehlednější
        String txt =
               DOUBLELINE_N +
               "The beginning of the expected and obtained messages differs.\n"
             + indent("   Expected: " + A_, expected  + _Z) + '\n'
             + indent("   Obtained: " + A_, obtainedMessage + _Z);
        sb.append(txt);
        int expectedLength = expected.length();
        int obtainedLength = obtained.length();
        if (expectedLength > obtainedLength) {
            sb.append("\nThe expected message is longer than the obtained one");
        }
        int numOfChar = Math.min(expectedLength, obtainedLength);
        for (int i=0;   i < numOfChar;   i++) {
            if (Character.toLowerCase(obtained.charAt(i))  !=
                Character.toLowerCase(expected .charAt(i)) )
            {
                char c = expected .charAt(i);
                char p = obtained.charAt(i);
                txt = "\nThe verified beginning: \n           " + A_ +
                            expected.substring(0, i) + _Z +
                      "\nDifference at position "   + i +
                      "\n         expected code "   + (int) c +
                      ((c > ' ')  ?  (", character " + A_ + c + _Z)  :  "") +
                      "\n         obtained code "   + (int) p +
                      ((p > ' ')  ?  (", character " + A_ + p + _Z)  :  "");
                sb.append(txt);
                break;
            }
        }
        sb.append(N_DOUBLELINE_N);
        txt = sb.toString();
        ERROR(txt);
    }


    /***************************************************************************
     * Zjistí, zda příčinou chyby nebyl prázdný odkaz, a pokud ano, tak jaký,
     * a potom vypíše očekávaný a obdržený stav hry po daném kroku scénáře.
     *
     * @param exc       Vyhozená výjimka
     * @param sb        StringBuilder s doposud zjištěnými informacemi o chybě
     * @param game      Testovaná hra
     * @param step      Prováděný krok scénáře
     * @param curPlace  Prostor, v němž se hra nachází
     *                  po provedení zadaného kroku scénáře
     * @param message   Zpráva, kterou hra předala
     *                  po provedení zadaného kroku scénáře
     * @return Poměrně podrobný popis zjištěné chyby
     */
    private String describeError(StringBuilder sb, ScenarioStep step,
                               IPlace curPlace, String message, Exception exc)
    {
        IBag                          gameBag;
        Collection<? extends IItem> bagContent;
        Collection<? extends IPlace>   neighbors;
        Collection<? extends IItem> placeContent;

        try {
            verifyNull(curPlace, "Game world did not return the current place");

            gameBag = game.getBag();
            verifyNull(gameBag, "Game did not return the bag");

            bagContent = gameBag.getItems();
            verifyNull(bagContent, "Bag did not return it content");

            neighbors = curPlace.getNeighbors();
            verifyNull(neighbors, "The current place did not return "
                                + "its neigbors");

            placeContent = curPlace.getItems();
            verifyNull(placeContent, "The current place did not return "
                                   + "its items");
        }
        catch (Exception ex) {
            throw new TestException(
                  NN_DOUBLELINE_NN + "After entering the «" + step.command
                + "»  command the game answered:"
                + N_LINE_N + message + N_LINE_N
                + "A problem appeared during the answer evaluation:\n" +
                  ex.getMessage(),
                  ex);
        }

        String obtained;
        try {
            ScenarioStep gameReturns = new ScenarioStep(step.getIndex(),
                TypeOfStep.tsNOT_SET,
                step.command,
                message,
                curPlace.getName(),
                colINamed2StringArr(neighbors),
                colINamed2StringArr(placeContent),
                colINamed2StringArr(bagContent));
            obtained = gameReturns.toString();
        }
        catch (Exception ex) {
            obtained = "Certain of the by game returned values is not allowed"
                   + "\nIt was throw the " + ex;
        }
        String notification = "\nWhile testing the next step — The step No. "
             + step.getIndex() + ": " + step.getCommand()
             + "\nthe ERROR appeared";

        String text = NN_DOUBLELINE + notification + "\n" + sb
                    + "\n\n===== Expected ====="   + step
                    + "\n\n===== Obtained ====="   + obtained
                    + N_DOUBLELINE_N;
        return text;
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
