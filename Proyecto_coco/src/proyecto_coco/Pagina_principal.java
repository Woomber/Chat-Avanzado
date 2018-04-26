/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_coco;


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
public class Pagina_principal extends JFrame{
    /* componentes */
    private final JMenuBar barraMenu = new JMenuBar();
    private final JMenu mnuIngreso = new JMenu("Salir");

    /* Formulario */  
    private JLabel lblUsuarios,lblFavoritos, lblGrupos, lblespacio;
    private JButton jbFavoritos,jbGrupos;   
     private JPanel jtpUsuarios, jtpFavoritos,jtpGrupos;
    
    public Pagina_principal(){
        /*Ventana*/
        super("Pagina principal"); 
        setIconImage(new ImageIcon(getClass().getResource("../Imagen/icono.png")).getImage());/*icono*/
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /* Menu */
        barraMenu.add(mnuIngreso);
        this.setJMenuBar(barraMenu);
        

        lblUsuarios = new JLabel("Contactos");
        lblFavoritos = new JLabel ("Favoritos");
        lblGrupos= new JLabel ("Grupos");
        lblespacio = new JLabel (" ");
        
        jbFavoritos = new JButton ();
        ImageIcon favorito = new ImageIcon(getClass().getResource("../Imagen/iconoF.png"));
        ImageIcon iconofavorito= new ImageIcon( favorito.getImage());
        jbFavoritos.setIcon(iconofavorito);
        
        jbGrupos = new JButton();
        ImageIcon grupo = new ImageIcon(getClass().getResource("../Imagen/iconoG.png"));
        ImageIcon iconogrupo= new ImageIcon( grupo.getImage());
        jbGrupos.setIcon(iconogrupo);
        
        
        
        /*
        jtpUsuarios =new JTextPane();
        jtpFavoritos = new JTextPane();
        jtpGrupos= new JTextPane();*/
        jtpUsuarios = new JPanel();
        jtpFavoritos= new JPanel();
        jtpGrupos=new JPanel();
        
        jtpUsuarios.setLayout(new BoxLayout(jtpUsuarios,BoxLayout.Y_AXIS));
        jtpFavoritos.setLayout(new BoxLayout(jtpFavoritos,BoxLayout.Y_AXIS));
        jtpGrupos.setLayout(new BoxLayout(jtpGrupos,BoxLayout.Y_AXIS));
        JScrollPane mU = new JScrollPane(jtpUsuarios);
        JScrollPane mF = new JScrollPane(jtpFavoritos);
        JScrollPane mG = new JScrollPane(jtpGrupos);
        /*Jframes*/
        
        for(int i=0; i<10;i++){
            jtpUsuarios.add(new Principal_usuario());
            jtpFavoritos.add(new Principal_favorito());
            jtpGrupos.add(new Principal_grupo());
        }
  
        
       /*acomodo de componentes*/
       GroupLayout orden = new GroupLayout(this.getContentPane());
       orden.setAutoCreateContainerGaps(true);
       orden.setAutoCreateGaps(true);
       orden.setVerticalGroup
            (
               orden.createSequentialGroup()
                    .addGroup(
                            orden.createParallelGroup()
                                    .addComponent(lblUsuarios,30,30,30)
                                    .addComponent(lblespacio)
                                    .addComponent(jbFavoritos,26,26,26)
                                    .addComponent(jbGrupos,26,26,26)
                    )
                    
                    .addGroup(
                                orden.createParallelGroup()
                                    .addComponent(mU,350,350,350)
                                    .addGroup( 
                                            orden.createSequentialGroup()
                                                    .addComponent(lblFavoritos,30,30,30)
                                                    .addComponent(mF,135,135,135)
                                                    .addComponent(lblGrupos,30,30,30)
                                                    .addComponent(mG,135,135,135)
                                    )
                    )
            );
       
       orden.setHorizontalGroup
            (
                    orden.createSequentialGroup()
                    .addGroup(
                                orden.createParallelGroup()
                                     .addComponent(lblUsuarios,340,340,340)
                                     .addComponent(mU)  
                      )
                    .addGroup(
                            orden.createParallelGroup()
                                .addComponent(lblFavoritos)
                                    .addGroup(
                                        orden.createSequentialGroup()
                                        .addComponent(lblespacio,238,238,238)
                                        .addComponent(jbFavoritos,26,26,26)
                                        .addComponent(jbGrupos,35,35,35)
                                )
                                .addComponent(mF,340,340,340)
                                .addComponent(lblGrupos)
                                .addComponent(mG)
                         )
            );
       
       this.setLayout(orden);
       this.pack();
       
    }
}
