package utilisateur;

/**Classe qui hérite de Exception nous permettant de lever et gérer une exception lorsque la taille d'une voiture inscrite sur le fichier texte n'est pas reconnue*/
public class TailleException extends Exception {
    public TailleException(){}
 
    public TailleException(String s ){
        super(s);
    }
}