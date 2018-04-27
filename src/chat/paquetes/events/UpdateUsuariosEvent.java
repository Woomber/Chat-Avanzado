package chat.paquetes.events;

import chat.paquetes.models.Paquete;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UpdateUsuariosEvent extends Paquete {

    public static final String ORDEN = "event-update-usuarios";

    public UpdateUsuariosEvent() {
        super(ORDEN);
    }

}
