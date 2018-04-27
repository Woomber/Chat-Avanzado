package chat.paquetes.requests;

import chat.exceptions.PaqueteException;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class LoginRequest extends Paquete {

    public static final String ORDEN = "request-login";

    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";

    public LoginRequest(String username, String password) {
        super(ORDEN);
        try {
            this.addParam(PARAM_USERNAME, username);
            this.addParam(PARAM_PASSWORD, password);
        } catch (PaqueteException ex) {
            ServerLog.log(LoginRequest.class, ex.getMessage());
        }

    }

}
