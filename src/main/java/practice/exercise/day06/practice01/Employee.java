package practice.exercise.day06.practice01;

public class Employee {
    private String firstName;
    private String lastName;
    private double wagesPerHour;
    private int hoursWorked;

    public Employee(String firstName, String lastName, double wagesPerHour, int hoursWorked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wagesPerHour = wagesPerHour;
        this.hoursWorked = hoursWorked;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getWagesPerHour() {
        return wagesPerHour;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Returns the employee data formatted as "FirstName | LastName | WagesPerHour | HoursWorked"
     * @return formatted employee data string
     */
    public String getFormattedData() {
        return firstName + "|" + lastName + "|" + wagesPerHour + "|" + hoursWorked + "\n";
    }
}