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
    private JLabel ImgUsuario, LblUsuario;
    private JRadioButton RadioButton;

    private boolean isOnline;

    private MouseClick OnInformationClick;
    private MouseEnter OnMouseEnter;
    private MouseLeave OnMouseLeave;

    public JComponent_Usuario(String username, boolean isOnline) {
        /*Ingreso de icono de usuario*/
        this.isOnline = isOnline;
        ImgUsuario = new JLabel();
        ImageIcon usuario;
        if (isOnline) {
            usuario = new ImageIcon(getClass().getResource("../Imagen/usuario_enlinea.png"));
        } else {
            usuario = new ImageIcon(getClass().getResource("../Imagen/usuario.png"));
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

    public void setOnInformationClick(MouseClick OnInformationClick) {
        this.OnInformationClick = OnInformationClick;
    }

    public void setOnMouseEnter(MouseEnter OnMouseEnter) {
        this.OnMouseEnter = OnMouseEnter;
    }

    public void setOnMouseLeave(MouseLeave OnMouseLeave) {
        this.OnMouseLeave = OnMouseLeave;
    }

    public JLabel getLblUsuario() {
        return LblUsuario;
    }

    public JRadioButton getRadioButton() {
        return RadioButton;
    }

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

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == ImgUsuario || e.getSource() == LblUsuario) {
            try {
                OnMouseEnter.Enter();
            } catch (Exception ex) {

            }
        }
    }

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
