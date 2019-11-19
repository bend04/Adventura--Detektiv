/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv19s_fw.utilities;

import java.io.IOException;
import java.io.OutputStream;



/*******************************************************************************
 * Instance třídy {@code MultiOutput} představuje proud sdružující sadu
 * proudů, které se navenek tváří jako jeden proud.
 * Každá operace se vždy provede s každým se sdružených proudů.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2018-Winter
 */
public class MultiOutput extends OutputStream
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

    /** Výstupní proudy, do nichž se bude paralelně zapisovat. */
    private final OutputStream[] thisStreams;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří nový proud zastupující skupinu zadaných sdružovaných proudů.
     *
     * @param streams Sdružované proudy
     */
    public MultiOutput(OutputStream... streams)
    {
        thisStreams = streams;
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Zavře všechny sdružované proudy.
     *
     * @throws IOException Případná výjimka vyhozená při zavírání
     */
    @Override
    public void close() throws IOException
    {
        for (OutputStream os : thisStreams) {
            os.close();
        }
    }


    /***************************************************************************
     * Spláchne všechny sdružované proudy.
     *
     * @throws IOException Případná výjimka vyhozená při splachování
     */
    @Override
    public void flush() throws IOException
    {
        for (OutputStream os : thisStreams) {
            os.flush();
        }
    }


    /***************************************************************************
     * Zapíše do sdružených proudů zadané pole znaků.
     *
     * @param b Zapisované znaky
     * @throws IOException Případná výjimka vyhozená při zápisu
     */
    @Override
    public void write(byte[] b) throws IOException
    {
        for (OutputStream os : thisStreams) {
            os.write(b);
        }
    }


    /***************************************************************************
     * Writes <code>len</code> bytes from the specified byte array
     * starting at offset <code>off</code> to this output stream.
     * The general contract for <code>write(b, off, len)</code> is that
     * some of the bytes in the array <code>b</code> are written to the
     * output stream in order; element <code>b[off]</code> is the first
     * byte written and <code>b[off+len-1]</code> is the last byte written
     * by this operation.
     * <p>
     * The <code>write</code> method of <code>OutputStream</code> calls
     * the write method of one argument on each of the bytes to be
     * written out. Subclasses are encouraged to override this method and
     * provide a more efficient implementation.
     * <p>
     * If <code>b</code> is <code>null</code>, a
     * <code>NullPointerException</code> is thrown.
     * <p>
     * If <code>off</code> is negative, or <code>len</code> is negative, or
     * <code>off+len</code> is greater than the length of the array
     * <code>b</code>, then an <tt>IndexOutOfBoundsException</tt> is thrown.
     *
     * @param      b     the data.
     * @param      off   the start offset in the data.
     * @param      len   the number of bytes to write.
     * @exception  IOException  if an I/O error occurs. In particular,
     *             an <code>IOException</code> is thrown if the output
     *             stream is closed.
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException
    {
        for (OutputStream os : thisStreams) {
            os.write(b, off, len);
        }
    }


    /***************************************************************************
     * Zapíše zadaný znak do všech sdružených proudů.
     *
     * @param b Kód zapisovaného znaku
     * @throws IOException Případná výjimka vyhozená při zápisu
     */
    @Override
    public void write(int b) throws IOException
    {
        for (OutputStream os : thisStreams) {
            os.write(b);
        }
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
