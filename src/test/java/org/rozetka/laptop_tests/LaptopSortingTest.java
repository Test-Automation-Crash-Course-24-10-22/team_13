package org.rozetka.laptop_tests;

import io.qameta.allure.*;
import org.rozetka.BaseTest;
import org.rozetka.pages.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LaptopSortingTest extends BaseTest {

    @Test(priority = 1)
    @Owner("Denis Pitsul")
    @Description("Verify if a user can sort notebooks from lower to higher price")
    @Severity(SeverityLevel.MINOR)
    @Link("https://github.com/Test-Automation-Crash-Course-24-10-22/team_13/issues/13")
    @Issue("13")
    public void sortFromTheCheapestToTheMostExpensiveTest() {
        List<Integer> actualPricesOfSortedItems = new HomePage(driver)
                .onHeaderComponent()
                .searchLaptops()
                .selectLowerToHigherOption()
                .getActualPricesOfItems();
        List<Integer> expectedPricesOfSortedItems = actualPricesOfSortedItems
                .stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        Assert.assertEquals(actualPricesOfSortedItems, expectedPricesOfSortedItems, "Goods was not sorted correctly");
    }

    @Test(priority = 2)
    @Owner("Denis Pitsul")
    @Description("Verify if a user can sort notebooks from higher to lower price")
    @Severity(SeverityLevel.MINOR)
    @Link("https://github.com/Test-Automation-Crash-Course-24-10-22/team_13/issues/17")
    @Issue("17")
    public void sortFromTheMostExpensiveToTheCheapestTest() {
        List<Integer> actualPricesOfSortedItems = new HomePage(driver)
                .onHeaderComponent()
                .searchLaptops()
                .selectHigherToLowerOption()
                .getActualPricesOfItems();
        List<Integer> expectedPricesOfSortedItems = actualPricesOfSortedItems
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        Assert.assertEquals(actualPricesOfSortedItems, expectedPricesOfSortedItems, "Goods was not sorted correctly");
    }
}
