package logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková
 * @version   květen 2014/2015
 */
public class ProstorTest
{

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {		
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě");
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku");
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }
    
     @Test
    public void testVeci()
    {
        logika.Prostor prostor1 = new logika.Prostor("prostor", "ahoj");
        logika.Vec vec1 = new logika.Vec("A", true);
        logika.Vec vec2 = new logika.Vec("B", false);
        prostor1.vlozVec(vec1);
        prostor1.vlozVec(vec2);
        assertEquals(true, prostor1.jeVProstoruVec("A"));
        assertEquals(true, prostor1.jeVProstoruVec("B"));
        assertEquals(false, prostor1.jeVProstoruVec("C"));
        assertNotNull(prostor1.seberVec("A"));
        assertEquals(false, prostor1.jeVProstoruVec("A"));
        assertNull(prostor1.seberVec("B"));
        assertEquals(true, prostor1.jeVProstoruVec("B"));
        assertNull(prostor1.seberVec("C"));
    }

}
