/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.handlers;

import chat.models.Grupo;
import chat.models.Mensaje;
import chat.models.MensajeVisto;
import chat.models.UsuarioGrupo;
import chat.paquetes.models.Paquete;
import chat.paquetes.requests.GrupoRequest;
import chat.paquetes.responses.GrupoResponse;
import chat.server.database.GrupoConnector;
import chat.server.database.MensajeConnector;
import chat.server.database.MensajeVistoConnector;
import chat.server.database.UsuarioGrupoConnector;
import chat.server.log.ServerLog;
import chat.server.vinculo.Vinculo;
import java.util.ArrayList;

/**
 *
 * @author Maritza
 */
public class GrupoHandler implements Handler {

    private final Paquete request;
    private final Vinculo vinculo;

    public GrupoHandler(Paquete request, Vinculo vinculo) {
        this.request = request;
        this.vinculo = vinculo;
    }

    @Override
    public Paquete run() {
        UsuarioGrupoConnector x = new UsuarioGrupoConnector();
        GrupoConnector gc = new GrupoConnector();
        
        Grupo grupo = gc.get(Integer.parseInt(request.getValue(GrupoRequest.PARAM_GRUPO)));
        
        UsuarioGrupo resultados;
        resultados = x.getUsuario( vinculo.getUsername(), grupo.getId_grupo());
        GrupoResponse.Status status;
        if (resultados.isStatus()) {
            status = GrupoResponse.Status.IN_GROUP;
        } else {
            status = GrupoResponse.Status.PENDING;
        }
        GrupoResponse response = new GrupoResponse(grupo.getId_grupo(), grupo.getNombre_grupo(), status);

        ArrayList<UsuarioGrupo> miembros = x.getAllUsuarios(Integer.parseInt(request.getValue(GrupoRequest.PARAM_GRUPO)));
        
        // Obtener todos los miembros del grupo
        for (UsuarioGrupo usr : miembros) {
            response.addMiembro(usr.getId_usuario());
        }
        
        // Obtener todos los mensajes del grupo
        ArrayList<Mensaje> mensajes = new MensajeConnector().getAll(resultados.getId_grupo());
        MensajeVistoConnector vistos = new MensajeVistoConnector();
       
        int nMiembros = new UsuarioGrupoConnector().getAllUsuarios(resultados.getId_grupo()).size();
        ServerLog.log(this, "MIEMBROS " + nMiembros);
        
        // Para cada mensaje
        for(Mensaje m : mensajes){
            // Si el usuario no lo ha visto, agregarlo a la lista que se mandará y marcarlo como visto
            if(vistos.get(m.getId_mensaje_grupal(), vinculo.getUsername()) == null){
                response.addMensaje(m.getId_usuario(), m.getTexto());
                vistos.add(new MensajeVisto(m.getId_mensaje_grupal(), vinculo.getUsername()));
            }
            // Si ya todos los miembros leyeron el mensaje, borrarlo
            if(nMiembros == vistos.getAll(m.getId_mensaje_grupal()).size()){
                ServerLog.log(this, "VISTO POR TODOS");
                vistos.eliminar(m.getId_mensaje_grupal());
                new MensajeConnector().eliminar(m.getId_mensaje_grupal());
            }
        }
        
        
        response.finish();

        return response;
    }

}
