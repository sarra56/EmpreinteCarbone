package consocarbone;
/**Classe abstraite représentant un poste de consommation carbone générique
@author Watrin Claire
@author Ouhmidou Sarra
*/
public abstract class ConsoCarbone implements Comparable<ConsoCarbone>{
    private int id;
    protected double impact; //pas private car on veut y avoir un accès direct dans les classes filles pour calculer sa valeur
    private static int k=1; //var de classe qui va nous permettre d'assigner un identifiant id unique pour chaque nouvelle instance 

    public ConsoCarbone(){
        this.id=k++;
    }

    //partagées par toutes les classes dérivées

    public int getId(){
        return this.id;
    }

    public double getImpact(){
        return this.impact;
    }

    /**Définition de la méthode compareTo de l'interface Comparable.
    Deux instances sont comparées en fonction de leurs impacts en termes d'émissions de GES.*/
    public int compareTo(ConsoCarbone o){
        if (this.impact>o.impact){
            return 1;
        }
        if (this.impact<o.impact){
            return -1;
        }
        return 0;
    }

    //à définir dans les classes filles

    /**Méthode qui met à jour l'impact d'un poste de consommation si une des données est modifiée à travers un setter*/
    public abstract void majImpact();

    /**Redéfinition de la méthode toString qui sera redéfinie dans chaque sous-classe*/
    @Override
    public abstract String toString();
}