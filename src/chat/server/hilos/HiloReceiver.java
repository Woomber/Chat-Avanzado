package chat.server.hilos;

import chat.exceptions.InvalidOperationException;
import chat.json.JsonParser;
import chat.exceptions.JsonParserException;
import chat.paquetes.requests.*;
import chat.paquetes.models.Paquete;
import chat.paquetes.responses.GenericResponse;
import chat.server.handlers.*;
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

    /**
     * Construcor para identificar socket y vinculo
     * @param socket Socket
     * @param vinculo  Vinculo
     */
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
            Paquete paquete = JsonParser.jsonToPaquete(json);
            this.operation(paquete);
        } catch (JsonParserException ex) {
            ServerLog.log(this, "Error procesando solicitud, cerrando " + socket.toString());
            VinculoList.remove(vinculo);
            vinculo.stop();
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

        // Para cada tipo de paquete, llamamos a su handler y recibimos la respuesta
        switch (paquete.getOrden()) {
            
            case LoginRequest.ORDEN:
                response = new LoginHandler(paquete, vinculo, socket).run();
                break;

            //Caso especial, desconectamos
            case LogoutRequest.ORDEN:
                response = new LogoutHandler(vinculo).run();
                sendResponse(response);
                VinculoList.remove(vinculo);
                vinculo.stop();
                return;
                
            case RegistroRequest.ORDEN:  
                response = new RegistroHandler(paquete).run();
                break;
                
            case AmigoRequest.ORDEN:
                try {
                    response = new AmigoHandler(paquete, vinculo).run();   
                } catch (InvalidOperationException ex) {
                    response = new GenericResponse(GenericResponse.Status.BAD_REQUEST);
                }
                break;
                
            case MensajeRequest.ORDEN:
                response = new MensajeHandler(paquete, vinculo).run();
                break;
                
            case UsuariosRequest.ORDEN:
                response = new UsuariosHandler(vinculo).run();
                break;
                
            ////////////////////////////////////////////////////////////////////
            // GRUPOS
            case CreateGrupoRequest.ORDEN:
                response = new CreateGrupoHandler(paquete, vinculo).run();
                break;
                
            case AlterGrupoRequest.ORDEN:
                try {
                    response = new AlterGrupoHandler(paquete).run();
                } catch (InvalidOperationException ex) {
                    response = new GenericResponse(GenericResponse.Status.BAD_REQUEST);
                }
                break;
                
            case GruposUsuarioRequest.ORDEN:
                response = new GruposUsuarioHandler(vinculo).run();
                break;
                
            case GrupoRequest.ORDEN:
                response = new GrupoHandler(paquete, vinculo).run();
                break;
                
            case ReplyGrupoRequest.ORDEN:
                response = new ReplyGrupoHandler(paquete, vinculo).run();
                break;

            case MensajeGrupoRequest.ORDEN:
                response = new MensajeGrupoHandler(paquete, vinculo).run();
                break;
                
            ////////////////////////////////////////////////////////////////////

            default:
                final String mensajeError = "Operación inválida ["
                        + paquete.getOrden() + "] en " + socket.toString();
                ServerLog.log(this, mensajeError);

                response = new GenericResponse(GenericResponse.Status.BAD_REQUEST);
        }

        // Envía la respuesta
        sendResponse(response);
        
    }
    
    private boolean sendResponse(Paquete response){
        try {
            this.send(socket, JsonParser.paqueteToJson(response));
            return true;
        } catch (JsonParserException ex) {
            ServerLog.log(this, ex.getMessage());
            return false;
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
