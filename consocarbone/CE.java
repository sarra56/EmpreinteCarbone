package consocarbone;
/**Les 7 instances de l'énumération CE représentent les différentes classes énergétiques possibles d'un logement
@author Watrin Claire
@author Ouhmidou Sarra
 */
public enum CE{
    A(0.005),
    B(0.01),
    C(0.02),
    D(0.035),
    E(0.055),
    F(0.08),
    G(0.1);

    private double coef;

    //en private car on ne peut pas creer de nouvelles instances 
    private CE(double k){
        coef=k;
    }

    public double getCoef(){
        return this.coef;
    }
}