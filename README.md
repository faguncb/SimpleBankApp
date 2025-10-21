# Simple Banking Application in Java

This is a beginner-friendly Java project designed to demonstrate core programming concepts through a simple command-line banking application.

## 🌟 Concepts Covered

* **Object-Oriented Programming (OOP):**
    * **Encapsulation:** Hiding data (`balance`) within the `Account` class.
    * **Classes and Objects:** Creating blueprints (`Account`, `Bank`) and instances of them.
* **Java Fundamentals:**
    * Variables, data types, methods, and constructors.
    * Control flow (`if-else`, `switch`, `while` loop).
    * Exception handling with try-catch blocks.
* **Collections Framework:**
    * Using `ArrayList` to manage a collection of `Account` objects.
* **Unit Testing:**
    * Writing and running tests with JUnit 5 to ensure code correctness.
    * Using `@BeforeEach`, `@Test`, and `@DisplayName` annotations.
    * Assertion methods for validating expected behavior.
* **Build Management:**
    * Using Gradle to compile code, manage dependencies, and run the application.
* **Modern Java Features:**
    * Using `Optional` to handle potentially null values gracefully.
    * Using the Streams API for searching collections.

## ✅ Prerequisites

* JDK 17 or higher
* Gradle 7.0 or higher

## 🚀 How to Run

1.  **Clone the repository:**
    ```bash
    git clone <git@github.com:faguncb/SimpleBankApp.git>
    cd SimpleBankApp
    ```

2.  **Build the project:**
    ```bash
    gradle build
    ```
    This compiles the code, runs tests, and packages the application.

3.  **Run the tests:**
    ```bash
    gradle test
    ```

4.  **Run the application:**
    ```bash
    gradle run
    ```
    Follow the on-screen menu to interact with the banking system.
    
    **Note:** For interactive input in terminal, use:
    ```bash
    gradle run --console=plain
    ```

5.  **Run the JAR file directly:**
    ```bash
    java -jar build/libs/SimpleBankApp.jar
    ```

## 📂 Project Structure
```
SimpleBankApp/
├── build.gradle              # Gradle build configuration
├── README.md                 # Project documentation
└── src/
    ├── main/
    │   └── java/com/bankapp/
    │       ├── Account.java  # Account class with deposit/withdraw methods
    │       ├── Bank.java     # Bank class managing multiple accounts
    │       └── Main.java     # Main application with user interface
    └── test/                 # Test directory (note: singular 'test', not 'tests')
        └── java/com/bankapp/
            └── AccountTest.java  # JUnit 5 unit tests for Account class
```

## 🔧 Dependencies

The project uses the following dependencies (managed by Gradle):

* **JUnit 5 (Jupiter)** - Testing framework
  * `org.junit.jupiter:junit-jupiter:5.8.1`
  * `org.junit.platform:junit-platform-launcher` (runtime)

## 📝 Recent Improvements

* ✅ Fixed test directory structure to follow Gradle conventions (`src/test/` instead of `src/tests/`)
* ✅ Added JUnit Platform Launcher dependency for proper test execution
* ✅ All unit tests passing successfully
* ✅ Build process fully functional