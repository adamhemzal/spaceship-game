package logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Adam Hemžal
 *@version    květen 2014/2015
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private boolean vitezstvi;
    private boolean prohra;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        this.vitezstvi = false;
        this.prohra = false;
    }
    
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor spaceship = new Prostor("spaceship","Nacházíš se na své kosmické lodi. Vidíš zde spoustu nepořádku způsobeného tvrdým přistáním. \n");
        Prostor poust = new Prostor("poust", "Pošť je dnes vyjímečně klidná, ale horká. Člověk bez vody by zde nepřežil. V dáli vidíš dva nomády.\n");
        Prostor vesnice = new Prostor("vesnice","Vstupuješ do malinké vesničky uprostřed pouště. Kolem tebe domy uplácané z písku. Sám nevíš, jak to, že stojí. Dostáváš se až na náměstí.\n");
        Prostor spacemech = new Prostor("spacemech","Jedině ve SpaceMech nalezneš kvalitní materiály do robotů, lodí a tryskáčů.\n");
        Prostor hospoda = new Prostor("hospoda","Hospoda v mechanickém stylu. Za barem hospodský a za stolem štamgast. Vzadu vidíš hrát kapelu Cantina Band. Právě hrají tvou oblíbenou písničku.\n");
        Prostor novakovi = new Prostor("novakovi","Tohle je dům Nováků. Vstupuješ do předsíně a slyšíš, jak někdo sestupuje po schodech dolů.\n");
        Prostor skladka = new Prostor("skladka","Stojíš před mříží, která tě dělí od skutečného harampádí. Vlevo je malá budka a v ní vidíš někoho.\n");
        Prostor uvnitr_skladky = new Prostor("uvnitr_skladky","Všude kolem tebe je hromada starého materiálu, který by ti pomohl pomoci.\n");
        Prostor most = new Prostor("most","Na mostě celkem fouká. Vidíš nějakého mimozemšťana, jak sleduje vodní hladinu.\n");
        Prostor vodarna = new Prostor("vodarna","Uvnitř vodárny je strašný hluk. Sotva něco slyšíš. Zaznamenáš však, že někdo k tobě přichází.\n");
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        spaceship.setVychod(poust);
        
        poust.setVychod(vesnice);
        poust.setVychod(spaceship);
        
        vesnice.setVychod(spacemech);
        vesnice.setVychod(hospoda);
        vesnice.setVychod(novakovi);
        vesnice.setVychod(most);
        vesnice.setVychod(poust);
        vesnice.setVychod(skladka);
        
        hospoda.setVychod(vesnice);
        
        spacemech.setVychod(vesnice);
        
        skladka.setVychod(vesnice);
        skladka.setVychod(uvnitr_skladky);
        
        novakovi.setVychod(vesnice);
        
        most.setVychod(vesnice);
        most.setVychod(vodarna);
        
        vodarna.setVychod(most);
        
        uvnitr_skladky.setVychod(skladka);
        // Vkládání věcí do prostoru
        //****************************
        
        //Spaceship
        Vec centralniPohonLodi = new Vec("centralniPohonLodi", false);
        spaceship.vlozVec(centralniPohonLodi);
        
        Vec pet_kreditu = new Vec("5kreditu", true);
        spaceship.vlozVec(pet_kreditu);
        
        Vec padesat_kreditu = new Vec("50kreditu", true);
        spaceship.vlozVec(padesat_kreditu);
        
        Vec oko_robota = new Vec("okoRobota", true);
        spaceship.vlozVec(oko_robota);
        
        Vec zlato = new Vec("zlato", true);
        spaceship.vlozVec(zlato);
        
        Vec blaster = new Vec("blaster", true);
        spaceship.vlozVec(blaster);
        
        Vec draty = new Vec("draty", true);
        spaceship.vlozVec(draty);
        
        //Poust
        Vec turbo = new Vec("turbo", true);
        poust.vlozVec(turbo);
        
        //Uvnitr skládky
        Vec stator = new Vec("stator", true);
        uvnitr_skladky.vlozVec(stator);
        
        Vec komutator = new Vec("komutator", true);
        uvnitr_skladky.vlozVec(komutator);
        
        Vec karburator = new Vec("karburator", true);
        uvnitr_skladky.vlozVec(karburator); 
        
        //Věci, které vlastní postavy
        Vec plutonium = new Vec("plutonium", true);
        Vec rotor = new Vec("rotor", true);
        Vec pivo = new Vec("pivo", true);
        Vec vizitka = new Vec("vizitka", true);
        Vec einsteinium = new Vec("einsteinium", true);
        Vec tvg = new Vec("TVG", true); 
        
        // Vkládání postav do prostoru
        //***************************
        
        //Poust
        Postava Lando = new Postava("Lando","LANDO: Zdravím tě cizinče. \n" +
                                            "       Vítej na planetě Tatooine. \n"+
                                            "       Viděl jsem, jak tvá loď nekontrolovaně směřuje sem, ale naštěstí jsi nějak přistál. \n "+
                                            "       Předpokládám, že tě zachránil autopilot před smrtí. \n"+
                                            "       Podle toho kouře, který vycházel z tvé lodi tipuju, že se ti něco porouchalo. \n"+
                                            "       Kousek odtud je vesnice a tam najdeš potřebný materiál na opravu. \n"+
                                            "       Máme tady s Owenem spoustu materiálu a tak bych ti snad něco mohl dát. \n"+
                                            "       Možná se ti tohle bude hodit. \n"+
                                            "\n"+
                                            "LANDO ti daroval Plutonium. Plutonium bylo vloženo do batohu \n",
                                                      
                                           "LANDO: Ve vesnici najdeš potřebný materiál. Opatruj se.\n",
                                           "LANDO: Díky, ale mám zde spoustu věcí, které chci opravit. Na další věci zde už nemám místo.\n",
                                           plutonium
                                                      
                                            );
        poust.vlozPostavu(Lando);
        
        Postava Owen = new Postava("Owen","OWEN: Zdravím. \n" + 
                                          "      Podle rychlosti pádu a toho kouře, který šel z tvé lodi, tipuji, že ti selhal motor. \n"+ 
                                          "      Pokud ho budeš chtít opravit, budeš určitě potřebovat komutátor. \n",
                                                    
                                          "OWEN: Nemohu se ti věnovat. Potřebuji tady opravit motor robotovi, aby mohl dál fungovat. \n",
                                          "OWEN: Díky, ale nechci od tebe žádnou věc.\n"
                                          );
        poust.vlozPostavu(Owen);
        
        //Vesnice
        Postava starenka = new Postava("starenka", "STAŘENKA: Ahoj mládenče.\n"+
                                                   "         Copak od takové staré báby jako jsem já chceš?\n"+
                                                   "         Tebe nepoznávám. Musíš být nová tvář. Mohla bych ti něco o vesnici povědět. \n"+
                                                   "         Žiji zde od svého narození a za ten můj krátký život se toho změnilo \n"+
                                                   "         Původně vesnici tvořilo pár rodin, které se starali o harampádí, které opravovali a pak prodávali.\n"+
                                                   "         Dnes tu však žije daleko více lidí a ze skladiště harampádí se stal zdroj technického materiálu do těch vašich lodí a tryskáčů. \n"+
                                                   "         Většina zde žijících se už nevěnuje tolik opravám jako prodeji \n"+
                                                   "         Můj syn pracuje ve SpaceMechu jako prodavač, tak pokud bys potřeboval nějaký materiál, určitě bude něco mít. \n",
                                                   
                                                   "STAŘENKA: Měl by jsi navštívit mého syna. Je to skvělý obchodník. \n",
                                                   "STAŘENKA: Na stáří mi chceš darovat věc? Raději si ji nech, ty ji lépe využiješ. \n"
                                                   );
        vesnice.vlozPostavu(starenka);
        
        Postava kluk = new Postava("kluk", "KLUK: Proč ten tryskáč nechce naskočit?! \n"+
                                           "      Jejda! Pardon pane, nevšiml jsem si vás. \n"+
                                           "      Víte, chci vyhrát zdejší závod, a proto pracuji na svém tryskáči, ale nějak mi motor stále zlobí. \n"+
                                           "      Musím ho opravit, protože bez funkčního a dobrého motoru mám malou šanci vyhrát. \n"+
                                           "      Poslední závod jsem prohrál právě kvůli němu - uprostřed závodu se mi zasek a přestal fungovat. \n"+
                                           "      Při opravě jsem pak zjistil proč. Praskly mi dráty. Ale nějak nemůžu najít kvalitní dráty. \n",
                                           
                                           "KLUK: Díky moc za dráty. Pokud budeš chtít opravit motor, tak potřebuješ rotor a stator.\n"+ 
                                           "      Hmmm, ale na tu třetí část si nemůžu vzpomenout.\n",
                                           
                                           "KLUK: Neměl byste nějaké dráty u sebe? \n",
                                           
                                           "KLUK: Tenhle předmět nepotřebuji. \n",
                                           draty
                                           );
        vesnice.vlozPostavu(kluk);
        
        //Hospoda
        Postava hospodsky = new Postava("hospodsky","HOSPODSKÝ: Zdravím. \n"+
                                                     "          Mohu vám nabídnout jedině pivo Tooito. Tvrdé nám došlo \n"+
                                                     "          Pivo Tooito vyrábíme přímo zde ve vesnici a svou jedinečnou chuť má díky místní Vodárně \n"+
                                                     "          Vodárna totiž generuje tu nejlepší vodu v této galaktické soustavě. Dáte si teda pivo Tooito? \n",
                                                     
                                                     "HOSPODSKÝ: Bez vodárny bychom neměli tak dobré pivo. \n",
                                                     
                                                     "HOSPODSKÝ: Dáte si teda to pivo Tooito? \n",
                                                     
                                                     "HOSPODSKÝ: Co mi to proboha dáváte? Chcete mě uplatit? To se vám nepovede! \n",
                                                     
                                                     pet_kreditu,
                                                     pivo
                                                     );
        hospoda.vlozPostavu(hospodsky);
        
        Postava stamgast = new Postava("stamgast","ŠTAMGAST: Ahoj člověče. \n"+ 
                                                  "          Poslyš, pracuju ve vodárně, ale teď nemám směnu. \n"+
                                                  "          A když nemám směnu jdu sem, abych ochutnal pivko, na jehož výrobu pomáhám svýma vlastníma rukama. \n"+
                                                  "          Jo a mimochodem,, viděl jsem padat tvé letadlo. \n",
                                                  
                                                  "ŠTAMGAST: Díky za to pivo. \n"+
                                                  "          Ve vodárně pracuje ještě můj kolega. \n"+ 
                                                  "          Vizitka ho přemluví, aby s tebou komunikoval. \n"+ 
                                                  "          Mohl by mít něco, co se ti bude hodit. \n",
                                                  
                                                  "ŠTAMGAST: Mmáám sssuuchoo v krrkuuu. Pppottřeebuju se nnečeehoo napíít \n",
                                                  
                                                  "ŠTAMGAST: Nechci žádnou věc ani tvrdé. Chci něco, co mě propláchne a uhasí to sucho v mém hrdle. \n",
                                                  
                                                  pivo,
                                                  vizitka
                                                  );
        
        hospoda.vlozPostavu(stamgast);
        
        //SpaceMech
        
        Postava prodavac = new Postava("prodavac","PRODAVAČ: Dobrý den. Jistě vás zajímá jaké zboží zde máme. \n"+
                                                   "         Bohužel máme zde jen poslední kus rotoru za 50 kreditů. \n"+
                                                   "         Další materiál by měl přijít příští týden. \n",
                                                   
                                                   "PRODAVAČ: Díky, že jste u nás nakoupili. Nashledanou. \n",
                                                   
                                                   "PRODAVAČ: Rotor je za 50 kreditů. Je nezbytný k opravě motoru.\n",
                                                   
                                                   "PRODAVAČ: Přijímáme pouze kredity. Předměty ne. \n",
                                                   
                                                   padesat_kreditu,
                                                   rotor
                                                   );
        spacemech.vlozPostavu(prodavac);
        
        //Novákovi
        
        Postava novakova = new Postava("novakova","NOVÁKOVÁ: Panebože, vy nejste strážník! \n"+
                                                  "          Kdo jste a co tady pohledáváte?! \n"+
                                                  "          No, nevypadáte zrovna na zloděje. Tak tedy. \n"+
                                                  "          Před týdnem se nám někdo vloupal do domu. \n"+
                                                  "          Je to už po druhé za tento měsíc. \n"+
                                                  "          Naštěstí lupič nic neukradl, ale to se může při dalším vloupání změnit \n"+
                                                  "          Policie to zatím neřešila a já nehodlám dále čekat než mi zmizí všechny svoje cennosti! \n",
                                                  
                                                  "NOVÁKOVÁ: Jste báječný. Díky vám mohu vzít teď spravedlnost do svých rukou! \n"+
                                                  "          Už mi nikdo nebude šmejdit po domě! \n",
                                                  
                                                  "NOVÁKOVÁ: Zavolejte prosím strážníka.\n"+ 
                                                  "          Potřebuji s ním urychleně probrat tuto záležitost. \n"+
                                                  "          A nebo mi nějak pomocte, abych mohla vyřešit svou situaci sama! \n",
                                                  
                                                  "NOVÁKOVÁ: Jste šlechetný, ale nemohu od vás příjmout žádné dary. Můj manžel by to nesnesl. \n",
                                                  blaster,
                                                  einsteinium
                                                   );
        novakovi.vlozPostavu(novakova);
        
        //Skládka
        
        Postava spravce = new Postava("spravce","SPRÁVCE: Ty, ty, doufám, že nejsi zloděj! \n"+
                                                "         Už podruhé musím opravovat plot, protože ho někdo vystříhl. \n"+
                                                "         Zloději si odnesli dobrý kus materiálů, který opraví a pak prodají. \n"+
                                                "         Kdyby se se mnou domluvili, že potřebují trochu materiálu, tak je pustím. \n"+
                                                "         Ale oni ne a berou si harampádí ve velkém! \n"+
                                                "         A policie s tím nic neudělá. Pořídil jsem si proto robota, ale je k ničemu. \n"+
                                                "         Nehlídá a já nevím proč. \n",
                                                
                                                "SPRÁVCE: Jsi můj zachránce. Konečně můžu přistihnout zloděje při činnu. \n"+
                                                "         Jo a mimochodem. Pokud chceš závodit a hledáš, co do motoru, tak rozhodně stator.\n"+
                                                "         Stator je hlavní součástkou motoru.\n",
                                                
                                                "SPRÁVCE: Nevíš, proč ten robot nehlídá? \n",
                                                
                                                "SPRÁVCE: Nechci tvoje věci. Mám jich tu na skládce dost. \n",
                                                oko_robota
                                                 );
        skladka.vlozPostavu(spravce);
        
        //Most
        
        Postava mimozemstan = new Postava("mimozemstan","MIMOZEMŠŤAN: Zlato...zlato...Viděl jsi to ZLATO?!\n"+
                                                        "             Zahlédl jsem ve vodě zlato, ale nejsem si jistý. \n"+
                                                        "             Pokud tam je, tak bych ho chtěl, ale nemůžu se namočit. \n"+
                                                        "             Voda mi nedělá dobře. \n"+
                                                        "             Chci vyhrát závod, ale nemám peníze na tryskáč. To zlato by mi pomohlo \n",
                                                        
                                                        "MIMOZEMŠŤAN: Psst. Prozradím ti něco. \n"+
                                                        "             Byl jsem krást u Nováků. Nepovedlo se mi nic vzít, ale viděl jsem tam einsteinium. \n"+
                                                        "             Budeš ho potřebovat do generátoru. \n",
                                                        
                                                        "MIMOZEMŠŤAN: Zlato...zlato...zlato.\n",
                                                        "MIMOZEMŠŤAN: Zlato...zlato...zlato.\n",
                                                        zlato
                                                         );
        most.vlozPostavu(mimozemstan);
        
        //Vodárna
        
        Postava pracovnik = new Postava("pracovnik","PRACOVNÍK: Hej! Co tu pohledáváš?!\n",
                                                    "PRACOVNÍK: Aha. Tak to tě sem určitě poslal Pepa.\n"+
                                                    "           Musím se přiznat, že miluju sbírání věcí. \n"+
                                                    "           A to především TVGčka. Zbožňuju tuto věc opravovat.\n"+
                                                    "           Mimochodem TVG je hlavní součástí generátoru.\n"+
                                                    "           Společně s einsteiniem tvoří nerozlučitelnou dvojici.\n"+
                                                    "           Nebýt TVG a einsteinia, tak nefunguje vodárna a ani další v kosmické lodě.\n",
                                                    
                                                    "PRACOVNÍK: Hej! Co tu pohledáváš?!\n",
                                                    "PRACOVNÍK: Hej! Co tu pohledáváš?!\n",
                                                    vizitka,
                                                    tvg
                                                    );
        vodarna.vlozPostavu(pracovnik);        
        aktualniProstor = spaceship;  // hra začíná v domečku
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return   aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
         
    /**
     * Metoda vrací odkaz na viztezstvi
     * 
     * @return vitezstvi
     */
    public boolean jeVitezstvi()
    {
        return vitezstvi;
    }
    
    /**
     * Metoda nastaví hodnotu vítezství (používá se v příkazu Zapnout)
     * 
     * @return odkaz na vítežství, které se přepíše na true nebo false podle potřeby
     */
    public void setVitezstvi(boolean nyni)
    {
        this.vitezstvi = nyni;
    }
    
    
    
    /**
     * Metoda vrací odkaz na prohru
     * 
     * @return prohra
     */
    public boolean jeProhra()
    {
        return prohra;
    }
    
    /**
     * Metoda nastaví hodnotu prohry (používá se v příkazu Zapnout)
     * 
     * @return odkaz na prohru, která se přepíše na true nebo false podle potřeby
     */
    public void setProhra(boolean nyni)
    {
        this.prohra = nyni;
    }
}
