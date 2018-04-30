package chat.server.handlers;

import chat.models.UsuarioGrupo;
import chat.paquetes.models.Paquete;
import chat.paquetes.responses.GruposUsuarioResponse;
import chat.server.database.UsuarioGrupoConnector;
import chat.server.vinculo.Vinculo;
import java.util.ArrayList;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class GruposUsuarioHandler implements Handler {

    private final Vinculo vinculo;
    
    public GruposUsuarioHandler(Vinculo vinculo){
        this.vinculo = vinculo;
    }
    
    @Override
    public Paquete run() {
        ArrayList<UsuarioGrupo> ug = new UsuarioGrupoConnector().getGrupos(vinculo.getUsername());
        
        GruposUsuarioResponse response = new GruposUsuarioResponse();
        
        for(UsuarioGrupo grupo : ug){
              response.addGrupo(grupo.getId_grupo());
        }
        response.finish();
        
        return response;
    }

    
    
}
