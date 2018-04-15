package chat.mensajes;

import chat.mensajes.models.Mensaje;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class MensajeLogin extends Mensaje {
    private static final String ORDEN = "login";
    
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    
    public MensajeLogin(String username, String password){
        super(ORDEN);
        this.addParam(PARAM_USERNAME, username);
        this.addParam(PARAM_PASSWORD, password);
    }
    
}
