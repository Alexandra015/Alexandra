package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

import java.util.List;

public class ProductList extends AbstractComponent{

	    WebDriver driver;

	    @FindBy(className = "inventory_item_name")
	    private List<WebElement> productTitle;

	    @FindBy(className = "inventory_item_price")
	    private List<WebElement> productPrice;

	    @FindBy(css = "button[class='btn_inventory']")
	    private WebElement addToCartButton;

	    @FindBy(className = "inventory_item")
	    private List<WebElement> productList;

	    @FindBy(className = "product_sort_container")
	    private WebElement sortDropdown;
	    
	    
	    
	    

	    public ProductList(WebDriver driver) {
	    	super(driver);
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        
	    }
	    
	    public void  goTo()
		{
			driver.get("https://www.saucedemo.com");
			
			
		}

	    public List<WebElement> getProductList() {
	        return productList;
	    }

	    public void addToCart(String productName) {
	        WebElement product = driver.findElement(By.xpath("//div[text()='" + productName + "']/following-sibling::div//button"));
	        product.click();
	    }

	    public boolean isProductDetailsDisplayed(String productName) {
	        WebElement product = driver.findElement(By.xpath("//div[text()='" + productName + "']"));
	        return product.isDisplayed();
	    }

	    public void sortProductsByNameAsc() {
	        sortDropdown.click();
	        driver.findElement(By.className("product_sort_container")).click();
	    }

	    public void sortProductsByNameDesc() {
	        sortDropdown.click();
	        driver.findElement(By.className("product_sort_container")).click();
	    }

	    public void sortProductsByPriceLowToHigh() {
	        sortDropdown.click();
	        driver.findElement(By.className("product_sort_container")).click();
	    }

	    public void sortProductsByPriceHighToLow() {
	        sortDropdown.click();
	        driver.findElement(By.className("product_sort_container")).click();
	    }

	    public boolean isProductsSortedByNameAsc() {
	        // Add logic for sorting validation
	        return true;
	    }

	    public boolean isProductsSortedByNameDesc() {
	        // Add logic for sorting validation
	        return true;
	    }

	    public boolean isProductsSortedByPriceLowToHigh() {
	        // Add logic for sorting validation
	        return true;
	    }

	    public boolean isProductsSortedByPriceHighToLow() {
	        // Add logic for sorting validation
	        return true;
	    }
	}

