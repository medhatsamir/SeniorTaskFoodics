package Pages;

import org.bouncycastle.tsp.TSPUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AllVideoGamesPage extends BasePage{
    public AllVideoGamesPage(WebDriver driver) throws AWTException {
        super(driver);
    }

    List<String> sortedPrices= new ArrayList<String>();
    float sum = 0;

    @FindBy(xpath="//div[@class='a-checkbox a-checkbox-fancy aok-float-left apb-browse-refinements-checkbox']")
    private WebElement FreeShippingBtn;
    @FindBy(id="p_n_condition-type/28071525031")
    private WebElement NewCondition;
    @FindBy(id="add-to-cart-button")
    private WebElement AddElementToCart;
    @FindBy(xpath="//span[@class='a-button-text a-declarative']//span[@class='a-dropdown-prompt' and text()='Bestselling']")
    private WebElement SortBtn;
    @FindBy(xpath="//li[@class='a-dropdown-item']//a[@class='a-dropdown-link' and text()='Price: High to Low']")
    private WebElement HighToLow;
    @FindBy(className = "a-price-whole")
    private List<WebElement> Prices;

//    @FindBy(xpath = "//*[contains(@class, 'a-section') and contains(@class, 'a-spacing-none') and contains(@class, 'a-spacing-top-mini')]")
//    private List<WebElement> SecondaryPrices;
    @FindBy(className = "a-button-text")
    private List<WebElement> AddToCart;
    @FindBy(xpath="//span[@class='a-button-inner']/input[@class='a-button-input' and @type='submit' and @aria-labelledby='attachSiNoCoverage-announce']\n")
    private WebElement NoThanksBtn;
    @FindBy(id="nav-cart-count")
    private WebElement Cart;
    @FindBy(id="sc-subtotal-amount-activecart")
    private WebElement TotalAmount;
    @FindBy(xpath="//li[@class='s-list-item-margin-right-adjustment']")
    private WebElement NextBtn;



    public WebElement getTotalAmount() {return TotalAmount;}
    public WebElement getNextBtn() {return NextBtn;}

    public WebElement getFreeShippingBtn() {return FreeShippingBtn;}
    public WebElement getAddElementToCart() {return AddElementToCart;}
    public WebElement getNoThanksBtn() {return NoThanksBtn;}
    public WebElement getCart() {return Cart;}
    public WebElement getNewCondition() {return NewCondition;}
    public WebElement getSortBtn() {return SortBtn;}
    public WebElement getHighToLow() {return HighToLow;}
    public List<WebElement> getPrices() {return Prices;}
    public List<WebElement> getAddToCart() {return AddToCart;}



    public void Print() throws InterruptedException {
//        sortedPrices.add((float)49.99);     //to check if the first highest element is duplicated

    }
    public boolean validateThatTheTotalAmountIsEqualTheSumOfProductsPriceUnderFifteenK(){

        System.out.println(Float.parseFloat(getTotalAmount().getText().replace(",", "").replace("EGP",""))==sum);
        return (Float.parseFloat(getTotalAmount().getText().replace(",", "").replace("EGP",""))==sum);
    }

    public void FilterAndSort() throws InterruptedException {
        ClickOnFreeShippingBtn();
        Thread.sleep(2000);
        this.ScrollDownToSpecificElement(getNewCondition());
//        ClickOnNewCondition();
        Thread.sleep(2000);
        getNewCondition().click();
        this.ScrollDownToSpecificElement(getSortBtn());
        Thread.sleep(1000);
        ClickOnSortBtn();
        ClickOnHighToLow();
    }

    public void AddElementToCart() throws InterruptedException {
        for (int i = 0; i < getPrices().size(); i++) {
            sortedPrices.add((getPrices().get(i).getText()));
            if(Float.parseFloat(getPrices().get(i).getText().replace(",", ""))<15000){
                sum +=Float.parseFloat(getPrices().get(i).getText().replace(",", ""));
                getPrices().get(i).click();
                getAddElementToCart().click();
                getNoThanksBtn().click();
                Thread.sleep(1000);
                driver.navigate().back();
                driver.navigate().back();

//                ValidateCartItem();
            }

        }
        getCart().click();
        Thread.sleep(2000);
        System.out.println(getTotalAmount().getText());
        ValidateCartItem();
    }
    public void ValidateCartItem() throws InterruptedException {
        if(Float.parseFloat(getTotalAmount().getText().replace(",", "").replace("EGP",""))==0){
            driver.navigate().back();
            getNextBtn().click();
            AddElementToCart();
        }
    }


    public void ClickOnFreeShippingBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='a-checkbox a-checkbox-fancy aok-float-left apb-browse-refinements-checkbox']")));
        getFreeShippingBtn().click();

    }

    public void ClickOnNewCondition(){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("p_n_condition-type/28071525031']")));
        getNewCondition().click();

    }

    public void ClickOnSortBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='a-button-text a-declarative']//span[@class='a-dropdown-prompt' and text()='Bestselling']")));
        getSortBtn().click();

    }

    public void ClickOnHighToLow(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='a-dropdown-item']//a[@class='a-dropdown-link' and text()='Price: High to Low']")));
        getHighToLow().click();

    }

}

