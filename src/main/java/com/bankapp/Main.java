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
            System.out.println("\n" + "=".repeat(50));
            System.out.println("🏦 WELCOME TO SIMPLE BANKING APP 🏦");
            System.out.println("=".repeat(50));
            System.out.println("1. 💰 Deposit Money");
            System.out.println("2. 💸 Withdraw Money");
            System.out.println("3. 📊 Check Balance");
            System.out.println("4. 👤 Create New Account");
            System.out.println("5. 📋 View All Accounts");
            System.out.println("6. 🗑️  Delete Account");
            System.out.println("7. 📈 View Account Details");
            System.out.println("8. ❌ Exit");
            System.out.println("=".repeat(50));
            System.out.print("Choose an option (1-8): ");

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
                        handleCreateAccount(scanner, bank);
                        break;
                    case 5:
                        handleViewAllAccounts(bank);
                        break;
                    case 6:
                        handleDeleteAccount(scanner, bank);
                        break;
                    case 7:
                        handleViewAccountDetails(scanner, bank);
                        break;
                    case 8:
                        exit = true;
                        System.out.println("\n🎉 Thank you for using the Simple Banking App! 🎉");
                        System.out.println("Have a great day! 👋");
                        break;
                    default:
                        System.out.println("❌ Invalid option. Please choose a number between 1-8.");
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
                            account -> System.out.println("💰 Current balance is: $" + String.format("%.2f", account.getBalance())),
                            () -> System.out.println("❌ Account not found.")
                    );
        } catch (java.util.NoSuchElementException e) {
            System.out.println("No input available. Returning to main menu.");
        }
    }

    private static void handleCreateAccount(Scanner scanner, Bank bank) {
        try {
            System.out.println("\n📝 Creating New Account");
            System.out.println("-".repeat(30));
            
            System.out.print("Enter account number: ");
            String accNum = scanner.nextLine().trim();
            
            if (accNum.isEmpty()) {
                System.out.println("❌ Account number cannot be empty.");
                return;
            }
            
            // Check if account already exists
            if (bank.findAccount(accNum).isPresent()) {
                System.out.println("❌ Account with this number already exists.");
                return;
            }
            
            System.out.print("Enter account holder name: ");
            String name = scanner.nextLine().trim();
            
            if (name.isEmpty()) {
                System.out.println("❌ Account holder name cannot be empty.");
                return;
            }
            
            System.out.print("Enter initial deposit amount: $");
            double initialDeposit = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            if (initialDeposit < 0) {
                System.out.println("❌ Initial deposit cannot be negative.");
                return;
            }
            
            Account newAccount = new Account(accNum, name, initialDeposit);
            bank.addAccount(newAccount);
            System.out.println("✅ Account created successfully!");
            System.out.println("📊 Account Details:");
            System.out.println("   Account Number: " + newAccount.getAccountNumber());
            System.out.println("   Holder Name: " + newAccount.getOwnerName());
            System.out.println("   Initial Balance: $" + String.format("%.2f", newAccount.getBalance()));
            
        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Invalid input. Please enter a valid amount.");
            scanner.nextLine();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("No input available. Returning to main menu.");
        }
    }

    private static void handleViewAllAccounts(Bank bank) {
        System.out.println("\n📋 All Accounts in the Bank");
        System.out.println("-".repeat(50));
        
        if (bank.getAllAccounts().isEmpty()) {
            System.out.println("📭 No accounts found in the bank.");
        } else {
            System.out.printf("%-15s %-20s %-15s%n", "Account #", "Holder Name", "Balance");
            System.out.println("-".repeat(50));
            
            for (Account account : bank.getAllAccounts()) {
                System.out.printf("%-15s %-20s $%-15.2f%n", 
                    account.getAccountNumber(), 
                    account.getOwnerName(), 
                    account.getBalance());
            }
        }
    }

    private static void handleDeleteAccount(Scanner scanner, Bank bank) {
        try {
            System.out.println("\n🗑️  Delete Account");
            System.out.println("-".repeat(30));
            
            System.out.print("Enter account number to delete: ");
            String accNum = scanner.nextLine().trim();
            
            Optional<Account> accountOpt = bank.findAccount(accNum);
            if (accountOpt.isPresent()) {
                Account account = accountOpt.get();
                System.out.println("⚠️  Account Details:");
                System.out.println("   Account Number: " + account.getAccountNumber());
                System.out.println("   Holder Name: " + account.getOwnerName());
                System.out.println("   Current Balance: $" + String.format("%.2f", account.getBalance()));
                
                System.out.print("Are you sure you want to delete this account? (yes/no): ");
                String confirmation = scanner.nextLine().trim().toLowerCase();
                
                if (confirmation.equals("yes") || confirmation.equals("y")) {
                    if (bank.deleteAccount(accNum)) {
                        System.out.println("✅ Account deleted successfully!");
                    } else {
                        System.out.println("❌ Failed to delete account.");
                    }
                } else {
                    System.out.println("❌ Account deletion cancelled.");
                }
            } else {
                System.out.println("❌ Account not found.");
            }
        } catch (java.util.NoSuchElementException e) {
            System.out.println("No input available. Returning to main menu.");
        }
    }

    private static void handleViewAccountDetails(Scanner scanner, Bank bank) {
        try {
            System.out.println("\n📈 Account Details");
            System.out.println("-".repeat(30));
            
            System.out.print("Enter account number: ");
            String accNum = scanner.nextLine().trim();
            
            bank.findAccount(accNum)
                .ifPresentOrElse(
                    account -> {
                        System.out.println("✅ Account Found!");
                        System.out.println("📊 Account Details:");
                        System.out.println("   Account Number: " + account.getAccountNumber());
                        System.out.println("   Holder Name: " + account.getOwnerName());
                        System.out.println("   Current Balance: $" + String.format("%.2f", account.getBalance()));
                        System.out.println("   Account Status: Active");
                    },
                    () -> System.out.println("❌ Account not found.")
                );
        } catch (java.util.NoSuchElementException e) {
            System.out.println("No input available. Returning to main menu.");
        }
    }
}