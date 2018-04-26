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
public class Mensaje_grupal {
    private int id_mensaje_grupal;
    private Date fecha;
    private String texto;
    private int visto;
    private int id_usuario;
    private int id_grupo;

    public int getId_mensaje_grupal() {
        return id_mensaje_grupal;
    }

    public void setId_mensaje_grupal(int id_mensaje_grupal) {
        this.id_mensaje_grupal = id_mensaje_grupal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getVisto() {
        return visto;
    }

    public void setVisto(int visto) {
        this.visto = visto;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }
    
    
}
