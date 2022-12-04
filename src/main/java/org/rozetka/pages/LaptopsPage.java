package org.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.rozetka.pages.modules.HeaderModule;
import org.rozetka.pages.modules.LaptopFilterModule;

import java.util.List;
import java.util.stream.Collectors;

public class LaptopsPage extends BasePage {

    private HeaderModule headerModule;
    private LaptopFilterModule laptopFilterModule;

    private By sortField = By.xpath("//select");
    private By fromLowerToHigherOption = By.xpath("//option[contains(text(), 'Від дешевих до дорогих')]");

    private By laptopTitle = By.xpath("//span[@class='goods-tile__title']");

    private static final String FROM_LOWER_TO_HIGHER_OPTION  = "Від дешевих до дорогих";

    public LaptopsPage(WebDriver driver) {
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

    public PriceIncreasedItemsPage selectLowerToHigherOption(){
        waitForElementToVisible(sortField);
        Select select = new Select(driver.findElement(sortField));
        waitForElementToVisible(fromLowerToHigherOption);
        select.selectByVisibleText(FROM_LOWER_TO_HIGHER_OPTION);
        return new PriceIncreasedItemsPage(driver);
    }

    public List<String> getActualLaptopTitles(){
        waitForElementsToVisible(laptopTitle);
        return driver.findElements(laptopTitle)
                .stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }
}
