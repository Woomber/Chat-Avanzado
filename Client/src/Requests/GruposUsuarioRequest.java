package Requests;

import PaquetesModels.Paquete;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class GruposUsuarioRequest extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "request-grupos-usuario";

    public GruposUsuarioRequest() {
        super(ORDEN);
    }

}
