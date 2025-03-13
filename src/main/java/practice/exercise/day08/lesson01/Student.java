package practice.exercise.day08.lesson01;

public class Student {

    private final String fullName;
    private final Gender gender;
    private final Level level;
    private final String matricNo;

    public Student(String fullName, Gender gender, Level level, String matricNo) {
        this.fullName = fullName;
        this.gender = gender;
        this.level = level;
        this.matricNo = matricNo;
    }

    public String getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public Level getLevel() {
        return level;
    }

    public String getMatricNo() {
        return matricNo;
    }

    @Override
    public String toString() {
        return "Name: " + fullName +
                ", Gender: " + gender +
                ", Level: " + level +
                ", Matric No: " + matricNo;
    }
}
