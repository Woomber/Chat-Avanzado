/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Delegates.Update;
import Documents.DocumentManager;
import Exceptions.JsonParserException;
import General.MessageBox;
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Thread_Receiver implements Runnable {

    public static Thread_Receiver receiver;

    private Thread miHilo;

    public Update onUpdate;
    
    ServerSocket server;
    Socket socketRx;
    PrintWriter pw;
    BufferedReader read;
    
    boolean funciona;
    
    public Thread_Receiver() {
        funciona = true;
        miHilo = new Thread(this);
        miHilo.start();
    }
    
    
    
    public void closeAll(){
        try {
            server.close();
            socketRx.close();
            pw.close();
            read.close();
            funciona = true;
        } catch (Exception ex) {
            Logger.getLogger(Thread_Receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        try {
            server = new ServerSocket(91);
            socketRx = server.accept();

            // Herramienta para enviar
            pw = new PrintWriter(socketRx.getOutputStream(), true);

            //Herramienta para recibir
            read = new BufferedReader(new InputStreamReader(socketRx.getInputStream()));

            while (funciona) {
                String json = read.readLine();
                Paquete paquete = JsonParser.jsonToPaquete(json);

                String nombreConversacion;
                String deQuien;
                String mensaje;
                GenericResponse response;
                String anotherJson;
                switch (paquete.getOrden()) {
                    case MensajeEvent.ORDEN:
                        response = new GenericResponse(Status.CORRECT);
                        anotherJson = JsonParser.paqueteToJson(response);
                        pw.write(anotherJson);
                        deQuien = paquete.getValue(MensajeEvent.PARAM_FROM);
                        mensaje = paquete.getValue(MensajeEvent.PARAM_MESSAGE);
                        DocumentManager.SaveMessage(deQuien, deQuien, mensaje.replace("\n", " ").trim(), false);
                        
                        break;
                    case UpdateGruposEvent.ORDEN:
                        response = new GenericResponse(Status.CORRECT);
                        anotherJson = JsonParser.paqueteToJson(response);
                        pw.write(anotherJson);
                        //paquete.getValue(UpdateGruposEvent.ORDEN)
                        
                        break;
                    case UpdateUsuariosEvent.ORDEN:
                        response = new GenericResponse(Status.CORRECT);
                        anotherJson = JsonParser.paqueteToJson(response);
                        pw.write(anotherJson);
                        try{
                            onUpdate.Invoke();
                        }
                        catch(Exception ex){
                            
                        }
                        break;
                }

            }
        } catch (IOException | JsonParserException ex) {
            MessageBox.Show("", ex.getMessage());
        }

    }

}
