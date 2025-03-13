package practice.exercise.day07.lesson03.class_work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO{

    private Connection connection;

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO Student( firstName, lastName) VALUES(?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(int id) {
        String sql = "INSERT * from Student where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        return List.of();
    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudent(int id) {

    }
}
