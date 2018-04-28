package chat.server.database;

import chat.models.Usuario;
import chat.server.log.ServerLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UsuarioConnector extends SqlConnector {

    private static final String BD_TABLE = "usuario";

    public ArrayList<Usuario> getAll() {
        final String QUERY = "SELECT * FROM " + BD_TABLE;

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            ResultSet rs = query.executeQuery();

            ArrayList<Usuario> resultados = new ArrayList<>();

            while (rs.next()) {
                Usuario item = new Usuario();
                item.setId_usuario(rs.getString("id_usuario"));
                item.setContrasena(rs.getString("contrasena"));
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
    
     public ArrayList<Usuario> getUsuario(int idUsuario) {
        final String QUERY = "SELECT * FROM " + BD_TABLE + "WHERE id_usuario =" + idUsuario;

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            ResultSet rs = query.executeQuery();

            ArrayList<Usuario> resultados = new ArrayList<>();

            while (rs.next()) {
                Usuario item = new Usuario();
                item.setId_usuario(rs.getString("id_usuario"));
                item.setContrasena(rs.getString("contrasena"));
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
    public boolean eliminar(String idUsuario) {
        final String QUERY = "DELETE FROM " + BD_TABLE + " WHERE id_usuario = "+ idUsuario;

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);

            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
    public boolean verificar(String idUsuario, String contrasena) {
        final String QUERY = "SELECT *FROM " + BD_TABLE + " WHERE id_usuario = "+ idUsuario + "AND contrasena ="+ contrasena;

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            ResultSet rs = query.executeQuery();

            ArrayList<Usuario> resultados = new ArrayList<>();

            while (rs.next()) {
                Usuario item = new Usuario();
                item.setId_usuario(rs.getString("id_usuario"));
                item.setContrasena(rs.getString("contrasena"));
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
