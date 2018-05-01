package Requests;

import PaquetesModels.Paquete;

/**
 * Solicitud de Logout
 * 
 * El cliente lo solicita cuando requiere cerrar sesión
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class LogoutRequest extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "request-logout";

    public LogoutRequest() {
        super(ORDEN);
    }

}
