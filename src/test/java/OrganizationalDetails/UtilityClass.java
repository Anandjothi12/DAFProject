package OrganizationalDetails;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UtilityClass {
	
	WebDriver d;
    SoftAssert softassert=new SoftAssert();
    @BeforeSuite
    public void setUpDriver() {
    	WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        d.manage().window().maximize();
    }

    @BeforeClass
    public void acceptLoginCookie() {
        d.get("https://portal.tst2.ct2.atos.net/#/auth/login");
        d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-authentication/app-login/footer/div[1]/div[2]/button/span[1]")).click();
    }

    @Test
    @Parameters({"username", "password"})
    public void login(String username, String password) throws InterruptedException {
        // Your login code here using the parameters
        d.findElement(By.xpath("//input[@placeholder='User Name']")).sendKeys(username);
        d.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
       
        //login button
        d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-authentication/app-login/div/div/div/div/div[4]/div/form/div/button")).click();

        
        //selecting organisation from dropdown 
        Thread.sleep(5000);
       // d.findElement(By.xpath("//mat-select[@formcontrolname='organization']")).click();
		//d.findElement(By.xpath("//mat-option/span[contains(text(), 'DAF Connect')]")).click();
		
		//Selecting role from dropdown 
		//d.findElement(By.xpath("//mat-select[@formcontrolname='role']")).click();
		//d.findElement(By.xpath("//mat-option/span[contains(text(), 'Platform Admin')]")).click();
        
        WebElement dropdown = d.findElement(By.xpath("//mat-select[@formcontrolname=\"organization\"]"));

        // Click on the dropdown element to open the dropdown menu
        dropdown.click();
        
        Actions actions1 = new Actions(d);
        // actions.moveToElement(dropdown).click().sendKeys(Keys.ARROW_DOWN).perform();
         
         actions1.moveByOffset(0, 20).sendKeys(Keys.ARROW_DOWN).perform();

        // Find the desired option in the dropdown menu by its text
        WebElement option = d.findElement(By.xpath("//mat-option/span[contains(text(), 'DAF Connect ')]"));
        option.click();
        
        WebElement dropdown1 = d.findElement(By.xpath("//mat-select[@formcontrolname=\"role\"]"));

        // Click on the dropdown element to open the dropdown menu
        dropdown1.click();
        
        Actions actions = new Actions(d);
       // actions.moveToElement(dropdown1).click().sendKeys(Keys.ARROW_DOWN).perform();
        
        actions.moveByOffset(0, 20).sendKeys(Keys.ARROW_DOWN).perform();

        // Find the desired option in the dropdown menu by its text
        WebElement option1 = d.findElement(By.xpath("//mat-option/span[contains(text(), 'Platform Admin ')]"));
        option1.click();
        
		
		//Clicking confirm button
		d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-login-dialog/div/form/mat-card/mat-card-actions/div/button[2]")).click();
       
       // WebDriverWait wait = new WebDriverWait(d, 100);

		
		Thread.sleep(10000);
       //clicking admin
        WebElement adminLink = d.findElement(By.xpath("//a//span[contains(text(), 'Admin')]"));
        adminLink.click();

        //organisation menu
        WebElement OrganisationLink = d.findElement(By.xpath("//a/span[contains(text(), 'Organisation details and preferences')]"));
        OrganisationLink.click();
    }

    @AfterSuite
    public void tearDown() {
        if (d != null) {
        
        	softassert.assertAll();
        	d.quit();
        }
    }
    
    public void logout() {
    	
    	WebElement logout=d.findElement(By.xpath("//mat-icon[@class='mat-icon notranslate mat-tooltip-trigger logoutIcon material-icons mat-icon-no-color']"));
    	logout.click();
    }

    


}
