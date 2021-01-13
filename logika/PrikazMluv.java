/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Třída PrikazMluv implementuje příkaz mluv <postava> pro hru.
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class PrikazMluv implements IPrikaz
{
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    private Batoh batoh;

    /***************************************************************************
     *  Konstruktor třídy 
     */
    public PrikazMluv(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }
    
    /**
     * Metoda provede rozhovor se zadanou postavou, pokud je v prostoru. Jesli postava drží věc a nechce vymenovat,
     * tak se vloží předmět od postavy hráči do batohu.
     * 
     * @return postava promluví
     */
    public String proved(String... parametry)
    {
        String odpoved = "";
        if (parametry.length == 0) 
        {
            odpoved = "Nezadal jsi s kým chceš hovořit";
        }
        else
        {
            String jmeno = parametry[0];
            Prostor aktualni = plan.getAktualniProstor();   
            if(aktualni.jeVProstoruPostava(jmeno))
            {
                Postava postava = aktualni.hledejPostavu(jmeno);
                if(postava.getDrziVec() && !postava.getBudeVymenovat())
                {
                     batoh.vlozVec(postava.getDrzi());                     
                     odpoved = postava.getMluv();
                }
                else
                {
                     odpoved = postava.getMluv();
                }
            }
            else
            {
                odpoved = "Tato postava tady není \n";
            }
        }
        return odpoved;
    }

     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     * @return nazev prikazu
     */
    public String getNazev() 
    {
        return NAZEV;
    }
}
