package chat.paquetes.responses;

import chat.exceptions.PaqueteException;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;

/**
 * Respuesta genérica
 * 
 * Respuesta que recibe el servidor o el cliente para reconocer que el comando
 * se recibió e interpretó
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class GenericResponse extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "response-generic";

    // Parámetros
    public static final String PARAM_STATUS = "status";

    public GenericResponse(Status status) {
        super(ORDEN);
        try {
            this.addParam(PARAM_STATUS, status.getName());
        } catch (PaqueteException ex) {
            ServerLog.log(GenericResponse.class, ex.getMessage());
        }

    }

    public enum Status {
        CORRECT("correct"),
        INCORRECT("incorrect"),
        BAD_REQUEST("badrequest");

        private final String name;

        Status(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
