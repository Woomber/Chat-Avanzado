package Exceptions;

/**
 * Excepción para las conversiones JSON
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class JsonParserException extends Exception {
    
    /**
     * Constructor encargado de mandar recuperar  mensaje de error
     * @param message mensaje de error 
     */

    public JsonParserException(String message) {
        super(message);
    }

}
