# Workrig Automation Framework

A professional, enterprise-grade test automation framework for the Workrig application using Selenium WebDriver, TestNG, and Maven.

## Framework Architecture

This framework follows industry best practices and design patterns:

- **Page Object Model (POM)**: Separates page elements and actions into dedicated classes
- **Singleton Pattern**: Used for configuration and driver management
- **Factory Pattern**: For creating WebDriver instances
- **Builder Pattern**: For constructing complex objects
- **Utility Classes**: For reusable functionality
- **Configuration Management**: Centralized configuration using properties files
- **Reporting**: Comprehensive test reporting with TestNG and custom listeners
- **Logging**: Detailed logging of test execution

## Project Structure

```
src/test/java/WorkrigAutoamation/
├── config/                 # Configuration management
│   └── ConfigManager.java  # Singleton for managing configuration
├── constants/              # Application constants
│   └── AppConstants.java   # Constants used throughout the application
├── listeners/              # TestNG listeners
│   └── TestListener.java   # Custom listener for reporting and logging
├── pages/                  # Page Object Model classes
│   ├── BasePage.java       # Base class for all page objects
│   └── LoginPage.java      # Login page object
├── reports/                # Test reports
├── utils/                  # Utility classes
│   ├── DriverManager.java  # WebDriver management
│   └── TestUtils.java      # Common test utilities
└── Workrig_Autoamtion.java # Main test class
```

## Technologies Used

- **Java 11**: Programming language
- **Selenium WebDriver 4.14.1**: Browser automation
- **TestNG 7.8.0**: Test execution and reporting
- **Maven**: Dependency management and build automation
- **WebDriverManager 5.5.3**: WebDriver binary management
- **Extent Reports**: Enhanced reporting (optional)

## Features

- **Multi-browser Support**: Run tests on Chrome, Firefox, Edge, or Safari
- **Parallel Execution**: Run tests in parallel for faster execution
- **Configurable Timeouts**: Adjustable wait times for elements
- **Screenshot Capture**: Automatic screenshots on test failure
- **Detailed Logging**: Comprehensive logging of test execution
- **Retry Mechanism**: Automatically retry failed tests
- **Headless Mode**: Run tests without browser UI
- **Cross-platform**: Works on Windows, macOS, and Linux

## Setup

1. Clone the repository:
   ```
   git clone https://github.com/ansul9052/Workrig-Automation.git
   ```

2. Navigate to the project directory:
   ```
   cd Workrig-Automation
   ```

3. Install dependencies:
   ```
   mvn clean install
   ```

## Configuration

The framework is highly configurable through `src/test/resources/config.properties`:

- **Browser Settings**: Choose browser type and headless mode
- **Timeouts**: Configure implicit and explicit wait times
- **Test Settings**: Enable/disable parallel execution and retry mechanism
- **Reporting**: Configure report generation and paths
- **Credentials**: Store application credentials securely

## Running Tests

### Using Maven

Run all tests:
```
mvn test
```

Run specific test class:
```
mvn test -Dtest=Workrig_Autoamtion
```

Run tests in parallel:
```
mvn test -Dparallel=true -DthreadCount=3
```

### Using TestNG

Run the TestNG suite directly from your IDE by right-clicking on the `testng.xml` file.

## Reports

After test execution, reports are available in:
- TestNG Reports: `test-output/index.html`
- Custom Logs: `test-output/logs/`
- Screenshots: `test-output/screenshots/`

## Best Practices

This framework follows these best practices:
- Separation of concerns
- DRY (Don't Repeat Yourself) principle
- SOLID principles
- Clean code practices
- Comprehensive documentation
- Proper error handling
- Robust element locators

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 