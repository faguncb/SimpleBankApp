# Simple Banking Application in Java

This is a beginner-friendly Java project designed to demonstrate core programming concepts through a simple command-line banking application.

## 🌟 Concepts Covered

* **Object-Oriented Programming (OOP):**
    * **Encapsulation:** Hiding data (`balance`) within the `Account` class.
    * **Classes and Objects:** Creating blueprints (`Account`, `Bank`) and instances of them.
* **Java Fundamentals:**
    * Variables, data types, methods, and constructors.
    * Control flow (`if-else`, `switch`, `while` loop).
* **Collections Framework:**
    * Using `ArrayList` to manage a collection of `Account` objects.
* **Unit Testing:**
    * Writing and running tests with JUnit 5 to ensure code correctness.
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

2.  **Run the tests:**
    ```bash
    ./gradlew test
    ```

3.  **Run the application:**
    ```bash
    ./gradlew run
    ```
    Follow the on-screen menu to interact with the banking system.

## 📂 Project Structure
```
SimpleBankApp/
├── build.gradle
└── src/
    ├── main/
    │   └── java/com/bankapp/
    └── test/
        └── java/com/bankapp/
```