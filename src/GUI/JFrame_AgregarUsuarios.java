/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import Delegates.MouseClick;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Danae
 */
public class JFrame_AgregarUsuarios extends JFrame implements ActionListener{

    /* Formulario */
    private JLabel LblTitulo, LblEspacio, LblIntegrantes;
    private JButton BtnAgregar, BtnCancelar;
    //private JTextPane TxtPaneIntegrantes;
    private JPanel PanelUsuarios;
    
    private MouseClick OnBtnAgregarClick, OnBtnCancelarClick;

    public JFrame_AgregarUsuarios() {
        super("Agregar usuarios");
        this.setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("../Images/icono.png")).getImage());
        this.setSize(500, 250);
        loadFrameDetails();
    }

    private void loadFrameDetails() {
        /*Formulario*/
        LblTitulo = new JLabel("Agregar Usuarios");
        LblEspacio = new JLabel(" ");
        LblIntegrantes = new JLabel("Contactos");

        PanelUsuarios = new JPanel();

        BtnAgregar = new JButton("Agregar");
        BtnCancelar = new JButton("Cancelar");
        BtnAgregar.addActionListener(this);
        BtnCancelar.addActionListener(this);
        PanelUsuarios.setLayout(new BoxLayout(PanelUsuarios, BoxLayout.Y_AXIS));
        JScrollPane mU = new JScrollPane(PanelUsuarios);
        /*TxtPaneIntegrantes = new JTextPane();

        TxtPaneIntegrantes.setEditable(false);*/
        /* Cambio de fuente de letra  */
        Font auxFont = LblTitulo.getFont();
        LblTitulo.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 18));

        /*for (int i = 0; i < 10; i++) {
            JComponent_Contacto contacto = new JComponent_Contacto("Yo merengues",true);
            contacto.setOnInformationClick(new MouseClick(){
                    @Override
                    public void Click() {
                        new JFrame_Conversacion().setVisible(true);
                    }   
                }
            );
            jpUsuarios.add(contacto);
        }*/
        /*Acomod de componentes*/
        GroupLayout orden = new GroupLayout(this.getContentPane());
        orden.setAutoCreateContainerGaps(true);
        orden.setAutoCreateGaps(true);
        orden.setVerticalGroup(orden.createSequentialGroup()
                        .addComponent(LblTitulo)
                        .addComponent(LblEspacio)
                        .addGroup(
                                orden.createParallelGroup()
                        )
                        .addComponent(LblIntegrantes, 25, 25, 25)
                        .addComponent(mU, 120, 120, 120)
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(BtnAgregar)
                                        .addComponent(BtnCancelar)
                        )
        );

        orden.setHorizontalGroup(orden.createParallelGroup()
                        .addComponent(LblTitulo, GroupLayout.Alignment.CENTER)
                        .addGroup(orden.createSequentialGroup()
                                        .addGroup(orden.createParallelGroup()
                                                        .addComponent(LblEspacio)
                                                        .addComponent(BtnCancelar)
                                        )
                                        .addGroup(orden.createParallelGroup()
                                                        .addComponent(BtnAgregar)
                                        )
                        )
                        .addComponent(LblIntegrantes)
                        .addComponent(mU, GroupLayout.Alignment.CENTER, 340, 340, 340)
        );
        this.setLayout(orden);
        this.pack();
    }

    public void setOnBtnAgregarClick(MouseClick OnBtnAgregarClick) {
        this.OnBtnAgregarClick = OnBtnAgregarClick;
    }

    public void setOnBtnCancelarClick(MouseClick OnBtnCancelarClick) {
        this.OnBtnCancelarClick = OnBtnCancelarClick;
    }

    public JPanel getPanelUsuarios() {
        return PanelUsuarios;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == BtnAgregar){
                OnBtnAgregarClick.Click();
            }
            if(e.getSource() == BtnCancelar){
                OnBtnCancelarClick.Click();
            }
        } catch (Exception ex) {
        }
    }
    
    
}
