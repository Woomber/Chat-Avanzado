package chat.paquetes.events;

import chat.paquetes.models.Paquete;

/**
 * Evento de actualizar Usuarios
 * 
 * Se envía al cliente cuando se necesitan actualizar los usuarios
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UpdateUsuariosEvent extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "event-update-usuarios";

    public UpdateUsuariosEvent() {
        super(ORDEN);
    }

}
