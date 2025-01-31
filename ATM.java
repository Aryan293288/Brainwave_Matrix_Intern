import java.util.Scanner;

public class ATM {
    private double balance;
    private final Scanner scanner;

    public ATM() {
        this.balance = 0.0;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f\n", balance);
    }

    private void depositMoney() {
        System.out.print("Enter the amount to deposit: $");
        double amount = getPositiveAmount();
        balance += amount;
        System.out.printf("$%.2f has been deposited. Your new balance is $%.2f\n", amount, balance);
    }

    private void withdrawMoney() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = getPositiveAmount();

        if (amount > balance) {
            System.out.println("Insufficient funds. Withdrawal failed.");
        } else {
            balance -= amount;
            System.out.printf("$%.2f has been withdrawn. Your new balance is $%.2f\n", amount, balance);
        }
    }

    private double getPositiveAmount() {
        double amount;
        while (true) {
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid input. Please enter a valid amount: $");
                scanner.next();
            }
            amount = scanner.nextDouble();

            if (amount > 0) {
                break;
            } else {
                System.out.print("Amount must be positive. Please enter again: $");
            }
        }
        return amount;
    }
}
