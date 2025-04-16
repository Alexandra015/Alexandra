package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	public  LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//WebElement userEmail =	driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="user-name")
	WebElement userEmail;
	
	@FindBy(id="password")
	WebElement userPasswordEle;

	@FindBy(id="login-button")
	WebElement submit;
	


	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue  loginApplication(String username, String password) 
	{
	
		userEmail.sendKeys(username);
		userPasswordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		 return errorMessage.getText();
	}
	
	public void  goTo()
	{
		driver.get("https://www.saucedemo.com");
		
		
	}
}
