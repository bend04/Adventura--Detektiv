/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.game;
import java.util.Arrays;
import java.util.stream.Collectors;



/*******************************************************************************
 * Knihovní třída {@code Texts} slouží jako schránka na textové konstanty,
 * které se používají na různých místech programu.
 * Centralizací definic těchto textových řetězců lze nejsnadněji dosáhnout toho,
 * že texty, které mají být shodné na různých místech programu,
 * budou doopravdy shodné.
 *
 * @author  Dominik Beneš
 * @version 2019 léto
 */
class Texts
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    /** Jméno autora programu. */
    static final String AUTHOR_NAME = "BENEŠ Dominik";

    /** Xname autora programu. */
    static final String AUTHOR_ID = "bend04";

    /** Názvy používaných prostorů - místností. */
    static final String
           PREDSIN = "predsin",
           LOZNICE = "loznice",
           OBYVAK  = "obyvak",
           KOUPELNA= "koupelna",
           KUCHYN  = "kuchyn",
           ZAHRADA = "zahrada",
           PRACOVNA = "pracovna",
           KULNA = "kulna",
           BEZPECNOSTNI_MISTNOST = "bezpecnostni_mistnost";
           
           


    /** Názvy používaných předmětů. */
    static final String
           SKRINKA  = "skrinka",
           KOS = "kos",
           LAHVICKA   = "lahvicka" ,
           MRTVOLA= "mrtvola",
           KLIC= "klic",
           KRVAVY_NUZ = "krvavy_nuz",
           TREZOR= "trezor",
           STUL   = "stul",
           PRISTUPOVA_KARTA    = "pristupova_karta",
           PENIZE     = "penize",
           DOKUMENTY   = "dokumenty",
           ZLOMENE_HRABE  = "zlomene_hrabe",
           KLADIVO    = "kladivo",
           SEKACKA  = "sekacka",
           POCITAC = "pocitac",
           SATNIK   = "satnik",
           TELEFON  = "telefon",
           BOTA = "bota",
           BUNDA   = "bunda",
           NOCNI_STOLEK = "nocni_stolek",
           SLUZKA = "sluzka",
           MANZELKA = "manzelka" ,
           ZAHRADNIK = "zahradnik";


    /** Názvy používaných akcí. */
    static final String
           HELP   = "?",
           OTEVRI    = "otevri",
           ZAVRI  = "zavri",
           VEZMI = "vezmi",
           PROHLEDEJ = "prohledej",
           POLOZ  = "poloz",
           ODEMKNI = "odemkni",
           OBVINIT  = "obvinit",
           KONEC  = "konec",
           PROHLEDNI = "prohledni",
           JDI = "jdi";        


    /** Formát dodatku zprávy informujícího o aktuálním stavu hráče. */
    static final String
           SOUSEDÉ = "Sousede:  ",
           PŘEDMĚTY= "Predměty: ",
           BATOH   = "Batoh:    ",
           FORMAT_INFORMACE = "\n\nNacházíte se v místnosti: %s" +
                              "\n" + SOUSEDÉ  + "[%s]" +
                              "\n" + PŘEDMĚTY + "[%s]" +
                              "\n" + BATOH    + "[%s]";

     /** Texty zpráv vypisovaných v reakci na příkazy vyvolávají povinné akce.
     *  Počáteční z (zpráva) slouží k odlišení od stavů. */
    static final String
    zNENÍ_START     = "Prvním příkazem není startovací příkaz." +
               "\nHru, která neběží, lze spustit pouze startovacím příkazem.",

    zUVÍTÁNÍ        =
          "Vítejte!\n" +
            "Jste v roli detektiva a Váš ukol je pomocí důkazů, které \n"
                    + "naleznete po domě, odhalit\n"
                    + "vraha pana Nováka.\n",

    zCELÉ_UVÍTÁNÍ   = zUVÍTÁNÍ +
                      String.format(FORMAT_INFORMACE,
                                    PREDSIN, cm(KUCHYN,KOUPELNA,LOZNICE),
                     cm(SATNIK,TELEFON,BOTA,BUNDA), cm()),

 zANP            = "Zadaná akce nebyla provedena.\n",
 zPORADÍM        = "\nChcete-li poradit, zadejte příkaz ?",

 zPRÁZDNÝ_PŘÍKAZ = "Zadal(a) jste prázdný příkaz." + zPORADÍM,
 zNEZNÁMÝ_PŘÍKAZ = "Tento příkaz neznám." + zPORADÍM,

 zPŘESUN         = "Přesunul(a) jste se do místnosti: ",
 zNEVÍM_KAM_JÍT  = zANP + "Nevím, kam mám jít.\n "
            + "Je třeba zadat jméno cílového prostoru.|n",
 zNENÍ_SOUSEDEM  = zANP + "Do zadané místnosti se odsud nedá přejít: ",

 zZVEDNUTO       = "Vzal(a) jste předmět: ",
 zNEVÍM_CO_VZÍT  = zANP + "Nevím, co mám zvednout.\n "
            + "Je třeba zadat jméno zvedaného objektu.\n",
 zTĚŽKÝ_PŘEDMĚT  = zANP + "Zadaný předmět nejde zvednout: ",
 zNENÍ_PŘÍTOMEN  = zANP + "Zadaný předmět v místnosti není: ",
 zBATOH_PLNÝ     = zANP +
                      "Zadaný objekt nejde zvednout - kapsy máš plné!",
            
 zNEVÍM_CO_OTEVŘÍT = zANP + "Nebylo zadáno, co se má otevřít",
 zNEVÍM_CO_POLOŽ = zANP + "Nevím, co mám položit.\n"
            + " Je třeba zadat jméno pokládaného objektu.\n",
 zNENÍ_V_BATOHU  = zANP + "Takový objekt v kapse nemáš: ",

 zNÁPOVĚDA = "Příkazy, které je možno v průběhu hry zadat:" +
                      "\n============================================\n",
                          

 zKONEC = "Konec hry.\nDěkuji, že jste si zahráli tuto hru! Mějte hezký den.";
    
     /** Texty vypisované v reakci na příkazy vyvolávající nepovinné akce. */
     static final String
 zTREZOR_NEJDE_OTEVRIT = zANP
           + "Trezor nelze otevřít, budeš k němu potřebovat klíč.",
            
 zNENÍ_OTEVÍRATELNÝ = zANP + "Zadaný předmět není otevíratelný: ",
 
 zOTEVŘEL_SKŘÍŇKU =
         "Otevřel jsi skříňku ",
 

             
 zINFO_MRTVOLA = "Po prohledání mrtvoly pana Nováka jsi zjistil,\n"
             + "že byl nejspíše\n"
            + "otráven a zemřel před 5 hodinami.\n",
    
 zINFO_POCITAC = "Chvíli se hrabeš v počítači.\n"
          + "Díky počítači jsi zjistil, že v době umrtí se nacházeli v domě\n"
           + "pouze manželka pana Nováka a zahradník\n",         
             
 zNEVÍM_CO_PROHLEDAT = "Nevím, co mám prohledat.",        
     
 zNEVÍM_CO_ZAVŘÍT = zANP + "Nebylo zadáno, co se má zavřít",
