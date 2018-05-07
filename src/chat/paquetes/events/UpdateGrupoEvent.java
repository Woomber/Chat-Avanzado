package chat.paquetes.events;

import chat.exceptions.PaqueteException;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;

/**
 * Evento de actualizar Grupos
 * 
 * Se envía al cliente cuando se necesita que se actualicen los grupos
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UpdateGrupoEvent extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "event-update-grupos";
    
    // Parámetros
    public static final String PARAM_GRUPO = "grupo"; 
    
    public UpdateGrupoEvent(int grupo) {
        super(ORDEN);
        try {
            this.addParam(PARAM_GRUPO, String.valueOf(grupo));
        } catch(PaqueteException ex){
            ServerLog.log(UpdateGrupoEvent.this, ex.getMessage());
        }
        
    }

}
