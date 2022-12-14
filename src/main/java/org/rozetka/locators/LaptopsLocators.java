package org.rozetka.locators;

import org.openqa.selenium.By;

public enum LaptopsLocators {
    SORT_FIELD(By.xpath("//select")),
    FROM_LOWER_TO_HIGHER_OPTION(By.xpath("//option[contains(text(), 'Від дешевих до дорогих')]")),
    LAPTOP_TITLE(By.xpath("//span[@class='goods-tile__title']"));

    private final By path;

    LaptopsLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
