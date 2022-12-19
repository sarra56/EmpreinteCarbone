package utilisateur;
import consocarbone.*;
import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args)throws IOException{
        Utilisateur u = new Utilisateur("utilisateur/utilisateur.txt");
        System.out.println("empreinte : " + u.getEmpreinte());
    }
}