package Exceptions;

/**
 * Excepción en el manejo de paquetes
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class PaqueteException extends Exception {

    /**
     * constructor encargado de obtener el mensaje de error
     * @param message mesaje de error 
     */
    public PaqueteException(String message) {
        super(message);
    }

}
