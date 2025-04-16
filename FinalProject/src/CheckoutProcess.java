import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import Excel.DataDriven;
import PageObject.CartPageObject;
import PageObject.CheckOutPageObject;
import PageObject.LandingPage;
import PageObject.ProductCatalogue;
import PageObject.ProductList;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckoutProcess  extends BaseTest{

	
	String productName = "Sauce Labs Backpack";
	//ProductList productlist;
    
	
    private DataDriven d;
    private String username;
    private String password;
    private ArrayList<ArrayList<String>> userData;
    private LandingPage landingPage;
    private String LastName;
    private String FirstName;
    private String ZipCode;

   @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        
        d = new DataDriven();
        userData = d.getData();
        username = userData.get(0).get(0);  
        password = userData.get(0).get(1); 
        FirstName = userData.get(0).get(2);
        LastName = userData.get(0).get(3);
        ZipCode = userData.get(0).get(4);
    } 
   

  
    @Test
    public void VerifyDisplayYourInfoPage()
    {
    	  LandingPage landingPage = new LandingPage(driver);
    	 ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
        productCatalogue.CheckAddtoCart();
         CartPageObject cartpageobject = productCatalogue.goToCartpage();
         cartpageobject.goTocheckout();
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
         Assert.assertTrue(driver.findElement(By.id("first-name")).isDisplayed(), "Error: First Name is required");
         Assert.assertTrue(driver.findElement(By.id("last-name")).isDisplayed(), "Error: Last Name is required");
         Assert.assertTrue(driver.findElement(By.id("postal-code")).isDisplayed(), "Error: Postal Code is required");
     }
    
    
    @Test 
    
    public void  VerifyCancelButton()
    {
    	  LandingPage landingPage = new LandingPage(driver);
    	 ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
    	    productCatalogue.CheckAddtoCart();
    	    CartPageObject cartPage = productCatalogue.goToCartpage();
    	    CheckOutPageObject checkoutPage = cartPage.goTocheckout();
    	    checkoutPage.clickCancel();
          
    }
    
    //
    @Test
    public void verifyFirstNameBlank() {
    	  LandingPage landingPage = new LandingPage(driver);
    	ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
	    productCatalogue.CheckAddtoCart();
	    CartPageObject cartPage = productCatalogue.goToCartpage();
	    CheckOutPageObject checkoutPage = cartPage.goTocheckout();
        checkoutPage.enterCheckoutInfo("", "Hilario", "3016");
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Error"), "Error: First Name is required.");
    }

    @Test
    public void verifyLastNameBlank() {
    	
    	  LandingPage landingPage = new LandingPage(driver);
    	ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
	    productCatalogue.CheckAddtoCart();
	    CartPageObject cartPage = productCatalogue.goToCartpage();
	    CheckOutPageObject checkoutPage = cartPage.goTocheckout();
        checkoutPage.enterCheckoutInfo("Alexandra", "", "3016");
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Error"), "Error: Last Name is required.");
    }

    @Test
    public void verifyZipBlank() {
    	  LandingPage landingPage = new LandingPage(driver);
    	ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
	    productCatalogue.CheckAddtoCart();
	    CartPageObject cartPage = productCatalogue.goToCartpage();
	    CheckOutPageObject checkoutPage = cartPage.goTocheckout();
        checkoutPage.enterCheckoutInfo("Alexandra", "Hilario", "");
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Error"), "Error: Postal Code is required.");
    }

    @Test
    public void verifyOnlyFirstNameEntered() {
    	  LandingPage landingPage = new LandingPage(driver);
        ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
	    productCatalogue.CheckAddtoCart();
	    CartPageObject cartPage = productCatalogue.goToCartpage();
	    CheckOutPageObject checkoutPage = cartPage.goTocheckout();
        checkoutPage.enterCheckoutInfo("Alexandra", "", "");
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Error"), "Error: Last Name is required.");
    }

    @Test
    public void verifyOnlyLastNameEntered() {
    	  LandingPage landingPage = new LandingPage(driver);
    	ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
	    productCatalogue.CheckAddtoCart();
	    CartPageObject cartPage = productCatalogue.goToCartpage();
	    CheckOutPageObject checkoutPage = cartPage.goTocheckout();
        checkoutPage.enterCheckoutInfo("", "Hilario", "");
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Error"), "Expected error for incomplete form.");
    }

    @Test
    public void verifyOnlyZipEntered() {
    	  LandingPage landingPage = new LandingPage(driver);
    	ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
	    productCatalogue.CheckAddtoCart();
	    CartPageObject cartPage = productCatalogue.goToCartpage();
	    CheckOutPageObject checkoutPage = cartPage.goTocheckout();
        checkoutPage.enterCheckoutInfo("", "", "3016");
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Error"), "Expected error for incomplete form.");
    }

    @Test
    public void verifyAllFieldsFilled() {
    	  LandingPage landingPage = new LandingPage(driver);
    	ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
	    productCatalogue.CheckAddtoCart();
	    CartPageObject cartPage = productCatalogue.goToCartpage();
	    CheckOutPageObject checkoutPage = cartPage.goTocheckout();
        checkoutPage.enterCheckoutInfo("Alexandra", "Hilario", "3016");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"), "Did not proceed to next checkout step.");
    } 
    
    

    }

