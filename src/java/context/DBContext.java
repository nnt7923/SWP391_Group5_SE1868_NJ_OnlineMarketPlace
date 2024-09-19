package context;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    public Connection connection;
    public static final String SOFT_DELETE_CONDITION = "deleted_at IS NULL";

    public DBContext() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName= online_marketplace";
            String username = "khanhduy583";
            String password = "sa";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
}