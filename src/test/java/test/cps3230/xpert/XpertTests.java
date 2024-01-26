package test.cps3230.xpert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static test.cps3230.xpert.ScenarioSteps.wait;

public class XpertTests {
    private final WebDriver driver;
    private Actions actions;

    public XpertTests(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void launchSite() {
        driver.get("https://www.xpert.mt");
        driver.manage().window().maximize();
//        wait();
        WebElement cookieAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("eu-cookie-ok")));
        cookieAcceptButton.click();
    }

    public void clickAllCategories() {
        WebElement allCategories = driver.findElement(By.className("category-navigation-title"));
        actions.moveToElement(allCategories).perform();
    }
    public void clickCategory(String Category) {
        WebElement categoryLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'with-subcategories') and contains(text(), '" + Category + "')]")));
        actions.moveToElement(categoryLink).perform();
    }
    public void clickSubCategory(String subCategory) {
        WebElement subCategoryLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + subCategory + "']")));
        actions.moveToElement(subCategoryLink).click().perform();
    }
    public String verifySubcategoryPage(String expectedSubcategory) {
        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='page-title']/h1")));


          return pageTitle.getText();

    }
    public int verifyProductCount(int minimumCount){
        List<WebElement> products = driver.findElements(By.cssSelector(".product-grid .item-box"));
        return products.size();

    }
    public String clickFirstProduct(){
        List<WebElement> products = driver.findElements(By.cssSelector(".item-box .product-item"));
        String expectedProductTitle = null;
        if (!products.isEmpty()) {

            WebElement firstProductTitleLink = products.get(0).findElement(By.cssSelector("h2.product-title > a"));
            expectedProductTitle = firstProductTitleLink.getText();
            firstProductTitleLink.click();
        } else {
            fail("No products found on the page.");
        }
        return expectedProductTitle;
    }
    public String verifyCorrectProduct(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-name h1")));

        WebElement productTitleElement = driver.findElement(By.cssSelector(".product-name h1"));
//        String actualProductTitle = productTitleElement.getText();


        return productTitleElement.getText();
    }
    public void searchForProduct(String productName){
        WebElement searchInputBox = driver.findElement(By.id("small-searchterms"));
        searchInputBox.clear();


        searchInputBox.sendKeys(productName);

        WebElement searchButton = driver.findElement(By.className("search-box-button"));
        searchButton.click();
    }
    public String verifyProductListings(String expectedSearchTerm){
        WebElement searchInputField = driver.findElement(By.id("q"));
//        String actualSearchTerm = searchInputField.getAttribute("value");
        return searchInputField.getAttribute("value");
//        assertEquals(expectedSearchTerm, actualSearchTerm, "The search term in the input field does not match the expected search term.");


    }

}
