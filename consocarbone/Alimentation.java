package consocarbone;
import java.util.*;
import java.io.*;
import java.util.Scanner;
/**Classe représentant le poste de consommation carbone : Alimentation.
@author Watrin Claire
@author Ouhmidou Sarra
*/
public class Alimentation extends ConsoCarbone{
    private double txBoeuf;
    private double txVege;
    static final double k1=8;
    static final double k2=1.6;
    static final double k3=0.9;

    /**Constructeur par défaut.*/
    public Alimentation(){}

    /**Constructeur qui prend en argument les informations d'un régime alimentaire.
    @param txBoeuf le taux de repas à base de boeuf
    @param txVege le taux de repas végétariens
    @throws IllegalArgumentException si les taux ne sont pas compris entre 0 et 1 ou que leur somme n'est pas inférieure ou égale à 1
    */
    public Alimentation(double txBoeuf, double txVege) throws IllegalArgumentException{
        try {
            if ( !(txBoeuf >= 0) || !(txBoeuf<=1) || !(txVege >= 0) || !(txVege <= 1) || !(txBoeuf + txVege <=1) ){
                throw new IllegalArgumentException();
            }
            else{
                this.txBoeuf=txBoeuf;
                this.txVege=txVege;
                this.impact=(k1*txBoeuf) + k2*(1-txVege-txBoeuf) + (k3*txVege);
            }
        }
        catch(IllegalArgumentException i){
            System.out.println("Exception dans le constructeur de la classe Alimentation : le taux de repas végétariens/à base de boeuf doivent être entre 0 et 1 et leur somme inférieure ou égale à 1 !\n");
            double txBoeuf2 = 0;
            double txVege2 = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir à nouveau le taux de repas comportant du boeuf dans votre alimentation (respectant les conditions mentionnées) : \n");
		    txBoeuf2 = sc.nextDouble();
		    System.out.println("\nVeuillez saisir à nouveau le taux de repas végétariens dans votre alimentation (respectant les conditions mentionnées) : \n");
		    txVege2 = sc.nextDouble();
            this.txBoeuf=txBoeuf2;
            this.txVege=txVege2;
            this.impact=(k1*txBoeuf2) + k2*(1-txVege2-txBoeuf2) + (k3*txVege2);
            i.printStackTrace();
        }
    }

    public double getTxBoeuf(){
        return this.txBoeuf;
    }

    public double getTxVege(){
        return this.txVege;
    }

    /**Setter sur le taux de repas à base de boeuf
    @throws IllegalArgumentException si le taux desiré n'est pas compris entre 0 et 1 ou si sa somme avec le taux txVege n'est pas inférieure ou égale à 1
    */
    public void setTxBoeuf(double t) throws IllegalArgumentException{
        try{
            if ((t < 0) || (t >1)){
                throw new IllegalArgumentException();
            }
            else{
                if ((this.txVege + t )>1){
                    throw new IllegalArgumentException();
                }
                else{
                    this.txBoeuf=t;
                    this.majImpact();
                }
            }
        }
        catch (IllegalArgumentException i){
            System.out.println("Exception dans le setter de txBoeuf : le taux desiré n'est pas compris entre 0 et 1 ou sa somme avec le taux txVege n'est pas inférieure ou égale à 1 !\n\n");
            System.out.println("Pour information, les taux actuels sont txBoeuf : "+this.txBoeuf+" et txVege : "+this.txVege+"\n");
            double txBoeuf2 = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir à nouveau le taux txBoeuf desiré (respectant les conditions mentionnées) :\n");
		    txBoeuf2 = sc.nextDouble();
            this.txBoeuf = txBoeuf2;
            this.majImpact();
            i.printStackTrace();
        }
    }

    /**Setter sur le taux de repas végétariens
    @throws IllegalArgumentException si le taux desiré n'est pas compris entre 0 et 1 ou si sa somme avec le taux txBoeuf n'est pas inférieure ou égale à 1
    */
    public void setTxVege(double t) throws IllegalArgumentException{
        try{
            if ((t < 0) || (t >1)){
                throw new IllegalArgumentException();
            }
            else{
                if ((this.txBoeuf + t )>1){
                    throw new IllegalArgumentException();
                }
                else{
                    this.txVege=t;
                    this.majImpact();
                }
            }
        }
        catch (IllegalArgumentException i){
            System.out.println("Exception dans le setter de txVege : le taux desiré n'est pas compris entre 0 et 1 ou sa somme avec le taux txBoeuf n'est pas inférieure ou égale à 1 !\n\n");
            System.out.println("Pour information, les taux actuels sont txBoeuf : "+this.txBoeuf+" et txVege : "+this.txVege+"\n");
            double txVege2 = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir à nouveau le taux txVege desiré (respectant les conditions mentionnées) :\n");
		    txVege2 = sc.nextDouble();
            this.txVege = txVege2;
            this.majImpact();
            i.printStackTrace();
        }
    }

    @Override
    public void majImpact(){
        this.impact=k1*(this.txBoeuf) + k2*(1-(this.txVege)-(this.txBoeuf)) + k3*(this.txVege);
    }
    /**Méthode qui affiche sur la console le détail de l'empreinte carbone des Francais pour l'alimention*/
    public static void afficheECMoyenne(){
        System.out.println("Empreinte carbone moyenne des Francais pour l'alimentation' :\n\tViandes et Poissons : 1144 Kg/an\n\tProduits laitiers et oeufs : 408 Kg/an\n\tAutres : 538 Kg/an\n\tBoissons : 263 Kg/an\nTotal : 2353 Kg/an.\n");
    }

    @Override
    public String toString(){
        return("Données du poste Alimentation identifié "+this.getId()+": taux de repas avec boeuf: "+this.txBoeuf+", taux de repas végétariens: "+this.txVege+", impact: "+this.impact);
    }

}