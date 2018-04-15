package chat.server.log;

import chat.mensajes.models.Mensaje;
import chat.mensajes.models.Param;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class ServerLog {
    
    public static void log(Object sender, String message){
        System.out.println("[" + sender.toString() + "]>" + message);
    }
    
     public static void logMensaje(Mensaje sender){
        StringBuilder builder = new StringBuilder();
        builder.append("Mostrando valores");
        for(Param p : sender.getParams()){
            builder.append("\n\t");
            builder.append(p.getNombre());
            builder.append(" = ");
            builder.append(p.getValor());
        }
        ServerLog.log(sender, builder.toString());
    }
}
