package chat.server.vinculo;

import chat.server.hilos.HiloReceiver;
import chat.server.hilos.HiloTransmitter;
import java.net.Socket;

/**
 * Clase Vinculo
 * 
 * Contiene los datos de conexión con un cliente: el nombre de usuario, 
 * los hilos de transmisión y recepción, y el número de intentos de login
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class Vinculo {

    private String username;
    private final HiloReceiver hiloRx;
    private HiloTransmitter hiloTx;
    private int loginAttempts;

    public Vinculo(Socket socketRx) {
        this.hiloRx = new HiloReceiver(socketRx, this);
        this.loginAttempts = 0;
    }

    /**
     * Iniciar el hilo de recepción
     */
    public void start() {
        new Thread(this.hiloRx).start();
    }
    
    /**
     * Iniciar el hilo de transmisión
     */
    public void startSend(){
        new Thread(this.hiloTx).start();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public HiloReceiver getHiloRx() {
        return hiloRx;
    }

    public HiloTransmitter getHiloTx() {
        return hiloTx;
    }

    /**
     * Mantiene un control de los intentos de inicio de sesión que se han
     * realizado.
     * 
     * Si el login es correcto, crea el socket de transmisión
     * 
     * @param correct Si el login fue correcto
     * @return Verdadero si el login fue correcto o si aún puede intentar iniciar sesión
     */
    public boolean attemptedLogin(boolean correct) {
        if (correct) {
            // TODO Iniciar socket Tx
            this.hiloTx = new HiloTransmitter(this);
            return true;
        }
        return ++loginAttempts < 3;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }
    
    /**
     * Detiene ambos hilos
     */
    public void stop(){
        if(hiloTx != null) hiloTx.stop();
        if(hiloRx != null) hiloRx.stop();
    }
    
    
}
