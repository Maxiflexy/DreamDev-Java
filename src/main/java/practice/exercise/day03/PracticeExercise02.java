package practice.exercise.day03;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * This class demonstrates the usage of Predicate and Consumer functional interfaces.
 * It includes methods to check number properties, string conditions, and apply consumer operations.
 */
public class PracticeExercise02 {

    /**
     * Main method to test various functional methods defined in this class.
     */
    public static void main(String[] args) {
        boolean result1 = checkIfANumberIsEven(13);
        System.out.println(result1);

        boolean result2 = checkIfAStringIsEmpty("");
        System.out.println(result2);

        boolean result3 = checkIfANumberIsGreaterThan10AndEven(12);
        System.out.println(result3);

        boolean result4 = checkIfANumberIsNotGreaterThan10(8);
        System.out.println(result4);

        consumerMethodThatPrintsTheInputName("Max_CAPACITY");

        consumerMethodThatPrintsTheSquareOfTheInput(16);

        List<Object> names = Arrays.asList("Alice", "Bob", "Charlie");
        consumerMethodThatPrintsAllElementsOfAList(names);

        combinedConsumerMethodThatPrintsNameAndItsLength("DreamDev!!");

        chainTwoConsumerOperationToPrintNameAndUppercase("Coding is so Fun!");
    }

    /**
     * Checks if a given number is even using a Predicate.
     * @param number The input number.
     * @return true if the number is even, false otherwise.
     */
    public static boolean checkIfANumberIsEven(int number) {
        Predicate<Integer> isEven = num -> num % 2 == 0;
        return isEven.test(number);
    }

    /**
     * Checks if a given string is empty using a Predicate.
     * @param input The input string.
     * @return true if the string is empty, false otherwise.
     */
    public static boolean checkIfAStringIsEmpty(String input) {
        Predicate<String> isEmpty = String::isEmpty;
        return isEmpty.test(input);
    }

    /**
     * Checks if a number is greater than 10 and even using Predicate composition.
     * @param number The input number.
     * @return true if the number is greater than 10 and even, false otherwise.
     */
    public static boolean checkIfANumberIsGreaterThan10AndEven(int number) {
        Predicate<Integer> isEven = num -> num % 2 == 0;
        Predicate<Integer> isGreaterThan10 = num -> num > 10;
        Predicate<Integer> isEvenAndGreaterThan10 = isEven.and(isGreaterThan10);
        return isEvenAndGreaterThan10.test(number);
    }

    /**
     * Checks if a number is NOT greater than 10 using Predicate.negate().
     * @param number The input number.
     * @return true if the number is not greater than 10, false otherwise.
     */
    public static boolean checkIfANumberIsNotGreaterThan10(int number) {
        Predicate<Integer> isGreaterThan10 = num -> num > 10;
        Predicate<Integer> isNotGreaterThan10 = isGreaterThan10.negate();
        return isNotGreaterThan10.test(number);
    }

    /**
     * Consumer method that prints a greeting message with the given name.
     * @param inputName The name to be included in the greeting.
     */
    public static void consumerMethodThatPrintsTheInputName(String inputName) {
        Consumer<String> greet = name -> System.out.println("Hello, " + name + ", Welcome to DreamDev Program..!");
        greet.accept(inputName);
    }

    /**
     * Consumer method that prints the square of the given number.
     * @param number The input number.
     */
    public static void consumerMethodThatPrintsTheSquareOfTheInput(int number) {
        Consumer<Integer> printSquare = num -> System.out.println("Square: " + (num * num));
        printSquare.accept(number);
    }

    /**
     * Consumer method that prints all elements of a given list.
     * @param inputList The list of elements to be printed.
     */
    public static void consumerMethodThatPrintsAllElementsOfAList(List<Object> inputList) {
        Consumer<Object> printName = System.out::println;
        inputList.forEach(printName);
    }

    /**
     * Consumer method that prints a name and its length using Consumer chaining.
     * @param input The input string (name).
     */
    public static void combinedConsumerMethodThatPrintsNameAndItsLength(String input) {
        Consumer<String> printName = name -> System.out.println("Name: " + name);
        Consumer<String> printLength = name -> System.out.println("Length: " + name.length());
        Consumer<String> printNameAndLength = printName.andThen(printLength);
        printNameAndLength.accept(input);
    }

    /**
     * Consumer method that prints a name and its uppercase version using Consumer chaining.
     * @param input The input string.
     */
    public static void chainTwoConsumerOperationToPrintNameAndUppercase(String input) {
        Consumer<String> printName = name -> System.out.println("Original: " + name);
        Consumer<String> printUpperCase = name -> System.out.println("Uppercase: " + name.toUpperCase());
        Consumer<String> printBoth = printName.andThen(printUpperCase);
        printBoth.accept(input);
    }
}