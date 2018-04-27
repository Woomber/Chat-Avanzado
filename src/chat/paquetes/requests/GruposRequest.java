package chat.paquetes.requests;

import chat.paquetes.models.Paquete;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class GruposRequest extends Paquete {

    public static final String ORDEN = "request-grupos";

    public GruposRequest() {
        super(ORDEN);
    }

}
