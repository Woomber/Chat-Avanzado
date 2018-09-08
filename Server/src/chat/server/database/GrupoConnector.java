package chat.server.database;

import chat.models.Grupo;
import chat.server.log.ServerLog;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Conector SQL para la tabla grupos  
 * 
 * @author Maritza
 */
public class GrupoConnector extends SqlConnector{
    
    private static final String BD_TABLE = "grupo";
    
    /**
     * Obtiene los grupos de un usuario en específico
     * 
     * @param usuario El usuario
     * @return Los grupos a los que pertenece
     */
    public ArrayList<Grupo> getGrupo(String usuario) {
        final String QUERY = "SELECT grupo.id, grupo.nombre FROM usuario_grupo, " + BD_TABLE + 
                " WHERE usuario_grupo.username = ? and usuario_grupo.id_grupo=grupo.id";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, usuario);
            ResultSet rs = query.executeQuery();

            ArrayList<Grupo> resultados = new ArrayList<>();

            while (rs.next()) {
                Grupo item = new Grupo();
                item.setId_grupo(rs.getInt("id"));
                item.setNombre_grupo(rs.getString("nombre"));
                
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
     * Obtiene un grupo por su id
     * 
     * @param id La id del grupo
     * @return El grupo con esa id
     */
    public Grupo get(int id) {
        final String QUERY = "SELECT * FROM " + BD_TABLE + " WHERE id = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();

            Grupo resultados = null;

            if (rs.next()) {
                resultados = new Grupo();
                resultados.setId_grupo(rs.getInt("id"));
                resultados.setNombre_grupo(rs.getString("nombre"));
            }
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros leídos: " +  (resultados == null ? 0 : 1));
            return resultados;

        } catch (SQLException | NullPointerException ex) {
            ServerLog.log(this, MSG_QUERY_ERROR + ": " + QUERY
                    + "\n\t> " + ex.getMessage());
            return null;
        }
    }
    
    /**
     * Agrega un grupo a la base de datos
     * 
     * @param item el grupo por agregar
     * @return El ID del grupo agregado
     */
    public int addGrupo(Grupo item) {
        final String QUERY = "INSERT INTO " + BD_TABLE + "(nombre) VALUES(?)";

        try {
            // Return generated keys permite saber qué se insertó
            PreparedStatement query = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, item.getNombre_grupo());

            query.executeUpdate();
            
            int inserted; 
            
            ResultSet rs = query.getGeneratedKeys();
            if(rs.next()) inserted = rs.getInt(1);
            else inserted = -1;
            rs.close();
            
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Creado grupo: " + inserted);
            return  inserted;

        } catch (SQLException ex) {
              ServerLog.log(this, MSG_QUERY_ERROR + ": " + QUERY
                    + "\n\t> " + ex.getMessage());
            return -1;
        }
    }
    
    /**
     * Elimina un grupo de la base de datos
     * 
     * @param id_grupo El ID del grupo a eliminar
     * @return Verdadero si tuvo éxito
     */
    public boolean eliminarGrupo(int id_grupo) {
        final String QUERY = "DELETE FROM " + BD_TABLE + " WHERE id = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, id_grupo);

            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
}
