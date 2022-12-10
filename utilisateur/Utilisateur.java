package utilisateur;
import consocarbone.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**Classe qui contient, pour chaque utilisateur, ces informations concernant chaque poste de consommation.
@author Watrin Claire
@author Ouhmidou Sarra
*/
public class Utilisateur{

    private static int i=1;
    private double empreinte;
    private int id;

    private Alimentation alimentation;
    private BienConso bienConso;
    private Logement logement;
    private Transport transport;
    private ServicesPublics services;

    private Collection<Logement> ColLogement;
    private Collection<Transport> ColTransport;

    /**Constructeur par défaut*/
    public Utilisateur(){
        this.id = i++;
    }

    /**Constructeur qui prend en argument toutes les informations de consommation carbone de l'utilisateur
    @param alimentation une instance de Alimentation qui correspond à son régime alimentaire 
    @param bienConso une instance de BienConso qui correspond à ses biens de consommation
    @param logement une instance de Logement qui correspond à son logement
    @param transport une instance de Transport qui correspond à son mode de transport
    @param services l'unique instance de ServicesPublics 
    */
    public Utilisateur(Alimentation alimentation, BienConso bienConso, Logement logement,Transport transport,ServicesPublics services){
        this.id = i++;
        this.alimentation = alimentation;
        this.bienConso = bienConso;
        this.logement = logement;
        this.transport = transport;
        this.services = services;
        this.empreinte = this.calculerEmpreinte();
    }

    public Utilisateur(Alimentation alimentation, BienConso bienConso, Collection<Logement> colLogement, Transport transport,ServicesPublics services){
        this.id = i++;
        this.alimentation = alimentation;
        this.bienConso = bienConso;
        this.transport = transport;
        this.services = services;
        this.colLogement = colLogement;
        this.empreinte = this.calculerEmpreinte();
        this.empreinte++(empreinteColLogement);
    }

    public Utilisateur(Alimentation alimentation, BienConso bienConso, Logement logement, Collection<Transport> colTransport,ServicesPublics services){
        this.id = i++;
        this.alimentation = alimentation;
        this.bienConso = bienConso;
        this.logement = logement;
        this.services = services;
        this.colTransport = colTransport;
        this.empreinte = this.calculerEmpreinte();
        this.empreinte++(empreinteColTransport);
    }

    public Utilisateur(Alimentation alimentation, BienConso bienConso, Logement logement, Collection<Transport> colTransport,ServicesPublics services){
        this.id = i++;
        this.alimentation = alimentation;
        this.bienConso = bienConso;
        this.logement = logement;
        this.services = services;
        this.colLogement = colLogement;
        this.colTransport = colTransport;
        this.empreinte = this.calculerEmpreinte();
        this.empreinte++(empreinteColLogement);
        this.empreinte++(empreinteColTransport);
    }
 
    public void setAlimentation(Alimentation a) {
        alimentation = a;
        alimentation.majImpact();
        empreinte = calculerEmpreinte();
    }
 
    public void setBienConso(BienConso b) {
	    bienConso = b;
        bienConso.majImpact();
        empreinte = calculerEmpreinte();
    }
 
    public void setLogement(Logement l) {
	    logement = l;
        logement.majImpact();
        empreinte = calculerEmpreinte();
    }
 
    public void setTransport(Transport t) {
	    transport = t;
        transport.majImpact();
        empreinte = calculerEmpreinte();
    }
 
    public void setServices(ServicesPublics s) {
	    services = s;
        services.majImpact();
        empreinte = calculerEmpreinte();
    }
 
    public Alimentation getAlimentation() {
	    return this.alimentation;
    }
 
    public BienConso getBienConso() {
	    return this.bienConso;
    }
 
    public Logement getLogement() {
	    return this.logement;
    }
 
    public Transport getTransport() {
	    return this.transport;
    }
 
    public ServicesPublics getServices() {
	    return this.services;
    }

    public Collection<Logement> getColLogement(){
        return this.colLogement;
    }

    public Collection<Transport> getColTransport(){
        return this.colTransport;
    }

    public double getEmpreinte(){
        return this.empreinte;
    }

    public int getId(){
        return this.id;
    }

    /**Méthode qui calcule l'empreinte carbone de l'utilisateur.
    @return la valeur de l'empreinte carbone
    */
    public double calculerEmpreinte(){
        return (this.alimentation.getImpact() + this.bienConso.getImpact() + this.logement.getImpact() + this.transport.getImpact() + this.services.getImpact());
    }

    /**Méthode qui affiche sur la console le détail de l'empreinte carbone de l'utilisateur*/
    public void detaillerEmpreinte(){
        System.out.println("Détail de l'empreinte de l'utilisateur identifié "+this.id+" :\n");
        System.out.println(this.alimentation.toString());
        System.out.println(this.bienConso.toString());
        System.out.println(this.logement.toString());
        System.out.println(this.transport.toString());
        System.out.println(this.services.toString());
        System.out.println("\nEmpreinte total : "+this.empreinte+"\n");
    }
    
    /**Méthode qui ordonne les consommations carbone de l'utilisateur et les préesentent*/
    public void detaillerEmpreinteOrdonne(){
        List<ConsoCarbone> listeConso = new ArrayList<ConsoCarbone>();
        listeConso.add(this.alimentation);
	    listeConso.add(this.bienConso);
	    listeConso.add(this.logement);
	    listeConso.add(this.transport);
	    listeConso.add(this.services);
        Collections.sort(listeConso);
        System.out.println("Détail de l'empreinte de l'utilisateur identifié "+this.id+" par odre croissant : \n");
	    for(int i=0; i<listeConso.size(); i++) {
            System.out.println(listeConso.get(i).toString());
	    }
	    System.out.println("Conseils pour un mode de vie plus durable :\nConcernant les transport, préférez les déplacements à pieds, en vélo ou en transports en commun lorque cela est possible, et achetez un véhicule qui consomme moins.\nConcernant l'alimentation, mangez local, le moins de produits provenant d'animaux possible, et évitez au maximum le gaspillage.\nConcernant le logement : optimisez le chauffage, contrôlez l'étanchéité des ouvertures, choisissez bien votre électroménager, économisez l'eau et éclairez de manière raisonnée.\nConcernant les biens de consommation : choisissez des marques écoresponsables, achetez des produits utiles et qui vont durer dans le temps.\n");
    }

    public double empreinteColLogement(){
        Iterator<Logement> it = colLogement.iterator();
        double empreinteTotale = 0;
        while(it.hasNext()){
            Logement l = it.next();
            empreinteTotale++(l.getImpact());
        }
        return empreinteTotale;
    }

    public double empreinteColTransport(){
        Iterator<Transport> it = colTransport.iterator();
        double empreinteTotale = 0;
        while(it.hasNext()){
            Transport t = it.next();
            empreinteTotale++(l.getImpact());
        }
        return empreinteTotale;
    }

}