package consocarbone;

public class Main{
    public static void main(String[] args) throws ExceptionTaux{
        Logement l = new Logement(26,CE.B);
        Alimentation a = new Alimentation(0.5,0.5);
        Transport t = new Transport(true,Taille.P,100,2);

        //test de la maj de l'impact : 
        System.out.println("\nTest de la màj de l'impact :\n");
        System.out.println("Impact de l : "+ l.impact); //ou utiliser l.getImpact()
        l.setSuperficie(30);
        System.out.println("Changement sur la superficie de l effectué.\nNouvel impact de l : "+ l.impact +"\n");

        System.out.println("Impact de t : "+ t.impact);
        t.setTaille(Taille.G);
        System.out.println("Changement sur la taille de t effectué.\nNouvel impact de t : "+ t.impact);
        t.setPossede(false);
        System.out.println("Changement sur possede de t effectué.\nNouvel impact de t : "+ t.impact +"\n");

        //test de la fonction afficheECMoyenne :
        System.out.println("Test de la la fonction afficheECMoyenne :\n");
        Logement.afficheECMoyenne();
        BienConso.afficheECMoyenne();
        Transport.afficheECMoyenne();
        Alimentation.afficheECMoyenne();

        //test de la redéfinition de toString : 
        System.out.println("Test de la redéfinition de toString :\n");
        System.out.println(a.toString());
        System.out.println(t.toString());
        System.out.println(l.toString());

        //test du polymorphisme :
        ConsoCarbone c = new Logement(20,CE.B);
        System.out.println("c est-elle une instance de Logement ?\nRésultat du test : "+ (c instanceof Logement) );
        System.out.println("c est-elle une instance de ConsoCarbone ?\nRésultat du test : "+ (c instanceof ConsoCarbone) );
        
        ServicesPublics.afficheECMoyenne();
        ServicesPublics f = ServicesPublics.getInstance();
        System.out.println(f.toString());

        Logement d = new Logement(30,CE.C);
        int r = c.compareTo(d);
        System.out.println(r);

        BienConso bien = new BienConso(100);
       // Utilisateur sarra = new Utilisateur(a,bien,d,t,f);
        //System.out.println(sarra.getEmpreinte());
    }   
}
