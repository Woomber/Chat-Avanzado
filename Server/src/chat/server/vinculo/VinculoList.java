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

    /**
     * Agregar un vínculo a la lista
     * 
     * @param v El vínculo a agregar
     */
    public static synchronized void add(Vinculo v) {
        VINCULOS.add(v);
    }

    /**
     * Eliminar un vínculo de la lista
     * 
     * @param v El vínculo a eliminar
     */
    public static synchronized void remove(Vinculo v) {
        VINCULOS.remove(v);
        sendUserUpdate();
    }

    /**
     * Enviar una actualización de usuarios conectados
     */
    public static synchronized void sendUserUpdate() {
        for (Vinculo v : getConnected()) {
            v.getHiloTx().setPaquete(new UpdateUsuariosEvent());
            v.startSend();
        }
    }
    
    /**
     * Enviar una actualización de todos los grupos a los usuarios de un grupo
     * 
     * @param grupo El grupo
     */
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
    
    /**
     * Enviar una actualización de un grupo a sus usuarios
     * 
     * @param grupo El grupo
     */
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

    /**
     * Busca si el hilo está en los vínculos
     * 
     * @param v El vínculo
     * @return Verdadero si está
     */
    public static boolean contains(Vinculo v) {
        return VINCULOS.contains(v);
    }

    /**
     * Obtener el vínculo del usuario
     * 
     * @param user El username
     * @return El vínculo
     */
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

    /**
     * Obtener el vínculo del usuario si está conectado
     * 
     * @param user El username
     * @return El vínculo
     */
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

    /**
     * Obtener el vínculo con ese hilo
     * @param t EL hilo
     * @return El vínculo
     */
    public static Vinculo get(HiloReceiver t) {
        for (Vinculo v : VINCULOS) {
            if (v.getHiloRx().equals(t)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Enviar todos los vínculos de usuarios conectados
     * 
     * @return Los vínculos conectados
     */
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
