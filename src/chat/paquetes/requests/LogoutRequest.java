package chat.paquetes.requests;

import chat.paquetes.models.Paquete;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class LogoutRequest extends Paquete {

    public static final String ORDEN = "request-logout";

    public LogoutRequest() {
        super(ORDEN);
    }

}
