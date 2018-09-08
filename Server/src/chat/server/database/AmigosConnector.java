package chat.server.database;

import chat.models.Amigo;
import chat.server.log.ServerLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Conector SQL para la tabla amigos
 * @author Maritza
 */
public class AmigosConnector extends SqlConnector{
    
    private static final String BD_TABLE = "amigos";
    
    /**
     * Envía todos los amigos de un usuario
     * @param id_usuario el usuario 
     * @return un arreglo con los amigos del usuario
     */
    public ArrayList<Amigo> getAll(String id_usuario) {
        final String QUERY = "SELECT * FROM " + BD_TABLE + " WHERE username = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, id_usuario);
            ResultSet rs = query.executeQuery();

            ArrayList<Amigo> resultados = new ArrayList<>();

            while (rs.next()) {
                Amigo item = new Amigo();
                item.setId_usuario(rs.getString("username"));
                item.setApodo(rs.getString("apodo"));
                item.setAmigo(rs.getString("amigo"));
                
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
     * Agrega un amigo a una persona
     * 
     * @param item La relación de amistad por agregar
     * @return Verdadero si se agregó
     */
    public boolean add(Amigo item) {
        final String QUERY = "INSERT INTO " + BD_TABLE + " VALUES(?, ?, ?)";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, item.getId_usuario());
            query.setString(3, item.getApodo());
            query.setString(2, item.getAmigo());

            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            ServerLog.log(ex, ex.getMessage());
            return false;
        }
    }
    
    /**
     * Eliminar un vínculo de amistad
     * 
     * @param item El vínculo de amistad a eliminar
     * @return Verdadero si tuvo éxito
     */
    public boolean eliminar(Amigo item) {
        final String QUERY = "DELETE FROM " + BD_TABLE + " WHERE username = ? AND amigo = ?";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            query.setString(1, item.getId_usuario());
            query.setString(2, item.getAmigo());
            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }
}
