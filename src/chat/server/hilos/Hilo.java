package chat.server.hilos;

import chat.server.log.ServerLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class Hilo {
    
    protected boolean continuar = true;

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
    
    public void stop(){
        continuar = false;
    }

}
