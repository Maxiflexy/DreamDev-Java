package practice.exercise.day03;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PracticeExercise02 {

    /**
     * Here's a Java implementation for each of your requests using Predicate and
     * Consumer functional interfaces from java.util.function.
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

    public static boolean checkIfANumberIsEven(int number){
        // Predicate to check if a number is even
        Predicate<Integer> isEven = num -> num % 2 == 0;
        return isEven.test(number);
    }

    public static boolean checkIfAStringIsEmpty(String input){
        // Predicate to check if a string is empty
        Predicate<String> isEmpty = String::isEmpty;
        return isEmpty.test(input);
    }

    public static boolean checkIfANumberIsGreaterThan10AndEven(int number){
        Predicate<Integer> isEven = num -> num % 2 == 0;
        Predicate<Integer> isGreaterThan10 = num -> num > 10;

        // Combined Predicate: Number should be greater than 10 and even
        Predicate<Integer> isEvenAndGreaterThan10 = isEven.and(isGreaterThan10);

        return isEvenAndGreaterThan10.test(number);
    }

    public static boolean checkIfANumberIsNotGreaterThan10(int number){
        Predicate<Integer> isGreaterThan10 = num -> num > 10;

        // Negate the predicate: Number should NOT be greater than 10
        Predicate<Integer> isNotGreaterThan10 = isGreaterThan10.negate();

        return isNotGreaterThan10.test(number);
    }

    public static void consumerMethodThatPrintsTheInputName(String inputName){
        Consumer<String> greet = name -> System.out.println("Hello, " + name + ", Welcome to DreamDev Program..!");
        greet.accept(inputName);
    }

    public static void consumerMethodThatPrintsTheSquareOfTheInput(int number){
        Consumer<Integer> printSquare = num -> System.out.println("Square: " + (num * num));
        printSquare.accept(number);
    }

    public static void consumerMethodThatPrintsAllElementsOfAList(List<Object> inputList){
        Consumer<Object> printName = System.out::println;
        inputList.forEach(printName);
    }

    public static void combinedConsumerMethodThatPrintsNameAndItsLength(String input){
        Consumer<String> printName = name -> System.out.println("Name: " + name);
        Consumer<String> printLength = name -> System.out.println("Length: " + name.length());

        // Combined Consumer
        Consumer<String> printNameAndLength = printName.andThen(printLength);
        printNameAndLength.accept(input);
    }

    public static void chainTwoConsumerOperationToPrintNameAndUppercase(String input){
        Consumer<String> printName = name -> System.out.println("Original: " + name);
        Consumer<String> printUpperCase = name -> System.out.println("Uppercase: " + name.toUpperCase());

        // Chain Consumers using andThen()
        Consumer<String> printBoth = printName.andThen(printUpperCase);
        printBoth.accept(input);
    }
}
