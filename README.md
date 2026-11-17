# Selenium Automation Framework

A Java-based test automation framework built with Selenium WebDriver, TestNG, Maven, and the Page Object Model (POM) design pattern.

## Tech Stack

- Java 21
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- JSON Data-Driven Testing
- SLF4J & Logback
- Allure Reporting
- GitHub Actions CI/CD

## Key Features

- Reusable Page Object classes
- Cross-browser support: Chrome, Firefox, Edge
- Configurable explicit waits
- Parallel test execution using ThreadLocal WebDriver
- JSON-based test data
- Automatic screenshot capture on test failure
- Logging and Allure reporting
- Automated CI/CD execution in headless mode

## Test Coverage

The framework includes automated login scenarios for [SauceDemo](https://www.saucedemo.com/).

Current verified CI execution: **4 tests passed, 0 failures**.

## Running Tests

Run all tests selected by Maven/TestNG:

```bash
mvn clean test
```

Run tests in headless mode:

```bash
mvn clean test -Dheadless=true
```

Configure browser and timeout:

```bash
mvn clean test -Dbrowser=chrome -Dexplicit.wait=15
```

## Reporting

Test execution results are available through Maven Surefire and Allure.

Failure screenshots and execution logs are saved under `target/`.

## CI/CD

GitHub Actions runs the automated tests on pushes and pull requests.

[View GitHub Actions](https://github.com/khaledmostafaelsayed/selenium-login-framework/actions)

## Repository

[selenium-login-framework](https://github.com/khaledmostafaelsayed/selenium-login-framework)
