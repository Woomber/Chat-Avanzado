/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

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

    public Funcion_AgregarFavorito(String user) {
        super(user);
        this.user = user;
        this.apodo = super.getTxtApodo();
        transmitter = Thread_Transmitter.transmitter;
        receiver = Thread_Receiver.receiver;
        super.setOnBtnCrearClick(() -> agregarFavorito());
    }

    public void agregarFavorito() {
        MessageBox.Show("", "Aquitoy");
        transmitter.setAction(
                (Socket socket, PrintWriter pw, BufferedReader read)
                -> mandarFavorito(socket, pw, read)
        );
        transmitter.StartThread();

    }

    public void mandarFavorito(Socket socket, PrintWriter pw, BufferedReader read) {
                MessageBox.Show("", "orastoyaqui");

        try {
            pw.println(JsonParser.paqueteToJson(new AmigoRequest(user, apodo.getText(), AmigoRequest.Operacion.ADD)));
            Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
            if(paquete.getValue(GenericResponse.PARAM_STATUS).equals(GenericResponse.Status.INCORRECT.getName())){
                MessageBox.Show("", "Eh we, fijate que no se pudo.");
            }
             if(paquete.getValue(GenericResponse.PARAM_STATUS).equals(GenericResponse.Status.CORRECT.getName())){
                 MessageBox.Show("", "Simon, carnal.");
             }

        } catch (IOException | JsonParserException ex) {
            System.out.println("");
            System.out.println(ex.getMessage());
            System.out.println("");
        }
    }
}
