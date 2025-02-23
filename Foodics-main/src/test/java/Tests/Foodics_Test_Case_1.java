package Tests;

import Pages.AllVideoGamesPage;
import Pages.AmazonHome;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class Foodics_Test_Case_1 extends BaseTest{
    AmazonHome amazonHome;
    AllVideoGamesPage allVideoGamesPage;


    @Test
    public void Foodics1() throws AWTException, InterruptedException {
        amazonHome = new AmazonHome(driver); // Create an Object from the start page
        amazonHome.ValidateTheHomePageInEnglishLanguage(); // Validate that we use english as a language and we are in the home page
        allVideoGamesPage = amazonHome.NavigateToAllVideoGamesPage(); // Navigate to the AllVideoGames page and create an Object from it
        allVideoGamesPage.FilterAndSort(); // Filter and sort the results with specific criteria
        allVideoGamesPage.AddElementToCart(); // Add all elements which have price less than 15000 EGP
        Assert.assertTrue(allVideoGamesPage.validateThatTheTotalAmountIsEqualTheSumOfProductsPriceUnderFifteenK()); // Validate that the total amount in the cart item is equal to all elements which have price less than 15000

    }
}
