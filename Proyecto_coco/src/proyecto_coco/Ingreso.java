/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_coco;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Danae
 */
public class Ingreso extends JFrame{
    /* Ventana */
    private final JMenuBar barraMenu = new JMenuBar();
    private final JMenu mnuRegistro = new JMenu("Ir a Registro");
    
    /* Formulario */
    private JLabel lblNombre,lblContrasena, lblTitulo, lblespacio;
    private JTextField txtNombre;
    private JPasswordField psfContrasena;
    private JButton aceptar; 

    public Ingreso(){
        /*Ventana*/
        super("Ingreso"); 
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage());
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,250);
        
        /* Menu */
        barraMenu.add(mnuRegistro);
        this.setJMenuBar(barraMenu);
        
        /*Formulario*/
        lblTitulo = new JLabel ("Ingreso de usuario");
        lblNombre = new JLabel("Usuario");
        lblContrasena = new JLabel ("Contraseña");
        lblespacio = new JLabel (" ");
        
        txtNombre = new JTextField();
        psfContrasena = new JPasswordField();
        aceptar = new JButton ("Ingresar");
        
        /* Cambio de fuente de letra  */
        Font auxFont =lblTitulo.getFont();
        lblTitulo.setFont(new Font (auxFont.getFontName(),auxFont.getStyle(),18));

       /*Acomod de componentes*/
       GroupLayout orden = new GroupLayout(this.getContentPane());
       orden.setAutoCreateContainerGaps(true);
       orden.setAutoCreateGaps(true);
       orden.setVerticalGroup
            (
               orden.createSequentialGroup()
                    .addComponent(lblTitulo)
                      .addComponent(lblespacio)
                    .addGroup
                        (
                                orden.createParallelGroup()
                                    .addComponent(lblNombre)
                                    .addComponent(txtNombre,25,25,25)
                        )
                    .addGroup
                        (
                                orden.createParallelGroup()
                                    .addComponent(lblContrasena)
                                    .addComponent( psfContrasena,25,25,25)
                        )
                       .addComponent(aceptar)
            );
       
       orden.setHorizontalGroup
            (
                    orden.createParallelGroup()
                            .addComponent(lblTitulo, GroupLayout.Alignment.CENTER)
                    .addGroup
                        (
                                orden.createSequentialGroup()
                                    .addGroup
                                        (
                                                orden.createParallelGroup()
                                                    
                                                    .addComponent(lblespacio)
                                                    .addComponent(lblNombre)
                                                    .addComponent(lblContrasena)                                              
                                        )
                                    .addGroup
                                        (
                                                orden.createParallelGroup()
                                                    .addComponent(txtNombre)
                                                    .addComponent(psfContrasena)
                                                .addComponent(aceptar, 250, 250, 250)
                                        )
                        )
            );
       
       this.setLayout(orden);
       this.pack();
       
    }
}
