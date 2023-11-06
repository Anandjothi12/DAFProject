package OrganizationalDetails;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

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
        d.findElement(By.xpath("//mat-select[@formcontrolname='organization']")).click();
		d.findElement(By.xpath("//mat-option/span[contains(text(), 'DAF Connect')]")).click();
		
		//Selecting role from dropdown   
		d.findElement(By.xpath("//mat-select[@formcontrolname='role']")).click();
		d.findElement(By.xpath("//mat-option/span[contains(text(), 'CERT_Office Staff')]")).click();
		
		//Clicking confirm button
		d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-login-dialog/div/form/mat-card/mat-card-actions/div/button[2]")).click();
       
        WebDriverWait wait = new WebDriverWait(d, 50);

       //clicking admin
        WebElement adminLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//span[contains(text(), 'Admin')]")));
        adminLink.click();

        //organisation menu
        WebElement OrganisationLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/span[contains(text(), 'Organisation details and preferences')]")));
        OrganisationLink.click();
    }

    @AfterSuite
    public void tearDown() {
        if (d != null) {
        
        	softassert.assertAll();
        	d.quit();
        }
    }


}
