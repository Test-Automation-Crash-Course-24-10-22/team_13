package org.rozetka.laptop_tests;

import io.qameta.allure.*;
import org.rozetka.BaseTest;
import org.rozetka.pages.home.HomePage;
import org.rozetka.pages.laptops.LaptopsBrandPage;
import org.rozetka.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class LaptopFilteringTest extends BaseTest {

    private static final String APPLE = TestDataReader.get().getAppleBrand();
    private static final int MIN_PRICE = TestDataReader.get().getMinPrice();
    private static final int MAX_PRICE = TestDataReader.get().getMaxPrice();
    private static final String AVAILABILITY_OF_GOODS_IS_AVAILABLE = "Є в наявності";
    private static final String AVAILABILITY_OF_GOODS_READY_FOR_DELIVERY = "Готовий до відправлення";

    @Test(priority = 1)
    @Owner("Denis Pitsul")
    @Description("Verify if user can filter laptops from apple brand")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://github.com/Test-Automation-Crash-Course-24-10-22/team_13/issues/9")
    @Issue("9")
    public void filterWithAppleLaptopsTest() {
        List<String> laptopTitles = new HomePage(driver)
                .onHeaderComponent()
                .searchLaptops()
                .onLaptopFilterComponent()
                .chooseAppleBrand()
                .getActualLaptopTitles();
        Assert.assertTrue(laptopTitles.stream().allMatch(t -> t.contains(APPLE)), "Not all laptop titles contains " + APPLE);
    }

    @Test(priority = 2)
    @Owner("Denis Pitsul")
    @Description("Verify if user can filter with 20000 min price and 30000 max price")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://github.com/Test-Automation-Crash-Course-24-10-22/team_13/issues/10")
    @Issue("10")
    public void filterWith20000MinPriceAnd30000MaxPriceTest() {
        List<Integer> actualPricesOfItems = new HomePage(driver)
                .onHeaderComponent()
                .searchLaptops()
                .onLaptopFilterComponent()
                .filterByPrice(MIN_PRICE, MAX_PRICE)
                .getActualPricesOfItems();
        Assert.assertTrue(actualPricesOfItems.stream().allMatch(p -> p >= MIN_PRICE && p <= MAX_PRICE),
                "Not all prices more than " + MIN_PRICE + " and less than " + MAX_PRICE);
    }

    @Test(priority = 3)
    @Owner("Denis Pitsul")
    @Description("Verify that filtering with 30000 min price and 20000 max price is not allowed")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://github.com/Test-Automation-Crash-Course-24-10-22/team_13/issues/11")
    @Issue("11")
    public void filterWith30000MinPriceAnd20000MaxPriceIsNotAllowedTest() {
        boolean isOkPriceFilterButtonDisabled = new HomePage(driver)
                .onHeaderComponent()
                .searchLaptops()
                .onLaptopFilterComponent()
                .inputMinPrice(MAX_PRICE)
                .inputMaxPrice(MIN_PRICE)
                .isOkPriceFilterButtonDisabled();
        Assert.assertTrue(isOkPriceFilterButtonDisabled, "Ok price filter button is enabled");
    }

    @Test(priority = 4)
    @Owner("Denis Pitsul")
    @Description("Verify if user can filter only available laptops from apple brand")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://github.com/Test-Automation-Crash-Course-24-10-22/team_13/issues/14")
    @Issue("14")
    public void filterOnlyAvailableLaptopsFromAppleTest() {
        SoftAssert softAssert = new SoftAssert();
        LaptopsBrandPage laptopsBrandPage = new HomePage(driver)
                .onHeaderComponent()
                .searchLaptops()
                .onLaptopFilterComponent()
                .chooseAppleBrand();
        List<String> laptopTitles = laptopsBrandPage.getActualLaptopTitles();
        softAssert.assertTrue(laptopTitles
                        .stream()
                        .allMatch(t -> t.contains(APPLE)), "Not all laptop titles contains " + APPLE);

        List<String> availabilityOfGoodsList = laptopsBrandPage
                .onLaptopFilterComponent()
                .chooseAvailableGoods()
                .getAvailabilityOfGoodsList();
        laptopTitles = laptopsBrandPage.getActualLaptopTitles();
        softAssert.assertTrue(laptopTitles
                        .stream()
                        .allMatch(t -> t.contains(APPLE)), "Not all laptop titles contains " + APPLE);
        softAssert.assertTrue(availabilityOfGoodsList
                        .stream()
                        .allMatch(avail -> avail.equals(AVAILABILITY_OF_GOODS_IS_AVAILABLE) || avail.equals(AVAILABILITY_OF_GOODS_READY_FOR_DELIVERY)),
                "Not all laptop is available after filter");
        softAssert.assertAll();
    }
}
