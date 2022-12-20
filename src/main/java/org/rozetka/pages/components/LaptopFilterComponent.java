package org.rozetka.pages.components;

import io.qameta.allure.Step;
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

    @Step("Choose apple brand checkbox")
    public LaptopBrandsPage chooseAppleBrand(){
        pause(5);
        waitForElementToBeVisible(LaptopFilterLocators.BRAND_INPUT.getPath());
        driver.findElement(LaptopFilterLocators.BRAND_INPUT.getPath()).click();
        driver.findElement(LaptopFilterLocators.BRAND_INPUT.getPath()).sendKeys(APPLE);
        waitForElementsToBeVisible(LaptopFilterLocators.APPLE_CHECKBOX.getPath());
        driver.findElement(LaptopFilterLocators.APPLE_CHECKBOX.getPath()).click();
        return new LaptopBrandsPage(driver);
    }

    @Step("Choose available goods checkbox")
    public LaptopBrandsPage chooseAvailableGoods(){
        waitForElementsToBeVisible(LaptopFilterLocators.AVAILABLE_GOODS_CHECKBOX.getPath());
        driver.findElement(LaptopFilterLocators.AVAILABLE_GOODS_CHECKBOX.getPath()).click();
        return new LaptopBrandsPage(driver);
    }

    @Step("Filter by min price {min} and max price {max}")
    public LaptopPricePage filterByPrice(int min, int max) {
        inputMinPrice(min);
        inputMaxPrice(max);
        clickOkPriceFilterButton();
        return new LaptopPricePage(driver);
    }

    @Step("Input min price {minPrice}")
    public LaptopFilterComponent inputMinPrice(int minPrice) {
        pause(5);
        waitForElementToBeVisible(LaptopFilterLocators.PRICE_MIN_INPUT.getPath());
        driver.findElement(LaptopFilterLocators.PRICE_MIN_INPUT.getPath()).clear();
        driver.findElement(LaptopFilterLocators.PRICE_MIN_INPUT.getPath()).sendKeys(String.valueOf(minPrice));
        return this;
    }

    @Step("Input max price {maxPrice}")
    public LaptopFilterComponent inputMaxPrice(int maxPrice) {
        waitForElementToBeVisible(LaptopFilterLocators.PRICE_MAX_INPUT.getPath());
        driver.findElement(LaptopFilterLocators.PRICE_MAX_INPUT.getPath()).clear();
        driver.findElement(LaptopFilterLocators.PRICE_MAX_INPUT.getPath()).sendKeys(String.valueOf(maxPrice));
        return this;
    }

    @Step("click Ok price filter button")
    public void clickOkPriceFilterButton() {
        waitForElementToBeClickable(LaptopFilterLocators.OK_PRICE_FILTER_BUTTON.getPath());
        driver.findElement(LaptopFilterLocators.OK_PRICE_FILTER_BUTTON.getPath()).click();
    }

    @Step("is Ok price filter button disabled?")
    public boolean isOkPriceFilterButtonDisabled() {
        waitForElementsToBeVisible(LaptopFilterLocators.OK_PRICE_FILTER_BUTTON.getPath());
        return !driver.findElement(LaptopFilterLocators.OK_PRICE_FILTER_BUTTON.getPath()).isEnabled();
    }
}
