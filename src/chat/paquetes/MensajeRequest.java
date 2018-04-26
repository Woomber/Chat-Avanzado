package chat.paquetes;

import chat.exceptions.PaqueteException;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class MensajeRequest extends Paquete {

    public static final String ORDEN = "send";

    public static final String PARAM_TO = "to";
    public static final String PARAM_FROM = "from";
    public static final String PARAM_MESSAGE = "message";

    public MensajeRequest(String to, String from, String message) {
        super(ORDEN);
        try {
            this.addParam(PARAM_TO, to);
            this.addParam(PARAM_FROM, from);
            this.addParam(PARAM_MESSAGE, message);
        } catch (PaqueteException ex) {
            ServerLog.log(MensajeRequest.class, ex.getMessage());
        }
    }

}
