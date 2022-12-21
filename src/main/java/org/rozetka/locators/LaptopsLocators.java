package org.rozetka.locators;

import org.openqa.selenium.By;

public enum LaptopsLocators {
    SORT_FIELD(By.xpath("//select"));

    private final By path;

    LaptopsLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
