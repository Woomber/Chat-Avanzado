package chat.server.vinculo;

import chat.models.UsuarioGrupo;
import chat.paquetes.events.UpdateGrupoEvent;
import chat.paquetes.events.UpdateGruposEvent;
import chat.paquetes.events.UpdateUsuariosEvent;
import chat.server.database.UsuarioGrupoConnector;
import chat.server.handlers.GruposUsuarioHandler;
import chat.server.hilos.HiloReceiver;
import chat.server.log.ServerLog;
import java.util.ArrayList;

/**
 * Contiene la lista de los vínculos que tiene el servidor
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class VinculoList {

    private static final ArrayList<Vinculo> VINCULOS;

    static {
        VINCULOS = new ArrayList<>();
    }

    public static synchronized void add(Vinculo v) {
        VINCULOS.add(v);
    }

    public static synchronized void remove(Vinculo v) {
        VINCULOS.remove(v);
        sendUserUpdate();
    }

    public static synchronized void sendUserUpdate() {
        for (Vinculo v : getConnected()) {
            v.getHiloTx().setPaquete(new UpdateUsuariosEvent());
            v.startSend();
        }
    }
    
    public static synchronized void sendGroupUpdateAll(int grupo){
        ArrayList<UsuarioGrupo> ug = new UsuarioGrupoConnector().getAllUsuarios(grupo);
        for(Vinculo v : getConnected()){
            for(UsuarioGrupo u : ug){
                if(v.getUsername().equals(u.getId_usuario())){
                    v.getHiloTx().setPaquete(new UpdateGruposEvent());
                    v.startSend();
                }
            }
        }
    }
    
    public static synchronized void sendGroupUpdate(int grupo){
        ArrayList<UsuarioGrupo> ug = new UsuarioGrupoConnector().getAllUsuarios(grupo);
        for(Vinculo v : getConnected()){
            for(UsuarioGrupo u : ug){
                if(v.getUsername().equals(u.getId_usuario())){
                    v.getHiloTx().setPaquete(new UpdateGrupoEvent(grupo));
                    v.startSend();
                }
            }
        }
    }

    public static boolean contains(Vinculo v) {
        return VINCULOS.contains(v);
    }

    public static Vinculo get(String user) {
        for (Vinculo v : VINCULOS) {
            if (v.getUsername() == null) {
                continue;
            }
            if (v.getUsername().equals(user)) {
                return v;
            }
        }
        return null;
    }

    public static Vinculo getIfConnected(String user) {
        Vinculo v = get(user);
        if (v == null) {
            return null;
        }
        if (v.getHiloTx() == null) {
            return null;
        }
        return v;
    }

    public static Vinculo get(HiloReceiver t) {
        for (Vinculo v : VINCULOS) {
            if (v.getHiloRx().equals(t)) {
                return v;
            }
        }
        return null;
    }

    public static ArrayList<Vinculo> getConnected() {
        ArrayList<Vinculo> array = new ArrayList<>();
        for (Vinculo v : VINCULOS) {
            if (v.getHiloTx() != null) {
                array.add(v);
            }
        }
        return array;
    }

    /**
     * Cerrar todas las conexiones del servidor
     */
    public static synchronized void stop() {
        for (Vinculo v : VINCULOS) {
            v.stop();
        }
    }
}
