package PaquetesEvents;

import PaquetesModels.Paquete;

/**
 * Evento de actualizar Grupos
 * 
 * Se envía al cliente cuando se necesita que se actualicen los grupos
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UpdateGruposEvent extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "event-update-grupos";
    
    public UpdateGruposEvent() {
        super(ORDEN);
    }

}
