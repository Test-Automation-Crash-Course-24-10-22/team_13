package org.rozetka.locators;

import org.openqa.selenium.By;

public enum PriceIncreasedItemsLocators {

    FROM_LOWER_TO_HIGHER_PRICING_SORTED_ITEMS(By.xpath("//span[contains(@class, 'price-value')]"));

    private final By path;

    PriceIncreasedItemsLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
