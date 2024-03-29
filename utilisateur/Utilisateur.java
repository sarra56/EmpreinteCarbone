package utilisateur;
import consocarbone.*;
import java.util.*;
import java.io.*;

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

    /**Constructeur qui initialise un utilisateur à partir d'un fichier texte
    Les exceptions de type paramètres négatifs sont traitées dans chacun des constructeurs 
    @param file_path le chemin vers le fichier
    @throws IOException 
    @throws IllegalArgumentException si la classe Alimentation ou BienConso apparait plus d'une fois dans le fichier 
    @throws IllegalClassNameException si une classe inscrite sur le fichier n'est pas reconnue
    @throws CException si une classe énergétique inscrite sur le fichier n'est pas reconnue
    @throws TailleException si une taille de voiture inscrite sur le fichier n'est pas reconnue
    @throws MissingClassException si une classe n'apparait pas dans le fichier 
    */
    public Utilisateur(String file_path) throws IOException, IllegalArgumentException, IllegalClassNameException, CException, TailleException, MissingClassException{
        this.id = i++;
        this.empreinte = 0;
        boolean[] apparait = {false,false,false,false};
        int l = 1; //numero de ligne dans le fichier .txt
        try{
            File inFile = new File(file_path);
            System.out.println("Lecture du fichier " + file_path);
            BufferedReader reader = new BufferedReader(new FileReader(inFile)); 
            String ligne = reader.readLine();
            while (ligne != null){
                String[] mots = ligne.split(" ");
                String classe = mots[0];
                int i = Utilisateur.appartientA(classe);
                if (i >= 0){
                    if (i == 0){
                        if (apparait[i]==false){
                            apparait[i]=true;
                            double tx1 = Double.parseDouble(mots[2]);
                            double tx2 = Double.parseDouble(mots[3]);
                            Alimentation alimentation = new Alimentation(tx1,tx2);
                            this.alimentation = alimentation;
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                    }
                    if (i == 1){
                        if (apparait[i]==false){
                            apparait[i]=true;
                            double m = Double.parseDouble(mots[2]);
                            BienConso bienConso = new BienConso(m);
                            this.bienConso = bienConso;
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                    }
                    if (i == 2){
                        int s = Integer.parseInt(mots[2]);
                        CE ce = CE.A;
                        int cpt=0;
                        if (mots[3].equals("A")){
                            ce = CE.A;
                            cpt=1;
                        }
                        if (mots[3].equals("B")){
                            ce = CE.B;
                            cpt=1;
                        }
                        if (mots[3].equals("C")){
                            ce = CE.C;
                            cpt=1;
                        }
                        if (mots[3].equals("D")){
                            ce = CE.D;
                            cpt=1;
                        }
                        if (mots[3].equals("E")){
                            ce = CE.E;
                            cpt=1;
                        }
                        if (mots[3].equals("F")){
                            ce = CE.F;
                            cpt=1;
                        }
                        if (mots[3].equals("G")){
                            ce = CE.G;
                            cpt=1;
                        }
                        if (cpt == 0){
                            throw new CException();
                        }
                        Logement logement = new Logement(s,ce);
                        if (apparait[i]==false){
                            apparait[i]=true;
                            this.logement = logement;
                            this.colLogement = new LinkedList<Logement>();
                            this.colLogement.add(logement);
                        }   
                        else {
                            this.ajouterLogement(logement);
                        }
                    }
                    if (i == 3){
                        boolean p = true;
                        int cpt = 0;
                        if (mots[2].equals("false")){
                            p = false;
                        }
                        Taille t = Taille.P;
                        if (mots[3].equals("P")){
                            t = Taille.P;
                            cpt = 1;
                        }
                        if (mots[3].equals("G")){
                            t = Taille.G;
                            cpt = 1;
                        }
                        if (cpt == 0){
                            throw new TailleException();
                        }
                        int k = Integer.parseInt(mots[4]);
                        int a = Integer.parseInt(mots[5]);
                        Transport transport = new Transport(p,t,k,a);
                        if (apparait[i]==false){
                            apparait[i]=true;
                            this.transport = transport;
                            this.colTransport = new LinkedList<Transport>();
                            this.colTransport.add(transport);
                        }
                        else {
                            this.ajouterTransport(transport);
                        }
                    }
                }
                else{
                    throw new IllegalClassNameException();
                }
                ligne = reader.readLine();
                l++;
            }
            if (apparait[0] && apparait[1] && apparait[2] && apparait[3]){
                this.services = ServicesPublics.getInstance();
                this.empreinte = this.empreinte + this.calculerEmpreinte();
            }
            else{
                throw new MissingClassException();
            }
        }
        catch(MissingClassException m){
            System.out.println("Les classes suivantes n'apparaissent pas dans le fichier :\n");
            String[] classes = {"Alimentation","BienConso","Logement","Transport"};
            for(i=0; i<4; i++){
                if (apparait[i] == false){
                    System.out.println(classes[i]);
                }
            }
            m.printStackTrace();
        }
        catch(IllegalClassNameException e){
            System.out.println("Nom de classe non reconnu à la ligne "+ l);
            e.printStackTrace();
        }
        catch(CException c){
            System.out.println("Classe énergétique du logement non reconnue à la ligne "+ l); 
            c.printStackTrace();
        }
        catch(TailleException t){
            System.out.println("Taille du véhicule inscrite non reconnue à la ligne "+ l);
            t.printStackTrace();
        }
        catch(IOException o){
            System.out.println("Une erreur dans l'ouverture/lecture du fichier est apparue.Veuillez vous assurez du chemin entré.\n");
            o.printStackTrace();
        }
        catch(IllegalArgumentException a){
            System.out.println("La classe à la ligne "+l+" apparait plus d'une fois dans le fichier.\n");
            a.printStackTrace();
        }
    }

    public static int appartientA(String nom){
        String[] classes = {"Alimentation","BienConso","Logement","Transport"};
        for (int i =0; i<classes.length; i++){
            if (classes[i].equals(nom)) {
                return i;
            }
        }
        return -1;
    }

    public void ajouterLogement(Logement l){
        colLogement.add(l);
        this.empreinte = this.empreinte + l.getImpact();
    }

    public void ajouterTransport(Transport t){
        colTransport.add(t);
        this.empreinte = this.empreinte + t.getImpact();
    }
    /**Méthode qui permet de retirer un des logements de la collection de logements de l'utilisateur
    @param l le logement à retirer
    @throws IllegalArgumentxception si l n'est pas présent dans la collection de logements de l'utilisateur 
    */
    public void retirerLogement(Logement l) throws IllegalArgumentException{
        try{
            boolean sup = colLogement.remove(l);
            if (sup == true){
                this.empreinte = this.empreinte - l.getImpact();
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("Exception dans retirerLogement : l'objet que vous souhaitez retirer n'appartient pas à de la collection de logements !");
            e.printStackTrace();
        }
    }

    /**Méthode qui permet de retirer un des transports de la collection de transports de l'utilisateur
    @param t le transport à retirer
    @throws IllegalArgumentException si t n'est pas présent dans la collection de transports de l'utilisateur 
    */
    public void retirerTransport(Transport t) throws IllegalArgumentException{
        try{
            boolean sup = colTransport.remove(t);
            if (sup == true){
                this.empreinte = this.empreinte - t.getImpact();
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("Exception dans retirerTransport : l'objet que vous souhaitez retirer n'appartient pas à de la collection de transports !");
            e.printStackTrace();
        }
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