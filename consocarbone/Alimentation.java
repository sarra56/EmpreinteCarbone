package consocarbone;
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
    */
    public Alimentation(double txBoeuf, double txVege) throws ExceptionTaux{
        if ( !(txBoeuf >= 0) || !(txBoeuf<=1) || !(txVege >= 0) || !(txVege <= 1) || !(txBoeuf + txVege <=1) ){
            throw new ExceptionTaux("Le taux de repas végétariens/à base de boeuf doivent être une valeur entre 0 et 1 et leur somme inférieure comprise entre 0 et 1 !\n");
        }
        else{
            this.txBoeuf=txBoeuf;
            this.txVege=txVege;
            this.impact=(k1*txBoeuf) + k2*(1-txVege-txBoeuf) + (k3*txVege);
        }
    }

    public double getTxBoeuf(){
        return this.txBoeuf;
    }

    public double getTxVege(){
        return this.txVege;
    }

    /**Setter sur le taux de repas à base de boeuf*/
    public void setTxBoeuf(double t) throws ExceptionTaux{
        if ((t < 0) || (t >1)){
            throw new ExceptionTaux("Un taux doit etre compris entre 0 et 1 !");
        }
        else{
            if ((this.txVege + t )>1){
                throw new ExceptionTaux("La somme des 2 taux doit etre comprise entre 0 et 1 ! ");
            }
            else{
                this.txBoeuf=t;
                this.majImpact();
            }
        }     
    }

    /**Setter sur le taux de repas végétariens*/
    public void setTxVege(double t) throws ExceptionTaux{
        if ( !(t >= 0) || !(t<=1) ){
            throw new ExceptionTaux("Un taux doit etre compris entre 0 et 1 !");
        }
        else{
            if ((this.txBoeuf + t )>1){
            throw new ExceptionTaux("La somme des 2 taux doit etre comprise entre 0 et 1 ! ");
            }
            else{
                this.txVege=t;
                this.majImpact();
            }
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