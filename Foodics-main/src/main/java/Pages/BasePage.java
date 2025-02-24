package Pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class BasePage {

    public static WebDriver driver;
    static JavascriptExecutor js ;
    WebDriverWait wait;

    //Constructor
    public BasePage(WebDriver driver) throws AWTException {
        this.driver = driver;
        js= (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

     public void ScrollDownToSpecificElement(WebElement X){
         js.executeScript("arguments[0].scrollIntoView();", X);
     }

    public void RetryToClick(By X){
        // Retry mechanism
        int retries = 10;
        while (retries > 0) {
            try {
                WebElement element = driver.findElement(X);
                element.click();
                break; // Exit the loop if successful
            } catch (StaleElementReferenceException e) {
                driver.navigate().refresh();
                retries--;
                if (retries == 0) {
                    throw e; // Re-throw the exception if all retries fail
                }
            }
        }
    }

}