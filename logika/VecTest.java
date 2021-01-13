/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code VecTest} slouží ke komplexnímu otestování
 * třídy {@link VecTest}.
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class VecTest
{
    private Vec lod;

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        lod = new Vec("centralniPohonLodi", false);
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Testuje přenositelnost věci
     */
    @Test
    public void testPrenositelna()
    {
        Vec vec1 = new Vec("křeslo", false);
        assertEquals(false, vec1.isPrenositelna());
    }
    
    @Test
    public void testVlozvec()
    {
        Vec vec1 = new Vec("banan", true);
        Vec vec2 = new Vec("křeslo", false);
        assertEquals(true, lod.vlozVec(vec1));
        assertEquals(true, lod.vlozVec(vec2));
    }
    
    /**
     * Testuje kapacitu lodi
     */
    @Test
    public void testKapacitaLodi()
    {
        Vec vec1 = new Vec("salat1", true);
        Vec vec2 = new Vec("salat2", true);
        Vec vec3 = new Vec("salat3", true);
        Vec vec4 = new Vec("salat4", true);
        Vec vec5 = new Vec("salat5", true);
        Vec vec6 = new Vec("salat6", true);
        assertEquals(true, lod.vlozVec(vec1));
        assertEquals(true, lod.vlozVec(vec2));
        assertEquals(true, lod.vlozVec(vec3));
        assertEquals(true, lod.vlozVec(vec4));
        assertEquals(true, lod.vlozVec(vec5));
        assertEquals(false, lod.vlozVec(vec6));
    }

}
