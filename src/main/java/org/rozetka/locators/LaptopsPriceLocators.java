package org.rozetka.locators;

import org.openqa.selenium.By;

public enum LaptopsPriceLocators {

    PRICE_OF_ITEMS(By.xpath("//span[contains(@class, 'price-value')]"));

    private final By path;

    LaptopsPriceLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
