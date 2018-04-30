package chat.models.serializables;

import java.io.Serializable;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class MensajeSerializable implements Serializable {

    String origen;
    String mensaje;

    public MensajeSerializable() {
    }

    public MensajeSerializable(String username, String nombre) {
        this.origen = username;
        this.mensaje = nombre;
    }

    public String getOrigen() {
        return origen;
    }

    public String getMensaje() {
        return mensaje;
    }
    
    
}
