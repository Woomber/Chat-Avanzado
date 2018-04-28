/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import GUI.JFrame_Registro;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class Funcion_Registro extends JFrame_Registro{
    JTextField TxtUsuario, TxtNombre;
    JPasswordField TxtContrasena, TxtContrasenaVerify; 
    
    public Funcion_Registro() {
        TxtUsuario = super.getTxtUsuario();
        TxtNombre = super.getTxtNombre();
        TxtContrasena = super.getTxtContrasena();
        TxtContrasenaVerify = super.getTxtContrasenaVerify();
        super.setOnBtnRegistroClick(() -> BtnRegistroClick());
    }
    
    private void BtnRegistroClick(){
        
    }
}
