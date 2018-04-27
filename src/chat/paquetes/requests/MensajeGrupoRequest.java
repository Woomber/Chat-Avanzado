package chat.paquetes.requests;

import chat.exceptions.PaqueteException;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class MensajeGrupoRequest extends Paquete {

    public static final String ORDEN = "request-mensaje-grupo";

    public static final String PARAM_GROUP = "to";
    public static final String PARAM_MESSAGE = "message";

    public MensajeGrupoRequest(String toGroup, String message) {
        super(ORDEN);
        try {
            this.addParam(PARAM_GROUP, toGroup);
            this.addParam(PARAM_MESSAGE, message);
        } catch (PaqueteException ex) {
            ServerLog.log(MensajeGrupoRequest.class, ex.getMessage());
        }
    }

}
