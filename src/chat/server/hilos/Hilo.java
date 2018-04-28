package chat.server.hilos;

import chat.server.log.ServerLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Clase abstracta de un Hilo
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
abstract class Hilo {
    
    protected boolean continuar = true;

    /**
     * Envía una cadena a un socket
     * 
     * @param socket El socket al que envía el mensaje
     * @param mensaje El mensaje que envía
     * @return Verdadero si se envió con éxito
     */
    protected boolean send(Socket socket, String mensaje) {
        try {
            PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
            print.println(mensaje);
            ServerLog.log(this, "Enviado a " + socket.toString() + ": " + mensaje);
            return true;
        } catch (IOException ex) {
            ServerLog.log(this, "Error enviando a " + socket.toString() + ": " + ex.getMessage());
            return false;
        }

    }

    /**
     * Espera para leer una cadena de un socket
     * 
     * @param socket El socket del que se va a leer
     * @return La cadena que recibió, o null si hubo error
     */
    protected String get(Socket socket) {
        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String lectura = read.readLine();
            ServerLog.log(this, "Leído de " + socket.toString() + ": " + lectura);
            return lectura;
        } catch (IOException ex) {
            ServerLog.log(this, "Error leyendo de " + socket.toString() + ": " + ex.getMessage());
            return null;
        }

    }
    
    /**
     * Facilitar la detención del hilo
     */
    protected void stop(){
        continuar = false;
    }

}
