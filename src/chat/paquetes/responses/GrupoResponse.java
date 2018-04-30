package chat.paquetes.responses;

import chat.exceptions.JsonParserException;
import chat.exceptions.PaqueteException;
import chat.json.JsonParser;
import chat.models.serializables.MensajeSerializable;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;
import java.util.ArrayList;

/**
 * Respuesta de grupo
 * 
 * Se envía al cliente cuando se requiera la información de los grupos a los
 * que pertenece el usuario, sus miembros, los mensajes sin leer y el estado de
 * su solicitud
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class GrupoResponse extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "response-grupo";

    // Parámetros
    public static final String PARAM_ID_GRUPO = "id";
    public static final String PARAM_MIEMBROS = "miembros";
    public static final String PARAM_NOMBRE_GRUPO = "nombre";
    public static final String PARAM_MENSAJES = "not-read-msg";
    public static final String PARAM_STATUS = "status";

    private transient final ArrayList<String> usernames;
    private transient final ArrayList<MensajeSerializable> mensajes;
    
    public GrupoResponse(int id, String nombre, Status status) {
        super(ORDEN);
        usernames = new ArrayList<>();
        mensajes = new ArrayList<>();
        try {
            this.addParam(PARAM_ID_GRUPO, String.valueOf(id));
            this.addParam(PARAM_NOMBRE_GRUPO, nombre);
            this.addParam(PARAM_STATUS, status.getName());
        } catch (PaqueteException ex){
             ServerLog.log(GrupoResponse.class, ex.getMessage());
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
     * Función para agregar un mensaje a la lista que se envía
     * @param origen El remitente del mensaje
     * @param mensaje El mensaje
     */
    public void addMensaje(String origen, String mensaje){
        mensajes.add(new MensajeSerializable(origen, mensaje));
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
            this.addParam(PARAM_MENSAJES, JsonParser.arrayToJson(
                    mensajes.toArray(new MensajeSerializable[mensajes.size()])
            ));
        } catch (PaqueteException | JsonParserException ex) {
            ServerLog.log(this, ex.getMessage());
        }

    }
    
     public enum Status {
        IN_GROUP("in"),
        PENDING("pending");

        private final String name;

        Status(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
