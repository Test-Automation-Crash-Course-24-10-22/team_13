package org.rozetka.pages.components;

import org.openqa.selenium.WebDriver;
import org.rozetka.locators.LaptopFilterLocators;
import org.rozetka.pages.BasePage;
import org.rozetka.pages.LaptopBrandsPage;
import org.rozetka.pages.LaptopPricePage;

public class LaptopFilterComponent extends BasePage {
    private static final String APPLE = "Apple";
    private static final String MICROSOFT = "Microsoft";

    public LaptopFilterComponent(WebDriver driver) {
        super(driver);
    }

    public LaptopBrandsPage chooseAppleBrand(){
        waitForElementToBeVisible(LaptopFilterLocators.BRAND_INPUT.getPath());
        driver.findElement(LaptopFilterLocators.BRAND_INPUT.getPath()).click();
        driver.findElement(LaptopFilterLocators.BRAND_INPUT.getPath()).sendKeys(APPLE);
        waitForElementsToBeVisible(LaptopFilterLocators.APPLE_CHECKBOX.getPath());
        driver.findElement(LaptopFilterLocators.APPLE_CHECKBOX.getPath()).click();
        return new LaptopBrandsPage(driver);
    }

    public LaptopBrandsPage chooseAvailableGoods(){
        waitForElementsToBeVisible(LaptopFilterLocators.AVAILABLE_GOODS_CHECKBOX.getPath());
        driver.findElement(LaptopFilterLocators.AVAILABLE_GOODS_CHECKBOX.getPath()).click();
        return new LaptopBrandsPage(driver);
    }

    public LaptopPricePage filterByPrice(int min, int max) {
        inputMinPrice(min);
        inputMaxPrice(max);
        clickOkPriceFilterButton();
        return new LaptopPricePage(driver);
    }

    public void inputMinPrice(int minPrice) {
        waitForElementToBeVisible(LaptopFilterLocators.PRICE_MIN_INPUT.getPath());
        driver.findElement(LaptopFilterLocators.PRICE_MIN_INPUT.getPath()).clear();
        driver.findElement(LaptopFilterLocators.PRICE_MIN_INPUT.getPath()).sendKeys(String.valueOf(minPrice));
    }

    public void inputMaxPrice(int maxPrice) {
        waitForElementToBeVisible(LaptopFilterLocators.PRICE_MAX_INPUT.getPath());
        driver.findElement(LaptopFilterLocators.PRICE_MAX_INPUT.getPath()).clear();
        driver.findElement(LaptopFilterLocators.PRICE_MAX_INPUT.getPath()).sendKeys(String.valueOf(maxPrice));
    }

    public void clickOkPriceFilterButton() {
        waitForElementToBeClickable(LaptopFilterLocators.OK_PRICE_FILTER_BUTTON.getPath());
        driver.findElement(LaptopFilterLocators.OK_PRICE_FILTER_BUTTON.getPath()).click();
    }

    public boolean isOkPriceFilterButtonDisabled() {
        waitForElementsToBeVisible(LaptopFilterLocators.OK_PRICE_FILTER_BUTTON.getPath());
        return !driver.findElement(LaptopFilterLocators.OK_PRICE_FILTER_BUTTON.getPath()).isEnabled();
    }
}
