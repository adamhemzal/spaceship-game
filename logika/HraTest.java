package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2014/2015
 */
public class HraTest {
    private Hra hra1;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHryVyhra() {
        assertEquals("spaceship", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("50kreditu"));
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("5kreditu"));
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("okoRobota"));
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("blaster"));
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("centralniPohonLodi"));
        hra1.zpracujPrikaz("seber 50kreditu");
        hra1.zpracujPrikaz("seber 5kreditu");
        hra1.zpracujPrikaz("seber okoRobota");
        hra1.zpracujPrikaz("seber blaster");
        hra1.zpracujPrikaz("seber centralniPohonLodi");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("okoRobota"));
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("okoRobota"));
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("centralniPohonLodi"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi poust");
        assertEquals("poust", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("mluv Owen");
        hra1.zpracujPrikaz("mluv Lando");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("plutonium"));
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("mluv starenka");
        hra1.zpracujPrikaz("mluv kluk");
        hra1.zpracujPrikaz("jdi spacemech");
        assertEquals("spacemech", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej 50kreditu prodavac");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("rotor"));
        assertEquals(false, hra1.getBatoh().jeVBatohuVec("50kreditu"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi hospoda");
        assertEquals("hospoda", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej 5kreditu hospodsky");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("pivo"));
        assertEquals(false, hra1.getBatoh().jeVBatohuVec("5kreditu"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("dej pivo stamgast");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("vizitka"));
        assertEquals(false, hra1.getBatoh().jeVBatohuVec("pivo"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi skladka");
        assertEquals("skladka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej okoRobota spravce");
        assertEquals(false, hra1.getBatoh().jeVBatohuVec("okoRobota"));
        hra1.zpracujPrikaz("jdi uvnitr_skladky");
        assertEquals("uvnitr_skladky", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("komutator"));
        hra1.zpracujPrikaz("seber komutator");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("komutator"));
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("komutator"));
        assertEquals(false, hra1.konecHry());
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("stator"));
        hra1.zpracujPrikaz("seber stator");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("stator"));
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("stator"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi skladka");
        assertEquals("skladka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi novakovi");
        assertEquals("novakovi", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("mluv novakova");
        hra1.zpracujPrikaz("dej blaster novakova");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("einsteinium"));
        assertEquals(false, hra1.getBatoh().jeVBatohuVec("blaster"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi most");
        assertEquals("most", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi vodarna");
        assertEquals("vodarna", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej vizitka pracovnik");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("TVG"));
        assertEquals(false, hra1.getBatoh().jeVBatohuVec("vizitka"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi most");
        assertEquals("most", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi poust");
        assertEquals("poust", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi spaceship");
        assertEquals("spaceship", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("vloz rotor");
        hra1.zpracujPrikaz("vloz stator");
        hra1.zpracujPrikaz("vloz komutator");
        hra1.zpracujPrikaz("vloz einsteinium");
        hra1.zpracujPrikaz("vloz TVG");
        hra1.zpracujPrikaz("zapnout");
        assertEquals(true, hra1.konecHry());

        // příkaz konec
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }
    
    @Test
    public void testPrubehHryProhra() {
        assertEquals("spaceship", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("50kreditu"));
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("5kreditu"));
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("okoRobota"));
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("blaster"));
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("centralniPohonLodi"));
        hra1.zpracujPrikaz("seber 50kreditu");
        hra1.zpracujPrikaz("seber 5kreditu");
        hra1.zpracujPrikaz("seber okoRobota");
        hra1.zpracujPrikaz("seber blaster");
        hra1.zpracujPrikaz("seber centralniPohonLodi");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("okoRobota"));
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("okoRobota"));
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("centralniPohonLodi"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi poust");
        assertEquals("poust", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("mluv Owen");
        hra1.zpracujPrikaz("mluv Lando");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("plutonium"));
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("mluv starenka");
        hra1.zpracujPrikaz("mluv kluk");
        hra1.zpracujPrikaz("jdi spacemech");
        assertEquals("spacemech", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej 50kreditu prodavac");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("rotor"));
        assertEquals(false, hra1.getBatoh().jeVBatohuVec("50kreditu"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi hospoda");
        assertEquals("hospoda", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej 5kreditu hospodsky");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("pivo"));
        assertEquals(false, hra1.getBatoh().jeVBatohuVec("5kreditu"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("dej pivo stamgast");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("vizitka"));
        assertEquals(false, hra1.getBatoh().jeVBatohuVec("pivo"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi skladka");
        assertEquals("skladka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej okoRobota spravce");
        assertEquals(false, hra1.getBatoh().jeVBatohuVec("okoRobota"));
        hra1.zpracujPrikaz("jdi uvnitr_skladky");
        assertEquals("uvnitr_skladky", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("komutator"));
        hra1.zpracujPrikaz("seber komutator");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("komutator"));
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("komutator"));
        assertEquals(false, hra1.konecHry());
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("stator"));
        hra1.zpracujPrikaz("seber stator");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("stator"));
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().jeVProstoruVec("stator"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi skladka");
        assertEquals("skladka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi novakovi");
        assertEquals("novakovi", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("mluv novakova");
        hra1.zpracujPrikaz("dej blaster novakova");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("einsteinium"));
        assertEquals(false, hra1.getBatoh().jeVBatohuVec("blaster"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi most");
        assertEquals("most", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi vodarna");
        assertEquals("vodarna", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej vizitka pracovnik");
        assertEquals(true, hra1.getBatoh().jeVBatohuVec("TVG"));
        assertEquals(false, hra1.getBatoh().jeVBatohuVec("vizitka"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi most");
        assertEquals("most", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi poust");
        assertEquals("poust", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi spaceship");
        assertEquals("spaceship", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("vloz rotor");
        hra1.zpracujPrikaz("vloz stator");
        hra1.zpracujPrikaz("vloz komutator");
        hra1.zpracujPrikaz("vloz plutonium");
        hra1.zpracujPrikaz("vloz TVG");
        hra1.zpracujPrikaz("zapnout");
        assertEquals(true, hra1.konecHry());


        // příkaz konec
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }
    
}
