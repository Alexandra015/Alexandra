package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalogue {

    WebDriver driver;
    WebDriverWait wait;

    // Find all "Add to cart" buttons on the page
    @FindBy(xpath = "//button[contains(text(),'Add to cart')]")
    public List<WebElement> addToCartButtons;
    
    

    // Find all "Remove" buttons on the cart page (or product page)
    @FindBy(xpath = "//button[contains(text(),'Remove')]")
    List<WebElement> removeButtons;
    
    @FindBy(xpath = "//button[contains(text(),'Remove')]")
    WebElement  removeButton;
    
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement  AddButton;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    WebElement shoppingCartBadge;
    //productTitle
    @FindBy(css=".inventory_item_name")
    List <WebElement> products;
    
    @FindBy(css=".inventory_item_desc")
    List <WebElement> productDesc;
    
    @FindBy(css=".inventory_item_img")
    List <WebElement> productImg;
    
    @FindBy(css=".inventory_item_price")
    List <WebElement> productPrice;
    
//cart page
    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;
    
    
     @FindBy(xpath="//button[contains(text(),'Remove')]")
    WebElement removebutton;
    
    
    @FindBy(id="react-burger-menu-btn")
    WebElement Menubar;
    
    
    @FindBy(css=".bm-menu-wrap")
    List<WebElement>  MenubarOption;
    
    
    @FindBy(css=".bm-item.menu-item")
	List <WebElement> navbar;
    
    
    @FindBy(id="react-burger-cross-btn")
    WebElement Closebar;
    
    /////
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

//Check Out Overview
    
    @FindBy(id="finish")
    private WebElement finishButton;
    

    
    By item = By.cssSelector(".inventory_item_name");
    By addTocart = By.xpath("//button[contains(text(),'Add to cart')]");
    By removeTocart = By.xpath("//button[contains(text(),'Remove')]");
    
  

    public ProductCatalogue(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public WebElement VerifytheProductName(String productName) throws InterruptedException {
        
        WebElement prod = getProductList().stream().filter(product->
        product.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName)).findFirst().orElse(null);
        
        return prod;
    }

    // Add all items to the cart
    
    public void VerifytheProductDetailPage(String productName) throws InterruptedException {
        WebElement prod = VerifytheProductName(productName);
        prod.findElement(item).click();
        
    }
    
    public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addTocart).click();

}

    public List<WebElement> getProductList() {
        return products; //gets the product
    }
    
    //removebutton 
    public String getRemoveButtonText() {
        // Wait until the 'Remove' button is visible or clickable
        if (removeButton.isDisplayed()) {
            return removeButton.getText();
        } else {
            return null; // Return null if the 'Remove' button is not visible
        }
    }
    

   /* public void addToCart(String productName) {
    	WebElement product = driver.findElement());
    	product.click();
    } */
    
    public WebElement getProductByName(String productName) throws InterruptedException {
		
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName)).findFirst().orElse(null);
		
	    return prod;
	}
    
   public void VerifyaddtocartDet() throws InterruptedException {
       WebElement addToCartButton = driver.findElement(addTocart); 
       addToCartButton.click();
       Thread.sleep(3000);
   }
   
   public String VerifyCount() {
	      
       try {
           String Count= shoppingCartBadge.getText();
           return Count;
       } catch (Exception e) {
           return "0"; 
       }
   }
     
   //sortoption
       
   public void sortProductsByNameAtoZ() {
	    Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
	    dropdown.selectByVisibleText("Name (A to Z)");
	}

	public void sortProductsByNameZtoA() {
	    Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
	    dropdown.selectByVisibleText("Name (Z to A)");
	}

	public void sortProductsByPriceHighToLow() {
	    Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
	    dropdown.selectByVisibleText("Price (high to low)");
	}
	
	
	public void sortProductsByPriceLowToHigh() {
	    Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
	    dropdown.selectByVisibleText("Price (low to high)");
	}
	public boolean isProductsSortedByPriceLowToHigh() {
	    List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
	    List<Double> prices = priceElements.stream()
	        .map(e -> Double.parseDouble(e.getText().replace("$", "")))
	        .collect(Collectors.toList());

	    List<Double> sortedPrices = new ArrayList<>(prices);
	    Collections.sort(sortedPrices);

	    return prices.equals(sortedPrices);
	    
	   
	}
	
	public boolean isProductsSortedByPriceHighToLow() {
	    List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
	    List<Double> prices = priceElements.stream()
	        .map(e -> Double.parseDouble(e.getText().replace("$", "")))
	        .collect(Collectors.toList());

	    List<Double> sortedPrices = new ArrayList<>(prices);
	    sortedPrices.sort(Collections.reverseOrder());

	    return prices.equals(sortedPrices);
	}
   
	public boolean isProductsSortedByNameAsc() {
	    List<WebElement> nameElements = driver.findElements(By.className("inventory_item_name"));
	    List<String> names = nameElements.stream()
	        .map(WebElement::getText)
	        .collect(Collectors.toList());

	    List<String> sortedNames = new ArrayList<>(names);
	    Collections.sort(sortedNames);

	    return names.equals(sortedNames);
	}
	
	public boolean isProductsSortedByNameDesc() {
	    List<WebElement> nameElements = driver.findElements(By.className("inventory_item_name"));
	    List<String> names = nameElements.stream()
	        .map(WebElement::getText)
	        .collect(Collectors.toList());

	    List<String> sortedNames = new ArrayList<>(names);
	    sortedNames.sort(Collections.reverseOrder());

	    return names.equals(sortedNames);
	}
	
	
	
	
	//Cart funtionality
	
