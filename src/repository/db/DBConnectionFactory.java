package repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import util.PropertiesLoader;

public class DBConnectionFactory {

    private Connection connection;
    private static DBConnectionFactory instance;

    private DBConnectionFactory() {
    }

    public static DBConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DBConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            String url = PropertiesLoader.getInstance().getProperty("url");
            String username = PropertiesLoader.getInstance().getProperty("username");
            String password = PropertiesLoader.getInstance().getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        }
        return connection;
    }
}
