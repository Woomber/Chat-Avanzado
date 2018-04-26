package chat.json;

import chat.exceptions.JsonParserException;
import chat.paquetes.models.Paquete;
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
    
    public static String paqueteToJson(Paquete paquete) throws JsonParserException {
        String json;
        
        try {
            json = JSON.toJson(paquete);
        } catch(Exception ex){
            throw new JsonParserException(ex.getMessage());
        }
        
        ServerLog.log(JsonParser.class,
                "Convertido " + paquete.toString() + " a JSON: " + json);
        return json;
    }
    
    public static Paquete JsonToPaquete(String json) throws JsonParserException {
        Paquete mensaje;
        
        try {
            mensaje = JSON.fromJson(json, Paquete.class);
        } catch(Exception ex){
            throw new JsonParserException(ex.getMessage());
        }
        
        ServerLog.log(JsonParser.class,
                "Convertido JSON a " + mensaje.toString() + " > " + json);
        ServerLog.logPaquete(mensaje);
        return mensaje;
    }

}
