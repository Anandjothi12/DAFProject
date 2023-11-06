package OrganizationalDetails;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NonAdminTc extends BaseClass {


	@Test(priority=1)  
	public void TC2() throws IOException, InterruptedException  {
		
		Thread.sleep(5000);
		//verify the organisation info section exists
		boolean page = d.findElement(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']"))
				.getText().contains("Organisation Information");			
		if(page) {
			System.out.println("Organization Information section is displayed");
			WebElement organisationpage=d.findElement(By.xpath("//mat-sidenav-content[@class='mat-drawer-content mat-sidenav-content']"));
			File src1=organisationpage.getScreenshotAs(OutputType.FILE);
			File trg1=new File(".\\organisation relationship management\\TC_001\\pic.png");
			FileUtils.copyFile(src1,trg1);
		} 
	}

		@Test(priority=2)
		public void TC3_1() throws IOException  {

			WebElement page=d.findElement(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']"));

			//verifying the existence of id & name fields in page 
			boolean verify = page.getText().contains("ID")&& page.getText().contains("Name");	
			if(verify)
			{
				System.out.println("Organization Information section contains id & name fields");

				//print the data in page
				System.out.println(d.findElement(By.xpath("//*[@class='full-width margin-bottom-xlg']")).getText());
			}	
		}
	
		@Test(priority=3)
		public void TC3_2() throws IOException  {
			//verify edit icon 
			List<WebElement> editicons = d.findElements(By.xpath("//mat-icon[@class='mat-icon notranslate material-icons mat-icon-no-color ng-star-inserted']"));		
			boolean verify= editicons.isEmpty();
			Assert.assertTrue(verify, "Edit icon does not exists.");
            
			WebElement eidtability=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-admin/app-organisation-details/div/mat-card"));
			File src=eidtability.getScreenshotAs(OutputType.FILE);
			File trg=new File(".\\organisation relationship management\\TC_003\\pic.png");
			FileUtils.copyFile(src,trg);
		} 

		@Test(priority=4)
		public void TC3_3() throws IOException {
			//step4(check for back button)
			WebElement button=d.findElement(By.xpath("//span[contains(text(), 'Back')]"));
			button.click();
			System.out.println("Button exists on the webpage.");
		}
		

}
