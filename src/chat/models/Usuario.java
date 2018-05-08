package chat.models;

/**
 * Modelo de usuarios
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class Usuario {
    
    private String id_usuario;
    private String contrasena;
    private String nombre;

    public Usuario() {
    }

    public Usuario(String id_usuario, String contrasena, String nombre) {
        this.id_usuario = id_usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
   
    
}
