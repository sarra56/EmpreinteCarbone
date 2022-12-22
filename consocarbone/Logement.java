package consocarbone;
import java.util.*;
import java.io.*;
import java.util.Scanner;

/**Classe représentant le poste de consommation carbone : Logement
@author Watrin Claire
@author Ouhmidou Sarra
*/
public class Logement extends ConsoCarbone{
    private int superficie;
    private CE ce;

    public Logement(){}

    /**Constructeur qui prend en argument les informations d'un logement
    @param superficie la superficie du logement
    @param ce la classe énergétique du logement
    @throws IllegalArgumentException si la superficie saisie est négative
    */
    public Logement(int superficie, CE ce) throws IllegalArgumentException{
        try{
            if (superficie<0){
                throw new IllegalArgumentException();
            }
            else {
                this.superficie=superficie;
                this.ce=ce;
                this.impact=(ce.getCoef())*superficie;
            }
        }
        catch(IllegalArgumentException i){
            System.out.println("Exception dans le constructeur de la classe Logement : la superficie d'un logement doit être strictement positive !\n");
            int superficie2 = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir à nouveau la superficie desirée (respectant les conditions mentionnées) : \n");
		    superficie2 = sc.nextInt();
		    this.superficie=superficie2;
            this.ce=ce;
            this.impact=(ce.getCoef())*(this.superficie);
            i.printStackTrace();
        }
    }

    public int getSuperficie(){
        return this.superficie;
    }

    public CE getCe(){
        return this.ce;
    }

    /**Setter sur la superficie du logement
    @param superficie la superficie desirée
    @throws IllegalArgumentException si la superficie desirée est négative
    */
    public void setSuperficie(int superficie) throws IllegalArgumentException{
        try{
            if (superficie<0){
                throw new IllegalArgumentException();
            }
            else {
                this.superficie=superficie;
                this.majImpact();
            }
        }
        catch(IllegalArgumentException i){
            System.out.println("Exception dans le setter de la superficie de la classe Logement : la superficie d'un logement doit être strictement positive !\n");
            int superficie2 = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir à nouveau la superficie desirée (respectant les conditions mentionnées) : \n");
		    superficie2 = sc.nextInt();
		    this.superficie=superficie2;
            this.majImpact();
            i.printStackTrace();
        }
    }

    public void setCe(CE ce){
        this.ce=ce;
        this.majImpact();
    }

    @Override
    public void majImpact(){
        this.impact=((this.ce).getCoef())*(this.superficie);
    }
    /**Méthode qui affiche sur la console le détail de l'empreinte carbone des Francais pour le logement*/
    public static void afficheECMoyenne(){
        System.out.println("Empreinte carbone moyenne des Francais pour le logement :\n\tEnergie et utilités : 1696 Kg/an\n\tConstruction et gros entretien : 675 Kg/an\n\tEquipement : 335 Kg/an\nTotal : 2706 Kg/an.\n");
    }

    @Override
    public String toString(){
        return("Données du poste Logement identifié "+this.getId()+": superficie: "+this.superficie+", classe énergétique: "+this.ce+", impact : "+this.impact);
    }

}

