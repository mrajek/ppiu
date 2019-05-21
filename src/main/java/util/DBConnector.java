package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public Connection initializeConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db_users?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url,user,password);
    }
}
