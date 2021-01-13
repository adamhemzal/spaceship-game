/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Třída PrikazOdhod implementuje příkaz odhod <věc> pro hru.
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class PrikazOdhod implements IPrikaz
{
    private static final String NAZEV = "odhod";
    private HerniPlan plan;
    private Batoh batoh;

    /***************************************************************************
     * Konstruktor třídy
     * 
     * @param batoh, plan
     */
    public PrikazOdhod(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }
    
    /**
     * Metoda umožní hráči vyhodit věc z batohu. 
     * Pokud hráč vyhodí věc z batohu, objeví se tato věc v prostoru
     * 
     * @return zpráva o provedení příkazu. Vypíše jakou věc hráč vyhodil z batohu
     */
    public String proved(String... parametry)
    {
        String odpoved = "";
        if(parametry.length == 0)
        {
            odpoved = "Zadej, jakou věc chceš vyhodit.\n";
        }
        else
        {
            String nazevVeci = parametry[0];
            Prostor aktualni = plan.getAktualniProstor();
            Vec vec = batoh.vyhodVec(nazevVeci);
            if(vec == null)
            {
                odpoved = "Nemůžeš vyhodit věc, která není v batohu.\n";
            }
            else
            {
                batoh.vyhodVec(nazevVeci);
                aktualni.vlozVec(vec);
                odpoved = "Vyhodil jsi "+ vec.getNazev() +" z batohu.\n";
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
