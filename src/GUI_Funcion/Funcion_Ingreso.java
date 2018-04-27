/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Delegates.MouseClick;
import GUI.JFrame_Ingreso;
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
        Funcion_Principal funcion = new Funcion_Principal();
        funcion.setOnMenuSalirClick(
                () -> {
                    this.setVisible(true);
                    funcion.setVisible(false);
                });
        funcion.setVisible(true);
        this.setVisible(false);
    }

    private void MenuRegistroClick() {
        Funcion_Registro funcion = new Funcion_Registro();
        funcion.setOnMenuIngresoClick(
                () -> {
                    this.setVisible(true);
                    funcion.setVisible(false);
                });
        funcion.setVisible(true);
        this.setVisible(false);
    }

}
