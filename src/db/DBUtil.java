package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String USERNAME = "kelton";
    private static final String PASSWORD = "Dfw36!jbp";
    private static final String H_CONN_STRING =
            "jdbc:hsqldb:data/exercise1";
    private static final String M_CONN_STRING =
            "jdbc:mysql://localhost/excercise1";

    public static Connection getConnection(DBType dbtype) throws SQLException {
        switch (dbtype) {
            case MYSQL:
                return DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
            case HSQLDB:
                return DriverManager.getConnection(H_CONN_STRING, USERNAME, PASSWORD);
            default:
                return null;
        }
    }

    public static void processException(SQLException e) {
        System.err.println("Error Message: " + e.getMessage());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("SQL state: " + e.getSQLState());
    }


}
