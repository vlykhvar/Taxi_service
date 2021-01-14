package mate.hwdao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private final static String LOGIN = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306"
            + "?useUnicode=true&useJDBCCompliantTimezoneShift="
            + "true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find MySQL Driver", e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", LOGIN);
        dbProperties.put("password", PASSWORD);
        try {
            Connection connection = DriverManager.getConnection(URL, dbProperties);
            return connection;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }
}
