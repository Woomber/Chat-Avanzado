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
        
        Ingreso ventana = new Ingreso();
        Registro ventana1 = new Registro();
        Conversacion ventana2= new Conversacion();
        RegistroGrupo ventana3=new RegistroGrupo();
        Pagina_principal principal = new Pagina_principal();
        RegistroFavorito ventana4= new RegistroFavorito();
        AgregarUsuarios ventana5 = new AgregarUsuarios();
        
        Principal_usuario muestrausuario = new Principal_usuario();
        Principal_grupo muestragrupo = new Principal_grupo();
        Principal_favorito muestrafavorito = new Principal_favorito();
       
        muestrafavorito.setVisible(true);
        muestragrupo.setVisible(true);
        muestrausuario.setVisible(true);
        
        ventana5.setVisible(true);
       ventana4.setVisible(true);
        ventana3.setVisible(true);
        ventana2.setVisible(true);
        ventana1.setVisible(true);
        ventana.setVisible(true);
         principal.setVisible(true);
    }
    
}
