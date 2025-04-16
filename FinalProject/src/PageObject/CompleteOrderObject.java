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

public class CompleteOrderObject extends AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;


 
    @FindBy(id="finish")
    private WebElement finishButton;

    @FindBy(id="back-to-products")
    private WebElement BackHomeButton;

    @FindBy(id = "first-name")
    WebElement firstNameInput;

    @FindBy(id = "last-name")
    WebElement lastNameInput;

    @FindBy(id = "postal-code")
    WebElement postalCodeInput;
    
    @FindBy(className = "complete-header")
    WebElement thankYouMessage;
    

    // Constructor
    public CompleteOrderObject(WebDriver driver) {
    	super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased timeout duration to 15 seconds
        PageFactory.initElements(driver, this);  // Initialize elements
    }
      

        public void clickFinish() {
            finishButton.click();
        
    }
         
        
        public void enterCheckoutInfo(String fname, String lname, String zip) {
        	
            firstNameInput.clear();
            lastNameInput.clear();
            postalCodeInput.clear();
            firstNameInput.sendKeys(fname);
            lastNameInput.sendKeys(lname);
            postalCodeInput.sendKeys(zip);
        }
        

        // Method to check if the Thank You message is displayed
        public boolean isThankYouMessageDisplayed() {
            return thankYouMessage.isDisplayed();
        }

        // Method to check if the Back Home button is displayed
        public boolean isBackHomeButtonDisplayed() {
            return BackHomeButton.isDisplayed();
        }

        
        public CompleteOrderObject VerifyBackHomeButton() {
        
        	BackHomeButton.click();
        	CompleteOrderObject  completeorder= new CompleteOrderObject (driver);
    		return completeorder;
        }
        
        //gotoComplete order
        public CompleteOrderObject goToCompeleOrder() {
        	finishButton.click();
        	CompleteOrderObject  completeorder= new CompleteOrderObject (driver);
    		return completeorder;
    	}
        
        public void Verifyfinish() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(finishButton));
            finishButton.click();
        }
        

    
        
           
    }

    
  
  
  
  