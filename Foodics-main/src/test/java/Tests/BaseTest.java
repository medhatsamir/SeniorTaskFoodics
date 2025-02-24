package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class BaseTest {
    public BaseTest() {
    }
    public static WebDriver driver;
    public void browserSetUp(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    @BeforeMethod
    public void setUp() {
        browserSetUp("https://www.amazon.eg/");
    }

    @AfterMethod
    public void quit(){
        driver.quit();
    }
}

