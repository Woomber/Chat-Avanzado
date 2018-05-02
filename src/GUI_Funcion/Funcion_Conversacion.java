/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Documents.DocumentManager;
import Exceptions.JsonParserException;
import GUI.JFrame_Conversacion;
import General.MessageBox;
import Json.JsonParser;
import Models.Grupo;
import Models.Usuario;
import PaquetesModels.Paquete;
import Requests.MensajeRequest;
import Threads.Thread_Transmitter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Cristofer
 */
public class Funcion_Conversacion extends JFrame_Conversacion{
    Usuario usuario;
    Grupo grupo;
    
    JPanel PanelConversacion;
    JTextField TxtMensaje;
    
    Thread_Transmitter transmitter = Thread_Transmitter.transmitter;
    
    public Funcion_Conversacion(){
        super("hola",false);
    }
    
    public Funcion_Conversacion(Usuario usuario, boolean isOnline) {
        super(usuario.getNombre(),false);
        this.usuario = usuario;
        PanelConversacion = super.getPanelConversacion();
        TxtMensaje = super.getTxtMensaje();
        if(isOnline){
            super.setOnBtnEnviarClick(() -> OnlineBtnEnviarClick());
        }
        else{
            super.setOnBtnEnviarClick(() -> OfflineBtnEnviarClick());
            TxtMensaje.setEnabled(false);
            TxtMensaje.setText("No puedes contestar a esta conversación.");
            TxtMensaje.setAlignmentX(CENTER_ALIGNMENT);
        }
        LoadInformation();
    }
    
    public Funcion_Conversacion(Grupo grupo){
        super(grupo.getNombre_grupo(),true);
        PanelConversacion = super.getPanelConversacion();
        TxtMensaje = super.getTxtMensaje();
        super.setOnBtnEnviarClick(() -> GroupBtnEnviarClick());
        super.setOnMenuAgregarUsuariosClick(() -> AgregarUsuariosClick());
        super.setOnMenuSalirGrupoClick(() -> MenuSalirGrupoClick());
    }
    
    private void LoadInformation() {
        ArrayList<String> messages = DocumentManager.GetLastNumberMessages(usuario.getId_usuario(), 5, false);
        
            
        if(messages == null) return;
        
        for(String s : messages){
            
            String[] parts = s.split(("l==>"));
            String from = parts[0].trim().replace("[", "").replace("]", "");
            String message = parts[1].trim();
            JLabel label;
            if(from.equals(Usuario.emisor)){
                label = new JLabel(message,SwingConstants.RIGHT);
                label.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
                label.setHorizontalTextPosition(SwingConstants.RIGHT);
            }
            else{
                label = new JLabel(message,SwingConstants.RIGHT);
                
            }
            PanelConversacion.add(label);
            
        }
        
    }
    
    

    private void OnlineBtnEnviarClick() {
        if(TxtMensaje.getText().equals("")){
            MessageBox.Show("Error","No puedes enviar un mensaje vacío");
            return;
        }
        transmitter.setAction((Socket socket, PrintWriter pw, BufferedReader read) -> {
            try {
                pw.println(JsonParser.paqueteToJson(new MensajeRequest(usuario.getId_usuario(),TxtMensaje.getText())));
                Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
                DocumentManager.SaveMessage(usuario.getId_usuario(), Usuario.emisor.getId_usuario(), TxtMensaje.getText(), false);
                JLabel label = new JLabel(TxtMensaje.getText());
                PanelConversacion.add(label);
                PanelConversacion.revalidate();
                TxtMensaje.setText("");
            } catch (JsonParserException | IOException ex) {
            }
        });
        transmitter.StartThread();
        
    }

    private void OfflineBtnEnviarClick() {

    }

    private void GroupBtnEnviarClick() {

    }

    private void AgregarUsuariosClick() {
        Funcion_AgregarUsuarios funcion = new Funcion_AgregarUsuarios();
        funcion.setVisible(true);
    }

    private void MenuSalirGrupoClick() {

    }
 
}
