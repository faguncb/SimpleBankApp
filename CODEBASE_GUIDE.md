# 🏦 Simple Banking App - Complete Codebase Guide

## 📚 Table of Contents
1. [Project Overview](#project-overview)
2. [Project Structure](#project-structure)
3. [Build Configuration](#build-configuration)
4. [Core Classes Explained](#core-classes-explained)
5. [How Everything Works Together](#how-everything-works-together)
6. [Testing Strategy](#testing-strategy)
7. [Key Programming Concepts](#key-programming-concepts)
8. [Step-by-Step Code Walkthrough](#step-by-step-code-walkthrough)

---

## 🎯 Project Overview

The **Simple Banking App** is a command-line application that simulates a basic banking system. It demonstrates fundamental Java programming concepts through a practical, real-world example.

### What This App Does:
- ✅ Create and manage bank accounts
- ✅ Deposit and withdraw money
- ✅ Check account balances
- ✅ View transaction history
- ✅ Delete accounts
- ✅ Display all accounts in the bank

---

## 📁 Project Structure

```
SimpleBankApp/
├── build.gradle              # Build configuration file
├── .gitignore               # Files to ignore in version control
├── README.md                # Project documentation
├── CODEBASE_GUIDE.md        # This comprehensive guide
└── src/
    ├── main/
    │   └── java/com/bankapp/
    │       ├── Account.java  # Account class (data model)
    │       ├── Bank.java     # Bank class (account manager)
    │       └── Main.java     # Main application (user interface)
    └── test/
        └── java/com/bankapp/
            ├── AccountTest.java  # Tests for Account class
            └── BankTest.java     # Tests for Bank class
```

### Why This Structure?
- **`src/main/java/`** - Contains the actual application code
- **`src/test/java/`** - Contains test code to verify our application works
- **`com.bankapp`** - Package name following Java naming conventions
- **`build.gradle`** - Tells Gradle how to build and run our project

---

## ⚙️ Build Configuration

### `build.gradle` File Explained

```gradle
plugins {
    id 'java'        // Enables Java compilation
    id 'application' // Enables running the app with 'gradle run'
}

repositories {
    mavenCentral()   // Where to download dependencies from
}

dependencies {
    // JUnit 5 for testing
    testImplementation 'org.junit.jupiter:junit-jupiter:5.8.1'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

application {
    mainClass = 'com.bankapp.Main'  // Which class contains main() method
}

test {
    useJUnitPlatform()  // Use JUnit 5 for testing
}
```

**What This Means:**
- **Plugins**: Tell Gradle what capabilities we need
- **Dependencies**: External libraries our project uses
- **Application**: Configuration for running the app
- **Test**: Configuration for running tests

---

## 🏗️ Core Classes Explained

### 1. 📋 Account.java - The Data Model

The `Account` class represents a single bank account. Think of it as a blueprint for creating accounts.

#### **Instance Variables (Data Storage):**
```java
private String accountNumber;     // Unique identifier (e.g., "ACC001")
private String ownerName;         // Account holder's name (e.g., "Alice")
private double balance;           // Current money in account (e.g., 500.0)
private List<String> transactionHistory;  // Record of all transactions
```

**Why `private`?** This is **Encapsulation** - we protect our data from being changed directly from outside the class.

#### **Constructor (Object Creation):**
```java
public Account(String accountNumber, String ownerName, double initialDeposit) {
    this.accountNumber = accountNumber;
    this.ownerName = ownerName;
    this.balance = initialDeposit;
    this.transactionHistory = new ArrayList<>();
    if (initialDeposit > 0) {
        this.transactionHistory.add("Initial deposit: $" + String.format("%.2f", initialDeposit));
    }
}
```

**What This Does:**
- Creates a new Account object
- Sets the account number, owner name, and initial balance
- Creates an empty transaction history list
- Records the initial deposit if it's positive

#### **Getter Methods (Safe Data Access):**
```java
public double getBalance() { return balance; }
public String getAccountNumber() { return accountNumber; }
public String getOwnerName() { return ownerName; }
```

**Why Getters?** They provide controlled access to private data without allowing direct modification.

#### **Business Logic Methods:**

**Deposit Method:**
```java
public void deposit(double amount) {
    if (amount > 0) {
        balance += amount;
        transactionHistory.add("Deposit: $" + String.format("%.2f", amount) + " | New balance: $" + String.format("%.2f", balance));
        System.out.println("✅ Deposit successful. New balance: $" + String.format("%.2f", balance));
    } else {
        System.out.println("❌ Deposit amount must be positive.");
    }
}
```

**What This Does:**
1. Checks if amount is positive
2. Adds amount to balance
3. Records transaction in history
4. Shows success message

**Withdraw Method:**
```java
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
```

**What This Does:**
1. Validates amount is positive
2. Checks if sufficient funds exist
3. Subtracts amount from balance
4. Records transaction
5. Shows appropriate message

### 2. 🏦 Bank.java - The Account Manager

The `Bank` class manages multiple accounts. It's like a container that holds all the accounts.

#### **Instance Variables:**
```java
private List<Account> accounts;  // List to store multiple Account objects
```

#### **Constructor:**
```java
public Bank() {
    this.accounts = new ArrayList<>();  // Create empty list
}
```

#### **Key Methods:**

**Add Account:**
```java
public void addAccount(Account account) {
    accounts.add(account);
    System.out.println("Account created for " + account.getAccountNumber());
}
```

**Find Account (Using Streams API):**
```java
public Optional<Account> findAccount(String accountNumber) {
    return accounts.stream()
            .filter(account -> account.getAccountNumber().equals(accountNumber))
            .findFirst();
}
```

**What This Does:**
1. Creates a stream from the accounts list
2. Filters to find matching account number
3. Returns the first match (or empty if not found)
4. Uses `Optional` to safely handle "not found" cases

**Get All Accounts:**
```java
public List<Account> getAllAccounts() {
    return new ArrayList<>(accounts);  // Return a copy to prevent external modification
}
```

**Delete Account:**
```java
public boolean deleteAccount(String accountNumber) {
    return accounts.removeIf(account -> account.getAccountNumber().equals(accountNumber));
}
```

### 3. 🖥️ Main.java - The User Interface

The `Main` class handles user interaction and coordinates between the Bank and Account classes.

#### **Main Method Structure:**
```java
public static void main(String[] args) {
    Bank bank = new Bank();                    // Create bank
    Scanner scanner = new Scanner(System.in);  // For user input
    
    // Create sample accounts
    bank.addAccount(new Account("ACC001", "Alice", 500.0));
    bank.addAccount(new Account("ACC002", "Bob", 1200.0));
    
    boolean exit = false;
    while (!exit) {
        // Display menu and handle user choice
    }
    scanner.close();
}
```

#### **Menu System:**
The app uses a `while` loop to continuously show the menu until the user chooses to exit.

#### **Exception Handling:**
```java
try {
    int choice = scanner.nextInt();
    // Process choice
} catch (java.util.InputMismatchException e) {
    System.out.println("Invalid input. Please enter a number.");
    scanner.nextLine(); // Clear invalid input
} catch (java.util.NoSuchElementException e) {
    System.out.println("No input available. Exiting application.");
    exit = true;
}
```

**Why Exception Handling?** It prevents the program from crashing when users enter invalid input.

#### **Handler Methods:**
Each menu option has its own handler method:
- `handleDeposit()` - Processes deposit requests
- `handleWithdrawal()` - Processes withdrawal requests
- `handleCheckBalance()` - Shows account balance
- `handleCreateAccount()` - Creates new accounts
- `handleViewAllAccounts()` - Shows all accounts
- `handleDeleteAccount()` - Deletes accounts
- `handleViewAccountDetails()` - Shows detailed account info

---

## 🔄 How Everything Works Together

### 1. **Application Startup:**
```
Main.main() → Creates Bank → Creates sample Accounts → Shows menu
```

### 2. **User Interaction Flow:**
```
User selects option → Handler method called → Bank methods used → Account methods used → Result displayed
```

### 3. **Example: Deposit Flow:**
```
1. User chooses "Deposit" (option 1)
2. handleDeposit() called
3. User enters account number
4. bank.findAccount() searches for account
5. If found, user enters amount
6. account.deposit() processes the deposit
7. Success message shown
8. Return to main menu
```

### 4. **Data Flow:**
```
User Input → Validation → Bank Logic → Account Logic → Database Update → User Feedback
```

---

## 🧪 Testing Strategy

### Why Testing?
- **Verification**: Ensures our code works correctly
- **Regression Prevention**: Catches bugs when we make changes
- **Documentation**: Tests serve as examples of how code should work

### Test Structure:

#### **AccountTest.java:**
Tests the Account class functionality:
- ✅ Successful deposits
- ✅ Successful withdrawals
- ✅ Insufficient funds handling
- ✅ Transaction history tracking
- ✅ Account summary generation
- ✅ Input validation

#### **BankTest.java:**
Tests the Bank class functionality:
- ✅ Adding accounts
- ✅ Finding accounts
- ✅ Deleting accounts
- ✅ Getting all accounts
- ✅ Calculating total balance
- ✅ Edge cases (empty bank, non-existent accounts)

### Test Annotations:
- `@BeforeEach` - Runs before each test (sets up fresh data)
- `@Test` - Marks a method as a test
- `@DisplayName` - Provides human-readable test descriptions

---

## 🎓 Key Programming Concepts

### 1. **Object-Oriented Programming (OOP):**

**Classes and Objects:**
- **Class**: Blueprint (like a cookie cutter)
- **Object**: Instance (like an actual cookie)
- Example: `Account` is a class, `new Account("ACC001", "Alice", 500.0)` creates an object

**Encapsulation:**
- Hiding internal data (`private` variables)
- Providing controlled access through methods
- Example: `balance` is private, accessed through `getBalance()`

**Abstraction:**
- Hiding complex implementation details
- Providing simple interfaces
- Example: User doesn't need to know how `findAccount()` works internally

### 2. **Collections Framework:**
- **ArrayList**: Dynamic array that can grow/shrink
- **List**: Interface that ArrayList implements
- Example: `List<Account> accounts` can hold multiple Account objects

### 3. **Modern Java Features:**
- **Optional**: Safely handles "might be null" values
- **Streams API**: Functional programming for collections
- **Lambda Expressions**: Concise way to write functions

### 4. **Exception Handling:**
- **Try-Catch**: Gracefully handle errors
- **Input Validation**: Check user input before processing
- **Graceful Degradation**: App continues running even with errors

### 5. **String Formatting:**
- **String.format()**: Professional number formatting
- **String.repeat()**: Create repeated characters for UI
- **String.trim()**: Remove whitespace from user input

---

## 📖 Step-by-Step Code Walkthrough

### Step 1: Understanding the Account Class

```java
// 1. Class declaration and package
package com.bankapp;
public class Account {
    
    // 2. Private instance variables (data storage)
    private String accountNumber;
    private String ownerName;
    private double balance;
    private List<String> transactionHistory;
    
    // 3. Constructor (object creation)
    public Account(String accountNumber, String ownerName, double initialDeposit) {
        // 'this' refers to the current object
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        
        // Record initial deposit if positive
        if (initialDeposit > 0) {
            this.transactionHistory.add("Initial deposit: $" + String.format("%.2f", initialDeposit));
        }
    }
```

**What's Happening:**
1. **Package Declaration**: Tells Java where this class belongs
2. **Class Declaration**: `public class Account` makes it accessible
3. **Instance Variables**: Store data for each account object
4. **Constructor**: Special method that runs when creating new objects
5. **Initialization**: Sets up the object with provided values

### Step 2: Understanding the Bank Class

```java
public class Bank {
    private List<Account> accounts;  // Collection to store accounts
    
    public Bank() {
        this.accounts = new ArrayList<>();  // Initialize empty list
    }
    
    public Optional<Account> findAccount(String accountNumber) {
        return accounts.stream()                    // Create stream
                .filter(account -> account.getAccountNumber().equals(accountNumber))  // Filter matching accounts
                .findFirst();                       // Get first match or empty
    }
```

**What's Happening:**
1. **Collection Management**: Uses ArrayList to store multiple accounts
2. **Stream Processing**: Modern Java way to search collections
3. **Optional Return**: Safely handles "not found" cases
4. **Lambda Expression**: `account -> account.getAccountNumber().equals(accountNumber)` is a concise function

### Step 3: Understanding the Main Class

```java
public static void main(String[] args) {
    // 1. Setup
    Bank bank = new Bank();
    Scanner scanner = new Scanner(System.in);
    
    // 2. Create sample data
    bank.addAccount(new Account("ACC001", "Alice", 500.0));
    bank.addAccount(new Account("ACC002", "Bob", 1200.0));
    
    // 3. Main application loop
    boolean exit = false;
    while (!exit) {
        // Display menu
        System.out.println("1. 💰 Deposit Money");
        System.out.println("2. 💸 Withdraw Money");
        // ... more options
        
        // Get user input
        int choice = scanner.nextInt();
        
        // Process choice
        switch (choice) {
            case 1: handleDeposit(scanner, bank); break;
            case 2: handleWithdrawal(scanner, bank); break;
            // ... more cases
        }
    }
}
```

**What's Happening:**
1. **Application Setup**: Creates necessary objects
2. **Sample Data**: Adds test accounts for demonstration
3. **Main Loop**: Continues until user chooses to exit
4. **Menu System**: Shows options and processes user choices
5. **Method Delegation**: Each option calls a specific handler method

### Step 4: Understanding Exception Handling

```java
try {
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    
    switch (choice) {
        // Process choice
    }
} catch (java.util.InputMismatchException e) {
    System.out.println("Invalid input. Please enter a number.");
    scanner.nextLine(); // Clear invalid input
} catch (java.util.NoSuchElementException e) {
    System.out.println("No input available. Exiting application.");
    exit = true;
}
```

**What's Happening:**
1. **Try Block**: Attempts to read user input
2. **Catch Blocks**: Handle specific types of errors
3. **InputMismatchException**: User entered text instead of number
4. **NoSuchElementException**: No input available (end of stream)
5. **Error Recovery**: Clear invalid input and continue

### Step 5: Understanding Method Organization

```java
private static void handleDeposit(Scanner scanner, Bank bank) {
    try {
        // 1. Get account number
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        
        // 2. Find account
        Optional<Account> accountOpt = bank.findAccount(accNum);
        
        // 3. Process if found
        if (accountOpt.isPresent()) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            accountOpt.get().deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    } catch (Exception e) {
        // Handle errors
    }
}
```

**What's Happening:**
1. **Method Signature**: `private static void` means it's a helper method
2. **Parameters**: Receives Scanner and Bank objects
3. **User Input**: Gets account number and amount
4. **Account Lookup**: Uses Bank to find the account
5. **Optional Handling**: Checks if account exists before processing
6. **Business Logic**: Calls Account's deposit method
7. **Error Handling**: Catches and handles exceptions

---

## 🎯 Key Takeaways

### **What You've Learned:**
1. **Object-Oriented Design**: How to model real-world entities as classes
2. **Encapsulation**: Protecting data while providing controlled access
3. **Collections**: Managing multiple objects efficiently
4. **Exception Handling**: Making robust applications that don't crash
5. **User Interface Design**: Creating intuitive command-line interfaces
6. **Testing**: Ensuring code quality through automated tests
7. **Modern Java**: Using streams, optionals, and lambda expressions

### **Best Practices Demonstrated:**
- ✅ **Separation of Concerns**: Each class has a specific responsibility
- ✅ **Input Validation**: Always check user input before processing
- ✅ **Error Handling**: Gracefully handle unexpected situations
- ✅ **Code Organization**: Clear method names and logical structure
- ✅ **Documentation**: Comments explain complex logic
- ✅ **Testing**: Comprehensive test coverage

### **Real-World Applications:**
This banking app demonstrates concepts used in:
- **Web Applications**: User input handling, data validation
- **Database Systems**: CRUD operations (Create, Read, Update, Delete)
- **Enterprise Software**: Business logic, transaction processing
- **API Development**: Method organization, error handling

---

## 🚀 Next Steps

### **Potential Enhancements:**
1. **Database Integration**: Store accounts in a real database
2. **Web Interface**: Create a web-based version
3. **User Authentication**: Add login/password system
4. **Transaction Types**: Add transfers between accounts
5. **Interest Calculation**: Add savings account features
6. **Reporting**: Generate account statements
7. **Data Persistence**: Save data between app runs

### **Learning Path:**
1. **Java Collections**: Learn more about Lists, Maps, Sets
2. **Design Patterns**: Study common software design patterns
3. **Database Programming**: Learn JDBC or JPA
4. **Web Development**: Learn Spring Boot for web applications
5. **Testing**: Explore more advanced testing techniques

---

*This guide provides a comprehensive understanding of the Simple Banking App codebase. Each concept builds upon the previous ones, creating a solid foundation for Java programming and software development.*
