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
import Requests.UsuariosRequest;
import Responses.UsuariosResponse;
import Threads.Thread_Transmitter;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
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

    public Funcion_AgregarUsuarios(String[] miembrosGrupo) {
        this.miembrosGrupo = miembrosGrupo;
        this.panelUsuarios = super.getPanelUsuarios();
        transmitter = Thread_Transmitter.transmitter;
        iniciarCargaUsuarios();
    }

    private void iniciarCargaUsuarios() {
        transmitter.setAction(
                (Socket socket, PrintWriter pw, BufferedReader read)
                -> listaUsuarios(socket, pw, read)
        );
        transmitter.StartThread();
    }

    private void listaUsuarios(Socket socket, PrintWriter pw, BufferedReader read) {
        try {

            pw.println(JsonParser.paqueteToJson(new UsuariosRequest()));
            Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
            
            ArrayList<String> lista = new ArrayList<>();
            for(String c: miembrosGrupo){
                lista.add(c);
            }
            
            
            UsuarioSerializable[] b = JsonParser.jsonToUsuarios(paquete.getValue(UsuariosResponse.USUARIOS));
            UsuarioSerializable[] a = JsonParser.jsonToUsuarios(paquete.getValue(UsuariosResponse.AMIGOS));

            

                for (UsuarioSerializable c : a) {
                                for (String ed : lista) {
                    if (!(c.username.equals(ed))) {
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
                    }else{
                        lista.remove(ed);
                    }
                }
            }
            
            lista = new ArrayList<>();
            for(String c: miembrosGrupo){
                lista.add(c);
            }
            
            for (String d : lista) {
              
                for (UsuarioSerializable c : b) {
                 
                    if (!(c.username.equals(Usuario.emisor.getId_usuario())) && !(c.username.equals(d))) {
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

                    }else{
                        lista.remove(d);
                    }
                }
            }

            panelUsuarios.revalidate();
        } catch (IOException | JsonParserException ex) {
            System.out.println("");
            System.out.println(ex.getMessage());
            System.out.println("");
        }
    }

}
