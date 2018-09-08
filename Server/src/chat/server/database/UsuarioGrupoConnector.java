package chat.server.database;

import chat.models.UsuarioGrupo;
import static chat.server.database.SqlConnector.MSG_QUERY_SUCCESS;
import chat.server.log.ServerLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Conector SQL para la tabla usuario grupo
 * 
 * @author Maritza
 */
public class UsuarioGrupoConnector extends SqlConnector{
    
    private static final String BD_TABLE = "usuario_grupo";
    
    /**
     * Obtiene los usuarios en un grupo
     * 
     * @param id_grupo El id del grupo a buscar
     * @return Los usuarios en ese grupo
     */
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
                item.setId_usuario(rs.getString("username"));
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
     
     /**
      * Obtiene si un usuario pertenece a un grupo
      * 
      * @param id El id del usuario
      * @param id_grupo El id del grupo
      * @return Un objeto si pertenece, null si no
      */
     public UsuarioGrupo getUsuario(String id, int id_grupo) {
        final String QUERY = "SELECT * FROM " + BD_TABLE + " WHERE username = ? and id_grupo = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, id);
            query.setInt(2, id_grupo);
            ResultSet rs = query.executeQuery();

            UsuarioGrupo resultados = null;

            if (rs.next()) {
                resultados = new UsuarioGrupo();
                resultados.setId_grupo(rs.getInt("id_grupo"));
                resultados.setId_usuario(rs.getString("username"));
                resultados.setStatus(rs.getBoolean("status"));   
            }                
            
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registro leido");
            return resultados;

        } catch (SQLException | NullPointerException ex) {
            ServerLog.log(this, MSG_QUERY_ERROR + ": " + QUERY
                    + "\n\t> " + ex.getMessage());
            return null;
        }
    }
    
     /**
      * Obtiene los grupos de un usuario
      * 
      * @param id El id de usuario
      * @return Los grupos a los que pertenece
      */
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
                item.setId_usuario(rs.getString("username"));
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
     
     /**
      * Agrega un usuario a un grupo
      * @param item El objeto usuario grupo
      * @return Verdadero si tuvo éxito
      */
    public boolean add(UsuarioGrupo item) {
        final String QUERY = "INSERT INTO " + BD_TABLE + "(id_grupo, username, status) VALUES(?, ?, ?)";

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
    
    /**
     * Cambia el estado de un usuario en un grupo
     * 
     * @param item El usuario grupo con el nuevo estado
     * @return Verdadero si tuvo éxito
     */
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
    
    /**
     * Elimina un usuario de un grupo
     * 
     * @param item El usuario y grupo a eliminar
     * @return Verdadero si tuvo éxito
     */
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
    
    /**
     * Elimina todos los usuarios de un grupo
     * 
     * @param id El grupo del cual eliminar
     * @return Verdadero si tuvo éxito
     */
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
