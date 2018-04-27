/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_coco;

/**
 *
 * @author Danae
 */
public class Proyecto_coco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        JFrame_Ingreso ingreso = new JFrame_Ingreso();
        JFrame_Registro registro = new JFrame_Registro();
        JFrame_Conversacion grupo = new JFrame_Conversacion("Grupo",true);
         JFrame_Conversacion individual = new JFrame_Conversacion("Yo meros",false);
        JFrame_RegistroGrupo registrogrupo = new JFrame_RegistroGrupo();
        JFrame_Principal pagina_principal = new JFrame_Principal();
        JFrame_AgregarFavorito registrofavorito = new JFrame_AgregarFavorito("Yo meros");
        JFrame_AgregarUsuarios agregarusuarios = new JFrame_AgregarUsuarios();

        //JComponent_Contacto muestrausuario = new JComponent_Contacto();
        //JComponent_Grupo muestragrupo = new JComponent_Grupo();
        //JComponent_Favorito muestrafavorito = new JComponent_Favorito();

        //muestrafavorito.setVisible(true);
        //muestragrupo.setVisible(true);
        //muestrausuario.setVisible(true);

        //agregarusuarios.setVisible(true);
        //registrofavorito.setVisible(true);
        registrogrupo.setVisible(true);
        //grupo.setVisible(true);
        //individual.setVisible(true);
        //registro.setVisible(true);
        //ingreso.setVisible(true);
        //pagina_principal.setVisible(true);
    }

}
