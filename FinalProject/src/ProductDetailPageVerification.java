import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import Excel.DataDriven;
import PageObject.LandingPage;
import PageObject.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductDetailPageVerification {

	String productName = "Sauce Labs Backpack";
    
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


  
    @Test
    public void AddToCartDetails() throws InterruptedException  {
        //Verify if the 'Add to Cart' button is clickable and if the cart count updates.
        ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
        productCatalogue.VerifytheProductDetailPage(productName);
        productCatalogue.VerifyaddtocartDet();
        System.out.println( productCatalogue.VerifyCount());
    }
    


    @Test
    public void RemoveCartButton() throws InterruptedException  {
        //Verify if the 'Add to Cart' button is clickable and if the cart count updates.
        ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
        productCatalogue.VerifytheProductDetailPage(productName);
        productCatalogue.VerifyaddtocartDet();
        productCatalogue.removeItemFromCart();
        System.out.println(productCatalogue.VerifyCount());
    }
    

    @Test
    public void DisplayProduct() throws InterruptedException  {
        //Verify if the 'Add to Cart' button is clickable and if the cart count updates.
        ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
        productCatalogue.VerifytheProductDetailPage(productName);
        
        
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  /*  public void verifyProductDetailPage() {
        // Add all items to the cart
        productCatalogue.addAllItemsToCart();
        
        // Dynamically calculate the expected cart count (based on the number of items on the page)
        int expectedCount = productCatalogue.addToCartButtons.size(); 
        
        // Assert that the cart count matches the number of items added
        Assert.assertTrue(productCatalogue.verifyCartItemCount(expectedCount), 
            "Failed to update cart count to " + expectedCount + " on product details page.");
    }

    @Test
    public void verifyCartCountMultipleItems() {
        // Add all items to the cart
        productCatalogue.addAllItemsToCart();
        
        // Get the expected count of items from the "Add to Cart" buttons available on the page
        int expectedCount = productCatalogue.addToCartButtons.size();
        
        // Get the actual cart item count
        int cartCount = productCatalogue.getCartItemCount();
        
        // Assert that the cart count matches the expected count
        Assert.assertEquals(cartCount, expectedCount, "Cart count did not update correctly! Expected: " + expectedCount + " but found: " + cartCount);
    }

    @Test
    public void verifyAddAndRemoveButtonsOnDetailPage() {
        // Add all items to the cart
        productCatalogue.addAllItemsToCart();
        
        // Calculate the expected count after removal (one item removed)
        int expectedCountAfterRemove = productCatalogue.addToCartButtons.size() - 1;
        
        // Remove one item from the cart
        productCatalogue.removeItemFromCart();
        
        // Get the updated cart count
        int cartCount = productCatalogue.getCartItemCount();
        
        // Assert that the cart count is correct after removing one item
        Assert.assertEquals(cartCount, expectedCountAfterRemove, 
            "Cart count is incorrect after removing an item. Expected " + expectedCountAfterRemove + " but found " + cartCount);
    }*/
}
