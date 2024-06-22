import java.util.Scanner;
import java.util.ArrayList;

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User("Goutam123", "1856");
        ATMOperations atmOperations = new ATMOperations(user);

        System.out.println("Welcome to the ATM!");
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (user.validate(userID, pin)) {
            int choice;
            do {
                System.out.println("\nATM Menu:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                System.out.print("Enter your choice: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Clear invalid input
                }
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        atmOperations.showTransactionHistory();
                        break;
                    case 2:
                        atmOperations.withdraw(scanner);
                        break;
                    case 3:
                        atmOperations.deposit(scanner);
                        break;
                    case 4:
                        atmOperations.transfer(scanner);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);
        } else {
            System.out.println("Invalid User ID or PIN.");
        }

        scanner.close();
    }
}

class User {
    private String userID;
    private String pin;

    public User(String userID, String pin) {
        this.userID = userID;
        this.pin = pin;
    }

    public boolean validate(String userID, String pin) {
        return this.userID.equals(userID) && this.pin.equals(pin);
    }
}

class ATMOperations {
    private User user;
    private double balance;
    private ArrayList<String> transactionHistory;

    public ATMOperations(User user) {
        this.user = user;
        this.balance = 1000.0; // Starting balance for the example
        this.transactionHistory = new ArrayList<>();
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    public void withdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.next(); // Clear invalid input
        }
        double amount = scanner.nextDouble();
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            transactionHistory.add("Withdraw: ₹" + amount);
            System.out.println("Withdrawn: ₹" + amount);
        }
    }

    public void deposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.next(); // Clear invalid input
        }
        double amount = scanner.nextDouble();
        balance += amount;
        transactionHistory.add("Deposit: ₹" + amount);
        System.out.println("Deposited: ₹" + amount);
    }

    public void transfer(Scanner scanner) {
        System.out.print("Enter recipient User ID: ");
        String recipientID = scanner.next();
        System.out.print("Enter amount to transfer: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.next(); // Clear invalid input
        }
        double amount = scanner.nextDouble();
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            transactionHistory.add("Transfer to " + recipientID + ": ₹" + amount);
            System.out.println("Transferred: ₹" + amount + " to " + recipientID);
        }
    }
}
