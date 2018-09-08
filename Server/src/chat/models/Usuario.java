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

    /**
     * Constructor para inicalizar valores al iniciar sesión con un usuario
     * @param id_usuario Identificador del usuario que inició sesión
     * @param contrasena La contraseña del usuario
     * @param nombre El nombre de usuario
     */
    public Usuario(String id_usuario, String contrasena, String nombre) {
        this.id_usuario = id_usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador del usuario
     * @return Retorna el id del usuario
     */
    public String getId_usuario() {
        return id_usuario;
    }

    /**
     * Establece el id del usuario en sesión
     * @param id_usuario Id del usuario
     */
    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * Obtiene la contraseña del usuario
     * @return Retorna la contraseña obtenida
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario
     * @param contrasena Contraseña del usuario
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el nombre de usuario que inició sesión
     * @return Retorna el nombre de usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de usuario en sesión
     * @param nombre Nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
   
    
}
