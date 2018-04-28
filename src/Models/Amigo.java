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
public class Amigo {
            
            
    private String id_usuario;
    private String amigo;
    private String apodo;

    public Amigo(String id_usuario, String amigo, String apodo) {
        this.id_usuario = id_usuario;
        this.amigo = amigo;
        this.apodo = apodo;
    }

    public Amigo() {
    }
    
    public String getUsuario() {
        return id_usuario;
    }

    public void setUsuario(String usuario) {
        this.id_usuario = usuario;
    }

    public String getAmigo() {
        return amigo;
    }

    public void setAmigo(String amigo) {
        this.amigo = amigo;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }
    
    
    
}
