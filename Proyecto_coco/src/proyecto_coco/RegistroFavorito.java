/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_coco;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author Danae
 */
public class RegistroFavorito extends JFrame{

    
    /* Formulario */
    private JLabel lblApodo, lblTitulo, lblespacio;
    private JTextField txtApodo;
    private JButton jbCrear, jbCancelar;

    public RegistroFavorito(){
        /*Ventana*/
        super("Favoritos"); 
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage());
        this.setSize(500,250);
 
        /*Formulario*/
        lblTitulo = new JLabel ("Agregar a favoritos");
        lblApodo = new JLabel("Apodo");
        lblespacio = new JLabel (" ");
       
        txtApodo = new JTextField();
        jbCrear = new JButton ("Crear");
        jbCancelar = new JButton("Cancelar");
        
       
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
                                    .addComponent(lblApodo)
                                    .addComponent(txtApodo,25,25,25)
                        )
                    .addGroup(
                                orden.createParallelGroup()
                                    .addComponent(jbCrear)
                                    .addComponent(jbCancelar)
                        )
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
                                                    .addComponent(lblApodo)   
                                                    
                                        )
                                    .addGroup(
                                                orden.createParallelGroup()    
                                                    .addComponent(txtApodo, 200,200,200)
                                                    .addGroup(
                                                            orden.createSequentialGroup()
                                                            .addComponent(jbCancelar)
                                                            .addComponent(jbCrear)
                                                     )
                                     )
                        )
            );
       this.setLayout(orden);
       this.pack();
       
    }
}

