package practice.exercise.day06.practice03;

import java.io.*;

public class SerializationExample {

    public static void main(String[] args) {
        Student student = new Student(101, "John Doe", "password123");

        try (ObjectInputStream objectOutputStream = new ObjectInputStream(new FileInputStream("student.ser"))){
            Student student1 = (Student) objectOutputStream.readObject();
            System.out.println("Student Details");
            System.out.println("Roll no: " + student.rollNo);
            System.out.println("Name: " + student);
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
