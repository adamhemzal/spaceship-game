/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Třída PrikazVloz implementuje příkaz vloz <věc> pro hru. Příkaz vloží zadanou věc do lodi.
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class PrikazVloz implements IPrikaz
{
    private static final String NAZEV = "vloz";
    private HerniPlan plan;
    private Batoh batoh;

    /***************************************************************************
     *  Konstruktor třídy
     */
    public PrikazVloz(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     * Metoda umožní hráči vložit zadaný předmět z batohu do lodi. Hráč však musí být v prostoru spaceship,
     * aby mohl příkaz použít. 
     * 
     * @return zpráva o provedení příkazu. Vypíše jaký předmět byl vložen do lodi
     */
    public String proved(String... parametry)
    {
        String odpoved = "";
      
        if(parametry.length == 0)
        {
           odpoved = "Co chceš vložit? Zadej věc, kterou chceš vložit do centrálního pohonu lodi.\n";
        }
        else
        {
            String jmenoVeci = parametry[0];
            Prostor aktualni = plan.getAktualniProstor();
            if(aktualni.getNazev().equals("spaceship"))
            {
                if(batoh.jeVBatohuVec(jmenoVeci))
                {
                    Vec vkladana = batoh.getVec(jmenoVeci);
                    Vec centralniPohonLodi = aktualni.hledejVec("centralniPohonLodi");
                    if(centralniPohonLodi.vlozVec(vkladana))
                    {
                        batoh.vyhodVec(jmenoVeci);
                        odpoved = "Vložil jsi "+ jmenoVeci + " do centrálního pohonu lodi.\n";
                    }
                    else
                    {
                        odpoved = "Věc se nevejde do centálního pohonu lodi. \n";
                        batoh.vlozVec(vkladana);
                    }
                }
                else
                {
                    odpoved = "Takovou věc nemáš v batohu";
                }
            }
            else
            {
                odpoved = "Jak můžeš vložit věc do lodi, když jsi mimo ní? \n";
            }
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
