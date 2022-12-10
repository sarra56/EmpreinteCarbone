package utilisateur;
import consocarbone.*;

public class Main{
    public static void main(String[] args){
        Alimentation a = new Alimentation(0.5,0.5);
        BienConso b = new BienConso(200);
        Logement l = new Logement(30,CE.B);
        Transport t = new Transport(true,Taille.P,1000,3);
        ServicesPublics s = ServicesPublics.getInstance();
        Utilisateur u = new Utilisateur(a,b,l,t,s);
        System.out.println(u.getId());
        //u.detaillerEmpreinte();
        System.out.println(u.getEmpreinte());
        Utilisateur u2 = new Utilisateur(a,b,l,t,s);
        System.out.println(u2.getId());
    }
}