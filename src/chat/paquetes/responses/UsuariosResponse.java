package chat.paquetes.responses;

import chat.exceptions.JsonParserException;
import chat.exceptions.PaqueteException;
import chat.json.JsonParser;
import chat.models.Amigo;
import chat.models.Usuario;
import chat.models.serializables.UsuarioSerializable;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;
import java.util.ArrayList;

/**
 * Respuesta de Usuarios
 * 
 * Se envía al cliente cuando se requiere las listas de los usuarios
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UsuariosResponse extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "response-usuarios";

    // Parámetros
    public static final String USUARIOS = "usuarios";
    public static final String AMIGOS = "amigos";

    private transient final ArrayList<UsuarioSerializable> usuarios;
    private transient final ArrayList<UsuarioSerializable> amigos;

    public UsuariosResponse() {
        super(ORDEN);
        usuarios = new ArrayList<>();
        amigos = new ArrayList<>();
    }

    /**
     * Función para agregar un usuario a la lista que se envía
     * @param u El usuario
     * @param connected Su estado (conectado, desconectado)
     */
    public void addUsuario(Usuario u, boolean connected) {
        usuarios.add(new UsuarioSerializable(
                u.getId_usuario(), u.getNombre(), connected
        ));
    }

     /**
     * Función para agregar un amigo a la lista que se envía
     * @param u El usuario amigo
     * @param connected Su estado (conectado, desconectado)
     */
    public void addAmigo(Amigo u, boolean connected) {
         usuarios.add(new UsuarioSerializable(
                String.valueOf(u.getAmigo()), u.getApodo(), connected
        ));
    }

    /**
     * Función para finalizar las listas de usuarios, convertirlas a JSON y
     * agregarlas como parámetros
     */
    public void finish() {
        try {
            this.addParam(USUARIOS, JsonParser.arrayToJson(
                    usuarios.toArray(new UsuarioSerializable[usuarios.size()])
            ));
            this.addParam(AMIGOS, JsonParser.arrayToJson(
                    amigos.toArray(new UsuarioSerializable[amigos.size()])
            ));
        } catch (PaqueteException | JsonParserException ex) {
            ServerLog.log(this, ex.getMessage());
        }

    }

}
