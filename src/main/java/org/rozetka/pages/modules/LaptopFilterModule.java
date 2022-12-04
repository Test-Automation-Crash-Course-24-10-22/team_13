package org.rozetka.pages.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.rozetka.pages.BasePage;
import org.rozetka.pages.LaptopBrandsPage;
import org.rozetka.pages.LaptopPricePage;
import org.rozetka.pages.LaptopsPage;

public class LaptopFilterModule extends BasePage {

    private By brandInput = By.xpath("//div[@data-filter-name='producer']//input[@type='search']");
    private By appleCheckBox = By.xpath("//a[@data-id='Apple']");

    private By microsoftCheckBox = By.xpath("//a[@data-id='Microsoft']");

    private By availableGoodsCheckBox = By.xpath("//a[@data-id='Є в наявності']");

    private By priceMinInput = By.xpath("//input[@formcontrolname='min']");

    private By priceMaxInput = By.xpath("//input[@formcontrolname='max']");

    private By okPriceFilterButton = By.xpath("//input[@formcontrolname='max']//following-sibling::button[contains(text(), 'Ok')]");

    private static final String APPLE = "Apple";
    private static final String MICROSOFT = "Microsoft";

    public LaptopFilterModule(WebDriver driver) {
        super(driver);
    }

    public LaptopBrandsPage chooseAppleBrand(){
        waitForElementToVisible(brandInput);
        driver.findElement(brandInput).click();
        driver.findElement(brandInput).sendKeys(APPLE);
        waitForElementsToVisible(appleCheckBox);
        driver.findElement(appleCheckBox).click();
        return new LaptopBrandsPage(driver);
    }

    public LaptopBrandsPage chooseAvailableGoods(){
        waitForElementsToVisible(availableGoodsCheckBox);
        driver.findElement(availableGoodsCheckBox).click();
        return new LaptopBrandsPage(driver);
    }

    public LaptopPricePage filterByPrice(int min, int max) {
        inputMinPrice(min);
        inputMaxPrice(max);
        clickOkPriceFilterButton();
        return new LaptopPricePage(driver);
    }

    public void inputMinPrice(int minPrice) {
        waitForElementToVisible(priceMinInput);
        driver.findElement(priceMinInput).clear();
        driver.findElement(priceMinInput).sendKeys(String.valueOf(minPrice));
    }

    public void inputMaxPrice(int maxPrice) {
        waitForElementToVisible(priceMaxInput);
        driver.findElement(priceMaxInput).clear();
        driver.findElement(priceMaxInput).sendKeys(String.valueOf(maxPrice));
    }

    public void clickOkPriceFilterButton() {
        waitForElementBeClickable(okPriceFilterButton);
        driver.findElement(okPriceFilterButton).click();
    }

    public boolean isOkPriceFilterButtonDisabled() {
        waitForElementsToVisible(okPriceFilterButton);
        return !driver.findElement(okPriceFilterButton).isEnabled();
    }
}
