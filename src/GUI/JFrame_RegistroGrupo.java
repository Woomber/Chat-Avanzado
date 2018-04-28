/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Delegates.MouseClick;
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
public class JFrame_RegistroGrupo extends JFrame implements ActionListener{

    /* Formulario */
    private JLabel LblGrupo, LblTitulo, Lblespacio, Lblintegrantes;
    private JTextField TxtGrupo;
    private JButton BtnCrear, BtnCancelar;
    private JTextPane PanelIntegrantes;
    private JScrollPane scrollpane;
    
    private MouseClick OnBtnCrearClick, OnBtnCancelarClick;

    public JFrame_RegistroGrupo() {
        /*Ventana*/
        super("Crear un grupo");
        this.setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage());
        this.setSize(500, 250);
        loadFrameDetails();
    }

    private void loadFrameDetails() {
        /*Formulario*/
        LblTitulo = new JLabel("Crear un nuevo grupo");
        LblGrupo = new JLabel("Nombre del grupo");
        Lblespacio = new JLabel(" ");
        Lblintegrantes = new JLabel("Integrantes seleccionados");

        TxtGrupo = new JTextField();
        BtnCrear = new JButton("Crear");
        BtnCancelar = new JButton("Cancelar");
        BtnCrear.addActionListener(this);
        BtnCancelar.addActionListener(this);
        
        PanelIntegrantes = new JTextPane();
        JScrollPane mC = new JScrollPane(PanelIntegrantes);

        PanelIntegrantes.setEditable(false);
        /* Cambio de fuente de letra  */
        Font auxFont = LblTitulo.getFont();
        LblTitulo.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 18));

        /*Acomod de componentes*/
        GroupLayout orden = new GroupLayout(this.getContentPane());
        orden.setAutoCreateContainerGaps(true);
        orden.setAutoCreateGaps(true);
        orden.setVerticalGroup(
                orden.createSequentialGroup()
                        .addComponent(LblTitulo)
                        .addComponent(Lblespacio)
                        .addGroup(
                                orden.createParallelGroup()
                                        .addComponent(LblGrupo)
                                        .addComponent(TxtGrupo, 25, 25, 25)
                        )
                        .addComponent(Lblintegrantes, 25, 25, 25)
                        .addComponent(mC, 50, 50, 50)
                        .addGroup(
                                orden.createParallelGroup()
                                        .addComponent(BtnCrear)
                                        .addComponent(BtnCancelar)
                        )
        );

        orden.setHorizontalGroup(
                orden.createParallelGroup()
                        .addComponent(LblTitulo, GroupLayout.Alignment.CENTER)
                        .addGroup(
                                orden.createSequentialGroup()
                                        .addGroup(
                                                orden.createParallelGroup()
                                                        .addComponent(Lblespacio)
                                                        .addComponent(LblGrupo)
                                                        .addComponent(BtnCancelar)
                                        )
                                        .addGroup(
                                                orden.createParallelGroup()
                                                        .addComponent(TxtGrupo, 200, 200, 200)
                                                        .addComponent(BtnCrear)
                                        )
                        )
                        .addComponent(Lblintegrantes)
                        .addComponent(mC, GroupLayout.Alignment.CENTER, 310, 310, 310)
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

    public JTextField getTxtGrupo() {
        return TxtGrupo;
    }

    public JTextPane getPanelIntegrantes() {
        return PanelIntegrantes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == BtnCrear){
                OnBtnCrearClick.Click();
            }
            if(e.getSource() == BtnCancelar){
                OnBtnCancelarClick.Click();;
            }
        } catch (Exception ex) {
            
        }
    }
    
    
}
