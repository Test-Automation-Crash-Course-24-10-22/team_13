package org.rozetka.pages;

import org.openqa.selenium.WebDriver;
import org.rozetka.locators.LaptopPriceLocators;
import org.rozetka.pages.components.LaptopFilterComponent;

import java.util.List;
import java.util.stream.Collectors;

public class LaptopPricePage extends BasePage {

    private LaptopFilterComponent laptopFilterComponent;

    public LaptopPricePage(WebDriver driver) {
        super(driver);
        this.laptopFilterComponent = new LaptopFilterComponent(driver);
    }

    public LaptopFilterComponent onLaptopFilterComponent() {
        return laptopFilterComponent;
    }

    public List<Integer> getActualPricesOfItems() {
        pause(5);
        waitForElementsToBeVisible(LaptopPriceLocators.PRICE_OF_ITEMS.getPath());
        return driver.findElements(LaptopPriceLocators.PRICE_OF_ITEMS.getPath())
                .stream()
                .map(e -> e.getText().replaceAll(" ", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
