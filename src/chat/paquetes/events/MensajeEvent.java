package chat.paquetes.events;

import chat.exceptions.PaqueteException;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;

/**
 * Evento de recepción de mensaje
 * 
 * Se envía al cliente cuando recibe un mensaje nuevo de una conversación
 * individual
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class MensajeEvent extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "event-mensaje";

    // Parámetros
    public static final String PARAM_FROM = "from";
    public static final String PARAM_MESSAGE = "message";

    public MensajeEvent(String from, String message) {
        super(ORDEN);
        try {
            this.addParam(PARAM_FROM, from);
            this.addParam(PARAM_MESSAGE, message);
        } catch (PaqueteException ex) {
            ServerLog.log(MensajeEvent.class, ex.getMessage());
        }
    }

}
