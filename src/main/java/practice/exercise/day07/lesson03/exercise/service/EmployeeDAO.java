package practice.exercise.day07.lesson03.exercise.service;

import practice.exercise.day07.lesson03.exercise.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    /**
     * Insert a new employee record
     * @param employee The employee to insert
     * @return The employee with ID populated
     */
    Employee insert(Employee employee) throws SQLException;

    /**
     * Find employee by ID
     * @param id The employee ID
     * @return The employee or null if not found
     */
    Employee findById(int id) throws SQLException;

    /**
     * Find all employees
     * @return List of all employees
     */
    List<Employee> findAll() throws SQLException;

    /**
     * Update an existing employee
     * @param employee The employee to update
     * @return true if updated successfully, false otherwise
     */
    boolean update(Employee employee) throws SQLException;

    /**
     * Delete an employee by ID
     * @param id The employee ID to delete
     * @return true if deleted successfully, false otherwise
     */
    boolean deleteById(int id) throws SQLException;

    /**
     * Save multiple employees in a batch operation
     * @param employees List of employees to save
     * @return Number of employees saved
     */
    int saveAll(List<Employee> employees) throws SQLException;
}
