package chat.server.database;

import chat.models.Mensaje;
import chat.server.log.ServerLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Conector SQL para la tabla mensaje
 * 
 * @author Maritza
 */
public class MensajeConnector extends SqlConnector{
    
    private static final String BD_TABLE = "mensaje";
    
    /**
     * Obtiene todos los mensajes de un grupo
     * 
     * @param id_grupo El grupo del que obtiene los mensajes
     * @return Los mensajes
     */
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
    
    /**
     * Obtiene los mensajes enviados por un usuario a un grupo
     * 
     * @param id_grupo El grupo donde se busca
     * @param id_usuario El usuario remitente
     * @return Los mensajes enviados por ese usuario
     */
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
    
    /**
     * Envía un mensaje a un grupo
     * 
     * @param item El mensaje a enviar
     * @return Verdadero si tuvo éxito
     */
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
    
    /**
     * Elimina todos los mensajes de un grupo
     * 
     * @param grupo El grupo a buscar
     * @return Verdadero si tuvo éxito
     */
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
    
    /**
     * Elimina un mensaje en particular
     * 
     * @param id_mensaje El mensaje a eliminar
     * @return Verdadero si tuvo éxito
     */
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
