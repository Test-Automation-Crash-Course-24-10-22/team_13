package org.rozetka;

import org.rozetka.pages.*;
import org.rozetka.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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

    @BeforeClass
    public void setPreconditions() {
        setDriver();
    }

    @Test
    public void filterWithAppleLaptopsTest() {
        HomePage homePage = new HomePage(driver);
        LaptopsPage laptopsPage = homePage.onHeaderComponent().searchLaptops();
        LaptopBrandsPage laptopBrandsPage = laptopsPage.onLaptopFilterComponent().chooseAppleBrand();
        List<String> laptopTitles = laptopBrandsPage.getActualLaptopTitles();
        Assert.assertTrue(laptopTitles.stream().allMatch(t -> t.contains(APPLE)),
                "Not all laptop titles contains " + APPLE);
    }

    @Test
    public void filterWithPriceTest() {
        HomePage homePage = new HomePage(driver);
        LaptopsPage laptopsPage = homePage.onHeaderComponent().searchLaptops();
        LaptopPricePage laptopPricePage = laptopsPage.onLaptopFilterComponent().filterByPrice(MIN_PRICE, MAX_PRICE);
        List<Integer> actualPricesOfItems = laptopPricePage.getActualPricesOfItems();
        Assert.assertTrue(actualPricesOfItems.stream().allMatch(p -> p >= MIN_PRICE && p <= MAX_PRICE),
                "Not all prices more than " + MIN_PRICE + " and less than " + MAX_PRICE);
    }

    @Test
    public void filterWith30000StartPriceAnd20000PriceIsNotAllowedTest() {
        HomePage homePage = new HomePage(driver);
        LaptopsPage laptopsPage = homePage.onHeaderComponent().searchLaptops();
        laptopsPage.onLaptopFilterComponent().inputMinPrice(MAX_PRICE);
        laptopsPage.onLaptopFilterComponent().inputMaxPrice(MIN_PRICE);
        Assert.assertTrue(laptopsPage.onLaptopFilterComponent().isOkPriceFilterButtonDisabled(),
                "Ok price filter button is enabled");
    }

    @Test
    public void sortFromTheCheapestToMostExpensiveTest() {
        HomePage homePage = new HomePage(driver);
        LaptopsPage laptopsPage = homePage.onHeaderComponent().searchLaptops();
        PriceIncreasedItemsPage priceIncreasedItemsPage = laptopsPage.selectLowerToHigherOption();
        List<Integer> actualPricesOfSortedItems = priceIncreasedItemsPage.getActualPricesOfSortedFromLowerToHigherItems();
        List<Integer> expectedPricesOfSortedItems = actualPricesOfSortedItems
                .stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        Assert.assertEquals(actualPricesOfSortedItems, expectedPricesOfSortedItems,
                "Goods was not sorted correctly");
    }

    @Test
    public void filterOnlyAvailableLaptopFromAppleTest() {
        HomePage homePage = new HomePage(driver);
        LaptopsPage laptopsPage = homePage.onHeaderComponent().searchLaptops();
        LaptopBrandsPage laptopBrandsPage = laptopsPage.onLaptopFilterComponent().chooseAppleBrand();
        List<String> laptopTitles = laptopBrandsPage.getActualLaptopTitles();
        Assert.assertTrue(laptopTitles
                        .stream()
                        .allMatch(t -> t.contains(APPLE)),
                "Not all laptop titles contains " + APPLE);
        laptopBrandsPage = laptopBrandsPage.onLaptopFilterComponent().chooseAvailableGoods();
        List<String> availabilityOfGoodsList = laptopBrandsPage.getAvailabilityOfGoodsList();
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
