package consocarbone;
import java.lang.Math;

/**Classe représentant le poste de consommation carbone : Transports urbains et périurbains.
Source : selon l'Agence Européenne de l'Environnement, un passager en avion émet 285g(=0,000285 Tonnes) de CO2 par kilometre.
Concernant les trajets en voiture, on utilise les cartes solutions du guide donné dans l'énoncé (à partir de la page 48).
D'après le site de la SNCF, un passager en TGV émet 1,73 gCO2e par km, en Intercités 5,29 gCO2e et en TER 24,81 gCO2e. On fait une moyenne de ces 3 valeurs : 10,61 gCO2e par km par passager pour un voyage en train.
@author Watrin Claire
@author Ouhmidou Sarra
*/
public class TransportGlobal extends ConsoCarbone{
 	private double kilom_en_avion;
 	private CarteTransport T;
 	private double kilom_en_tramway;
 	private double kilom_en_metro;
 	private double kilom_en_RER;
 	private double kilom_en_bus;
 	private double kilom_en_train; 

 	public TransportGlobal(double kilom_en_avion,CarteTransport T,double kilom_en_tramway,double kilom_en_metro,double kilom_en_RER,double kilom_en_bus,double kilom_en_train){
	 	super();
	 	this.kilom_en_avion=kilom_en_avion;
	 	this.T=T;
	 	this.kilom_en_tramway=kilom_en_tramway;
	 	this.kilom_en_metro=kilom_en_metro;
		this.kilom_en_RER=kilom_en_RER;
		this.kilom_en_bus=kilom_en_bus;
	 	this.kilom_en_train=kilom_en_train;
	 	majImpact();
 	}
 
 	public void majImpact() { //données p61 du guide
	 	this.impact = this.kilom_en_avion*0.000285 + this.T.getCoef() + this.kilom_en_tramway*3.3*Math.pow(10,-6) + this.kilom_en_metro*3.4*Math.pow(10,-6) + this.kilom_en_RER*5.2*Math.pow(10,-6) + this.kilom_en_bus*0.0001321 + this.kilom_en_train*1.061*Math.pow(10,-5);
 	}
 
 	public double getKilomAvion() {
	 	return this.kilom_en_avion;
 	}
 
 	public double getKilomTram() {
	 	return this.kilom_en_tramway;
 	}
 
 	public double getKilomMetro() {
	 	return this.kilom_en_metro;
	 }
 
	public double getKilomRER() {
	 	return this.kilom_en_RER;
 	}
 
 	public double getKilomBus() {
	 	return this.kilom_en_bus;
 	}
 
 	public double getKilomTrain() {
	 	return this.kilom_en_train;
 	}
 
 	public CarteTransport getT() {
	 	return this.T;
 	}
 
 	public void setKilomAvion(double ka) {
	 	this.kilom_en_avion=ka;
		this.majImpact();
 	}
 
 	public void setKilomTram(double kt) {
	 	this.kilom_en_tramway=kt;
	 	this.majImpact();
 	}
 
 	public void setKilomMetro(double km) {
	 	this.kilom_en_metro=km;
	 	this.majImpact();
 	}
 
 	public void setKilomRER(double kr) {
	 	this.kilom_en_RER=kr;
	 	this.majImpact();
 	}
 
 	public void setKilomBus(double kb) {
	 	this.kilom_en_bus=kb;
	 	this.majImpact();
 	}
 
 	public void setKilomTrain(double kt) {
	 	this.kilom_en_train=kt;
	 	this.majImpact();
 	}
 
 	public void setT(CarteTransport T) {
	 	this.T=T;
		this.majImpact();
 	}
 
 	public static void afficheECMoyenne(){
     	System.out.println("Empreinte carbone moyenne des Francais pour les transports urbains et périurbains :\n\tVoiture : 1972 Kg/an\n\tAvion : 480 Kg/an\n\tFret et messagerie : 383 Kg/an\n\tTrain et bus : 85 Kg/an\nTotal : 2920 Kg/an.\n");
	}

 	@Override
 	public String toString(){
 		return "Poste de consommation Transport urbains numéro "+this.getId()+"impact global (en TCO2e) : "+this.impact+",nombre de km parcourus en avion : "+this.kilom_en_avion+", nombre de km parcourus en tram : "+this.kilom_en_tramway+", nombre de km parcourus en métro : "+this.kilom_en_metro+", nombre de km parcourus en RER : "+this.kilom_en_RER+", nombre de km parcourus en train : "+this.kilom_en_train+", nombre de km parcourus en bus : "+this.kilom_en_bus+", impact dû à l'utilisation de la voiture : "+this.T.getCoef();
	}
 
}
