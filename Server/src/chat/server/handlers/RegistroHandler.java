package chat.server.handlers;

import chat.models.Usuario;
import chat.paquetes.models.Paquete;
import chat.paquetes.requests.RegistroRequest;
import chat.paquetes.responses.GenericResponse;
import chat.server.database.UsuarioConnector;

/**
 * Handler para registrarse
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class RegistroHandler implements Handler {

    public Usuario usuario;
    
    public RegistroHandler(Paquete request){
        usuario = new Usuario();
        usuario.setId_usuario(request.getValue(RegistroRequest.PARAM_USERNAME).trim());
        usuario.setContrasena(request.getValue(RegistroRequest.PARAM_PASSWORD));
        usuario.setNombre(request.getValue(RegistroRequest.PARAM_NAME));
    }

    /**
     * Ejecuta el handler
     * @return El paquete con el resultado
     */    
    @Override
    public Paquete run() {
        if(new UsuarioConnector().add(usuario)){
            return new GenericResponse(GenericResponse.Status.CORRECT); 
        } else {
            return new GenericResponse(GenericResponse.Status.INCORRECT);
        } 
    }    
}
