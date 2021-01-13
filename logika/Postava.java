/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Třída Postava - představuje postavy a jejich vlastnosti.
 * 
 * Třída implementuje vlastnosti různých typů postav. Tyto postavy mohou držet předměty, vyměňovat je s hráčem,
 * darovat je hráčí, chtít určité předměty. Dále implementuje řeč - co postava řekne, pokud hráč s ní mluví poprvé,pokud nechce nabízenou věc,
 * a podobně.
 *
 * @author    Adam Hemžal
 * @version   květen 2014/2015
 */
public class Postava
{
    private String jmeno;
    private String proslovPred; //proslov pred vymenou
    private String proslovPo; //proslov po vymene
    private String recChce; //co řekne, když nabizenou vec chce
    private String recNechce; //co řekne, kdyz nabizenou vec nechce
    private Vec chce; // věc, kterou chce postava
    private Vec drzi; //věc, kterou dostane hráč po výměně (věc, kterou postava má)
    private boolean probehlaVymena;
    private boolean probehlRozhovor;
    private boolean budeVymenovat;
    private boolean drziVec;
    
    /***************************************************************************
     *  Konstruktor Postavy, která vlastní předmět a vyměňuje ho.
     *
     */
    public Postava(String jmeno, String proslovPred, String proslovPo, String recChce, String recNechce, Vec chce, Vec drzi)
    {
        this.jmeno = jmeno;
        this.proslovPred = proslovPred;
        this.proslovPo = proslovPo;
        this.recChce = recChce;
        this.recNechce = recNechce;
        this.chce = chce;
        this.drzi = drzi;
        this.probehlRozhovor = false;
        this.probehlaVymena = false;
        this.budeVymenovat = true;
        this.drziVec = true;
    }
        
     /**
     *  Konstruktor Postavy, která drží věc, ale nechce vyměňovat - pouze daruje
     *
     */
    public Postava(String jmeno, String proslovPred, String proslovPo, String recNechce, Vec drzi)
    {
        this.jmeno = jmeno;
        this.proslovPred = proslovPred;
        this.proslovPo = proslovPo;
        this.recNechce = recNechce;
        this.drzi = drzi;
        this.probehlRozhovor = false;
        this.probehlaVymena = true;
        this.budeVymenovat = false;
        this.drziVec = true;
    }
    
     /**
     *  Konstruktor Postavy, která chce věc, ale nedrží
     *
     */
    public Postava(String jmeno, String proslovPred, String proslovPo, String recChce, String recNechce, Vec chce)
    {
        this.jmeno = jmeno;
        this.proslovPred = proslovPred;
        this.proslovPo = proslovPo;
        this.recChce = recChce;
        this.recNechce = recNechce;
        this.chce = chce;
        this.probehlRozhovor = false;
        this.probehlaVymena = false;
        this.budeVymenovat = true;
        this.drziVec = false;
    }
    
     /**
     *  Konstruktor Postavy, která jenom mluví
     *
     */
    public Postava(String jmeno, String proslovPred, String proslovPo, String recNechce)
    {
        this.jmeno = jmeno;
        this.proslovPred = proslovPred;
        this.proslovPo = proslovPo;
        this.recNechce = recNechce;
        this.probehlRozhovor = false;
        this.probehlaVymena = true;
        this.budeVymenovat = false;
        this.drziVec = false;
    }
    
    /**
     * Metoda vrací jméno postavy
     * 
     * @return - jméno
     */
    public String getJmeno()
    {
        return jmeno;
    }

    /**
     * Metoda (používá se v příkazu Mluv)
     * 
     * @return věc, kterou postava má u sebe
     */
    public Vec getDrzi()
    {
        return drzi;
    }
    
    /**
     * Metoda vrací věc, kterou postava chce
     * 
     * @return věc, kterou postava chce
     */
    public Vec getChce()
    {
        return chce;
    }
    
    
    /**
     * Metoda vrací hodnotu, zda postava bude vyměňovat nebo ne (slouží pro příkaz Dej)
     * 
     * @return true | false
     */
    public boolean getBudeVymenovat()
    {
        return budeVymenovat;
    }
    
    /**
     * Metoda vrací hodnotu true/false podle toho, jestli postava nějakou věc drží (slouží pro příkaz Mluv)
     * 
     * @return true | false
     */
    public boolean getDrziVec()
    {
        return drziVec;
    }
    
    
    /**
     * Metoda vrací odkaz na řeč, ve které postava prozradí, co chce
     * 
     * @return řeč - co postava chce
     */    
    public String getRecChce()
    {
        return recChce;
    }
    
    /**
     * Metoda vrací odkaz na řeč, kterou postava pronese, pokud nabízený předmět nechce
     * 
     * @return řeč - co postava nechce
     */
    public String getRecNechce()
    {
        return recNechce;
    }
    
    /**
     * Metoda popisuje chování postav, pokud se na ně opakovaně mluví a pokud proběhla výměna předmětů.
     * 
     * @return  pokud neproběhl rozhovor - vrátí první proslov
     *          pokud rozhovor proběhl ale neproběhla výměna - vrátí co postava chce
     *          pokud proběhla výměna a rozhovor - vrátí druhý proslov
     */
    public String getMluv()
    {
        if(!probehlRozhovor)
        {
            probehlRozhovor = true;
            return proslovPred;
        }
        
        if(!probehlaVymena)
        {
            return recChce;
        }
        return proslovPo;
    }
    
     /**
     * Metoda nastavuje zda proběhla výměna předmětů mezi hráčem a postavou
     * 
     * @return nastaví proměnou probehlaVymena na true nebo false podle podmínek
     */
    public void setProbehlaVymena(boolean probehla) 
    {
        this.probehlaVymena = probehla;
    }
 
}
