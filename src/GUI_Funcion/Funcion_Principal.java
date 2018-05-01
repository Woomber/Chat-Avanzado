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
import java.awt.Color;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Funcion_Principal extends JFrame_Principal {

    ArrayList<Object> usuarios;
    public JPanel PanelUsuarios, PanelFavoritos, PanelGrupos;

    Thread_Transmitter transmitter;

    public Funcion_Principal() {
        PanelUsuarios = super.getPanelUsuarios();
        PanelFavoritos = super.getPanelFavoritos();
        PanelGrupos = super.getPanelGrupos();
        transmitter = Thread_Transmitter.transmitter;
        /*JComponent_Usuario com = new JComponent_Usuario("Malditasea", true);
        this.PanelUsuarios.add(com);*/
        super.setOnBtnGruposClick(() -> BtnGruposClick());
        super.setOnBtnFavoritosClick(() -> BtnFavoritosClick());
        super.setOnMenuSalirClick(() -> MenuSalirClick());
        LoadUsuarios();
        LoadGrupos();

    }

    private void LoadUsuarios() {
        transmitter.setAction(
                (Socket socket, PrintWriter pw, BufferedReader read)
                -> listaUsuarios(socket, pw, read)
        );
        transmitter.StartThread();
    }

    private void LoadGrupos() {

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
            //System.out.println(paquete.getValue(UsuariosResponse.USUARIOS));
            UsuarioSerializable[] b = JsonParser.jsonToUsuarios(paquete.getValue(UsuariosResponse.USUARIOS));
            UsuarioSerializable[] a = JsonParser.jsonToUsuarios(paquete.getValue(UsuariosResponse.AMIGOS));
            
            for (UsuarioSerializable c : b) {
                if (!(c.username.equals(Usuario.emisor.getId_usuario()))) {
                    JComponent_Usuario com = new JComponent_Usuario(c.nombre, c.connected);
                    System.out.println(c.username + " " + String.valueOf(c.connected) + "\n");
                    com.setOnMouseEnter(() -> {this.setCursor(Cursor.HAND_CURSOR);});
                    com.setOnMouseLeave(() -> {this.setCursor(Cursor.DEFAULT_CURSOR);});
                    com.setOnInformationClick(() -> {InformationClick(c);});
                    com.revalidate();
                    PanelUsuarios.add(com);
                }
            }
            for (UsuarioSerializable c : a) {
                JComponent_Favorito com = new JComponent_Favorito(c.nombre, c.connected);
                System.out.println(c.username + " " + String.valueOf(c.connected) + "\n");
                PanelFavoritos.add(com);
            }
            PanelUsuarios.revalidate();
            PanelFavoritos.revalidate();
        } catch (IOException | JsonParserException ex) {
            System.out.println("");
            System.out.println(ex.getMessage());
            System.out.println("");
        }
    }

    private void InformationClick(UsuarioSerializable US) {
        Usuario usuario = new Usuario(US.username,"","");
        Funcion_Conversacion funcion_conversacion = new Funcion_Conversacion(usuario,US.connected);
        funcion_conversacion.setVisible(true);
    }

}
