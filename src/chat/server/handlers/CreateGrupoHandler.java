/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.handlers;

import chat.exceptions.JsonParserException;
import chat.json.JsonParser;
import chat.models.Grupo;
import chat.models.UsuarioGrupo;
import chat.paquetes.models.Paquete;
import chat.paquetes.requests.CreateGrupoRequest;
import chat.paquetes.responses.GenericResponse;
import chat.server.database.UsuarioGrupoConnector;
import chat.server.database.GrupoConnector;
import java.util.ArrayList;

/**
 *
 * @author Maritza
 */
public class CreateGrupoHandler implements Handler{

    private final Grupo grupo;
    /*
    private final UsuarioGrupo usuario1;//me perdi con el array de los usuaros, si que a manita
    private final UsuarioGrupo usuario2;
    */
    // Arreglo de usuarios
    private final ArrayList<UsuarioGrupo> usuarios;
    
    public CreateGrupoHandler (CreateGrupoRequest request){
        
        usuarios = new ArrayList<>();
        
        // Recibir los nombres de usuarios del paquete
        String [] miembros;
        try {
            //Recuerda que miembros es un json, y por lo tanto hay que desempacarlo
            miembros = JsonParser.JsonToStrings(request.getValue(CreateGrupoRequest.PARAM_MIEMBROS));
            
            // Agregar cada usuario
            for(String str : miembros){
                UsuarioGrupo usr = new UsuarioGrupo();
                usr.setId_usuario(str);
                usr.setStatus(false);
                usuarios.add(usr);
            }
            
        } catch(JsonParserException ex){
            miembros = null;
        }
       
      
        /*
        usuario1 = new UsuarioGrupo();
        usuario2 = new UsuarioGrupo();
        usuario1.setId_usuario(request.getValue(CreateGrupoRequest.PARAM_MIEMBRO1));
        usuario2.setId_usuario(request.getValue(CreateGrupoRequest.PARAM_MIEMBRO2));
        usuario1.setStatus(true);
        usuario2.setStatus(true);
        */
        grupo = new Grupo();
        grupo.setNombre_grupo(request.getValue(CreateGrupoRequest.PARAM_NOMBRE_GRUPO));   
    }
    
    @Override
    public Paquete run() {
       GrupoConnector nu = new GrupoConnector();
       UsuarioGrupoConnector usu = new UsuarioGrupoConnector();
       boolean correct = true;
       
       //Retornar la id del grupo
       int id = nu.addGrupo(grupo);
       if(id == -1) return new GenericResponse(GenericResponse.Status.INCORRECT);
       
       // Agregar la id y establecer si fue correcto el agregar
       for(UsuarioGrupo u : usuarios){
           u.setId_grupo(id);
           correct = usu.add(u) && correct;
       }
    
       if (correct) {
            return new GenericResponse(GenericResponse.Status.CORRECT);
        }
        return new GenericResponse(GenericResponse.Status.INCORRECT);
    }
}
