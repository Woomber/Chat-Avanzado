package chat.server.database;

import chat.models.UsuarioGrupo;
import static chat.server.database.SqlConnector.MSG_QUERY_SUCCESS;
import chat.server.log.ServerLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Maritza
 */
public class UsuarioGrupoConnector extends SqlConnector{
    
    private static final String BD_TABLE = "usuario_grupo";
    
     public ArrayList<UsuarioGrupo> getAllUsuarios(int id_grupo) {
        final String QUERY = "SELECT * FROM " + BD_TABLE + " WHERE id_grupo = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, id_grupo);
            ResultSet rs = query.executeQuery();

            ArrayList<UsuarioGrupo> resultados = new ArrayList<>();

            while (rs.next()) {
                UsuarioGrupo item = new UsuarioGrupo();
                item.setId_grupo(rs.getInt("id_grupo"));
                item.setId_usuario(rs.getString("id_usuario"));
                item.setStatus(rs.getBoolean("status"));
                
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
     public UsuarioGrupo getUsuario(String id, int id_grupo) {
        final String QUERY = "SELECT * FROM " + BD_TABLE + " WHERE username = ? and id_grupo = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, id);
            query.setInt(2, id_grupo);
            ResultSet rs = query.executeQuery();

            UsuarioGrupo resultados = new UsuarioGrupo();

                resultados.setId_grupo(rs.getInt("id_grupo"));
                resultados.setId_usuario(rs.getString("id_usuario"));
                resultados.setStatus(rs.getBoolean("status"));
                
            
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registro leido");
            return resultados;

        } catch (SQLException | NullPointerException ex) {
            ServerLog.log(this, MSG_QUERY_ERROR + ": " + QUERY
                    + "\n\t> " + ex.getMessage());
            return null;
        }
    }
     
     public ArrayList<UsuarioGrupo> getGrupos(String id) {
        final String QUERY = "SELECT * FROM " + BD_TABLE + " WHERE username = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, id);
            ResultSet rs = query.executeQuery();

            ArrayList<UsuarioGrupo> resultados = new ArrayList<>();
            
            while (rs.next()) {
                UsuarioGrupo item = new UsuarioGrupo();
                item.setId_grupo(rs.getInt("id_grupo"));
                item.setId_usuario(rs.getString("id_usuario"));
                item.setStatus(rs.getBoolean("status"));
                
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
    public boolean add(UsuarioGrupo item) {
        final String QUERY = "INSERT INTO " + BD_TABLE + " VALUES(?, ?, ?)";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, item.getId_grupo());
            query.setString(2, item.getId_usuario());
            query.setBoolean(3, item.isStatus());

            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
    public boolean modificar(UsuarioGrupo item) {
        final String QUERY = "UPDATE " + BD_TABLE + " SET status = ? WHERE username = ? and id_grupo = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setBoolean(1, item.isStatus());
            query.setString(2, item.getId_usuario());
            query.setInt(3, item.getId_grupo());
            
            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean eliminar(UsuarioGrupo item) {
        final String QUERY = "DELETE FROM " + BD_TABLE + " WHERE username = ? and id_grupo = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, item.getId_usuario());
            query.setInt(2, item.getId_grupo());
            
            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
    public boolean eliminarGrupo(int id) {
        final String QUERY = "DELETE FROM " + BD_TABLE + " WHERE id_grupo = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, id);
            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
}
