package Log;

import PaquetesModels.Paquete;
import PaquetesModels.Param;

/**
 * Clase que imprime el log de los eventos ocurridos en el sistema
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class ServerLog {

    /**
     * Imprime el objeto enviado, junto con un mensaje, a la consola
     * @param sender El objeto que se imprimirá
     * @param message El mensaje que se imprimirá
     */
    public static synchronized void log(Object sender, String message) {
        System.out.println("[" + sender.toString() + "]>" + message);
    }

    /**
     * Imprime el paquete recibido: orden y lista de parámetros, en la consola
     * @param sender El paquete que se imprimirá
     */
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
