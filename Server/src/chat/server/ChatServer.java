/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server;

import chat.server.hilos.HiloFactory;
import chat.server.log.ServerLog;
import chat.server.vinculo.VinculoList;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clase principal del chat server
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class ChatServer {

    // El generador de hilos
    private static HiloFactory factory;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            factory = new HiloFactory();
            new Thread(factory).start();
        } catch(IOException ex){
            ServerLog.log(ChatServer.class, ex.getMessage());
            return;
        }
        waitInput();
    }
    
    /**
     * Lee la consola, esperando un comando 'stop' para detener el servidor
     */
    private static void waitInput(){
         final Scanner sc = new Scanner(System.in);
          while(true){
            if(sc.next().trim().toLowerCase().equals("stop")){
                VinculoList.stop();
                factory.stop();
                return;
            }
        }
    }

}
