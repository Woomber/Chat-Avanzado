/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Delegates.MouseClick;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.paint.Color;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Danae
 */
public class JFrame_Ingreso extends JFrame implements ActionListener{
    /* Ventana */
    private final JMenuBar MenuBarra = new JMenuBar();
    private final JButton MenuRegistro = new JButton("Ir a Registro");
    
    /* Formulario */
    private JLabel LblNombre, LblContrasena, LblTitulo, LblEspacio;
    private JTextField TxtNombre;
    private JPasswordField TxtContrasena;
    private JButton BtnAceptar; 
    
    MouseClick OnMenuRegistroClick, OnBtnAceptarClick; 

    public JFrame_Ingreso(){
        /*Ventana*/
        super("Ingreso"); 
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,250);
        loadFrameDetails();
    }
    
    private void loadFrameDetails(){
        MenuRegistro.setOpaque(false);
        MenuRegistro.setContentAreaFilled(false);
        MenuRegistro.setBorderPainted(false);
        MenuRegistro.setMargin(new Insets(0,5,0,0));
        /* Menu */
        MenuRegistro.addActionListener(this);
        MenuBarra.add(MenuRegistro);
        this.setJMenuBar(MenuBarra);
        
        /*Formulario*/
        LblTitulo = new JLabel ("Ingreso de usuario");
        LblNombre = new JLabel("Usuario");
        LblContrasena = new JLabel ("Contraseña");
        LblEspacio = new JLabel (" ");
        
        TxtNombre = new JTextField();
        TxtContrasena = new JPasswordField();
        BtnAceptar = new JButton ("Ingresar");
        BtnAceptar.addActionListener(this);
        /* Cambio de fuente de letra  */
        Font auxFont =LblTitulo.getFont();
        LblTitulo.setFont(new Font (auxFont.getFontName(),auxFont.getStyle(),18));

       /*Acomod de componentes*/
       GroupLayout orden = new GroupLayout(this.getContentPane());
       orden.setAutoCreateContainerGaps(true);
       orden.setAutoCreateGaps(true);
       orden.setVerticalGroup
            (orden.createSequentialGroup()
                    .addComponent(LblTitulo)
                      .addComponent(LblEspacio)
                    .addGroup
                        (orden.createParallelGroup()
                                    .addComponent(LblNombre)
                                    .addComponent(TxtNombre,25,25,25)
                        )
                    .addGroup
                        (orden.createParallelGroup()
                                    .addComponent(LblContrasena)
                                    .addComponent(TxtContrasena,25,25,25)
                        )
                       .addComponent(BtnAceptar)
            );
       
       orden.setHorizontalGroup
            (orden.createParallelGroup()
                            .addComponent(LblTitulo, GroupLayout.Alignment.CENTER)
                    .addGroup
                        (orden.createSequentialGroup()
                                    .addGroup
                                        (orden.createParallelGroup()
                                                    
                                                    .addComponent(LblEspacio)
                                                    .addComponent(LblNombre)
                                                    .addComponent(LblContrasena)                                              
                                        )
                                    .addGroup
                                        (orden.createParallelGroup()
                                                    .addComponent(TxtNombre)
                                                    .addComponent(TxtContrasena)
                                                .addComponent(BtnAceptar, 250, 250, 250)
                                        )
                        )
            );
       
       this.setLayout(orden);
       this.pack();
       
    }

    public void setOnMenuRegistroClick(MouseClick OnMenuRegistroClick) {
        this.OnMenuRegistroClick = OnMenuRegistroClick;
    }

    public void setOnBtnAceptarClick(MouseClick OnBtnAceptarClick) {
        this.OnBtnAceptarClick = OnBtnAceptarClick;
    }

    public JTextField getTxtNombre() {
        return TxtNombre;
    }

    public JPasswordField getTxtContrasena() {
        return TxtContrasena;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == BtnAceptar){
                OnBtnAceptarClick.Click();
            }
            if(e.getSource() == MenuRegistro){
                OnMenuRegistroClick.Click();
            }
        } catch (Exception ex) {
        }
    }
    
    
}
