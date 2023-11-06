package subscriptionManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class dropdowncheck {
	
	public static ChromeDriver d;
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		d = new ChromeDriver();
		
		d.get("https://portal.tst2.ct2.atos.net/#/auth/login");

        Thread.sleep(2000);

        d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-authentication/app-login/footer/div[1]/div[2]/button/span[1]")).click();

        d.findElement(By.xpath("//*[@id=\"mat-input-0\"]")).sendKeys("jothilakshmi.anand@atos.net");

        d.findElement(By.xpath("//input[@id='mat-input-1']")).sendKeys("Jothilakshmi@0308");

        d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-authentication/app-login/div/div/div/div/div[4]/div/form/div/button")).click();

        Thread.sleep(10000);

        d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-login-dialog/div/form/mat-card/mat-card-content/div[3]/div/mat-form-field/div/div[1]/div[3]")).click();

        d.findElement(By.xpath("//*[@role='option'][12]")).click();

        d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-login-dialog/div/form/mat-card/mat-card-actions/div/button[2]")).click();
	}

}
