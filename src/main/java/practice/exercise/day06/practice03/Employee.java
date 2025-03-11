package practice.exercise.day06.practice03;

import java.io.Serializable;

class Employee implements Serializable {
    private String firstName;
    private String lastName;
    private double wagesPerHour;
    private int hoursWorked;

    public Employee() {}

    public Employee(String firstName, String lastName, double wagesPerHour, int hoursWorked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wagesPerHour = wagesPerHour;
        this.hoursWorked = hoursWorked;
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

    public double getWagesPerHour() {
        return wagesPerHour;
    }

    public void setWagesPerHour(double wagesPerHour) {
        this.wagesPerHour = wagesPerHour;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    // Calculate total wages
    public double calculateTotalWages() {
        return wagesPerHour * hoursWorked;
    }

    /**
     * Convert Employee object to JSON format
     * @return String representation of the employee in JSON format
     */
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"firstName\": \"").append(firstName).append("\",\n");
        sb.append("  \"lastName\": \"").append(lastName).append("\",\n");
        sb.append("  \"wagesPerHour\": ").append(wagesPerHour).append(",\n");
        sb.append("  \"hoursWorked\": ").append(hoursWorked).append("\n");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", wagesPerHour=" + wagesPerHour +
                ", hoursWorked=" + hoursWorked +
                ", totalWages=" + calculateTotalWages() +
                '}';
    }

    /**
     * Parse an employee from a JSON string
     * @param json JSON string to parse
     * @return Employee object
     */
    public static Employee fromJson(String json) {
        Employee employee = new Employee();

        // Extract firstName
        String firstNameKey = "\"firstName\": \"";
        int firstNameStart = json.indexOf(firstNameKey) + firstNameKey.length();
        int firstNameEnd = json.indexOf("\"", firstNameStart);
        employee.setFirstName(json.substring(firstNameStart, firstNameEnd));

        // Extract lastName
        String lastNameKey = "\"lastName\": \"";
        int lastNameStart = json.indexOf(lastNameKey) + lastNameKey.length();
        int lastNameEnd = json.indexOf("\"", lastNameStart);
        employee.setLastName(json.substring(lastNameStart, lastNameEnd));

        // Extract wagesPerHour
        String wagesKey = "\"wagesPerHour\": ";
        int wagesStart = json.indexOf(wagesKey) + wagesKey.length();
        int wagesEnd = json.indexOf(",", wagesStart);
        employee.setWagesPerHour(Double.parseDouble(json.substring(wagesStart, wagesEnd)));

        // Extract hoursWorked
        String hoursKey = "\"hoursWorked\": ";
        int hoursStart = json.indexOf(hoursKey) + hoursKey.length();
        int hoursEnd = json.indexOf("\n", hoursStart);
        employee.setHoursWorked(Integer.parseInt(json.substring(hoursStart, hoursEnd)));

        return employee;
    }
}
