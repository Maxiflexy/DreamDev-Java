package practice.exercise.day02.task5;

public class BankAccount {

    private final String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("\nSuccessfully deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be greater than zero.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("\nSuccessfully withdrew: $" + amount);
        } else if (amount > balance) {
            System.out.println("\nInsufficient balance.");
        } else {
            System.out.println("\nWithdrawal amount must be greater than zero.");
        }
    }

    public void displayBalance() {
        System.out.println("\n\nAccount Holder: " + accountHolder);
        System.out.println("Current Balance: $" + balance);
    }

}
