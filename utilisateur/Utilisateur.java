package utilisateur;
import consocarbone.*;
import java.util.*;

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

    private List<Logement> colLogement;
    private List<Transport> colTransport;

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
        this.colLogement = new LinkedList<Logement>();
        this.colTransport = new LinkedList<Transport>();
        this.colLogement.add(logement);
        this.colTransport.add(transport);
    }

    public void ajouterLogement(Logement l){
        colLogement.add(l);
        this.empreinte = this.empreinte + l.getImpact();
    }

    public void ajouterTransport(Transport t){
        colTransport.add(t);
        this.empreinte = this.empreinte + t.getImpact();
    }

    public void retirerLogement(Logement l){ //exception a traiter : element qui n'appartient pas a la collection
        colLogement.remove(l);
        this.empreinte = this.empreinte - l.getImpact();
    }

    public void retirerTransport(Transport t){
        colTransport.remove(t);
        this.empreinte = this.empreinte - t.getImpact();
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

    public List<Logement> getColLogement(){
        return colLogement;
    }

    public List<Transport> getColTransport(){
        return colTransport;
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

    /**Méthode qui met à jour l'empreinte de l'utilisateur.*/
    public void majEmpreinte(){
        this.empreinte = this.alimentation.getImpact() + this.bienConso.getImpact() + this.services.getImpact() + this.empreinteColTransport() + this.empreinteColLogement();
    }

    /**Méthode qui affiche sur la console le détail de l'empreinte carbone de l'utilisateur*/
    public void detaillerEmpreinte(){
        System.out.println("\nDétail de l'empreinte de l'utilisateur identifié "+this.id+" :\n");
        System.out.println(this.alimentation.toString());
        System.out.println(this.bienConso.toString());
        System.out.println(this.services.toString());
        System.out.println("Empreinte totale de la collection de logements : "+this.empreinteColLogement()+".Détail par logement :");
        for (Logement l : colLogement){
            System.out.println(l.toString());
        }
        System.out.println("Empreinte totale de la collection de transports : "+this.empreinteColTransport()+".Détail par transport :");
        for (Transport t : colTransport){
            System.out.println(t.toString());
        }
        System.out.println("\nEmpreinte total : "+this.empreinte+"\n");
    }
    
    /**Méthode qui ordonne les consommations carbone de l'utilisateur et les préesentent*/
    public void detaillerEmpreinteOrdonne(){
        List<ConsoCarbone> listeConso = new ArrayList<ConsoCarbone>();
        listeConso.add(this.alimentation);
	    listeConso.add(this.bienConso);
        listeConso.add(this.services);
        for (Logement l : colLogement){
            listeConso.add(l);
        }
        for (Transport t : colTransport){
            listeConso.add(t);
        }
        Collections.sort(listeConso);
        System.out.println("\nDétail de l'empreinte de l'utilisateur identifié "+this.id+" par odre croissant :");
	    for(int i=0; i<listeConso.size(); i++) {
            System.out.println(listeConso.get(i).toString());
	    }
	    System.out.println("Conseils pour un mode de vie plus durable :\nConcernant les transport, préférez les déplacements à pieds, en vélo ou en transports en commun lorque cela est possible, et achetez un véhicule qui consomme moins.\nConcernant l'alimentation, mangez local, le moins de produits provenant d'animaux possible, et évitez au maximum le gaspillage.\nConcernant le logement : optimisez le chauffage, contrôlez l'étanchéité des ouvertures, choisissez bien votre électroménager, économisez l'eau et éclairez de manière raisonnée.\nConcernant les biens de consommation : choisissez des marques écoresponsables, achetez des produits utiles et qui vont durer dans le temps.\n");
    }

    /**Méthode qui calcule l'empreinte carbone de la collection de logements de l'utilisateur.
    @return la valeur de l'empreinte carbone
    */
    public double empreinteColLogement(){
        double empreinteTotale = 0;
        for (Logement l : colLogement){
            empreinteTotale = empreinteTotale + l.getImpact();
        }
        return empreinteTotale;
    }

    /**Méthode qui calcule l'empreinte carbone de la collection de transports de l'utilisateur.
    @return la valeur de l'empreinte carbone
    */
    public double empreinteColTransport(){
        double empreinteTotale = 0;
        for (Transport t : colTransport){
            empreinteTotale = empreinteTotale + t.getImpact();
        }
        return empreinteTotale;
    }

}