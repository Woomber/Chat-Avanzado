/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_coco;

import java.awt.Image;
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

public class Conversacion extends JFrame {
    /* Ventana */
    private final JMenuBar barraMenu = new JMenuBar();
    private final JMenu mnuPrincipal = new JMenu("Ir a Pagina principal");
    private final JMenu mnuOpciones = new JMenu("Opciones");
    
    /* Mensajes */ 
    private JLabel lblMensaje,imagen, lblusuario;
    private JTextField txtMensaje;
    private JButton enviar; 
    private JTextPane jpConversacion;
    private Image icon;
    private JMenuItem jmiOpciones;
    public Conversacion()
    {
        /*Ventana*/
        super("Chat");        
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage()); /*icono*/
        this.setSize(500,250);
        
        /* Menu */
        barraMenu.add(mnuPrincipal);
        barraMenu.add(mnuOpciones);
        jmiOpciones = new JMenuItem("Salir del grupo");
        mnuOpciones.add(jmiOpciones);
        jmiOpciones= new JMenuItem("Agregar usuarios");
        mnuOpciones.add(jmiOpciones);
        this.setJMenuBar(barraMenu);
        
        
        /*Ingreso de icono de usuario*/ 
        imagen = new JLabel();
        ImageIcon usuario = new ImageIcon(getClass().getResource("../Imagen/usuario.png"));
        ImageIcon icono = new ImageIcon (usuario.getImage());
        imagen.setIcon(icono);
        lblusuario= new JLabel("Nombre de usuario");
        /*seccion de mensaje*/
                
        jpConversacion= new JTextPane();
        jpConversacion.setEditable(false);
        JScrollPane mC = new JScrollPane(jpConversacion);
        
        lblMensaje = new JLabel("Mensaje ");
        txtMensaje = new JTextField();
        
        enviar = new JButton ("Enviar");
        
        
        /* panel de mensaje */
       GroupLayout orden = new GroupLayout(this.getContentPane());
       orden.setAutoCreateContainerGaps(true);
       orden.setAutoCreateGaps(true);
       orden.setVerticalGroup
            (
             orden.createSequentialGroup()
                    .addGroup(
                        orden.createParallelGroup()
                            .addComponent(imagen)
                            .addComponent(lblusuario, GroupLayout.Alignment.CENTER)
                    )
                    .addComponent(mC,200,200,200)
                    .addGroup
                        (
                                orden.createParallelGroup()
                                    .addComponent(lblMensaje,GroupLayout.Alignment.CENTER,25,25,25)
                                    .addComponent(txtMensaje)
                                    .addComponent(enviar)
                        )
            );
       
       orden.setHorizontalGroup
            (
                    orden.createParallelGroup()
                    .addGroup(
                        orden.createSequentialGroup()
                        .addComponent(imagen,30,30,30)
                        .addComponent(lblusuario)
                        )
                    .addComponent(mC)
                    .addGroup
                        (
                                orden.createSequentialGroup()
                                      .addComponent(lblMensaje,60,60,60)   
                                      .addComponent(txtMensaje,280,280,280)                                               
                                      .addComponent(enviar)
                                )
            );
       
       
       this.setLayout(orden);
       this.pack();   
    }

}

