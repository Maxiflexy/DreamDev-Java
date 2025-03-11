package practice.exercise.day07.lesson02;

import java.sql.*;

public class JDBCExample {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5433/dreamdev";
        String user = "dreamdev";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the PostgreSQL server successfully.");

            // CREATE - Insert a new student
            insertStudent(connection, "maximillian", "ejemba");

            // READ - Retrieve all students
            readAllStudents(connection);

            // UPDATE - Update a student's information
            updateStudent(connection, "maximillian", "ejemba", "max", "kachi");

            // READ AGAIN - See the changes
            readAllStudents(connection);

            // DELETE - Remove a student
            deleteStudent(connection, "max", "ejemba");

            // READ AGAIN - Confirm deletion
            readAllStudents(connection);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }


    // CREATE - Insert a student
    private static void insertStudent(Connection conn, String firstName, String lastName) throws SQLException {
        String sql = "INSERT INTO student (firstName, lastName) VALUES (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted + " student inserted: " + firstName + " " + lastName);
        }
    }

    // READ - Retrieve all students
    private static void readAllStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM student";
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("\n--- All Students ---");
            while (resultSet.next()) {
                // Adjust column names if needed
                int id = resultSet.getInt("id");  // Assuming there's an id column
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");

                System.out.println(id + ": " + firstName + " " + lastName);
            }
            System.out.println("-------------------\n");
        }
    }

    // UPDATE - Update a student's information
    private static void updateStudent(Connection conn, String oldFirstName, String oldLastName,
                                      String newFirstName, String newLastName) throws SQLException {
        String sql = "UPDATE student SET firstName = ?, lastName = ? WHERE firstName = ? AND lastName = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, newFirstName);
            statement.setString(2, newLastName);
            statement.setString(3, oldFirstName);
            statement.setString(4, oldLastName);

            int rowsUpdated = statement.executeUpdate();
            System.out.println(rowsUpdated + " student updated from " +
                    oldFirstName + " " + oldLastName + " to " +
                    newFirstName + " " + newLastName);
        }
    }

    // DELETE - Remove a student
    private static void deleteStudent(Connection conn, String firstName, String lastName) throws SQLException {
        String sql = "DELETE FROM student WHERE firstName = ? AND lastName = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            int rowsDeleted = statement.executeUpdate();
            System.out.println(rowsDeleted + " student deleted: " + firstName + " " + lastName);
        }
    }
}
