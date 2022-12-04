package org.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.rozetka.pages.modules.HeaderModule;

import java.util.List;
import java.util.stream.Collectors;

public class PriceIncreasedItemsPage extends BasePage {

    private HeaderModule headerModule;

    private By fromLowerToHigherPricingSortedItems = By.xpath("//span[contains(@class, 'price-value')]");

    public PriceIncreasedItemsPage(WebDriver driver) {
        super(driver);
        this.headerModule = new HeaderModule(driver);
    }

    public HeaderModule onHeaderModule() {
        return headerModule;
    }

    public List<Integer> getActualPricesOfSortedFromLowerToHigherItems(){
        pause(5);
        waitForElementsToVisible(fromLowerToHigherPricingSortedItems);
        return driver.findElements(fromLowerToHigherPricingSortedItems)
                .stream()
                .map(e -> e.getText().replaceAll(" ", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
