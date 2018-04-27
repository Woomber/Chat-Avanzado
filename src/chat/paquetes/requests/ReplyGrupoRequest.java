package chat.paquetes.requests;

import chat.exceptions.PaqueteException;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class ReplyGrupoRequest extends Paquete {

    public static final String ORDEN = "request-reply-grupo";

    public static final String PARAM_GROUP = "grupo";
    public static final String ACCEPTED = "accepted";

    public ReplyGrupoRequest(String grupo, boolean accepted) {
        super(ORDEN);
        try {
            this.addParam(PARAM_GROUP, grupo);
            this.addParam(ACCEPTED, String.valueOf(accepted));
        } catch (PaqueteException ex) {
            ServerLog.log(ReplyGrupoRequest.class, ex.getMessage());
        }
    }

}
