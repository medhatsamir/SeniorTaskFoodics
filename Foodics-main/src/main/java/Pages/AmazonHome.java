package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;

public class AmazonHome extends BasePage {

    public AmazonHome(WebDriver driver) throws AWTException {
        super(driver);
    }

    @FindBy(id="nav-logo-sprites")
    private WebElement AmazonLogo;
    @FindBy(className="nav-arrow")
    private WebElement TranslateLogo;
    @FindBy(id="nav-hamburger-menu")
    private WebElement AllMenu;
    @FindBy(xpath="//*[@id=\"hmenu-content\"]/ul[32]/li[3]/a")
    private WebElement AllVideoGames;
    @FindBy(xpath="//a[@class='hmenu-item' and @data-menu-id='16']")
    private WebElement VideoGames;
    @FindBy(xpath="//a[contains(@class, 'hmenu-compressed-btn')]")
    private WebElement SeeAllBtn;
    @FindBy(xpath="//label[input[@name='lop' and @value='en_AE']]//span[contains(text(), 'English')]")
    private WebElement EnglishLogo;
    @FindBy(id="icp-save-button")
    private WebElement SaveChangesBtn;



    public WebElement GetAmazonLogo() {return AmazonLogo;}
    public WebElement getTranslateLogo() {return TranslateLogo;}
    public WebElement getAllMenu() {return AllMenu;}
    public WebElement getAllVideoGames() {return AllVideoGames;}
    public WebElement getVideoGames() {return VideoGames;}
    public WebElement getSeeAllBtn() {return SeeAllBtn;}
    public WebElement getEnglishLogo() {return EnglishLogo;}
    public WebElement getSaveChangesBtn() {return SaveChangesBtn;}



    public void ClickOnAllMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-hamburger-menu")));
        getAllMenu().click();

    }
    public void ClickOnSeeAllBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'hmenu-compressed-btn')]")));
        getSeeAllBtn().click();

    }
    public void ClickOnVideoGames(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='hmenu-item' and @data-menu-id='16']")));
        getVideoGames().click();

    }
    public void ClickOnAllVideoGames(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hmenu-content\"]/ul[32]/li[3]/a")));
        getAllVideoGames().click();

    }


    public void ScrollDown(WebElement x){
        this.ScrollDownToSpecificElement(x);
    }


    public void ValidateTheHomePageInEnglishLanguage() throws InterruptedException {
        GetAmazonLogo().click();
        getTranslateLogo().click();
        getEnglishLogo().click();
        getSaveChangesBtn().click();
    }

    public AllVideoGamesPage NavigateToAllVideoGamesPage() throws AWTException, InterruptedException {
        Thread.sleep(1000);
        ClickOnAllMenu();
//        Thread.sleep(1000);
        ScrollDown(getSeeAllBtn());
        ClickOnSeeAllBtn();
//        Thread.sleep(1000);
        ClickOnVideoGames();
//        Thread.sleep(1000);
        ClickOnAllVideoGames();

        return new AllVideoGamesPage(driver);
    }








}