//hamburger 
    // Remove an item from the cart (removes the first item)
    public void removeItemFromCart() throws InterruptedException {
       Thread.sleep(2000);
       removebutton.click();
       
    }  
    public void verifyHamburgerMenu() throws InterruptedException {
        // Click the hamburger menu button 
    	Thread.sleep(2000);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Menubar.click(); 

        // Verify that all menu options are visible
        List<WebElement> menuOptions = MenubarOption;
        Assert.assertTrue(menuOptions.size() > 0, "Menu options are not visible.");
    }	
    
    
    // hamburger menu 

	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	

	public void waitForWebElementToDisappear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
    
    
    public Boolean VeirfyNavbarCheck() throws InterruptedException {
 	  return Menubar.isDisplayed();
    
 	}
    
    public void waitWebElementListAppear(List<WebElement> elements) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public void waitWebElementListDisappear(List<WebElement> elements) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}
	
	public List<WebElement> NavbarItemsCheck() {
		waitWebElementListAppear(navbar);
		   return navbar;
	   
	}
	
	public void OpenNavbar() throws InterruptedException {
		Menubar.click();
		Thread.sleep(1000);
	   
	}
	
	public void CloseBar() throws InterruptedException {
		Closebar.click();
	}
	
	
	public int getCartItemCount() {
        String itemCountText = shoppingCartBadge.getText();
        return Integer.parseInt(itemCountText);  // Convert the cart count to an integer
    }
    
    // Method to add all items to the cart
    public void addAllItemsToCart() {
        for (WebElement addButton : addToCartButtons) {
            addButton.click();
            // Click each "Add to cart" button
        }
    }
        
        
   //product list features 
        
      public void CheckAddtoCart()
      {
    	  //
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    WebElement AddButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-sauce-labs-backpack")));
    	  AddButton.click();
      }
     
        public void CheckProductFeatures() {
        	 printDisplayStatus("Descriptions", productDesc);
             printDisplayStatus("Product Prices", productPrice);
             printDisplayStatus("Product Titles", products);
             printDisplayStatus("Product Images", productImg);
             printDisplayStatus("Cart Buttons", addToCartButtons);
        }

        private void printDisplayStatus(String label, List<WebElement> elements) {
                System.out.println(label + ": " + elements.get(0).isDisplayed());
            }
        ////
     /*   public void VerifyCartpage()
        {
      	  cartIcon.click();
        }  */
        

        public CartPageObject VerifyCartpage() {
            // Example of navigating to the cart page
            WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
            cartIcon.click();  // Click the cart icon to go to the cart page

            // Return an instance of CartPageObject after navigating to the cart page
            return new CartPageObject(driver);
       
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
        

        // Verify if multiple products are displayed in the cart
        public boolean areMultipleProductsInCart() {
        	
            return cartItems.size() > 1;
        }

        // Verify product details in the cart
        public boolean areProductDetailsCorrect() {
            return cartQuantities.size() > 0 && products.size() > 0
                    && productDesc.size() > 0 && productPrice.size() > 0;
        }

        // Remove an item from the cart and check if cart count updates
        public void removeItemFromCartPage() {
            if (!removeButtons.isEmpty()) {
                removeButtons.get(0).click();
            }
        }

        public boolean isCartCountUpdated(int expectedCount) {
            return Integer.parseInt(shoppingCartBadge.getText()) == expectedCount;
        }

        // Remove all items from the cart
        public void removeAllItemsFromCart() {
            while (!removeButtons.isEmpty()) {
                removeButtons.get(0).click();
            }
        }

        public boolean isCartEmpty() {
            return cartItems.isEmpty();
        }

        public void addItemToCartMultipleTimes(String productName, int quantity) {
            for (int i = 0; i < quantity; i++) {
                WebElement addToCartButton = driver.findElement(By.id("add-to-cart-" + productName));
                addToCartButton.click();
            }
        }
        
       //continue to checkout page
        public CheckOutPageObject goTocheckout() {
       		checkoutButton.click();
       		CheckOutPageObject checkout= new CheckOutPageObject (driver);
       		return checkout;
       	}  
        //continue to  cartpage
        public CartPageObject goToCartpage() {
        	cartIcon.click();
    		CartPageObject  cartpage= new CartPageObject (driver);
    		return cartpage;
    	}
     
        //continue to  checkoupage
        public CheckOutOverviewObject goToCheckOutOverview() {
        	finishButton.click();
        	CheckOutOverviewObject  checkoutOverview= new CheckOutOverviewObject (driver);
    		return checkoutOverview;
    	}

         
}


