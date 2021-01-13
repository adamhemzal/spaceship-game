/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Map;
import java.util.HashMap;
/*******************************************************************************
 * Třída Vec - představuje vlastnosti věcí vyskytujících se ve hře.
 * 
 * Popisuje vlastnosti věcí (přenositelnost, název), ale také implementuje možnost vložit věci do jiné věci (kosmické lodi).
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class Vec
{
    private String nazev;
    private Map<String,Vec> veci; //věci v centrálním pohonu lodi
    private boolean prenositelna;
    private static final int KAPACITA = 5;

    /***************************************************************************
     *  Konstruktor třídy Vec
     */
    public Vec(String nazev, boolean prenositelna)
    {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.veci = new HashMap<>();
    }

    /**
     * Metoda vrací název věci
     * 
     * @returm název
     */
    public String getNazev()
    {
        return nazev;
    }
      
    /**
     * Metoda vrací přenositelnost věci
     * 
     * @return přenositelná - true nebo false
     */
    public boolean isPrenositelna()
    {
        return prenositelna;
    }
    
    /**
     * Metoda vloží věc do lodi (používá se v příkazu Vloz)
     * 
     * @param název něci, kterou cheme vložit
     * @return true - vloží se věc | false - nevloží se věc
     */
    public boolean vlozVec (Vec neco)
    {
        if(veci.size() < KAPACITA)
        {
            veci.put(neco.getNazev(), neco);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Metoda vrací počet věcí vložených v lodi
     * 
     * @return množství věcí
     */
    public int mnozstviVeci()
    {
        return veci.size();
    }
    
    /**
     * Metoda vrátí seznam věcí uložených v kosmické lodi (používá se v příkazu ObsahLodi)
     * 
     * @return věci v kosmické lodi
     */
    public String getVeci()
    {
        String getVeci = "";
        if(veci.isEmpty())
        {
            getVeci = "Do kosmické lodi jsi nic nevložil.\n";
        }
        else
        {
            getVeci = "Vložené opravné části: ";
            for(String neco : veci.keySet())
            {
                getVeci += neco + " ";
            }
        }
        return getVeci;    
    }
    
    /**
     * Metoda zjistí kapacitu centrálního pohonu lodi
     * 
     * @return kapacita
     */
    public int getKapacitaLodi()
    {
        return KAPACITA;
    } 

}
