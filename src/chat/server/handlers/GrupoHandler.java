/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.handlers;

import chat.models.Grupo;
import chat.models.Mensaje;
import chat.models.UsuarioGrupo;
import chat.paquetes.models.Paquete;
import chat.paquetes.responses.GruposResponse;
import chat.server.database.GrupoConnector;
import chat.server.database.MensajeConnector;
import java.util.ArrayList;

/**
 *
 * @author Maritza
 *//*
public class GrupoHandler implements Handler{
    private final int grupo;
    private final String usuario;

    public GrupoHandler(int grupo, String usuario) {
        this.grupo = grupo;
        this.usuario = usuario;
    }

    @Override
    public Paquete run() {
       GruposResponse response = new GruposResponse();
        
        ArrayList<Grupo> grup;
        ArrayList<Mensaje> men;

        grup = new GrupoConnector().getGrupo(usuario);
        men = new MensajeConnector().getUsuario(grupo, usuario);
        
    }
    
}*/
