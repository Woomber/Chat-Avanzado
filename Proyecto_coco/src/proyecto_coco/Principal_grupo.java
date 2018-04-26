/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_coco;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author co2
 */

public class Principal_grupo extends JComponent  {
    
    /* Componentes*/ 
    private JLabel imagen, lblusuario;
    private JButton jrbEliminar;
    public Principal_grupo()
    {
        /*Ingreso de icono de usuario*/ 
        imagen = new JLabel();
        ImageIcon usuario = new ImageIcon(getClass().getResource("../Imagen/grupo.png"));
        ImageIcon icono = new ImageIcon (usuario.getImage());
        imagen.setIcon(icono);
        
        lblusuario= new JLabel("Ejemplo de nombre Grupo");
        jrbEliminar = new JButton();
        ImageIcon eliminar = new ImageIcon(getClass().getResource("../Imagen/eliminar.png"));
        ImageIcon iconoEliminar= new ImageIcon( eliminar.getImage());
        jrbEliminar.setIcon(iconoEliminar);

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
                            .addComponent(jrbEliminar, GroupLayout.Alignment.CENTER,30,30,30)
                    )
            );
       
       orden.setHorizontalGroup
            (
                    orden.createParallelGroup()
                    .addGroup(
                        orden.createSequentialGroup()
                        .addComponent(imagen,40,40,40)
                        .addComponent(lblusuario, 200,200,200)
                        .addComponent(jrbEliminar, 30, 30, 30)
                        )
            );
       this.setLayout(orden);
      // this.pack();   
    }

}


