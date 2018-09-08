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
 * Handler para una solicitud de usuarios
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UsuariosHandler implements Handler {

    private final Vinculo vinculo;

    public UsuariosHandler(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    /**
     * Ejecuta el handler
     * @return El paquete con el resultado
     */
    @Override
    public Paquete run() {
        
        UsuariosResponse response = new UsuariosResponse();
        
        ArrayList<Usuario> usuarios;
        ArrayList<Amigo> amigos;

        amigos = new AmigosConnector().getAll(vinculo.getUsername());
        usuarios = new UsuarioConnector().getAll();

        ArrayList<Usuario> porEliminar = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if(amigos == null) break;
            for (Amigo a : amigos) {
                if (u.getId_usuario().equals(a.getAmigo())) {
                    porEliminar.add(u);
                response.addAmigo(a, VinculoList.getIfConnected(a.getAmigo()) != null);    
                }
            }         
        }
        
        for(Usuario u : porEliminar){
            usuarios.remove(u);
        }
        for(Usuario u : usuarios){
            response.addUsuario(u, VinculoList.getIfConnected(u.getId_usuario()) != null);
        }
        
        response.finish();
        
        return response;
    }

}
