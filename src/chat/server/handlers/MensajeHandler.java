package chat.server.handlers;

import chat.paquetes.events.MensajeEvent;
import chat.paquetes.models.Paquete;
import chat.paquetes.requests.MensajeRequest;
import chat.paquetes.responses.GenericResponse;
import chat.server.vinculo.Vinculo;
import chat.server.vinculo.VinculoList;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class MensajeHandler implements Handler {

    public final Vinculo vinculo;
    
    public final String origen;
    public final String destino;
    public final String mensaje;
    
    public MensajeHandler(MensajeRequest request, Vinculo vinculo){
        this.vinculo = vinculo;
        
        origen = vinculo.getUsername();
        destino = request.getValue(MensajeRequest.PARAM_TO);
        mensaje = request.getValue(MensajeRequest.PARAM_MESSAGE);
    }
    
    @Override
    public Paquete run() {
        Vinculo destV = VinculoList.get(destino);
        if(destV != null){
            if(destV.getHiloTx() != null){
                destV.getHiloTx().setPaquete(new MensajeEvent(origen, mensaje));
                destV.start();
                return new GenericResponse(GenericResponse.Status.CORRECT);
            } else return new GenericResponse(GenericResponse.Status.INCORRECT);
        } else return new GenericResponse(GenericResponse.Status.INCORRECT);
    }

}
