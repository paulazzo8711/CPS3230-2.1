package test.cps3230.xpert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class XpertTests {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.xpert.mt");

        WebElement cookieAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("eu-cookie-ok")));

        // Click the cookie acceptance button
        cookieAcceptButton.click();
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
//    @Test
//    public void testLoadXpert() {
//
//        int x=2;
//
//    }
    @Test
    public void testCategoryRechability(){
//        testLoadXpert();
        Actions actions = new Actions(driver);

        WebElement allCategories = driver.findElement(By.className("category-navigation-title"));
        actions.moveToElement(allCategories).perform();

        WebElement audioCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/en/audio')]")));
        actions.moveToElement(audioCategory).perform();

        WebElement headsetsCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Headsets']")));
        headsetsCategory.click();

        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='page-title']/h1")));

        String expectedTitle = "Headsets";
        String actualTitle = pageTitle.getText();

        assertEquals(expectedTitle, actualTitle, "The page title does not match the expected category name.");

        int x=2;
    }
    @Test
    public void testCategoryShowsMinimumProducts() {
        testCategoryRechability();
        int expectedMinimum = 10;

        List<WebElement> products = driver.findElements(By.cssSelector(".product-grid .item-box"));

        assertTrue(products.size() >= expectedMinimum, "The category does not show the expected minimum number of products. Expected at least " + expectedMinimum + ", but found " + products.size());
    }
    @Test
    public void testFirstProductDetailsPage() {
        testCategoryShowsMinimumProducts();
        List<WebElement> products = driver.findElements(By.cssSelector(".item-box .product-item"));
        String expectedProductTitle = null;
        if (!products.isEmpty()) {

            WebElement firstProductTitleLink = products.get(0).findElement(By.cssSelector("h2.product-title > a"));
            expectedProductTitle = firstProductTitleLink.getText();
            firstProductTitleLink.click();
        } else {
            fail("No products found on the page.");
        }



        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-name h1")));

        WebElement productTitleElement = driver.findElement(By.cssSelector(".product-name h1"));
        String actualProductTitle = productTitleElement.getText();

        int x =2;
        // Verify that the product details page has the correct title
        assertEquals(expectedProductTitle, actualProductTitle);
    }

    @Test
    public void testHeadphoneCategory() {

    }
}
