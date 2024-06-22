import java.util.ArrayList;
import java.util.Scanner;

// Class to represent a Bank Account
class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactionHistory;

    // Constructor to initialize the bank account
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add("Initial balance: " + initialBalance);
    }

    // Method to get the account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Method to get the account holder's name
    public String getAccountHolder() {
        return accountHolder;
    }

    // Method to get the current balance
    public double getBalance() {
        return balance;
    }

    // Method to get the transaction history
    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    // Method to deposit funds into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposit: +" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw funds from the account
    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                transactionHistory.add("Withdrawal: -" + amount);
            } else {
                System.out.println("Insufficient funds!");
            }
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    // Method to transfer funds to another account
    public void transfer(BankAccount receiver, double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                receiver.deposit(amount);
                transactionHistory.add("Transfer to " + receiver.getAccountNumber() + ": -" + amount);
            } else {
                System.out.println("Insufficient funds!");
            }
        } else {
            System.out.println("Invalid transfer amount.");
        }
    }

    // Method to print transaction history
    public void printTransactionHistory() {
        System.out.println("Transaction History for Account " + accountNumber);
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

// Class to represent the online banking system
public class onlinebankingsystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create two sample bank accounts
        BankAccount account1 = new BankAccount("123", "Srinivas", 1000.0);
        BankAccount account2 = new BankAccount("456", "Sai", 1500.0);

        // Simple menu for user interaction
        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: $" + account1.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    account1.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    account1.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient's account number: ");
                    String recipientAccountNumber = scanner.next();
                    BankAccount recipientAccount = (recipientAccountNumber.equals(account2.getAccountNumber())) ? account2 : null;

                    if (recipientAccount != null) {
                        System.out.print("Enter transfer amount: $");
                        double transferAmount = scanner.nextDouble();
                        account1.transfer(recipientAccount, transferAmount);
                    } else {
                        System.out.println("Recipient account not found!");
                    }
                    break;
                case 5:
                    account1.printTransactionHistory();
                    break;
                case 6:
                    System.out.println("Exiting Online Banking System. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
