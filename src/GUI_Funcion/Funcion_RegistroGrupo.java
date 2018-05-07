/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Delegates.Update;
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
    
    ArrayList<String> users;
    ArrayList<String> usernames;
    JPanel panelIntegrantes;
    Thread_Transmitter transmitter;
    JTextField nombreGrupo;
    
    Update OnUpdate;
    
    public Funcion_RegistroGrupo() {
    }
    
    public Funcion_RegistroGrupo(ArrayList<String> users) {
        this.users = users;
        this.panelIntegrantes = super.getPanelIntegrantes();
        this.nombreGrupo = super.getTxtGrupo();
        usernames = new ArrayList<>();
        transmitter = Thread_Transmitter.transmitter;
        super.setOnBtnCrearClick(() -> BtnCrearGrupo());
        super.setOnBtnCancelarClick(() -> {this.setVisible(false);});
        for (String x : users) {
            panelIntegrantes.add(new JLabel(x));
        }
    }

    public void setOnUpdate(Update OnUpdate) {
        this.OnUpdate = OnUpdate;
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
            for (String s : users) {
                miCreacionGrupo.addMiembro(s);
            }
            miCreacionGrupo.finish();
            pw.println(JsonParser.paqueteToJson(miCreacionGrupo));
            Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
            this.setVisible(false);
            OnUpdate.Invoke();
            
        } catch (IOException | JsonParserException ex) {
            System.out.println("");
            System.out.println(ex.getMessage());
            System.out.println("");
        }
    }
}
