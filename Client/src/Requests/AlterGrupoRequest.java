package Requests;

import Exceptions.PaqueteException;
import PaquetesModels.Paquete;
import Log.ServerLog;

/**
 * Solicitud de Modificar Grupo
 * 
 * El cliente lo envía para agregar o eliminar un usuario a un grupo
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class AlterGrupoRequest extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "request-alter-grupo";

    // Parámetros
    public static final String PARAM_OPERACION = "op";
    public static final String PARAM_GRUPO = "grupo";
    public static final String PARAM_USUARIO = "usuario";

    public AlterGrupoRequest(int grupo, String usuario, Operacion operacion) {
        super(ORDEN);
        try {
            this.addParam(PARAM_GRUPO, String.valueOf(grupo));
            this.addParam(PARAM_USUARIO, usuario);
            this.addParam(PARAM_OPERACION, operacion.getName());
        } catch (PaqueteException ex) {
            ServerLog.log(AlterGrupoRequest.class, ex.getMessage());
        }

    }

    public enum Operacion {
        ADD("add"),
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
