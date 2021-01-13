package uiText;


import java.util.Scanner;
import logika.IHra;
import java.io.*;
/**
 *  Class TextoveRozhrani
 * 
 *  Toto je uživatelského rozhraní aplikace Adventura
 *  Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *  Pokud chcete hrát tuto hru, vytvořte instanci této třídy
 *  a poté na ní vyvolejte metodu "hraj". 
 *  
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2014/2015
 */

public class TextoveRozhrani {
    private IHra hra;

    /**
     *  Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println(hra.vratEpilog());
    }
    
    /**
     * Metoda hrajZeSouboru umožňuje spustit hru s předdefinovaným průchodem 
     * Příkazy jsou v souboru .txt, každý na samostatném řádku
     * 
     * @param nazevSouboru - textový soubor jako parametr při spouštění
     */
    public void hrajZeSouboru(String soubor) 
    {
        try(BufferedReader cteni = new BufferedReader (new FileReader(soubor))) 
        {
            System.out.println(hra.vratUvitani());

            String radek = cteni.readLine();
            while (!hra.konecHry() && radek != null) {
                System.out.println("--> " + radek);
                System.out.println(hra.zpracujPrikaz(radek));
                radek = cteni.readLine();
            }

            System.out.println(hra.vratEpilog());
        }
        catch(FileNotFoundException e) {
            System.out.println("Soubor s příkazy nenalezen.");
            return;
        }
        catch(IOException e) {
            System.out.println("Chyba vstupu.");
            return;
        }
    }

    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

}
