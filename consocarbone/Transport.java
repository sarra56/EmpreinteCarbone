package consocarbone;

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

    public Transport(boolean possede, Taille taille, int kilomAnnee, int amortissement){
        super();
        this.possede=possede;
        this.taille=taille;
        this.kilomAnnee=kilomAnnee;
        this.amortissement=amortissement;
        if (possede){
            this.impact=(kilomAnnee*0.000193)+((taille.getEmission())/amortissement);
        }
        else{
            this.impact=0;
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

    public void setKilomAnnee(int kilomnAnnee){
        this.kilomAnnee=kilomAnnee;
        this.majImpact();
    }

    public void setAmortissement(int amortissement){
        this.amortissement=amortissement;
        this.majImpact();
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