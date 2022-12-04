package org.rozetka.pages.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.rozetka.pages.BasePage;
import org.rozetka.pages.LaptopsPage;

public class HeaderModule extends BasePage {

    private By searchInput = By.xpath("//input[@name='search']");

    public HeaderModule(WebDriver driver) {
        super(driver);
    }

    public LaptopsPage searchLaptops() {
        waitForElementsToVisible(searchInput);
        driver.findElement(searchInput).sendKeys("Ноутбук");
        driver.findElement(searchInput).sendKeys(Keys.RETURN);
        return new LaptopsPage(driver);
    }
}
