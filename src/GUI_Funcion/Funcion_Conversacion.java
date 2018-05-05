/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Funcion;

import Delegates.Close;
import Documents.DocumentManager;
import Exceptions.JsonParserException;
import GUI.JFrame_Conversacion;
import General.MessageBox;
import Json.JsonParser;
import Models.Grupo;
import Models.Usuario;
import PaquetesModels.Paquete;
import Requests.MensajeRequest;
import Threads.Thread_Transmitter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Cristofer
 */
public class Funcion_Conversacion extends JFrame_Conversacion implements Runnable {

    boolean isOnline;
    boolean newOnline;

    Thread checkOnline;

    public Usuario usuario;
    Grupo grupo;

    int count;

    JPanel PanelConversacion;
    JTextField TxtMensaje;

    public Close close;

    private Thread hilo;
    Thread_Transmitter transmitter = Thread_Transmitter.transmitter;

    public Funcion_Conversacion() {
        super("hola", false);
    }

    public void checkOnline() {
        if (isOnline != newOnline) {
            isOnline = newOnline;
            SetOnline();
        }
    }

    public void SetOnline() {
        if (isOnline) {
            super.setOnBtnEnviarClick(() -> OnlineBtnEnviarClick());
            TxtMensaje.setEnabled(true);
            TxtMensaje.setText("");
            MessageBox.Show("",usuario.getId_usuario() + " se ha conectado");
        } else {
            super.setOnBtnEnviarClick(() -> OfflineBtnEnviarClick());
            TxtMensaje.setEnabled(false);
            TxtMensaje.setText("No puedes contestar a esta conversación.");
            TxtMensaje.setAlignmentX(CENTER_ALIGNMENT);
            MessageBox.Show("",usuario.getId_usuario() + " se ha desconectado");
        }
        this.revalidate();
    }

    public void setNewOnline(boolean newOnline) {
        this.newOnline = newOnline;
        checkOnline();
    }
    
    public void close(){
        this.setVisible(false);
        close.Invoke();
    }

    public Funcion_Conversacion(Usuario usuario, boolean isOnline) {
        super(usuario.getNombre(), false);

        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        super.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                close();
            }
        });

        this.isOnline = isOnline;
        this.newOnline = isOnline;
        this.usuario = usuario;
        count = DocumentManager.GetNumberLines(usuario.getId_usuario(), false);
        PanelConversacion = super.getPanelConversacion();
        TxtMensaje = super.getTxtMensaje();
        if (isOnline) {
            super.setOnBtnEnviarClick(() -> OnlineBtnEnviarClick());
        } else {
            super.setOnBtnEnviarClick(() -> OfflineBtnEnviarClick());
            TxtMensaje.setEnabled(false);
            TxtMensaje.setText("No puedes contestar a esta conversación.");
            TxtMensaje.setAlignmentX(CENTER_ALIGNMENT);
        }
        LoadInformation();
        hilo = new Thread(this);
        hilo.start();
        /*checkOnline = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    checkOnline();
                }
            }
        });
        checkOnline.start();*/
    }

    public Funcion_Conversacion(Grupo grupo) {
        super(grupo.getNombre_grupo(), true);
        PanelConversacion = super.getPanelConversacion();
        TxtMensaje = super.getTxtMensaje();
        super.setOnBtnEnviarClick(() -> GroupBtnEnviarClick());
        super.setOnMenuAgregarUsuariosClick(() -> AgregarUsuariosClick());
        super.setOnMenuSalirGrupoClick(() -> MenuSalirGrupoClick());
    }

    private void LoadInformation() {
        ArrayList<String> messages = DocumentManager.GetLastNumberMessages(usuario.getId_usuario(), 5, false);

        if (messages == null) {
            return;
        }

        for (String s : messages) {

            String[] parts = s.split(("l==>"));
            String from = parts[0].trim().replace("[", "").replace("]", "");
            String message = parts[1].trim();
            JLabel label;
            if (from.equals(Usuario.emisor)) {
                label = new JLabel(from + ">" + message, SwingConstants.RIGHT);
                label.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
                label.setHorizontalTextPosition(SwingConstants.RIGHT);
            } else {
                label = new JLabel(from + "> " + message, SwingConstants.RIGHT);

            }
            PanelConversacion.add(label);

        }

    }

    private void OnlineBtnEnviarClick() {
        if (TxtMensaje.getText().equals("")) {
            MessageBox.Show("Error", "No puedes enviar un mensaje vacío");
            return;
        }
        transmitter.setAction((Socket socket, PrintWriter pw, BufferedReader read) -> {
            try {
                pw.println(JsonParser.paqueteToJson(new MensajeRequest(usuario.getId_usuario(), TxtMensaje.getText())));
                Paquete paquete = JsonParser.jsonToPaquete(read.readLine());
                DocumentManager.SaveMessage(usuario.getId_usuario(), Usuario.emisor.getId_usuario(), TxtMensaje.getText(), false);
                TxtMensaje.setText("");
            } catch (JsonParserException | IOException ex) {
            }
        });
        transmitter.StartThread();

    }

    private void OfflineBtnEnviarClick() {

    }

    private void GroupBtnEnviarClick() {

    }

    private void AgregarUsuariosClick() {
        Funcion_AgregarUsuarios funcion = new Funcion_AgregarUsuarios();
        funcion.setVisible(true);
    }

    private void MenuSalirGrupoClick() {

    }

    @Override
    public void run() {
        while (true) {
            int newCount = DocumentManager.GetNumberLines(usuario.getId_usuario(), false);
            if (newCount > count) {
                ArrayList<String> messages = DocumentManager.GetLastNumberMessages(usuario.getId_usuario(), 1, false);
                count = newCount;

                for (String s : messages) {

                    String[] parts = s.split(("l==>"));
                    String from = parts[0].trim().replace("[", "").replace("]", "");
                    String message = parts[1].trim();
                    JLabel label;
                    if (from.equals(Usuario.emisor)) {
                        label = new JLabel(from + ">" + message, SwingConstants.RIGHT);
                        label.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
                        label.setHorizontalTextPosition(SwingConstants.RIGHT);
                    } else {
                        label = new JLabel(from + "> " + message, SwingConstants.RIGHT);

                    }
                    PanelConversacion.add(label);

                }
                PanelConversacion.revalidate();
            }
        }
    }

}
