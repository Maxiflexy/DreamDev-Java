package practice.exercise.day03;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PracticeExercise03 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 2, 6, 8, 6, 10);
        List<String> words = Arrays.asList("Apple", "Banana", "Avocado", "apricot", "Cherry");

        System.out.println("Sum of squares of even numbers: " + sumOfSquaresOfEvenNumbers(numbers));

        Optional<Integer> max = findMaxNumber(numbers);
        max.ifPresent(value -> System.out.println("Maximum number: " + value));

        System.out.println("Uppercase words: " + convertToUpperCase(words));

        System.out.println("Words starting with A: " + countWordsStartingWithA(words));

        System.out.println("List without duplicates: " + removeDuplicates(numbers));
    }

    public static int sumOfSquaresOfEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(num -> num % 2 == 0) // Filter even numbers
                .map(num -> num * num) // Square each number
                .reduce(0, Integer::sum); // Sum all squares
    }

    public static Optional<Integer> findMaxNumber(List<Integer> numbers) {
        return numbers.stream()
                .reduce(Integer::max); // Find the maximum using reduce
    }

    public static List<String> convertToUpperCase(List<String> words) {
        return words.stream()
                .map(String::toUpperCase) // Convert each string to uppercase
                .collect(Collectors.toList()); // Collect as a list
    }

    public static long countWordsStartingWithA(List<String> words) {
        return words.stream()
                .filter(word -> word.startsWith("A") || word.startsWith("a")) // Case-insensitive check
                .count(); // Count matching words
    }

    public static List<Integer> removeDuplicates(List<Integer> numbers) {
        return numbers.stream()
                .distinct() // Remove duplicates
                .collect(Collectors.toList()); // Collect unique values
    }
}