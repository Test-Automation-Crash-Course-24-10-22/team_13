package org.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.rozetka.pages.modules.HeaderModule;
import org.rozetka.pages.modules.LaptopFilterModule;

import java.util.List;
import java.util.stream.Collectors;

public class LaptopPricePage extends BasePage {

    private HeaderModule headerModule;
    private LaptopFilterModule laptopFilterModule;

    private By priceOfItems = By.xpath("//span[contains(@class, 'price-value')]");

    public LaptopPricePage(WebDriver driver) {
        super(driver);
        this.headerModule = new HeaderModule(driver);
        this.laptopFilterModule = new LaptopFilterModule(driver);
    }

    public List<Integer> getActualPricesOfItems() {
        pause(5);
        waitForElementsToVisible(priceOfItems);
        return driver.findElements(priceOfItems)
                .stream()
                .map(e -> e.getText().replaceAll(" ", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
