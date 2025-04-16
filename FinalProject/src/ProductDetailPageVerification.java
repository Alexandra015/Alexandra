import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import BaseTest.BaseTest;
import Excel.DataDriven;
import PageObject.LandingPage;
import PageObject.ProductCatalogue;
import PageObject.ProductList;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductDetailPageVerification extends BaseTest{

	String productName = "Sauce Labs Backpack";
	
	
    private DataDriven d;
    private String username;
    private String password;
    private ArrayList<ArrayList<String>> userData;
    private LandingPage landingPage;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        
        d = new DataDriven();
        userData = d.getData();
        username = userData.get(0).get(0);  
        password = userData.get(0).get(1); 
        }
        
 

    @Test
    public void AddToCartDetails() throws InterruptedException  {
    	 LandingPage landingPage = new LandingPage(driver);
        //Verify if the 'Add to Cart' button is clickable and if the cart count updates.
        ProductCatalogue productCatalogue =landingPage.loginApplication(username, password);
        productCatalogue.clickOnProduct(1);
        Assert.assertTrue(productCatalogue.isProductNameDisplayed(), "Product name is not displayed.");
        Assert.assertTrue(productCatalogue.isProductImgDisplayed(), "Product image is not displayed.");
        Assert.assertTrue(productCatalogue.isProductDecsDisplayed(), "Product description is not displayed.");
        Assert.assertTrue(productCatalogue.isProductPriceDisplayed(), "Product price is not displayed.");
       // Assert.assertTrue(productCatalogue.isAddButtonDisplayed(), "Add to Cart button is not displayed.");

        productCatalogue.clickAddButtonDetailPage();
        // Wait for the Remove button to be visible after adding the product
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Use Duration.ofSeconds() in Selenium 4
        wait.until(ExpectedConditions.visibilityOf(productCatalogue.removebuttondetail));
        Assert.assertTrue(productCatalogue.isRemoveButtonDisplayed(), "Remove button not displayed after adding.");

        // Remove from Cart
        productCatalogue.clickRemoveButtonDetailPage();
        // Wait for the Add button to be visible again after removing the product
        wait.until(ExpectedConditions.visibilityOf(productCatalogue.addbuttondetail));
        Assert.assertTrue(productCatalogue.isAddButtonDisplayed(), "Add to Cart button not visible after removing.");
    
       // productCatalogue.VerifytheProductDetailPage(productName);
        productCatalogue.VerifyaddtocartDet();
        System.out.println( productCatalogue.VerifyCount());
        
    }
}
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  