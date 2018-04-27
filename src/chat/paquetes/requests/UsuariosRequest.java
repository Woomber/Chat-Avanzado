package chat.paquetes.requests;

import chat.paquetes.models.Paquete;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UsuariosRequest extends Paquete {

    public static final String ORDEN = "request-usuarios";

    public UsuariosRequest() {
        super(ORDEN);
    }

}
