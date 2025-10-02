package com.bankapp;

public class Account {
    // Instance variables are 'private' to protect them from outside access.
    // This is Encapsulation.
    private String accountNumber;
    private String ownerName;
    private double balance;

    // Constructor: A special method for creating new 'Account' objects.
    public Account(String accountNumber, String ownerName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialDeposit;
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
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Public method to withdraw money.
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
        }
    }
}