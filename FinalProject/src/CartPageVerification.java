import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import Excel.DataDriven;
import PageObject.CartPageObject;
import PageObject.LandingPage;
import PageObject.ProductCatalogue;
import PageObject.ProductList;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartPageVerification extends BaseTest {
    
    	String productName = "Sauce Labs Backpack";
    	
        
    	
        private DataDriven d;
        private String username;
        private String password;
        private ArrayList<ArrayList<String>> userData;
        private LandingPage landingPage;

        @BeforeClass
        public void setUp() throws IOException {
            //WebDriverManager.chromedriver().setup();
            
            d = new DataDriven();
            userData = d.getData();
            username = userData.get(0).get(0);
            password = userData.get(0).get(1);
        }

        
        
       @Test
        public void AccesstheCartPage() throws IOException {
            // Log in and navigate to the product catalog
    	   LandingPage landingPage = new LandingPage(driver);
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
            productCatalogue.CheckAddtoCart();
            CartPageObject cartpageobject = productCatalogue.VerifyCartpage();

            // Now you can interact with CartPageObject (e.g., verifying cart contents)
            // Example: cartpageobject.verifyCartContent();
        }
        
        //
        
     @Test
        public void verifyCartDetailsDisplayed() throws InterruptedException, IOException {
        	  LandingPage landingPage = new LandingPage(driver);
            ProductCatalogue productCatalogue = landingPage.loginApplication("standard_user", "secret_sauce"); //
            productCatalogue.CheckAddtoCart();

            CartPageObject cartPage = productCatalogue.VerifyCartpage();
            Assert.assertTrue(cartPage.isCartDetailsDisplayed(), "Required cart details are not displayed!");
        }
        
        
        
        ////////////

        @Test
        public void verifyContinueShoppingFunctionality() throws InterruptedException, IOException {
        	LandingPage landingPage = new LandingPage(driver);
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
            productCatalogue.CheckAddtoCart();

            CartPageObject cartPage = productCatalogue.VerifyCartpage();
            cartPage.clickContinueShopping();

            Assert.assertTrue(cartPage.isBackOnProductPage(), "User was not redirected to the product page!");
        }

      @Test
        public void verifyAddedProductInCart() throws InterruptedException, IOException {
    	  LandingPage landingPage = new LandingPage(driver);
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
            productCatalogue.CheckAddtoCart();

            CartPageObject cartPage = productCatalogue.VerifyCartpage();

            Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), "Added product is not displayed in the cart!");
        }

        @Test
        public void verifyMultipleProductsInCart() throws InterruptedException, IOException {
        	  LandingPage landingPage = new LandingPage(driver);
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
            productCatalogue.addAllItemsToCart();

            CartPageObject cartPage = productCatalogue.VerifyCartpage();

            Assert.assertTrue(cartPage.areMultipleProductsInCart(), "Multiple products are not displayed in the cart!");
        }

        @Test
        public void verifyCartProductDetails() throws InterruptedException, IOException {
        	  LandingPage landingPage = new LandingPage(driver);
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
            //
            productCatalogue.CheckAddtoCart();
            productCatalogue.CheckProductFeatures();

            CartPageObject cartPage = productCatalogue.VerifyCartpage();

            Assert.assertTrue(cartPage.areProductDetailsCorrect(), "Product details are missing in the cart!");
        } 



        @Test
        public void verifyRemoveAllItemsFromCart() throws InterruptedException, IOException {
        	  LandingPage landingPage = new LandingPage(driver);
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
          //
            productCatalogue.addItemToCartMultipleTimes("sauce-labs-backpack");
           productCatalogue.addItemToCartMultipleTimes("sauce-labs-bolt-t-shirt");
            CartPageObject cartPage = productCatalogue.VerifyCartpage();
            cartPage.removeAllItemsFromCart();
            Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after removing all items!"); 
            
        }
}

