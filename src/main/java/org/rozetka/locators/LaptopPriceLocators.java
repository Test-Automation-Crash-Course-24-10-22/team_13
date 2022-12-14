package org.rozetka.locators;

import org.openqa.selenium.By;

public enum LaptopPriceLocators {

    PRICE_OF_ITEMS(By.xpath("//span[contains(@class, 'price-value')]"));

    private final By path;

    LaptopPriceLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
