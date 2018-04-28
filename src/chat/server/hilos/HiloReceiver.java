package chat.server.hilos;

import chat.exceptions.InvalidOperationException;
import chat.json.JsonParser;
import chat.exceptions.JsonParserException;
import chat.paquetes.requests.LoginRequest;
import chat.paquetes.responses.LoginResponse;
import chat.paquetes.requests.MensajeRequest;
import chat.paquetes.models.Paquete;
import chat.paquetes.responses.GenericResponse;
import chat.server.handlers.LoginHandler;
import chat.server.log.ServerLog;
import chat.server.vinculo.Vinculo;
import chat.server.vinculo.VinculoList;
import java.io.IOException;
import java.net.Socket;

/**
 * Hilo encargado de recibir solicitudes del cliente
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

    /**
     * Lee el socket y convierte el JSON a paquete para enviarlo a operation
     */
    private void getJson() {
        try {
            String json = this.get(socket);
            Paquete paquete = JsonParser.JsonToPaquete(json);
            this.operation(paquete);
        } catch (JsonParserException ex) {
            ServerLog.log(this, "Error procesando solicitud, cerrando " + socket.toString());
            this.stop();
        }
    }

    /**
     * Selecciona la operación a realizar según la solicitud recibida
     *
     * @param paquete El paquete con la solicitud
     */
    private void operation(Paquete paquete) {

        // Donde se guardará la respuesta para el cliente
        Paquete response;

        switch (paquete.getOrden()) {

            // ============================================================== //
            case LoginRequest.ORDEN:

                String username = paquete.getValue(LoginRequest.PARAM_USERNAME);

                // Crear el manejador de login
                LoginHandler handler = new LoginHandler(
                        username,
                        paquete.getValue(LoginRequest.PARAM_PASSWORD)
                );

                // Guarda un vínculo que ya tenga ese nombre de usuario, si hay
                Vinculo repeated = VinculoList.get(username);

                // Pone el nombre de usuario recibido en el vínculo actual
                this.vinculo.setUsername(username);

                /*
                Ejecutar handler, retorna si fue correcto o no el login
                Llamar a intento de login en vínculo, retorna si puede reintentar
                 */
                boolean retry = this.vinculo.attemptedLogin(handler.run());

                LoginResponse.Status status;

                // Si HiloTx no es nulo, significa que el login fue correcto
                if (this.vinculo.getHiloTx() != null) {
                    status = LoginResponse.Status.CORRECT;
                    /*
                    Si repetido no es nulo, significa que ya se había iniciado
                    sesión con ese usuario, cerrar la sesión antigua
                    */
                    if (repeated != null) {
                        repeated.getHiloTx().stop();
                        repeated.getHiloRx().stop();
                        VinculoList.remove(repeated);
                    }
                } else {
                    // Según el valor de retry, enviar volver a intentar o registro
                    status = retry
                            ? LoginResponse.Status.TRY_AGAIN
                            : LoginResponse.Status.REGISTER;
                }

                response = new LoginResponse(status);
                break;

            // ============================================================== //
                
            case MensajeRequest.ORDEN:
                
                response = new GenericResponse(GenericResponse.Status.CORRECT);
                break;
                
            // ============================================================== //
                
            default:
                final String mensajeError = "Operación inválida ["
                        + paquete.getOrden() + "] en " + socket.toString();
                ServerLog.log(this, mensajeError);

                response = new GenericResponse(GenericResponse.Status.BAD_REQUEST);
        }

        // Envía la respuesta
        try {
            this.send(socket, JsonParser.paqueteToJson(response));
        } catch (JsonParserException ex) {
            ServerLog.log(this, ex.getMessage());
        }
    }

    /**
     * Detener el hilo
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
