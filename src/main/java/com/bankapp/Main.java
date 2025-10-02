package com.bankapp;

import java.util.Scanner;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        // Create some sample accounts
        bank.addAccount(new Account("ACC001", "Alice", 500.0));
        bank.addAccount(new Account("ACC002", "Bob", 1200.0));

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Simple Banking App ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        handleDeposit(scanner, bank);
                        break;
                    case 2:
                        handleWithdrawal(scanner, bank);
                        break;
                    case 3:
                        handleCheckBalance(scanner, bank);
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Thank you for using the banking app!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            } catch (java.util.NoSuchElementException e) {
                System.out.println("No input available. Exiting application.");
                exit = true;
            }
        }
        scanner.close();
    }

    private static void handleDeposit(Scanner scanner, Bank bank) {
        try {
            System.out.print("Enter account number: ");
            String accNum = scanner.nextLine();
            Optional<Account> accountOpt = bank.findAccount(accNum);

            if (accountOpt.isPresent()) {
                System.out.print("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                accountOpt.get().deposit(amount);
            } else {
                System.out.println("Account not found.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine(); // Clear the invalid input
        } catch (java.util.NoSuchElementException e) {
            System.out.println("No input available. Returning to main menu.");
        }
    }

    private static void handleWithdrawal(Scanner scanner, Bank bank) {
        try {
            System.out.print("Enter account number: ");
            String accNum = scanner.nextLine();
            Optional<Account> accountOpt = bank.findAccount(accNum);

            if (accountOpt.isPresent()) {
                System.out.print("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();
                accountOpt.get().withdraw(amount);
            } else {
                System.out.println("Account not found.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine(); // Clear the invalid input
        } catch (java.util.NoSuchElementException e) {
            System.out.println("No input available. Returning to main menu.");
        }
    }

    private static void handleCheckBalance(Scanner scanner, Bank bank) {
        try {
            System.out.print("Enter account number: ");
            String accNum = scanner.nextLine();
            bank.findAccount(accNum)
                    .ifPresentOrElse(
                            account -> System.out.println("Current balance is: " + account.getBalance()),
                            () -> System.out.println("Account not found.")
                    );
        } catch (java.util.NoSuchElementException e) {
            System.out.println("No input available. Returning to main menu.");
        }
    }
}