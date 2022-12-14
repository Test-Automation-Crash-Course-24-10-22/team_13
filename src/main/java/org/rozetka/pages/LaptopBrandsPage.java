package org.rozetka.pages;

import org.openqa.selenium.WebDriver;
import org.rozetka.locators.LaptopBrandsLocators;
import org.rozetka.pages.components.LaptopFilterComponent;

import java.util.List;
import java.util.stream.Collectors;

public class LaptopBrandsPage extends BasePageWithHeader {

    private LaptopFilterComponent laptopFilterComponent;

    public LaptopBrandsPage(WebDriver driver) {
        super(driver);
        this.laptopFilterComponent = new LaptopFilterComponent(driver);
    }

    public LaptopFilterComponent onLaptopFilterComponent() {
        return laptopFilterComponent;
    }

    public List<String> getActualLaptopTitles(){
        pause(5);
        waitForElementsToBePresent(LaptopBrandsLocators.LAPTOP_TITLE.getPath());
        return driver.findElements(LaptopBrandsLocators.LAPTOP_TITLE.getPath())
                .stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }

    public List<String> getAvailabilityOfGoodsList() {
        pause(5);
        waitForElementsToBeVisible(LaptopBrandsLocators.AVAILABILITY_OF_GOODS.getPath());
        return driver.findElements(LaptopBrandsLocators.AVAILABILITY_OF_GOODS.getPath())
                .stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }
}
