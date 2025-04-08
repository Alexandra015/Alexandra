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
import PageObject.LandingPage;
import PageObject.ProductCatalogue;
import PageObject.ProductList;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckOutPageOverview {
	

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
    public void verifyAllRequiredDetailsDisplayed() {
    	ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
        productCatalogue.CheckAddtoCart();
         CartPageObject cartpageobject = productCatalogue.goToCartpage();
         CheckOutPageObject checkoutPage =cartpageobject.goTocheckout();
         checkoutPage.enterCheckoutInfo(FirstName, LastName, ZipCode);
        CheckOutOverviewObject  checkoutOverview = checkoutPage.goToCheckOutOverview();
        
        //checkoutPage.clickFinish();
        Assert.assertTrue(checkoutOverview.verifyAllProductDetailsDisplayed(), "Product details are missing.");
        Assert.assertTrue(checkoutOverview.getTotalPriceText().contains("Total"), "Total price is not displayed.");
    }

    @Test
    public void verifyCancelButtonRedirection() {
    	ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
        productCatalogue.CheckAddtoCart();
         CartPageObject cartpageobject = productCatalogue.goToCartpage();
         CheckOutPageObject checkoutPage =cartpageobject.goTocheckout();
         checkoutPage.enterCheckoutInfo(FirstName, LastName, ZipCode);
        CheckOutOverviewObject  checkoutOverview = checkoutPage.goToCheckOutOverview();
        checkoutPage.clickCancel();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Cancel did not redirect to product listing.");
    }
    
    @Test
    public void verifyfinishButtonRedirection() {
    	ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
        productCatalogue.CheckAddtoCart();
         CartPageObject cartpageobject = productCatalogue.goToCartpage();
         CheckOutPageObject checkoutPage =cartpageobject.goTocheckout();
         checkoutPage.enterCheckoutInfo(FirstName, LastName, ZipCode);
         //
        CheckOutOverviewObject  checkoutOverview = checkoutPage.goToCheckOutOverview();
        checkoutOverview.clickFinish();
        Assert.assertTrue(driver.getCurrentUrl().contains("complete"), "Finish did not redirect confirmation page.");
    }
    
    
    

}
