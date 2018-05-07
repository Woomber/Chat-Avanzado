/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Exceptions.JsonParserException;
import GUI.JFrame_Registro;
import General.MessageBox;
import Json.JsonParser;
import Models.Usuario;
import PaquetesModels.Paquete;
import Requests.LoginRequest;
import Requests.RegistroRequest;
import Responses.GenericResponse;
import Responses.LoginResponse;
import Threads.Thread_Receiver;
import Threads.Thread_Transmitter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class Funcion_Registro extends JFrame_Registro {

    JTextField TxtUsuario, TxtNombre;
    JPasswordField TxtContrasena, TxtContrasenaVerify;

    Thread_Transmitter transmitter;
    Thread_Receiver receiver;

    public Funcion_Registro() {
        transmitter = Thread_Transmitter.transmitter;
        receiver = Thread_Receiver.receiver;
        TxtUsuario = super.getTxtUsuario();
        TxtNombre = super.getTxtNombre();
        TxtContrasena = super.getTxtContrasena();
        TxtContrasenaVerify = super.getTxtContrasenaVerify();
        super.setOnBtnRegistroClick(() -> BtnRegistroClick());
        super.setOnMenuIngresoClick(() -> MenuIngresoClick());
    }

    private void BtnRegistroClick() {
        if (!TxtContrasena.getText().equals(TxtContrasenaVerify.getText())) {
            MessageBox.Show("Contraseñas diferentes", "Asegurese de que las contraseñas coincidan");
            return;
        }
        transmitter.setAction(
                (Socket socket, PrintWriter pw, BufferedReader read)
                -> NewRegistro(socket, pw, read));
        transmitter.StartThread();
    }

    private void MenuIngresoClick() {
        new Funcion_Ingreso().setVisible(true);
        this.setVisible(false);
    }

    private void NewRegistro(Socket socket, PrintWriter pw, BufferedReader read) {
        try {
            String json
                    = JsonParser.paqueteToJson((Paquete) new RegistroRequest(
                            TxtUsuario.getText(),
                            TxtNombre.getText(),
                            TxtContrasena.getText()
                    ));
            while (true) {
                pw.println(json);
                Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
                if (paquete.getValue(GenericResponse.PARAM_STATUS).equals(GenericResponse.Status.CORRECT.getName())) {
                    break;
                }
                if (paquete.getValue(GenericResponse.PARAM_STATUS).equals(GenericResponse.Status.INCORRECT.getName())) {
                    MessageBox.Show("Error", "Ocurrió un error durante el Registro. Posibles errores:\n"
                            + "-No existe conexión con el servidor\n"
                            + "-El usuario ya ha sido registrado\n");
                    return;
                }
            }
            MessageBox.Show("Registrado", "El usuario ha sido registrado");
            System.out.println("Registrado");
            Usuario.emisor = new Usuario(TxtUsuario.getText(), "", TxtNombre.getText());
            
             try {
            json = 
                    JsonParser.paqueteToJson((Paquete) new LoginRequest(
                            TxtUsuario.getText(), 
                            TxtContrasena.getText())
                    );
            
            while (true) {
                pw.println(json);
                Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
                System.out.println(json);
               
                if (paquete.getValue(LoginResponse.PARAM_STATUS).equals(LoginResponse.Status.CORRECT.getName())) {
                    Usuario.emisor = new Usuario(TxtUsuario.getText(),"","");
                    Funcion_Principal funcion = new Funcion_Principal();
                    funcion.setVisible(true);
                    this.setVisible(false);
                    return;
                }
                if (paquete.getValue(LoginResponse.PARAM_STATUS).equals(LoginResponse.Status.TRY_AGAIN.getName())) {
                    break;
                }
            }
            MessageBox.Show("Error", "Inicio de sesión inválido");
            return;
        } catch (IOException | JsonParserException ex) {
            System.out.println("");
            System.out.println(ex.getMessage());
            System.out.println("");
        }
        
        } catch (IOException | JsonParserException ex) {
            System.out.println("No se pudo: " + ex.getMessage());
            MessageBox.Show("Error", "Ocurrió un error durante el Registro. Posibles errores:\n"
                    + "-No existe conexión con el servidor\n"
                    + "-El usuario ya ha sido registrado\n");
        }
    }
}
