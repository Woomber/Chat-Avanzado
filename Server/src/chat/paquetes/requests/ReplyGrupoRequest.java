package chat.paquetes.requests;

import chat.exceptions.PaqueteException;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;

/**
 * Solicitud de Respuesta de Grupo
 * 
 * El cliente lo envía cuando desea aceptar o rechazar una solicitud de 
 * unirse a un grupo
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class ReplyGrupoRequest extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "request-reply-grupo";

    // Parámetros
    public static final String PARAM_GROUP = "grupo";
    public static final String ACCEPTED = "accepted";

    /**
     * Inicaliza los valores para la operación
     * @param grupo Identificador del grupo
     * @param accepted Indica si se acepta o no
     */
    public ReplyGrupoRequest(int grupo, boolean accepted) {
        super(ORDEN);
        try {
            this.addParam(PARAM_GROUP, String.valueOf(grupo));
            this.addParam(ACCEPTED, String.valueOf(accepted));
        } catch (PaqueteException ex) {
            ServerLog.log(ReplyGrupoRequest.class, ex.getMessage());
        }
    }

}
