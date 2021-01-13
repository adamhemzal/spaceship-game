/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Třída PrikazObsahLodi implementuje příkaz obsahLodi pro hru. Příkaz vypíše vložené věci v lodi.
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class PrikazObsahLodi implements IPrikaz
{
    private static final String NAZEV = "obsahLodi";
    private HerniPlan plan;

    /***************************************************************************
     *  Konstruktor třídy
     */
    public PrikazObsahLodi(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     * Metoda vypíše seznam věcí vložených v lodi. hráč však musí být v prostoru spaceship,
     * aby příkaz fungoval.
     * 
     * @return seznam věcí v lodi
     */
    public String proved(String... parametry)
    {
        String odpoved = "";
        Prostor aktualni = plan.getAktualniProstor();
        if(aktualni.getNazev().equals("spaceship"))
        {
            Vec opraveneCasti = aktualni.hledejVec("centralniPohonLodi");
            odpoved = opraveneCasti.getVeci();
        }
        else
        {
            odpoved = "Nejsi v lodi, aby jsi mohl zkontrolovat vložené opravené části";
        }
        return odpoved;
    }
    
    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     * 
     * @return nazev prikazu
     */
    public String getNazev()
    {
        return NAZEV;
    }
}
