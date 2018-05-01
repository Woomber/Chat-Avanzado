/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author PC
 */
public class Thread_Receiver implements Runnable {

    private Thread miHilo;

    public Thread_Receiver() {
        miHilo = new Thread(this);
        miHilo.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(91);
            Socket socketRx = server.accept();

            // Herramienta para enviar
            PrintWriter pw = new PrintWriter(socketRx.getOutputStream(), true);

            //Herramienta para recibir
            BufferedReader read = new BufferedReader(new InputStreamReader(socketRx.getInputStream()));

            while (true) {
                // estar leyendo del socket y actuar con un switch case

            }
        } catch (IOException ex) {

        }

    }

}
