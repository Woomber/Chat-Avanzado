/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Delegates.MouseClick;
import Exceptions.JsonParserException;
import GUI.JFrame_Ingreso;
import General.MessageBox;
import Json.JsonParser;
import Models.Usuario;
import PaquetesModels.Paquete;
import Requests.LoginRequest;
import Requests.RegistroRequest;
import Responses.LoginResponse;
import Threads.Thread_Receiver;
import Threads.Thread_Transmitter;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class Funcion_Ingreso extends JFrame_Ingreso {

    JTextField usuario;
    JPasswordField contrasena;

    Thread_Transmitter transmitter;
    Thread_Receiver receiver;

    public Funcion_Ingreso() {
        this.usuario = super.getTxtNombre();
        this.contrasena = super.getTxtContrasena();
        Thread_Transmitter.transmitter = new Thread_Transmitter();
        Thread_Receiver.receiver = new Thread_Receiver();
        transmitter = Thread_Transmitter.transmitter;
        receiver = Thread_Receiver.receiver;
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BtnAceptarClick();
            }
        };
        this.usuario.addActionListener(action);
        this.contrasena.addActionListener(action);
        super.setOnBtnAceptarClick(() -> BtnAceptarClick());
        super.setOnMenuRegistroClick(() -> MenuRegistroClick());

    }

    private void BtnAceptarClick() {
        transmitter.setAction(
                (Socket socket, PrintWriter pw, BufferedReader read)
                -> NewSesion(socket, pw, read)
        );
        transmitter.StartThread();
    }

    private void MenuRegistroClick() {
        Funcion_Registro funcion = new Funcion_Registro();
        funcion.setVisible(true);
        this.setVisible(false);
    }

    private void NewSesion(Socket socket, PrintWriter pw, BufferedReader read) {
        try {
            String json = JsonParser.paqueteToJson((Paquete) new LoginRequest(usuario.getText(), contrasena.getText()));
            while (true) {
                pw.println(json);
                Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
                System.out.println(json);
                if (paquete.getValue(LoginResponse.PARAM_STATUS).equals(LoginResponse.Status.REGISTER.getName())) {
                    new Funcion_Registro().setVisible(true);
                    this.setVisible(false);
                    return;
                }
                if (paquete.getValue(LoginResponse.PARAM_STATUS).equals(LoginResponse.Status.CORRECT.getName())) {
                    Usuario.emisor = new Usuario(usuario.getText(), "", "");
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

    }

}
