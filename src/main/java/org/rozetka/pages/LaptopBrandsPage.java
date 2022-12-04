package org.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.rozetka.pages.modules.HeaderModule;
import org.rozetka.pages.modules.LaptopFilterModule;

import java.util.List;
import java.util.stream.Collectors;

public class LaptopBrandsPage extends BasePage {

    private HeaderModule headerModule;
    private LaptopFilterModule laptopFilterModule;

    private By laptopTitle = By.xpath("//span[@class='goods-tile__title']");

    private By availabilityOfGoods = By.xpath("//div[contains(@class, 'goods-tile__availability')]");

    public LaptopBrandsPage(WebDriver driver) {
        super(driver);
        this.headerModule = new HeaderModule(driver);
        this.laptopFilterModule = new LaptopFilterModule(driver);
    }

    public HeaderModule onHeaderModule() {
        return headerModule;
    }

    public LaptopFilterModule onLaptopFilterModule() {
        return laptopFilterModule;
    }

    public List<String> getActualLaptopTitles(){
        pause(5);
        waitForElementsToBePresent(laptopTitle);
        return driver.findElements(laptopTitle)
                .stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }

    public List<String> getAvailabilityOfGoodsList() {
        pause(5);
        waitForElementsToVisible(availabilityOfGoods);
        return driver.findElements(availabilityOfGoods)
                .stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }
}
