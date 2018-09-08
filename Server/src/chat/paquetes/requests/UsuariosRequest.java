package chat.paquetes.requests;

import chat.paquetes.models.Paquete;

/**
 * Solicitud de Usuarios
 * 
 * El cliente lo envía cuando desea actualizar su lista de usuarios y amigos
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UsuariosRequest extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "request-usuarios";

    public UsuariosRequest() {
        super(ORDEN);
    }

}
