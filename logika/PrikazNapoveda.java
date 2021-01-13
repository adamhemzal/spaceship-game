package logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Adam Hemžal
 *@version    květen 2014/2015
 *  
 */
class PrikazNapoveda implements IPrikaz {  
    private static final String NAZEV = "napoveda";

     /**
    *  Konstruktor třídy
    *  
    *  @param platnePrikazy seznam příkazů,
    *                       které je možné ve hře použít,
    *                       aby je nápověda mohla zobrazit uživateli. 
    */    
    public PrikazNapoveda() 
    {
        // potřebuju konstruktor pro vytvoření příkazu Napověda;
    }
    
    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *  
     *  @return napoveda ke hre
     */
    @Override
    public String proved(String... parametry) {
        return "Tvým úkolem je zjistit, jaké předměty jsou nutné k opravě centrálního pohonu lodi, tedy motoru a generátoru, vložit je do lodi a zapnout.\n"+
        "K opravě budeš potřebovat 5 předmětů. Více se do lodi ani nevejde.\n"+
        "Dávej si ale pozor co vkládáš do lodi! \n"+
        "Protože to, co do lodi vložíš, už nejde vytáhnout zpátky!\n"+
        "Proto mluv se všemi postavami i několikrát. \n"+
        "Ony ti prozradí, jak opravit tvou loď.\n"+
        "Postavy se nachází v různých prostorech. \n"+
        "Některé prostory obsahují i předměty. \n"+
        "Ale hlavně předměty najdeš u postav. \n"+
        "Většina postav potřebuje pomoc - chce nějaký předmět.\n"+
        "Pokud jim dáš, to co chtějí, na oplátku ti poradí nebo ti dají předmět. \n"+
        "\n";
    }
    
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
      public String getNazev() {
        return NAZEV;
     }

}
