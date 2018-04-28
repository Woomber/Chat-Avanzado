/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Delegates.MouseClick;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author Danae
 */
public class JFrame_Principal extends JFrame implements ActionListener{

    /* componentes */
    private final JMenuBar MenuBar = new JMenuBar();
    private final JButton MenuSalir = new JButton("Salir");

    /* Formulario */
    private JLabel LblUsuarios, LblFavoritos, LblGrupos, LblEspacio;
    private JButton BtnFavoritos, BtnGrupos;
    private JPanel PanelUsuarios, PanelFavoritos, PanelGrupos;
    
    private MouseClick OnBtnFavoritosClick, OnBtnGruposClick, OnMenuSalirClick;

    public JFrame_Principal() {
        /*Ventana*/
        super("Pagina principal");
        this.setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage());/*icono*/
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadFrameDetails();
    }

    private void loadFrameDetails() {
        MenuSalir.setOpaque(false);
        MenuSalir.setContentAreaFilled(false);
        MenuSalir.setBorderPainted(false);
        MenuSalir.setMargin(new Insets(0,5,0,0));
        /* Menu */
        MenuSalir.addActionListener(this);
        MenuBar.add(MenuSalir);
        this.setJMenuBar(MenuBar);

        LblUsuarios = new JLabel("Contactos");
        LblFavoritos = new JLabel("Favoritos");
        LblGrupos = new JLabel("Grupos");
        LblEspacio = new JLabel(" ");

        BtnFavoritos = new JButton();
        ImageIcon favorito = new ImageIcon(getClass().getResource("../Imagen/iconoF.png"));
        ImageIcon iconofavorito = new ImageIcon(favorito.getImage());
        BtnFavoritos.setIcon(iconofavorito);

        BtnGrupos = new JButton();
        ImageIcon grupo = new ImageIcon(getClass().getResource("../Imagen/iconoG.png"));
        ImageIcon iconogrupo = new ImageIcon(grupo.getImage());
        BtnGrupos.setIcon(iconogrupo);
        BtnFavoritos.addActionListener(this);
        BtnGrupos.addActionListener(this);
        /*
        jtpUsuarios =new JTextPane();
        jtpFavoritos = new JTextPane();
        jtpGrupos= new JTextPane();*/
        PanelUsuarios = new JPanel();
        PanelFavoritos = new JPanel();
        PanelGrupos = new JPanel();

        PanelUsuarios.setLayout(new BoxLayout(PanelUsuarios, BoxLayout.Y_AXIS));
        PanelFavoritos.setLayout(new BoxLayout(PanelFavoritos, BoxLayout.Y_AXIS));
        PanelGrupos.setLayout(new BoxLayout(PanelGrupos, BoxLayout.Y_AXIS));
        JScrollPane mU = new JScrollPane(PanelUsuarios);
        JScrollPane mF = new JScrollPane(PanelFavoritos);
        JScrollPane mG = new JScrollPane(PanelGrupos);
        /*Jframes*/

        /*for (int i = 0; i < 10; i++) {
            jtpUsuarios.add(new JComponent_Contacto("",false));
            jtpFavoritos.add(new JComponent_Favorito());
            jtpGrupos.add(new JComponent_Grupo());
        }*/

        /*acomodo de componentes*/
        GroupLayout orden = new GroupLayout(this.getContentPane());
        orden.setAutoCreateContainerGaps(true);
        orden.setAutoCreateGaps(true);
        orden.setVerticalGroup(orden.createSequentialGroup()
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(LblUsuarios, 30, 30, 30)
                                        .addComponent(LblEspacio)
                                        .addComponent(BtnFavoritos, 26, 26, 26)
                                        .addComponent(BtnGrupos, 26, 26, 26)
                        )
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(mU, 350, 350, 350)
                                        .addGroup(orden.createSequentialGroup()
                                                        .addComponent(LblFavoritos, 30, 30, 30)
                                                        .addComponent(mF, 135, 135, 135)
                                                        .addComponent(LblGrupos, 30, 30, 30)
                                                        .addComponent(mG, 135, 135, 135)
                                        )
                        )
        );

        orden.setHorizontalGroup(orden.createSequentialGroup()
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(LblUsuarios, 340, 340, 340)
                                        .addComponent(mU)
                        )
                        .addGroup(orden.createParallelGroup()
                                        .addComponent(LblFavoritos)
                                        .addGroup(orden.createSequentialGroup()
                                                        .addComponent(LblEspacio, 238, 238, 238)
                                                        .addComponent(BtnFavoritos, 26, 26, 26)
                                                        .addComponent(BtnGrupos, 35, 35, 35)
                                        )
                                        .addComponent(mF, 340, 340, 340)
                                        .addComponent(LblGrupos)
                                        .addComponent(mG)
                        )
        );

        this.setLayout(orden);
        this.pack();
    }

    public void setOnBtnFavoritosClick(MouseClick OnBtnFavoritosClick) {
        this.OnBtnFavoritosClick = OnBtnFavoritosClick;
    }

    public void setOnBtnGruposClick(MouseClick OnBtnGruposClick) {
        this.OnBtnGruposClick = OnBtnGruposClick;
    }

    public void setOnMenuSalirClick(MouseClick OnMenuSalirClick) {
        this.OnMenuSalirClick = OnMenuSalirClick;
    }

    public JPanel getPanelUsuarios() {
        return PanelUsuarios;
    }

    public JPanel getPanelFavoritos() {
        return PanelFavoritos;
    }

    public JPanel getPanelGrupos() {
        return PanelGrupos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == BtnFavoritos){
                OnBtnFavoritosClick.Click();
            }
            if(e.getSource() == BtnGrupos){
                OnBtnGruposClick.Click();
            }
            if(e.getSource() == MenuSalir){
                OnMenuSalirClick.Click();
            }
        } catch (Exception ex) {
        }
    }
    
    
}
