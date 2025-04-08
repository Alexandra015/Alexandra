//import actions.ProductActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import Excel.DataDriven;
import PageObject.ProductList;
import PageObject.LandingPage;
import PageObject.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class ProductListingPageVerification {


	String productName = "Sauce Labs Backpack";
	ProductList productlist;
    
	private WebDriver driver;
    private DataDriven d;
    private String username;
    private String password;
    private String lockedusername;
    private ArrayList<ArrayList<String>> userData;
    private LandingPage landingPage;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        
        d = new DataDriven();
        userData = d.getData();
        username = userData.get(0).get(0);
        password = userData.get(0).get(1);
        lockedusername = userData.get(1).get(0);
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
    
   
    // adding item to cart 
    
    @Test
    public void AddtoCart () throws InterruptedException
    {
    	 ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
    	 productCatalogue.CheckAddtoCart();
    	 
    	 Thread.sleep(2000);
    	 String buttonText = productCatalogue.getRemoveButtonText();
         
         // Print the button text (optional)
         System.out.println("Button Text after adding to cart: " + buttonText);

         // Assert that the button text is "Remove" (this ensures that the product was added to the cart)
         Assert.assertEquals(buttonText, "Remove", "Remove button not displayed after adding product.");
      
    }  
    
    
   
    //sorting option
    @Test
    public void verifySortingByNameDesc() {
    	 ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
    	 productCatalogue.sortProductsByNameZtoA();
        Assert.assertTrue(productCatalogue.isProductsSortedByNameDesc(), "Products are not sorted by name Z to A.");
    }  
    
    
    @Test
    public void verifySortingByNameAsc() {
        // This assumes A to Z sorting is default. If not, select it:
    	 ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
        productCatalogue.sortProductsByNameAtoZ();
        Assert.assertTrue(productCatalogue.isProductsSortedByNameAsc(), "Products are not sorted by name A to Z.");
    }

    @Test
    public void verifyProductSortingByPriceLowToHigh() {
    	 ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
    	productCatalogue.sortProductsByPriceLowToHigh();
        Assert.assertTrue(productCatalogue.isProductsSortedByPriceLowToHigh(), "Products not sorted by price (low to high).");
    }
    
    @Test
    public void verifySortingByPriceHighToLow() {
    	 ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
    	 productCatalogue.sortProductsByPriceHighToLow();
        Assert.assertTrue(productCatalogue.isProductsSortedByPriceHighToLow(), "Products not sorted by price (high to low).");
    }
    
    @Test
    public void verifyAllElementsDisplayed ()
    {
    	ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
    	productCatalogue.CheckProductFeatures();
    	
    	
    }
    
    
}
