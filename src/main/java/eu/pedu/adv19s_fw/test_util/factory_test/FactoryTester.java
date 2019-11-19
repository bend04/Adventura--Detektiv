/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.factory_test;

import eu.pedu.adv19s_fw.Framework;
import eu.pedu.adv19s_fw.game_txt.IAuthor;
import eu.pedu.adv19s_fw.game_txt.IGSMFactory;
import eu.pedu.adv19s_fw.game_txt.IGSMFactoryProduct;
import eu.pedu.adv19s_fw.test_util.ATester;
import eu.pedu.adv19s_fw.test_util.common.FrameworkException;
import eu.pedu.adv19s_fw.test_util.common.TestException;
import eu.pedu.adv19s_fw.utilities.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;



/*******************************************************************************
 * Instance třídy {@code FactoryTester} představují ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class FactoryTester extends ATester
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    //Požadovaný pseudokořenový balíček
    private static final String PSEUDOROOT_PKG_NAME = "eu.pedu.adv19s";


    //===== Konstanty související s lokalizací vypisovaných textů =====

    /** Seznam lokalizovaných zpráv. */
    private static final List<String> I18N;

    /** Index textu "správce scénářů". */
    private static final int SCEANRIO_MGR;

    /** Index textu "hry". */
    private static final int GAME;

    /** Index textu "uživatelského rozhraní". */
    private static final int UI;

    /** Index zprávy o různých jménech autorů. */
    private static final int AUTHOR_DIFFERS;

    /** Index textu o různých instancích dodávaných továrním objektem. */
    private static final int DIFFERENT_INSTANCES_MSG;

    /** Index zprávy o lišících se továrních třídách. */
    private static final int DIFFERENT_FACTORY_CLASS_MSG;

    /** Index zprávy o různých jménech autorů. */
    private static final int INSTANCE_NOT_GET_MSG;

    /** Index zprávy o špatných názvech balíčků. */
    private static final int PKG_NAMES_MSG;

    /** Index zprávy o špatných informacích o autorovi. */
    private static final int WRONG_AUTHOR_MSG;



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Statický konstruktor nastavuje:<br>
     *  - jazykovou verzi textů
     */
    static {
        List<String> CZ = new ArrayList<>();
        List<String> EN = new ArrayList<>();

        SCEANRIO_MGR = CZ.size();
        CZ.add("správce scénářů");
        EN.add("scanario manager");

        GAME = CZ.size();
        CZ.add("hry");
        EN.add("game");

        UI = CZ.size();
        CZ.add("správce scénářů");
        EN.add("scanario manager");

        AUTHOR_DIFFERS = CZ.size();
        CZ.add("Liší se autor továrního objektu a testovaného objektu %s."
           + "\n   Autor továrního objektu: %s"
           + "\n   Autor %$1-19s: %s");
        EN.add("Authors of the factory object and tested object %s differs."
           + "\n   Author of the factory object: %s\"\n"
           + "\n   Author of the tested %$1s: %s");

        DIFFERENT_INSTANCES_MSG = CZ.size();
        CZ.add("Následná volání tovární metody poskytují různé instance %s"
           + "\nTřída použitého generátoru: %s");
        EN.add("The successive factory method calls returns different"
           +   " instances of %s"
           + "\nThe used generator class: %s");

        DIFFERENT_FACTORY_CLASS_MSG = CZ.size();
        CZ.add("Instance %s vrací jinou tovární třídu."
           + "\nTřída továrního objektu: %s"
           + "\nTřída vracená instancí %$1d: %s");
        EN.add("The instance of the %s returns different factory class."
           + "\nThe factory object class: %s"
           + "\nThe factory class returned by the instance of the %$1d: %s");

        INSTANCE_NOT_GET_MSG = CZ.size();
        CZ.add("Při pokusu o získání instance %s byla vyhozena výjimka %s"
           + "\nTřída použitého generátoru: %s");
        EN.add("The %s instance creation did not succeeded - it was thrown %s"
           + "\nThe used generator class: %s");

        PKG_NAMES_MSG = CZ.size();
        CZ.add("Očekávaný a obdržený název balíčku nesouhlasí."
           + "\n   %s"
           + "\n   Ohlášený auto musí mít svoji třídu v balíčku %s"
           + "\n   nebo v některém z jeho podbalíčků.");
        EN.add("The expected and obtained package names differs."
           + "\n   %s"
           + "\n   The announced author should have its classes "
           +       "in the package %s"
           + "\n   or in certain of its subpackages.");

        WRONG_AUTHOR_MSG = CZ.size();
        CZ.add("\nZadaný objekt %s\n"
             + "vrací špatný identifikační řetězec či jména autora\n"
             + "   ID autora: \"%s\"\n"
             + "   Jméno autora: \"%s\"");
        EN.add("The given object %s\n"
             + "returns wrong author name or its ID\n"
             + "   author ID: \"%s\"\n"
             + "   author name: \"%s\"");

        switch (Framework.LANGUAGE)
        {
            case Framework.CZECH:   I18N = CZ;  break;
            case Framework.ENGLISH: I18N = EN;  break;
            default:
                throw new RuntimeException(
                    "\nThe text language is wrong.");
        }
/*
        UI = CZ.size();
        CZ.add();
        EN.add();
*/
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Identifikační kód autora. */
    protected final String authorID;

    /** Jméno autora. */
    protected final String authorName;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří objekt schopný otestovat zadaný tovární objekt.
     *
     * @param factory Tovární objekt určený k otestování
     */
    public FactoryTester(IGSMFactory factory)
    {
        super(factory);
        this.authorID   = factory.getAuthorID();
        this.authorName = factory.getAuthorName();

        if ((authorID   == null)  ||  authorID  .isEmpty()  ||
            (authorName == null)  ||  authorName.isEmpty() )
        {
            throw new TestException(String.format(I18N.get(WRONG_AUTHOR_MSG),
                                           this, authorID, authorName));
        }
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Prověří formální korektnost metod zadaného továrního objektu,
     * přičemž sada prověřovaných metod je dána zadanou hladinou.
     *
     * @param level Hloubka testování určující sadu testovaných metod
     */
    public void testForLevel(int level)
    {
        verifyPackage(gsmFactory);
        if (level <= FACTORY_ONLY)  { return; }     //==========>

        checkProduct(gsmFactory::getScenarioManager, SCEANRIO_MGR);
        if (level <= SM_LEVEL)      { return; }     //==========>

        checkProduct(gsmFactory::getGame, GAME);
        if (level <= GAME_LEVEL)    { return; }     //==========>

        checkProduct(gsmFactory::getUI, UI);
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================

    /***************************************************************************
     * Prověří, jestli se autor daného továrního objektu shoduje s autorem
     * jím vytvořeného objektu.
     *
     * @param product     Testovaný "výrobek"
     * @param productName Název typu výrobku
     */
    private void checkCommonAuthor(IAuthor product, String productName)
    {
        if (!authorID  .equals(product.getAuthorID()  )  ||
            !authorName.equals(product.getAuthorName())  )
        {
            productName += ':';
            ERROR(I18N.get(AUTHOR_DIFFERS), productName,
                  gsmFactory.getAuthorString(), product.getAuthorString());
        }
    }


    /***************************************************************************
     * Prověří formální korektnost instance produktu dodaného továrním objektem.
     * Prověřuje se, zde následná volání generátoru vracejí totožný produkt,
     * zda produkt vrací třídu svého generátoru a
     * zda se autor produktu shoduje s autorem generátoru.
     *
     * @param generator   Generátor produktu
     * @param productName Název typu produktu ve 2. pádě
     * @throws TestException Chyba v testované aplikaci
     * @throws FrameworkException Objevena chyba frameworku
     */
    private void checkProduct(Supplier<IGSMFactoryProduct> generator,
                              int productNameIndex)
    {
        IGSMFactoryProduct product1, product2;
        try {
            product1 = generator.get();
            product2 = generator.get();
        }
        catch (Exception ex) {
            ERROR(I18N.get(INSTANCE_NOT_GET_MSG),
                  I18N.get(productNameIndex), ex, gsmFactory);
            throw new FrameworkException();
        }
        if (product1 != product2) {
            ERROR(I18N.get(DIFFERENT_INSTANCES_MSG),
                  I18N.get(productNameIndex), gsmFactory);
        }
        if (gsmFactory.getClass()  !=  product1.getFactoryClass()) {
            ERROR(I18N.get(DIFFERENT_FACTORY_CLASS_MSG),
                  I18N.get(productNameIndex), gsmFactory.getClass(),
                  product1.getFactoryClass());
        }
        checkCommonAuthor(product1, I18N.get(productNameIndex));
    }


    /***************************************************************************
     * Prověří, že se zadaná třída se nachází ve správném balíčku,
     * jehož název je odvozen z ID a jména jejího autora.
     *
     * @param authored Prověřovaná  třída
     */
    public void verifyPackage(IAuthor authored)
    {
        String  ID      = authored.getAuthorID().toLowerCase();
        String  name    = authored.getAuthorName();
        String[]words   = name.split(" ");
        String  surname = words[0].toLowerCase();
        String  ascii   = IO.removeAccents(surname);
        String  reqName = '.' + ID + "_" + ascii; //Požadovaný název

        if ("pecinovsky".equals(ascii)) {
            return;                             //==========>
        }

        Class<? extends IAuthor> cls = authored.getClass();
        Package pkg         = cls.getPackage();
        String  pkgFullName = pkg.getName();
        String  gamePkgName = pkgFullName.substring(
                              PSEUDOROOT_PKG_NAME.length() + 8);
        if ((! pkgFullName.startsWith(PSEUDOROOT_PKG_NAME + "."))  ||
            (! gamePkgName.startsWith(reqName)))
        {
            String requested = PSEUDOROOT_PKG_NAME + "._D_HHMM" + reqName;
            ERROR(I18N.get(PKG_NAMES_MSG), cls.getName(), requested);
        }
    }



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
