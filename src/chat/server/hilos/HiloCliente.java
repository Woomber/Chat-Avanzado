package chat.server.hilos;

import chat.json.JsonParser;
import chat.mensajes.MensajeLoginResponse;
import chat.mensajes.models.Mensaje;
import chat.server.log.ServerLog;
import java.net.Socket;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class HiloCliente extends Hilo implements Runnable {

    public String username;
    private final Socket socket;

    public HiloCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (continuar) {
            this.getJson();
        }
    }

    private void getJson() {
        try {
            String json = this.get(socket);
            Mensaje mensaje = JsonParser.JsonToMensaje(json);
            this.operation(mensaje);
        } catch (Exception ex) {
            ServerLog.log(this, "Error procesando solicitud, cerrando " + socket.toString());
            this.stop();
        }
    }

    private void operation(Mensaje mensaje) throws Exception {
        switch (mensaje.getOrden()) {
            case "login":
                this.send(socket, JsonParser.MensajeToJson((Mensaje)
                        new MensajeLoginResponse(mensaje.getValue("username"),
                        MensajeLoginResponse.Status.TRY_AGAIN)
                ));
                break;
            case "enviar":

                break;
            default:
                ServerLog.log(this, "Operación inválida ["
                        + mensaje.getOrden() + "] en " + socket.toString());

        }
    }

    @Override
    public void stop() {
        super.stop();
        try {
            socket.close();
        } catch (Exception ex) {
            ServerLog.log(this, "Error cerrando " + socket.toString()
                    + ": " + ex.getMessage());
        }

    }
}
