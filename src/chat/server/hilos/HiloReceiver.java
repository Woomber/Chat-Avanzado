package chat.server.hilos;

import chat.exceptions.InvalidOperationException;
import chat.json.JsonParser;
import chat.exceptions.JsonParserException;
import chat.paquetes.requests.LoginRequest;
import chat.paquetes.responses.LoginResponse;
import chat.paquetes.requests.MensajeRequest;
import chat.paquetes.models.Paquete;
import chat.server.handlers.LoginHandler;
import chat.server.log.ServerLog;
import chat.server.vinculo.Vinculo;
import chat.server.vinculo.VinculoList;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class HiloReceiver extends Hilo implements Runnable {

    private final Vinculo vinculo;
    private final Socket socket;

    public HiloReceiver(Socket socket, Vinculo vinculo) {
        this.vinculo = vinculo;
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
            Paquete mensaje = JsonParser.JsonToPaquete(json);
            this.operation(mensaje);
        } catch (JsonParserException | InvalidOperationException ex) {
            ServerLog.log(this, "Error procesando solicitud, cerrando " + socket.toString());
            this.stop();
        }
    }

    private void operation(Paquete mensaje) throws InvalidOperationException {
        switch (mensaje.getOrden()) {

            case LoginRequest.ORDEN:
                String username = mensaje.getValue(LoginRequest.PARAM_USERNAME);
                
                LoginHandler handler = new LoginHandler(
                        username,
                        mensaje.getValue(LoginRequest.PARAM_PASSWORD)
                );
                
                Vinculo repeated = VinculoList.get(username);
                this.vinculo.setUsername(username);
                
                boolean retry = this.vinculo.attemptedLogin(handler.run());
                
                LoginResponse.Status status;

                if(this.vinculo.getHiloTx() != null){
                    status = LoginResponse.Status.CORRECT;
                    if(repeated != null){
                        repeated.getHiloTx().stop();
                        repeated.getHiloRx().stop();
                        VinculoList.remove(repeated);
                    }
                } else {
                    status = retry ?
                            LoginResponse.Status.TRY_AGAIN :
                            LoginResponse.Status.REGISTER;
                }
                try {
                    this.send(socket,
                            JsonParser.paqueteToJson(new LoginResponse(status)));
                } catch(JsonParserException ex){
                    ServerLog.log(this, ex.getMessage());
                }
                break;

            case MensajeRequest.ORDEN:

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
