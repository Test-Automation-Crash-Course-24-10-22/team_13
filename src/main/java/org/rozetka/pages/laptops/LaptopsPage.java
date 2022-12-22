package org.rozetka.pages.laptops;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.rozetka.locators.LaptopsLocators;
import org.rozetka.pages.BasePageWithHeader;
import org.rozetka.pages.components.LaptopFilterComponent;

public class LaptopsPage extends BasePageWithHeader {

    private LaptopFilterComponent laptopFilterComponent;

    private static final String FROM_LOWER_TO_HIGHER_OPTION  = "Від дешевих до дорогих";
    private static final String FROM_HIGHER_TO_LOWER_OPTION  = "Від дорогих до дешевих";

    public LaptopsPage(WebDriver driver) {
        super(driver);
        this.laptopFilterComponent = new LaptopFilterComponent(driver);
    }

    public LaptopFilterComponent onLaptopFilterComponent() {
        return laptopFilterComponent;
    }

    @Step("Select the 'Від дешевих до дорогих' option")
    public LaptopsPricePage selectLowerToHigherOption() {
        pause(5);
        waitForElementToBeVisible(LaptopsLocators.SORT_FIELD.getPath());
        Select select = new Select(driver.findElement(LaptopsLocators.SORT_FIELD.getPath()));
        select.selectByVisibleText(FROM_LOWER_TO_HIGHER_OPTION);
        return new LaptopsPricePage(driver);
    }

    @Step("Select the 'Від дорогих до дешевих' option")
    public LaptopsPricePage selectHigherToLowerOption() {
        pause(5);
        waitForElementToBeVisible(LaptopsLocators.SORT_FIELD.getPath());
        Select select = new Select(driver.findElement(LaptopsLocators.SORT_FIELD.getPath()));
        select.selectByVisibleText(FROM_HIGHER_TO_LOWER_OPTION);
        return new LaptopsPricePage(driver);
    }
}
