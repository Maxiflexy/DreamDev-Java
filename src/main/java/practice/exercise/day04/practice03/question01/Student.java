package practice.exercise.day04.practice03.question01;

public class Student {

    private Integer id;
    private String name;
    private Integer age;
    private String grade;

    public Student(Integer id, String name, Integer age, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void displayStudentDetails() {
        System.out.println("\nID: " + id + ", Name: " + name + ", Age: " + age + ", Grade: " + grade);
    }

    public void updateGrade(String newGrade) {
        this.grade = newGrade;
        System.out.println("\nGrade updated successfully for " + name);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
