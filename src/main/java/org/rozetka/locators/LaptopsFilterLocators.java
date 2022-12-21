package org.rozetka.locators;

import org.openqa.selenium.By;

public enum LaptopsFilterLocators {

    BRAND_INPUT(By.xpath("//div[@data-filter-name='producer']//input[@type='search']")),
    APPLE_CHECKBOX(By.xpath("//a[@data-id='Apple']")),
    AVAILABLE_GOODS_CHECKBOX(By.xpath("//a[@data-id='Є в наявності']")),
    PRICE_MIN_INPUT(By.xpath("//input[@formcontrolname='min']")),
    PRICE_MAX_INPUT(By.xpath("//input[@formcontrolname='max']")),
    OK_PRICE_FILTER_BUTTON(By.xpath("//input[@formcontrolname='max']//following-sibling::button[contains(text(), 'Ok')]"));

    private final By path;

    LaptopsFilterLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
