package consocarbone;
import java.util.*;
import java.io.*;
import java.util.Scanner;

/**Classe représentant le poste de consommation carbone : Transport individuel.
@author Watrin Claire
@author Ouhmidou Sarra
*/
public class Transport extends ConsoCarbone{
    private boolean possede;
    private Taille taille;
    private int kilomAnnee;
    private int amortissement;

    public Transport(){}

    /**Constructeur qui prend en argument les informations d'un véhicule
    @param possede booléen qui indique si l'utilisateur possede un véhicule ou non
    @param taille la taille du véhicule
    @param kilomAnnee le nombre de kilomètres parcourus par année
    @param amortissement la durée d'amortissement du véhicule
    @throws IllegalArgumentException si le nombre de kilomètres saisi ou l'amortissement sont strictement négatifs
    */
    public Transport(boolean possede, Taille taille, int kilomAnnee, int amortissement) throws IllegalArgumentException{
        int i = 0;
        try{
            if (possede == false){
                this.possede = false;
                this.impact = 0;
            }
            else{
                if(kilomAnnee<0 && amortissement<0){
                    i = 1;
                    throw new IllegalArgumentException("");
                }
                if(amortissement<0){
                    i = 2;
                    throw new IllegalArgumentException("");
                }
                if(kilomAnnee<0){
                    i = 3;
                    throw new IllegalArgumentException("");
                }
                else{
                    this.possede=possede;
                    this.taille=taille;
                    this.kilomAnnee=kilomAnnee;
                    this.amortissement=amortissement;
                    this.impact=(kilomAnnee*0.000193)+((taille.getEmission())/amortissement);
                }
            }
        }
        catch(IllegalArgumentException e){
            this.possede = possede;
            this.taille = taille;
            int k2;
            int amortissement2;
            if (i == 1){
                System.out.println("Exception dans le constructeur de la classe Transport : le nombre de kilomètres et l'amortissement doivent être positifs !\n");
                Scanner sc = new Scanner(System.in);
                System.out.println("Veuillez saisir à nouveau le nombre de km desiré (respectant les conditions mentionnées) :\n");
                k2 = sc.nextInt();
                System.out.println("Veuillez saisir à nouveau l'amortissement desiré (respectant les conditions mentionnées) :\n");
		        amortissement2 = sc.nextInt();
		        this.kilomAnnee=k2;
                this.amortissement=amortissement2;
            }
            if (i == 2){
                System.out.println("Exception dans le constructeur de la classe Transport : l'amortissement doit être positif !\n");
                Scanner sc = new Scanner(System.in);
                System.out.println("Veuillez saisir à nouveau l'amortissement desiré (respectant les conditions mentionnées) :\n");
		        amortissement2 = sc.nextInt();
		        this.amortissement=amortissement2;
            }
            if (i == 3){
                System.out.println("Exception dans le constructeur de la classe Transport : le nombre de km doit être positif !\n");
                Scanner sc = new Scanner(System.in);
                System.out.println("Veuillez saisir à nouveau le nombre de km desiré (respectant les conditions mentionnées) :\n");
		        k2 = sc.nextInt();
		        this.kilomAnnee=k2;
            }
            this.impact=(kilomAnnee*0.000193)+((taille.getEmission())/amortissement);
        }
    }

    public boolean getPossede(){
        return this.possede;
    }

    public Taille getTaille(){
        return this.taille;
    }

    public int getKilomAnnee(){
        return this.kilomAnnee;
    }

    public int getAmortissement(){
        return this.amortissement;
    }

    public void setPossede(boolean possede){
        this.possede=possede;
        if (possede == false){
            this.impact=0;
        }
        else{
            this.majImpact();
        }
    }

    public void setTaille(Taille taille){
        this.taille=taille;
        this.majImpact();
    }

    /**Setter sur le nombre de kilomètres parcourus par année
    @param kilomAnnee le nombre de kilomètres par année désiré
    @throws IllegalArgumentException si le nombre de kilomètres par année désiré est strictement négatif
    */
    public void setKilomAnnee(int kilomAnnee) throws IllegalArgumentException{
        try{
            if (kilomAnnee < 0){
                throw new IllegalArgumentException("");
            }
            else{
                this.kilomAnnee=kilomAnnee;
                this.majImpact();
            }
        }
        catch(IllegalArgumentException i){
            int k2;
            System.out.println("Exception dans le setter du km de la classe Transport : le nombre de km doit être positif !\n");
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir à nouveau le nombre de km desiré (respectant les conditions mentionnées) :\n");
		    k2 = sc.nextInt();
		    this.kilomAnnee=k2;
            this.majImpact();
        }
    }

    /**Setter sur l'amortissement du véhicule
    @param amortissement l'amortissement désiré
    @throws IllegalArgumentException si l'amortissement désiré est strictement négatif
    */
    public void setAmortissement(int amortissement) throws IllegalArgumentException{
        try{
            if (amortissement < 0){
                throw new IllegalArgumentException("");
            }
            else{
                this.amortissement=amortissement;
                this.majImpact();
            }
        }
        catch(IllegalArgumentException i){
            int amortissement2;
            System.out.println("Exception dans le setter de l'amortissement de la classe Transport : l'amortissement doit être positif !\n");
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir à nouveau l'amortissement desiré (respectant les conditions mentionnées) :\n");
		    amortissement2 = sc.nextInt();
		    this.amortissement=amortissement2;
            this.majImpact();
        }
    }

    @Override
    public void majImpact(){
        if (this.possede){
            this.impact=(this.kilomAnnee*0.000193)+((this.taille.getEmission())/(this.amortissement));
        }
        else{
            this.impact=0;
        }
    }
    
    public static void afficheECMoyenne(){
        System.out.println("Empreinte carbone moyenne des Francais pour le transport :\n\tVoiture : 1972 Kg/an\n\tAvion : 480 Kg/an\n\tFret et messagerie : 383 Kg/an\n\tTrain et bus : 85 Kg/an\nTotal : 2920 Kg/an.\n");
    }

    @Override
    public String toString(){
        if(this.possede){
            return ("Données du poste Transport identifié "+this.getId()+": taille du véhicule : "+this.taille+", nombre de kilometres parcourus par an: "+this.kilomAnnee+", durée de conservation du véhicule: "+this.amortissement+", impact: "+this.impact);
        }
        return ("Données du poste Transport identifié "+this.getId()+": pas de véhicule, impact: "+this.impact);
    }

}