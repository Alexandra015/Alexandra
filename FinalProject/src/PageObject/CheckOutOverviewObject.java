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

public class CheckOutOverviewObject {

    WebDriver driver;
    WebDriverWait wait;


    @FindBy(className="inventory_item_name")
    private List<WebElement> productList;

    @FindBy(className="summary_info")
    private WebElement summaryInfo;

    @FindBy(className="summary_total_label")
    private WebElement totalPrice;

    @FindBy(id="finish")
    private WebElement finishButton;

    @FindBy(id="cancel")
    private WebElement cancelButton;
    
    @FindBy(id = "first-name")
    WebElement firstNameInput;

    @FindBy(id = "last-name")
    WebElement lastNameInput;

    @FindBy(id = "postal-code")
    WebElement postalCodeInput;
    
    
    @FindBy(id="continue")
    private WebElement continueButton;
    
    
    
    
    
    

    // Constructor
    public CheckOutOverviewObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased timeout duration to 15 seconds
        PageFactory.initElements(driver, this);  // Initialize elements
    }
        public boolean isOverviewPageLoaded() {
            return summaryInfo.isDisplayed();
        }

        public boolean verifyAllProductDetailsDisplayed() {
            return productList.size() > 0;
        }

        public String getTotalPriceText() {
            return totalPrice.getText();
        }

        public void clickCancel() {
            cancelButton.click();
        }

        public void clickFinish() {
            finishButton.click();
        
    }
        

        
        public CheckOutOverviewObject goToCheckOutOverview() {
        	finishButton.click();
        	CheckOutOverviewObject  checkoutOverview= new CheckOutOverviewObject (driver);
    		return checkoutOverview;
    	}
        
        public void enterCheckoutInfo(String fname, String lname, String zip) {
        	
            firstNameInput.clear();
            lastNameInput.clear();
            postalCodeInput.clear();
            firstNameInput.sendKeys(fname);
            lastNameInput.sendKeys(lname);
            postalCodeInput.sendKeys(zip);
        }

        public CompleteOrderObject goToCompeleOrder() {
        	finishButton.click();
        	CompleteOrderObject  completeorder= new CompleteOrderObject (driver);
    		return completeorder;
    	}
        
           
    }

    
  
  
  
  