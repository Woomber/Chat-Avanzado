/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Delegates.Update;
import Delegates.UpdateGroup;
import Documents.DocumentManager;
import Exceptions.JsonParserException;
import General.MessageBox;
import Json.JsonParser;
import PaquetesEvents.MensajeEvent;
import PaquetesEvents.UpdateGrupoEvent;
import PaquetesEvents.UpdateGruposEvent;
import PaquetesEvents.UpdateUsuariosEvent;
import PaquetesModels.Paquete;
import Requests.MensajeRequest;
import Responses.GenericResponse;
import Responses.GenericResponse.Status;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
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
    public Update onGroupUpdate;
    public UpdateGroup onOneGroupUpdate;
    
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
            pw = new PrintWriter(new OutputStreamWriter(
                    socketRx.getOutputStream(), StandardCharsets.UTF_8), true);

            //Herramienta para recibir
            read = new BufferedReader(new InputStreamReader(socketRx.getInputStream(), StandardCharsets.UTF_8));

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
                    case UpdateGrupoEvent.ORDEN:
                        response = new GenericResponse(Status.CORRECT);
                        anotherJson = JsonParser.paqueteToJson(response);
                        pw.write(anotherJson);
                        
                        onOneGroupUpdate.Invoke(Integer.valueOf(paquete.getValue(UpdateGrupoEvent.PARAM_GRUPO)));
                        break;
                    case UpdateGruposEvent.ORDEN:
                        response = new GenericResponse(Status.CORRECT);
                        anotherJson = JsonParser.paqueteToJson(response);
                        pw.write(anotherJson);
                        onGroupUpdate.Invoke();
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
