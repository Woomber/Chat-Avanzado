package chat.server.database;

import chat.server.log.ServerLog;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conector de SQL abstracto
 * 
 * Funciona como clase base para los controladores de la base de datos
 * 
 * @author Yael Arturo Chavoya Andalón 14300094
 */
abstract class SqlConnector {

    protected Connection connection;

    // Detalles conexión
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/cocochat";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Mensajes de error
    protected static final String MSG_CONNECTION_ERROR = "Error conectando con la BD";
    protected static final String MSG_CONNECTION_SUCCESS = "Conexión a la BD realizada con éxito";
    protected static final String MSG_QUERY_ERROR = "Error al realizar consulta";
    protected static final String MSG_QUERY_SUCCESS = "Consulta realizada con éxito";

    /**
     * Conecta a la base de datos
     */
    protected SqlConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            ServerLog.log(connection, MSG_CONNECTION_SUCCESS);
        } catch (ClassNotFoundException | SQLException ex) {
            ServerLog.log(SqlConnector.class, MSG_CONNECTION_ERROR + ": " + ex.getMessage());
        }
    }

}
