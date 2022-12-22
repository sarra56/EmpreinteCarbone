package utilisateur;

/**Classe qui hérite de Exception nous permettant de lever et gérer une exception lorsque le nom d'une classe inscrite sur le fichier texte n'est pas reconnue*/
public class IllegalClassNameException extends Exception {
    public IllegalClassNameException(){}
 
    public IllegalClassNameException(String s ){
        super(s);
    }
}