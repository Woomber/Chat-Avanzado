package chat.server.hilos;

import chat.exceptions.InvalidOperationException;
import chat.json.JsonParser;
import chat.exceptions.JsonParserException;
import chat.paquetes.LoginResponse;
import chat.paquetes.models.Paquete;
import chat.server.log.ServerLog;
import java.io.IOException;
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
            Paquete mensaje = JsonParser.JsonToMensaje(json);
            this.operation(mensaje);
        } catch (JsonParserException | InvalidOperationException ex) {
            ServerLog.log(this, "Error procesando solicitud, cerrando " + socket.toString());
            this.stop();
        }
    }

    private void operation(Paquete mensaje) throws InvalidOperationException {
        switch (mensaje.getOrden()) {
            case "login":
                /* Ejemplo
                this.send(socket, JsonParser.MensajeToJson((Paquete)
                        new LoginResponse(mensaje.getValue("username"),
                        LoginResponse.Status.TRY_AGAIN)
                ));*/
                break;
            case "enviar":

                break;
            default:
                final String mensajeError = "Operación inválida ["
                        + mensaje.getOrden() + "] en " + socket.toString();
                ServerLog.log(this, mensajeError);
                throw new InvalidOperationException(mensajeError);
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
