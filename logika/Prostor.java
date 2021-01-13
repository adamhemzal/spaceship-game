package logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author  Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Adam Hemžal
 * @version květen 2014/2015
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci; //nebo seznamveci
    private Map<String, Postava> seznamPostav;
    
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        seznamPostav = new HashMap<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v prostoru: " + nazev + "\n " + popis + "\n"
                + popisPostav()+"\n"
                + popisVeci()+"\n"
                + popisVychodu()+"\n";
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "Východy: ";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        if (nazevSouseda == null) {
            return null;
        }
        for (Prostor sousedni : vychody) {
            if (sousedni.getNazev().equals(nazevSouseda)) {
                return sousedni;
            }
        }
        return null;  // prostor nenalezen
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() { // na dokreslení navíc, hodí se na grafické rozhraní
        return Collections.unmodifiableCollection(vychody);
    }
    
    
    
    // VĚCI V PROSTORU //
    
    
    /**
     * Metoda vloží věc do prostoru (používá se v příkazu Seber)
     * 
     * @param něco
     * @return vloží věc něco do prostoru
     */
    public void vlozVec(Vec neco) //neda se otestovat
    {
        veci.put(neco.getNazev(),neco);
    }
    
    /**
     * Metoda vrátí seznam věcí v prostoru
     * 
     * @return seznam věcí v prostoru
     */
    private String popisVeci() // private protože máme public dlouhy popis
    {
        String popisVeci = "";
        if(veci.isEmpty())
        {
            popisVeci = "Věci: Žádné věci tu nejsou";
        }
        else 
        {
            popisVeci = "Věci: ";
            for(String nazev : veci.keySet())
            {
                popisVeci += nazev + " | ";
            }
        }
        return popisVeci;
        // mapa je prázdna - isEmpty
    }
    
    /**
     * Metoda odstraní věc z místnosti (používá se v příkazu Seber)
     * 
     * @return odstraněná věc
     */
    public Vec seberVec(String nazev)
    {
        Vec odebirana = veci.remove(nazev); //metoda remove - pokud existuje klic, tak ho odebere
        if(odebirana != null && !odebirana.isPrenositelna())
        {
            veci.put(odebirana.getNazev(), odebirana);
            odebirana = null;
        }
        return odebirana;
    }
    
    /**
     * Metoda zjistí, zda je věc v prostoru (používá se v příkazu Seber)
     * 
     * @param název věci
     * @return true - obsahuje věc | false - neobsahuje věc
     * 
     */
    public boolean jeVProstoruVec(String nazev)
    {
        return veci.containsKey(nazev);
    }
    
    /**
     * Metoda vrací název hledané věci v prostoru (používá se v příkazu Vloz)
     * 
     * @param náztev věci, kterou hledáme v prostoru
     * @return hledaná věc
     */
    public Vec hledejVec(String nazev)
    {
      return veci.get(nazev);  
    }
    
    
    // POSTAVY V PROSTORU//
    
    
    /**
     * Metoda vloží postavu do prostoru
     * 
     * @param postava
     * @return vloží zadanou postavu do prostoru
     */
    public void vlozPostavu(Postava postava)
    {
        seznamPostav.put(postava.getJmeno(),postava); 
    }
    
    /**
     * Metoda zjisí, zda je postava v prostoru (používá se v příkazu Mluv)
     * 
     * @param jméno postavy
     * @return true - je v prostoru | false - není v prostoru
     */
    public boolean jeVProstoruPostava(String jmeno)
    {
        return seznamPostav.containsKey(jmeno);
    }
    
    /**
     * Metoda nalezne jmnéno postavy (používá se v příkazu Mluv a Dej)
     * 
     * @param jméno postavy
     * @return ze seznamu postav získáme jméno postavy
     */
    public Postava hledejPostavu(String jmeno)
    {
        return seznamPostav.get(jmeno);
    }
    
    /**
     * Metoda vypíše názvy postav v prostoru
     * 
     * @return seznam postav v prostoru
     */
    private String popisPostav()
    {
        String popisPostav = "";
        if(seznamPostav.isEmpty())
        {
            popisPostav = "Postavy: Žádné postavy tu nejsou";
        }
        else 
        {
            popisPostav = "Postavy: ";
            for(String jmeno : seznamPostav.keySet())
            {
                popisPostav += jmeno + " ";
            }
        }
        return popisPostav;
        
    }
    
}
