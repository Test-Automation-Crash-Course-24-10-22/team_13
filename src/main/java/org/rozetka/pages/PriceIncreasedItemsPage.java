package org.rozetka.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.rozetka.locators.PriceIncreasedItemsLocators;

import java.util.List;
import java.util.stream.Collectors;

public class PriceIncreasedItemsPage extends BasePageWithHeader {

    public PriceIncreasedItemsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get actual prices of sorted from lower to higher items")
    public List<Integer> getActualPricesOfSortedFromLowerToHigherItems(){
        pause(5);
        waitForElementsToBeVisible(PriceIncreasedItemsLocators.FROM_LOWER_TO_HIGHER_PRICING_SORTED_ITEMS.getPath());
        return driver.findElements(PriceIncreasedItemsLocators.FROM_LOWER_TO_HIGHER_PRICING_SORTED_ITEMS.getPath())
                .stream()
                .map(e -> e.getText().replaceAll(" ", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
