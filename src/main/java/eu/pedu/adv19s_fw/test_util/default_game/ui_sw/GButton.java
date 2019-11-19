/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.test_util.default_game.ui_sw;

import eu.pedu.adv19s_fw.game_gui.IItemG;
import eu.pedu.adv19s_fw.game_txt.INamed;
import eu.pedu.adv19s_fw.game_gui.Icon;

import java.awt.event.ActionEvent;
import javax.swing.JButton;



/*******************************************************************************
 * Instance třídy {@code GButton} představují tlačítka
 * sloužící k zobrazení jim zadaného pojmenovaného objektu.
 * <br><br>
 * Vylepšení změnilo předka z {@code JLabel} na {@code JButton} a
 * přidalo dva další konstruktory s parametrem specifikujícím název příkazu.
 * Tím umožnilo definovat definovat prvky jako tlačítka, po jejichž stisku
 * se provede příslušná akce.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
@SuppressWarnings("serial")
final class GButton extends JButton
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Název zobrazovaného prvku. */
    private final String name;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří komponentu zobrazující název zadaného h-objektu
     * spolu s jeho obrázkem.
     *
     * @param item     Zobrazovaný h-objekt
     * @param command  Příkaz, který se zadá po stisku tlačítka
     * @param gui      GUI, jehož prostřednictvím ovládáme hru
     */
    GButton(IItemG item, String command, IMyGUI gui)
    {
        this(item, null, item.getPicture(), command, gui);
        setToolTipText(name);
    }


    /***************************************************************************
     * Vytvoří komponentu zobrazující název zadaného pojemnovaného objektu
     * (většinou místnosti).
     *
     * @param object  Zobrazovaný objekt
     * @param command Příkaz, který se zadá po stisku tlačítka
     * @param gui     GUI, jehož prostřednictvím ovládáme hru
     */
    GButton(INamed object, String command, IMyGUI gui)
    {
        this(object, object.getName(), null, command, gui);
    }


    /***************************************************************************
     * Vytvoří komponentu zobrazující název zadaného pojemnovaného objektu
     * (většinou místnosti).
     *
     * @param object  Zobrazovaný objekt
     * @param name    Jméno zobrazovaného objektu
     * @param icon    Obraz zobrazovaného prvku
     * @param command Příkaz, který se zadá po stisku tlačítka
     * @param gui     GUI, jehož prostřednictvím ovládáme hru
     */
    private GButton(INamed object, String name, Icon icon,
                           final String command, final IMyGUI gui)
    {
        super(name, icon);
        this.name = object.getName();

        addActionListener((ActionEvent e) -> {
            gui.executeCommand(command + " " + this.name);
        });
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Vrátí název zobrazovaného objetku.
     *
     * @return Název zobrazovaného objetku
     */
    @Override
    public String getName()
    {
        return name;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
