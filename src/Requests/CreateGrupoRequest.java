package Requests;

import Exceptions.JsonParserException;
import Exceptions.PaqueteException;
import Json.JsonParser;
import PaquetesModels.Paquete;
import Log.ServerLog;
import java.util.ArrayList;

/**
 * Solicitud de crear grupo
 * 
 * El cliente lo envía para crear un nuevo grupo
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class CreateGrupoRequest extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "request-create-grupo";

    // Parámetros
    public static final String PARAM_MIEMBROS = "miembros";
    public static final String PARAM_NOMBRE_GRUPO = "nombre";

    private transient final ArrayList<String> usernames;
    
    public CreateGrupoRequest(String nombre) {
        super(ORDEN);
        usernames = new ArrayList<>();
        try {
             this.addParam(PARAM_NOMBRE_GRUPO, nombre);
        } catch (PaqueteException ex){
             ServerLog.log(CreateGrupoRequest.class, ex.getMessage());
        }
       
    }

    /**
     * Función para agregar un nombre de usuario a la lista que se envía
     * @param u El usuario
     */
    public void addMiembro(String u) {
        usernames.add(u);
    } 

    /**
     * Función para finalizar las listas de usuarios, convertirlas a JSON y
     * agregarlas como parámetros
     */
    public void finish() {
        try {
            this.addParam(PARAM_MIEMBROS, JsonParser.arrayToJson(
                    usernames.toArray(new String[usernames.size()])
            ));
        } catch (PaqueteException | JsonParserException ex) {
            ServerLog.log(this, ex.getMessage());
        }

    }

}
