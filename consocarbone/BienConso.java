package consocarbone;
import java.util.*;
import java.io.*;
import java.util.Scanner;

/**Classe représentant le poste de consommation carbone : Consommations diverses
@author Watrin Claire
@author Ouhmidou Sarra
*/
public class BienConso extends ConsoCarbone{
    private double montant;

    /**Constructeur par défaut*/
    public BienConso(){}

    /**Constructeur qui prend en argument les informations des consommations diverses
    @param montant le montant des dépenses diverses
    @throws IllegalArgumentException si le montant saisi est négatif
    */
    public BienConso(double montant) throws IllegalArgumentException{
        try{
            if (montant < 0){
                throw new IllegalArgumentException();
            }
            else{
                this.montant=montant;
                this.impact=(this.montant)/1750;
            }
        }
        catch(IllegalArgumentException i){
            System.out.println("Exception dans le constructeur de la classe BienConso : le montant doit être un double positif !\n");
            double montant2 = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir à nouveau le montant desiré (respectant les conditions mentionnées) : \n");
		    montant2 = sc.nextDouble();
		    this.montant=montant2;
            this.impact=(this.montant)/1750;
        }
    }

    public double getMontant(){
        return this.montant;
    }

    /**Setter sur le montant des dépenses
    @param montant le montant desiré
    @throws IllegalArgumentException si le montant desiré est négatif
    */
    public void setMontant(double montant) throws IllegalArgumentException{
        try{
            if (montant < 0){
                throw new IllegalArgumentException();
            }
            else{
                this.montant=montant;
                this.majImpact();
            }
        }
        catch(IllegalArgumentException i){
            System.out.println("Exception dans le setter de la classe BienConso : le montant doit être un double positif !\n");
            double montant2 = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir à nouveau le montant desiré (respectant les conditions mentionnées) : \n");
		    montant2 = sc.nextDouble();
		    this.montant=montant2;
            this.majImpact();
        }
    }

    @Override
    public void majImpact(){
        this.impact=(this.montant)/1750;
    }

    /**Méthode qui affiche sur la console le détail de l'empreinte carbone des Francais pour les biens de consommation*/
    public static void afficheECMoyenne(){
        System.out.println("Empreinte carbone moyenne des Francais pour les biens de consommation :\n\tAchats et usages Internet et technologies : 1180 Kg/an\n\tAutres biens et services : 682 Kg/an\n\tHabillement : 763 Kg/an\nTotal : 2625 Kg/an.\n");
    }

    @Override
    public String toString(){
        return("Données du poste BienConso identifié "+this.getId()+": montant : "+this.montant+", impact: "+this.impact);
    }

}