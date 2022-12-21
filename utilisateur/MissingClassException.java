package utilisateur;

public class MissingClassException extends Exception {
    public MissingClassException(){}
 
    public MissingClassException(String s ){
        super(s);
    }
}