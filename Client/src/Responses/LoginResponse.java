package Responses;

import Exceptions.PaqueteException;
import PaquetesModels.Paquete;
import Log.ServerLog;

/**
 * Respuesta de login
 * 
 * Se envía al cliente como respuesta de inicio de sesión
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class LoginResponse extends Paquete {

    // Orden que identifica al tipo de paquete
    public static final String ORDEN = "response-login";

    // Parámetros
    public static final String PARAM_STATUS = "status";

    public LoginResponse(Status status) {
        super(ORDEN);
        try {
            this.addParam(PARAM_STATUS, status.getName());
        } catch (PaqueteException ex) {
            ServerLog.log(LoginResponse.class, ex.getMessage());
        }

    }

    public enum Status {
        CORRECT("correct"),
        TRY_AGAIN("incorrect"),
        REGISTER("register");

        private final String name;

        Status(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
