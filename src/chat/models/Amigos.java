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
public class Amigos {
    private int id_amigos;
    private int estado;
    private String apodo1;
    private String apodo2;
    private int id_usuario1;
    private int id_usuario2;

    public int getId_amigos() {
        return id_amigos;
    }

    public void setId_amigos(int id_amigos) {
        this.id_amigos = id_amigos;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getApodo1() {
        return apodo1;
    }

    public void setApodo1(String apodo1) {
        this.apodo1 = apodo1;
    }

    public String getApodo2() {
        return apodo2;
    }

    public void setApodo2(String apodo2) {
        this.apodo2 = apodo2;
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
