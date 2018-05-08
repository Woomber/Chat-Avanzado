/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Exceptions.JsonParserException;
import GUI.JComponent_Favorito;
import GUI.JComponent_Usuario;
import GUI.JFrame_AgregarUsuarios;
import General.MessageBox;
import Json.JsonParser;
import Models.Usuario;
import ModelsSerializables.UsuarioSerializable;
import PaquetesModels.Paquete;
import Requests.CreateGrupoRequest;
import Requests.AlterGrupoRequest;
import Requests.UsuariosRequest;
import Responses.UsuariosResponse;
import Threads.Thread_Transmitter;
import java.awt.Component;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author Cristofer
 */
public class Funcion_AgregarUsuarios extends JFrame_AgregarUsuarios {

    ArrayList<String> users;
    ArrayList<String> usernames;
    JPanel panelUsuarios;
    Thread_Transmitter transmitter;
    String[] miembrosGrupo;
    ArrayList<String> miembrosCompletos;
    int id;

    
    public Funcion_AgregarUsuarios(String[] miembrosGrupo, int id) {
        this.id = id;
        this.miembrosGrupo = miembrosGrupo;
        this.panelUsuarios = super.getPanelUsuarios();
        transmitter = Thread_Transmitter.transmitter;
        super.setOnBtnAgregarClick(() -> iniciarAgregarUsuarios());
        super.setOnBtnCancelarClick(() -> this.setVisible(false));
        this.miembrosCompletos = new ArrayList<>();
        for (String c : miembrosGrupo) {
            miembrosCompletos.add(c);
        }
        iniciarCargaUsuarios();
    }

    private void iniciarCargaUsuarios() {
        transmitter.setAction(
                (Socket socket, PrintWriter pw, BufferedReader read)
                -> listaUsuarios(socket, pw, read)
        );
        transmitter.StartThread();
    }

    private void iniciarAgregarUsuarios() {
        transmitter.setAction(
                (Socket socket, PrintWriter pw, BufferedReader read)
                -> agregarUsuarios(socket, pw, read)
        );
        transmitter.StartThread();
    }

    private void agregarUsuarios(Socket socket, PrintWriter pw, BufferedReader read) {
        ArrayList<String> nuevosMiembros = new ArrayList<>();
        for (Component c : this.panelUsuarios.getComponents()) {
            if (((JComponent_Usuario) c).getRadioButton().isSelected()) {
                nuevosMiembros.add(((JComponent_Usuario) c).getUsername());
                miembrosCompletos.add(((JComponent_Usuario) c).getUsername());
            }
        }

        for (String s : nuevosMiembros) {
            AlterGrupoRequest miAlteracionGrupo = new AlterGrupoRequest(id, s, AlterGrupoRequest.Operacion.ADD);
            try {

                pw.println(JsonParser.paqueteToJson(miAlteracionGrupo));
                Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
                this.setVisible(false);

            } catch (IOException | JsonParserException ex) {
                System.out.println("");
                System.out.println(ex.getMessage());
                System.out.println("");
            }
        }

        //OnUpdate.Invoke();
    }

    private void listaUsuarios(Socket socket, PrintWriter pw, BufferedReader read) {
        try {

            pw.println(JsonParser.paqueteToJson(new UsuariosRequest()));
            Paquete paquete = JsonParser.jsonToPaquete(read.readLine());

            ArrayList<UsuarioSerializable> usuarios = new ArrayList<>();
            ArrayList<UsuarioSerializable> amigos = new ArrayList<>();
            Iterator<UsuarioSerializable> itUsuarios;
            Iterator<UsuarioSerializable> itAmigos;

            UsuarioSerializable[] b = JsonParser.jsonToUsuarios(paquete.getValue(UsuariosResponse.USUARIOS));
            UsuarioSerializable[] a = JsonParser.jsonToUsuarios(paquete.getValue(UsuariosResponse.AMIGOS));

            for (UsuarioSerializable c : b) {
                usuarios.add(c);
            }

            for (UsuarioSerializable c : a) {
                amigos.add(c);
            }

            itUsuarios = usuarios.iterator();
            itAmigos = amigos.iterator();

            for (String c : miembrosCompletos) {
                while (itUsuarios.hasNext()) {
                    if (itUsuarios.next().username.equals(c)) {
                        itUsuarios.remove();
                        // If you know it's unique, you could `break;` here
                    }
                }
                itUsuarios = usuarios.iterator();
            }

            for (String c : miembrosCompletos) {
                while (itAmigos.hasNext()) {
                    if (itAmigos.next().username.equals(c)) {
                        itAmigos.remove();
                        // If you know it's unique, you could `break;` here
                    }
                }
                itAmigos = amigos.iterator();
            }

            for (UsuarioSerializable c : usuarios) {
                if (!(c.username.equals(Usuario.emisor.getId_usuario()))) {
                    JComponent_Usuario com = new JComponent_Usuario(c.nombre, c.connected, c.username);
                    System.out.println(c.username + " " + String.valueOf(c.connected) + "\n");

                    com.setOnMouseEnter(() -> {
                        this.setCursor(Cursor.HAND_CURSOR);
                    });
                    com.setOnMouseLeave(() -> {
                        this.setCursor(Cursor.DEFAULT_CURSOR);
                    });
                    com.revalidate();
                    panelUsuarios.add(com);
                }

            }

            for (UsuarioSerializable c : amigos) {

                JComponent_Usuario com = new JComponent_Usuario(c.nombre, c.connected, c.username);
                System.out.println(c.username + " " + String.valueOf(c.connected) + "\n");

                com.setOnMouseEnter(() -> {
                    this.setCursor(Cursor.HAND_CURSOR);
                });
                com.setOnMouseLeave(() -> {
                    this.setCursor(Cursor.DEFAULT_CURSOR);
                });
                com.revalidate();
                panelUsuarios.add(com);

            }

            panelUsuarios.revalidate();
        } catch (IOException | JsonParserException ex) {
            System.out.println("");
            System.out.println(ex.getMessage());
            System.out.println("");
        }
    }

    
    public ArrayList<String> getMiembrosCompletos() {
        return miembrosCompletos;
    }

    
}
