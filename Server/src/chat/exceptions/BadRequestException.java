package chat.exceptions;

/**
 * Excepción al recibir una estructura de mensaje inválida
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }

}
