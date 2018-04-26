/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.vinculo;

import chat.server.hilos.HiloReceiver;
import chat.server.log.ServerLog;
import java.util.ArrayList;

/**
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
    
    public static synchronized void stop(){
        for(Vinculo v : VINCULOS){
            if(v.getHiloRx() != null) v.getHiloRx().stop();
            if(v.getHiloTx() != null) v.getHiloTx().stop();
        }
    }
}
