/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_coco;

import Delegates.MouseClick;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Danae
 */
public class JFrame_Registro extends JFrame implements ActionListener{

    /* componentes */
    private final JMenuBar MenuBar = new JMenuBar();
    private final JMenu MenuIngreso = new JMenu("Ir a Ingreso");

    /* Formulario */
    private JLabel LblNombre, LblContrasena, LblPrueba, LblTitulo, LblEspacio, LblUsuario;
    private JTextField TxtNombre, TxtUsuario;
    private JPasswordField TxtContrasena, TxtContrasenaVerify;
    private JButton BtnRegistro;
    
    private MouseClick OnMenuIngresoClick, OnBtnRegistroClick;

    public JFrame_Registro() {
        /*Ventana*/
        super("Registro");
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage());/*icono*/
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 250);

        loadFrameDetails();
    }

    private void loadFrameDetails() {
        /* Menu */
        MenuIngreso.addActionListener(this);
        MenuBar.add(MenuIngreso);
        this.setJMenuBar(MenuBar);


        /* Formulario */
        LblTitulo = new JLabel("Registro de usuario");
        LblNombre = new JLabel("Nombre");
        LblContrasena = new JLabel("Contraseña");
        LblPrueba = new JLabel("Verificacion de contraseña");
        LblEspacio = new JLabel(" ");
        LblUsuario = new JLabel("Usuario");

        TxtNombre = new JTextField();
        TxtUsuario = new JTextField();
        TxtContrasena = new JPasswordField();
        TxtContrasenaVerify = new JPasswordField();
        BtnRegistro = new JButton("Registrarme");
        BtnRegistro.addActionListener(this);
        /* Cambio de fuente de letra  */
        Font auxFont = LblTitulo.getFont();/*obtener tipo de fuente usada actual*/
        LblTitulo.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 18));

        /*acomodo de componentes*/
        GroupLayout orden = new GroupLayout(this.getContentPane());
        orden.setAutoCreateContainerGaps(true);
        orden.setAutoCreateGaps(true);
        orden.setVerticalGroup(orden.createSequentialGroup()
                        .addComponent(LblTitulo)
                        .addComponent(LblEspacio)
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(LblNombre)
                                        .addComponent(TxtNombre, 25, 25, 25)
                        )
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(LblUsuario)
                                        .addComponent(TxtUsuario, 25, 25, 25)
                        )
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(LblContrasena)
                                        .addComponent(TxtContrasena, 25, 25, 25)
                        )
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(LblPrueba)
                                        .addComponent(TxtContrasenaVerify, 25, 25, 25)
                        )
                        .addComponent(BtnRegistro)
        );

        orden.setHorizontalGroup(orden.createParallelGroup()
                        .addComponent(LblTitulo, GroupLayout.Alignment.CENTER)
                        .addGroup(orden.createSequentialGroup()
                                        .addGroup(orden.createParallelGroup()
                                                        .addComponent(LblEspacio)
                                                        .addComponent(LblNombre)
                                                        .addComponent(LblUsuario)
                                                        .addComponent(LblContrasena)
                                                        .addComponent(LblPrueba)
                                        )
                                        .addGroup(
                                                orden.createParallelGroup()
                                                        .addComponent(TxtNombre)
                                                        .addComponent(TxtUsuario)
                                                        .addComponent(TxtContrasena)
                                                        .addComponent(TxtContrasenaVerify)
                                                        .addComponent(BtnRegistro, 250, 250, 250)
                                        )
                        )
        );

        this.setLayout(orden);
        this.pack();

    }

    public void setOnMenuIngresoClick(MouseClick OnMenuIngresoClick) {
        this.OnMenuIngresoClick = OnMenuIngresoClick;
    }

    public void setOnBtnRegistroClick(MouseClick OnBtnRegistroClick) {
        this.OnBtnRegistroClick = OnBtnRegistroClick;
    }

    public JTextField getTxtNombre() {
        return TxtNombre;
    }

    public JTextField getTxtUsuario() {
        return TxtUsuario;
    }

    public JPasswordField getTxtContrasena() {
        return TxtContrasena;
    }

    public JPasswordField getTxtContrasenaVerify() {
        return TxtContrasenaVerify;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == MenuIngreso){
                OnMenuIngresoClick.Click();
            }
            if(e.getSource() == BtnRegistro){
                OnBtnRegistroClick.Click();
            }
        } catch (Exception ex) {
        }
    }
    
    
    
}
