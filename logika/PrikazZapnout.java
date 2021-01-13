/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Třída PrikazZapnout implementuje příkaz zapnout pro hru. Pokud hráč posbíral správné předměty - vyhrál. 
 * Pokud posbíral špatné - prohrál.
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class PrikazZapnout implements IPrikaz
{
    private static final String NAZEV = "zapnout";
    private HerniPlan plan;
    
    /***************************************************************************
     *  Konstruktor třídy
     */
    public PrikazZapnout(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     * Metoda nastaví podle podmínek konec hry.
     * Pokud hráč vložil správné součástky, hra nastaví true hodnotu pro vitezstvi
     * Pokud hráč vložil nesprávné součástky, nastaví true hodnotu pro prohru
     * 
     * @return vitezstvi nebo prohra
     */
    public String proved(String... parametry)
    {
        String odpoved = "";
        Prostor aktualni = plan.getAktualniProstor();
        
        if(aktualni.getNazev().equals("spaceship"))
        {
           Vec pohonLodi = aktualni.hledejVec("centralniPohonLodi"); 
           if(pohonLodi.getVeci().contains("stator") && pohonLodi.getVeci().contains("rotor") && 
              pohonLodi.getVeci().contains("komutator") && pohonLodi.getVeci().contains("TVG") && 
              pohonLodi.getVeci().contains("einsteinium"))
           {
               plan.setVitezstvi(true);
           }
           else
           {
             if(pohonLodi.mnozstviVeci() < pohonLodi.getKapacitaLodi())
             {
                 odpoved = "Musíš vložit 5 předmětů, aby jsi mohl zapnout loď. \n";
             }
             else
             {
                 if(pohonLodi.getVeci().contains("karburator") || pohonLodi.getVeci().contains("turbo") ||
                    pohonLodi.getVeci().contains("plutonium") || pohonLodi.getVeci().contains("5kreditu") ||
                    pohonLodi.getVeci().contains("blaster"))
                 {
                     plan.setProhra(true);
                 }
             }
           }
        }   
        else
        {
           odpoved = "Musíš být uvnitř lodi, aby jsi ji mohl zapnout.";
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
