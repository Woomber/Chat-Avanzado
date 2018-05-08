package chat.server.handlers;

import chat.exceptions.InvalidOperationException;
import chat.models.Grupo;
import chat.models.UsuarioGrupo;
import chat.paquetes.models.Paquete;
import chat.paquetes.requests.AlterGrupoRequest;
import chat.paquetes.requests.GrupoRequest;
import chat.paquetes.responses.GenericResponse;
import chat.server.database.GrupoConnector;
import chat.server.database.MensajeConnector;
import chat.server.database.MensajeVistoConnector;
import chat.server.database.UsuarioGrupoConnector;
import chat.server.vinculo.Vinculo;
import chat.server.vinculo.VinculoList;

/**
 * Handler para modificar un grupo (personas)
 * 
 * @author Maritza
 */
public class AlterGrupoHandler implements Handler {

    private final AlterGrupoRequest.Operacion operacion;

    private final UsuarioGrupo ug;

    public AlterGrupoHandler(Paquete request) throws InvalidOperationException {

        String op = request.getValue(AlterGrupoRequest.PARAM_OPERACION);
        if (op.equals(AlterGrupoRequest.Operacion.ADD.getName())) {
            operacion = AlterGrupoRequest.Operacion.ADD;
        } else if (op.equals(AlterGrupoRequest.Operacion.REMOVE.getName())) {
            operacion = AlterGrupoRequest.Operacion.REMOVE;
        } else {
            throw new InvalidOperationException("Operación inválida");
        }

        ug = new UsuarioGrupo();
        ug.setId_grupo(Integer.parseInt(request.getValue(AlterGrupoRequest.PARAM_GRUPO)));
        ug.setId_usuario(request.getValue(AlterGrupoRequest.PARAM_USUARIO));
        ug.setStatus(false);

    }

    /**
     * Ejecuta el handler
     * @return El paquete con el resultado
     */
    @Override
    public Paquete run() {
        GrupoConnector connector = new GrupoConnector();
        MensajeConnector connme = new MensajeConnector();
        MensajeVistoConnector visto = new MensajeVistoConnector();
        UsuarioGrupoConnector usua = new UsuarioGrupoConnector();
        boolean correct = false;

        switch (operacion) {
            case ADD:
                if (usua.add(ug)) {
                    VinculoList.sendGroupUpdate(ug.getId_grupo());
                    return new GenericResponse(GenericResponse.Status.CORRECT);
                }
                return new GenericResponse(GenericResponse.Status.INCORRECT);

            case REMOVE:
                // Eliminar integrante
                correct = usua.eliminar(ug);
                // Si hay menos de dos personas, eliminarGrupo grupo
                if (usua.getAllUsuarios(ug.getId_grupo()).size() < 2) {
                    correct = visto.eliminarGrupo(ug.getId_grupo()) && correct;
                    correct = connme.eliminarGrupo(ug.getId_grupo()) && correct;
                    correct = usua.eliminarGrupo(ug.getId_grupo()) && correct;
                    correct = connector.eliminarGrupo(ug.getId_grupo()) && correct;
                    if (correct) {
                        VinculoList.sendGroupUpdateAll(ug.getId_grupo());
                        return new GenericResponse(GenericResponse.Status.CORRECT);
                    }
                    return new GenericResponse(GenericResponse.Status.INCORRECT);

                } else {
                    if (correct) {
                        VinculoList.sendGroupUpdate(ug.getId_grupo());
                        return new GenericResponse(GenericResponse.Status.CORRECT);
                    }
                    return new GenericResponse(GenericResponse.Status.INCORRECT);
                }
            default:
                return new GenericResponse(GenericResponse.Status.INCORRECT);
        }
    }

}
