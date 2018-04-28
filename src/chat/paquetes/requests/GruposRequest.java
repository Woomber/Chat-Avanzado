package chat.paquetes.requests;

import chat.paquetes.models.Paquete;

/**
 * Solicitud de Grupos
 * 
 * El cliente lo envía cuando quiere solicitar la lista de grupos y mensajes
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class GruposRequest extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "request-grupos";

    public GruposRequest() {
        super(ORDEN);
    }

}
