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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class Funcion_Ingreso extends JFrame_Ingreso {

    JTextField usuario;
    JPasswordField contrasena;

    public Funcion_Ingreso() {
        this.usuario = super.getTxtNombre();
        this.contrasena = super.getTxtContrasena();
        super.setOnBtnAceptarClick(() -> BtnAceptarClick());
        super.setOnMenuRegistroClick(() -> MenuRegistroClick());
    }

    private void BtnAceptarClick() {
        /*try {
            
            Socket socketTx = new Socket("localhost", 90);
            PrintWriter pw = new PrintWriter(socketTx.getOutputStream(), true);
            BufferedReader read = new BufferedReader(new InputStreamReader(socketTx.getInputStream()));
            String json = JsonParser.paqueteToJson((Paquete) new LoginRequest(usuario.getText(), contrasena.getText()));
            for(int i=0; i<20; i++) {
                pw.println(json);
                Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
                System.out.println(json);
                if (paquete.getValue(LoginResponse.PARAM_STATUS).equals(LoginResponse.Status.REGISTER.getName())) {
                    new Funcion_Registro().setVisible(true);
                    this.setVisible(false);
                }
                if (paquete.getValue(LoginResponse.PARAM_STATUS).equals(LoginResponse.Status.CORRECT.getName())) {
                    Funcion_Principal funcion = new Funcion_Principal();
                    funcion.setVisible(true);
                    this.setVisible(false);
                }
                if (paquete.getValue(LoginResponse.PARAM_STATUS).equals(LoginResponse.Status.TRY_AGAIN.getName())) {
                    break;
                }
            }
            MessageBox.Show("Error", "Inicio de sesión inválido");
        } catch (IOException | JsonParserException ex) {

        }*/
        Usuario usuario = new Usuario("erick","erick","erick");
        Usuario.emisor = usuario;
        new Funcion_Principal().setVisible(true);
        this.setVisible(false);
    }

    private void MenuRegistroClick() {
        Funcion_Registro funcion = new Funcion_Registro();
        funcion.setVisible(true);
        this.setVisible(false);
    }

}
