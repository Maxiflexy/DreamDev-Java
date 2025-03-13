package practice.exercise.day07.lesson03.exercise.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/dreamdevs_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    private DatabaseConnectionFactory() {}

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found", e);
        }
    }
}
