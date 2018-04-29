package chat.paquetes.requests;

import chat.exceptions.PaqueteException;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;

/**
 * Solicitud de Grupos
 * 
 * El cliente lo envía cuando quiere solicitar la lista de grupos y mensajes
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class GruposRequest extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "request-grupos";
    public static final String PARAM_GRUPO = "grupo";
    public static final String PARAM_USUARIO = "usuario";

    public GruposRequest(int grupo, String usuario) {
        super(ORDEN);
        try {
            this.addParam(PARAM_GRUPO, String.valueOf(grupo));
            this.addParam(PARAM_USUARIO, usuario);
        } catch (PaqueteException ex) {
            ServerLog.log(AlterGrupoRequest.class, ex.getMessage());
        }

    }

}
