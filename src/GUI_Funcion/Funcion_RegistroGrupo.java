/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Exceptions.JsonParserException;
import GUI.JComponent_Usuario;
import GUI.JFrame_RegistroGrupo;
import General.MessageBox;
import Json.JsonParser;
import PaquetesModels.Paquete;
import Requests.AmigoRequest;
import Requests.CreateGrupoRequest;
import Responses.GenericResponse;
import Threads.Thread_Transmitter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author PC
 */
public class Funcion_RegistroGrupo extends JFrame_RegistroGrupo {
    
    ArrayList<JComponent_Usuario> users;
    ArrayList<String> usernames;
    JPanel panelIntegrantes;
    Thread_Transmitter transmitter;
    JTextField nombreGrupo;
    
    public Funcion_RegistroGrupo() {
    }
    
    public Funcion_RegistroGrupo(ArrayList<JComponent_Usuario> users) {
        this.users = users;
        this.panelIntegrantes = super.getPanelIntegrantes();
        this.nombreGrupo = super.getTxtGrupo();
        usernames = new ArrayList<>();
        transmitter = Thread_Transmitter.transmitter;
        super.setOnBtnCrearClick(() -> BtnCrearGrupo());
        for (JComponent_Usuario x : users) {
            panelIntegrantes.add(x.getLblUsuario());
            usernames.add(x.username);
        }
    }
    
    public void BtnCrearGrupo() {
        transmitter.setAction(
                (Socket socket, PrintWriter pw, BufferedReader read)
                -> mandarGrupo(socket, pw, read)
        );
        transmitter.StartThread();
    }
    
    public void mandarGrupo(Socket socket, PrintWriter pw, BufferedReader read) {
        try {
            CreateGrupoRequest miCreacionGrupo = new CreateGrupoRequest(nombreGrupo.getText());
            for (String s : usernames) {
                miCreacionGrupo.addMiembro(s);
                MessageBox.Show("", s);
            }
            miCreacionGrupo.finish();
            pw.println(JsonParser.paqueteToJson(miCreacionGrupo));
            Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
            
        } catch (IOException | JsonParserException ex) {
            System.out.println("");
            System.out.println(ex.getMessage());
            System.out.println("");
        }
    }
}
