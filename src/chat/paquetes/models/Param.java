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
    
    public Param(String nombre, String valor){
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }
   
}
