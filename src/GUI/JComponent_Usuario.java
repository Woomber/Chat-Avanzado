/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import Delegates.MouseClick;
import Delegates.MouseEnter;
import Delegates.MouseLeave;

/**
 *
 * @author co2
 */
public class JComponent_Usuario extends JComponent implements MouseListener {

    /* Componentes*/
    public String username;
    private JLabel ImgUsuario, LblUsuario;
    private JRadioButton RadioButton;

    private boolean isOnline;

    private MouseClick OnInformationClick;
    private MouseEnter OnMouseEnter;
    private MouseLeave OnMouseLeave;

    
    /**
     * Funcion encargada de recibir los parametros necesarios y los inicializa
     * @param username nombre de usuario
     * @param isOnline si el usuario esta en linea
     * @param username1 
     */
    public JComponent_Usuario(String username, boolean isOnline, String username1) {
        /*Ingreso de icono de usuario*/
        this.isOnline = isOnline;
        this.username = username1;
        ImgUsuario = new JLabel();
        ImageIcon usuario;
        if (isOnline) {
            usuario = new ImageIcon(getClass().getResource("/Images/usuario_enlinea.png"));
        } else {
            usuario = new ImageIcon(getClass().getResource("/Images/usuario.png"));
        }
        ImageIcon icono = new ImageIcon(usuario.getImage());
        ImgUsuario.setIcon(icono);
        LblUsuario = new JLabel(username);
        ImgUsuario.addMouseListener(this);
        LblUsuario.addMouseListener(this);
        RadioButton = new JRadioButton();

        loadComponentDetails();
        //this.pack();   
    }

    /**
     * Crea el componente de usuario
     */
    private void loadComponentDetails() {
        /* panel de mensaje */
        GroupLayout orden = new GroupLayout(this);
        orden.setAutoCreateContainerGaps(true);
        orden.setAutoCreateGaps(true);
        orden.setVerticalGroup(orden.createSequentialGroup()
                .addGroup(orden.createParallelGroup()
                        .addComponent(ImgUsuario)
                        .addComponent(LblUsuario, GroupLayout.Alignment.CENTER)
                        .addComponent(RadioButton, GroupLayout.Alignment.CENTER)
                )
        );

        orden.setHorizontalGroup(orden.createParallelGroup()
                .addGroup(orden.createSequentialGroup()
                        .addComponent(ImgUsuario, 40, 40, 40)
                        .addComponent(LblUsuario, 200, 200, 200)
                        .addComponent(RadioButton, 40, 40, 40)
                )
        );
        this.setLayout(orden);
    }

    /**
     * se le esta dando funcionalidad al click
     * @param OnInformationClick recibe el evento del clic
     */
    public void setOnInformationClick(MouseClick OnInformationClick) {
        this.OnInformationClick = OnInformationClick;
    }

    /**
     * 
     * @param OnMouseEnter cuando el mouse entra al copmponente
     */
    public void setOnMouseEnter(MouseEnter OnMouseEnter) {
        this.OnMouseEnter = OnMouseEnter;
    }

    /**
     * 
     * @param OnMouseLeave recibe la informacion si el mouse ssale del componente
     */
    public void setOnMouseLeave(MouseLeave OnMouseLeave) {
        this.OnMouseLeave = OnMouseLeave;
    }

    /**
     * Obtiene el valor de usuario
     * @return valor de usuario
     */
    public JLabel getLblUsuario() {
        return LblUsuario;
    }

    /**
     * obtiene el valor de del radiobutton
     * @return regresa el 
     */
    public JRadioButton getRadioButton() {
        return RadioButton;
    }
    
    

    public String getUsername() {
        return username;
    }

      /**
     * Permite entrar a la conversacion ya sea presionando la imagen o el texto
     * @param e evento que fue accionado 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == ImgUsuario || e.getSource() == LblUsuario) {
            try {
                OnInformationClick.Click();
            } catch (Exception ex) {

            }
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
        if (e.getSource() == ImgUsuario || e.getSource() == LblUsuario) {
            try {
                OnMouseEnter.Enter();
            } catch (Exception ex) {

            }
        }
    }

     /**
     * detecta que salio del un componente
     * @param e evento accionado 
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == ImgUsuario || e.getSource() == LblUsuario) {
            try {
                OnMouseLeave.Leave();
            } catch (Exception ex) {

            }
        }
    }
}
