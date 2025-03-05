package practice.exercise.day02.task5;

import java.util.Scanner;

public class BankingApp {

    public static void main(String[] args) {

        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        // Create a bank account
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        BankAccount account = new BankAccount(name, initialDeposit);

        while (true) {
            System.out.println("\n\nBanking System Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Display Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("\nEnter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    account.displayBalance();
                    break;
                case 4:
                    System.out.println("\nExiting... Thank you for using our banking system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("\nInvalid option! Please choose a valid menu option.");
            }
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("=============================================================");
        System.out.println("          WELCOME TO DREAM-DEVs BANKING APPLICATION    ");
        System.out.println("=============================================================\n");
    }
}
