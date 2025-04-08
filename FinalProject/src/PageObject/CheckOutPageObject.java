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

public class CheckOutPageObject {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "Checkout")
    WebElement checkout;
    
    @FindBy(id = "first-name")
    WebElement firstNameInput;

    @FindBy(id = "last-name")
    WebElement lastNameInput;

    @FindBy(id = "postal-code")
    WebElement postalCodeInput;

    @FindBy(id = "continue")
    WebElement continueBtn;

    @FindBy(id = "cancel")
    WebElement cancelBtn;
    
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errorMessage;
    
    
    @FindBy(id="finish")
    private WebElement finishButton;
    
    

    // Constructor
    public CheckOutPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased timeout duration to 15 seconds
        PageFactory.initElements(driver, this);  // Initialize elements
    }
    

    public void enterCheckoutInfo(String fname, String lname, String zip) {
    	
        firstNameInput.clear();
        lastNameInput.clear();
        postalCodeInput.clear();
        firstNameInput.sendKeys(fname);
        lastNameInput.sendKeys(lname);
        postalCodeInput.sendKeys(zip);
    }

    public void clickContinue() {
    	
        continueBtn.click();
    }

    public void clickCancel() {
        cancelBtn.click();
    }

    public void waitForWebElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }
    
    
    //continue to  checkoutpageoverview
    public CheckOutOverviewObject goToCheckOutOverview() {
    	continueBtn.click();
    	CheckOutOverviewObject  checkoutOverview= new CheckOutOverviewObject (driver);
		return checkoutOverview;
    }
    
    
    
    }

    
  
  
  
  