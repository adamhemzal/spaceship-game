/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazSeber implementuje příkaz seber <vec> z prostoru pro hru.
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class PrikazSeber implements IPrikaz
{
    private static final String NAZEV = "seber"; //klíčové slovo je seber, 
    // vytvářím pouze jeden herní plán!
    private HerniPlan plan;
    private Batoh batoh;

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazSeber(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }
    
    /**
     *  Metoda pro provedení příkazu ve hře.
     *  Počet parametrů je závislý na konkrétním příkazu,
     *  např. příkazy konec a napoveda nemají parametry
     *  příkazy jdi, seber, polož mají jeden parametr
     *  příkaz pouzij může mít dva parametry.
     *  
     *  @param parametry počet parametrů závisí na konkrétním příkazu.
     *  
     */
    public String proved(String... parametry)
    {
        String odpoved = ""; 
        if(parametry.length == 0) //někdo napíše jenom seber | délka pole = 0
        {
            odpoved = "Co mám sebrat? Zadej název věci. \n";
        }
        else // řešíme ostatní varianty 
        {
            String nazevVeci = parametry[0]; // název toho, co chci sebrat
            Prostor aktualni = plan.getAktualniProstor(); // kde ji hledám
            if(aktualni.jeVProstoruVec(nazevVeci)/*==true*/)
            {
                Vec sbirana = aktualni.seberVec(nazevVeci); // vec je momentálně v pomocné proměnne sbirana
                if(sbirana==null) // ptám se, zda se mi vec vrátí (pouze v případe, že tam je a je penositelna)
                {
                    odpoved = "Tato věc se nedá vzít. \n";
                }
                else 
                {
                    if(batoh.vlozVec(sbirana))
                    {
                        odpoved = "Vložil jsi " + sbirana.getNazev() + " do batohu. \n";
                    }
                    else
                    {
                        odpoved = "Věc se nevejde do batohu. Batoh je plný. \n"; // ted by se nevratila
                        aktualni.vlozVec(sbirana); // vložíš zpátky do prostoru
                    }
                }
            }
            else
            {
                odpoved = "Tahle věc tady není \n";
            }
        }
        return odpoved;
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
