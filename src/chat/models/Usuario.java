package chat.models;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class Usuario {
    // Temporal
    private int id_usuario;
    private String contrasena;
    private String nombre_usuario;
    private String nombre_pila;
    
   
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getNombre_pila() {
        return nombre_pila;
    }

    public void setNombre_pila(String nombre_pila) {
        this.nombre_pila = nombre_pila;
    }
    
    
}
