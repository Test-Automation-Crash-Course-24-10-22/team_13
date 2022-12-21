# Automation testing stack using Java, Selenium, TestNG, and Maven.

It consists of:

'Page Component Object Model' design pattern.
reports, created with Allure framework.
The target website is https://rozetka.com.ua/ua/

Automated tests:

Checking sorting notebooks by price.
Checkin filtering notebooks by the valid and invalid brand's name (negative test example).
Checking filtering notebooks by the price range.
Executing the Tests
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
