/*******************************************************************************
 * V balíčku <b>{@code eu.pedu.adv19s_fw}</b> je definován aplikační rámec
 * pro textové konverzační hry &ndash; adventury
 * vyvíjené v rámci semestrálního projektu úvodních kurzů programování
 * v letním semestru roku 2016.<br>
 * <br>
 * Balíček <b>{@code eu.pedu.adv19s_fw}</b>
 * obsahuje podbalíčky s následujícím obsahem:
 * <ul>
 *  <li>
 *    <b>{@code empty_classes}</b> &ndash;
 *    Základní kostry (výchozí verze) tříd, které mají studenti vytvořit.
 *    </li>
 *  <li>
 *    <b>{@code game_gui}</b> &ndash;
 *    Interfejsy, které mají být implementovány třídami v nadstavbové verzi
 *    doplňující původní hru o grafické uživatelské rozhraní.
 *    </li>
 *  <li>
 *    <b>{@code game_txt}</b> &ndash;
 *    Interfejsy, které mají být implementovány třídami ve výchozí verzi
 *    používající pouze textové uživatelské rozhraní.
 *    </li>
 *  <li>
 *    <b>{@code scenario}</b> &ndash;
 *    Výčtové typy a třídy, jejichž instance slouží k realizaci scénářů
 *    definujících průběh hry a ke správě těchto scénářů.
 *    </li>
 *  <li>
 *    <b>{@code test_util}</b> &ndash;
 *    Třídy sloužící k otestování jednotlivých součástí aplikace +
 *    ukázkové řešení.
 *    </li>
 *  <li>
 *    <b>{@code utilities}</b> &ndash;
 *    Pomocné třídy.
 *    </li>
 *  <li>
 * </ul>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
package eu.pedu.adv19s_fw;

//TODO Doplnit typ scénáře o demonstrační pro GUI
//TODO Doplnit implicitní GUI, které by bylo k dipozici jako knihovna a používalo by se v _Test_115
//TODO Doplnit test toho, zda hra vyhovuje novému rámci

//TODO Doplnit typy kroků testující nestandardní akce
//TODO Upravit pro ScenarioStep metodu equals aby nekontrolovala indexy a pořadí položek v poli

