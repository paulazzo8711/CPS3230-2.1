package test.cps3230.xpert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class XpertTests {
    WebDriver driver;
    @BeforeEach
    public void setup(){
        //Chrome driver is an environment variable
        driver = new ChromeDriver();
    }
    @AfterEach
    public void teardown(){
        driver.quit();
    }
    @Test
    public void testLoadXpert() {
        driver.get("https://www.xpert.mt");

    }
}
