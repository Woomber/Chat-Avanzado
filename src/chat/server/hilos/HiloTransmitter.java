package chat.server.hilos;

import chat.json.JsonParser;
import chat.exceptions.JsonParserException;
import chat.paquetes.models.Paquete;
import chat.paquetes.responses.GenericResponse;
import chat.server.log.ServerLog;
import chat.server.vinculo.Vinculo;
import java.io.IOException;
import java.net.Socket;

/**
 * Hilo encargado de transmitir información primero al cliente
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class HiloTransmitter extends Hilo implements Runnable {

    public Vinculo vinculo;
    private Socket socket;

    private Paquete paquete;

    /**
     * Constructor para el Vínculo del hilo
     * @param vinculo Vínculo
     */
    public HiloTransmitter(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    public synchronized void setSocket(Socket socket){
        this.socket = socket;
    }
    
    /**
     * Establece el paquete que se va a enviar al socket
     * @param paquete El paquete a enviar
     */
    public synchronized void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    /**
     * Realiza el envío de paquetes y procesa la respuesta
     */
    @Override
    public void run() {
        try {
            this.send(socket, JsonParser.paqueteToJson(paquete));
            Paquete reply = JsonParser.jsonToPaquete(this.get(socket));
            

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

    /**
     * Detiene el hilo
     */
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
