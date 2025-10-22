package com.bankapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    private Bank bank;
    private Account account1;
    private Account account2;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        account1 = new Account("ACC001", "Alice", 500.0);
        account2 = new Account("ACC002", "Bob", 1000.0);
    }

    @Test
    @DisplayName("Should add accounts to the bank")
    void testAddAccount() {
        bank.addAccount(account1);
        bank.addAccount(account2);
        
        assertEquals(2, bank.getAccountCount(), "Bank should have 2 accounts");
        assertTrue(bank.findAccount("ACC001").isPresent(), "Should find ACC001");
        assertTrue(bank.findAccount("ACC002").isPresent(), "Should find ACC002");
    }

    @Test
    @DisplayName("Should find existing account by number")
    void testFindExistingAccount() {
        bank.addAccount(account1);
        
        assertTrue(bank.findAccount("ACC001").isPresent(), "Should find existing account");
        assertEquals("Alice", bank.findAccount("ACC001").get().getOwnerName(), "Should return correct account");
    }

    @Test
    @DisplayName("Should not find non-existing account")
    void testFindNonExistingAccount() {
        bank.addAccount(account1);
        
        assertFalse(bank.findAccount("NONEXISTENT").isPresent(), "Should not find non-existing account");
    }

    @Test
    @DisplayName("Should return all accounts")
    void testGetAllAccounts() {
        bank.addAccount(account1);
        bank.addAccount(account2);
        
        var allAccounts = bank.getAllAccounts();
        assertEquals(2, allAccounts.size(), "Should return 2 accounts");
        assertTrue(allAccounts.contains(account1), "Should contain account1");
        assertTrue(allAccounts.contains(account2), "Should contain account2");
    }

    @Test
    @DisplayName("Should delete existing account")
    void testDeleteExistingAccount() {
        bank.addAccount(account1);
        bank.addAccount(account2);
        
        assertTrue(bank.deleteAccount("ACC001"), "Should successfully delete account");
        assertEquals(1, bank.getAccountCount(), "Should have 1 account remaining");
        assertFalse(bank.findAccount("ACC001").isPresent(), "Should not find deleted account");
        assertTrue(bank.findAccount("ACC002").isPresent(), "Should still find other account");
    }

    @Test
    @DisplayName("Should not delete non-existing account")
    void testDeleteNonExistingAccount() {
        bank.addAccount(account1);
        
        assertFalse(bank.deleteAccount("NONEXISTENT"), "Should not delete non-existing account");
        assertEquals(1, bank.getAccountCount(), "Should still have 1 account");
    }

    @Test
    @DisplayName("Should calculate total bank balance correctly")
    void testGetTotalBankBalance() {
        bank.addAccount(account1); // 500.0
        bank.addAccount(account2); // 1000.0
        
        assertEquals(1500.0, bank.getTotalBankBalance(), "Total balance should be 1500.0");
    }

    @Test
    @DisplayName("Should return zero balance for empty bank")
    void testGetTotalBankBalanceEmpty() {
        assertEquals(0.0, bank.getTotalBankBalance(), "Empty bank should have zero balance");
    }

    @Test
    @DisplayName("Should return zero count for empty bank")
    void testGetAccountCountEmpty() {
        assertEquals(0, bank.getAccountCount(), "Empty bank should have zero accounts");
    }
}
