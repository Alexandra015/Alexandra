import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Excel.DataDriven;
import PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPageVerification {
    
    private WebDriver driver;
    private DataDriven d;
    private String username;
    private String password;
    private String lockedusername;
    private ArrayList<ArrayList<String>> userData;
    private LandingPage landingPage;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        d = new DataDriven();
        userData = d.getData();
        username = userData.get(0).get(0);
        password = userData.get(0).get(1);
        lockedusername = userData.get(1).get(0);
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.get("https://www.saucedemo.com/");
        landingPage = new LandingPage(driver); // Initialize Page Object
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyLoginPageElements() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        Assert.assertTrue(driver.findElement(By.id("user-name")).isDisplayed(), "Username field not displayed");
        Assert.assertTrue(driver.findElement(By.id("password")).isDisplayed(), "Password field not displayed");
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(), "Login button not displayed");
    }

    @Test
    public void verifyInvalidPasswordLogin() {
        landingPage.loginApplication(username, "wrong_password");
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for incorrect password");
    }

    @Test
    public void verifyInvalidUsernameLogin() {
        landingPage.loginApplication("wrong_username", password);
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for incorrect username");
    }

    @Test
    public void verifyEmptyUsernameLogin() {
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for empty username");
    }
    
    @Test
    public void verifyEmptyFilled() {
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("login-button")).click();
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for empty username");
    }


    @Test
    public void verifyEmptyPasswordLogin() {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("login-button")).click();
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for empty password");
    }

    @Test
    public void verifySuccessfulStandardUserLogin() {
        landingPage.loginApplication(username, password);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login Failed: Inventory page not loaded.");
    }

    @Test
    public void verifyLockedOutUserLogin() {
        landingPage.loginApplication(lockedusername, password);
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.getText().contains("locked out"), "Error message for locked-out user not displayed");
    }
}
