package chat.server.database;

import chat.models.Mensaje;
import chat.server.log.ServerLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Maritza
 */
public class MensajeConnector extends SqlConnector{
    
    private static final String BD_TABLE = "mensaje";
    
    public ArrayList<Mensaje> getAll(int id_grupo) {
        final String QUERY = "SELECT * FROM " + BD_TABLE + " WHERE id_grupo = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, id_grupo);
            ResultSet rs = query.executeQuery();

            ArrayList<Mensaje> resultados = new ArrayList<>();

            while (rs.next()) {
                Mensaje item = new Mensaje();
                item.setId_mensaje_grupal(rs.getInt("id"));
                item.setId_usuario(rs.getString("username"));
                item.setTexto(rs.getString("texto"));
                item.setId_grupo(rs.getInt("id_grupo"));
                
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
    public ArrayList<Mensaje> getUsuario(int id_grupo, String id_usuario) {
        final String QUERY = "SELECT * FROM " + BD_TABLE + " WHERE id_grupo = ? and username = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, id_grupo);
            query.setString(2, id_usuario);
            ResultSet rs = query.executeQuery();

            ArrayList<Mensaje> resultados = new ArrayList<>();

            while (rs.next()) {
                Mensaje item = new Mensaje();
                item.setId_mensaje_grupal(rs.getInt("id"));
                item.setId_usuario(rs.getString("username"));
                item.setTexto(rs.getString("texto"));
                item.setId_grupo(rs.getInt("id_grupo"));
                
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
    public boolean add(Mensaje item) {
        final String QUERY = "INSERT INTO " + BD_TABLE + "(username, texto, id_grupo) VALUES(?, ?, ?)";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, item.getId_usuario());
            query.setString(2, item.getTexto());
            query.setInt(3, item.getId_grupo());

            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
    public boolean eliminarGrupo(int grupo) {
        final String QUERY = "DELETE FROM " + BD_TABLE + " WHERE id_grupo = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, grupo);
            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean eliminar(int id_mensaje){
      final String QUERY = "DELETE FROM " + BD_TABLE + " WHERE id = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, id_mensaje);
            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }   
    }
    
}
