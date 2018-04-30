package chat.server.handlers;

import chat.exceptions.InvalidOperationException;
import chat.models.UsuarioGrupo;
import chat.paquetes.models.Paquete;
import chat.paquetes.requests.ReplyGrupoRequest;
import chat.paquetes.responses.GenericResponse;
import chat.server.database.UsuarioGrupoConnector;

/**
 *
 * @author Maritza
 */
public class ReplyGrupoHandler implements Handler{
    
    private final UsuarioGrupo us;

    public ReplyGrupoHandler (ReplyGrupoRequest request){
        us = new UsuarioGrupo();
        us.setId_grupo(Integer.parseInt(request.getValue(ReplyGrupoRequest.PARAM_GROUP)));
        us.setId_usuario(request.getValue(ReplyGrupoRequest.PARAM_USUARIO));
        us.setStatus(Boolean.parseBoolean(request.getValue(ReplyGrupoRequest.ACCEPTED)));
    }
    @Override
    public Paquete run() {
       UsuarioGrupoConnector usu = new UsuarioGrupoConnector();

       if (usu.modificar(us)) {
            return new GenericResponse(GenericResponse.Status.CORRECT);
        }
        return new GenericResponse(GenericResponse.Status.INCORRECT);   
    }
}
