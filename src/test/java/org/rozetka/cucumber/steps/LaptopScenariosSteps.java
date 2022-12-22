package org.rozetka.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.rozetka.pages.home.HomePage;
import org.rozetka.pages.laptops.LaptopsBrandPage;
import org.rozetka.pages.laptops.LaptopsPage;
import org.rozetka.pages.laptops.LaptopsPricePage;
import org.testng.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LaptopScenariosSteps {

    private WebDriver driver;

    @Given("User open rozetka main page")
    public void openRozetkaMainPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/ua/");
    }

    @When("User search laptops")
    public void userSearchLaptops() {
        new HomePage(driver)
                .onHeaderComponent()
                .searchLaptops();
    }

    @When("User choose apple brand checkbox")
    public void userChooseBrandCheckbox() {
        new LaptopsPage(driver)
                .onLaptopFilterComponent()
                .chooseAppleBrand();
    }

    @When("User filter by min price {int} and max price {int}")
    public void userFilterByMinPriceAndMaxPrice(int minPrice, int maxPrice) {
        new LaptopsPage(driver)
                .onLaptopFilterComponent()
                .filterByPrice(minPrice, maxPrice);
    }

    @When("User input min price {int}")
    public void userInputMinPrice(int minPrice) {
        new LaptopsPage(driver)
                .onLaptopFilterComponent()
                .inputMinPrice(minPrice);
    }

    @When("User Input max price {int}")
    public void userInputMaxPrice(int maxPrice) {
        new LaptopsPage(driver)
                .onLaptopFilterComponent()
                .inputMaxPrice(maxPrice);
    }

    @When("User choose available goods checkbox")
    public void userChooseAvailableGoodsCheckbox() {
        new LaptopsBrandPage(driver)
                .onLaptopFilterComponent()
                .chooseAvailableGoods();
    }

    @When("User select the 'Від дешевих до дорогих' option")
    public void userSelectFromTheCheapestToTheMostExpensiveOption() {
        new LaptopsPage(driver)
                .selectLowerToHigherOption();
    }

    @When("User select the 'Від дорогих до дешевих' option")
    public void userSelectFromTheTheMostExpensiveToTheCheapestOption() {
        new LaptopsPage(driver)
                .selectHigherToLowerOption();
    }

    @Then("All search results contains {string} in goods name")
    public void allSearchResultsContainsBrandInGoodsName(String brand) {
        List<String> laptopTitles = new LaptopsBrandPage(driver)
                .getActualLaptopTitles();
        Assert.assertTrue(laptopTitles.stream().allMatch(t -> t.contains(brand)), "Not all laptop titles contains " + brand);
    }

    @Then("All goods have price from {int} to {int} griven")
    public void allGoodsHavePriceFromMinPriceToMaxPriceGriven(int minPrice, int maxPrice) {
        List<Integer> actualPricesOfItems = new LaptopsPricePage(driver)
                .getActualPricesOfItems();
        Assert.assertTrue(actualPricesOfItems.stream().allMatch(p -> p >= minPrice && p <= maxPrice),
                "Not all prices more than " + minPrice + " and less than " + maxPrice);
    }

    @Then("OK button in price filter module is disabled")
    public void oKButtonInPriceFilterModuleIsDisabled() {
        boolean isOkPriceFilterButtonDisabled = new LaptopsPage(driver)
                .onLaptopFilterComponent()
                .isOkPriceFilterButtonDisabled();
        Assert.assertTrue(isOkPriceFilterButtonDisabled, "Ok price filter button is enabled");
    }

    @Then("All search results contains {string} in goods name and with {string} or {string} label")
    public void allSearchResultsContainsBrandInGoodsNameAndWithAvailabilityLabel(String brand, String availabilityLabel1, String availabilityLabel2) {
        List<String> availabilityOfGoodsList = new LaptopsBrandPage(driver)
                .getAvailabilityOfGoodsList();
        List<String> laptopTitles = new LaptopsBrandPage(driver)
                .getActualLaptopTitles();
        Assert.assertTrue(laptopTitles
                        .stream()
                        .allMatch(t -> t.contains(brand)), "Not all laptop titles contains " + brand);
        Assert.assertTrue(availabilityOfGoodsList
                        .stream()
                        .allMatch(avail -> avail.equals(availabilityLabel1) || avail.equals(availabilityLabel2)),
                "Not all laptop is available after filter");
    }

    @Then("Every next goods is more expensive or equal than the previous")
    public void everyNextGoodsIsMoreExpensiveOrEqualThanThePrevious() {
        List<Integer> actualPricesOfSortedItems = new LaptopsPricePage(driver)
                .getActualPricesOfItems();
        List<Integer> expectedPricesOfSortedItems = actualPricesOfSortedItems
                .stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        Assert.assertEquals(actualPricesOfSortedItems, expectedPricesOfSortedItems, "Goods was not sorted correctly");
    }

    @Then("Every next goods is cheaper or equal than the previous")
    public void everyNextGoodsIsCheaperOrEqualThanThePrevious() {
        List<Integer> actualPricesOfSortedItems = new LaptopsPricePage(driver)
                .getActualPricesOfItems();
        List<Integer> expectedPricesOfSortedItems = actualPricesOfSortedItems
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        Assert.assertEquals(actualPricesOfSortedItems, expectedPricesOfSortedItems, "Goods was not sorted correctly");
    }

    @After
    public void tearDown(){
        if (driver != null)
            driver.quit();
    }
}
