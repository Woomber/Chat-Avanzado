package chat.server.log;

import chat.paquetes.models.Paquete;
import chat.paquetes.models.Param;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class ServerLog {

    public static synchronized void log(Object sender, String message) {
        System.out.println("[" + sender.toString() + "]>" + message);
    }

    public static void logPaquete(Paquete sender) {
        StringBuilder builder = new StringBuilder();
        
        if(sender == null){
            ServerLog.log(ServerLog.class, "Paquete nulo recibido");
            return;
        }
        
        builder.append("Mostrando valores");
        for (Param p : sender.getParams()) {
            builder.append("\n\t");
            builder.append(p.getNombre());
            builder.append(" = ");
            builder.append(p.getValor());
        }
        ServerLog.log(sender, builder.toString());
    }
}
