import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Excel.DataDriven;
import PageObject.CartPageObject;
import PageObject.CheckOutOverviewObject;
import PageObject.CheckOutPageObject;
import PageObject.CompleteOrderObject;
import PageObject.LandingPage;
import PageObject.ProductCatalogue;
import PageObject.ProductList;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CompleteOrder {
	

	String productName = "Sauce Labs Backpack";
	ProductList productlist;
    
	private WebDriver driver;
    private DataDriven d;
    private String username;
    private String password;
    private String lockedusername;
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

    @BeforeMethod
    public void setupTest() {
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.get("https://www.saucedemo.com/");
        landingPage = new LandingPage(driver); // Initialize Page Object
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    
   
    @Test
    public void verifyThankYouMessageAndBackHomeDisplayed() {
    	ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
        productCatalogue.CheckAddtoCart();
         CartPageObject cartpageobject = productCatalogue.goToCartpage();
         CheckOutPageObject checkoutPage =cartpageobject.goTocheckout();
         checkoutPage.enterCheckoutInfo(FirstName, LastName, ZipCode);
         //
        CheckOutOverviewObject  checkoutOverview = checkoutPage.goToCheckOutOverview();
        //checkoutOverview.clickFinish();
        CompleteOrderObject completeorder = checkoutOverview.goToCompeleOrder();
        Assert.assertTrue(completeorder.isThankYouMessageDisplayed(), "Thank you message is not displayed.");
        Assert.assertTrue(completeorder.isBackHomeButtonDisplayed(), "Back Home button is not displayed.");
    }

    @Test
    public void verifyBackHomeRedirectsToInventoryPage() {
    	ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
        productCatalogue.CheckAddtoCart();
         CartPageObject cartpageobject = productCatalogue.goToCartpage();
         CheckOutPageObject checkoutPage =cartpageobject.goTocheckout();
         checkoutPage.enterCheckoutInfo(FirstName, LastName, ZipCode);
        CheckOutOverviewObject  checkoutOverview = checkoutPage.goToCheckOutOverview();
       // checkoutOverview.clickFinish();
        CompleteOrderObject completeorder = checkoutOverview.goToCompeleOrder();
        completeorder.VerifyBackHomeButton();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "User was not redirected to the inventory page after clicking 'Back Home'.");
    }
    
    
    

}
