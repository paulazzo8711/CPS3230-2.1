package test.cps3230.xpert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReachabilitySteps {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private XpertTests xpertTests;
    @Given("I am a user of the website")
    public void iAmAUserOfTheWebsite() {
    }

    @When("I visit the xpert website")
    public void iVisitTheXpertWebsite() {
    }

    @And("I click on the {string} category")
    public void iClickOnTheCategory(String arg0) {
    }

    @And("I click on the {string} subcategory")
    public void iClickOnTheSubcategory(String arg0) {
    }

    @Then("I should be taken to {string} subcategory page")
    public void iShouldBeTakenToSubcategoryPage(String arg0) {
    }

    @And("the subcategory should show at least <num-products> products")
    public void theSubcategoryShouldShowAtLeastNumProductsProducts() {
    }

    @When("I click on the first product in the results")
    public void iClickOnTheFirstProductInTheResults() {
    }

    @Then("I should be taken to the details page for that product")
    public void iShouldBeTakenToTheDetailsPageForThatProduct() {
    }


}
