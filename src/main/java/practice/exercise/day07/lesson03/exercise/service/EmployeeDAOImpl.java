package practice.exercise.day07.lesson03.exercise.service;

import practice.exercise.day07.lesson03.exercise.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Employee insert(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (first_name, last_name, wages_per_hour, hours_worked) " +
                "VALUES (?, ?, ?, ?) RETURNING id";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setBigDecimal(3, employee.getWagesPerHour());
            statement.setBigDecimal(4, employee.getHoursWorked());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    employee.setId(resultSet.getInt("id"));
                }
            }
        }

        return employee;
    }

    @Override
    public Employee findById(int id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToEmployee(resultSet);
                }
            }
        }

        return null; // Not found
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                employees.add(mapResultSetToEmployee(resultSet));
            }
        }

        return employees;
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET first_name = ?, last_name = ?, " +
                "wages_per_hour = ?, hours_worked = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setBigDecimal(3, employee.getWagesPerHour());
            statement.setBigDecimal(4, employee.getHoursWorked());
            statement.setInt(5, employee.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM employee WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    @Override
    public int saveAll(List<Employee> employees) throws SQLException {
        String sql = "INSERT INTO employee (first_name, last_name, wages_per_hour, hours_worked) " +
                "VALUES (?, ?, ?, ?)";

        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            boolean autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            try {
                for (Employee employee : employees) {
                    statement.setString(1, employee.getFirstName());
                    statement.setString(2, employee.getLastName());
                    statement.setBigDecimal(3, employee.getWagesPerHour());
                    statement.setBigDecimal(4, employee.getHoursWorked());
                    statement.addBatch();
                    count++;
                }

                statement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(autoCommit);
            }
        }

        return count;
    }

    private Employee mapResultSetToEmployee(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getBigDecimal("wages_per_hour"),
                resultSet.getBigDecimal("hours_worked")
        );
    }
}
