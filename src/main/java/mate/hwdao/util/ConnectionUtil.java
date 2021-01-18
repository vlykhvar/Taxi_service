package mate.hwdao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import mate.hwdao.dao.exception.DataProcessingException;

public class ConnectionUtil {
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/dao"
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
            return DriverManager.getConnection(URL, dbProperties);
        } catch (SQLException ex) {
            throw new DataProcessingException("SQLException: " + ex.getMessage(), ex);
        }
    }

    public static void setTable() {
        dropTable();
        String query = FileReader.readFile("src/main/resources/init_db.sql");
        try (PreparedStatement preparedStatement
                    = ConnectionUtil.getConnection()
                .prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't set table ", ex);
        }
    }

    public static void dropTable() {
        String query = "drop table if exists manufacture";
        try {
            PreparedStatement preparedStatement
                    = getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DataProcessingException("table was not deleted", throwables);
        }
    }
}
