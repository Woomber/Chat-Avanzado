package chat.server.hilos;

import chat.exceptions.InvalidOperationException;
import chat.json.JsonParser;
import chat.exceptions.JsonParserException;
import chat.paquetes.responses.LoginResponse;
import chat.paquetes.models.Paquete;
import chat.paquetes.responses.GenericResponse;
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

    private Paquete paquete;

    public HiloTransmitter(Socket socket, Vinculo vinculo) {
        this.socket = socket;
        this.vinculo = vinculo;
    }

    public synchronized void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    @Override
    public void run() {
        try {
            this.send(socket, JsonParser.paqueteToJson(paquete));
            Paquete reply = JsonParser.JsonToPaquete(this.get(socket));
            

            String status = reply.getValue(GenericResponse.PARAM_STATUS);
            
            if(GenericResponse.Status.CORRECT.getName().equals(status)){
                // El cliente recibió bien el paquete
                ServerLog.log(this, "Cliente " + socket.toString() +
                        " recibió con éxito " + paquete.toString());
            } else {
                 ServerLog.log(this, "Cliente " + socket.toString() +
                        " recibió con error " + paquete.toString());
            }
            ServerLog.logPaquete(reply);
            
        } catch (JsonParserException | NullPointerException ex) {
            ServerLog.log(this, ex.getMessage());
        }

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
