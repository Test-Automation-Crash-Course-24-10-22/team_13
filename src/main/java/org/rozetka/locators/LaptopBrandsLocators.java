package org.rozetka.locators;

import org.openqa.selenium.By;

public enum LaptopBrandsLocators {

    LAPTOP_TITLE(By.xpath("//span[@class='goods-tile__title']")),
    AVAILABILITY_OF_GOODS(By.xpath("//div[contains(@class, 'goods-tile__availability')]"));

    private final By path;

    LaptopBrandsLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
