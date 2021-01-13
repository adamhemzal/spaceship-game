/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Třída PrikazPomoc implementuje příkaz pomoc pro hru.
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class PrikazPomoc implements IPrikaz
{
    private static final String NAZEV = "pomoc";
    private SeznamPrikazu platnePrikazy;

    /***************************************************************************
     *  Konstruktor třídy
     */
    public PrikazPomoc(SeznamPrikazu platnePrikazy)
    {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     * Metoda vypíše seznam příkazů, které můžeme ve hře provést
     * 
     * @return seznam příkazů
     */
    @Override
    public String proved(String... parametry) 
    {
        return "Příkazy, které můžeš použít, aby jsi prošel/a hru: "
        + platnePrikazy.vratNazvyPrikazu();
    }
    
    /**
     * Metoda vrací název příkazu 
     * 
     * @return název příkazu
     */
    @Override
    public String getNazev() 
    {
        return NAZEV;
    }

}
