package org.rozetka.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.rozetka.locators.LaptopsLocators;
import org.rozetka.pages.components.LaptopFilterComponent;

import java.util.List;
import java.util.stream.Collectors;

public class LaptopsPage extends BasePage {

    private LaptopFilterComponent laptopFilterComponent;

    private static final String FROM_LOWER_TO_HIGHER_OPTION  = "Від дешевих до дорогих";

    public LaptopsPage(WebDriver driver) {
        super(driver);
        this.laptopFilterComponent = new LaptopFilterComponent(driver);
    }

    public LaptopFilterComponent onLaptopFilterComponent() {
        return laptopFilterComponent;
    }

    public PriceIncreasedItemsPage selectLowerToHigherOption(){
        waitForElementToBeVisible(LaptopsLocators.SORT_FIELD.getPath());
        Select select = new Select(driver.findElement(LaptopsLocators.SORT_FIELD.getPath()));
        waitForElementToBeVisible(LaptopsLocators.FROM_LOWER_TO_HIGHER_OPTION.getPath());
        select.selectByVisibleText(FROM_LOWER_TO_HIGHER_OPTION);
        return new PriceIncreasedItemsPage(driver);
    }

    public List<String> getActualLaptopTitles(){
        waitForElementsToBeVisible(LaptopsLocators.LAPTOP_TITLE.getPath());
        return driver.findElements(LaptopsLocators.LAPTOP_TITLE.getPath())
                .stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }
}
