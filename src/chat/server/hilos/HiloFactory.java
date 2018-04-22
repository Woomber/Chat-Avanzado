package chat.server.hilos;

import chat.server.log.ServerLog;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class HiloFactory extends Hilo implements Runnable {

    private static final int PORT = 90;

    private final ServerSocket server;

    public HiloFactory() throws IOException {
        server = new ServerSocket(PORT);
    }

    @Override
    public void run() {
        while (continuar) {
            ServerLog.log(this, "Listo para recibir socket nuevo");
            try {
                Socket client = server.accept();
                ServerLog.log(this, "Aceptado " + client.toString());
                new Thread(new HiloCliente(client)).start();

            } catch (IOException ex) {
                ServerLog.log(this, "Error al aceptar conexión: " + ex.getMessage());
            }
        }
    }
    
    @Override
    public void stop(){
        super.stop();
         try {
            server.close();
        } catch(Exception ex){
            ServerLog.log(this, "Error cerrando " + server.toString()
                    + ": " + ex.getMessage());
        }
    }

}
