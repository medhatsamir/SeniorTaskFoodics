package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.awt.*;
import java.util.List;

public class AllVideoGamesPage extends BasePage{
    public AllVideoGamesPage(WebDriver driver) throws AWTException {
        super(driver);
    }

    // Sum of Total Prices
    float sum = 0;

    // Locators For All Element
    private By FreeShippingBtn = By.xpath("//div[@class='a-checkbox a-checkbox-fancy aok-float-left apb-browse-refinements-checkbox']");
    private By NewCondition = By.id("p_n_condition-type/28071525031");
    private By SortBtn = By.xpath("//span[@class='a-button-text a-declarative']//span[@class='a-dropdown-prompt' and text()='Bestselling']");
    private By AddElementToCart = By.id("add-to-cart-button");
    private By HighToLow = By.xpath("//li[@class='a-dropdown-item']//a[@class='a-dropdown-link' and text()='Price: High to Low']");
    private By Prices = By.className("a-price-whole");
    private By Cart = By.id("nav-cart-count");
    private By NoThanksBtn = By.xpath("//span[@class='a-button-inner']/input[@class='a-button-input' and @type='submit' and @aria-labelledby='attachSiNoCoverage-announce']");
    private By AddToCart = By.className("a-button-text");
    private By NextBtn = By.xpath("//li[@class='s-list-item-margin-right-adjustment']");
    private By TotalAmount = By.id("sc-subtotal-amount-activecart");
    private By ProceedToBuy = By.xpath("//input[@value='Proceed to checkout']");



    // Getters For All Elements
    public WebElement getTotalAmount() {return driver.findElement(TotalAmount);}
    public WebElement getNextBtn() {return driver.findElement(NextBtn);}
    public WebElement getFreeShippingBtn() {return driver.findElement(FreeShippingBtn);}
    public WebElement getAddElementToCart() {return driver.findElement(AddElementToCart);}
    public WebElement getNoThanksBtn() {return driver.findElement(NoThanksBtn);}
    public WebElement getCart() {return driver.findElement(Cart);}
    public WebElement getNewCondition() {return driver.findElement(NewCondition);}
    public WebElement getSortBtn() {return driver.findElement(SortBtn);}
    public WebElement getHighToLow() {return driver.findElement(HighToLow);}
    public List<WebElement> getPrices() {return driver.findElements(Prices);}
    public List<WebElement> getAddToCart() {return driver.findElements(AddToCart);}
    public WebElement getProceedToBuy() {return driver.findElement(ProceedToBuy);}


    // Clicks for All Elements
    public void ClickOnFreeShippingBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(FreeShippingBtn));
        getFreeShippingBtn().click();
    }
    public void ClickOnNewCondition(){
        wait.until(ExpectedConditions.elementToBeClickable(NewCondition));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getNewCondition());
    }
    public void ClickOnSortBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(SortBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getSortBtn());
    }
    public void ClickOnHighToLow(){
        wait.until(ExpectedConditions.elementToBeClickable(HighToLow));
        getHighToLow().click();
    }
    public void ClickOnCart(){
        wait.until(ExpectedConditions.elementToBeClickable(Cart));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getCart());
        getCart().click();
    }
    public void ClickOnAddElementToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(AddElementToCart));
        getAddElementToCart().click();
    }
    public void ClickOnNoThanksBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(NoThanksBtn));
        getNoThanksBtn().click();
    }
    public void ClickOnNextBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(NextBtn));
        getNextBtn().click();
    }
    public void ClickOnPrices(int x){
        wait.until(ExpectedConditions.elementToBeClickable(Prices));
        getPrices().get(x).click();
    }
    public void ClickOnProceedToBuy(){
        wait.until(ExpectedConditions.elementToBeClickable(ProceedToBuy));
        getProceedToBuy().click();
    }



    // Functions
    public void FilterAndSort() throws InterruptedException {
        ClickOnFreeShippingBtn();
        ScrollDownToSpecificElement(getNewCondition());
        ClickOnNewCondition();
        ScrollDownToSpecificElement(getSortBtn());
        ClickOnSortBtn();
        ClickOnHighToLow();
    }
    public void AddElementToCart() throws InterruptedException {
        for (int i = 0; i < getPrices().size(); i++) {
            if(Float.parseFloat(getPrices().get(i).getText().replace(",", ""))<15000){
                sum += Float.parseFloat(getPrices().get(i).getText().replace(",", ""));
                ClickOnPrices(i);
                ClickOnAddElementToCart();
                ClickOnNoThanksBtn();
                if(getProceedToBuy().isDisplayed()){
                    driver.navigate().back();
                }
                driver.navigate().back();
            }
        }
        ClickOnCart();
        System.out.println(getTotalAmount().getText());
        ValidateCartItem();
    }
    public void ValidateCartItem() throws InterruptedException {
        if(Float.parseFloat(getTotalAmount().getText().replace(",", "").replace("EGP",""))==0){
            driver.navigate().back();
            ClickOnNextBtn();
            AddElementToCart();
        }
    }
    public boolean validateThatTheTotalAmountIsEqualTheSumOfProductsPriceUnderFifteenK(){
        System.out.println(Float.parseFloat(getTotalAmount().getText().replace(",", "").replace("EGP",""))==sum);
        return (Float.parseFloat(getTotalAmount().getText().replace(",", "").replace("EGP",""))==sum);
    }

}

