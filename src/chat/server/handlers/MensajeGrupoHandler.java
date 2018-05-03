package chat.server.handlers;

import chat.models.Mensaje;
import chat.paquetes.models.Paquete;
import chat.paquetes.requests.MensajeGrupoRequest;
import chat.paquetes.responses.GenericResponse;
import chat.server.database.MensajeConnector;
import chat.server.vinculo.Vinculo;
import chat.server.vinculo.VinculoList;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class MensajeGrupoHandler implements Handler {
    
    private final Mensaje mensaje;

    public MensajeGrupoHandler(Paquete request, Vinculo vinculo){
        mensaje = new Mensaje();
        mensaje.setId_usuario(vinculo.getUsername());
        mensaje.setId_grupo(Integer.parseInt(request.getValue(MensajeGrupoRequest.PARAM_GROUP)));
        mensaje.setTexto(request.getValue(MensajeGrupoRequest.PARAM_MESSAGE));
    }
    
    @Override
    public Paquete run() {
        if(new MensajeConnector().add(mensaje)){
            VinculoList.sendGroupUpdate(mensaje.getId_grupo());
            return new GenericResponse(GenericResponse.Status.CORRECT);
        }
        return new GenericResponse(GenericResponse.Status.INCORRECT);
    }

}