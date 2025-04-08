package abstractcomponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import cattleyanadora.CartPage;
//import cattleyanadora.OrderPage;

//this will be the greatest parent class
//use this when something has a common header
public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//you can add the headers here (ex.navbar)
	@FindBy(css = "[routerlink*='cart']")
	WebElement carHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
/*	public CartPage goToCartPage() {
		carHeader.click();
		CartPage cartPage = new CartPage (driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage (driver);
		return orderPage;
	}
	*/
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(1000);		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		
		//you can ask the dev for the locator of the loading screen
		//visibility- until it appeared
		//invinsibility- until it disappears 
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	

    @FindBy(css=".inventory_item")
    List <WebElement> products;
    
    @FindBy(css=".shopping_cart_badge")
    WebElement AddtocartIcon;
    
   
}
