package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.awt.*;

public class AmazonHome extends BasePage {

    public AmazonHome(WebDriver driver) throws AWTException {
        super(driver);
    }

    // Locators For All Element
    private List<WebElement> elements;
    private By AmazonSubLogo = By.id("nav-bb-logo");
    private By AmazonLogo = By.id("nav-logo-sprites");
    private By TranslateLogo = By.className("nav-arrow");
    private By EnglishLogo = By.xpath("//label[input[@name='lop' and @value='en_AE']]//span[contains(text(), 'English')]");
    private By SaveChangesBtn = By.id("icp-save-button");
    private By AllMenu = By.id("nav-hamburger-menu");
    private By SeeAllBtn = By.xpath("//a[contains(@class, 'hmenu-compressed-btn')]");
    private By VideoGames = By.xpath("//a[@class='hmenu-item' and @data-menu-id='16']");
    private By AllVideoGames = By.xpath("//ul[@class='hmenu hmenu-visible hmenu-translateX']//a[contains(text(), 'All Video Games')]");


    // Getters For All Elements
    public WebElement GetAmazonSubLogo() {return driver.findElement(AmazonSubLogo);}
    public WebElement GetAmazonLogo() {return driver.findElement(AmazonLogo);}
    public WebElement getTranslateLogo() {return driver.findElement(TranslateLogo);}
    public WebElement getEnglishLogo() {return driver.findElement(EnglishLogo);}
    public WebElement getSaveChangesBtn() {return driver.findElement(SaveChangesBtn);}
    public By getAllMenu() {return AllMenu;}
    public WebElement getSeeAllBtn() {return driver.findElement(SeeAllBtn);}
    public WebElement getVideoGames() {return driver.findElement(VideoGames);}
    public WebElement getAllVideoGames() {return driver.findElement(AllVideoGames);}


    public void ClickOnAmazonSubLogo(){
        wait.until(ExpectedConditions.presenceOfElementLocated(AmazonSubLogo));
        wait.until(ExpectedConditions.elementToBeClickable(AmazonSubLogo));
        GetAmazonSubLogo().click();
    }
    public void ClickOnAllMenu(){
        wait.until(ExpectedConditions.presenceOfElementLocated(getAllMenu()));
        wait.until(ExpectedConditions.elementToBeClickable(getAllMenu()));
        RetryToClick(getAllMenu());
    }
    public void ClickOnSeeAllBtn(){
        wait.until(ExpectedConditions.presenceOfElementLocated(SeeAllBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SeeAllBtn));
        getSeeAllBtn().click();
    }
    public void ClickOnVideoGames(){
        wait.until(ExpectedConditions.elementToBeClickable(VideoGames));
        getVideoGames().click();

    }
    public void ClickOnAllVideoGames(){
        wait.until(ExpectedConditions.elementToBeClickable(AllVideoGames));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getAllVideoGames());
    }


    // Functions
    public void ValidateTheHomePageInEnglishLanguage() throws InterruptedException {
        ValidateThatWeAreInRightScreen();
        getTranslateLogo().click();
        getEnglishLogo().click();
        getSaveChangesBtn().click();
    }

    public AllVideoGamesPage NavigateToAllVideoGamesPage() throws AWTException, InterruptedException {
        ClickOnAllMenu();
        ScrollDownToSpecificElement(getSeeAllBtn());
        ClickOnSeeAllBtn();
        ScrollDownToSpecificElement(getVideoGames());
        ClickOnVideoGames();
        ClickOnAllVideoGames();
        return new AllVideoGamesPage(driver);
    }

    public void ValidateThatWeAreInRightScreen(){

        // Find elements matching the locator
        elements = driver.findElements(AmazonSubLogo);

        // Check if the element exists
        if (elements.size() > 0) {
            System.out.println("Element exists!");
            ClickOnAmazonSubLogo();
        } else {
            System.out.println("Element does not exist.");
        }
    }

}
