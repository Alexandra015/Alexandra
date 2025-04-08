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

public class CartPageVerification {
    
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
        
        
        @Test
        public void AccesstheCartPage() {
            // Log in and navigate to the product catalog
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
            productCatalogue.CheckAddtoCart();
            CartPageObject cartpageobject = productCatalogue.VerifyCartpage();

            // Now you can interact with CartPageObject (e.g., verifying cart contents)
            // Example: cartpageobject.verifyCartContent();
        }
        
        ///////
        
        @Test
        public void verifyCartDetailsDisplayed() throws InterruptedException {
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
            productCatalogue.CheckAddtoCart();

            CartPageObject cartPage = productCatalogue.VerifyCartpage();
            Assert.assertTrue(cartPage.isCartDetailsDisplayed(), "Required cart details are not displayed!");
        }
        
        
        
        ////////////

        @Test
        public void verifyContinueShoppingFunctionality() throws InterruptedException {
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
            productCatalogue.CheckAddtoCart();

            CartPageObject cartPage = productCatalogue.VerifyCartpage();
            cartPage.clickContinueShopping();

            Assert.assertTrue(cartPage.isBackOnProductPage(), "User was not redirected to the product page!");
        }

       @Test
        public void verifyAddedProductInCart() throws InterruptedException {
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
            productCatalogue.CheckAddtoCart();

            CartPageObject cartPage = productCatalogue.VerifyCartpage();

            Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), "Added product is not displayed in the cart!");
        }

        @Test
        public void verifyMultipleProductsInCart() throws InterruptedException {
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
            productCatalogue.addAllItemsToCart();

            CartPageObject cartPage = productCatalogue.VerifyCartpage();

            Assert.assertTrue(cartPage.areMultipleProductsInCart(), "Multiple products are not displayed in the cart!");
        }

        @Test
        public void verifyCartProductDetails() throws InterruptedException {
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
            //
            productCatalogue.CheckAddtoCart();
            productCatalogue.CheckProductFeatures();

            CartPageObject cartPage = productCatalogue.VerifyCartpage();

            Assert.assertTrue(cartPage.areProductDetailsCorrect(), "Product details are missing in the cart!");
        }

        @Test
        public void verifyRemoveItemFromCart() throws InterruptedException {
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
            productCatalogue.addAllItemsToCart();
            
            //
            CartPageObject cartPage = productCatalogue.VerifyCartpage();
            cartPage.removeItemFromCartPage();

            Assert.assertTrue(cartPage.isCartCountUpdated(0), "Cart count did not update after removing item!");
        }
        

        @Test
        public void verifyRemoveAllItemsFromCart() throws InterruptedException {
            ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
          //
            productCatalogue.addItemToCartMultipleTimes("sauce-labs-backpack", 2);
            productCatalogue.removeItemFromCartPage();

            CartPageObject cartPage = productCatalogue.VerifyCartpage();
            cartPage.removeAllItemsFromCart();

            Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after removing all items!"); 
            
        }
}

    
        
        
        


        
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
        // WebDriverManager will handle driver setup, no need for System.setProperty
   /*     WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            // Navigate to login page
            driver.get("https://www.saucedemo.com/");
            
            // Perform login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            
            // Navigate to product page and add item to cart
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            driver.findElement(By.className("inventory_item_name")).click();

            // ✅ FIX: Use `By.cssSelector()` instead of `By.className()`
            driver.findElement(By.cssSelector(".btn_primary.btn_small.btn_inventory")).click();
            
            // Go to cart page
            driver.findElement(By.className("shopping_cart_link")).click();
            
            // Verify cart page access
            if (driver.getCurrentUrl().contains("https://www.saucedemo.com/cart.html")) {
                System.out.println("✅ Cart page accessed successfully.");
            } else {
                System.out.println("❌ Failed to access cart page.");
            }
            
            // Verify product details
            WebElement productTitle = driver.findElement(By.className("inventory_item_name"));
            WebElement productPrice = driver.findElement(By.className("inventory_item_price"));

            if (productTitle.isDisplayed() && productPrice.isDisplayed()) {
                System.out.println("✅ Product details are displayed correctly.");
            } else {
                System.out.println("❌ Product details are missing.");
                
            }
            
            // Verify 'Continue Shopping' button
            WebElement continueShopping = driver.findElement(By.id("continue-shopping"));
            continueShopping.click();
            
            if (driver.getCurrentUrl().contains("https://www.saucedemo.com/inventory.html")) {
                System.out.println("✅ Navigated back to product page after clicking 'Continue Shopping'.");
            } else {
                System.out.println("❌ Failed to navigate back.");
            }
            
            // Go back to cart page
            
            driver.findElement(By.className("shopping_cart_link")).click();
            
            // Verify removing an item
            driver.findElement(By.id("remove-sauce-labs-backpack")).click();
            List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
            if (cartItems.isEmpty()) {
                System.out.println("✅ Item removed successfully.");
            } else {
                System.out.println("❌ Item removal failed.");
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          
        }
    }
} */
