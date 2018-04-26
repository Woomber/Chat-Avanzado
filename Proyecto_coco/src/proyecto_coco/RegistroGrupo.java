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
public class RegistroGrupo extends JFrame{

    
    /* Formulario */
    private JLabel lblGrupo, lblTitulo, lblespacio,lblintegrantes;
    private JTextField txtGrupo;
    private JButton jbCrear, jbCancelar; 
    private JTextPane jpIntegrantes;
    private JScrollPane scrollpane;

    public RegistroGrupo(){
        /*Ventana*/
        super("Crear un grupo"); 
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage());
        this.setSize(500,250);
 
        /*Formulario*/
        lblTitulo = new JLabel ("Crear un nuevo grupo");
        lblGrupo = new JLabel("Nombre del grupo");
        lblespacio = new JLabel (" ");
        lblintegrantes = new JLabel("Integrantes seleccionados");
       
        txtGrupo = new JTextField();
        jbCrear = new JButton ("Crear");
        jbCancelar = new JButton("Cancelar");
        
        jpIntegrantes= new JTextPane();
        JScrollPane mC = new JScrollPane(jpIntegrantes);
        
        jpIntegrantes.setEditable(false);
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
                                    .addComponent(lblGrupo)
                                    .addComponent(txtGrupo,25,25,25)
                        )
                       .addComponent(lblintegrantes,25,25,25)
                       .addComponent(mC,50,50,50)
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
                                                    .addComponent(lblGrupo)   
                                                    .addComponent(jbCancelar)
                                        )
                                    .addGroup(
                                                orden.createParallelGroup()    
                                                    .addComponent(txtGrupo, 200,200,200)   
                                                    .addComponent(jbCrear)
                                     )
                        )
                    .addComponent(lblintegrantes)
                    .addComponent(mC,GroupLayout.Alignment.CENTER,310,310,310)
            );
       this.setLayout(orden);
       this.pack();
       
    }
}
