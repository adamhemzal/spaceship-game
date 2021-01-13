/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Třída PrikazDej implementuje příkaz dej <věc> <postava> pro hru.
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class PrikazDej implements IPrikaz
{
    private static final String NAZEV = "dej";
    private HerniPlan plan;
    private Batoh batoh;

    /***************************************************************************
     *  Konstruktor třídy
     *  
     *  @param plan, batoh
     */
    public PrikazDej(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     * Metoda umožní hráči dát předmět z batohu postavě. Metoda kontroluje, zda se postava nachází v aktuálním prostoru,
     * poté zda má hráč v batohu vyměňovanou věc, zda chce postava vyměňovat, zda postava chce nabízenou věc a zda postava věc drží.
     * 
     * @return zpráva o provedení příkazu. Vypíše komu byl dán předmět a jaký předmět.
     */
    public String proved(String... parametry)
    {
        String odpoved = "";
        if(parametry.length == 0)
        {
            odpoved = "Nevím, co mám dát. Musíš uvést název věci.";
        }
        else
        { 
            if(parametry.length == 1)
            {
               odpoved = "Musis zadat jeste jeden parametr";
            }
            else
            {
                String nazevVeci = parametry[0];
                String jmenoPostavy = parametry[1];
                Prostor aktualni = plan.getAktualniProstor();
                Postava kdo = aktualni.hledejPostavu(jmenoPostavy);
                Vec nabizena = batoh.getVec(nazevVeci);
                
                if(aktualni.jeVProstoruPostava(jmenoPostavy))
                {
                    if(batoh.jeVBatohuVec(nazevVeci))
                    {
                        if(kdo.getBudeVymenovat())
                        {
                            if(kdo.getChce().equals(nabizena))
                            { 
                                if(kdo.getDrziVec())
                                {
                                    batoh.vyhodVec(nazevVeci);
                                    batoh.vlozVec(kdo.getDrzi());
                                    kdo.setProbehlaVymena(true);
                                    odpoved = "Dal jsi "+ kdo.getJmeno() + " předmět "+ 
                                         nabizena.getNazev() + ".\n" + kdo.getJmeno() + " ti dal " 
                                         +kdo.getDrzi().getNazev() + ".\n"+
                                         kdo.getDrzi().getNazev()+ " byl vložen do batohu. \n";  
                                         
                                    batoh.vyhodVec(nazevVeci);
                                    kdo.setProbehlaVymena(true);
                                    odpoved = "Dal jsi "+ kdo.getJmeno() + " předmět " + 
                                             nabizena.getNazev() + "\n";
                                 
                                } 
                                else
                                {
                                    batoh.vyhodVec(nazevVeci);
                                    kdo.setProbehlaVymena(true);
                                    odpoved = "Dal jsi "+ kdo.getJmeno() + " předmět " + 
                                             nabizena.getNazev() + "\n"; 
                                }
                            }
                            else
                            {
                                odpoved = kdo.getRecNechce();
                            }

                        }
                        else
                        {
                            odpoved = kdo.getRecNechce();
                        }
                    }
                    else
                    {
                        odpoved = "Zadanou věc nemáš v batohu. \n";
                    }
                }
                else
                {
                    odpoved = "Zadaná postava se v prostoru nenachází. \n";
                }
            }
        }
        return odpoved;
    }
    
    /**
     * Metoda vrátí název příkazu (slovo, které se používá při jejím volání)
     * 
     * @return název příkazu
     */
    public String getNazev()
    {
        return NAZEV;
    }

}
