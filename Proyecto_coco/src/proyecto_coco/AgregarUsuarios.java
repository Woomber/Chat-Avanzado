/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_coco;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author Danae
 */
public class AgregarUsuarios extends JFrame{

    
    /* Formulario */
    private JLabel  lblTitulo, lblespacio,lblintegrantes;
    private JButton jbAgregar, jbCancelar; 
    private JTextPane jpIntegrantes;
    private JScrollPane scrollpane;
    private JPanel jtpUsuarios;

    public AgregarUsuarios(){
        /*Ventana*/
        super("Agregar usuarios"); 
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage());
        this.setSize(500,250);
 
        /*Formulario*/
        lblTitulo = new JLabel ("Agregar Usuarios");
        lblespacio = new JLabel (" ");
        lblintegrantes = new JLabel("Contactos");
       
        jtpUsuarios = new JPanel();
        
        jbAgregar = new JButton ("Agregar");
        jbCancelar = new JButton("Cancelar");
        
        jtpUsuarios.setLayout(new BoxLayout(jtpUsuarios,BoxLayout.Y_AXIS));
        JScrollPane mU = new JScrollPane(jtpUsuarios);
        jpIntegrantes= new JTextPane();
        
        jpIntegrantes.setEditable(false);
        /* Cambio de fuente de letra  */
        Font auxFont =lblTitulo.getFont();
        lblTitulo.setFont(new Font (auxFont.getFontName(),auxFont.getStyle(),18));

        for(int i=0; i<10;i++){
            jtpUsuarios.add(new Principal_usuario());
        }
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
                        )
                       .addComponent(lblintegrantes,25,25,25)
                       .addComponent(mU,120,120,120)
                    .addGroup(
                                orden.createParallelGroup()
                                    .addComponent(jbAgregar)
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
                                                    .addComponent(jbCancelar)
                                        )
                                    .addGroup(
                                                orden.createParallelGroup()      
                                                    .addComponent(jbAgregar)
                                     )
                        )
                    .addComponent(lblintegrantes)
                    .addComponent(mU,GroupLayout.Alignment.CENTER,340,340,340)
            );
       this.setLayout(orden);
       this.pack();
       
    }
}
