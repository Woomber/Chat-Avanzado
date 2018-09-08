package ModelsSerializables;

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

    /**
     * Constructor que recibe los datos  para el modelo
     * @param username
     * @param nombre 
     */
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
