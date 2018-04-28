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

    /**
     * Convierte un objeto de tipo Paquete a una estructura JSON
     * @param paquete El paquete a convertir
     * @return La cadena con la estructura JSON
     * @throws JsonParserException En caso de que el paquete no se pueda serializar
     */
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

    /**
     * Convierte una cadena JSON a un objeto tipo Paquete
     * @param json El JSON a convertir
     * @return El paquete resultante
     * @throws JsonParserException En caso de que la cadena no sea JSON válido
     */
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

    /**
     * Convierte un arreglo de objetos a JSON
     * @param objetos El arreglo a convertir
     * @return La cadena con la estructura JSON
     * @throws JsonParserException En caso de no poder convertir el arreglo
     */
    public static String arrayToJson(Object[] objetos) throws JsonParserException {
        String json;

        try {
            json = JSON.toJson(objetos);
        } catch (Exception ex) {
            throw new JsonParserException(ex.getMessage());
        }

        ServerLog.log(JsonParser.class,
                "Convertido " + objetos.toString() + " a JSON: " + json);
        return json;
    }

    /**
     * Convierte una cadena JSON a un arreglo de Usuarios
     * @param json La estructura JSON a convertir
     * @return El arreglo resultante
     * @throws JsonParserException En caso de recibir JSON inválido
     */
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
