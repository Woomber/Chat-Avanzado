package chat.server.hilos;

import chat.server.log.ServerLog;
import chat.server.vinculo.Vinculo;
import chat.server.vinculo.VinculoList;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hilo encargado de recibir nuevas conexiones y crear nuevos hilos
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class HiloFactory extends Hilo implements Runnable {

    public static final int PORT_RX = 90;
    public static final int PORT_TX = 91;

    private final ServerSocket server;

    public HiloFactory() throws IOException {
        try {
            server = new ServerSocket(PORT_RX);
        } catch(IOException ex){
            throw ex;
        }    
    }

    /**
     * Función que espera para recibir conexiones
     */
    @Override
    public void run() {
        while (continuar) {
            ServerLog.log(this, "Listo para recibir socket nuevo");
            try {
                // Socket de conexión nueva
                Socket client = server.accept();
                ServerLog.log(this, "Aceptado " + client.toString());
                
                // Crea un nuevo vínculo para la conexión
                Vinculo v = new Vinculo(client);
                v.start();
                VinculoList.add(v);
                
            } catch (IOException ex) {
                ServerLog.log(this, "Error al aceptar conexión: " + ex.getMessage());
            }
        }
    }

    /**
     * Función que cierra la conexión al hilo actual
     */
    @Override
    public void stop() {
        super.stop();
        try {
            server.close();
            ServerLog.log(this, server.toString() + " cerrado");
        } catch (IOException ex) {
            ServerLog.log(this, "Error cerrando " + server.toString()
                    + ": " + ex.getMessage());
        }
    }

}
