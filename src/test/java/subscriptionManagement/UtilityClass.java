package subscriptionManagement;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class UtilityClass {
	
	public static ChromeDriver driver;
	public static Alert al;
	public static Select s;
	
	public static void launchBrowser() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
			
		driver.get("https://portal.tst2.ct2.eviden.com/#/auth/login");
		driver.manage().window().maximize();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//span[text() = 'Accept To Login']")).click();
		driver.findElement(By.xpath("//input[@placeholder='User Name']")).sendKeys("jothilakshmi.anand@atos.net");
	    driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Jothilakshmi@0308");
	    driver.findElement(By.xpath("//span[text() = 'Login']")).click();
	}
	    
	public static void dropdownselect() throws InterruptedException {
		
		//Selecting Organization and Roles
		
		Thread.sleep(10000);
		
		// Find the dropdown element by its ID or any other locator strategy
        WebElement dropdown = driver.findElement(By.xpath("//mat-select[@formcontrolname=\"organization\"]"));

        // Click on the dropdown element to open the dropdown menu
        dropdown.click();
        
        Actions actions1 = new Actions(driver);
        // actions.moveToElement(dropdown).click().sendKeys(Keys.ARROW_DOWN).perform();
         
         actions1.moveByOffset(0, 20).sendKeys(Keys.ARROW_DOWN).perform();

        // Find the desired option in the dropdown menu by its text
        WebElement option = driver.findElement(By.xpath("//mat-option/span[contains(text(), 'Jan de Rijk Services BV ')]"));
        option.click();
        
        WebElement dropdown1 = driver.findElement(By.xpath("//mat-select[@formcontrolname=\"role\"]"));

        // Click on the dropdown element to open the dropdown menu
        dropdown1.click();
        
        Actions actions = new Actions(driver);
       // actions.moveToElement(dropdown1).click().sendKeys(Keys.ARROW_DOWN).perform();
        
        actions.moveByOffset(0, 20).sendKeys(Keys.ARROW_DOWN).perform();

        // Find the desired option in the dropdown menu by its text
        WebElement option1 = driver.findElement(By.xpath("//mat-option/span[contains(text(), 'CERT_Global Admin ')]"));
        option1.click();
        
        driver.findElement(By.xpath("(//span[@class = \"mat-button-wrapper\"])[4]")).click();
	   
	}
	
	public static void waitpgm() {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
	}

}
