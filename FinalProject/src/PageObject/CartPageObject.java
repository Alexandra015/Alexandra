package PageObject;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractcomponents.AbstractComponent;

public class CartPageObject extends AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;

    
    ////Locators

    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamburgerMenu;
    
    @FindBy(xpath = "//button[contains(text(),'Add to cart')]")
    public List<WebElement> addToCartButtons;
    
    //////
    @FindBy(className = "cart_quantity")
    private List<WebElement> cartQuantities;


    @FindBy(className = "cart_item") 
    private List<WebElement> cartItems;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;


    @FindBy(id = "checkout")
    private WebElement checkoutButton;
    
    @FindBy(className = "cart_list")
    private WebElement cartList;
    ///remove button in  cart page
    @FindBy(className = "cart_button")
    private List<WebElement> removeCartButton;
    
    @FindBy(css = "[data-test='shopping-cart-badge']")
    WebElement shoppingCartBadge;
    
    @FindBy(css=".inventory_item_name")
    List <WebElement> products;
    
    @FindBy(css=".inventory_item_desc")
    List <WebElement> productDesc;
    

    // Find all "Remove" buttons on the cart page (or product page) //8
    @FindBy(xpath = "//button[contains(text(),'Remove')]")
    List<WebElement> removeButtons;
    
    @FindBy(css=".inventory_item_price")
    List <WebElement> productPrice;
    
    @FindBy(id="finish")
    private WebElement finishButton;
    

    // Constructor
    public CartPageObject(WebDriver driver) {
    	super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased timeout duration to 15 seconds
        PageFactory.initElements(driver, this);  // Initialize elements
    }
    
    // Method to open the hamburger menu
    public void openHamburgerMenu() {
        try {
            WebElement hamburgerButton = wait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu));
            hamburgerButton.click();
        } catch (NoSuchElementException e) {
            System.out.println("Hamburger menu button not found!");
        }
    }

    // Method to close the hamburger menu
    public void closeHamburgerMenu() {
        try {
            WebElement closeButton = driver.findElement(By.id("react-burger-cross-btn"));
            wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
        } catch (NoSuchElementException e) {
            System.out.println("Close button for hamburger menu not found!");
        }
    }
    
   
 // Waits until all elements in the list become visible within the specified timeout
    public void waitWebElementToAppear(List<WebElement> elements) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
 // Waits until all elements in the list become invisible within the specified timeout
	public void waitWebElementToDisappear(List<WebElement> elements) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}
    //
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	

	public void waitForWebElementToDisappear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
    
    
    public Boolean VerifyNavbarCheck() throws InterruptedException {
 	   waitForWebElementToAppear(hamburgerMenu);
 	  return hamburgerMenu.isDisplayed();
    
 	}
    
    
    
    ///CartPage
    public boolean areMultipleProductsInCart() {
        return cartItems.size() > 1; // If more than one cart item exists, return true
    }
    
    // Verify if product details are correct
    public boolean areProductDetailsCorrect() {
        return cartItems.size() > 0 && !cartQuantities.isEmpty() && !products.isEmpty();
    }

    	// Remove all items from cart
    public void removeAllItemsFromCart() {
        for (WebElement removeButton : removeButtons) {
            removeButton.click();
        }
    }
//*
    // Verify if cart is empty
    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }
        
        
        // Verify required elements are displayed in the cart
        public boolean isCartDetailsDisplayed() {
            return cartQuantities.size() > 0 && products.size() > 0 
                    && continueShoppingButton.isDisplayed() && checkoutButton.isDisplayed();
        }

        // Click 'Continue Shopping' button
        public void clickContinueShopping() {
            continueShoppingButton.click();
        }

        // Verify if the user is navigated back to the product page
        public boolean isBackOnProductPage() {
            return driver.getCurrentUrl().contains("inventory.html");
        }

        // Verify if an added product appears in the cart
        public boolean isProductInCart(String productName) {
            for (WebElement product : products) {
                if (product.getText().equals(productName)) {
                    return true;
                }
            }
            return false;
            
        }
       
        
            // Continue to check outpage
       public CheckOutPageObject  goTocheckout() {
   		checkoutButton.click();
   		CheckOutPageObject checkout= new CheckOutPageObject (driver);
   		return checkout;
   	}  
       //continue to CheckoutOverview
       public CheckOutOverviewObject goToCheckOutOverview() {
    	   finishButton.click();
       	CheckOutOverviewObject  checkoutOverview= new CheckOutOverviewObject (driver);
   		return checkoutOverview;
   	}
       
        
    }

    
  
  
  
  

  

