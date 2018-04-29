/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.handlers;

import chat.exceptions.InvalidOperationException;
import chat.models.Grupo;
import chat.models.UsuarioGrupo;
import chat.paquetes.models.Paquete;
import chat.paquetes.requests.AlterGrupoRequest;
import chat.paquetes.requests.GruposRequest;
import chat.paquetes.responses.GenericResponse;
import chat.server.database.GrupoConnector;
import chat.server.database.MensajeConnector;
import chat.server.database.MensajeVistoConnector;
import chat.server.database.UsuarioGrupoConnector;
import chat.server.vinculo.Vinculo;

/**
 *
 * @author Maritza
 */
public class AlterGrupoHandler implements Handler {
    
    private final AlterGrupoRequest.Operacion operacion;
    private final Grupo grup;

    public AlterGrupoHandler( AlterGrupoRequest request) {
         String op = request.getValue(AlterGrupoRequest.PARAM_OPERACION);
        if (op.equals(AlterGrupoRequest.Operacion.ADD.getName())) {
            operacion = AlterGrupoRequest.Operacion.ADD;
        } else if (op.equals(AlterGrupoRequest.Operacion.REMOVE.getName())) {
            operacion = AlterGrupoRequest.Operacion.REMOVE;
        } else {
            throw new InvalidOperationException("Operación inválida");
        }

        grup = new Grupo();

        grup.setId_grupo(request.getValue(AlterGrupoRequest.PARAM_GRUPO));
        grup.setNombre_grupo(request.getValue(AlterGrupoRequest.PARAM_USUARIO));

    }
    

    @Override
    public Paquete run() {
        GrupoConnector connector = new GrupoConnector();
        MensajeConnector connme = new MensajeConnector();
        MensajeVistoConnector visto = new MensajeVistoConnector();
        UsuarioGrupoConnector usua = new UsuarioGrupoConnector();
        boolean correct = false;

        switch (operacion) {
            case ADD:
                correct = connector.addGrupo(grup);
                break;
            case REMOVE:
                correct = visto.eliminar(grup.getId_grupo());
                correct = connme.eliminar(grup.getId_grupo());
                correct = usua.eliminarGrupo(grup.getId_grupo());
                correct = connector.eliminarGrupo(grup.getId_grupo());
                break;
        }

        if (correct) {
            return new GenericResponse(GenericResponse.Status.CORRECT);
        }
        return new GenericResponse(GenericResponse.Status.INCORRECT);

    }
    
}
