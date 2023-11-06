package subscriptionManagement;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class SMTC1 extends UtilityClass {
	
	public static void main(String[] args) throws InterruptedException {
		
		launchBrowser();
		
		dropdownselect();
		
		waitpgm();
		
		driver.findElement(By.xpath("//span[@aria-describedby=\"cdk-describedby-message-972\"]")).click();
	}

}
