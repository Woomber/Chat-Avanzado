package chat.server.vinculo;

import chat.paquetes.events.UpdateUsuariosEvent;
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
    
    public static synchronized void add(Vinculo v){
        VINCULOS.add(v);
    }
    
    public static synchronized void remove(Vinculo v){
        VINCULOS.remove(v);
        for(Vinculo v1 : VINCULOS){
            if(v1.getHiloTx() != null)
                v1.getHiloTx().setPaquete(new UpdateUsuariosEvent());
            v1.start();
        }
    }
    
    public static boolean contains(Vinculo v){
        return VINCULOS.contains(v);
    }
    
    public static Vinculo get(String user) {
        for(Vinculo v : VINCULOS){
            if(v.getUsername() == null) continue;
            if(v.getUsername().equals(user)) return v;
        }
        return null;
    }
    
    public static Vinculo get(HiloReceiver t){
        for(Vinculo v : VINCULOS){
            if(v.getHiloRx().equals(t)) return v;
        }
        return null;
    }
    
    public static ArrayList<Vinculo> getConnected(){
        ArrayList<Vinculo> array = new ArrayList<>();
        for(Vinculo v : VINCULOS){
            if(v.getHiloTx() != null) array.add(v);
        }
        return array;
    }
    
    /**
     * Cerrar todas las conexiones del servidor
     */
    public static synchronized void stop(){
        for(Vinculo v : VINCULOS){
            v.stop();
        }
    }
}
