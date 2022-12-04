package org.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void waitForElementToVisible(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected void waitForElementsToVisible(By locator){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    protected void waitForElementsToBePresent(By locator){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    protected void waitForElementBeClickable(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
