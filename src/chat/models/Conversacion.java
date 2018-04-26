/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.models;

/**
 *
 * @author Maritza
 */
public class Conversacion {
    private int id_conversacion;
    private int id_usuario1;
    private int id_usuario2;

    public int getId_conversacion() {
        return id_conversacion;
    }

    public void setId_conversacion(int id_conversacion) {
        this.id_conversacion = id_conversacion;
    }

    public int getId_usuario1() {
        return id_usuario1;
    }

    public void setId_usuario1(int id_usuario1) {
        this.id_usuario1 = id_usuario1;
    }

    public int getId_usuario2() {
        return id_usuario2;
    }

    public void setId_usuario2(int id_usuario2) {
        this.id_usuario2 = id_usuario2;
    }
    
    
}
