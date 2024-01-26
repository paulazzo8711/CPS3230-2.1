package test.cps3230.xpert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScenarioSteps {
    private static WebDriver driver;
    public static WebDriverWait wait;
    private String expectedProductTitle;

    private XpertTests xpertTests;
    public ScenarioSteps(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Given("I am a user of the website")
    public void iAmAUserOfTheWebsite() {
        xpertTests = new XpertTests(driver);

    }

    @When("I visit the xpert website")
    public void iVisitTheXpertWebsite() {
        xpertTests.launchSite();
    }
    @And("I click on All Categories")
    public void iClickOnAllCategories() {
        xpertTests.clickAllCategories();
    }
    @And("I click on the {string} category")
    public void iClickOnTheCategory(String arg0) {
        xpertTests.clickCategory(arg0);
    }

    @And("I click on the {string} subcategory")
    public void iClickOnTheSubcategory(String arg0) {
        xpertTests.clickSubCategory(arg0);
    }

    @Then("I should be taken to {string} subcategory page")
    public void iShouldBeTakenToSubcategoryPage(String arg0) {
        String actualTitle = xpertTests.verifySubcategoryPage();
        assertEquals(arg0, actualTitle, "The page title does not match the expected category name.");
    }

    @And("the page should show at least {int} products")
    public void thePageShouldShowAtLeastNumProductsProducts(int arg0) {
        int actualProductsNumber = xpertTests.verifyProductCount();
        assertTrue(actualProductsNumber >= arg0, "The category does not show the expected minimum number of products. Expected at least " + arg0 + ", but found " + actualProductsNumber);
    }



    @When("I click on the first product in the results")
    public void iClickOnTheFirstProductInTheResults() {
        expectedProductTitle = xpertTests.clickFirstProduct();
    }

    @Then("I should be taken to the details page for that product")
    public void iShouldBeTakenToTheDetailsPageForThatProduct() {
        String actualProductTitle = xpertTests.verifyCorrectProduct();

        assertEquals(expectedProductTitle, actualProductTitle, "The page title does not match the expected product title.");
    }


    @And("I search for a product using the term {string} product")
    public void iSearchForAProductUsingTheTermProduct(String arg0) {
        xpertTests.searchForProduct(arg0);
    }

    @Then("I should be taken to {string} product results page")
    public void iShouldBeTakenToProductResultsPage() {
       String actualProductTitle = xpertTests.verifyProductListings();
        assertEquals(expectedProductTitle, actualProductTitle, "The page title does not match the expected product title.");
    }
    @AfterAll
    public static void tearDown(){
        if (driver!= null){
            driver.quit();
            driver = null;
        }
    }
}
