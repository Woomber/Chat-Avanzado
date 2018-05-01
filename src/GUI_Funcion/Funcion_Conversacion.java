/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Documents.DocumentManager;
import GUI.JFrame_Conversacion;
import General.MessageBox;
import Models.Grupo;
import Models.Usuario;
import Threads.Thread_Transmitter;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author Cristofer
 */
public class Funcion_Conversacion extends JFrame_Conversacion{
    Usuario usuario;
    Grupo grupo;
    
    JTextPane PanelConversacion;
    JTextField TxtMensaje;
    
    Thread_Transmitter transmitter = Thread_Transmitter.transmitter;
    
    public Funcion_Conversacion(){
        super("hola",false);
    }
    
    public Funcion_Conversacion(Usuario usuario, boolean isOnline) {
        super(usuario.getNombre(),false);
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
        MessageBox.Show("", "Si llegue");
        ArrayList<String> messages = DocumentManager.GetLastNumberMessages(usuario.getId_usuario(),5,false);
        
        if(messages == null) return;
        /*for(String s : messages){
            String[] parts = s.split("|==>");
            String from = parts[0].trim().replace("[", "").replace("]", "");
            String message = parts[1].trim();
            JLabel label = new JLabel();
            label.setText(message);
            if(from.equals(Usuario.emisor)){
                label.setAlignmentX(RIGHT_ALIGNMENT);
            }
            PanelConversacion.add(label);
        }
        PanelConversacion.revalidate();*/
    }
    
    

    private void OnlineBtnEnviarClick() {
        
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
