package consocarbone;
/**Classe représentant le poste de consommation carbone : Services publics
Design pattern type Singleton : une seule instance car tou.te.s les Français.e.es ont la même empreinte pour ce poste
@author Watrin Claire
@author Ouhmidou Sarra
 */
public class ServicesPublics extends ConsoCarbone{
    //design pattern singleton 

    private static ServicesPublics instance = null;

    private ServicesPublics(){
        this.impact=1.5;
    }

    public static ServicesPublics getInstance(){
        if (ServicesPublics.instance == null){
            ServicesPublics.instance = new ServicesPublics();
        }
        return ServicesPublics.instance;
    }

    @Override
    public String toString(){
        return ("Données du poste Services publics identifié "+this.getId()+": impact:"+this.impact);
    }
    /**Méthode qui affiche sur la console le détail de l'empreinte carbone des Francais pour les services publics*/
    public static void afficheECMoyenne(){
        System.out.println("Empreinte carbone moyenne des Francais pour les Services publics : 1489 kg/an.\n");
    }

    @Override 
    public void majImpact(){}

}