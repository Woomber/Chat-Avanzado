package chat.json;

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
    
    public static String MensajeToJson(Mensaje mensaje) throws Exception {
        String json = JSON.toJson(mensaje);
        ServerLog.log(JsonParser.class,
                "Convertido " + mensaje.toString() + " a JSON: " + json);
        return json;
    }
    
    public static Mensaje JsonToMensaje(String json) throws Exception {
        Mensaje mensaje = JSON.fromJson(json, Mensaje.class);
        ServerLog.log(JsonParser.class,
                "Convertido JSON a " + mensaje.toString() + " > " + json);
        ServerLog.logMensaje(mensaje);
        return mensaje;
    }

}
