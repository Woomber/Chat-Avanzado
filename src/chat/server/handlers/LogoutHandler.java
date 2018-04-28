package chat.server.handlers;

import chat.paquetes.models.Paquete;
import chat.paquetes.requests.LogoutRequest;
import chat.paquetes.responses.GenericResponse;
import chat.server.vinculo.Vinculo;
import chat.server.vinculo.VinculoList;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class LogoutHandler implements Handler {
    
    private final Vinculo vinculo;

    public LogoutHandler(Vinculo vinculo){
        this.vinculo = vinculo;
    }
    
    @Override
    public Paquete run() {
        if(VinculoList.contains(vinculo)){
            return new GenericResponse(GenericResponse.Status.CORRECT);
        }
        return new GenericResponse(GenericResponse.Status.INCORRECT);
    }

}
