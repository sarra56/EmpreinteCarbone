package utilisateur;
import consocarbone.*;
import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		    int numMenu = 0;
		    Scanner sc = new Scanner(System.in);
		    do{

			System.out.println("-------------\nVoici le Menu\n-------------\nTapez 0 pour sortir du menu\nTapez 1 pour saisir vos informations en tant qu'utilisateur\n");
			numMenu = sc.nextInt();
			 
			if(numMenu==1){
				System.out.println("Nous allons calculer votre empreinte carbone. Veuillez saisir le taux de repas comportant du boeuf dans votre alimentation (un double compris entre 0 et 1) : \n");
		    	double txBoeuf = sc.nextDouble(); //attention à bien mettre une virgule et pas un point dans la déclaration du double
		    	System.out.println("\nVeuillez saisir le taux de repas végétariens dans votre alimentation (un double compris entre 0 et 1) : \n");
		    	double txVege = sc.nextDouble();
		    	Alimentation alimentation = new Alimentation(txBoeuf,txVege);
		    	System.out.println("\nVeuillez saisir le montant de vos dépenses annuelles : \n");
		    	double montant = sc.nextDouble();
		    	BienConso bienConso = new BienConso(montant);
		    	System.out.println("\nVeuillez saisir la superficie (en m2) de votre logement : \n");
		    	int superficie = sc.nextInt();
		    	System.out.println("\nVeuillez saisir la classe énergétique de votre logement (comprise entre A et G) : \n");
		    	String ce = sc.next();
				CE i = CE.A;
		    	Logement logement = new Logement(superficie, i);
		    	if(ce.equals("B")) {logement.setCe(CE.B);}
		    	if(ce.equals("C")) {logement.setCe(CE.C);}
		    	if(ce.equals("D")) {logement.setCe(CE.D);}
		    	if(ce.equals("E")) {logement.setCe(CE.E);}
		    	if(ce.equals("F")) {logement.setCe(CE.F);}
		    	if(ce.equals("G")) {logement.setCe(CE.G);}
		    	ServicesPublics services = ServicesPublics.getInstance();
		    	Transport transport = new Transport(); //je dois déclarer mes classe en dehors des if sinon il y a un bug
		    	System.out.println("\nPossedez vous une voiture ? (OUI/NON) \n");
		    	String rep = sc.next();
		    	if(rep.equals("NON")){
		    		transport.setPossede(false);
		    	}
		    	else if(rep.equals("OUI")){
		    		System.out.println("\nSaisir la taille de votre voiture (P pour petite,G pour grande) : \n");
		    		String taille = sc.next();
		    		System.out.println("Saisir le nombre de kilomètres parcourus par an : \n");
		    		int kilomAnnee = sc.nextInt();
		    		System.out.println("Saisir l'amortissement du véhicule (durée réelle d'utilisateur du bien en années) : \n");
		    		int amortissement = sc.nextInt();
		    		if(taille.equals("P")) { 
		    			transport.setKilomAnnee(kilomAnnee); 
		    			transport.setAmortissement(amortissement);
		    			transport.setTaille(Taille.P);
		    			transport.setPossede(true);  //bien mettre taille en premier (ou laisser possede=false jusqu'à avoir tous les attributs) car l'ajout/la modification d'autres attributs fait appel à majImpact, qui a besoin de connaitre la taille
		    		}
		    		else if(taille.equals("G")) { 
		    			transport.setKilomAnnee(kilomAnnee); 
		    			transport.setAmortissement(amortissement);
		    			transport.setTaille(Taille.G);
		    			transport.setPossede(true);
		    		}
		    	}
		    
		    Utilisateur utilisateur = new Utilisateur(alimentation,bienConso,logement,transport,services);
		    utilisateur.detaillerEmpreinte();
		 
		    boolean addTransport = true;
		    while(addTransport) {
		    	System.out.println("\nSouhaitez vous ajouter un transport ? (OUI/NON) : \n");
		    	String addT = sc.next();
		    	if(addT.equals("OUI")) {
		    		Transport transport2 = new Transport();
		    		System.out.println("\nSaisir la taille de votre voiture (P pour petite,G pour grande) : \n");
		        	String taille = sc.next();
		        	System.out.println("Saisir le nombre de kilomètres parcourus par an : \n");
		        	int kilomAnnee = sc.nextInt();
		        	System.out.println("Saisir l'amortissement du véhicule (durée réelle d'utilisateur du bien en années) : \n");
		        	int amortissement = sc.nextInt();
		        	if(taille.equals("P")) { 
		        		transport2.setKilomAnnee(kilomAnnee); 
		        		transport2.setAmortissement(amortissement);
		        		transport2.setTaille(Taille.P);
		        		transport2.setPossede(true);  //bien mettre taille en premier (ou laisser possede=false jusqu'à avoir tous les attributs) car l'ajout/la modification d'autres attributs fait appel à majImpact, qui a besoin de connaitre la taille
                        transport2.majImpact();
		        	}
		        	if(taille.equals("G")) { 
		        		transport2.setKilomAnnee(kilomAnnee); 
		        		transport2.setAmortissement(amortissement);
		        		transport2.setTaille(Taille.G);
		        		transport2.setPossede(true);
                        transport2.majImpact();
		        	}
		        	utilisateur.ajouterTransport(transport2);
		        	System.out.println("\nNouvelle empreinte : " + utilisateur.getEmpreinte());
		    	    //System.out.println("\n\n");
		    	}
		    	else {
		    		addTransport = false;
		    	}    		
		    }
		    
		    boolean addLogement = true;
		    while(addLogement) {
		    	System.out.println("\nSouhaitez vous ajouter un logement ? (OUI/NON) : \n");
		    	String addV = sc.next();
		    	if(addV.equals("OUI")) {
		    		System.out.println("\nVeuillez saisir la superficie (en m2) de votre logement : \n");
		    	    int superficie2 = sc.nextInt();
		    	    System.out.println("\nVeuillez saisir la classe énergétique de votre logement (comprise entre A et G) : \n");
		    	    String ce2 = sc.next();
		    	    Logement logement2 = new Logement(superficie2,CE.A);
		    	    if(ce2.equals("B")) { logement2.setCe(CE.B);} 
		    	    if(ce2.equals("C")) { logement2.setCe(CE.C);}
		    	    if(ce2.equals("D")) { logement2.setCe(CE.D);}
		    	    if(ce2.equals("E")) { logement2.setCe(CE.E);}
		    	    if(ce2.equals("F")) { logement2.setCe(CE.F);}
		    	    if(ce2.equals("G")) { logement2.setCe(CE.G);}
                    logement2.majImpact();
		    	    utilisateur.ajouterLogement(logement2);
		    	    System.out.println("\nNouvelle empreinte : " + utilisateur.getEmpreinte());
		    	    //System.out.println("\n\n");
		    	}
		    	else {
		    		addLogement = false;
		    	}
		    }
		    
			}
			
			} while (!(numMenu==0));
		}
	
}
