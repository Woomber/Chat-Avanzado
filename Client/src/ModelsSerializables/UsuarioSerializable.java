package ModelsSerializables;

import java.io.Serializable;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UsuarioSerializable implements Serializable {

    public String username;
    public String nombre;
    public boolean connected;

    public UsuarioSerializable() {
    }

    /**
     * Constructor 
     * @param username
     * @param nombre
     * @param connected 
     */
    public UsuarioSerializable(String username, String nombre, boolean connected) {
        this.username = username;
        this.nombre = nombre;
        this.connected = connected;
    }
}
