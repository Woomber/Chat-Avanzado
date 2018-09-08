package Requests;

import Exceptions.PaqueteException;
import PaquetesModels.Paquete;
import Log.ServerLog;

/**
 * Solicitud de Grupos
 * 
 * El cliente lo envía cuando quiere solicitar la lista de grupos y mensajes
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class GrupoRequest extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "request-grupo";
    
    // Parámetros
    public static final String PARAM_GRUPO = "grupo";

    public GrupoRequest(int grupo) {
        super(ORDEN);
        try {
            this.addParam(PARAM_GRUPO, String.valueOf(grupo));
        } catch (PaqueteException ex) {
            ServerLog.log(AlterGrupoRequest.class, ex.getMessage());
        }

    }

}
