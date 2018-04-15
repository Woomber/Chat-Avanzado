package chat.mensajes;

import chat.mensajes.models.Mensaje;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class MensajeLoginResponse extends Mensaje {
    private static final String ORDEN = "loginResponse";
    
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_STATUS = "status";
    
    public MensajeLoginResponse(String username, Status status){
        super(ORDEN);
        this.addParam(PARAM_USERNAME, username);
        this.addParam(PARAM_STATUS, status.getName());
    }
    
    public enum Status {
        CORRECT ("correct"),
        TRY_AGAIN ("incorrect"),
        REGISTER ("register");
        
        private final String name;
        
        Status(String name){
            this.name = name;
        }
        
        public String getName(){
            return name;
        }
    }
    
}
