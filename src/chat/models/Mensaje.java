/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.models;

import java.sql.Date;

/**
 *
 * @author Maritza
 */
public class Mensaje {
    private int id_mensaje_grupal; 
    private String id_usuario;
    private String texto;
    private int id_grupo;

    public Mensaje() {
    }

    public Mensaje(int id_mensaje_grupal, String id_usuario, String texto, int id_grupo) {
        this.id_mensaje_grupal = id_mensaje_grupal;
        this.id_usuario = id_usuario;
        this.texto = texto;
        this.id_grupo = id_grupo;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String usuario) {
        this.id_usuario = usuario;
    }

    public int getId_mensaje_grupal() {
        return id_mensaje_grupal;
    }

    public void setId_mensaje_grupal(int id_mensaje_grupal) {
        this.id_mensaje_grupal = id_mensaje_grupal;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }
    
    
}
