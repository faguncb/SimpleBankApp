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
}

