/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Delegates.Update;
import Documents.DocumentManager;
import Exceptions.JsonParserException;
import GUI.JComponent_Favorito;
import GUI.JComponent_Grupo;
import GUI.JComponent_Usuario;
import GUI.JFrame_Conversacion;
import GUI.JFrame_Principal;
import General.DialogConfirm;
import General.MessageBox;
import Json.JsonParser;
import Models.Usuario;
import ModelsSerializables.MensajeSerializable;
import ModelsSerializables.UsuarioSerializable;
import PaquetesModels.Paquete;
import Requests.AmigoRequest;
import Requests.GrupoRequest;
import Requests.GruposUsuarioRequest;
import Requests.LoginRequest;
import Requests.LogoutRequest;
import Requests.ReplyGrupoRequest;
import Requests.UsuariosRequest;
import Responses.GenericResponse;
import Responses.GrupoResponse;
import Responses.GruposUsuarioResponse;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Funcion_Principal extends JFrame_Principal {

    private String userFavorito;
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

    private void BtnGruposClick() {
        ArrayList<String> misUsuariosGrupo = new ArrayList<>();
        for (Component c : PanelUsuarios.getComponents()) {
            if (((JComponent_Usuario) c).getRadioButton().isSelected()) {
                misUsuariosGrupo.add(((JComponent_Usuario) c).getUsername());

            }
        }
        for (Component c : PanelFavoritos.getComponents()) {
            if (((JComponent_Favorito) c).getRadioButton().isSelected()) {
                misUsuariosGrupo.add(((JComponent_Favorito) c).getUsername());
            }
        }
        Funcion_RegistroGrupo funcion = new Funcion_RegistroGrupo(misUsuariosGrupo);
        funcion.setVisible(true);
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

        transmitter.setAction((Socket socket, PrintWriter pw, BufferedReader read) -> LogOut(socket, pw, read));
        transmitter.StartThread();
    }

    private void LogOut(Socket socket, PrintWriter pw, BufferedReader read) {
        try {
            receiver.closeAll();
            pw.println(JsonParser.paqueteToJson(new LogoutRequest()));
            socket.close();
            pw.close();
            read.close();
            Usuario.emisor = null;
            new Funcion_Ingreso().setVisible(true);
            this.setVisible(false);

        } catch (Exception ex) {

        }
    }

    private void listaUsuarios(Socket socket, PrintWriter pw, BufferedReader read) {

        try {
            loadGroups(socket, pw, read);
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
                JComponent_Favorito com = new JComponent_Favorito(c.username, c.nombre, c.connected);
                System.out.println(c.username + " " + String.valueOf(c.connected) + "\n");
                userFavorito = c.username;
                com.setOnInformationEnter(() -> {
                    this.setCursor(Cursor.HAND_CURSOR);
                });
                com.setOnInformationLeave(() -> {
                    this.setCursor(Cursor.DEFAULT_CURSOR);
                });
                com.setOnInformationClick(() -> {
                    InformationClick(c);
                });
                com.revalidate();
                com.setOnBtnEliminarClick(() -> iniciarEliminarFavorito(userFavorito));
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
        funcion_conversacion.close = () -> {
            conversaciones.remove(funcion_conversacion);
        };
        funcion_conversacion.setVisible(true);
        conversaciones.add(funcion_conversacion);
    }

    private void iniciarEliminarFavorito(String user) {
        userFavorito = user;
        transmitter.setAction(
                (Socket socket, PrintWriter pw, BufferedReader read)
                -> eliminarFavorito(socket, pw, read)
        );

        transmitter.StartThread();
    }

    private void eliminarFavorito(Socket socket, PrintWriter pw, BufferedReader read) {
        try {
            pw.println(JsonParser.paqueteToJson(new AmigoRequest(userFavorito, "HOLA", AmigoRequest.Operacion.REMOVE)));
            Paquete paquete = JsonParser.jsonToPaquete(read.readLine());

            if (paquete.getValue(GenericResponse.PARAM_STATUS).equals(GenericResponse.Status.INCORRECT.getName())) {
                MessageBox.Show("", "Eh we, fijate que no se pudo.");
            }
            if (paquete.getValue(GenericResponse.PARAM_STATUS).equals(GenericResponse.Status.CORRECT.getName())) {

            }
        } catch (IOException | JsonParserException ex) {
            System.out.println("");
            System.out.println(ex.getMessage());
            System.out.println("");
        }
        LoadUsuarios();
    }

    private void loadGroups(Socket socket, PrintWriter pw, BufferedReader read) {
        try {
            pw.println(JsonParser.paqueteToJson(new GruposUsuarioRequest()));
            Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
            String json = paquete.getValue(GruposUsuarioResponse.PARAM_GRUPOS);
            Integer[] ids = JsonParser.jsonToIntegers(json);
            MessageBox.Show("", (ids.length)==0?"No hay nada":"Hay algo");
            for (Integer i : ids) {
                MessageBox.Show("", i.toString());
                pw.println(JsonParser.paqueteToJson(new GrupoRequest(i.intValue())));
                Paquete grupoPaquete = JsonParser.jsonToPaquete(read.readLine());
                String id = grupoPaquete.getValue(GrupoResponse.PARAM_ID_GRUPO);
                String nombreGrupo = grupoPaquete.getValue(GrupoResponse.PARAM_NOMBRE_GRUPO);
                MessageBox.Show("", nombreGrupo);
                String status = grupoPaquete.getValue(GrupoResponse.PARAM_STATUS);
                MessageBox.Show("", status);
                boolean stat = false;
                if (status.equals(GrupoResponse.Status.PENDING.getName())) {
                    if (DialogConfirm.Show("Ha sido invitado a participar en el grupo: " + nombreGrupo + ".\n ¿Desea aceptar la invitación?")) {
                        stat = true;
                    }
                    pw.println(JsonParser.paqueteToJson(new ReplyGrupoRequest(Integer.valueOf(id), stat, Usuario.emisor.getId_usuario())));
                    Paquete responsePaquete = JsonParser.jsonToPaquete(read.readLine());
                }
                else if(status.equals(GrupoResponse.Status.IN_GROUP.getName())){
                    stat = true;
                }
                
                MessageBox.Show("", "aquitoy");
                if (stat) {
                    MessageBox.Show("", "ahora aquí");
                    MensajeSerializable[] a = JsonParser.jsonToMensajes(grupoPaquete.getValue(GrupoResponse.PARAM_MENSAJES));
                    for (MensajeSerializable m : a) {
                        DocumentManager.SaveMessage(id + "_" + nombreGrupo, m.getOrigen(), m.getMensaje(), true);
                    }
                    JComponent_Grupo grupo = new JComponent_Grupo(nombreGrupo);
                    PanelGrupos.add(grupo);
                    MessageBox.Show("", "yacabe");
                }

            }
            PanelGrupos.revalidate();
        } catch (JsonParserException | IOException ex) {
            MessageBox.Show("", ex.getMessage());
        }
    }

}
