package chat.server.handlers;

import chat.exceptions.InvalidOperationException;
import chat.models.UsuarioGrupo;
import chat.paquetes.models.Paquete;
import chat.paquetes.requests.ReplyGrupoRequest;
import chat.paquetes.responses.GenericResponse;
import chat.server.database.UsuarioGrupoConnector;
import chat.server.vinculo.Vinculo;
import chat.server.vinculo.VinculoList;

/**
 *
 * @author Maritza
 */
public class ReplyGrupoHandler implements Handler {

    private final UsuarioGrupo us;

    public ReplyGrupoHandler(Paquete request, Vinculo vinculo) {
        us = new UsuarioGrupo();
        us.setId_grupo(Integer.parseInt(request.getValue(ReplyGrupoRequest.PARAM_GROUP)));
        us.setId_usuario(vinculo.getUsername());
        us.setStatus(Boolean.parseBoolean(request.getValue(ReplyGrupoRequest.ACCEPTED)));
    }

    @Override
    public Paquete run() {
        UsuarioGrupoConnector usu = new UsuarioGrupoConnector();
        if (us.isStatus()) {
            if (usu.modificar(us)) {
                VinculoList.sendGroupUpdate(us.getId_grupo());
                return new GenericResponse(GenericResponse.Status.CORRECT);
            }
            return new GenericResponse(GenericResponse.Status.INCORRECT);
        } else {
            if (usu.eliminar(us)) {
                VinculoList.sendGroupUpdate(us.getId_grupo());
                return new GenericResponse(GenericResponse.Status.CORRECT);
            }
            return new GenericResponse(GenericResponse.Status.INCORRECT);
        }

    }
}
