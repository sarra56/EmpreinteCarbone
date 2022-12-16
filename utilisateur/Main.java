package utilisateur;
import consocarbone.*;

public class Main{
    public static void main(String[] args){
        Alimentation a = new Alimentation(0.5,0.5);
        BienConso b = new BienConso(200);
        Logement l1 = new Logement(30,CE.B);
        Logement l2 = new Logement(40,CE.A);
        Logement l3 = new Logement(50,CE.C);
        Transport t1 = new Transport(true,Taille.P,1000,5);
        Transport t2 = new Transport(true,Taille.P,2000,10);
        ServicesPublics s = ServicesPublics.getInstance();
        Utilisateur u = new Utilisateur(a,b,l1,t1,s);
        u.ajouterLogement(l2);
        u.ajouterLogement(l3);
        u.ajouterTransport(t2);
        Alimentation a1 = new Alimentation(0.5,0.5);
        BienConso b2 = new BienConso(10000);
        BienConso b3 = new BienConso(40000);
        Logement l12 = new Logement(50,CE.G);
        Logement l22 = new Logement(200,CE.B);
        Logement l32 = new Logement(20,CE.F);
        Transport t12 = new Transport(true,Taille.P,1000,5);
        Transport t22 = new Transport(true,Taille.G,2000,10);
        //ServicesPublics s = ServicesPublics.getInstance();
        Utilisateur u1 = new Utilisateur(a1,b2,l12,t12,s);
        Utilisateur u2 = new Utilisateur(a1,b3,l22,t22,s);
        u2.ajouterLogement(l32);
        Population P = new Population();
        P.addUser(u);
        P.addUser(u1);
        P.addUser(u2);
        System.out.println(P.toString());
        System.out.println("Impact moyen de la population : ");
        System.out.println(P.getEmpreinteMoyenne());
        System.out.println("On impose une limite de 1000km par an, nouvel impact moyen : ");
        P.mesureKilomAnnee(1000);
        System.out.println(P.getEmpreinteMoyenne());
        System.out.println("Tous les logements de type G doivent etre rénovés et passer en type F, nouvel impact moyen : ");
        P.renove(CE.G,CE.F);
        System.out.println(P.getEmpreinteMoyenne());
    }
}