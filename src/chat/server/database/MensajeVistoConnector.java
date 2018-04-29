package chat.server.database;

import chat.models.MensajeVisto;
import chat.server.log.ServerLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Maritza
 */
public class MensajeVistoConnector extends SqlConnector{
    
    private static final String BD_TABLE = "mensaje_visto";
    
    public boolean add(MensajeVisto item) {
        final String QUERY = "INSERT INTO " + BD_TABLE + " VALUES(?, ?)";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, item.getId_mensaje());
            query.setString(2, item.getId_usuario());

            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
    public ArrayList<MensajeVisto> getAll(int idMensaje) {
        final String QUERY = "SELECT *FROM " + BD_TABLE + " WHERE id_mensaje =" + idMensaje;

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            ResultSet rs = query.executeQuery();

            ArrayList<MensajeVisto> resultados = new ArrayList<>();

            while (rs.next()) {
                MensajeVisto item = new MensajeVisto();
                item.setId_mensaje(rs.getInt("id_mensaje_grupal"));
                item.setId_usuario(rs.getString("id_usuario"));
                
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
    public boolean eliminar(int id) {
        final String QUERY = "DELETE FROM " + BD_TABLE + " WHERE id_mensaje = (SELECT id FROM mensaje where id_grupo="+id+")";

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
