package chat.json;

import chat.exceptions.JsonParserException;
import chat.mensajes.models.Mensaje;
import chat.server.log.ServerLog;
import com.google.gson.Gson;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class JsonParser {
    
    private static final Gson JSON;
    
    static {
        JSON = new Gson();
    }
    
    public static String MensajeToJson(Mensaje mensaje) throws JsonParserException {
        String json;
        
        try {
            json = JSON.toJson(mensaje);
        } catch(Exception ex){
            throw new JsonParserException(ex.getMessage());
        }
        
        ServerLog.log(JsonParser.class,
                "Convertido " + mensaje.toString() + " a JSON: " + json);
        return json;
    }
    
    public static Mensaje JsonToMensaje(String json) throws JsonParserException {
        Mensaje mensaje;
        
        try {
            mensaje = JSON.fromJson(json, Mensaje.class);
        } catch(Exception ex){
            throw new JsonParserException(ex.getMessage());
        }
        
        ServerLog.log(JsonParser.class,
                "Convertido JSON a " + mensaje.toString() + " > " + json);
        ServerLog.logMensaje(mensaje);
        return mensaje;
    }

}
