/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.models;

/**
 * Modelo de usuarios en grupo
 * @author Maritza
 */
public class UsuarioGrupo {
    private int id_grupo;
    private String id_usuario;
    private boolean status;

    public UsuarioGrupo() {
    }

    /**
     * Constructor para inicalizar los valores para comprobar si un usuario está en línea o no
     * @param id_grupo El id del grupo al que pertenece el usuario
     * @param id_usuario El id del usuario
     * @param status  El estado en el que se encuentra el usuario, puede ser True (En línea) o False (Desconectado)
     */
    public UsuarioGrupo(int id_grupo, String id_usuario, boolean status) {
        this.id_grupo = id_grupo;
        this.id_usuario = id_usuario;
        this.status = status;
    }

    /**
     * Obtiene el id del miembro
     * @return Retorna el id obtenido
     */
    public String getId_usuario() {
        return id_usuario;
    }

    /**
     * Establece el id del usuario en el grupo
     * @param id_usuario Id del usuario
     */
    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * Obtiene el estado en el que se encuentra el usuario
     * @return Retorna el estado 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Establece el usuario en el que está el usuario
     * @param status Guarda el estado del usuario
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Obtiene el id del grupo 
     * @return Retorna el id obtenido
     */
    public int getId_grupo() {
        return id_grupo;
    }

    /**
     * Establece el id del grupo al que pertenece
     * @param id_grupo Id el grupo involucrado
     */
    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }
}
