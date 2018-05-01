/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import GUI.JFrame_Conversacion;
import Models.Grupo;
import Models.Usuario;
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
    
    public Funcion_Conversacion(){
        super("Emma",false);
        TxtMensaje = super.getTxtMensaje();
        TxtMensaje.setEnabled(false);
        TxtMensaje.setText("No puedes contestar a esta conversación.");
        TxtMensaje.setAlignmentX(CENTER_ALIGNMENT);
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
    }
    
    public Funcion_Conversacion(Grupo grupo){
        super(grupo.getNombre_grupo(),true);
        PanelConversacion = super.getPanelConversacion();
        TxtMensaje = super.getTxtMensaje();
        super.setOnBtnEnviarClick(() -> GroupBtnEnviarClick());
        super.setOnMenuAgregarUsuariosClick(() -> AgregarUsuariosClick());
        super.setOnMenuSalirGrupoClick(() -> MenuSalirGrupoClick());
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
