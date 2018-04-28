package chat.server.database;

import chat.models.Grupo;
import chat.server.log.ServerLog;
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
    
    public ArrayList<Grupo> getGrupo(int id_grupo) {
        final String QUERY = "SELECT * FROM " + BD_TABLE + "WHERE id_grupo =" + id_grupo;

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            ResultSet rs = query.executeQuery();

            ArrayList<Grupo> resultados = new ArrayList<>();

            while (rs.next()) {
                Grupo item = new Grupo();
                item.setId_grupo(rs.getInt("id_grupo"));
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
    
    public boolean addGrupo(Grupo item) {
        final String QUERY = "INSERT INTO " + BD_TABLE + " VALUES(?, ?)";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, item.getId_grupo());
            query.setString(2, item.getNombre_grupo());

            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean eliminarGrupo(int id_grupo) {
        final String QUERY = "DELETE FROM " + BD_TABLE + " WHERE id_grupo =" + id_grupo;

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
}
