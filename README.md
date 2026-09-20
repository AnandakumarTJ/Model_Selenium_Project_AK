# Model Selenium Automation Framework For E-Commerce Application created using lovable GenAI by my Own

A **Selenium WebDriver automation framework** built using **Java, Cucumber, Maven, and Page Object Model (POM)** to demonstrate a scalable and maintainable approach to web application test automation.

## 🚀 Introduction
This project is designed based on a real-world **QA Automation / SDET framework architecture**, with a focus on creating reusable, maintainable, and readable automation components.

The framework supports **BDD-based test automation using Cucumber**, reusable page objects, browser management, test execution through Maven, and automated test reporting.

## 🛠️ Tech Stack

* **Java 11**
* **Selenium WebDriver**
* **Cucumber BDD**
* **Maven**
* **JUnit**
* **WebDriverManager**
* **Page Object Model (POM)**
* **Git / GitHub**
* **Jenkins / CI-CD Ready**

## ✨ Key Features

* Page Object Model for better maintainability
* Cucumber BDD for readable test scenarios
* Reusable WebDriver and utility components
* Automated browser driver management using WebDriverManager
* Support for **Chrome, Edge, and Firefox**
* Tag-based test execution such as `@Smoke`
* HTML, JSON, JUnit, and rerun reports
* Maven-based test execution
* Structured separation of features, step definitions, page classes, and support components
* Designed for CI/CD integration

## 📂 Project Structure

```text
model-test-framework
│
├── src
│   └── test
│       └── java
│           ├── features
│           ├── pages
│           ├── stepDefinitions
│           ├── support
│           └── testRunners
│
├── pom.xml
└── README.md
```

## ▶️ How to Run

Clone the repository and import it as a **Maven project** in IntelliJ IDEA or any compatible IDE.

Run the complete test suite using:

```bash
mvn test
```

To execute specific Cucumber scenarios using tags:

```bash
mvn test -Dcucumber.filter.tags="@Smoke"
```

## 📊 Reporting

The framework generates multiple test execution reports, including:

* HTML report
* JSON report
* JUnit report
* Rerun file for failed scenarios

## 🎯 Framework Objective
The objective of this project is to demonstrate how a modern Selenium automation framework can be structured for:

* **Scalability**
* **Maintainability**
* **Readability**
* **Easy debugging**
* **CI/CD integration**

This repository is continuously evolving with additional automation scenarios, utilities, and framework enhancements.

👨‍💻 About
This project is maintained as part of my hands-on work in QA Automation and SDET practices, covering UI automation, BDD, framework design, reusable automation components, and CI/CD-oriented test execution.
