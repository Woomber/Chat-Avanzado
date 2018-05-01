/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import GUI.JComponent_Favorito;
import GUI.JComponent_Grupo;
import GUI.JComponent_Usuario;
import GUI.JFrame_Conversacion;
import GUI.JFrame_Principal;
import Models.Usuario;
import java.awt.Cursor;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Funcion_Principal extends JFrame_Principal{
    ArrayList<Object> usuarios;
    JPanel PanelUsuarios, PanelFavoritos, PanelGrupos;
    
    public Funcion_Principal() {
        boolean x = true;
        super.setOnBtnGruposClick(() -> BtnGruposClick());
        super.setOnBtnFavoritosClick(() -> BtnFavoritosClick());
        super.setOnMenuSalirClick(() -> MenuSalirClick());
        PanelUsuarios = super.getPanelUsuarios();
        PanelFavoritos = super.getPanelFavoritos();
        PanelGrupos = super.getPanelGrupos();
        LoadUsuarios();
        LoadGrupos();
        LoadFavoritos();
    }
    
    
    
    private void LoadUsuarios(){
        
    }
    
    private void LoadGrupos() {
        
    }

    private void LoadFavoritos() {
        
    }
    
    private void BtnGruposClick(){
        
    }
    
    private void BtnFavoritosClick(){
        
    }

    private void MenuSalirClick() {
        Usuario.emisor = null;
        new Funcion_Ingreso().setVisible(true);
        this.setVisible(false);
    }
}
