package org.rozetka.pages.laptops;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.rozetka.locators.LaptopsPriceLocators;
import org.rozetka.pages.BasePageWithHeader;
import org.rozetka.pages.components.LaptopFilterComponent;

import java.util.List;
import java.util.stream.Collectors;

public class LaptopsPricePage extends BasePageWithHeader {

    private LaptopFilterComponent laptopFilterComponent;

    public LaptopsPricePage(WebDriver driver) {
        super(driver);
        this.laptopFilterComponent = new LaptopFilterComponent(driver);
    }

    public LaptopFilterComponent onLaptopFilterComponent() {
        return laptopFilterComponent;
    }

    @Step("Get actual prices of items")
    public List<Integer> getActualPricesOfItems() {
        pause(5);
        waitForElementsToBeVisible(LaptopsPriceLocators.PRICE_OF_ITEMS.getPath());
        return driver.findElements(LaptopsPriceLocators.PRICE_OF_ITEMS.getPath())
                .stream()
                .map(e -> e.getText().replaceAll(" ", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
