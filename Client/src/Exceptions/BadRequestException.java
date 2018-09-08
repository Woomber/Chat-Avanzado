package Exceptions;

/**
 * Excepción al recibir una estructura de mensaje inválida
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class BadRequestException extends Exception {

    /**
     * Constructor encargado de obter el mensaje de error
     * @param message  Mensaje de error
     */
    public BadRequestException(String message) {
        super(message);
    }

}
