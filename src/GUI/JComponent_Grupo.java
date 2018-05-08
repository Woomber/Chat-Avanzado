/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Delegates.MouseClick;
import Delegates.MouseEnter;
import Delegates.MouseLeave;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class JComponent_Grupo extends JComponent implements ActionListener, MouseListener {

    /* Componentes*/
    private JLabel ImgUsuario, LblUsuario;
    private JButton BtnEliminar;
    
    private MouseClick BtnEliminarClick;
    
    private MouseClick OnInformationClick;
    private MouseEnter OnInformationEnter;
    private MouseLeave OnInformationLeave;

    String groupName;
            
    /**
     * Constructor encargado de inicializar los parametros
     * @param groupName nombre del grupo
     */
    public JComponent_Grupo(String groupName) {
        this.groupName = groupName;
        loadComponentDetails();
    }

    /**
     * Funcion encargada de crear el bloque de grupo
     */
    private void loadComponentDetails() {
        /*Ingreso de icono de usuario*/
        ImgUsuario = new JLabel();
        ImageIcon usuario = new ImageIcon(getClass().getResource("/Images/grupo.png"));
        ImageIcon icono = new ImageIcon(usuario.getImage());
        ImgUsuario.setIcon(icono);

        LblUsuario = new JLabel(groupName);
        BtnEliminar = new JButton();
        ImageIcon eliminar = new ImageIcon(getClass().getResource("/Images/eliminar.png"));
        ImageIcon iconoEliminar = new ImageIcon(eliminar.getImage());
        BtnEliminar.setIcon(iconoEliminar);
        BtnEliminar.addActionListener(this);
        LblUsuario.addMouseListener(this);
        ImgUsuario.addMouseListener(this);
        /* panel de mensaje */
        GroupLayout orden = new GroupLayout(this);
        orden.setAutoCreateContainerGaps(true);
        orden.setAutoCreateGaps(true);
        orden.setVerticalGroup(orden.createSequentialGroup()
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(ImgUsuario)
                                        .addComponent(LblUsuario, GroupLayout.Alignment.CENTER)
                                        .addComponent(BtnEliminar, GroupLayout.Alignment.CENTER, 30, 30, 30)
                        )
        );

        orden.setHorizontalGroup(orden.createParallelGroup()
                        .addGroup(orden.createSequentialGroup()
                                        .addComponent(ImgUsuario, 40, 40, 40)
                                        .addComponent(LblUsuario, 200, 200, 200)
                                        .addComponent(BtnEliminar, 30, 30, 30)
                        )
        );
        this.setLayout(orden);
        // this.pack();   
    }

    /**
     * 
     * @param BtnEliminarClick recibe el valor del componente cuando es 
     * presionado
     */
    public void setBtnEliminarClick(MouseClick BtnEliminarClick) {
        this.BtnEliminarClick = BtnEliminarClick;
    }
    
    /**
     * se le esta dando funcionalidad al click
     * @param OnInformationClick recibe el evento del clic
     */
    public void setOnInformationClick(MouseClick OnInformationClick) {
        this.OnInformationClick = OnInformationClick;
    }

     /**
     * recibe informacion cuando esta por encima del componente
     * @param OnInformationEnter recibe el evento cuando  esta encima de 
     * un componente
     */
    public void setOnInformationEnter(MouseEnter OnInformationEnter) {
        this.OnInformationEnter = OnInformationEnter;
    }

   /**
     * recibe informaicon cuando sale del componente
     * @param OnInformationLeave  recibe el evento cuando sale de por encima 
     * del componente
     */
    public void setOnInformationLeave(MouseLeave OnInformationLeave) {
        this.OnInformationLeave = OnInformationLeave;
    }
    
    
    /**
     * detecta si fue presionado el boton de eliminar
     * @param e evento que se acciona 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            BtnEliminarClick.Click();
        } catch (Exception ex) {
            
        }
    }

    
    /**
     * Permite entrar a la conversacion ya sea presionando la imagen o el texto
     * @param e evento que fue accionado 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            if(e.getSource() == ImgUsuario || e.getSource() == LblUsuario){
                OnInformationClick.Click();
            }
        } catch (Exception ex) {
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
 * Detecta sobre que componente esta encima
 * @param e evento accionado 
 */
    @Override
    public void mouseEntered(MouseEvent e) {
        try {
            if(e.getSource() == ImgUsuario || e.getSource() == LblUsuario){
                OnInformationEnter.Enter();
            }
        } catch (Exception ex) {
        }
    }

    /**
     * detecta que salio del un componente
     * @param e evento accionado 
     */
    @Override
    public void mouseExited(MouseEvent e) {
        try {
            if(e.getSource() == ImgUsuario || e.getSource() == LblUsuario){
                OnInformationLeave.Leave();
            }
        } catch (Exception ex) {
        }
    }
}
