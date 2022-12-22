package utilisateur;

/**Classe qui hérite de Exception nous permettant de lever et gérer une exception lorsque la classe énergétique d'un logement inscrite sur le fichier texte n'est pas reconnue */
public class CException extends Exception {
    public CException(){}
 
    public CException(String s ){
        super(s);
    }
}