package chat.paquetes.events;

import chat.paquetes.models.Paquete;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UpdateGruposEvent extends Paquete {

    public static final String ORDEN = "event-update-grupos";
    
    public UpdateGruposEvent() {
        super(ORDEN);
    }

}
