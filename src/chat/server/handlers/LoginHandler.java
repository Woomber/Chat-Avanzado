package chat.server.handlers;

import chat.paquetes.models.Paquete;
import chat.paquetes.requests.LoginRequest;
import chat.paquetes.responses.LoginResponse;
import chat.server.database.UsuarioConnector;
import chat.server.hilos.HiloFactory;
import chat.server.log.ServerLog;
import chat.server.vinculo.Vinculo;
import chat.server.vinculo.VinculoList;
import java.io.IOException;
import java.net.Socket;

/**
 * Handler de inicio de sesión
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class LoginHandler implements Handler {

    private final Vinculo vinculo;
    private final Socket socketRx;

    private final String username;
    private final String password;

    public LoginHandler(LoginRequest request, Vinculo vinculo, Socket socketRx) {
        this.vinculo = vinculo;
        this.socketRx = socketRx;

        this.username = request.getValue(LoginRequest.PARAM_USERNAME);
        this.password = request.getValue(LoginRequest.PARAM_PASSWORD);
    }

    @Override
    public Paquete run() {
        LoginResponse.Status status;
        
        // Guarda un vínculo que ya tenga ese nombre de usuario, si hay
        Vinculo repeated = VinculoList.get(username);

        // Pone el nombre de usuario recibido en el vínculo actual
        vinculo.setUsername(username);

        /*
            Ejecutar consulta, retorna si fue correcto o no el login
            Llamar a intento de login en vínculo, retorna si puede reintentar
         */
        boolean correcto = new UsuarioConnector().verificar(username, password);
        boolean retry = vinculo.attemptedLogin(correcto);

        // Si HiloTx no es nulo, significa que el login fue correcto
        if (vinculo.getHiloTx() != null) {

            try {
                // Se conecta a un ServerSocket en el cliente
                Socket socketTx = new Socket(socketRx.getInetAddress(), HiloFactory.PORT_TX);
                vinculo.getHiloTx().setSocket(socketTx);
            } catch (IOException ex) {
                ServerLog.log(vinculo, "No se pudo conectar a "
                        + socketRx.getInetAddress().toString() + " > " + ex.getMessage());
            }

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

        return new LoginResponse(status);

    }

}
