/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.models;

/**
 * Modelo de Mensajes vistos
 * @author Maritza
 */
public class MensajeVisto {
    private int id_mensaje;
    private String id_usuario;

    public MensajeVisto() {
    }

    /**
     * Constructor para inicializar valores si el mensaje fue visto
     * @param id_mensaje Identificador del mensaje mandado
     * @param id_usuario Identificador del usuario que vio el mensaje
     */
    public MensajeVisto(int id_mensaje, String id_usuario) {
        this.id_mensaje = id_mensaje;
        this.id_usuario = id_usuario;
    }

    /**
     * Obtiene el id del mensaje mandado
     * @return Retorna el id del mensaje
     */
    public int getId_mensaje() {
        return id_mensaje;
    }

    /**
     * Establece el valor del id del mensaje
     * @param id_mensaje Id del mensaje
     */
    public void setId_mensaje(int id_mensaje) {
        this.id_mensaje = id_mensaje;
    }

    /**
     * Obtiene el valor del id del usuario
     * @return Retorna el id de usuario
     */
    public String getId_usuario() {
        return id_usuario;
    }

    /**
     * Establece el valor del usuario que vio el mensaje
     * @param usuario Id del usuario
     */
    public void setId_usuario(String usuario) {
        this.id_usuario = usuario;
    }
    
    
}
