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
    // Arreglo de usuarios
    private final ArrayList<UsuarioGrupo> usuarios;
    
    public CreateGrupoHandler (Paquete request){
        
        usuarios = new ArrayList<>();
        
        // Recibir los nombres de usuarios del paquete
        String [] miembros;
        try {
            // miembros es un json, y por lo tanto hay que desempacarlo
            miembros = JsonParser.jsonToStrings(request.getValue(CreateGrupoRequest.PARAM_MIEMBROS));
            
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