zNENÍ_AKTUÁLNÍ_PROSTOREM =zANP+"Zavřít je možno pouze aktuální prostor"
             ,        
    
zZAVŘÍT_LZE_JEN_TREZOR = zANP
                             + "Jediným zavíratelným předmětem je trezor",  
     
zZAVŘEL_TREZOR =
        "Úspěšně jste zavřel trezor.",        
             
             
zODEMKNOUT =
         "Odemknul jsi trezor.",
 
 zOTEVREL_TREZOR = "Otevřel jsi trezor.\n"
      +"v trezoru jsi našel pristupova_karta, penize a dokumenty\n",
         
 
 zPOLOŽIT =
         "Rozhodl jste se podložit předmět ",
 
       
 zODEMKNUL_BEZPEČNOSTNÍ_MÍSTNOST=
         "\nOdemknul jsi bezpečnostní místnost.",
 
 zCHCE_PROHLEDNOUT_MANZELKA =
    "Na pohled jde vidět, že má prsty poleptané od nějaké kyseliny",
             
 zCHCE_PROHLEDNOUT_ZAHRADNIK =
    "Starý, na pohled milý pán. Vidím mu obvázanou, nejspíš pořezanou ruku",
             
 zCHCE_PROHLEDNOUT_SLUZKA = 
        "Vypadá smutně a vystrašeně. Nic zláštního jsem nezaregistroval.",
             
 
 zCHCE_OBVINIT_MANŽELKA =
         "Na základě důkazů chceš obvinit manželku pana Nováka.",
             
             
             
 zOBVINIT_SPRÁVNĚ = 
             "Gratuluji, obvinili jste správnou osobu a stal se z Vás\n"
             + "úspěšný detektiv.\n",       
             
 zOBVINIT_ŠPATNĚ = 
             "Obvinili jste špatnou osobu. Za špatné vyšetření případu\n"
             +"jste ztratil zaměstnaní.\n",
             
 
 zCHCE_PROHLEDAT_POCITAC =
            "Chvíli se hrabeš v počítači.\n"
          + "Díky počítači jsi zjistil, že v době umrtí se nacházeli v domě\n"
           + "pouze manželka pana Nováka a zahradník\n",
             
 zNEDOSTATEK_DUKAZU = "Nemáte ještě dostatek důkazů, abyste mohl někoho obvinit"
             ,
             
zOSOBA_TU_NENÍ = "Daná osoba se zde nenachází",
             
zNEVÍM_KOHO_OBVINIT = "Nevím, koho mám obvinit",
            
zNEVÍM_KOHO_PROHLEDNOUT = "Nevím, koho si mám prohlednout",
             
zPROHLEDNOUT_JEN_OSOBY = "Prohlédnout so můžu jen osoby",
             
zNEVÍM_CO_POLOŽIT = "Nevím, co mám položit",
             
zNEJDE_PROHLEDAT = "Daný prostor nejde prohledat",
             
zPOTŘEBA_KARTA = "Pro přistup k počítači potřebuješ kartu.",
             
zNIC = "Nic zajímavého tu není",
             
zNEVÍM_CO_ODEMKNOUT = "Nevím, co mám odemknout";       






//== VARIABLE CLASS ATTRIBUTES =================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

    /***************************************************************************
     * Vrátí řetězec obsahující zadané názvy oddělené čárkami.
     *
     * @param názvy Názvy, které je třeba sloučit
     * @return Výsledný řetězec ze sloučených zadaných názvů
     */
    static String cm(String... názvy)
    {
        String result = Arrays.stream(názvy)
                              .collect(Collectors.joining(", "));
        return result;
    }



//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /** Soukromý konstruktor zabraňující vytvoření instance.*/
    private Texts() {}


//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
