package chat.paquetes.requests;

import chat.exceptions.PaqueteException;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;

/**
 * Solicitud de Amigo
 * 
 * El cliente lo envía para agregar, modificar o eliminar un amigo
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class AmigoRequest extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "request-amigo";

    // Parámetros
    public static final String PARAM_OPERACION = "op";
    public static final String PARAM_AMIGO = "amigo";
    public static final String PARAM_APODO = "nickname";

    /**
     * Inicializa los valores para la operación
     * @param amigo Identifica al amigo del usuario
     * @param apodo El apodo del amigo
     * @param operacion Identificador de la operación
     */
    public AmigoRequest(String amigo, String apodo, Operacion operacion) {
        super(ORDEN);
        try {
            this.addParam(PARAM_AMIGO, amigo);
            this.addParam(PARAM_APODO, apodo);
            this.addParam(PARAM_OPERACION, operacion.getName());
        } catch (PaqueteException ex) {
            ServerLog.log(AmigoRequest.class, ex.getMessage());
        }

    }

    public enum Operacion {
        ADD("add"),
        RENAME("rename"),
        REMOVE("remove");

        private final String name;

        Operacion(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
