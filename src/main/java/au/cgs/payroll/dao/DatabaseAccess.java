package au.cgs.payroll.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is a help class for accessing database
 */

public class DatabaseAccess {
    /**
     * Get connection to mySQl database
     *
     * @return Connection
     * @throws SQLException a SQL exception
     */
    public static Connection getConnection() throws SQLException {
        Connection conn;

        String url = "jdbc:mysql://localhost:3306/PayrollManager?useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "PayrollManager";
        String password = "Wowsofun~2";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

}


