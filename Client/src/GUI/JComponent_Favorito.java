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
import javax.swing.JRadioButton;

/**
 *
 * @author co2
 */
public class JComponent_Favorito extends JComponent implements ActionListener, MouseListener {

    /* Componentes*/
    private JLabel ImgUsuario, LblUsuario;
    private JButton BtnEliminar;
    private JRadioButton radioButton;
    
    
    private MouseClick BtnEliminarClick;
    
    private MouseClick OnInformationClick;
    private MouseEnter OnInformationEnter;
    private MouseLeave OnInformationLeave;

    private String username;
    private String apodo;
    private boolean isOnline;
    
    /**
     * Constructor encargado de inicializar los parametros
     * @param username Nombre de usuario
     * @param apodo apodo del usuario
     * @param isOnline SI  esta conectado
     */
    public JComponent_Favorito(String username, String apodo, boolean isOnline) {
        this.username = username;
        this.apodo = apodo;
        this.isOnline = isOnline;
        loadComponentDetails();
    }
    /**
     * Crea el bloque donde aparece un usuario que es tu amigo o favorito 
     */
    private void loadComponentDetails(){
        /*Ingreso de icono de usuario*/
        ImgUsuario = new JLabel();
        ImageIcon usuario;
        if(isOnline) usuario = new ImageIcon(getClass().getResource("/Images/favorito_enlinea.png"));
        else usuario = new ImageIcon(getClass().getResource("/Images/favorito.png"));
        ImageIcon icono = new ImageIcon(usuario.getImage());
        ImgUsuario.setIcon(icono);
        
        LblUsuario = new JLabel(apodo);
        radioButton = new JRadioButton();
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
                                        .addComponent(radioButton,GroupLayout.Alignment.CENTER, 25, 25, 25)
                                        .addComponent(BtnEliminar, GroupLayout.Alignment.CENTER, 30, 30, 30)
                        )
        );

        orden.setHorizontalGroup(orden.createParallelGroup()
                        .addGroup(orden.createSequentialGroup()
                                        .addComponent(ImgUsuario, 40, 40, 40)
                                        .addComponent(LblUsuario, 180, 180, 180)
                                        .addComponent(radioButton,30,30,30)
                                        .addComponent(BtnEliminar, 30, 30, 30)
                        )
        );
        this.setLayout(orden);
        //this.pack();   
    }

   

    /**
     * Le asigna funcionamiento al boton de eliminar 
     * @param BtnEliminarClick recibe el evento click en el boton de eliminar 
     */
    public void setOnBtnEliminarClick(MouseClick BtnEliminarClick) {
        this.BtnEliminarClick = BtnEliminarClick;
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
     * Recibe informacion cuando hace clic en al entrar a la conexion 
     * @param OnInformationClick recibe el evento click en abrir de conversacion
     */
    public void setOnInformationClick(MouseClick OnInformationClick) {
        this.OnInformationClick = OnInformationClick;
    }

    
    /**
     * 
     * @return regresa el valor de radiobutton
     */
    public JRadioButton getRadioButton() {
        return radioButton;
    }

  /**
   * 
   * @return regresa el valor de el usuario
   */
    public String getUsername() {
        return username;
    }
    /**
     * 
     * @return regresa el valor del apodo
     */
    public String getApodo() {
        return apodo;
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
