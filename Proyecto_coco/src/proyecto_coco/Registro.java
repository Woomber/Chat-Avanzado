/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_coco;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Danae
 */
public class Registro extends JFrame{
    /* componentes */
    private final JMenuBar barraMenu = new JMenuBar();
    private final JMenu mnuIngreso = new JMenu("Ir a Ingreso");

    /* Formulario */  
    private JLabel lblNombre,lblContrasena, lblPrueba, lblTitulo, lblespacio, lblUsuario;
    private JTextField txtNombre, txtUsuario;
    private JPasswordField psfContrasena, psfPrueba;
    private JButton aceptar;   

    
    public Registro(){
        /*Ventana*/
        super("Registro"); 
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage());/*icono*/
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,250);
        
        /* Menu */
        barraMenu.add(mnuIngreso);
        this.setJMenuBar(barraMenu);
        

        /* Formulario */ 
        lblTitulo = new JLabel ("Registro de usuario");
        lblNombre = new JLabel("Nombre");
        lblContrasena = new JLabel ("Contraseña");
        lblPrueba= new JLabel ("Verificacion de contraseña");
        lblespacio = new JLabel (" ");
        lblUsuario = new JLabel ("Usuario"); 
        
        txtNombre = new JTextField();
        txtUsuario= new JTextField();
        psfContrasena = new JPasswordField();
        psfPrueba = new JPasswordField();
        aceptar = new JButton ("Registrarme");
     
       /* Cambio de fuente de letra  */
       Font auxFont =lblTitulo.getFont();/*obtener tipo de fuente usada actual*/
       lblTitulo.setFont(new Font (auxFont.getFontName(),auxFont.getStyle(),18));

       /*acomodo de componentes*/
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
                                    .addComponent(lblUsuario)
                                    .addComponent(txtUsuario,25,25,25)
                        )
                    .addGroup
                        (
                                orden.createParallelGroup()
                                    .addComponent(lblContrasena)
                                    .addComponent(psfContrasena,25,25,25)
                        )
                    .addGroup
                        (
                                orden.createParallelGroup()
                                    .addComponent(lblPrueba)
                                    .addComponent(psfPrueba,25,25,25)
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
                                                    .addComponent(lblUsuario)
                                                    .addComponent(lblContrasena)
                                                    .addComponent(lblPrueba)                                                
                                        )
                                    .addGroup
                                        (
                                                orden.createParallelGroup()
                                                    .addComponent(txtNombre)
                                                    .addComponent(txtUsuario)
                                                    .addComponent(psfContrasena)
                                                    .addComponent(psfPrueba) 
                                                .addComponent(aceptar, 250, 250, 250)
                                        )
                        )
            );
       
       this.setLayout(orden);
       this.pack();
       
    }
}
