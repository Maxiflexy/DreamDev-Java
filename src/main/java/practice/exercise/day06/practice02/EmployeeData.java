package practice.exercise.day06.practice02;

public class EmployeeData {

    private String firstName;
    private String lastName;
    private double wagesPerHour;
    private int hoursWorked;

    public EmployeeData(String firstName, String lastName, double wagesPerHour, int hoursWorked) {
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
     * Calculate the payout for this employee
     * @return payout amount (wages * hours)
     */
    public double calculatePayout() {
        return wagesPerHour * hoursWorked;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ": $" + calculatePayout();
    }
}
