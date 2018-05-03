/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Delegates.Update;
import Exceptions.JsonParserException;
import GUI.JComponent_Favorito;
import GUI.JComponent_Usuario;
import GUI.JFrame_AgregarFavorito;
import General.MessageBox;
import Json.JsonParser;
import Models.Usuario;
import ModelsSerializables.UsuarioSerializable;
import PaquetesModels.Paquete;
import Requests.AmigoRequest;
import Requests.UsuariosRequest;
import Responses.GenericResponse;
import Responses.UsuariosResponse;
import Threads.Thread_Receiver;
import Threads.Thread_Transmitter;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class Funcion_AgregarFavorito extends JFrame_AgregarFavorito {

    JTextField apodo;
    Thread_Transmitter transmitter;
    Thread_Receiver receiver;
    String user;
    
    Update onUpdate;

    public Funcion_AgregarFavorito(String user, Update onUpdate) {
        super(user);
        this.onUpdate = onUpdate;
        this.user = user;
        this.apodo = super.getTxtApodo();
        transmitter = Thread_Transmitter.transmitter;
        receiver = Thread_Receiver.receiver;
        super.setOnBtnCrearClick(() -> agregarFavorito());
        super.setOnBtnCancelarClick(() -> Cancelar());
    }

    public void agregarFavorito() {
        transmitter.setAction(
                (Socket socket, PrintWriter pw, BufferedReader read)
                -> mandarFavorito(socket, pw, read)
        );
        transmitter.StartThread();
        
        this.setVisible(false);
        
    }
    
   
    
    public void Cancelar(){
        this.setVisible(false);
    }

    public void mandarFavorito(Socket socket, PrintWriter pw, BufferedReader read) {
        try {
            pw.println(JsonParser.paqueteToJson(new AmigoRequest(user, apodo.getText(), AmigoRequest.Operacion.ADD)));
            Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
            if(paquete.getValue(GenericResponse.PARAM_STATUS).equals(GenericResponse.Status.INCORRECT.getName())){
                MessageBox.Show("", "Eh we, fijate que no se pudo.");
            }
             if(paquete.getValue(GenericResponse.PARAM_STATUS).equals(GenericResponse.Status.CORRECT.getName())){
                 
             }
             onUpdate.Invoke();
        } catch (IOException | JsonParserException ex) {
            System.out.println("");
            System.out.println(ex.getMessage());
            System.out.println("");
        }
    }
}
