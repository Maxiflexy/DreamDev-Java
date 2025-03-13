package practice.exercise.day07.lesson03.exercise.model;

import java.math.BigDecimal;

public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private BigDecimal wagesPerHour;
    private BigDecimal hoursWorked;


    public Employee() {
    }

    public Employee(String firstName, String lastName, BigDecimal wagesPerHour, BigDecimal hoursWorked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wagesPerHour = wagesPerHour;
        this.hoursWorked = hoursWorked;
    }

    public Employee(Integer id, String firstName, String lastName, BigDecimal wagesPerHour, BigDecimal hoursWorked) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.wagesPerHour = wagesPerHour;
        this.hoursWorked = hoursWorked;
    }

    public BigDecimal calculateTotalSalary() {
        return wagesPerHour.multiply(hoursWorked);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getWagesPerHour() {
        return wagesPerHour;
    }

    public void setWagesPerHour(BigDecimal wagesPerHour) {
        this.wagesPerHour = wagesPerHour;
    }

    public BigDecimal getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(BigDecimal hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", wagesPerHour=" + wagesPerHour +
                ", hoursWorked=" + hoursWorked +
                ", totalSalary=" + calculateTotalSalary() +
                '}';
    }
}
