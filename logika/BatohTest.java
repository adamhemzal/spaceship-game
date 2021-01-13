/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování třídy Batoh
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class BatohTest
{
    private Batoh batoh1;

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        batoh1 = new Batoh();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {     
    }

    /**
     * Testuje metodu vlozVec
     */
    @Test
    public void testVlozVec()
    {
        Vec vec1 = new Vec("pomeranc", true);
        assertEquals(true, batoh1.vlozVec(vec1));
        assertEquals(true, batoh1.jeVBatohuVec("pomeranc"));
        assertEquals(false, batoh1.jeVBatohuVec("jablko"));
    }
    
    /**
     * Testuje metodu vyhodVec
     */
    @Test
    public void testVyhodVec()
    {
        Vec vec1 = new Vec("brokolice", true);
        assertEquals(true, batoh1.vlozVec(vec1));
        assertEquals(vec1, batoh1.vyhodVec("brokolice"));
        assertEquals(false, batoh1.jeVBatohuVec("brokolice"));
        
    }
    
    /**
     * Testuje zda se vloží nepřenositelna věc
     */
    @Test
    public void testVlozeniNeprenositelnaVeci()
    {
        Vec vec1 = new Vec("parlament", false);
        assertEquals(false, batoh1.vlozVec(vec1));
    }
    
    /**
     * Testuje kapacitu batohu, která je 8. Při deváté věci se věc nevloží
     */
    @Test
    public void testKapacityBatohu()
    {
        Vec vec1 = new Vec("salat1", true);
        Vec vec2 = new Vec("salat2", true);
        Vec vec3 = new Vec("salat3", true);
        Vec vec4 = new Vec("salat4", true);
        Vec vec5 = new Vec("salat5", true);
        Vec vec6 = new Vec("salat6", true);
        Vec vec7 = new Vec("salat7", true);
        Vec vec8 = new Vec("salat8", true);
        Vec vec9 = new Vec("salat9", true);
        assertEquals(true, batoh1.vlozVec(vec1));
        assertEquals(true, batoh1.vlozVec(vec2));
        assertEquals(true, batoh1.vlozVec(vec3));
        assertEquals(true, batoh1.vlozVec(vec4));
        assertEquals(true, batoh1.vlozVec(vec5));
        assertEquals(true, batoh1.vlozVec(vec6));
        assertEquals(true, batoh1.vlozVec(vec7));
        assertEquals(true, batoh1.vlozVec(vec8));
        assertEquals(false, batoh1.vlozVec(vec9));
        assertEquals(false, batoh1.jeBatohVolny());

    }
    
}
