package chat.server.vinculo;

import chat.server.hilos.HiloReceiver;
import chat.server.hilos.HiloTransmitter;
import java.net.Socket;

/**
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

    public void start() {
        new Thread(this.hiloRx).start();
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

    public boolean attemptedLogin(boolean correct) {
        if (correct) {
            // TODO Iniciar socket Tx
            Socket socket = new Socket();
            this.hiloTx = new HiloTransmitter(socket, this);
            return true;
        }
        return ++loginAttempts < 3;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }
    
    
}
