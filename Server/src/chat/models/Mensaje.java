/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.models;


/**
 * Modelo de Mensajes en grupo
 * @author Maritza
 */
public class Mensaje {
    private int id_mensaje_grupal; 
    private String id_usuario;
    private String texto;
    private int id_grupo;

    public Mensaje() {
    }

    /**
     * Constructor para inicializar los valores en Mensaje, con id de mensaje, usuario, grupo y el texto
     * @param id_mensaje_grupal Identificador del mensaje grupal
     * @param id_usuario Identificador del usuario que mando el mensaje
     * @param texto El texto que tiene el mensaje
     * @param id_grupo Identificador del grupo en donde se mandó
     */
    public Mensaje(int id_mensaje_grupal, String id_usuario, String texto, int id_grupo) {
        this.id_mensaje_grupal = id_mensaje_grupal;
        this.id_usuario = id_usuario;
        this.texto = texto;
        this.id_grupo = id_grupo;
    }
    
    /**
     * Obtiene el id del usuario que manda el mensaje
     * @return Retorna el id del usuario
     */
    public String getId_usuario() {
        return id_usuario;
    }

    /**
     * Establece el valor de id_usuario
     * @param usuario Usuario remitente
     */
    public void setId_usuario(String usuario) {
        this.id_usuario = usuario;
    }

    /**
     * Obitne el id del mensaje que se envio en el grupo
     * @return Retorna el id del mensaje
     */
    public int getId_mensaje_grupal() {
        return id_mensaje_grupal;
    }

    /**
     * Establece el id del mensaje grupal
     * @param id_mensaje_grupal Id del mensaje
     */
    public void setId_mensaje_grupal(int id_mensaje_grupal) {
        this.id_mensaje_grupal = id_mensaje_grupal;
    }

    /**
     * Obtiene el texto que contiene el mensaje
     * @return Retorna el texto del mensaje
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Establece el contenido del texto en la variable
     * @param texto Contenido del mensaje
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * Obtiene el id del grupo en donde se mandó el mensaje
     * @return Retorna el id del grupo
     */
    public int getId_grupo() {
        return id_grupo;
    }

    /**
     * Establece el valor del identificador del grupo
     * @param id_grupo Identificador del grupo
     */
    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }
    
    
}
