package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNG {
	static WebDriver driver;
	String browser;
	String url;

	public void readConfig() {
		// Buffered , InputStream, fileReader,Scanner
		Properties prop = new Properties();

		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used " + browser );
			url = prop.getProperty("url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void launchBrowser() {
		String browser = "chrome";
		if (browser.equalsIgnoreCase("Chrome")) { // we have double == in java
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("FireFox")) {
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			// we use firefox browser
			driver = new FirefoxDriver();
		}

		// go to website
		driver.get("https://www.techfios.com/billing/?ng=admin/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

// valid login Test
	@Test(priority=2)
	public void loginTest() throws InterruptedException {
		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Wrong Page");
		// ELEMENT LIBERIES
		WebElement USER_NAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement LOGIN_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@name='login']"));

		String loginID = "demo@techfios.com";
		String password = "abc123";
		USER_NAME_ELEMENT.sendKeys(loginID);
		PASSWORD_ELEMENT.sendKeys(password);
		LOGIN_BUTTON_ELEMENT.click();
		WebElement DASHBOARD_TITLE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(),'Dashboard')]"));
		Assert.assertEquals(DASHBOARD_TITLE_ELEMENT.getText(), "Dashboard", "Wrong Page");
	}
	@Test(priority=1)
	public void addCustomer() {
		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Wrong Page");
		// ELEMENT LIBERIES
		WebElement USER_NAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement LOGIN_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@name='login']"));

		String loginID = "demo@techfios.com";
		String password = "abc123";
		//Test data or mock data
		String fullName = "Testing_jan2021";
		String campanyName = "Google";
		String email = "demo@techfios.com";
		String phone= "12365445";
		
		
		USER_NAME_ELEMENT.sendKeys(loginID);
		PASSWORD_ELEMENT.sendKeys(password);
		LOGIN_BUTTON_ELEMENT.click();
		WebElement DASHBOARD_TITLE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(),'Dashboard')]"));
		Assert.assertEquals(DASHBOARD_TITLE_ELEMENT.getText(), "Dashboard", "Wrong Page");
		
		
		WebElement CONSUMER_ELEMENT = driver.findElement(By.xpath("//ul[@id=\"side-menu\"]/li[3]/a/span[1]"));
		CONSUMER_ELEMENT.click();
		WebElement ADDCUSTOMER_ELEMENT = driver.findElement(By.xpath("//ul[@id='side-menu']/li[3]/ul/li[1]/a"));
		
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.invisibilityOf(ADDCUSTOMER_ELEMENT));
		ADDCUSTOMER_ELEMENT.click();
		
		WebElement FULL_NAME_ELEMENT = driver.findElement(By.xpath("//INPUT[@id='account']"));
		WebElement COMPANY_DROPDOWN_ELEMENT = driver.findElement(By.xpath("//select[@id='cid']/option[22]"));
		WebElement EMAIL_ELEMENT = driver.findElement(By.xpath("//INPUT[@id='email']"));
		
		
		FULL_NAME_ELEMENT.sendKeys(fullName);
		//dropdown
		Select sel =new Select(COMPANY_DROPDOWN_ELEMENT);
		sel.selectByVisibleText(campanyName);
		
		COMPANY_DROPDOWN_ELEMENT.sendKeys(campanyName);
		//Generator Random Number
		Random rnd	= new Random();
		int randomNum = rnd.nextInt(999);
		EMAIL_ELEMENT.sendKeys(randomNum + email);
		
		//WebElement ADDCUSTOMER_ELEMENT = driver.findElement(By.xpath("//ul[@id='side-menu']/li[3]/ul/li[1]/a"));
		//WebElement ADDCUSTOMER_ELEMENT = driver.findElement(By.xpath("//ul[@id='side-menu']/li[3]/ul/li[1]/a"));

		
	}
		
		
	
	//@AfterMethod
	public void tearDown() {
		driver.close();
		// driver.quit();

	}

}
