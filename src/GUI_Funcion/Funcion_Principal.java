/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Delegates.Update;
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
import Threads.Thread_Receiver;
import Threads.Thread_Transmitter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Funcion_Principal extends JFrame_Principal {

    ArrayList<Funcion_Conversacion> conversaciones;
    public JPanel PanelUsuarios, PanelFavoritos, PanelGrupos;

    Thread_Transmitter transmitter;
    Thread_Receiver receiver;

    public Funcion_Principal() {
        conversaciones = new ArrayList<Funcion_Conversacion>();
        PanelUsuarios = super.getPanelUsuarios();
        PanelFavoritos = super.getPanelFavoritos();
        PanelGrupos = super.getPanelGrupos();
        transmitter = Thread_Transmitter.transmitter;
        receiver = Thread_Receiver.receiver;
        receiver.onUpdate = () -> LoadUsuarios();
        /*JComponent_Usuario com = new JComponent_Usuario("Malditasea", true);
        this.PanelUsuarios.add(com);*/
        super.setOnBtnGruposClick(() -> BtnGruposClick());
        super.setOnBtnFavoritosClick(() -> BtnFavoritosClick());
        super.setOnMenuSalirClick(() -> MenuSalirClick());
        LoadUsuarios();
        LoadGrupos();

    }

    private void LoadUsuarios() {
        PanelUsuarios.removeAll();
        PanelFavoritos.removeAll();
        PanelUsuarios.revalidate();
        PanelFavoritos.revalidate();
        PanelUsuarios.repaint();
        PanelFavoritos.repaint();
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
        String username, nombre;
        ArrayList<Funcion_AgregarFavorito> listas = new ArrayList<Funcion_AgregarFavorito>();
        for (Component c : PanelUsuarios.getComponents()) {
            if (((JComponent_Usuario) c).getRadioButton().isSelected()) {
                username = ((JComponent_Usuario) c).username;
                nombre = ((JComponent_Usuario) c).getLblUsuario().getText();
                Funcion_AgregarFavorito funcion = new Funcion_AgregarFavorito(username, nombre, () -> LoadUsuarios());
                funcion.setVisible(true);
                listas.add(funcion);
            }
        }
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
                    JComponent_Usuario com = new JComponent_Usuario(c.nombre, c.connected, c.username);
                    System.out.println(c.username + " " + String.valueOf(c.connected) + "\n");
                    com.setOnMouseEnter(() -> {
                        this.setCursor(Cursor.HAND_CURSOR);
                    });
                    com.setOnMouseLeave(() -> {
                        this.setCursor(Cursor.DEFAULT_CURSOR);
                    });
                    com.setOnInformationClick(() -> {
                        InformationClick(c);
                    });
                    com.revalidate();
                    PanelUsuarios.add(com);
                    for (Funcion_Conversacion fc : conversaciones) {
                        if (fc.usuario.getId_usuario().equals(c.username)) {
                            fc.setNewOnline(c.connected);
                        }
                    }
                }
            }
            for (UsuarioSerializable c : a) {
                JComponent_Favorito com = new JComponent_Favorito(c.nombre, c.connected);
                System.out.println(c.username + " " + String.valueOf(c.connected) + "\n");
                com.revalidate();
                PanelFavoritos.add(com);
                for (Funcion_Conversacion fc : conversaciones) {
                    if (fc.usuario.getId_usuario().equals(c.username)) {
                        fc.setNewOnline(c.connected);
                    }
                }
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
        Usuario usuario = new Usuario(US.username, "", US.nombre);
        Funcion_Conversacion funcion_conversacion = new Funcion_Conversacion(usuario, US.connected);
        funcion_conversacion.close = () -> {conversaciones.remove(funcion_conversacion);};
        funcion_conversacion.setVisible(true);
        conversaciones.add(funcion_conversacion);
    }

}
