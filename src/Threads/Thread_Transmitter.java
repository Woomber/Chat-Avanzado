/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Delegates.TransmitterAction;
import PaquetesModels.Paquete;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import Json.JsonParser;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Thread_Transmitter implements Runnable {
    
    public static Thread_Transmitter transmitter;
    
    private Thread hilo;
    private TransmitterAction action;

    private String user;
    private String pass;
    
    Socket socketTx;
    PrintWriter pw;
    BufferedReader read;

    public Thread_Transmitter() {
        try {
<<<<<<< HEAD
<<<<<<< HEAD
            socketTx = new Socket("192.168.0.100", 90);
=======
=======
>>>>>>> parent of cce82fb... lo primero ya está
            hilo = new Thread(this);
            socketTx = new Socket("localhost", 90);
>>>>>>> parent of cce82fb... lo primero ya está
            pw = new PrintWriter(socketTx.getOutputStream(), true);
            read = new BufferedReader(new InputStreamReader(socketTx.getInputStream()));
        } catch (IOException ex) {
        }
    }

    public void StartThread(){
        hilo.start();
    }
    
    public Thread_Transmitter(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public void setAction(TransmitterAction action) {
        this.action = action;
    }    
    
    @Override
    public void run() {
        action.Invoke(socketTx, pw, read);
    }

}
