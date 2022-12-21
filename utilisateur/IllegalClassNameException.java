package utilisateur;

public class IllegalClassNameException extends Exception {
    public IllegalClassNameException(){}
 
    public IllegalClassNameException(String s ){
        super(s);
    }
}