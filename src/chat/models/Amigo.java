/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.models;

/**
 * Modelo de Amigos
 * @author Maritza
 */
public class Amigo {
    private String id_usuario;
    private String amigo;
    private String apodo;

    /**
     * Constructor que inicializa los parámetros de id_usuario, amigo y apodo
     * @param id_usuario Identificador del usuario
     * @param amigo Identifica al amigo
     * @param apodo El apodo que recibe el amigo
     */
    public Amigo(String id_usuario, String amigo, String apodo) { 
        this.id_usuario = id_usuario;
        this.amigo = amigo;
        this.apodo = apodo;
    }

    public Amigo() {
    }
    
    /**
     * Obtiene el id del usuario 
     * @return Retorna id del usuario
     */
    public String getId_usuario() { 
        return id_usuario;
    }
    
    /**
     * Le da el valor a id_usuario
     * @param usuario Id del usuario
     */
    public void setId_usuario(String usuario) {
        this.id_usuario = usuario;
    }

    /**
     * Obtiene el nombre del Amigo
     * @return Retorna nombre Amigo
     */
    public String getAmigo() {
        return amigo;
    }

    /**
     * Da el valor a amigo
     * @param amigo Nombre del amigo
     */
    public void setAmigo(String amigo) {
        this.amigo = amigo;
    }

    /**
     * Obtiene el Apodo establecido
     * @return Retorna el apodo
     */
    public String getApodo() {
        return apodo;
    }

    /**
     * Da el valor obtenido a Apodo
     * @param apodo Apodo del amigo
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }
    
    
    
}
