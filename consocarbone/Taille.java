package consocarbone;
/**Les 2 instances de l'énumération Taille correspondent à petite voiture (P) et grosse voiture (G)
@author Watrin Claire
@author Ouhmidou Sarra
*/
public enum Taille{
    P(4.2),
    G(19);

    private double emission;

    private Taille(double k){
        emission=k;
    }

    public double getEmission(){
        return this.emission;
    }

}