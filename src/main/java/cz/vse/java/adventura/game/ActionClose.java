/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.java.adventura.game;



/*******************************************************************************
 * Instance třídy {@code ActionOpen} zpracovávají příkazy, které
 * mají za úkol zavřít nějaký předmět.
 * V dané hře je tímto předmětem vždy lednička v kuchyni,
 * ale obecně lze hru rozšířit o možnost otevření dalších předmětů
 * v dalších prostorech.
 *
 * @author  Dominik BENEŠ
 * @version 2019 léto
 */
class ActionClose extends AAction
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří instanci akce pro
     * zavření předmětu, který je aktuálním prostorem.
     * Po zavření se aktuálním prostorem stane prostor,
     * v němž je zavíraný předmět umístěn.
     */
    ActionClose()
    {
        super (Texts.ZAVRI,
               "Ověří, že hráč chce opravdu zavřít trezor,\n"
          + "zadaný jako parametr příkazu.\nJe-li tomu tak, tak to zavře a\n"
          + "přesune hráče do prostoru pracovna.\n"
          + "Nebude-li podmínka splněna, bude příkaz považovat za chybný");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

   
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return Texts.zNEVÍM_CO_ZAVŘÍT;
        }
        String      roomName = arguments[1];
        Dum  dum = Dum.getInstance();
        Room     currentRoom = dum.getCurrentPlace();
        if (! currentRoom.getName().equalsIgnoreCase(roomName)) {
            return Texts.zNENÍ_AKTUÁLNÍ_PROSTOREM;
        }
        if (! roomName.equalsIgnoreCase(Texts.TREZOR)) {
            return Texts.zZAVŘÍT_LZE_JEN_TREZOR;
        }
        Room newCurrent = dum.getORoom(Texts.PRACOVNA).get();
        Dum.getInstance().setCurrentPlace(newCurrent);
        return Texts.zZAVŘEL_TREZOR;
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
