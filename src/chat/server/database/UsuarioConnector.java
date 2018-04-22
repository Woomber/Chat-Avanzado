package chat.server.database;

import chat.models.Usuario;
import chat.server.log.ServerLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class UsuarioConnector extends SqlConnector {

    private static final String BD_TABLE = "usuarios";

    public ArrayList<Usuario> getAll() {
        final String QUERY = "SELECT * FROM " + BD_TABLE;

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            ResultSet rs = query.executeQuery();

            ArrayList<Usuario> resultados = new ArrayList<>();

            while (rs.next()) {
                Usuario item = new Usuario();
                // Temporal, sólo como ejemplo
                item.setUsername(rs.getString("username"));
                item.setUsername(rs.getString("password"));
                
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

    public boolean add(Usuario item) {
        final String QUERY = "INSERT INTO " + BD_TABLE + " VALUES(?, ?)";

        try {
            PreparedStatement query = connection.prepareStatement(QUERY);
            // Temporal, sólo como ejemplo
            query.setString(1, item.getUsername());
            query.setString(2, item.getPassword());

            int updated = query.executeUpdate();
            ServerLog.log(this, MSG_QUERY_SUCCESS + ": " + QUERY
                    + " > Registros actualizados: " + updated);
            return  updated > 0;

        } catch (SQLException ex) {
            return false;
        }
    }

}
