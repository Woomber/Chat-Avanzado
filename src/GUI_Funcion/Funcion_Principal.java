/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Exceptions.JsonParserException;
import GUI.JComponent_Favorito;
import GUI.JComponent_Grupo;
import GUI.JComponent_Usuario;
import GUI.JFrame_Conversacion;
import GUI.JFrame_Principal;
import General.MessageBox;
import Json.JsonParser;
import Models.Usuario;
import ModelsSerializables.UsuarioSerializable;
import PaquetesModels.Paquete;
import Requests.LoginRequest;
import Requests.UsuariosRequest;
import Responses.LoginResponse;
import Responses.UsuariosResponse;
import Threads.Thread_Transmitter;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Funcion_Principal extends JFrame_Principal {

    ArrayList<Object> usuarios;
    JPanel PanelUsuarios, PanelFavoritos, PanelGrupos;
    Thread_Transmitter transmitter;

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

    private void LoadUsuarios() {
        Thread_Transmitter.transmitter = new Thread_Transmitter();
        transmitter = Thread_Transmitter.transmitter;
        transmitter.setAction(
                (Socket socket, PrintWriter pw, BufferedReader read)
                -> listaUsuarios(socket, pw, read)
        );
        transmitter.StartThread();
    }

    private void LoadGrupos() {

    }

    private void LoadFavoritos() {

    }

    private void BtnGruposClick() {

    }

    private void BtnFavoritosClick() {

    }

    private void MenuSalirClick() {
        Usuario.emisor = null;
        new Funcion_Ingreso().setVisible(true);
        this.setVisible(false);
    }

    private void listaUsuarios(Socket socket, PrintWriter pw, BufferedReader read) {
        try {
            
            pw.println(JsonParser.paqueteToJson(new UsuariosRequest()));
            Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
            System.out.println(paquete.getValue(UsuariosResponse.USUARIOS));
            UsuarioSerializable[] b = JsonParser.jsonToUsuarios(paquete.getValue(UsuariosResponse.USUARIOS));
            
            for (UsuarioSerializable c : b) {
                this.PanelUsuarios.add(new JComponent_Usuario(c.username, c.connected));
                System.out.println(c.username + " " + String.valueOf(c.connected) + "\n");
            }

        } catch (IOException | JsonParserException ex) {
            System.out.println("");
            System.out.println(ex.getMessage());
            System.out.println("");
        }

    }
}
