package chat.server.database;

import chat.models.Usuario;
import chat.server.log.ServerLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Conector SQL de la tabla usuarios
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UsuarioConnector extends SqlConnector {

    private static final String BD_TABLE = "usuario";

    /**
     * Obtiene todos los usuarios
     * 
     * @return Una lista con los usuarios
     */
    public ArrayList<Usuario> getAll() {
        final String QUERY = "SELECT * FROM " + BD_TABLE;

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            ResultSet rs = query.executeQuery();

            ArrayList<Usuario> resultados = new ArrayList<>();

            while (rs.next()) {
                Usuario item = new Usuario();
                item.setId_usuario(rs.getString("username"));
                item.setContrasena(rs.getString("password"));
                item.setNombre(rs.getString("nombre"));
                
                resultados.add(item);
            }
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros leídos: " + resultados.size());
            return resultados;

        } catch (SQLException | NullPointerException ex) {
            ServerLog.log(this, MSG_QUERY_ERROR + ": " + QUERY
                    + "\n\t> " + ex.getMessage());
            return null;
        }
    }
    
    /**
     * Obtiene los datos de un usuario dado su username
     * @param idUsuario El username
     * @return El usuario con ese username
     */
    public ArrayList<Usuario> getUsuario(String idUsuario) {
        final String QUERY = "SELECT * FROM " + BD_TABLE + " WHERE username = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, idUsuario);
            ResultSet rs = query.executeQuery();

            ArrayList<Usuario> resultados = new ArrayList<>();

            while (rs.next()) {
                Usuario item = new Usuario();
                item.setId_usuario(rs.getString("username"));
                item.setContrasena(rs.getString("password"));
                item.setNombre(rs.getString("nombre"));
                
                resultados.add(item);
            }
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros leídos: " + resultados.size());
            return resultados;

        } catch (SQLException | NullPointerException ex) {
            ServerLog.log(this, MSG_QUERY_ERROR + ": " + QUERY
                    + "\n\t> " + ex.getMessage());
            return null;
        }
    }

    /**
     * Agrega un nuevo usuario
     * 
     * @param item El usuario por agregar
     * @return Verdadero si tuvo éxito
     */
    public boolean add(Usuario item) {
        final String QUERY = "INSERT INTO " + BD_TABLE + " VALUES(?, ?, ?)";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, item.getId_usuario());
            query.setString(2, item.getContrasena());
            query.setString(3, item.getNombre());

            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
    
    /**
     * Elimina un usuario
     * 
     * @param idUsuario El id del usuario a eliminar
     * @return Verdadero si tuvo éxito
     */
    public boolean eliminar(String idUsuario) {
        final String QUERY = "DELETE FROM " + BD_TABLE + " WHERE username = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, idUsuario);

            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
    
    /**
     * Verifica si un usuario y contraseña coinciden con la base de datos
     * 
     * @param idUsuario El usuario
     * @param contrasena La contraseña
     * @return Verdadero si coinciden
     */
    public boolean verificar(String idUsuario, String contrasena) {
        final String QUERY = "SELECT *FROM " + BD_TABLE + " WHERE username = ? AND password = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, idUsuario);
            query.setString(2, contrasena);
            ResultSet rs = query.executeQuery();

            ArrayList<Usuario> resultados = new ArrayList<>();

            while (rs.next()) {
                Usuario item = new Usuario();
                item.setId_usuario(rs.getString("username"));
                item.setContrasena(rs.getString("password"));
                item.setNombre(rs.getString("nombre"));
                
                resultados.add(item);
            }
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros leídos: " + resultados.size());
            return resultados.size() == 1;

        } catch (SQLException | NullPointerException ex) {
            ServerLog.log(this, MSG_QUERY_ERROR + ": " + QUERY
                    + "\n\t> " + ex.getMessage());
            return false;
        }
    }

}
