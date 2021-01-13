
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


import java.util.Map;
import java.util.HashMap;
/*******************************************************************************
 * Třída Batoh - představuje odkládací prostor hráče.
 * 
 * Třída implementuje metody umožňující odkládat věci do batohu, vypisovat je a odebírat je. 
 * 
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class Batoh
{
    private Map<String, Vec> veci; // věcí v batohu
    private static final int KAPACITA = 8; // maximální počet věcí, které se vlezou do batohu

    /***************************************************************************
     *  Konstruktor třídy Batoh
     */
    public Batoh()
    {
        veci = new HashMap<>();
    }

    /**
     * Metoda zjistí, zda je v batohu místo
     * 
     * @return true - batoh je volný | false - batoh je plný
     */
    public boolean jeBatohVolny()
    {
        if(veci.size() < KAPACITA)
        {
            return true;
        }
        return false;
    }
        
    /**
     * Metoda vloží věc, pokud je batoh volný a pokud je věc přenositelná
     * 
     * @return true - věc je přenositelná a batoh je volný | false - batoh je plný a nebo věc není přenositelná
     */
    public boolean vlozVec(Vec neco)
    {
        if(jeBatohVolny() && neco.isPrenositelna())
        {
            veci.put(neco.getNazev(),neco);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Metoda odstraní věc z batohu. Pokud je v batohu zadaná věc, smaže ji (používá se v příkazu Odhod)
     * 
     * @return vyhozena
     */
    public Vec vyhodVec(String nazev)
    {
        Vec vyhozena = null;
        if(veci.containsKey(nazev))
        {
            vyhozena = veci.get(nazev);
            veci.remove(nazev);
        }
        return vyhozena;
    }
    
    /**
     * Metoda zjistí, jaké věci jsou v batohu
     * 
     * @return seznam věcí v batohu
     */
    public String coJeVBatohu()
    {
        String vecicky = "";
        if(veci.isEmpty())
        {
            vecicky = "V batohu máš: Žádné věci tu nejsou";
        }
        else
        {
            vecicky = "V batohu máš: ";
            for(String nazev : veci.keySet())
            {
                vecicky += nazev + " ";
            }
        }
        return vecicky;
    }
    
    /**
     * Metoda najde věc, kterou chceme získat
     * 
     * @return věc
     */
    public Vec getVec(String nazev) {
        return veci.get(nazev);
    }
    
    /**
     * Meto zjistí, zda je v batohu věc (používá se v příkazu Dej)
     * 
     * @return true - batoh obsahuje věc | false - batoh neobsahuje věc
     */
    public boolean jeVBatohuVec(String nazev)
    {
        return veci.containsKey(nazev);
    }
    
    /**
     * Metoda zjistí kapacitu batohu
     * 
     * @return kapacita
     */
    public int getKapacitaBatohu()
    {
        return KAPACITA;
    }

}
