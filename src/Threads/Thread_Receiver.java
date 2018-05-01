/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Documents.DocumentManager;
import Exceptions.JsonParserException;
import Json.JsonParser;
import PaquetesEvents.MensajeEvent;
import PaquetesEvents.UpdateGruposEvent;
import PaquetesEvents.UpdateUsuariosEvent;
import PaquetesModels.Paquete;
import Requests.MensajeRequest;
import Responses.GenericResponse;
import Responses.GenericResponse.Status;
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
    
    public static Thread_Receiver receiver;

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
                String json = read.readLine();
                Paquete paquete = JsonParser.jsonToPaquete(json);
                
                String nombreConversacion;
                String deQuien;
                String mensaje;
                
                switch(paquete.getOrden()){
                    case MensajeEvent.ORDEN:
                        
                        deQuien = paquete.getValue(MensajeEvent.PARAM_FROM);
                        mensaje = paquete.getValue(MensajeEvent.PARAM_MESSAGE);
                        DocumentManager.SaveMessage(deQuien, deQuien, mensaje, false);
                       
                        break;
                    case UpdateGruposEvent.ORDEN:
                        
                        break;
                    case UpdateUsuariosEvent.ORDEN:
                        
                        break;
                }
                GenericResponse response = new GenericResponse(Status.CORRECT);
                String anotherJson = JsonParser.paqueteToJson(response);
                pw.write(anotherJson);
            }
        } catch (IOException | JsonParserException ex) {

        }

    }

}
