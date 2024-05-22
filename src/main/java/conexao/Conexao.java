package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conexao {
    protected String password = "senha";
    private static Connection connection;

    protected abstract String getUser();
    protected abstract String getUrl();

    public Connection getConexao() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(getUrl(), getUser(), password);
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
