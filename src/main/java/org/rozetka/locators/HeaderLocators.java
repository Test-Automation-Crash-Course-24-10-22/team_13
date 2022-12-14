package org.rozetka.locators;

import org.openqa.selenium.By;

public enum HeaderLocators {
    SEARCH_INPUT(By.xpath("//input[@name='search']"));

    private final By path;

    HeaderLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
