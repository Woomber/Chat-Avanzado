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
public class UsuarioGrupo {
    private int id_grupo;
    private String id_usuario;
    private boolean status;

    public UsuarioGrupo() {
    }

    /**
     * Constructor que recibe los datos del modelo
     * @param id_grupo
     * @param id_usuario
     * @param status 
     */
    public UsuarioGrupo(int id_grupo, String id_usuario, boolean status) {
        this.id_grupo = id_grupo;
        this.id_usuario = id_usuario;
        this.status = status;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }
}
