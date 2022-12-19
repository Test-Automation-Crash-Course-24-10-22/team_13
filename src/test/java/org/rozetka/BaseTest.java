package org.rozetka;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    private static final String ROZETKA_URL = "https://rozetka.com.ua/ua/";
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setPreconditions() {
        setDriver();
    }

    public void setDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
    }

    @AfterMethod
    public void afterSuite(){
        if(driver != null){
            driver.quit();
        }
    }
}
