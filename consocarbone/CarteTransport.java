package consocarbone;

/**Chaque instance de cette classe correspond à ...
@author Watrin Claire
@author Ouhmidou Sarra
*/
public enum CarteTransport {
 	T1(0), //cas où l'utilisateur n'a pas de voiture
 	T2(2.1), //petite voiture amortie sur 30 ans - 10 000km
 	T3(1.3), //petite voiture amortie sur 30 ans - 6 000km
 	T4(0.5), //petite voiture amortie sur 30 ans - 2 000km
 	T5(3), //grosse voiture amortie sur 10 ans - 5 000km
 	T6(0.6); //voiture électrique moyen de gamme - 5 000 km
 
 	private double coef;
 
 	private CarteTransport(double coef) {
	 	this.coef = coef ;
 	}
 
 	public double getCoef(){
	 	return this.coef;
 	}
}
