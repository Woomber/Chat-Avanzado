package chat.paquetes.responses;

import chat.exceptions.JsonParserException;
import chat.exceptions.PaqueteException;
import chat.json.JsonParser;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;
import java.util.ArrayList;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class GruposUsuarioResponse extends Paquete {

     // Orden que identifica al tipo de paquete
    public static final String ORDEN = "response-grupos-usuario";

    // Parámetros
    public static final String PARAM_GRUPOS = "grupos";
    
    public static transient ArrayList<Integer> grupos;
    
    public GruposUsuarioResponse() {
        super(ORDEN);
        grupos = new ArrayList<>();       
    }

    /**
     * Función para agregar un grupo a la lista que se envía
     * @param grupo El usuario
     */
    public void addGrupo(int grupo) {
        grupos.add(grupo);
    } 

    /**
     * Función para finalizar las listas de grupos, convertirlas a JSON y
     * agregarlas como parámetros
     */
    public void finish() {
        try {
            this.addParam(PARAM_GRUPOS, JsonParser.arrayToJson(
                    grupos.toArray(new Integer[grupos.size()])
            ));
        } catch (PaqueteException | JsonParserException ex) {
            ServerLog.log(this, ex.getMessage());
        }

    }

    
}
