import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Excel.DataDriven;
import PageObject.CartPageObject;
import PageObject.LandingPage;
import PageObject.ProductCatalogue;
import abstractcomponents.AbstractComponent;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CartFunctionalityVerification  {
	    
	    String productName = "Sauce Labs Backpack";
	    
		private WebDriver driver;
	    private DataDriven d;
	    private String username;
	    private String password;
	    private String lockedusername;
	    private ArrayList<ArrayList<String>> userData;
	    private LandingPage landingPage;
	    private CartPageObject cartpageobject;
	   
	    

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
	   /* @Test
	    public void AddToCartDetails() throws InterruptedException  {
	        //Verify if the 'Add to Cart' button is clickable and if the cart count updates.
	        ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
	        productCatalogue.VerifytheProductDetailPage(productName);
	        productCatalogue.VerifyaddtocartDet();
	        System.out.println( productCatalogue.VerifyCount());
	    } */
	    //addtocart 
	    @Test
	    public void Cart() throws InterruptedException  {
	        //Verify if the 'Add to Cart' button is clickable and if the cart count updates.
	        ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
	        productCatalogue.verifyHamburgerMenu();
	    }
	    //verifyif hamburger is visible
	   @Test
	    public void verifyHamburgerMenuAccess() throws InterruptedException {
	    	ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
	       System.out.println(productCatalogue.VeirfyNavbarCheck()); 
	       productCatalogue.OpenNavbar();
	       System.out.println(productCatalogue.NavbarItemsCheck().size());
	       productCatalogue.CloseBar();
	       
	    }
	   
	   //multiple items add to cart 
	  @Test
	    public void verifyCartCountMultipleItems() throws InterruptedException {
	        // Add all items to the cart
		  ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
	        productCatalogue.addAllItemsToCart();
	        Thread.sleep(3000);
	        // Get the actual cart item count
	        int cartCount = productCatalogue.getCartItemCount();
	        System.out.println(cartCount);
	       
	    }
	  

	    
	    
	  
		
}
	    
	   
	    

	  


	
	
	

