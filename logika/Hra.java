package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Adam Hemžal
 *@version    květen 2014/2015
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Batoh batoh;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        batoh = new Batoh();
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda());
        platnePrikazy.vlozPrikaz(new PrikazPomoc(platnePrikazy));
        
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan, batoh)); // poslední krok k přidání příkazu
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazBatoh(batoh));
        platnePrikazy.vlozPrikaz(new PrikazOdhod(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazDej(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazVloz(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazObsahLodi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZapnout(herniPlan));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return 
        "\n"+
        "Při cestě na tvou domovskou planetu byla tvá vesmírná loď zasažena nepřátelskou lodí. \n"+
        "V tu chvíli jsi letěl kolem planety Tatooine. \n"+ 
        "Vlivem nízkého kyslíku a tlaku jsi upadl do bezvědomí. \n"+
        "Probouzíš se v kabině své lodi uprostřed pouště. \n"+
        "Díky pilotnímu robotovi tvoje loď přistála bezpečně a ty jsi přežil. \n"+
        "Zjišťuješ, že zásah ti zničil centrální pohon lodi, který je složen z generátoru a motoru. \n"+
        "Tvé lokalizační zařízení ti ukázalo, že se nacházíš kousek od vesnice. \n"+
        "Tvým úkolem je zjistit, jaké díly budeš potřebovat k opravě lodi, opravit loď a vydat se zpátky na cestu domů. \n"+
        "\n"+
        "Pokud si nevíš rady napiš 'pomoc' a pokud chceš zopakovat hlavní úkol napiš 'napoveda' \n"+
        "******************************************************************************************** \n"+
        "\n"+
        herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Doufám, že se ti hra líbila. Ahoj příště. \n";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu); // IPrikaz 
            textKVypsani = prikaz.proved(parametry);
            if(herniPlan.jeVitezstvi())
            {
                konecHry = true;
                textKVypsani="******************************************************\n"+
                "Tvá kosmická loď úspěšně nastartovala. \n"+
                "Posadíš se a připoutáš se. \n"+
                "Štíty zapnuty, motory běží, zahlásí tvůj pilotní robot.\n"+
                "Na palubní desce nastavíš směr letu - tvou domovskou planetu.\n"+
                "Kosmická loď se pomalu zvedá ze země a ty si prohlížíš zmenšující se poušť pod tebou.\n"+
                "Najednou se pilotní robot oznámí rychlost světla připravena.\n"+
                "Zmáčkneš tlačítko a a stejně rychle jako světlo opouštíš tento kout galaxie.\n"+
                "Míříš domů.\n"+
                "\n"+
                "VYHRÁL JSI.\n"+
                "\n";
            }
            else
            {
                if(herniPlan.jeProhra())
                {
                    konecHry = true;
                    textKVypsani="****************************************\n"+
                    "Centální pohon lodi se zapnul.\n"+
                    "Najednou se z pohonu lodi zajiskřilo a celá loď se ponořila do tmy.\n"+
                    "Začínáš cítit kouř spálených součástek a uvědomuješ si, že domů nepojedeš.\n"+
                    "\n"+
                    "PROHRÁL JSI. NEPOSBÍRAL JSI SPRÁVNÉ SOUČÁSTKY.\n"+
                    "\n";
                }
            }
        }
        else {
            textKVypsani="Tento příkaz neznám. Zkus ho napsat znovu.";
        }
        
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) 
    {
        this.konecHry = konecHry;
    }
    
    /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
    public HerniPlan getHerniPlan(){
       return herniPlan;
    }
    
    /**
     * Metoda vrátí odkaz na aktuální batoh. Používá se v testu
     * 
     * @return batoh
     */
    public Batoh getBatoh()
    {
        return batoh;
    }
    
}

