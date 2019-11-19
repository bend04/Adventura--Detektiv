/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



/*******************************************************************************
 * Knihovní třída {@code IO} obsahuje sadu metod
 * pro jednoduchý vstup a výstup prostřednictvím dialogových oken
 * spolu s metodou zastavující běh programu na daný počet milisekund
 * a metodu převádějící texty na ASCII jednoduchým odstraněním diakritiky.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public final class IO
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Přepravka pro nulové velikosti okrajů. */
    private static final Insets ZERO_BORDER = new Insets(0, 0, 0, 0);

    /** Rozdíl mezi tloušťkou rámečku okna ohlašovanou před a po
     *  volání metody {@link javax.swing.JFrame#setResizable(boolean)}.
     *  Tento rozdíl ve Windows ovlivňuje nastavení velikosti a pozice.
     *  Při {@code setResizable(true)} jsou jeho hodnoty větší,
     *  a proto se spočte se jako "true" - "false". */
    private static final Insets INSETS_DIF;

    /** Informace o tom, budou-li se opravovat pozice a rozměry oken. */
    private static final boolean TO_CORRECT;

    /** Seznam registrovaných testerů. */
    private static final List<ITester> TESTERS = new ArrayList<>();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Pozice dialogových oken. */
    private static Point windowLocation = new Point(0,0);

    /** Příznak testovacího režimu - je-li nastaven na {@code true},
     *  metoda {@link #inform(Object)} neotevírá dialogové okno
     *  a metoda {@link #pause(int)} nečeká. */
    private static boolean testMode = false;

    /** Generátor náhodných čísel. */
    private static Random random;

    /** Pomocná výjimka umožňující určit, odkud bylo voláno dialogové okno,
     *  z nějž uživatel program ukončil. */
    private static Throwable callStack;

    /** Informace o tom, zda je program používající třídu IO právě testován. */
    private static boolean isTested = false;



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /** Windows Vista + Windows 7 se neumějí dohodnout s Javou na skutečné
     *  velikosti oken a jejich rámů a v důsledku toho nefunguje správně
     *  ani umísťování oken na zadané souřadnice.
     *  Následující statický konstruktor se snaží zjistit chování aktuálního
     *  operačního systému a podle toho připravit potřebné korekce.
     *  Doufejme, že záhy přestane být potřeba.
     */
    static {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            JFrame frame = new JFrame();
            frame.setLocation(-1000, -1000);
            frame.setResizable(true);
            frame.pack();
            Insets insTrue  = frame.getInsets();
            frame.setResizable(false);
            Insets insFalse = frame.getInsets();
            Insets insets;
            insets = new Insets(insTrue.top    - insFalse.top,
                                insTrue.left   - insFalse.left,
                                insTrue.bottom - insFalse.bottom,
                                insTrue.right  - insFalse.right);
            if (ZERO_BORDER.equals(insets)) {
                //Nevěřím mu, určitě kecá
                int decrement = (insTrue.left == 8)  ?  5  :  1;
                insets = new Insets(decrement, decrement, decrement, decrement);
            }
            INSETS_DIF = insets;
//            CORRECT = true;
            TO_CORRECT = ! ZERO_BORDER.equals(INSETS_DIF);
        }
        else {
            INSETS_DIF = ZERO_BORDER;
            TO_CORRECT  = false;
        }
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

    /***************************************************************************
     * Při splnění zadané podmínky otevře dialogové okno s nápisem KONEC
     * a po jeho zavření ukončí program.
     *
     * @param end Podmínka, při jejímž splnění se program ukončí
     */
    public static void endIf(boolean end)
    {
        endIf(end, null);
    }


    /***************************************************************************
     * Při splnění zadané podmínky otevře dialogové okno se zadanou zprávou
     * a po jeho zavření ukončí program.
     *
     * @param end     Podmínka, při jejímž splnění se program ukončí
     * @param message Zpráva vypisovaná v dialogovém okně. Je-li {@code null}
     *                nebo prázdný řetězec, vypíše {@code KONEC}
     */
    public static void endIf(boolean end, String message)
    {
        if (isTested) {
            for (ITester tester : TESTERS) {
                tester.endIf(end, message);
            }
        }
        if (end) {
            if ((message == null)  ||  (message.isEmpty())) {
                message = "END";
            }
            inform(message);
            System.exit(0);
        }
    }


    /***************************************************************************
     * Vrátí pseudonáhodné celé číslo ze zadaného uzavřeného intervalu.
     *
     * @param  from Nejmenší očekávané číslo
     * @param  to   Největší očekávané číslo
     * @return Pseudonáhodné celé číslo ze zadaného uzavřeného intervalu
     */
    public static int getRandom(int from, int to)
    {
        if (random == null) {
            random = new Random();
        }
        int result = from + random.nextInt(to - from + 1);
        return result;
    }


    /***************************************************************************
     * Počká zadaný počet milisekund.
     * Na přerušení nijak zvlášť nereaguje - pouze skončí dřív.
     * Před tím však nastaví příznak, aby volající metoda poznala,
     * že vlákno bylo žádáno o přerušení.
     *
     * @param milliseconds Počet milisekund, po něž se má čekat.
     */
    public static void pause(int milliseconds)
    {
        if (isTested) {
            for (ITester tester : TESTERS) {
                tester.pause(milliseconds);
            }
            if (testMode) {
                return;
            }
        }
        try {
            Thread.sleep(milliseconds);
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    /***************************************************************************
     * Zbaví zadaný text diakritických znamének; současně ale odstraní také
     * všechny další znaky nespadající do tabulky ASCII.
     *
     * @param text Text určený k "odháčkování"
     * @return  "Odháčkovaný" text
     */
    public static String removeAccents(CharSequence text)
    {
        return RemoveAccents.text(text);
    }


    /***************************************************************************
     * Převede zadaný znak na ASCII.
     *
     * @param c Znak určený k "odháčkování"
     * @return  "Odháčkovaný" znak
     */
    public static String removeAccent(char c)
    {
        return RemoveAccents.character(c);
    }


    /***************************************************************************
     * Nastaví pozici příštího dialogového okna.
     *
     * @param x  Vodorovná souřadnice
     * @param y  Svislá souřadnice
     */
    public static void setDialogsPosition(int x, int y)
    {
        windowLocation = new Point(x, y);
        if (TO_CORRECT) {
            windowLocation.x += INSETS_DIF.left;
            windowLocation.y += INSETS_DIF.top + INSETS_DIF.bottom;
        }
    }


    /***************************************************************************
     * Zobrazí dialogové okno se zprávou a umožní uživateli odpovědět
     * ANO nebo NE. Vrátí informaci o tom, jak uživatel odpověděl.
     * Neodpoví-li a zavře dialog, ukončí program.
     *
     * @param question   Zobrazovaný text otázky.
     * @return Odpověděl-li uživatel <b>ANO</b>, vrátí {@code true}
     *         odpověděl-li <b>NE</b>, vrátí {@code false}
     */
    public static boolean confirm(Object question)
    {
        if (isTested) {
            boolean result = false;
            for (ITester tester : TESTERS) {
                result = tester.confirm(question);
            }
            if (testMode) {
                return result;
            }
        }
        return (choose(question, "Ano", "Ne") == 0);
    }


    /***************************************************************************
     * Zobrazí dialogové okno se zprávou a umožní uživateli odpovědět
     * ANO nebo NE. Vrátí informaci o tom, jak uživatel odpověděl.
     * Neodpoví-li a zavře dialog, ukončí program.
     *
     * @param question Zobrazovaný text otázky.
     * @param buttons  Popisky na tlačítcích
     * @return Odpověděl-li uživatel <b>ANO</b>, vrátí {@code true}
     *         odpověděl-li <b>NE</b>, vrátí {@code false}
     */
    public static int choose(Object question, String... buttons)
    {
        if (isTested) {
            int result = 0;
            for (ITester tester : TESTERS) {
                result = tester.choose(question, buttons);
            }
            if (testMode) {
                return result;
            }
        }
        return chooseImpl(question, buttons);
    }


    /***************************************************************************
     * Zobrazí dialogové okno s výzvou k zadání reálné hodnoty;
     * při zavření okna zavíracím tlačítkem ukončí aplikaci.
     *
     * @param prompt     Text, který se uživateli zobrazí.
     * @param doubleImpl Implicitní hodnota.
     * @return Uživatelem zadaná hodnota, resp. potvrzená implicitní hodnota
     */
    public static double enter(Object prompt, double doubleImpl)
    {
        if (isTested) {
            double result = 0;
            for (ITester tester : TESTERS) {
                result = tester.enter(prompt, doubleImpl);
            }
            if (testMode) {
                return result;
            }
        }
        return Double.parseDouble(enterImpl(prompt, ""+doubleImpl).trim());
    }


    /***************************************************************************
     * Zobrazí dialogové okno s výzvou k zadání celočíselné hodnoty;
     * při zavření okna nebo stisku tlačítka Cancel
     * se celá aplikace ukončí.
     *
     * @param prompt  Text, který se uživateli zobrazí.
     * @param intImpl Implicitní hodnota.
     * @return Uživatelem zadaná hodnota, resp. potvrzená implicitní hodnota
     */
    public static int enter(Object prompt, int intImpl)
    {
        if (isTested) {
            int result = 0;
            for (ITester tester : TESTERS) {
                result = tester.enter(prompt, intImpl);
            }
            if (testMode) {
                return result;
            }
        }
        return Integer.parseInt(enterImpl(prompt, ""+intImpl).trim());
    }


    /***************************************************************************
     * Zobrazí dialogové okno s výzvou k zadání textové hodnoty;
     * při zavření okna nebo stisku tlačítka Cancel
     * se celá aplikace ukončí.
     *
     * @param prompt     Text, který se uživateli zobrazí.
     * @param stringImpl Implicitní hodnota.
     * @return Uživatelem zadaná hodnota, resp. potvrzená implicitní hodnota
     */
    public static String enter(Object prompt, String stringImpl)
    {
        if (isTested) {
            String result = null;
            for (ITester tester : TESTERS) {
                result = tester.enter(prompt, stringImpl);
            }
            if (testMode) {
                return result;
            }
        }
        return enterImpl(prompt, stringImpl);
    }


    /***************************************************************************
     * Zobrazí dialogové okno se zprávou a počká,
     * až uživatel stiskne tlačítko OK;
     * při zavření okna zavíracím tlačítkem ukončí celou aplikaci.
     *
     * @param text   Zobrazovaný text.
     */
    public static void inform(Object text)
    {
        if (isTested) {
            for (ITester tester : TESTERS) {
                tester.inform(text);
            }
            if (testMode) {
                return;
            }
        }
        JOptionPane jop = new JOptionPane(
                          text,                            //Sended message
                          JOptionPane.INFORMATION_MESSAGE  //Message type
                        );
        processJOP(jop);
    }


    /***************************************************************************
     * Zobrazí dialogové okno s výzvou k zadání
     * některé z textových hodnoty uvedených v seznamu;
     * při zavření okna nebo stisku tlačítka Cancel
     * se celá aplikace ukončí.
     *
     * @param prompt  Text, který se uživateli zobrazí
     * @param options Texty, z nichž si může uživatel vybrat
     * @return Uživatelem zadaná hodnota, resp. potvrzená implicitní hodnota
     */
    public static String select(Object prompt, String... options)
    {
        if (isTested) {
            String result = null;
            for (ITester tester : TESTERS) {
                result = tester.select(prompt, options);
            }
            if (testMode) {
                return result;
            }
        }
        JOptionPane jop = new JOptionPane(
                              prompt,
                              JOptionPane.QUESTION_MESSAGE, //Message type
                              JOptionPane.OK_CANCEL_OPTION, //Option type - OK
                              null,                         //Icon
                              null,                         //Options
                              null                          //InitialValue
                          );
        jop.setWantsInput(true);
        jop.setSelectionValues(options);
        jop.setInitialSelectionValue(null);
        processJOP(jop);
        String answer = jop.getInputValue().toString();
        return answer;
    }




//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================


    /***************************************************************************
     * Zobrazí dialogové okno se zprávou a umožní uživateli odpovědět
     * ANO nebo NE. Vrátí informaci o tom, jak uživatel odpověděl.
     * Neodpoví-li a zavře dialog, ukončí program.
     *
     * @param question Zobrazovaný text otázky.
     * @param buttons  Popisky na tlačítcích
     * @return Odpověděl-li uživatel <b>ANO</b>, vrátí {@code true}
     *         odpověděl-li <b>NE</b>, vrátí {@code false}
     */
    private static int chooseImpl(Object question, String[] buttons)
    {
        JOptionPane jop = new JOptionPane(
            question,
            JOptionPane.QUESTION_MESSAGE, //Message type
            0,                            //Option type
            null,                         //Icon
            buttons,                      //Options
            null                          //InitialValue
        );
        processJOP(jop);
        String answer = (String)jop.getValue();
        if (answer == null) {
            exit(1);
        }
        for (int index = 0;   index < buttons.length;   index++) {
            if (answer.equals(buttons[index])) {
                return index;
            }
        }
        return -1;
    }


    /***************************************************************************
     * Zobrazí dialogové okno s výzvou k zadání textové hodnoty;
     * při zavření okna nebo stisku tlačítka Cancel
     * se celá aplikace ukončí.
     *
     * @param prompt     Text, který se uživateli zobrazí.
     * @param stringImpl Implicitní hodnota.
     * @return Uživatelem zadaná hodnota, resp. potvrzená implicitní hodnota
     */
    private static String enterImpl(Object prompt, String stringImpl)
    {
        JOptionPane jop = new JOptionPane(
            prompt,
            JOptionPane.QUESTION_MESSAGE,   //Message type
            JOptionPane.DEFAULT_OPTION      //Option type - OK
        );
        jop.setWantsInput(true);
        jop.setInitialSelectionValue(stringImpl);
        processJOP(jop);
        String answer = jop.getInputValue().toString();
        return answer;
    }


    /***************************************************************************
     * Ukončí běh programu a vyhodí výjimku oznamující tuto skutečnost
     * a umožňující zjistit, kde se v danou chvíli program nacházel.
     *
     * @param i Kód ukončení
     */
    private static void exit(int i)
    {
        callStack.printStackTrace(System.out);
        System.exit(i);
    }


    /***************************************************************************
     * Creates a dialog from the given {@link JOptionPane}, makes it non-modal
     * and waits for its closing leaving the entered value in the parameter's
     * attribute {@code value}. If the user closed the dialog
     * from the window's system menu, exit the whole application.
     *
     * @param jop Zpracovávaný {@link JOptionPane}
     */
    private static void processJOP(JOptionPane jop)
    {
        callStack = new Throwable(
            "Program byl předčasně ukončen uživatelem z dialogového okna");

        final int WAITING=0, CANCELLED=1;
        final Boolean[] USER = {true, false};

        final JDialog jd = jop.createDialog((Component)null, "Information");

        jd.addWindowListener(new WindowAdapter()
        {
            /** Set the information about closing the window from its
             *  systme menu - the application will be cancelled. */
            @Override
            public void windowClosing(WindowEvent e) {
                synchronized(USER) {
//                    USER[CANCELLED] = true;
                    exit(1);
                }
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
                windowLocation = jd.getLocation();
                if (jd.isShowing()) {
                    return;
                }
                jd.dispose();
                synchronized(USER) {
                    USER[WAITING] = false;
                    USER.notifyAll();
                }
            }
         });

        jd.setModal(false);
        jd.setVisible(true);
        jd.setLocation(windowLocation);
        jd.toFront();
        jd.setAlwaysOnTop(true);
//        jd.setAlwaysOnTop(false);

        //Waiting until the user answers or closes the dialog
        synchronized(USER) {
            while (USER[WAITING]) {
                try {
                    USER.wait();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Třída IO je knihovní třídou a proto není určena k tomu,
     * aby měla nějaké instance.
     */
    private IO() {}



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

    /***************************************************************************
     * Třída {@code Correction} je knihovní třídou poskytující metody
     * pro opravy nejrůznějších nesrovnalostí týkajících se práce
     * s grafickým vstupem a výstupem.
     */
    public static class Correction
    {
    //== CONSTANT CLASS FIELDS =================================================
    //== VARIABLE CLASS FIELDS =================================================
    //== STATIC INICIALIZATION BLOCK - STATIC CONSTRUCTOR ======================
    //== CONSTANT INSTANCE FIELDS ==============================================
    //== VARIABLE INSTANCE FIELDS ==============================================
    //== CLASS GETTERS AND SETTERS =============================================
    //== OTHER NON-PRIVATE CLASS METHODS =======================================

        /***********************************************************************
         * Ve Windows 7 používajících definuje Java jinou velikost okna,
         * než odpovídá velikosti panelu obrázku.
         *
         * @param cont   Kontejner, jehož rozměry upravujeme
         */
        public static void windowLocation(Container cont)
        {
            Point  loc;
            if (TO_CORRECT) {
                loc = cont.getLocation();
                cont.setLocation(loc.x + INSETS_DIF.left,
                                 loc.y + INSETS_DIF.top);
            }
        }


        /***********************************************************************
         * Ve Windows 7 definuje Java jinou velikost okna,
         * než odpovídá velikosti panelu obrázku.
         *
         * @param cont     Kontejner, jehož rozměry upravujeme
         */
        public static void windowSize(Container cont)
        {
            Dimension dim;
            if (TO_CORRECT) {
                dim = cont.getSize();
                cont.setSize(dim.width - INSETS_DIF.left - INSETS_DIF.right,
                             dim.height- INSETS_DIF.top  - INSETS_DIF.bottom);
            }
        }



    //##########################################################################
    //== CONSTRUCTORS AND FACTORY METHODS ======================================

       /** Soukromý konstruktor bránící vytvoření instance. */
        private Correction() {}


    //== ABSTRACT METHODS ======================================================
    //== INSTANCE GETTERS AND SETTERS ==========================================
    //== OTHER NON-PRIVATE INSTANCE METHODS ====================================
    //== PRIVATE AND AUXILIARY CLASS METHODS ===================================
    //== PRIVATE AND AUXILIARY INSTANCE METHODS ================================
    //== EMBEDDED AND INNER CLASSES ============================================
    //== TESTING CLASSES AND METHODS ===========================================
    }



///#############################################################################
///#############################################################################
///#############################################################################

    /***************************************************************************
     * Instance rozhraní {@code ITester} představují testovací objekty,
     * které chtějí být zpravovány o voláních metod třídy IO.
     */
    public static interface ITester
    {
    //== STATIC CONSTANTS ======================================================
    //== STATIC METHODS ========================================================

        /***************************************************************************
         * Prozradí, pracuje-li třída v testovacím režimu.
         *
         * @return Pracuje-li v testovacím režimu, vrátí {@code true},
         *         jinak vrátí {@code false}
         */
        public static boolean getTestMode()
        {
            return testMode;
        }


        /***************************************************************************
         * Nastaví, zda bude třída pracovat v testovacím režimu.
         *
         * @param testMode Zadávaný režim
         */
        public static void setTestMode(boolean testMode)
        {
            IO.testMode = testMode;
        }


        /***************************************************************************
         * Přidá zadaný objekt do seznamu testovacích objektů,
         * které budou oslovovány při volání ošetřovaných metod,
         * a nastaví, že třída bude pracovat v testovacím režimu.
         * Pokus o opětovné přidání již přidaného testeru vyvolá výjimku.
         *
         * @param tester Přidávaný tester
         * @throws IllegalArgumentException
         *         Při pokusu o registraci již zaregistrovaného testeru
         */
        public static void registerTester(ITester tester)
        {
            if (TESTERS.contains(tester)) {
                throw new IllegalArgumentException(
                   "\nPokus o opětovnou registraci již registrovaného testeru");
            }
            TESTERS.add(tester);
            isTested = true;
            testMode = true;
        }


        /***************************************************************************
         * Prozradí, je-li zadaný tester již zaregistrován.
         *
         * @param tester Prověřovaný tester
         * @return Je-li zadaný tester registrován, vrátí {@code true},
         *         není-li registrován, vrátí {@code false}
         */
        public static boolean isRegistered(ITester tester)
        {
            boolean resutlt = TESTERS.contains(tester);
            return resutlt;
        }


        /***************************************************************************
         * Odebere zadaný objekt ze seznamu testovacích objektů,
         * které budou oslovovány při volání ošetřovaných metod.
         * Pokus o odregistrování nezaregistrovaného testeru vyvolá výjimku.
         *
         * @param tester Odebíraný tester
         * @throws IllegalArgumentException
         *         Při pokusu o odregistraci nezaregistrovaného testeru
         */
        public static void unregisterTester(ITester tester)
        {
            if (! TESTERS.contains(tester)) {
                throw new IllegalArgumentException(
                   "\nPokus o odregistrování nezaregistrovaného testeru");
            }
            TESTERS.remove(tester);
        }



    //##########################################################################
    //== ABSTRACT GETTERS AND SETTERS ==========================================
    //== OTHER ABSTRACT METHODS ================================================
    //== DEFAULT GETTERS AND SETTERS ===========================================

        /***********************************************************************
         * Zobrazí dialogové okno se zprávou a umožní uživateli odpovědět
         * ANO nebo NE. Vrátí informaci o tom, jak uživatel odpověděl.
         * Neodpoví-li a zavře dialog, ukončí program.
         *
         * @param question   Zobrazovaný text otázky.
         * @return Odpověděl-li uživatel <b>ANO</b>, vrátí {@code true}
         *         odpověděl-li <b>NE</b>, vrátí {@code false}
         */
        default
        public boolean confirm(Object question)
        {
            throw new UnsupportedOperationException(
                "\nNení definována reakce na vyvolání metody "
                + "confirm(Object question)");
        }


        /***********************************************************************
         * Zobrazí dialogové okno se zprávou a umožní uživateli odpovědět
         * ANO nebo NE. Vrátí informaci o tom, jak uživatel odpověděl.
         * Neodpoví-li a zavře dialog, ukončí program.
         *
         * @param question Zobrazovaný text otázky.
         * @param buttons  Popisky na tlačítcích
         * @return Odpověděl-li uživatel <b>ANO</b>, vrátí {@code true}
         *         odpověděl-li <b>NE</b>, vrátí {@code false}
         */
        default
        public int choose(Object question, String... buttons)
        {
            throw new UnsupportedOperationException(
                "\nNení definována reakce na vyvolání metody "
                + "choose(Object question, String... buttons)");
        }


        /***********************************************************************
         * Při splnění zadané podmínky otevře dialogové okno se zadanou zprávou
         * a po jeho zavření ukončí program.
         *
         * @param end     Podmínka, při jejímž splnění se program ukončí
         * @param message Zpráva vypisovaná v dialogovém okně. Je-li
         *        {@code null} nebo prázdný řetězec, vypíše {@code KONEC}.
         */
        default
        public void endIf(boolean end, String message)
        {
            throw new UnsupportedOperationException(
                "\nNení definována reakce na vyvolání metody "
                + "endIf(boolean end, String message)");
        }


    /***************************************************************************
     * Zobrazí dialogové okno s výzvou k zadání reálné hodnoty;
     * při zavření okna zavíracím tlačítkem ukončí aplikaci.
     *
     * @param prompt     Text, který se uživateli zobrazí.
     * @param doubleImpl Implicitní hodnota.
     * @return Uživatelem zadaná hodnota, resp. potvrzená implicitní hodnota.
     */
        default
        public double enter(Object prompt, double doubleImpl)
        {
            throw new UnsupportedOperationException(
                "\nNení definována reakce na vyvolání metody "
                + "enter(Object prompt, double doubleImpl)");
        }


        /***************************************************************************
         * Zobrazí dialogové okno s výzvou k zadání celočíselné hodnoty;
         * při zavření okna nebo stisku tlačítka Cancel
         * se celá aplikace ukončí.
         *
         * @param prompt  Text, který se uživateli zobrazí.
         * @param intImpl Implicitní hodnota.
         * @return Uživatelem zadaná (resp. potvrzená implicitní) hodnota
         */
        default
        public int enter(Object prompt, int intImpl)
        {
            throw new UnsupportedOperationException(
                "\nNení definována reakce na vyvolání metody "
                + "eenter(Object prompt, int intImpl)");
        }


        /***********************************************************************
         * Zobrazí dialogové okno s výzvou k zadání textové hodnoty;
         * při zavření okna nebo stisku tlačítka Cancel
         * se celá aplikace ukončí.
         *
         * @param prompt     Text, který se uživateli zobrazí.
         * @param stringImpl Implicitní hodnota.
         * @return Uživatelem zadaná (resp. potvrzená) implicitní hodnota
         */
        default
        public String enter(Object prompt, String stringImpl)
        {
            throw new UnsupportedOperationException(
                "\nNení definována reakce na vyvolání metody "
                + "eenter(Object prompt, int intImpl)");
        }


        /***********************************************************************
         * Oznámí zavolání metody {@link IO#inform(Object)}
         * a předá v parametru vypisovaný text.
         *
         * @param message Zobrazovaný text
         */
        default
        public void inform(Object message)
        {
            throw new UnsupportedOperationException(
                "\nNení definována reakce na vyvolání metody "
                + "inform(Object message)");
        }


        /***********************************************************************
         * Oznámí zavolání metody {@link IO#pause(int)}
         * a předá v parametru zadanou dobu čekání.
         *
         * @param ms Zadaná doba čekání v milisekundách
         */
        default
        public void pause(int ms)
        {
            throw new UnsupportedOperationException(
                "\nNení definována reakce na vyvolání metody "
                + "pause(int ms)");
        }


        /***********************************************************************
         * Zobrazí dialogové okno s výzvou k zadání
         * některé z textových hodnoty uvedených v seznamu;
         * při zavření okna nebo stisku tlačítka Cancel
         * se celá aplikace ukončí.
         *
         * @param prompt  Text, který se uživateli zobrazí
         * @param options Texty, z nichž si může uživatel vybrat
         * @return Uživatelem zadaná (resp. potvrzená implicitní) hodnota
         */
        default
        public String select(Object prompt, String... options)
        {
            throw new UnsupportedOperationException(
                "\nNení definována reakce na vyvolání metody "
                + "select(Object prompt, String... options)");
        }



    //== OTHER DEFAULT METHODS =================================================



    //##########################################################################
    //== NESTED DATA TYPES =====================================================
    }



///#############################################################################
///#############################################################################
///#############################################################################

    /***************************************************************************
     * Třída {@code RemoveAccents_RUP} je knihovní třídou poskytující metodu na
     * odstranění diakritiky ze zadaného textu a následné převedení všech znaků,
     * jejichž kód je stále větší než 127, na příslušné kódové
     * únikové posloupnosti (escape sekvence).
     */
    private static class RemoveAccents
    {
    //== CONSTANT CLASS FIELDS =================================================

        /** Mapa s převody znaků do ASCII. */
        private static final Map<Character,String> CONVERSION =
                                                   new HashMap<>(64);



    //== VARIABLE CLASS FIELDS =================================================



    //##########################################################################
    //== STATIC INICIALIZATION BLOCK - STATIC CONSTRUCTOR ======================

        static {
            /** Převody UNICODE znaků na jejich ASCII ekvivalenty. */
            String[][] pairs = {

                {"Á", "A"},  {"á", "a"},    {"Ä", "AE"}, {"ä", "ae"},
                {"Č", "C"},  {"č", "c"},
                {"Ď", "D"},  {"ď", "d"},
                {"Ë", "E"},  {"ë", "e"},
                {"É", "E"},  {"é", "e"},    {"Ě", "E"},  {"ě", "e"},
                {"Í", "I"},  {"í", "i"},    {"Ï", "IE"}, {"ï", "ie"},
                {"Ĺ", "L"},  {"ĺ", "l"},    {"Ľ", "L"},  {"ľ", "l"},
                {"Ň", "N"},  {"ň", "n"},
                {"Ó", "O"},  {"ó", "o"},    {"Ö", "OE"}, {"ö", "oe"},
                {"Ô", "O"},  {"ô", "o"},
                {"Ŕ", "R"},  {"ŕ", "r"},    {"Ř", "R"},  {"ř", "r"},
                {"Š", "S"},  {"š", "s"},
                {"Ť", "T"},  {"ť", "t"},
                {"Ú", "U"},  {"ú", "u"},    {"Ü", "UE"}, {"ü", "ue"},
                {"Ů", "U"},  {"ů", "u"},
                {"Ý", "Y"},  {"ý", "y"},    {"Ÿ", "YE"}, {"ÿ", "ye"},
                {"Ž", "Z"},  {"ž", "z"},
                {"ß", "ss"},
                {"‹", "<"},  {"›", ">"},    {"«", "<<"}, {"»", ">>"},
                {"©", "(c)"},{"®", "(R)"},
                {"„", "\""}, {"“", "\""},   {"”", "\""},
                {"‚", "\'"}, {"‘", "\'"},   {"’", "\'"},
                {"×", "x"},  {"÷", ":"},
                {"–", "-"},  {"—", "-"},    //ndash, mdash
                {"¦", "|"},
//                {"",""},
            };
            for (String[] ss : pairs) {
                CONVERSION.put(new Character(ss[0].charAt(0)),  ss[1]);
            }
        }



    //== CLASS GETTERS AND SETTERS =============================================
    //== OTHER NON-PRIVATE CLASS METHODS =======================================

        /***********************************************************************
         * Převede zadaný znak na jeho ASCII-ekvivalent.
         * Je však třeba počítat s tím, že některé znaky
         *
         * @param c Znak určený k "odháčkování"
         * @return  Převedený ekvivalent
         */
        public static String character(char c)
        {
            return character(c, new StringBuilder(8));
        }


        /***********************************************************************
         * Zbaví zadaný text diakritických znamének - <b>POZOR</b> -
         * Spolu s nimi odstraní také všechny znaky s kódem větším než 127.
         *
         * @param text Text určený k "odháčkování"
         * @return  "Odháčkovaný" text
         */
        public static String text(CharSequence text)
        {
            final int LENGTH = text.length();
            final StringBuilder sb = new StringBuilder(LENGTH);
            for (int i = 0;   i < LENGTH;   i++) {
                char c = text.charAt(i);
                character(c, sb);
            }
            return sb.toString();
        }



    //== PRIVATE AND AUXILIARY CLASS METHODS ===================================

        /***********************************************************************
         * Převede zadaný znak na jeho ASCII-ekvivalent.
         * Je však třeba počítat s tím, že některé znaky
         *
         * @param c Znak určený k "odháčkování"
         * @return  Převedený ekvivalent
         */
        public static String character(char c, StringBuilder sb)
        {
            if (c < 128) {
                sb.append(c);
            }else if (CONVERSION.containsKey(c)) {
                sb.append(CONVERSION.get(c));
            }else {
                sb.append(expand(c));
            }
            return sb.toString();
        }


        /***********************************************************************
         * Rozepíše zadaný znak do příslušné ńikové k´dové posloupnosti.
         *
         * @param c Převáděný znak
         * @return Text ve formátu \\uXXXX
         */
        private static String expand(char c) {
            return String.format("\\u%04x", (int)c);
        }



    //##########################################################################
    //== CONSTANT INSTANCE FIELDS ==============================================
    //== VARIABLE INSTANCE FIELDS ==============================================



    //##########################################################################
    //== CONSTRUCTORS AND FACTORY METHODS ======================================

       /** Soukromý konstruktor bránící vytvoření instance. */
        private RemoveAccents() {}


    //== ABSTRACT METHODS ======================================================
    //== INSTANCE GETTERS AND SETTERS ==========================================
    //== OTHER NON-PRIVATE INSTANCE METHODS ====================================
    //== PRIVATE AND AUXILIARY INSTANCE METHODS ================================
    //== EMBEDDED AND INNER CLASSES ============================================
    //== TESTING CLASSES AND METHODS ===========================================
    }



///#############################################################################
///#############################################################################
///#############################################################################

}
