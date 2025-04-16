package BaseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import Excel.DataDriven;
import PageObject.LandingPage;
import PageObject.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

		public WebDriver driver;
		public LandingPage landingPage;
		public ProductCatalogue productCatalogue;
		DataDriven d;
		public String username;
		public String password;
		public String lockedusername;
		public ArrayList<ArrayList<String>> userData;
		public String LastName;
		public String FirstName;
		public String ZipCode;
		
		public WebDriver intializeDriver() throws IOException {
			if (driver == null) {
		
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\global\\globalproperties.properties");
			prop.load(fis);
			String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
			prop.getProperty("browser");
			
			if (browserName.contains("chrome")) {		
			ChromeOptions options = new ChromeOptions();
			 options.addArguments("--incognito"); 
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			}
			return driver;
		}
	
		
		
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
	    if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
	        driver = intializeDriver();  
	    }
	    
	    LandingPage landingPage = new LandingPage(driver);
	    landingPage.goTo();
	    return landingPage; 
		
	} 
		
		

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
	    if (driver != null) {
	        try {
	            driver.quit();
	        } catch (Exception ignored) {
	        }
	        driver = null;  
	    }
	}
	

	}
	

