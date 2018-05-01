/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;

import GUI_Funcion.Funcion_AgregarFavorito;
import GUI_Funcion.Funcion_AgregarUsuarios;
import GUI_Funcion.Funcion_Conversacion;
import GUI_Funcion.Funcion_Ingreso;
import GUI_Funcion.Funcion_Principal;
import GUI_Funcion.Funcion_Registro;
import GUI_Funcion.Funcion_RegistroGrupo;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class ChatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Funcion_Ingreso control = new Funcion_Ingreso();
        control.setVisible(true);
        //new Funcion_AgregarFavorito().setVisible(true);
        //new Funcion_AgregarUsuarios().setVisible(true);
        //new Funcion_Conversacion().setVisible(true);
        //new Funcion_Principal().setVisible(true);
        /*new Funcion_Registro().setVisible(true);*/
        //new Funcion_RegistroGrupo().setVisible(true);
    }
    
}
