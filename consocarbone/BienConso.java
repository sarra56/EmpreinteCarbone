package consocarbone;

/**Classe représentant le poste de consommation carbone : Consommations diverses
@author Watrin Claire
@author Ouhmidou Sarra
*/
public class BienConso extends ConsoCarbone{
    private double montant;
    /**Constructeur par défaut*/
    public BienConso(){}
    /**Constructeur qui prend en argument les informations ddes consommations diverses
    @param montant le montant des dépenses diverses
    */
    public BienConso(double montant){
        super();
        this.montant=montant;
        this.impact=(this.montant)/1750;
    }

    public double getMontant(){
        return this.montant;
    }

    public void setMontant(int montant){
        this.montant=montant;
        this.majImpact();
    }

    //Méthodes : 

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