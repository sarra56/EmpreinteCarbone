package utilisateur;
import consocarbone.*;
import java.util.*;

/**Classe Population contenant une collection d'utilisateur et des methodes simulant l'instauration de mesures publics visant à réduire l'empreinte carbone des utilisateurs
@author Watrin Claire
@author Ouhmidou Sarra
*/

public class Population{
    private List<Utilisateur> population;
    private double empreinteMoyenne; //empreinte moyenne de la population
    private int taille; //taille de la population
  
    public Population(){
        population = new LinkedList<Utilisateur>();
        empreinteMoyenne = 0;
        taille = 0;
    }

    public Population(LinkedList<Utilisateur> population){
        this.population = population;
        this.taille = population.size();
        this.majEmpreinteMoyenne();
    }
  
    public void ajouterUtilisateur(Utilisateur u){
        population.add(u);
        taille = taille+1;
        this.majEmpreinteMoyenne();
    }
  
    /**Méthode qui permet de retirer un des utilisateurs de la population
    @param u l'utilisateur à retirer
    @throws IllegalArgumentxception si u n'est pas présent dans la collection d'utilisateurs
    */
    public void retirerUtilisateur(Utilisateur u) throws IllegalArgumentException{
        try{
            boolean sup = population.remove(u);
            if (sup == true){
                taille = taille-1;
                this.majEmpreinteMoyenne();
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("Exception dans retirerUtilisateur : l'utilisateur que vous souhaitez retirer n'appartient pas à la collection d'utilisateurs de cette population !");
            e.printStackTrace();
        }
    }
  
    public void majEmpreinteMoyenne(){
        double somme = 0;
        for (Utilisateur u : population){
          somme = somme + u.getEmpreinte();
        }
        this.empreinteMoyenne = somme/this.taille;
    }
  
    public double getEmpreinteMoyenne(){
        return this.empreinteMoyenne;
    }

    public int getTaille(){
        return this.taille;
    }

    public List<Utilisateur> getPopulation(){
      return this.population;
    }
  
    /**Méthode imposant à tous les utilisateur vivant dans des logements de type CE1 de les rénover pour passer au type CE2.
    @param CE1 classe énergétique des logements à rénover
    @param CE2 nouvelle classe énergétique 
    */
    public void renove(CE CE1,CE CE2){
        Iterator<Utilisateur> it = population.iterator();
        while(it.hasNext()){
            Utilisateur u = it.next();
            Iterator<Logement> it2 = (u.getColLogement()).iterator();
            while(it2.hasNext()){
                Logement l = it2.next();
                if(l.getCe()==CE1){
                    l.setCe(CE2);
                }
            }
            u.majEmpreinte();
        }
        this.majEmpreinteMoyenne();
    }
  
    /**Méthode imposant un certain taux de boeuf dans l'alimentation des utilisateurs de la population.
    @param txBoeuf : le taux de repas à base de boeuf à imposer.
    */
    public void mesureTxBoeuf(double txBoeuf){
        Iterator<Utilisateur> it = population.iterator();
        while(it.hasNext()){
            Utilisateur u = it.next();
            (u.getAlimentation()).setTxBoeuf(txBoeuf);
            u.majEmpreinte();
        }
        this.majEmpreinteMoyenne();
    }
  
    /**Méthode imposant un certain kilometrage par année aux utilisateurs de la population possédant au moins une voiture.
    @param kilomAnnee : le kilometrage à imposer.
    */
    public void mesureKilomAnnee(int kilomAnnee){
        Iterator<Utilisateur> it = population.iterator();
        while(it.hasNext()){
            Utilisateur u = it.next();
            Iterator<Transport> it2 = (u.getColTransport()).iterator();
            while(it2.hasNext()){
                Transport t = it2.next();
                if (t.getKilomAnnee() > kilomAnnee){
                    t.setKilomAnnee(kilomAnnee);
                }
            }
            u.majEmpreinte();
        }
        this.majEmpreinteMoyenne();
    }
  
    /**Méthode imposant une reduction des dépenses aux utilisateurs ayant une empreinte trop élevée.
    On ne sait pas si ces mesures sont pertinentes (peut etre que ce n'est pas au niveau des dépenses qu'il faudrait imposer une reduction par ex), mais ces methodes nous permettront de le tester.
    @param empreinteActuelle : seuil à partir duquel on considère que l'empreinte est trop élevée.
    @param depenseReduite : montant des dépenses que l'on va imposer à l'utilisateur. 
    */
    public void mesureDepenses(double empreinteActuelle, double depenseReduite){
        Iterator<Utilisateur> it = population.iterator();
        while(it.hasNext()){
            Utilisateur u = it.next();
            if (u.getEmpreinte() >= empreinteActuelle){
                BienConso b = u.getBienConso();
                b.setMontant(depenseReduite);
            }
            u.majEmpreinte(); 
        }
        this.majEmpreinteMoyenne();
    }

    /**Méthode imposant un certain amortissement aux utilisateurs de la population possédant au moins une voiture.
    @param amortissement : l'amortissement à imposer.
    */
    public void mesureAmortissement(int amortissement){
        Iterator<Utilisateur> it = population.iterator();
        while(it.hasNext()){
            Utilisateur u = it.next();
            Iterator<Transport> it2 = (u.getColTransport()).iterator();
            while(it2.hasNext()){
                Transport t = it2.next();
                t.setAmortissement(amortissement);
            }
            u.majEmpreinte();
        }
        this.majEmpreinteMoyenne();
    }

    /**Redéfintion de la méthode toString().*/
    @Override
    public String toString(){
        String res = "Empreinte moyenne de la population : "+this.getEmpreinteMoyenne()+".";
        for(Utilisateur u: population){
            res += ("Utilisateur numéro " +u.getId()+ " : son empreinte (en TCO2eq) est de " + u.getEmpreinte()+",");
        }
        return res;
    }
  
}