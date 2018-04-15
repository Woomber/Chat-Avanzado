package chat.mensajes;

import chat.mensajes.models.Mensaje;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class MensajeEnviar extends Mensaje {
    private static final String ORDEN = "send";
    
    private static final String PARAM_TO = "to";
    private static final String PARAM_FROM = "from";
    private static final String PARAM_MESSAGE = "message";
    
    public MensajeEnviar(String to, String from, String message){
        super(ORDEN);
        this.addParam(PARAM_TO, to);
        this.addParam(PARAM_FROM, from);
        this.addParam(PARAM_MESSAGE, message);
    }
    
}
