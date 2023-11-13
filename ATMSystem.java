import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

class Transaction {
    private Date date;
    private String description;
    private double amount;

    public Transaction(String description, double amount) {
        this.date = new Date();
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return date + "\t" + description + "\t$" + amount;
    }
}

class Account {
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account() {
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void transfer(Account targetAccount, double amount) {
        if (balance >= amount) {
            balance -= amount;
            targetAccount.deposit(amount);
            transactions.add(new Transaction("Transfer to " + targetAccount, amount));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}

class User {
    private String userId;
    private String userPin;
    private Account account;

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.account = new Account();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public Account getAccount() {
        return account;
    }
}

class TransactionHistory {
    public static void printTransactionHistory(User user) {
        System.out.println("\nTransaction History for User: " + user.getUserId());
        System.out.println("Date\t\tDescription\tAmount");
        System.out.println("---------------------------------------------");
        for (Transaction transaction : user.getAccount().getTransactions()) {
            System.out.println(transaction);
        }
        System.out.println("<--------------------------------------------->");
    }
}

class ATMSystem {
    public static void main(String[] args) {
        // Create a sample user
        User user = new User("Sudha", "1404");

        // Simulate ATM login
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        String enteredUserId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        if (enteredUserId.equals(user.getUserId()) && enteredPin.equals(user.getUserPin())) {
            System.out.println("Login successful!\n");

            int choice;
            do {
                System.out.println("ATM Menu:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        TransactionHistory.printTransactionHistory(user);
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        user.getAccount().withdraw(withdrawalAmount);
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        user.getAccount().deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter target user ID for transfer: ");
                        String targetUserId = scanner.next();
                        User targetUser = new User(targetUserId, "");
                        System.out.print("Enter transfer amount: $");
                        double transferAmount = scanner.nextDouble();
                        user.getAccount().transfer(targetUser.getAccount(), transferAmount);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

            } while (choice != 5);
        } else {
            System.out.println("Login failed.Incorrect User ID or PIN.");
        }

        scanner.close();
    }
}
