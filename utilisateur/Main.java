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
        System.out.println("l'empreinte : "+u.getEmpreinte());
        u.ajouterLogement(l2);
        u.ajouterLogement(l3);
        u.ajouterTransport(t2);
        System.out.println("l'empreinte : "+u.getEmpreinte());
        u.detaillerEmpreinte();
        u.detaillerEmpreinteOrdonne();
        u.retirerLogement(l2);
        u.detaillerEmpreinte();
    }
}