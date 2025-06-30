import java.util.*;

public class BankSystem {
    public static void main(String[] args) {
        Account acc = new Account("123456", "John Doe");
        acc.deposit(5000);
        acc.withdraw(1200);
        acc.deposit(300);
        acc.withdraw(7000);
        acc.printStatement();
    }
}

class Account {
    private String accountNumber;
    private String holderName;
    private double balance;
    private List<String> transactionHistory;

    public Account(String accountNumber, String holderName) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount);
        } else {
            transactionHistory.add("Failed Withdrawal Attempt: $" + amount);
        }
    }

    public void printStatement() {
        System.out.println("Account Statement for " + holderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
        System.out.println("Transactions:");
        for (String transaction : transactionHistory) {
            System.out.println("- " + transaction);
        }
    }
}
