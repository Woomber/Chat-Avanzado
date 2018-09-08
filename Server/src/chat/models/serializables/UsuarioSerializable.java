package chat.models.serializables;

import java.io.Serializable;

/**
 * Clase serializable para los usuarios enviados
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UsuarioSerializable implements Serializable {

    String username;
    String nombre;
    boolean connected;

    public UsuarioSerializable() {
    }

    /**
     * Inicaliza los valores para la serialización de usuarios
     * @param username El nombre de usuario
     * @param nombre El nombre de pila
     * @param connected Estado si está conectado o no
     */
    public UsuarioSerializable(String username, String nombre, boolean connected) {
        this.username = username;
        this.nombre = nombre;
        this.connected = connected;
    }
}
