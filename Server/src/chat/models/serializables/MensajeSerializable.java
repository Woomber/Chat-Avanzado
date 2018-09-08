package chat.models.serializables;

import java.io.Serializable;

/**
 * Clase serializable para los mensajes enviados
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class MensajeSerializable implements Serializable {

    String origen;
    String mensaje;

    public MensajeSerializable() {
    }

    /**
     * Constructor para datos de nombre de usuario y el nombre de pila del usuario
     * @param username Nombre de usuario
     * @param nombre Nombre de pila
     */
    public MensajeSerializable(String username, String nombre) {
        this.origen = username;
        this.mensaje = nombre;
    }

    /**
     * Obtiene el origen del mensaje
     * @return Retorna el origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Obtiene el mensaje
     * @return Retorna el mensaje
     */
    public String getMensaje() {
        return mensaje;
    }
    
    
}
