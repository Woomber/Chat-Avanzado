package chat.paquetes;

import chat.exceptions.PaqueteException;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class LoginResponse extends Paquete {

    public static final String ORDEN = "loginResponse";

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
