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
    static Actions act;
    static Robot robot ;
    static JavascriptExecutor js ;
    WebDriverWait wait;

    static HashMap<String, String> Months;


    //Constructor
    public BasePage(WebDriver driver) throws AWTException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        robot = new Robot();
        js= (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));


    }

    //Dismiss Alert
    public void AlertDismiss() {
        driver.switchTo().alert().dismiss();
    }

    //Accept Alert
    public void AlertAccept() {
        driver.switchTo().alert().accept();
    }

    //Get String from Alert
    public void AlertGetText() {
        driver.switchTo().alert().getText();
    }

    //send keys to Alert
    public void AlertSendKeys(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    //screenshot
    public static String takePicture(String name) throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File des = new File("C:\\Users\\msm\\eclipse-workspace\\AutomationSquadTasks\\screenshot\\" + name + ".png");
        FileUtils.copyFile(file, des);
        String errflpath = des.getAbsolutePath();
        return errflpath;
    }

    public static String getPageURL() {
        return driver.getCurrentUrl();
    }

    public static void handleMultipleWindows(int x) {
        ArrayList tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window((String) tabs.get(x));
    }

    public void HoverOnElement(WebElement x) {
        act.moveToElement(x).perform();
    }

    public void DragAndDrop(WebElement From, WebElement To) {
        act.dragAndDrop(From, To).build().perform();
    }
    public static void openNewTabWithKeyBoard() throws AWTException {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_T);
    }
     public static void SwitchToFrame(WebElement X){
        driver.switchTo().frame(X);
     }

     public void PrintTitlesOfWindows(){
         ArrayList tabs = new ArrayList(driver.getWindowHandles());
         for(int i = 0 ; i<tabs.size() ; i++) {
             handleMultipleWindows(i);
             System.out.println(driver.getTitle());
         }
     }

     public void ScrollDownToSpecificElement(WebElement X){
         js.executeScript("arguments[0].scrollIntoView();", X);
     }

     public void ScrollDownToBottomOfPage(){
         js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
     }
     public void ScrollHorizontallyToSpecificElement(WebElement X){
         js.executeScript("arguments[0].scrollIntoView();", X);
     }

     public static String getTitleWithJavaScript(){
         return js.executeScript("return document.title;").toString();
     }

     public void WaitUntilVisible(WebElement X){
         wait.until(ExpectedConditions.visibilityOf(X));
     }
    public void WaitUntilClickable(WebElement X){
        X = wait.until(ExpectedConditions.elementToBeClickable(X));
    }




    public boolean searchSuggestions(WebElement searchList) {
        boolean result = true;
        HashSet<String> set = new HashSet();
        java.util.List<WebElement> searchTwoList = searchList.findElements(By.xpath("*"));
        java.util.List<WebElement> firstList = searchTwoList.get(0).findElements(By.xpath("*"));
        List<WebElement> secondList = searchTwoList.get(1).findElements(By.xpath("*"));
        for (WebElement element : firstList) {
            set.add(element.getText());
        }
        for (WebElement webElement : secondList) {
            set.add(webElement.getText());
        }
        int suggNum = firstList.size() + secondList.size();
        if (set.size() != suggNum) {
            result = false;
        }

        return result;
    }

}