package com.bankapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;

    // This method runs before each test, ensuring a fresh start.
    @BeforeEach
    void setUp() {
        account = new Account("12345", "John Doe", 100.0);
    }

    @Test
    @DisplayName("Should correctly deposit a positive amount")
    void testSuccessfulDeposit() {
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), "The balance should be 150.0 after deposit.");
    }

    @Test
    @DisplayName("Should correctly withdraw with sufficient funds")
    void testSuccessfulWithdrawal() {
        account.withdraw(30.0);
        assertEquals(70.0, account.getBalance(), "The balance should be 70.0 after withdrawal.");
    }

    @Test
    @DisplayName("Should not change balance on withdrawal with insufficient funds")
    void testInsufficientFundsWithdrawal() {
        account.withdraw(200.0);
        assertEquals(100.0, account.getBalance(), "The balance should remain 100.0 if funds are insufficient.");
    }

    @Test
    @DisplayName("Should track transaction history correctly")
    void testTransactionHistory() {
        // Initial deposit should be in history
        assertEquals(1, account.getTransactionHistory().size(), "Should have initial deposit in history");
        assertTrue(account.getTransactionHistory().get(0).contains("Initial deposit"), "History should contain initial deposit");

        // Deposit should be added to history
        account.deposit(50.0);
        assertEquals(2, account.getTransactionHistory().size(), "Should have 2 transactions after deposit");
        assertTrue(account.getTransactionHistory().get(1).contains("Deposit"), "History should contain deposit");

        // Withdrawal should be added to history
        account.withdraw(30.0);
        assertEquals(3, account.getTransactionHistory().size(), "Should have 3 transactions after withdrawal");
        assertTrue(account.getTransactionHistory().get(2).contains("Withdrawal"), "History should contain withdrawal");
    }

    @Test
    @DisplayName("Should provide correct account summary")
    void testAccountSummary() {
        String summary = account.getAccountSummary();
        assertTrue(summary.contains("12345"), "Summary should contain account number");
        assertTrue(summary.contains("John Doe"), "Summary should contain owner name");
        assertTrue(summary.contains("100.00"), "Summary should contain balance");
        assertTrue(summary.contains("Transactions: 1"), "Summary should contain transaction count");
    }

    @Test
    @DisplayName("Should handle negative deposit amounts")
    void testNegativeDeposit() {
        double originalBalance = account.getBalance();
        account.deposit(-50.0);
        assertEquals(originalBalance, account.getBalance(), "Balance should not change with negative deposit");
    }

    @Test
    @DisplayName("Should handle zero withdrawal amount")
    void testZeroWithdrawal() {
        double originalBalance = account.getBalance();
        account.withdraw(0.0);
        assertEquals(originalBalance, account.getBalance(), "Balance should not change with zero withdrawal");
    }
}

