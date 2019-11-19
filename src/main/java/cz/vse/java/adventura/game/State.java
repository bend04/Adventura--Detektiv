/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.game;



/*******************************************************************************
 * Knihovní třída {@code State} je schránkou na nejrůznější příznaky,
 * které si je potřeba v průběhu hry zapamatovat.

 *
 * @author  Dominik BENEŠ
 * @version 2019 léto
 */
public class State
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================

    /** Příznak, že má u sebe klíč. */
    private static boolean key;
   
/** Příznak, že má u sebe kartu */
    private static boolean card;
    
    /** Příznak, že je odemknut trezor. */
    private static boolean trezorOpened;
    
    /** Příznak, že prohledal mrtvolu. */
    private static boolean search1;
    
    /** Příznak, že prohledal počítač. */
    private static boolean search2;
    
    /** Příznak, že má u sebe důkaz. */
    private static boolean evidence;
    
    /** Příznak, že má důkaz z prohlednutí osob. */
    private static boolean evidenceInspect;
    
    private static boolean zvedaci;

   //##########################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================

 
   
    static boolean isKey()
    {
        return key;
    }


    
    static void setKey(boolean key)
    {
        State.key = key;
    }

    
    static boolean isCard()
    {
        return card;
    }


    
    static void setCard(boolean card)
    {
        State.card = card;
    }

    
    static boolean isTrezorOpened()
    {
        return trezorOpened;
    }


    
    static void setTrezorOpened(boolean trezorOpened)
    {
        State.trezorOpened = trezorOpened;
    }

    
    static boolean isSearch1()
    {
        return search1;
    }


    
    static void setSearch1(boolean search1)
    {
        State.search1 = search1;
    }

    
    static boolean isSearch2()
    {
        return search2;
    }


    
    static void setSearch2(boolean search2)
    {
        State.search2 = search2;
    }

       
    
    static boolean isEvidence()
    {
        return evidence;
    }


    
    static void setEvidence(boolean evidence)
    {
        State.evidence = evidence;
    }
    
    
    static boolean isEvidenceInspect()
    {
        return evidenceInspect;
    }


    
    static void setEvidenceInspect(boolean evidenceInspect)
    {
        State.evidenceInspect = evidenceInspect;
    }
      

    /***************************************************************************
     * Inicializuje všechny příznaky, které udržují informace
     * o aktuálním stavu hry a jejích součástí.
     */
    static void initialize()
    {
        key = false;
        card = false;
        search1 = false;
        search2 = false;
        evidence = false;
        evidenceInspect = false;
     
    }

    /**
     * Soukromý konstruktor zabra�?ující vytvoření instance.
     */
    private State()
    {
    }
}
