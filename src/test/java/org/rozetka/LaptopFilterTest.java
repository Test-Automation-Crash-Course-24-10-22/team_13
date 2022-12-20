package org.rozetka;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.rozetka.pages.*;
import org.rozetka.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LaptopFilterTest extends BaseTest {

    private static final String APPLE = TestDataReader.get().getAppleBrand();
    private static final int MIN_PRICE = TestDataReader.get().getMinPrice();
    private static final int MAX_PRICE = TestDataReader.get().getMaxPrice();
    private static final String AVAILABILITY_OF_GOODS_IS_AVAILABLE = "Є в наявності";
    private static final String AVAILABILITY_OF_GOODS_READY_FOR_DELIVERY = "Готовий до відправлення";

    @Test
    @Owner("Denis Pitsul")
    @Description("Filter with apple laptops test")
    public void filterWithAppleLaptopsTest() {
        List<String> laptopTitles = new HomePage(driver)
                .onHeaderComponent()
                .searchLaptops()
                .onLaptopFilterComponent()
                .chooseAppleBrand()
                .getActualLaptopTitles();
        Assert.assertTrue(laptopTitles.stream().allMatch(t -> t.contains(APPLE)), "Not all laptop titles contains " + APPLE);
    }

    @Test
    @Owner("Denis Pitsul")
    @Description("Filter with 20000 min price and 30000 max price test")
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

    @Test
    @Owner("Denis Pitsul")
    @Description("Filter with 30000 min price and 20000 max price is not allowed test")
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

    @Test
    @Owner("Denis Pitsul")
    @Description("Sort from the cheapest to most expensive test")
    public void sortFromTheCheapestToMostExpensiveTest() {
        List<Integer> actualPricesOfSortedItems = new HomePage(driver)
                .onHeaderComponent()
                .searchLaptops()
                .selectLowerToHigherOption()
                .getActualPricesOfSortedFromLowerToHigherItems();
        List<Integer> expectedPricesOfSortedItems = actualPricesOfSortedItems
                .stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        Assert.assertEquals(actualPricesOfSortedItems, expectedPricesOfSortedItems, "Goods was not sorted correctly");
    }

    @Test
    @Owner("Denis Pitsul")
    @Description("Filter only available laptops from apple test")
    public void filterOnlyAvailableLaptopsFromAppleTest() {
        LaptopBrandsPage laptopBrandsPage = new HomePage(driver)
                .onHeaderComponent()
                .searchLaptops()
                .onLaptopFilterComponent()
                .chooseAppleBrand();
        List<String> laptopTitles = laptopBrandsPage.getActualLaptopTitles();
        Assert.assertTrue(laptopTitles
                        .stream()
                        .allMatch(t -> t.contains(APPLE)),
                "Not all laptop titles contains " + APPLE);

        List<String> availabilityOfGoodsList = laptopBrandsPage
                .onLaptopFilterComponent()
                .chooseAvailableGoods()
                .getAvailabilityOfGoodsList();
        laptopTitles = laptopBrandsPage.getActualLaptopTitles();
        Assert.assertTrue(laptopTitles
                        .stream()
                        .allMatch(t -> t.contains(APPLE)),
                "Not all laptop titles contains " + APPLE);
        Assert.assertTrue(availabilityOfGoodsList
                        .stream()
                        .allMatch(avail -> avail.equals(AVAILABILITY_OF_GOODS_IS_AVAILABLE) || avail.equals(AVAILABILITY_OF_GOODS_READY_FOR_DELIVERY)),
                "Not all laptop is available after filter");
    }
}
