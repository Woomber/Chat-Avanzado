package chat.server.handlers;

import chat.exceptions.InvalidOperationException;
import chat.models.Amigo;
import chat.paquetes.models.Paquete;
import chat.paquetes.requests.AmigoRequest;
import chat.paquetes.responses.GenericResponse;
import chat.server.database.AmigosConnector;
import chat.server.vinculo.Vinculo;

/**
 * Handler para modificar amigos
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class AmigoHandler implements Handler {

    private final AmigoRequest.Operacion operacion;
    private final Amigo amigo;

    public AmigoHandler(Paquete request, Vinculo vinculo) throws InvalidOperationException {

        String op = request.getValue(AmigoRequest.PARAM_OPERACION);
        if (op.equals(AmigoRequest.Operacion.ADD.getName())) {
            operacion = AmigoRequest.Operacion.ADD;
        } else if (op.equals(AmigoRequest.Operacion.REMOVE.getName())) {
            operacion = AmigoRequest.Operacion.REMOVE;
        } else {
            throw new InvalidOperationException("Operación inválida");
        }

        amigo = new Amigo();

        amigo.setId_usuario(vinculo.getUsername());
        amigo.setAmigo(request.getValue(AmigoRequest.PARAM_AMIGO));
        amigo.setApodo(request.getValue(AmigoRequest.PARAM_APODO));

    }

        /**
     * Ejecuta el handler
     * @return El paquete con el resultado
     */
    @Override
    public Paquete run() {

        AmigosConnector connector = new AmigosConnector();
        boolean correct = false;

        switch (operacion) {
            case ADD:
                correct = connector.add(amigo);
                break;
            case REMOVE:
                correct = connector.eliminar(amigo);
                break;
        }

        if (correct) {
            return new GenericResponse(GenericResponse.Status.CORRECT);
        }
        return new GenericResponse(GenericResponse.Status.INCORRECT);

    }

}
