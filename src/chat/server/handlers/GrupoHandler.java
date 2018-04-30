/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.handlers;

import chat.json.JsonParser;
import chat.models.Grupo;
import chat.models.Mensaje;
import chat.models.UsuarioGrupo;
import chat.paquetes.models.Paquete;
import chat.paquetes.requests.GruposRequest;
import chat.paquetes.responses.GruposResponse;
import chat.server.database.GrupoConnector;
import chat.server.database.MensajeConnector;
import chat.server.database.UsuarioGrupoConnector;
import java.util.ArrayList;

/**
 *
 * @author Maritza
 *//*
public class GrupoHandler implements Handler{
    private final GruposRequest request;

    public GrupoHandler(GruposRequest request) {
     this.request = request;
    }

    @Override
    public Paquete run() {
      UsuarioGrupoConnector x = new UsuarioGrupoConnector();
      
      UsuarioGrupo resultados = new UsuarioGrupo();
      resultados = x.getUsuario(request.getValue(GruposRequest.PARAM_USUARIO), Integer.parseInt(request.getValue(GruposRequest.PARAM_GRUPO)));
      GruposResponse.Status status;
      if(resultados.isStatus()){
          status = GruposResponse.Status.IN_GROUP;
      }
      else status = GruposResponse.Status.PENDING;
      GruposResponse res = new GruposResponse(resultados.getId_grupo(),resultados.getId_usuario(),status);
      
      ArrayList<UsuarioGrupo> u = x.getAllUsuarios(Integer.parseInt(request.getValue(GruposRequest.PARAM_GRUPO)));
      for(UsuarioGrupo usr : u){
          res.addMiembro(usr.getId_usuario());
      }
      
      return res;
      //m.getGrupo(request.getValue(GruposRequest.PARAM_USUARIO));
      
      
    }
    
}*/
