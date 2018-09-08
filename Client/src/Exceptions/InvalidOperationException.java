package Exceptions;

/**
 * Excepción para las operaciones de HiloReceiver
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class InvalidOperationException extends Exception {
    
    /**
     * Constructor encargado de recuperar el menaje de error 
     * @param message mensaje de error
     */

    public InvalidOperationException(String message) {
        super(message);
    }

}
