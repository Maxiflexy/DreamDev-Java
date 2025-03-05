package practice.exercise.day03;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PracticeExercise01 {

    public static void main(String[] args) {
        List<Integer> numbers = IntStream.rangeClosed(1, 20).boxed().toList();
        System.out.println(numbers);

        System.out.println("Even Numbers: " + filterEvenNumbers(numbers));
        System.out.println("Odd Numbers: " + filterOddNumbers(numbers));
        System.out.println("Numbers > 5 and Even: " + filterGreaterThan5AndEven(numbers));

        System.out.println("Even Numbers Printed:");
        printEvenNumbers(numbers);

        System.out.println("Sum of Even Numbers: " + sumEvenNumbers(numbers));

        System.out.println("Sorted Descending: " + sortDescending(numbers));
        System.out.println("Squared Numbers: " + squareNumbers(numbers));
        System.out.println("Filtered (Not Odd & >10): " + filterOddAndGreaterThan10(numbers));

    }

    /**
     * Filters even numbers from the given list.
     * @param numbers List of integers.
     * @return List containing only even numbers.
     */
    public static List<Integer> filterEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 == 0) // Keep only numbers divisible by 2 (even numbers)
                .collect(Collectors.toList()); // Collect results into a new list
    }

    /**
     * Filters odd numbers from the given list.
     * @param numbers List of integers.
     * @return List containing only odd numbers.
     */
    public static List<Integer> filterOddNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 != 0) // Keep only numbers NOT divisible by 2 (odd numbers)
                .collect(Collectors.toList());
    }

    /**
     * Filters numbers that are both greater than 5 and even.
     * @param numbers List of integers.
     * @return List containing numbers greater than 5 and even.
     */
    public static List<Integer> filterGreaterThan5AndEven(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n > 5 && n % 2 == 0) // Keep only numbers > 5 and even
                .collect(Collectors.toList());
    }

    /**
     * Prints each even number from the list on a new line using forEach.
     * @param numbers List of integers.
     */
    public static void printEvenNumbers(List<Integer> numbers) {
        numbers.stream()
                .filter(n -> n % 2 == 0) // Keep only even numbers
                .forEach(System.out::println); // Print each even number on a new line
    }

    /**
     * Finds the sum of all even numbers in the given list using the reduce method.
     * @param numbers List of integers.
     * @return The sum of all even numbers.
     */
    public static int sumEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 == 0) // Keep only even numbers
                .reduce(0, Integer::sum); // Sum up all even numbers starting from 0
    }

    /**
     * Sorts a list of numbers in descending order.
     * @param numbers List of integers.
     * @return A new list sorted in descending order.
     */
    public static List<Integer> sortDescending(List<Integer> numbers) {
        return numbers.stream()
                .sorted((a, b) -> Integer.compare(b, a)) // Sort in descending order
                .collect(Collectors.toList());
    }


    /**
     * Uses the map function to create a new list where each number is squared.
     * @param numbers List of integers.
     * @return A list where each number is squared.
     */
    public static List<Integer> squareNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(n -> n * n) // Square each number
                .collect(Collectors.toList());
    }


    /**
     * Filters out numbers that are both odd and greater than 10.
     * @param numbers List of integers.
     * @return A list that excludes numbers that are both odd and greater than 10.
     */
    public static List<Integer> filterOddAndGreaterThan10(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> !(n % 2 != 0 && n > 10)) // Exclude numbers that are both odd and >10
                .collect(Collectors.toList());
    }

}