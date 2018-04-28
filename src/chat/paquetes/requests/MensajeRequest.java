package chat.paquetes.requests;

import chat.exceptions.PaqueteException;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;

/**
 * Solicitud de Mensaje
 * 
 * El cliente lo envía cuando desea enviar un mensaje a una persona
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class MensajeRequest extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "request-mensaje";

    // Parámetros
    public static final String PARAM_TO = "to";
    public static final String PARAM_MESSAGE = "message";

    public MensajeRequest(String to, String message) {
        super(ORDEN);
        try {
            this.addParam(PARAM_TO, to);
            this.addParam(PARAM_MESSAGE, message);
        } catch (PaqueteException ex) {
            ServerLog.log(MensajeRequest.class, ex.getMessage());
        }
    }

}
