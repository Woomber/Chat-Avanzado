/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import PaquetesModels.Paquete;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import Json.JsonParser;

/**
 *
 * @author PC
 */
public class Thread_Transmitter implements Runnable {

    private String user;
    private String pass;

    public Thread_Transmitter() {
    }

    public Thread_Transmitter(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    @Override
    public void run() {
        try {
            Socket socketTx = new Socket("localhost", 90);
            PrintWriter pw = new PrintWriter(socketTx.getOutputStream(), true);
            BufferedReader read = new BufferedReader(new InputStreamReader(socketTx.getInputStream()));
            while (true) {

            }

        } catch (IOException e) {
            e.getMessage();
        }

    }

}
