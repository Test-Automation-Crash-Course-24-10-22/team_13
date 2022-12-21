package org.rozetka.locators;

import org.openqa.selenium.By;

public enum LaptopsBrandLocators {

    LAPTOP_TITLE(By.xpath("//span[@class='goods-tile__title']")),
    AVAILABILITY_OF_GOODS(By.xpath("//div[contains(@class, 'goods-tile__availability')]"));

    private final By path;

    LaptopsBrandLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
