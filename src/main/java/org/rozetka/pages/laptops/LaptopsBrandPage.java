package org.rozetka.pages.laptops;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.rozetka.locators.LaptopsBrandLocators;
import org.rozetka.pages.BasePageWithHeader;
import org.rozetka.pages.components.LaptopFilterComponent;

import java.util.List;
import java.util.stream.Collectors;

public class LaptopsBrandPage extends BasePageWithHeader {

    private LaptopFilterComponent laptopFilterComponent;

    public LaptopsBrandPage(WebDriver driver) {
        super(driver);
        this.laptopFilterComponent = new LaptopFilterComponent(driver);
    }

    public LaptopFilterComponent onLaptopFilterComponent() {
        return laptopFilterComponent;
    }

    @Step("Get actual laptop titles")
    public List<String> getActualLaptopTitles(){
        pause(5);
        waitForElementsToBeVisible(LaptopsBrandLocators.LAPTOP_TITLE.getPath());
        return driver.findElements(LaptopsBrandLocators.LAPTOP_TITLE.getPath())
                .stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }

    @Step("Get availability of goods list")
    public List<String> getAvailabilityOfGoodsList() {
        pause(5);
        waitForElementsToBeVisible(LaptopsBrandLocators.AVAILABILITY_OF_GOODS.getPath());
        return driver.findElements(LaptopsBrandLocators.AVAILABILITY_OF_GOODS.getPath())
                .stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }
}
