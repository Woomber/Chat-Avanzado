/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Delegates.MouseClick;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author co2
 */
public class JFrame_Conversacion extends JFrame implements ActionListener{

    /* Ventana */
    private final JMenuBar MenuBar = new JMenuBar();
    //private final JMenu mnuPrincipal = new JMenu("Ir a Pagina principal");
    private final JMenu MenuOpciones = new JMenu("Opciones");

    /* Mensajes */
    private JLabel LblMensaje, ImgIcono, LblNombreConversacion;
    private JTextField TxtMensaje;
    private JButton BtnEnviar;
    private JTextPane PanelConversacion;
    private Image icon;
    private JMenuItem MenuSalirGrupo, MenuAgregarUsuarios;
    
    MouseClick OnBtnEnviarClick, OnMenuSalirGrupoClick, OnMenuAgregarUsuariosClick;
    
    private String name;
    private boolean isGroup;
    
    

    public JFrame_Conversacion(String name, boolean isGroup) {
        /*Ventana*/
        super("Chat");
        this.setResizable(false);
        this.name = name;
        this.isGroup = isGroup;
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage());
        /*icono*/
        this.setSize(500, 250);
        loadFrameDetails();
    }
    
    private void loadFrameDetails(){
        /* Menu */
        //barraMenu.add(mnuPrincipal);
        if(isGroup){
            MenuBar.add(MenuOpciones);
            MenuSalirGrupo = new JMenuItem("Salir del grupo");
            MenuSalirGrupo.addActionListener(this);
            MenuOpciones.add(MenuSalirGrupo);
            MenuAgregarUsuarios = new JMenuItem("Agregar usuarios");
            MenuAgregarUsuarios.addActionListener(this);
            MenuOpciones.add(MenuAgregarUsuarios);
            this.setJMenuBar(MenuBar);
        }

        /*Ingreso de icono de usuario*/
        ImgIcono = new JLabel();
        ImageIcon usuario;
        if(isGroup) usuario = new ImageIcon(getClass().getResource("../Imagen/grupo.png"));
        else usuario = new ImageIcon(getClass().getResource("../Imagen/usuario.png"));
        ImageIcon icono = new ImageIcon(usuario.getImage());
        ImgIcono.setIcon(icono);
        LblNombreConversacion = new JLabel(name);
        /*seccion de mensaje*/

        PanelConversacion = new JTextPane();
        PanelConversacion.setEditable(false);
        JScrollPane mC = new JScrollPane(PanelConversacion);

        LblMensaje = new JLabel("Mensaje ");
        TxtMensaje = new JTextField();

        BtnEnviar = new JButton("Enviar");
        BtnEnviar.addActionListener(this);
        /* panel de mensaje */
        GroupLayout orden = new GroupLayout(this.getContentPane());
        orden.setAutoCreateContainerGaps(true);
        orden.setAutoCreateGaps(true);
        orden.setVerticalGroup(orden.createSequentialGroup()
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(ImgIcono)
                                        .addComponent(LblNombreConversacion, GroupLayout.Alignment.CENTER)
                        )
                        .addComponent(mC, 200, 200, 200)
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(LblMensaje, GroupLayout.Alignment.CENTER, 25, 25, 25)
                                        .addComponent(TxtMensaje)
                                        .addComponent(BtnEnviar)
                        )
        );

        orden.setHorizontalGroup(orden.createParallelGroup()
                        .addGroup(orden.createSequentialGroup()
                                        .addComponent(ImgIcono, 30, 30, 30)
                                        .addComponent(LblNombreConversacion)
                        )
                        .addComponent(mC)
                        .addGroup(orden.createSequentialGroup()
                                        .addComponent(LblMensaje, 60, 60, 60)
                                        .addComponent(TxtMensaje, 280, 280, 280)
                                        .addComponent(BtnEnviar)
                        )
        );

        this.setLayout(orden);
        this.pack();
    }

    public void setOnBtnEnviarClick(MouseClick OnBtnEnviarClick) {
        this.OnBtnEnviarClick = OnBtnEnviarClick;
    }

    public void setOnMenuSalirGrupoClick(MouseClick OnMenuSalirGrupoClick) {
        this.OnMenuSalirGrupoClick = OnMenuSalirGrupoClick;
    }

    public void setOnMenuAgregarUsuariosClick(MouseClick OnMenuAgregarUsuariosClick) {
        this.OnMenuAgregarUsuariosClick = OnMenuAgregarUsuariosClick;
    }

    public JTextField getTxtMensaje() {
        return TxtMensaje;
    }

    public JTextPane getPanelConversacion() {
        return PanelConversacion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == BtnEnviar){
                OnBtnEnviarClick.Click();
            }
            if(e.getSource() == MenuSalirGrupo){
                OnMenuSalirGrupoClick.Click();
            }
            if(e.getSource() == MenuAgregarUsuarios){
                OnMenuAgregarUsuariosClick.Click();
            }
        } catch (Exception ex) {
        }
    }
    
    
}
