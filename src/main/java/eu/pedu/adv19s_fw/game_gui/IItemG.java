/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.game_gui;

import eu.pedu.adv19s_fw.game_txt.IItem;



/*******************************************************************************
 * Instance interfejsu {@code IItemG} přestavují objekty v místnostech.
 * Předměty mohou být jak věci, tak i osoby či jiné skutečnosti (vůně,
 * světlo, fluidum, ...).
 * <p>
 * Některé z předmětů mohou charakterizovat stav místnosti (např. je rozsvíceno),
 * jiné mohou být určeny k tomu, aby je hráč "zvednul" a získal tím nějakou
 * schopnost či možnost projít nějakým kritickým místem hry
 * (např. klíč k odemknutí dveří).
 * <p>
 * Rozhodnete-li se použít ve hře předměty, které budou obsahovat jiné předměty,
 * (truhla, stůl, ...), můžete je definovat paralelně jako zvláštní druh
 * místnosti, do které se "vstupuje" např. příkazem "prozkoumej truhlu"
 * a která se opouští např. příkazem "zavři truhlu".
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public interface IItemG
         extends IItem
{
//\CC== CLASS (STATIC) CONSTANTS ===============================================
//\CM== CLASS (STATIC) METHODS =================================================



//##############################################################################
//\AG== ABSTRACT GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí odkaz na obrázek daného předmětu.
     *
     * @return  Požadovaný odkaz
     */
//    @Override
    public Icon getPicture()
    ;



//\AM== REMAINING ABSTRACT METHODS =============================================
//\DG== DEFAULT GETTERS AND SETTERS ============================================
//\DM== REMAINING DEFAULT METHODS ==============================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
