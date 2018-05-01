package Json;

import Exceptions.JsonParserException;
import ModelsSerializables.MensajeSerializable;
import ModelsSerializables.UsuarioSerializable;
import PaquetesModels.Paquete;
import Log.ServerLog;
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
     *
     * @param paquete El paquete a convertir
     * @return La cadena con la estructura JSON
     * @throws JsonParserException En caso de que el paquete no se pueda
     * serializar
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
     *
     * @param json El JSON a convertir
     * @return El paquete resultante
     * @throws JsonParserException En caso de que la cadena no sea JSON válido
     */
    public static Paquete jsonToPaquete(String json) throws JsonParserException {
        Paquete paquete;

        try {
            paquete = JSON.fromJson(json, Paquete.class);
            ServerLog.log(JsonParser.class,
                    "Convertido JSON a " + paquete.toString() + " > " + json);
            ServerLog.logPaquete(paquete);
        } catch (JsonSyntaxException | NullPointerException ex) {
            throw new JsonParserException(ex.getMessage());
        }

        return paquete;
    }

    /**
     * Convierte un arreglo de objetos a JSON
     *
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
     *
     * @param json La estructura JSON a convertir
     * @return El arreglo resultante
     * @throws JsonParserException En caso de recibir JSON inválido
     */
    public static UsuarioSerializable[] jsonToUsuarios(String json) throws JsonParserException {
        UsuarioSerializable[] usuario;

        try {
            usuario = JSON.fromJson(json, UsuarioSerializable[].class);
        } catch (JsonSyntaxException | NullPointerException ex) {
            throw new JsonParserException(ex.getMessage());
        }

        ServerLog.log(JsonParser.class,
                "Convertido JSON a " + usuario.toString() + " > " + json);
        return usuario;
    }

    /**
     * Convierte una cadena JSON a un arreglo de Cadenas
     *
     * @param json La estructura JSON a convertir
     * @return El arreglo resultante
     * @throws JsonParserException En caso de recibir JSON inválido
     */
    public static String[] jsonToStrings(String json) throws JsonParserException {
        String[] strings;

        try {
            strings = JSON.fromJson(json, String[].class);
        } catch (JsonSyntaxException | NullPointerException ex) {
            throw new JsonParserException(ex.getMessage());
        }

        ServerLog.log(JsonParser.class,
                "Convertido JSON a " + strings.toString() + " > " + json);
        return strings;
    }
    
        /**
     * Convierte una cadena JSON a un arreglo de Enteros
     *
     * @param json La estructura JSON a convertir
     * @return El arreglo resultante
     * @throws JsonParserException En caso de recibir JSON inválido
     */
    public static Integer[] jsonToIntegers(String json) throws JsonParserException {
        Integer[] ints;

        try {
            ints = JSON.fromJson(json, Integer[].class);
        } catch (JsonSyntaxException | NullPointerException ex) {
            throw new JsonParserException(ex.getMessage());
        }

        ServerLog.log(JsonParser.class,
                "Convertido JSON a " + ints.toString() + " > " + json);
        return ints;
    }
    
        /**
     * Convierte una cadena JSON a un arreglo de Mensajes
     *
     * @param json La estructura JSON a convertir
     * @return El arreglo resultante
     * @throws JsonParserException En caso de recibir JSON inválido
     */
    public static MensajeSerializable[] jsonToMensajes(String json) throws JsonParserException {
        MensajeSerializable[] mensajes;

        try {
            mensajes = JSON.fromJson(json, MensajeSerializable[].class);
        } catch (JsonSyntaxException | NullPointerException ex) {
            throw new JsonParserException(ex.getMessage());
        }

        ServerLog.log(JsonParser.class,
                "Convertido JSON a " + mensajes.toString() + " > " + json);
        return mensajes;
    }

    public static Paquete JsonToPaquete(String readLine) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
