package Requests;

import Exceptions.PaqueteException;
import PaquetesModels.Paquete;
import Log.ServerLog;

/**
 * Solicitud de Mensaje Grupo
 * 
 * El cliente lo envía cuando desea mandar un mensaje a un grupo
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class MensajeGrupoRequest extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "request-mensaje-grupo";

    // Parámetros
    public static final String PARAM_GROUP = "to";
    public static final String PARAM_MESSAGE = "message";

    public MensajeGrupoRequest(int grupo, String message) {
        super(ORDEN);
        try {
            this.addParam(PARAM_GROUP, String.valueOf(grupo));
            this.addParam(PARAM_MESSAGE, message);
        } catch (PaqueteException ex) {
            ServerLog.log(MensajeGrupoRequest.class, ex.getMessage());
        }
    }

}
