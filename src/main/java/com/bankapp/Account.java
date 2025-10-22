package com.bankapp;

import java.util.ArrayList;
import java.util.List;

public class Account {
    // Instance variables are 'private' to protect them from outside access.
    // This is Encapsulation.
    private String accountNumber;
    private String ownerName;
    private double balance;
    private List<String> transactionHistory;

    // Constructor: A special method for creating new 'Account' objects.
    public Account(String accountNumber, String ownerName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        if (initialDeposit > 0) {
            this.transactionHistory.add("Initial deposit: $" + String.format("%.2f", initialDeposit));
        }
    }

    // Public method (getter) to safely access the balance.
    public double getBalance() {
        return balance;
    }

    // Public method (getter) for the account number.
    public String getAccountNumber() {
        return accountNumber;
    }

    // Public method (getter) for the owner name.
    public String getOwnerName() {
        return ownerName;
    }

    // Public method to deposit money.
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposit: $" + String.format("%.2f", amount) + " | New balance: $" + String.format("%.2f", balance));
            System.out.println("✅ Deposit successful. New balance: $" + String.format("%.2f", balance));
        } else {
            System.out.println("❌ Deposit amount must be positive.");
        }
    }

    // Public method to withdraw money.
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Withdrawal amount must be positive.");
        } else if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawal: $" + String.format("%.2f", amount) + " | New balance: $" + String.format("%.2f", balance));
            System.out.println("✅ Withdrawal successful. New balance: $" + String.format("%.2f", balance));
        } else {
            System.out.println("❌ Withdrawal failed. Insufficient funds. Current balance: $" + String.format("%.2f", balance));
        }
    }

    // Method to get transaction history.
    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory); // Return a copy to prevent external modification
    }

    // Method to get account summary.
    public String getAccountSummary() {
        return String.format("Account: %s | Holder: %s | Balance: $%.2f | Transactions: %d", 
                accountNumber, ownerName, balance, transactionHistory.size());
    }
}