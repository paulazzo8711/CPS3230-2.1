package test.cps3230.xpert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


import static test.cps3230.xpert.steps.ScenarioSteps.wait;

public class XpertTests {
    private final WebDriver driver;
    private final Actions actions;

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
        try {
            WebElement allCategories = driver.findElement(By.className("category-navigation-title"));
            actions.moveToElement(allCategories).perform();
        } catch (Exception e) {
            System.err.println("Error clicking on All Categories: " + e.getMessage());
        }
    }
    public void clickCategory(String Category) {
        try {
            WebElement categoryLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'with-subcategories') and contains(text(), '" + Category + "')]")));
            actions.moveToElement(categoryLink).perform();
        } catch (Exception e) {
            System.err.println("Error clicking on category " + Category + ": " + e.getMessage());
        }
    }

    public void clickSubCategory(String subCategory) {
        try {
            WebElement subCategoryLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + subCategory + "']")));
            actions.moveToElement(subCategoryLink).click().perform();
        } catch (Exception e) {
            System.err.println("Error clicking on sub-category " + subCategory + ": " + e.getMessage());
        }
    }
    public String verifySubcategoryPage() {
        try {
            WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='page-title']/h1")));
            return pageTitle.getText();
        } catch (Exception e) {
            System.err.println("Error verifying subcategory page: " + e.getMessage());
            return "";
        }
    }
    public int verifyProductCount() {
        try {
            List<WebElement> products = driver.findElements(By.cssSelector(".product-grid .item-box"));
            return products.size();
        } catch (Exception e) {
            System.err.println("Error verifying product count: " + e.getMessage());
            return 0;
        }
    }
    public String clickFirstProduct() {
        try {
            List<WebElement> products = driver.findElements(By.cssSelector(".item-box .product-item"));
            if (!products.isEmpty()) {
                WebElement firstProductTitleLink = products.get(0).findElement(By.cssSelector("h2.product-title > a"));
                String expectedProductTitle = firstProductTitleLink.getText();
                firstProductTitleLink.click();
                return expectedProductTitle;
            } else {
                System.err.println("No products found on the page.");
                return "";
            }
        } catch (Exception e) {
            System.err.println("Error clicking first product: " + e.getMessage());
            return "";
        }
    }
    public String verifyCorrectProduct() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-name h1")));
            WebElement productTitleElement = driver.findElement(By.cssSelector(".product-name h1"));
            return productTitleElement.getText();
        } catch (Exception e) {
            System.err.println("Error verifying the correct product: " + e.getMessage());
            return "";
        }
    }

    public void searchForProduct(String productName) {
        try {
            WebElement searchInputBox = driver.findElement(By.id("small-searchterms"));
            searchInputBox.clear();
            searchInputBox.sendKeys(productName);

            WebElement searchButton = driver.findElement(By.className("search-box-button"));
            searchButton.click();
        } catch (Exception e) {
            System.err.println("Error searching for product: " + e.getMessage());
        }
    }

    public String verifyProductListings() {
        try {
            WebElement searchInputField = driver.findElement(By.id("q"));
            return searchInputField.getAttribute("value");
        } catch (Exception e) {
            System.err.println("Error verifying product listings: " + e.getMessage());
            return "";
        }
    }


}
