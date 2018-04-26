package chat.server.hilos;

import chat.exceptions.InvalidOperationException;
import chat.json.JsonParser;
import chat.exceptions.JsonParserException;
import chat.paquetes.LoginResponse;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;
import chat.server.vinculo.Vinculo;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class HiloTransmitter extends Hilo implements Runnable {

    public Vinculo vinculo;
    private final Socket socket;

    public HiloTransmitter(Socket socket, Vinculo vinculo) {
        this.socket = socket;
        this.vinculo = vinculo;
    }

    @Override
    public void run() {
        // Transmisión a cliente
    }

    @Override
    public void stop() {
        super.stop();
        try {
            socket.close();
            ServerLog.log(this, socket.toString() + " cerrado");
        } catch (IOException ex) {
            ServerLog.log(this, "Error cerrando " + socket.toString()
                    + ": " + ex.getMessage());
        }

    }
}
