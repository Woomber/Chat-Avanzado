package chat.paquetes.responses;

import chat.exceptions.JsonParserException;
import chat.exceptions.PaqueteException;
import chat.json.JsonParser;
import chat.models.Amigos;
import chat.models.Usuario;
import chat.models.UsuarioSerializable;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;
import java.util.ArrayList;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UsuariosResponse extends Paquete {

    public static final String ORDEN = "response-usuarios";

    public static final String USUARIOS = "usuarios";
    public static final String AMIGOS = "amigos";

    private transient final ArrayList<UsuarioSerializable> usuarios;
    private transient final ArrayList<UsuarioSerializable> amigos;

    public UsuariosResponse() {
        super(ORDEN);
        usuarios = new ArrayList<>();
        amigos = new ArrayList<>();
    }

    public void addUsuario(Usuario u, boolean connected) {
        usuarios.add(new UsuarioSerializable(
                u.getNombre_usuario(), u.getNombre_pila(), connected
        ));
    }

    public void addAmigo(Amigos u, boolean connected) {
         usuarios.add(new UsuarioSerializable(
                String.valueOf(u.getId_usuario2()), u.getApodo2(), connected
        ));
    }

    public void finish() {
        try {
            this.addParam(USUARIOS, JsonParser.usuariosToJson(
                    usuarios.toArray(new UsuarioSerializable[usuarios.size()])
            ));
            this.addParam(AMIGOS, JsonParser.usuariosToJson(
                    amigos.toArray(new UsuarioSerializable[amigos.size()])
            ));
        } catch (PaqueteException | JsonParserException ex) {
            ServerLog.log(this, ex.getMessage());
        }

    }

}
