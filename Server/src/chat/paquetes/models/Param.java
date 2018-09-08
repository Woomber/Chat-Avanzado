package chat.paquetes.models;

import java.io.Serializable;

/**
 * Clase que contiene un par nombre-valor para el envío de paquetes
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class Param implements Serializable {
    private String nombre;
    private String valor;
    
    public Param(){
        
    }
    
    /**
     * Iniciaiza variables que se mandan para enviar paquetes
     * @param nombre El nombre del paquete
     * @param valor  El valor del paquete
     */
    public Param(String nombre, String valor){
        this.nombre = nombre;
        this.valor = valor;
    }

    /**
     * Obtiene el nombre
     * @return Retorna el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el valor
     * @return Retorna el valor
     */
    public String getValor() {
        return valor;
    }
   
}
