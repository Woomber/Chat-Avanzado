/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Maritza
 */
public class MensajeVisto {
    private int id_mensaje;
    private String id_usuario;

    public MensajeVisto() {
    }

    /**
     * Constructor que recibe los parametros del modelo
     * @param id_mensaje
     * @param id_usuario 
     */
    public MensajeVisto(int id_mensaje, String id_usuario) {
        this.id_mensaje = id_mensaje;
        this.id_usuario = id_usuario;
    }

    public int getId_mensaje() {
        return id_mensaje;
    }

    public void setId_mensaje(int id_mensaje) {
        this.id_mensaje = id_mensaje;
    }

    public String getUsuario() {
        return id_usuario;
    }

    public void setUsuario(String usuario) {
        this.id_usuario = usuario;
    }
    
    
}
