package org.rozetka.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.rozetka.locators.LaptopsFilterLocators;
import org.rozetka.pages.BasePage;
import org.rozetka.pages.laptops.LaptopsBrandPage;
import org.rozetka.pages.laptops.LaptopsPricePage;

public class LaptopFilterComponent extends BasePage {
    private static final String APPLE = "Apple";

    public LaptopFilterComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Choose apple brand checkbox")
    public LaptopsBrandPage chooseAppleBrand(){
        pause(5);
        waitForElementToBeVisible(LaptopsFilterLocators.BRAND_INPUT.getPath());
        driver.findElement(LaptopsFilterLocators.BRAND_INPUT.getPath()).click();
        driver.findElement(LaptopsFilterLocators.BRAND_INPUT.getPath()).sendKeys(APPLE);
        waitForElementsToBeVisible(LaptopsFilterLocators.APPLE_CHECKBOX.getPath());
        driver.findElement(LaptopsFilterLocators.APPLE_CHECKBOX.getPath()).click();
        return new LaptopsBrandPage(driver);
    }

    @Step("Choose available goods checkbox")
    public LaptopsBrandPage chooseAvailableGoods(){
        waitForElementsToBeVisible(LaptopsFilterLocators.AVAILABLE_GOODS_CHECKBOX.getPath());
        driver.findElement(LaptopsFilterLocators.AVAILABLE_GOODS_CHECKBOX.getPath()).click();
        return new LaptopsBrandPage(driver);
    }

    @Step("Filter by min price {minPrice} and max price {maxPrice}")
    public LaptopsPricePage filterByPrice(int minPrice, int maxPrice) {
        inputMinPrice(minPrice);
        inputMaxPrice(maxPrice);
        clickOkPriceFilterButton();
        return new LaptopsPricePage(driver);
    }

    @Step("Input min price {minPrice}")
    public LaptopFilterComponent inputMinPrice(int minPrice) {
        pause(5);
        waitForElementToBeVisible(LaptopsFilterLocators.PRICE_MIN_INPUT.getPath());
        driver.findElement(LaptopsFilterLocators.PRICE_MIN_INPUT.getPath()).clear();
        driver.findElement(LaptopsFilterLocators.PRICE_MIN_INPUT.getPath()).sendKeys(String.valueOf(minPrice));
        return this;
    }

    @Step("Input max price {maxPrice}")
    public LaptopFilterComponent inputMaxPrice(int maxPrice) {
        waitForElementToBeVisible(LaptopsFilterLocators.PRICE_MAX_INPUT.getPath());
        driver.findElement(LaptopsFilterLocators.PRICE_MAX_INPUT.getPath()).clear();
        driver.findElement(LaptopsFilterLocators.PRICE_MAX_INPUT.getPath()).sendKeys(String.valueOf(maxPrice));
        return this;
    }

    @Step("click Ok price filter button")
    public void clickOkPriceFilterButton() {
        waitForElementToBeClickable(LaptopsFilterLocators.OK_PRICE_FILTER_BUTTON.getPath());
        driver.findElement(LaptopsFilterLocators.OK_PRICE_FILTER_BUTTON.getPath()).click();
    }

    @Step("is Ok price filter button disabled?")
    public boolean isOkPriceFilterButtonDisabled() {
        waitForElementsToBeVisible(LaptopsFilterLocators.OK_PRICE_FILTER_BUTTON.getPath());
        return !driver.findElement(LaptopsFilterLocators.OK_PRICE_FILTER_BUTTON.getPath()).isEnabled();
    }
}
