package practice.exercise.day06.practice03;

import java.io.Serializable;

public class Student implements Serializable {
    int rollNo;
    String name;
    transient String password;

    public Student(int rollNo, String name, String password) {
        this.rollNo = rollNo;
        this.name = name;
        this.password = password;
    }
}
