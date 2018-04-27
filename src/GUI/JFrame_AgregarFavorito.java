/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Delegates.MouseClick;
import GUI_Utilities.TextPrompt;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class JFrame_AgregarFavorito extends JFrame implements ActionListener {

    /* Formulario */
    private JLabel LblApodo, LblTitulo, LblEspacio;
    private JTextField TxtApodo;
    private JButton BtnCrear, BtnCancelar;
    
    private MouseClick OnBtnCrearClick, OnBtnCancelarClick;
    
    private String user;

    public JFrame_AgregarFavorito(String user) {
        /*Ventana*/
        super("Favoritos");
        this.user = user;
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage());
        this.setSize(500, 250);
        loadFrameDetails();
    }

    private void loadFrameDetails() {
        TextPrompt placeholder;
        /*Formulario*/
        LblTitulo = new JLabel("Agregar a favoritos");
        LblApodo = new JLabel("Apodo");
        LblEspacio = new JLabel(" ");

        TxtApodo = new JTextField();
        
        placeholder = new TextPrompt(user,TxtApodo);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
                
        BtnCrear = new JButton("Crear");
        BtnCancelar = new JButton("Cancelar");
        
        BtnCrear.addActionListener(this);
        BtnCancelar.addActionListener(this);
        
        /* Cambio de fuente de letra  */
        Font auxFont = LblTitulo.getFont();
        LblTitulo.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 18));

        /*Acomod de componentes*/
        GroupLayout orden = new GroupLayout(this.getContentPane());
        orden.setAutoCreateContainerGaps(true);
        orden.setAutoCreateGaps(true);
        orden.setVerticalGroup(orden.createSequentialGroup()
                        .addComponent(LblTitulo)
                        .addComponent(LblEspacio)
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(LblApodo)
                                        .addComponent(TxtApodo, 25, 25, 25)
                        )
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(BtnCrear)
                                        .addComponent(BtnCancelar)
                        )
        );

        orden.setHorizontalGroup(orden.createParallelGroup()
                        .addComponent(LblTitulo, GroupLayout.Alignment.CENTER)
                        .addGroup(orden.createSequentialGroup()
                                        .addGroup(orden.createParallelGroup()
                                                        .addComponent(LblEspacio)
                                                        .addComponent(LblApodo)
                                        )
                                        .addGroup(orden.createParallelGroup()
                                                        .addComponent(TxtApodo, 200, 200, 200)
                                                        .addGroup(orden.createSequentialGroup()
                                                                        .addComponent(BtnCancelar)
                                                                        .addComponent(BtnCrear)
                                                        )
                                        )
                        )
        );
        this.setLayout(orden);
        this.pack();
    }

    public void setOnBtnCrearClick(MouseClick OnBtnCrearClick) {
        this.OnBtnCrearClick = OnBtnCrearClick;
    }

    public void setOnBtnCancelarClick(MouseClick OnBtnCancelarClick) {
        this.OnBtnCancelarClick = OnBtnCancelarClick;
    }

    public JTextField getTxtApodo() {
        return TxtApodo;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == BtnCrear){
                OnBtnCrearClick.Click();
            }
            if(e.getSource() == BtnCancelar){
                OnBtnCancelarClick.Click();
            }
        } catch (Exception ex) {
        }
    }
    
    
}
