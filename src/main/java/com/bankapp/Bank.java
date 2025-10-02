package com.bankapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {
    // Using a List to hold multiple Account objects.
    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    // Method to add a new account to the bank.
    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account created for " + account.getAccountNumber());
    }

    // Method to find an account by its number.
    // 'Optional' is a modern Java feature to handle cases where a value might be null.
    public Optional<Account> findAccount(String accountNumber) {
        // Using Java Streams API for a clean search.
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst();
    }
}