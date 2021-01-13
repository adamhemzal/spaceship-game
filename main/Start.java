/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package main;



import logika.*;
import uiText.TextoveRozhrani;

/*******************************************************************************
 * Třída {@code Start} je hlavní třídou projektu,
 * který ...
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class Start // public final - deklaruješ, že třída nemá potomka
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        
        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        if(args.length == 0)
        {
            ui.hraj();
        }
        else
        {
            ui.hrajZeSouboru(args[0]);
        }
    }
    
    private Start(){} // vypneš konstruktor
}
