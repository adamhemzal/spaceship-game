/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Třída PrikazBatoh implementuje příkaz batoh pro hru. Vypisuje obsah batohu hráče.
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class PrikazBatoh implements IPrikaz
{
    private static final String NAZEV = "batoh";
    private Batoh batoh;
    
    /***************************************************************************
     * Konstruktor třídy 
     */
    public PrikazBatoh(Batoh batoh)
    {
        this.batoh = batoh;
    }

    /**
     * Metoda vrací seznam věcí, které jsou uloženy v batohu
     * 
     * @return obsah batohu
     */
    public String proved(String... parametry)
    {
        return batoh.coJeVBatohu();
    }
    
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev()
    {
        return NAZEV;
    }
}
