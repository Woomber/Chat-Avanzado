package chat.server.handlers;

import chat.models.Amigo;
import chat.models.Usuario;
import chat.paquetes.models.Paquete;
import chat.paquetes.responses.UsuariosResponse;
import chat.server.database.AmigosConnector;
import chat.server.database.UsuarioConnector;
import chat.server.vinculo.Vinculo;
import chat.server.vinculo.VinculoList;
import java.util.ArrayList;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UsuariosHandler implements Handler {

    private final Vinculo vinculo;

    public UsuariosHandler(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    @Override
    public Paquete run() {
        
        UsuariosResponse response = new UsuariosResponse();
        
        ArrayList<Usuario> usuarios;
        ArrayList<Amigo> amigos;

        amigos = new AmigosConnector().getAll(vinculo.getUsername());
        usuarios = new UsuarioConnector().getAll();

        for (Usuario u : usuarios) {
            for (Amigo a : amigos) {
                if (u.getId_usuario().equals(a.getAmigo())) {
                    usuarios.remove(u);
                }
                response.addAmigo(a, VinculoList.getIfConnected(a.getAmigo()) != null);
            }
            if(usuarios.contains(u)){
                response.addUsuario(u, VinculoList.getIfConnected(u.getId_usuario()) != null);
            }          
        }
        
        response.finish();
        
        return response;
    }

}
