/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.handlers;

import chat.models.Grupo;
import chat.models.UsuarioGrupo;
import chat.paquetes.models.Paquete;
import chat.paquetes.requests.CreateGrupoRequest;
import chat.paquetes.responses.GenericResponse;
import chat.server.database.UsuarioGrupoConnector;
import chat.server.database.GrupoConnector;

/**
 *
 * @author Maritza
 */
public class CreateGrupoHandler implements Handler{

    private final Grupo grupo;
    private final UsuarioGrupo usuario1;//me perdi con el array de los usuaros, si que a manita
    private final UsuarioGrupo usuario2;
    
    public CreateGrupoHandler (CreateGrupoRequest request, String u1, String u2){
        usuario1 = new UsuarioGrupo();
        usuario2 = new UsuarioGrupo();
        usuario1.setId_usuario(request.getValue(CreateGrupoRequest.PARAM_MIEMBRO1));
        usuario2.setId_usuario(request.getValue(CreateGrupoRequest.PARAM_MIEMBRO2));
        usuario1.setStatus(true);
        usuario2.setStatus(true);
        
        grupo = new Grupo();
        grupo.setNombre_grupo(request.getValue(CreateGrupoRequest.PARAM_NOMBRE_GRUPO));   
    }
    
    @Override
    public Paquete run() {
       GrupoConnector nu = new GrupoConnector();
       UsuarioGrupoConnector usu = new UsuarioGrupoConnector();
       boolean correct = false;
       
       correct = nu.addGrupo(grupo);
       correct = usu.add(usuario1);//falta saber con que id se quedo el grupo para completar el usuario
       correct = usu.add(usuario2);
    
       if (correct) {
            return new GenericResponse(GenericResponse.Status.CORRECT);
        }
        return new GenericResponse(GenericResponse.Status.INCORRECT);
    }
}
