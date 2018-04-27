package chat.json;

import chat.exceptions.JsonParserException;
import chat.models.UsuarioSerializable;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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
        } catch (Exception ex) {
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
        } catch (JsonSyntaxException ex) {
            throw new JsonParserException(ex.getMessage());
        }

        ServerLog.log(JsonParser.class,
                "Convertido JSON a " + mensaje.toString() + " > " + json);
        ServerLog.logPaquete(mensaje);
        return mensaje;
    }

    public static String usuariosToJson(UsuarioSerializable[] usuario) throws JsonParserException {
        String json;

        try {
            json = JSON.toJson(usuario);
        } catch (Exception ex) {
            throw new JsonParserException(ex.getMessage());
        }

        ServerLog.log(JsonParser.class,
                "Convertido " + usuario.toString() + " a JSON: " + json);
        return json;
    }

    public static UsuarioSerializable[] JsonToUsuarios(String json) throws JsonParserException {
        UsuarioSerializable[] usuario;

        try {
            usuario = JSON.fromJson(json, UsuarioSerializable[].class);
        } catch (JsonSyntaxException ex) {
            throw new JsonParserException(ex.getMessage());
        }

        ServerLog.log(JsonParser.class,
                "Convertido JSON a " + usuario.toString() + " > " + json);
        return usuario;
    }

}
