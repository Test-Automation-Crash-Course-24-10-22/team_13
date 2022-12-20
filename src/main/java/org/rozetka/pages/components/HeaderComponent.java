package org.rozetka.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.rozetka.locators.HeaderLocators;
import org.rozetka.pages.BasePage;
import org.rozetka.pages.LaptopsPage;

public class HeaderComponent extends BasePage {

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Search laptops")
    public LaptopsPage searchLaptops() {
        waitForElementsToBeVisible(HeaderLocators.SEARCH_INPUT.getPath());
        driver.findElement(HeaderLocators.SEARCH_INPUT.getPath()).sendKeys("Ноутбук");
        driver.findElement(HeaderLocators.SEARCH_INPUT.getPath()).sendKeys(Keys.RETURN);
        return new LaptopsPage(driver);
    }
}
