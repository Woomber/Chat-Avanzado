/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.models;

/**
 * Modelo de Grupos
 * @author Maritza
 */
public class Grupo {
    private int id_grupo;
    private String nombre_grupo;

    public Grupo() {
    }
    
    /**
     * Constructor para inicializar los parámetros en Grupo, de Id_grupo y Nombre_grupo
     * @param id_grupo Identificador del usuario
     * @param nombre_grupo El nombre del grupo
     */
    public Grupo(int id_grupo, String nombre_grupo) {
        this.id_grupo = id_grupo;
        this.nombre_grupo = nombre_grupo;
    }

    /**
     * Obtiene el id del grupo
     * @return Retorna el id del grupo
     */
    public int getId_grupo() {
        return id_grupo;
    }

    /**
     * Establece el valor del Id_grupo
     * @param id_grupo  Id del grupo
     */
    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    /**
     * Obtiene el nombre del grupo
     * @return Retorna el Nombre del grupo
     */
    public String getNombre_grupo() {
        return nombre_grupo;
    }

    /**
     * Establece el nombre del grupo
     * @param nombre_grupo Nombre del grupo
     */
    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }
    
    
}
