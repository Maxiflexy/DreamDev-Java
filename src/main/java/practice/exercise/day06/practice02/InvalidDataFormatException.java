package practice.exercise.day06.practice02;

/**
 * Custom exception for invalid data format
 */
class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }

    public InvalidDataFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
