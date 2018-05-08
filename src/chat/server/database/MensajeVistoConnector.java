package chat.server.database;

import chat.models.MensajeVisto;
import chat.server.log.ServerLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Conector SQL para la tabla mensaje visto
 * @author Maritza
 */
public class MensajeVistoConnector extends SqlConnector{
    
    private static final String BD_TABLE = "mensaje_visto";
    
    /**
     * Marca un mensaje como visto
     * 
     * @param item El mensaje a marcar
     * @return Verdadero si tuvo éxito
     */
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
    
    /**
     * Obtiene todos los que vieron un mensaje determinado
     * 
     * @param idMensaje El ID del mensaje a revisar
     * @return Los que vieron el mensaje
     */
    public ArrayList<MensajeVisto> getAll(int idMensaje) {
        final String QUERY = "SELECT *FROM " + BD_TABLE + " WHERE id_mensaje = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, idMensaje);
            ResultSet rs = query.executeQuery();

            ArrayList<MensajeVisto> resultados = new ArrayList<>();

            while (rs.next()) {
                MensajeVisto item = new MensajeVisto();
                item.setId_mensaje(rs.getInt("id_mensaje"));
                item.setId_usuario(rs.getString("username"));
                
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
     * Obtiene si el usuario ha visto el mensaje
     * 
     * @param idMensaje El ID del mensaje a revisar
     * @param id_usuario El usuario
     * @return Un objeto con la relación si lo ha visto, null si no
     */
    public MensajeVisto get(int idMensaje, String id_usuario) {
        final String QUERY = "SELECT *FROM " + BD_TABLE + " WHERE id_mensaje = ? AND username = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setInt(1, idMensaje);
            query.setString(2, id_usuario);
            ResultSet rs = query.executeQuery();

            MensajeVisto item = null;

            if (rs.next()) {
                item = new MensajeVisto();
                item.setId_mensaje(rs.getInt("id_mensaje"));
                item.setId_usuario(rs.getString("username"));
            }
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros leídos: " + (item == null ? "1" : "0"));
            return item;

        } catch (SQLException | NullPointerException ex) {
            ServerLog.log(this, MSG_QUERY_ERROR + ": " + QUERY
                    + "\n\t> " + ex.getMessage());
            return null;
        }
    }

    /**
     * Elimina las confirmaciones de visto de un mensaje
     * 
     * @param id El id del mensaje
     * @return Verdadero si tuvo éxito
     */
    public boolean eliminar(int id) {
        final String QUERY = "DELETE FROM " + BD_TABLE + " WHERE id_mensaje = ?";

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
    
    /**
     * Elimina los mensajes vistos en un grupo
     * 
     * @param id el ID del grupo
     * @return verdadero si tuvo éxito
     */
    public boolean eliminarGrupo(int id) {
        final String QUERY = "DELETE FROM " + BD_TABLE + " WHERE id_mensaje IN (SELECT id FROM mensaje where id_grupo = ?)";

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
