package utilisateur;

/**Classe qui hérite de Exception nous permettant de lever et gérer une exception lorsqu'il manque une classe sur le fichier texte*/
public class MissingClassException extends Exception {
    public MissingClassException(){}
 
    public MissingClassException(String s ){
        super(s);
    }
}