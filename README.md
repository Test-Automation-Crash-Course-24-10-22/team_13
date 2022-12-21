## Automation testing stack using Java, Selenium, TestNG, and Maven.

It consists of:

  - Page Component Object Model design pattern.
  - Reports, created with Allure framework.

The target website is https://rozetka.com.ua/ua/

Automated tests:

 - Checking filtering laptops by the brand, price and availability.
 - Checking sorting notebooks by price.

## Executing the Tests
Clone the repository.
```
git clone https://github.com/Test-Automation-Crash-Course-24-10-22/team_13.git
```
Run the test.
```
mvn clean test
```
Generate the report.
```
allure serve target/allure-results
```
