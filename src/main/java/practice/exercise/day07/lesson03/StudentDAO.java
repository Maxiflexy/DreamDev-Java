package practice.exercise.day07.lesson03;

import java.util.List;

public interface StudentDAO {

    void addStudent(Student student);
    Student getStudentById(int id);
    List<Student> getAllStudents();
    void updateStudent(Student student);
    void deleteStudent(int id);
}
