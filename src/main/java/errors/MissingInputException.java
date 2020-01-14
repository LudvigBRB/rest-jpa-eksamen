
package errors;

/**
 *
 * @author Ludvig
 */
public class MissingInputException extends Exception {
    
    public MissingInputException(String message) {
        super(message);
    }

    public MissingInputException() {
        super("Username or password is missing");
    }  
}
