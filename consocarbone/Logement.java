package consocarbone;
/**Classe représentant le poste de consommation carbone : Logement
@author Watrin Claire
@author Ouhmidou Sarra
*/
public class Logement extends ConsoCarbone{
    private int superficie;
    private CE ce;

    public Logement(){}

    public Logement(int superficie, CE ce){
        super();
        this.superficie=superficie;
        this.ce=ce;
        this.impact=(ce.getCoef())*superficie;
    }

    public int getSuperficie(){
        return this.superficie;
    }

    public CE getCe(){
        return this.ce;
    }

    public void setSuperficie(int superficie){
        this.superficie=superficie;
        this.majImpact();
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

