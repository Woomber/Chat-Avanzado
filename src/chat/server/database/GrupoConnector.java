package chat.server.database;

import chat.models.Grupo;
import chat.server.log.ServerLog;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Maritza
 */
public class GrupoConnector extends SqlConnector{
    
    private static final String BD_TABLE = "grupo";
    
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
