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

public class CartPageObject {

    WebDriver driver;
    WebDriverWait wait;

    
    ////Locators
    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartItemCount;

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
    

    // Find all "Remove" buttons on the cart page (or product page)
    @FindBy(xpath = "//button[contains(text(),'Remove')]")
    List<WebElement> removeButtons;
    
    @FindBy(css=".inventory_item_price")
    List <WebElement> productPrice;
    
    @FindBy(id="finish")
    private WebElement finishButton;
    

    // Constructor
    public CartPageObject(WebDriver driver) {
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

    
    public void waitWebElementToAppear(List<WebElement> elements) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
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
    ///////
    ///cartpage
    
    public boolean areMultipleProductsInCart() {
        return cartItems.size() > 1; // If more than one cart item exists, return true
    }
    
 // ✅ 6. Verify if product details are correct
    public boolean areProductDetailsCorrect() {
        return cartItems.size() > 0 && !cartQuantities.isEmpty() && !products.isEmpty();
    }

    // ✅ 7. Remove an item from the cart
    public void removeItemFromCartPage() {
        if (!removeButtons.isEmpty()) {
            removeButtons.get(0).click();  // Removes the first item in the cart
        }
    }

    // ✅ 8. Check if cart count updated
    public boolean isCartCountUpdated(int expectedCount) {
        try {
            return Integer.parseInt(cartItemCount.getText()) == expectedCount;
        } catch (NoSuchElementException e) {
            return expectedCount == 0; // If count is 0, element may not be visible
        }
    }

    // ✅ 9. Remove all items from cart
    public void removeAllItemsFromCart() {
        for (WebElement removeButton : removeButtons) {
            removeButton.click();
        }
    }

    // ✅ 10. Verify if cart is empty
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
        
            public CartPageObject VerifyCartpage() {
       // Example of navigating to the cart page
       WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
       cartIcon.click();  // Click the cart icon to go to the cart page

       // Return an instance of CartPageObject after navigating to the cart page
       return new CartPageObject(driver);

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

    
  
  
  
  

     // Method to get hamburger menu options as a list of strings
  /*  public List<String> getHamburgerMenuOptions() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-menu-wrap"))); 
            List<WebElement> menuItems = getHamburgerMenuElements();
            waitWebElementListAppear(menuItems); 

            return menuItems.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        } catch (TimeoutException e) {
            System.out.println("Menu options did not appear in time!");
            return List.of();
        }
    }

    // Helper method to wait for a list of elements to appear
    public void waitWebElementListAppear(List<WebElement> elements) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (StaleElementReferenceException e) {
            System.out.println("Elements were not stable. Retrying...");
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(elements)));
        }
    }

    // Helper method to check if an element is displayed
    private boolean isElementDisplayed(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        } */
    

