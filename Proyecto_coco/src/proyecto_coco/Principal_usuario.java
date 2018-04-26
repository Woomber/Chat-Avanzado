/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_coco;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author co2
 */

public class Principal_usuario extends JComponent {
    
    /* Componentes*/ 
    private JLabel imagen, lblusuario;
    private JRadioButton jrbSeleccion;
    public Principal_usuario()
    {
        /*Ingreso de icono de usuario*/ 
        imagen = new JLabel();
        ImageIcon usuario = new ImageIcon(getClass().getResource("../Imagen/usuario.png"));
        ImageIcon icono = new ImageIcon (usuario.getImage());
        imagen.setIcon(icono);
        
        lblusuario= new JLabel("Ejemplo de nombre usuario");
        jrbSeleccion = new JRadioButton();

        /* panel de mensaje */
       GroupLayout orden = new GroupLayout(this);
       orden.setAutoCreateContainerGaps(true);
       orden.setAutoCreateGaps(true);
       orden.setVerticalGroup
            (
             orden.createSequentialGroup()
                    .addGroup(
                        orden.createParallelGroup()
                            .addComponent(imagen)
                            .addComponent(lblusuario, GroupLayout.Alignment.CENTER)
                            .addComponent(jrbSeleccion, GroupLayout.Alignment.CENTER)
                    )
            );
       
       orden.setHorizontalGroup
            (
                    orden.createParallelGroup()
                    .addGroup(
                        orden.createSequentialGroup()
                        .addComponent(imagen,40,40,40)
                        .addComponent(lblusuario, 200,200,200)
                        .addComponent(jrbSeleccion, 40, 40, 40)
                        )
            );
       this.setLayout(orden);
       //this.pack();   
    }

}


